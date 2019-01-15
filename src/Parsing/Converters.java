package Parsing;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Converters {
	public static SimpleTripFormat strideConverter(String url) {
		Document doc;
		SimpleTripFormat trip = null;
		String name, desc, ratingString, image, location = null;
		float rating = 0;
		try {
			doc = Jsoup.connect(url).get();
			Element title = doc.select("h1").first();
			name = title.text();
			desc =doc.getElementById("overview").text().substring(11);
			Element reviews = doc.getElementById("reviews");
			ratingString = reviews.html().substring(reviews.html().indexOf("ratingValue") + 13);
			ratingString = ratingString.substring(0, 3);
			rating = Float.parseFloat(ratingString);
			Element locationContainer = doc.getElementById("supplier_details");
			image = locationContainer.html().substring(locationContainer.html().indexOf("src") + 5);
			image = image.substring(0, image.indexOf(".jpg") + 4);
			//Element categories = doc.getElementById("product_detail").getElementById(id);
			//System.err.println(categories);
			//String location = locationContainer.html().substring(locationContainer.html().indexOf("addressLocality"));
			//location = location.substring(18, location.indexOf(",") - 1);
			trip = new SimpleTripFormat(name, location, rating, image, desc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return trip;
	}
	
	public static SimpleTripFormat lonleyPlanetConverter(String url) {
		Document doc;
		SimpleTripFormat trip = null;
		try {
			doc = Jsoup.connect(url).get();
			Element title = doc.select("h1").first();
			String name = title.text();
			Element text = doc.select("p").get(1);
			String desc = text.text();
			String body = doc.select("body").first().data();
			String image = body.substring(body.indexOf("image"));
			image = image.substring(8, image.indexOf(".jpg") + 4);
			String location = body.substring(body.indexOf("addressLocality"));
			location = location.substring(18, location.indexOf(",") - 1);
			trip = new SimpleTripFormat(name, location, 0, image, desc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return trip;
	}
}
