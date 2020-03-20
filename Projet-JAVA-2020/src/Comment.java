import java.util.Date;

public class Comment {
    private static Date date;

    private static Integer  idCommentaire,
                            idUser,
                            pidCommentaire,
                            pidMessage;

    private static String   comment,
                            user;



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
    public int getIdCommentaire() {
        return idCommentaire;
    }
    /**
     * Mise à jour de l'id du commentaire
     * @param idCommentaire nouvelle identifiant de commentaire
     * @throws Exception si le nouvelle identifiant du commentaire existe dèjà (l'identifiant du commentaire doit être unique)
     */
    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
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
    public int getPidCommentaire() {
        return pidCommentaire;
    }
    /**
     * Mise à jour du pid du commentaire
     * @param pidCommentaire nouveau pid du commentaire
     */
    public void setPidCommentaire(int pidCommentaire) {
        this.pidCommentaire = pidCommentaire;
    }
}
