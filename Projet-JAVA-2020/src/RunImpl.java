import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RunImpl implements Runnable {
    List<String> lines; // Liste de chaque ligne du fichier reseauSocial.txt
    Map<Integer, LineOfFile> line_map;
    List<LineOfFile> three_best_messages;

    @Override
    public void run() {
        // Lecture unique de toutes les lignes du fichier reseauSocial.txt
        try {
            lines = Files.readAllLines(Paths.get("./src/reseauSocial.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // On crée une map contenant la liste des lignes du fichiers, identifiées par leur ID (Integer)
        line_map = new HashMap<>();
        // On crée une liste pouvant contenir au maximum 3 messages (ceux avec le score le plus élevé)
        three_best_messages = new ArrayList<>(3);

        // On lit chaque ligne du fichier toutes les 1 à 3s et on réalise les opérations nécessaires
        for(String line : lines){
            wait_random_time(1000,3000);
            String[] r = line.split("\\|", -1);
            LineOfFile msg = createMsg(r);

            // On met le message dans la table de hachage, identifié par son ID
            line_map.put(msg.getId(), msg);

            // On insère le message dans l'ArrayList des 3 meilleurs messages si son score est suffisant
            if(three_best_messages.size() < 3)
                three_best_messages.add(msg);
            else if(msg.getScore() > three_best_messages.get(0).getScore())
                three_best_messages.set(0, msg);

            // On trie ensuite le tableau dans l'ordre croissant pour récupérer le score min. plus facilement plus tard
            three_best_messages.sort(Comparator.comparingInt(LineOfFile::getScore));

            // On met à jour le score du message parent si la ligne lue était un commentaire
            if(msg instanceof Comment)
                updateScoreParent((Comment) msg);

            System.out.println("-----------------");
            for(LineOfFile l : three_best_messages)
                l.printMsg();


        }
    }

    public void updateScoreParent(Comment msg){
        // On récupère le message parent
        int pidParent = msg.getPidParent();
        LineOfFile msgParent = line_map.get(pidParent);

        // S'il était déjà présent dans le tableau des 3 meilleurs messages, on met à jour son score et on retrie
        if(three_best_messages.contains(msgParent)){
            three_best_messages.indexOf(msgParent);
            msgParent.addToScore(msg.getScore());
            line_map.replace(pidParent, msgParent);
            three_best_messages.sort(Comparator.comparingInt(LineOfFile::getScore));
        }

        else{
            // On met à jour le score du message parent
            msgParent.addToScore(msg.getScore());
            line_map.replace(pidParent, msgParent);

            // On vérifie si son score lui permet de rentrer dans le tableau des 3 meilleurs messages
            // Puis on retrie le tableau selon le score
            if(msgParent.getScore() > three_best_messages.get(0).getScore()){
                three_best_messages.set(0, msgParent);
                three_best_messages.sort(Comparator.comparingInt(LineOfFile::getScore));
            }
        }
    }

    public void wait_random_time(int origin, int bound){
        int random_time = ThreadLocalRandom.current().nextInt(origin,bound);
        try {
            Thread.sleep(random_time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public LineOfFile createMsg(String[] line){
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
