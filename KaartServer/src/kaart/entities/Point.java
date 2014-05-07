package kaart.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Point
 * 
 */
@Entity
@Table(name = "POINT")
public class Point implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "point")
	private List<Category> pointsByCategory;
	
	@OneToMany(mappedBy = "point")
	private List<Ratings> pointRatings;
	
	@ManyToOne
	private User user;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "LINK")
	private String link;
	
	@Column(name = "ISACTIVE")
	private boolean ISACTIVE = true;
	private static final long serialVersionUID = 1L;

	public Point() {
		super();
	}

	public Point(String name, String location, String description, String link, User user) {
		this.name = name;
		this.location = location;
		this.description = description;
		this.link = link;
		this.user = user;
	}
	

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public User getUser() {
		return user;
	}
	
	public boolean isActive() {
		return ISACTIVE;
	}

	public void setActive(boolean isActive) {
		this.ISACTIVE = isActive;
	}

	@Override
	public String toString() {
		return new StringBuffer(" point_id : ").append(id).append(" name : ")
				.append(name).append(" location : ").append(location)
				.append(" description : ").append(description)
				.append(" link : ").append(link).toString();
	}
}
