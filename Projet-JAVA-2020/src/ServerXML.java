import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerXML {
    private ServerSocket serverSocket;
    private final ExecutorService pool;

    public static void main(String[] args) throws IOException {
        // On instancie le serveur, port 33333 et un pool de threads de taille 5 puis on le démarre
        ServerXML server = new ServerXML(33333, 5);
        server.openServer();

    }

    public void openServer() throws IOException {
        // On crée puis démarre le thread principal qui calcule les 3 meilleurs messages continuellement
        RunImpl imp = new RunImpl();
        Thread mainThread = new Thread(imp);
        mainThread.start();

        // On fait une boucle infinie pour détecter à chaque instant une connexion provenant d'un client
        while(true){
            Socket connectionSocket = serverSocket.accept();
            pool.execute(() -> {
                try {
                    PrintWriter writer = new PrintWriter(connectionSocket.getOutputStream());
                    String toSend = imp.getThreeBestMessagesToString();
                    writer.write(toSend);
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public ServerXML(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        // On crée un pool de threads de taille poolSize
        pool = Executors.newFixedThreadPool(poolSize);
    }
}