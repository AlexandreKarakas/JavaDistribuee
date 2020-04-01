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
     * @param idComment
     * @param idUser
     * @param comment
     * @param user
     * @param pidParent
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


    public int getScore() {
        return score;
    }

    public int getPidParent(){
        return pidComment==-1?pidMessage:pidComment;
    }

    public void decreaseScoreByOne() {
        if(score > 0)
            score--;
    }

    public int getId(){
        return idComment;
    }

    /*public Message getRootMessage(TreeNode<FileData> thread){

    }*/


    /**
     * Renvoie la date du commentaire
     * @return Date correspondant à la date
     */
    public Date getDate() {
        return date;
    }
    /**
     * Mise à jour de la date du commentaire
     * @param date nouvelle date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Renvoie l'id du commentaire
     * @return int correspondant à l'idCommentaire
     */
    public int getIdComment() {
        return idComment;
    }
    /**
     * Mise à jour de l'id du commentaire
     * @param idComment nouvel identifiant de commentaire
     * @throws Exception si le nouvel identifiant du commentaire existe dèjà (l'identifiant du commentaire doit être unique)
     */
    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    /**
     * Renvoie l'id de l'utilisateur
     * @return int correspondant à l'idUser
     */
    public int getIdUser() {
        return idUser;
    }
    /**
     * Mise à jour de l'id utilisateur
     * @param idUser nouvelle identifiant de l'utilisateur
     * @throws Exception si le nouveau nom d'utilisateur existe dèjà (le nom d'utilisateur doit être unique)
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * Renvoie le contenu du commentaire
     * @return String correspondant à comment
     */
    public String getComment() {
        return comment;
    }
    /**
     * Mise à jour du commentaire
     * @param comment nouveau commentaire
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Renvoie le nom de l'utilisateur
     * @return String correspondant à user
     */
    public String getUser() {
        return user;
    }
    /**
     * Mise à jour du nom de l'utilisateur
     * @param user nouveau nom d'utilisateur
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Renvoie le pid du message
     * @return int correspondant au pid du message
     */
    public int getPidMessage() {
        return pidMessage;
    }
    /**
     * Mise à jour du pid du message
     * @param pidMessage nouveau pid du message
     */
    public void setPidMessage(int pidMessage) {
        this.pidMessage = pidMessage;
    }

    /**
     * Renvoie le pid du commentaire
     * @return int correspondant au pid du commentaire
     */
    public int getPidComment() {
        return pidComment;
    }
    /**
     * Mise à jour du pid du commentaire
     * @param pidComment nouveau pid du commentaire
     */
    public void setPidComment(int pidComment) {
        this.pidComment = pidComment;
    }

    public boolean is_a_comment_to_another_comment() {
        return is_a_comment_to_another_comment;
    }
}