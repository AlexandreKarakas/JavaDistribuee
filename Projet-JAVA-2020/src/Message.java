import java.util.Date;

public class Message implements LineOfFile {
    private Date date;

    private int idMessage,
                idUser,
                score;

    private String  message,
                    user;


    public Message(int idMessage, int idUser, String message, String user){
        this.idMessage = idMessage;
        this.idUser = idUser;
        this.message = message;
        this.user = user;
        score = 20;
    }

    public int getScore() {
        return score;
    }

    public void addToScore(int value) {
        score += value;
    }

    public void decreaseScoreByOne() {
        score--;
    }


    public void printMsg() {
        System.out.println("-----------------");
        System.out.println("ID Message : " + idMessage);
        System.out.println("ID User : " + idUser);
        System.out.println("Message : " + message);
        System.out.println("User : " + user);
        System.out.println("Score : " + score);
        System.out.println("-----------------");
    }


    /**
     * Renvoie la date du message
     * @return Date correspondant à la date du message
     */
    public Date getDate() {
        return date;
    }
    /**
     * Mise à jour de la date du message
     * @param date nouvelle date du message
     * @throws Exception si la date n'est pas valide
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Renvoie l'id du message
     * @return int correspondant à l'identifiant du message
     */
    public int getIdMessage() {
        return idMessage;
    }
    /**
     * Mise à jour de l'id du message
     * @param idMessage nouvel identifiant du message
     * @throws Exception si le nouvel identifiant du message existe dèjà (l'identifiant du message doit être unique)
     */
    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    /**
     * Renvoie l'id de l'utilisateur
     * @return int correspondant à l'identifiant de l'utilisateur
     */
    public int getIdUser() {
        return idUser;
    }
    /**
     * Mise à jour de l'id de l'utilisateur
     * @param idUser nouvel identifiant de l'utilisateur
     * @throws Exception si le nouveau nom d'utilisateur existe dèjà (le nom d'utilisateur doit être unique)
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * Renvoie le contenu du message
     * @return String correspondant au contenu du message
     */
    public String getMessage() {
        return message;
    }
    /**
     * Mise à jour du contenu du message
     * @param message nouveau contenu du message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Renvoie le nom de l'utilisateur
     * @return String correspondant au nom de l'utilisateur
     */
    public String getUser() {
        return user;
    }
    /**
     * Mise à jour du nom de l'utilisateur
     * @param user nouveau nom de l'utilisateur
     */
    public void setUser(String user) {
        this.user = user;
    }
}