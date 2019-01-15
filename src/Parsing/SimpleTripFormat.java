package Parsing;

public class SimpleTripFormat extends TripFormat{
	private String name;
	private String location;
	private float rating;
	private int numReviews;
	private String image;
	private String description;
	
	public SimpleTripFormat(String name, String location, float rating, int numReviews, String image, String description) {
		super();
		this.name = name;
		this.location = location;
		this.rating = rating;
		this.numReviews = numReviews;
		this.image = image;
		this.description = description;
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

	@Override
	public String toString() {
		return "SimpleTripFormat \n name: " + name + "\n location: " + location + "\n rating: " + rating + "\n numReviews: "
				+ numReviews + "\n image: " + image + "\n description: " + description + "\n";
	}
}
