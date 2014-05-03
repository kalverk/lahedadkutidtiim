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
@Table(name = "RATINGS")
public class Ratings implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Point point;
	
	@ManyToOne
	private User user;

	@Column(name = "RATING")
	private int rating;

	private static final long serialVersionUID = 1L;

	public Ratings() {
		super();
	}

	public Ratings(Point point, User user, int rating) {
		this.point = point;
		this.user = user;
		this.rating = rating;
	}
	
	public void setRating(int rating){
		this.rating = rating;
	}
}
