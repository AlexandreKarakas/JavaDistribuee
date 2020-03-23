import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RunImpl implements Runnable {
    List<String> lines; // Liste de chaque ligne du fichier reseauSocial.txt
    Map<Integer, String[]> line_map;

    @Override
    public void run() {
        // Lecture unique de toutes les lignes du fichier reseauSocial.txt
        try {
            lines = Files.readAllLines(Paths.get("./src/reseauSocial.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // On crée une liste pouvant contenir au maximum 3 messages
        List<LineOfFile> three_best_messages = new ArrayList<>(3);

        // On lit chaque ligne du fichier toutes les 1 à 3s et on réalise les opérations nécessaires
        for(String line : lines){
            wait_random_time(1000,3000);
            String[] r = line.split("\\|", -1);
            LineOfFile msg = getLineType(r);

            int score = msg.getScore();

            if(three_best_messages.size() < 3)
                three_best_messages.add(msg);
            else if(score > three_best_messages.get(0).getScore())
                three_best_messages.set(0, msg);
            three_best_messages.sort(Comparator.comparingInt(LineOfFile::getScore));
            // DEBUG //
            for(LineOfFile l : three_best_messages){
                l.printMsg();
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

    public LineOfFile getLineType(String[] line){
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

    public void updateScores(){

    }
}
