public class Point {

	private String name;
	private String location;
	private String description;
	private String link;

	public Point(String name, String location, String description, String link) {
		this.name = name;
		this.location = location;
		this.description = description;
		this.link = link;
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

	@Override
	public String toString() {
		return "Point [name=" + name + ", location=" + location
				+ ", description=" + description + ", link=" + link + "]";
	}
}
