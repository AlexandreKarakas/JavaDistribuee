import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientServer {
    public static void main(String [] args) {
        try{
            Socket soc = new Socket("localhost", 3333);

        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
