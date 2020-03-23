import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.*;
import java.net.*;
import java.io.*;

public class Server {
    private static int port;
    private static int poolSize;
    private boolean isFinished;
    private ExecutorService pool;
    private final ServerSocket serverSocket;

    public ExecutorService getPool() {
        return pool;
    }

    public void setPool(ExecutorService pool) {
        this.pool = pool;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public static int getPoolSize() {
        return poolSize;
    }

    public static void setPoolSize(int poolSize) {
        Server.poolSize = poolSize;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Server.port = port;
    }

    public Server(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(poolSize);

    }

    public void manageRequest() {
        try{
            for(;;) {
                pool.execute(new Slave(serverSocket.accept()));
            }
        }catch(IOException ex) {
            pool.shutdown();
        }
    }

    public void lecture() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./src/reseauSocial.txt"));

        String line = lines.get(0);
        String[] r = line.split("\\|", -1);
        Message M = null; // Classe Message
        Comment C = null; // Classe Commentaire

        if(r[4] == "" && r[5] == ""){
            M.setIdMessage(Integer.parseInt(r[0]));
            M.setIdUser(Integer.parseInt(r[1]));
            M.setMessage(r[2]);
            M.setUser(r[3]);
        }
        else if(r[5] == ""){
            C.setIdCommentaire(Integer.parseInt(r[0]));
            C.setIdUser(Integer.parseInt(r[1]));
            C.setComment(r[2]);
            C.setUser(r[3]);
            C.setPidCommentaire(Integer.parseInt(r[4]));
        }
        else if(r[4] == ""){
            C.setIdCommentaire(Integer.parseInt(r[0]));
            C.setIdUser(Integer.parseInt(r[1]));
            C.setComment(r[2]);
            C.setUser(r[3]);
            C.setPidCommentaire(Integer.parseInt(r[5]));
        }
    }


}
