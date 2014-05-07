package kaart.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 * 
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "user")
	private List<Ratings> userRatings;
	
	@OneToMany(mappedBy = "user")
	private List<Point> userPoints;

	@Column(name = "NAME")
	private String name;

	@Column(name = "FB_USERID")
	private String fbUserID;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	public User(String name, String fbUserID) {
		this.name = name;
		this.fbUserID = fbUserID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFbUserID() {
		return fbUserID;
	}

	public void setFbUserID(String fbUserID) {
		this.fbUserID = fbUserID;
	}
}
