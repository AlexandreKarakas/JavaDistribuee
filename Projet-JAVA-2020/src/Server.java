import java.io.IOException;

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
                pool.execute(new RunImpl(serverSocket.accept()));
            }
        }catch(IOException ex) {
            pool.shutdown();
        }
    }
}
