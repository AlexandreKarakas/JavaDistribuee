import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 33333);
        BufferedInputStream reader = new BufferedInputStream(socket.getInputStream());

        //Il ne nous reste plus qu'à le lire
        byte[] b = new byte[4096];
        int stream = reader.read(b);
        String content = new String(b, 0, stream);
        System.out.println(content);
    }

}
