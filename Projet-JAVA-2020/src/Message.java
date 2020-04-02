import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Message implements FileData {
    private Date date;

    private int idMessage,
                idUser,
                score,
                valueOfImportance;

    private String  message,
                    user;


    public Message(int idMessage, int idUser, String message, String user){
        this.idMessage = idMessage;
        this.idUser = idUser;
        this.message = message;
        this.user = user;
        score = 20;
        valueOfImportance = score;
    }

    public void decreaseScoreByOne() {
        score--;
    }

    public boolean isActive(){
        return valueOfImportance > 0;
    }

    /**
     * Renvoie l'id du message
     * @return int correspondant à l'identifiant du message
     */
    public int getId(){
        return idMessage;
    }

    /**
     * Renvoie l'id de l'utilisateur
     * @return int correspondant à l'identifiant de l'utilisateur
     */
    public int getIdUser() {
        return idUser;
    }

    public int getScore() {
        return score;
    }

    public int getValueOfImportance() {
        return valueOfImportance;
    }

    public void setValueOfImportance(int valueOfImportance){
        this.valueOfImportance = valueOfImportance;
    }
}