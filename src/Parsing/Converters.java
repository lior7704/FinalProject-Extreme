package Parsing;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
			Element text = doc.select("p").first();
			String desc = text.text();
			String location = null;
			Element img = doc.select("img").get(1);
			//String src = img.text();
			//System.out.println("Image Found!");
			//System.out.println("src attribute is : " + src);
			//String image = getImage(src);
			String image = img.attr("src");
			trip = new SimpleTripFormat(name, location, 0, 0, image, desc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trip;
	}

	private static String getImage(String src) {
		//Exctract the name of the image from the src attribute

		int indexname = src.lastIndexOf("/");
		if (indexname == src.length()) {
			src = src.substring(1, indexname);
		}
		indexname = src.lastIndexOf("/");
		String name = src.substring(indexname, src.length());
		System.out.println(name);	
		return name;
	}
}
