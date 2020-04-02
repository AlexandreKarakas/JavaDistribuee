import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) throws IOException {
        // Version avec serveur RMI
        try{
            Registry registry = LocateRegistry.getRegistry(33333);
            BestMessages bm = (BestMessages) registry.lookup("BestMessages");
            System.out.println(bm.getThreeBestMessagesToString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Version avec socket pour la question 2 (décommenter et commenter le try catch du dessus)
        /*Socket socket = new Socket("localhost", 33333);
        BufferedInputStream reader = new BufferedInputStream(socket.getInputStream());

        //Il ne nous reste plus qu'à le lire
        byte[] b = new byte[4096];
        int stream = reader.read(b);
        String content = new String(b, 0, stream);
        System.out.println(content);*/
    }

}
