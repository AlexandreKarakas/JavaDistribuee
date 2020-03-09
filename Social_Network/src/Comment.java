import java.util.Date;

public class Comment {
	private Date date;
	private int idCommentaire;
	private int idUser;
	private String comment;
	private String user;
	private int pidMessage;
	private int pidCommentaire;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getIdCommentaire() {
		return idCommentaire;
	}
	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public int getPidMessage() {
		return pidMessage;
	}
	public void setPidMessage(int pidMessage) {
		this.pidMessage = pidMessage;
	}
	
	public int getPidCommentaire() {
		return pidCommentaire;
	}
	public void setPidCommentaire(int pidCommentaire) {
		this.pidCommentaire = pidCommentaire;
	}
	
	
}
