package kaart.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Category
 * 
 */
@Entity
@Table(name = "Category")
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long category_id;

	@Column(name = "Category")
	private String Category;

	@ManyToOne
	private Point point;

	private static final long serialVersionUID = 1L;

	public Category() {
		super();
	}
	
	public Category(String category, Point point){
		this.Category = category;
		this.point = point;
	}

	public String getCategory() {
		return this.Category;
	}

	public void setCategory(String Category) {
		this.Category = Category;
	}

	public Long getId() {
		return this.category_id;
	}

	public void setId(Long id) {
		this.category_id = id;
	}
	
	public Point getPoint(){
		return point;
	}

	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", Category="
				+ Category + ", point=" + point.toString() + "]";
	}
	
}
