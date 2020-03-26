public class main {
    public static void main(String[] args) {
        try{
            Server networkService = new Server(333, 1);
            System.out.println("Serveur");
            networkService.manageRequest();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}