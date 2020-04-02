import java.util.Date;

public class Comment implements FileData {
    private Date date;

    private int idComment,
                idUser,
                pidComment,
                pidMessage,
                score;

    private String  comment,
                    user;

    private boolean is_a_comment_to_another_comment;

    /**
     * Crée un objet "Comment" : il s'agit d'un commentaire répondant soit à un autre commentaire,
     * soit à un message
     * @param idComment Id du commentaire
     * @param idUser Id de l'utilisateur
     * @param comment Contenu du commentaire
     * @param user Nom de l'utilisateur qui a écrit le commentaire
     * @param pidParent Id du message/commentaire auquel il répond
     */
    public Comment(int idComment, int idUser, String comment, String user, int pidParent, boolean is_a_comment_to_another_comment){
        if(is_a_comment_to_another_comment){
            pidMessage = -1;
            pidComment = pidParent;
        } else {
            pidComment = -1;
            pidMessage = pidParent;
        }
        this.idComment = idComment;
        this.idUser = idUser;
        this.comment = comment;
        this.user = user;
        this.is_a_comment_to_another_comment = is_a_comment_to_another_comment;
        score = 20;
    }

    public void decreaseScoreByOne() {
        if(score > 0)
            score--;
    }

    /**
     * Renvoie l'id du commentaire
     * @return int correspondant à l'identifiant du message
     */
    public int getId(){
        return idComment;
    }

    /**
     * Renvoie le pid du message
     * @return int correspondant au pid du message
     */
    public int getPidMessage() {
        return pidMessage;
    }

    /**
     * Renvoie le pid du commentaire
     * @return int correspondant au pid du commentaire
     */
    public int getPidComment() {
        return pidComment;
    }

    /**
     * Renvoie le score du commentaire
     * @return int correspondant au score du commentaire
     */
    public int getScore() {
        return score;
    }

    /**
     * Renvoie vrai si le commentaire répond à un autre commentaire
     * @return boolean vrai si oui, faux sinon
     */
    public boolean is_a_comment_to_another_comment() {
        return is_a_comment_to_another_comment;
    }
}