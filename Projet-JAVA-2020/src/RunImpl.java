import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class RunImpl implements Runnable {
    private Map<Integer, GenericTree<FileData>> map_threads_of_discussion;
    private List<Message> messageList;
    private Timer timer = new Timer();

    public void computeValueOfImportance(GenericTreeNode<FileData> root){
        if(((Message) root.getData()).isActive()){
            int value_of_importance = root.getData().getScore();

            for(GenericTreeNode<FileData> node : root.getChildren()){
                value_of_importance += node.getData().getScore();
            }

            ((Message) root.getData()).setValueOfImportance(value_of_importance);
        }
    }

    private void scheduleScoreDecrease(GenericTreeNode<FileData> node, GenericTree<FileData> tree, int time){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                node.getData().decreaseScoreByOne();
                // On n'oublie pas de recalculer la VOI du thread
                computeValueOfImportance(tree.getRoot());
            }
        }, time, time);
    }

    private void doAllTheWork(String strData){
        // On patiente 1-3s avant de réaliser les opérations
        wait_random_time(1000,3000);

        // On délimite la ligne lue afin de séparer les différentes valeurs pour les stocker dans un tableau
        String[] r = strData.split("\\|", -1);
        // On crée ensuite une variable de type FileData, qui peut désigner soit un commentaire, soit un message
        FileData data = createData(r);

        // On crée un noeud contenant le message/commentaire lu
        GenericTreeNode<FileData> node = new GenericTreeNode<>(data);

        // Si la ligne lue est un message, on exécute les opérations suivantes
        if(data instanceof Message){
            // On crée un nouveau thread de discussion (TdD), dont la racine sera le message lu
            GenericTree<FileData> thread_of_discussion = new GenericTree<>();
            thread_of_discussion.setRoot(node);

            // On programme la diminution du score du message de 1 toutes les 30 secondes
            // Et on met à jour la Value of Importance du thread
            scheduleScoreDecrease(node, thread_of_discussion, 30000);

            // On ajoute ensuite ce TdD à la map de tous les TdD
            map_threads_of_discussion.put(data.getId(), thread_of_discussion);

            // Et enfin on ajoute le message à liste des messages
            messageList.add((Message) data);
        }

        // Sinon (donc si la ligne lue est un commentaire), on exécute les opérations suivantes
        else{

            // Si le commentaire répond à un autre commentaire, on cherche dans quel thread ce dernier se situe
            // dans le but de récupérer le message racine du thread pour modifier la value of importance
            if(((Comment) data).is_a_comment_to_another_comment()){
                GenericTree<FileData> thread_of_discussion = findThread(((Comment) data).getPidComment());
                GenericTreeNode<FileData> root = thread_of_discussion.getRoot();

                // On ajoute le nouveau noeud enfant à notre arbre
                root.addChild(node);
                thread_of_discussion.setRoot(root);

                // On programme la diminution du score du commentaire de 1 toutes les 30 secondes
                // Et on met à jour la Value of Importance du thread
                scheduleScoreDecrease(node, thread_of_discussion, 30000);

                // On calcule le nouveau score du message racine
                computeValueOfImportance(thread_of_discussion.getRoot());
            }

            // Si le commentaire répond à un message, on récupère le noeud racine et on met à jour la value of importance
            else{
                // On récupère le thread de discussion (TdD) dont l'ID de la racine correspond au PID du message auquel le commentaire répond
                GenericTree<FileData> thread_of_discussion = map_threads_of_discussion.get(((Comment) data).getPidMessage());
                GenericTreeNode<FileData> root = thread_of_discussion.getRoot();

                // On ajoute le nouveau noeud enfant à notre arbre
                root.addChild(node);
                thread_of_discussion.setRoot(root);

                // On programme la diminution du score du commentaire de 1 toutes les 30 secondes
                // Et on met à jour la Value of Importance du thread
                scheduleScoreDecrease(node, thread_of_discussion, 30000);

                // On calcule le nouveau score du message racine
                computeValueOfImportance(thread_of_discussion.getRoot());
            }
        }



        // DEBUG
        System.out.println("-----------------");
        System.out.println(getThreeBestMessagesToString());
        /*for(Message m : getThreeBestMessages()){
            m.printMsg();
        }*/
    }

    public GenericTree<FileData> findThread(int idComment){
        for(Map.Entry<Integer, GenericTree<FileData>> thread : map_threads_of_discussion.entrySet()){
            GenericTree<FileData> curr = thread.getValue();
            if(curr.find(idComment) != null)
                return curr;
        }
        return null;
    }

    @Override
    public void run() {
        // On crée une map contenant la liste de tous les threads de discussion, identifiés par l'ID du message racine
        map_threads_of_discussion = new HashMap<>();
        // On crée une liste contenant les messages
        messageList = new ArrayList<>();

        // On lit chaque ligne du fichier reseauSocial.txt, et on exécute la methode doAllTheWork pour chacune d'entre elles
        try(Stream<String> stream = Files.lines(Paths.get("./src/reseauSocial.txt"))){
            stream.forEach(this::doAllTheWork);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getThreeBestMessagesToString(){
        StringBuilder result = new StringBuilder();
        for(Message m : getThreeBestMessages()){
            result.append(m.getId() + "|" + m.getIdUser() + "|");
        }
        return String.valueOf(result);
    }

    public List<Message> getThreeBestMessages(){
        // On trie la liste des messages dans l'ordre décroissant des scores pour récupérer les 3 meilleurs messages
        messageList.sort(Comparator.comparingInt(Message::getValueOfImportance).reversed());

        List<Message> three_best_messages = new ArrayList<>(3);

        if(messageList.size() <= 3) three_best_messages.addAll(messageList);
        else for (int i = 0; i < 3; i++) three_best_messages.add(messageList.get(i));

        return three_best_messages;
    }

    public void wait_random_time(int origin, int bound){
        int random_time = ThreadLocalRandom.current().nextInt(origin,bound);
        try {
            Thread.sleep(random_time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public FileData createData(String[] line){
        // Throws error if line.length != 6

        if(line[4].equals("") && line[5].equals("")){
            int idMessage = Integer.parseInt(line[0]),
                    idUser = Integer.parseInt(line[1]);
            String message = line[2],
                    user = line[3];

            return new Message(idMessage, idUser, message, user);
        }

        else if(line[5].equals("")){
            int idComment = Integer.parseInt(line[0]),
                    idUser = Integer.parseInt(line[1]),
                    pidComment = Integer.parseInt(line[4]);
            String comment = line[2],
                    user = line[3];

            return new Comment(idComment, idUser, comment, user, pidComment, true);

        }

        else {
            int idComment = Integer.parseInt(line[0]),
                    idUser = Integer.parseInt(line[1]),
                    pidMessage = Integer.parseInt(line[5]);
            String comment = line[2],
                    user = line[3];

            return new Comment(idComment, idUser, comment, user, pidMessage, false);
        }

    }
}
