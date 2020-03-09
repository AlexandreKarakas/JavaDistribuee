import java.util.Date;

public class Message {
	private Date date;
	private int idMessage;
	private int idUser;
	private String message;
	private String user;
	
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
	 * @param idMessage nouvelle identifiant du message
	 * @throws Exception si le nouvelle identifiant du message existe dèjà (l'identifiant du message doit être unique)
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
	 * @param idUser nouvelle identifiant de l'utilisateur
	 * @throws Exception si le nouveau nom d'utilisateur existe dèjà (le nom d'utilisateur doit être unique)
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	/**
	 * Renvoie le contenue du message
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
