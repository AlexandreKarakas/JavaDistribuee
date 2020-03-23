import java.io.IOException;

public class Server {

    public static void main(String[] args) {

        Thread q2a = new Thread(new RunImpl());
        q2a.start();

    }
}