package com.entity;

public class Point {

	private String name;
	private String location;
	private String description;
	private String link;
	private Long id;
	private String rating;
	private String userFbId;

	public Point(Long id, String name, String location, String description, String link, String rating, String userFbId) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.description = description;
		this.link = link;
		this.rating = rating;
		this.userFbId = userFbId;
	}

	public String getUserFbId() {
		return userFbId;
	}

	public void setUserFbId(String userFbId) {
		this.userFbId = userFbId;
	}

	public Long getId() {
		return id;
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
	
	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Point [name=" + name + ", location=" + location
				+ ", description=" + description + ", link=" + link + "]";
	}
}

