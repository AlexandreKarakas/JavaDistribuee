import java.io.IOException;
public class main {
    public static void main(String[] args) {
        try {
            Server networkService = new Server(33333, 10);
            System.out.println("Serveur lance");
            networkService.manageRequest();
            networkService.lecture();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
