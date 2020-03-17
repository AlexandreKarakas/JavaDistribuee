import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Slave implements Runnable{
    private final Socket socket;
    public Slave(Socket accept) {
        this.socket = accept;
    }
    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            OutputStream ou = socket.getOutputStream();
            PrintWriter bos;
            int i;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String s = reader.readLine();
            System.out.println(s);
        }
        catch (SocketException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        try {
            socket.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }

    }

}
