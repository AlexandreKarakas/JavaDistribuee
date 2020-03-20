import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Server {

    public static void main(String[] args) throws IOException {

        Thread q2a = new Thread() {
          public void run(){

          }
        };
        q2a.start();

        List<String> lines = Files.readAllLines(Paths.get("./src/reseauSocial.txt"));

        String line = lines.get(0);
        String[] r = line.split("\\|", -1);
        String idMessage, message, pidMessage, idUser, user, idCommentaire, commentaire, pidCommentaire;

        if(r[4] == "" && r[5] == ""){
            idMessage = r[0];
            idUser = r[1];
            message = r[2];
            user = r[3];
        }
        else if(r[5] == ""){
            idCommentaire = r[0];
            idUser = r[1];
            commentaire = r[2];
            user = r[3];
            pidCommentaire = r[4];
        }
        else if(r[4] == ""){
            idCommentaire = r[0];
            idUser = r[1];
            commentaire = r[2];
            user = r[3];
            pidMessage = r[5];
        }

    }
}