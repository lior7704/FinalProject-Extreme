package Parsing;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Converters {
	public static SimpleTripFormat strideConverter(String url) {
		Document doc;
		SimpleTripFormat trip = null;
		String name, desc, ratingString, image, location = null;
		float rating = 0;
		try {
			doc = Jsoup.connect(url).get();
			// Trip name
			Element title = doc.select("h1").first();
			name = title.text();
			
			// Description
			desc = doc.getElementById("overview").text().substring(11);
			
			// Rating
			Element reviews = doc.getElementById("reviews");
			ratingString = reviews.html().substring(reviews.html().indexOf("ratingValue") + 13);
			ratingString = ratingString.substring(0, 3);
			rating = Float.parseFloat(ratingString);
			
			// Image
			Element imageContainer = doc.getElementById("supplier_details");
			image = imageContainer.html().substring(imageContainer.html().indexOf("src") + 5);
			image = image.substring(0, image.indexOf(".jpg") + 4);
			
			// Location
			Elements countries = doc.getElementById("content").getElementsByClass("mosbox").get(1)
					.getElementsByTag("a");
			Elements regions = doc.getElementById("content").getElementsByClass("mosbox").get(2).getElementsByTag("a");
			StringBuilder locationBuilder = new StringBuilder("");
			for (Element element : countries) {
				if (countries.size() > 1 && !element.equals(countries.get(0)) && 
						!element.equals(countries.get(countries.size() - 1)))
					locationBuilder.append(", ");
				locationBuilder.append(element.text());
			}
			locationBuilder.append(": ");
			System.err.println();
			for (Element element : regions) {
				if (regions.size() > 1 && !element.equals(regions.get(0)) && 
					!element.equals(regions.get(regions.size() - 1)))
					locationBuilder.append(", ");
				if (element.text().equals("N/A")) {
					locationBuilder.deleteCharAt(locationBuilder.length()-1);
					locationBuilder.deleteCharAt(locationBuilder.length()-1);
				}
				if (!element.text().equals("More"))
					locationBuilder.append(element.text());
			}
			location = locationBuilder.toString();
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
