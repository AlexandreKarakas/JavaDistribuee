import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        // On lit chaque ligne du fichier toutes les 1 à 3s et on réalise les opérations nécessaires
        for(String line : lines){
            wait_random_time(1000,3000);
            String[] r = line.split("\\|", -1);

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

    public void getLineType(String[] line){
        // Throws error si pas line.length != 6

        if(line[4].equals("") && line[5].equals("")){
            int idMessage = Integer.parseInt(line[0]),
                    idUser = Integer.parseInt(line[1]);
            String message = line[2],
                    user = line[3];

            new Message(idMessage, idUser, message, user);
        }

        else if(line[5].equals("")){
            int idComment = Integer.parseInt(line[0]),
                    idUser = Integer.parseInt(line[1]),
                    pidComment = Integer.parseInt(line[4]);
            String comment = line[2],
                    user = line[3];

            new Comment(idComment, idUser, comment, user, pidComment, true);
        }

        else if(line[4].equals("")){
            int idComment = Integer.parseInt(line[0]),
                    idUser = Integer.parseInt(line[1]),
                    pidMessage = Integer.parseInt(line[5]);
            String comment = line[2],
                    user = line[3];

            new Comment(idComment, idUser, comment, user, pidMessage, false);
        }
    }

    public void updateScores(){

    }
}
