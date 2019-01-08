package Parsing;

public class SimpleTripFormat extends TripFormat{
	private String location;
	private float rating;
	private int numReviews;
	private String image;
	private String description;
	
	public SimpleTripFormat(String html) {
		
	}
	
	public SimpleTripFormat(String location, float rating, int numReviews, String image, String description) {
		super();
		this.location = location;
		this.rating = rating;
		this.numReviews = numReviews;
		this.image = image;
		this.description = description;
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

	public int getNumReviews() {
		return numReviews;
	}

	public void setNumReviews(int numReviews) {
		this.numReviews = numReviews;
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
}
