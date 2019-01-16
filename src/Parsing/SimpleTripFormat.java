package Parsing;

public class SimpleTripFormat extends TripFormat {
	private String name;
	private String location;
	private float rating;
	private String image;
	private String description;
	private String sourceUrl; 

	public SimpleTripFormat(String name, String location, float rating, String image, String description, String sourceUrl) {
		super();
		this.name = name;
		this.location = location;
		this.rating = rating;
		this.image = image;
		this.description = description;
		this.sourceUrl = sourceUrl;
	}

	public String getName() {
		return location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	@Override
	public String toString() {
		return "Trip Name: " + name + "\nLocation: " + location + "\nRating: " + rating + " out of 5\nimage: "
				+ image + "\nDescription: " + description + "\nSource URL: " + sourceUrl;
	}
}
