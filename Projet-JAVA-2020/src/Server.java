import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static ServerSocket serverSocket;
    private static ExecutorService pool;

    public static void main(String[] args) throws IOException {
        // Version avec serveur RMI
        try{
            System.out.println("Le serveur est démarré.");
            BestMessages imp = (BestMessages) UnicastRemoteObject.exportObject(new RunImpl(), 33333);
            Registry registry = LocateRegistry.createRegistry(33333);
            registry.rebind("BestMessages", imp);
        } catch (Exception e){
            e.printStackTrace();
        }


        // Version avec socket pour la question 2 (décommenter et commenter le try catch du dessus)
        /*
        // On instancie le serveur, port 33333 et un pool de threads de taille 5 puis on le démarre
        Server server = new Server(33333, 5);
                // On crée puis démarre le thread principal qui calcule les 3 meilleurs messages continuellement
        RunImpl imp = new RunImpl();
        Thread mainThread = new Thread(imp);
        mainThread.start();

        System.out.println("Le serveur est démarré");

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
        */
    }

    public Server(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        // On crée un pool de threads de taille poolSize
        pool = Executors.newFixedThreadPool(poolSize);
    }
}