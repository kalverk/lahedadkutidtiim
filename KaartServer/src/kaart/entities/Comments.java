package kaart.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comment
 * 
 */
@Entity
@Table(name = "COMMENTS")
public class Comments implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Point point;
	
	@ManyToOne
	private User user;

	@Column(name = "COMMENT")
	private String comment;

	private static final long serialVersionUID = 1L;

	public Comments() {
		super();
	}

	public Comments(Point point, User user, String comment) {
		this.point = point;
		this.user = user;
		this.comment = comment;
	}
	
	public User getUser(){
		return user;
	}
	
	public Point getPoint(){
		return point;
	}
	
	public String getComment(){
		return comment;
	}
}
