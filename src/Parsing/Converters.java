package Parsing;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Converters {
	public static SimpleTripFormat strideConverter(String url) {
		return null;
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
			trip = new SimpleTripFormat(name, location, 0, 0, image, desc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trip;
	}
}
