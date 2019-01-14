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
		try {
			doc = Jsoup.connect(url).get();
			Element title = doc.select("h1").first();
			String name = title.text();
			Element text = doc.select("p").first();
			String desc = text.text();
			Element img = doc.getElementsByTag("img").get(0);
			String src = img.absUrl("src");
			System.out.println("Image Found!");
			System.out.println("src attribute is : " + src);
			getImage(src);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//String name = html.substri
		/*String image =html.substring(html.indexOf("<img class=\"styles__heroPhoto___11UU0\" srcset=") 
										+ "<img class=\"styles__heroPhoto___11UU0\" srcset=".length());*/
		return null;
	}

	private static void getImage(String src) {
		//Exctract the name of the image from the src attribute

		int indexname = src.lastIndexOf("/");
		if (indexname == src.length()) {
			src = src.substring(1, indexname);
		}
		indexname = src.lastIndexOf("/");
		String name = src.substring(indexname, src.length());
		System.out.println(name);	
	}
}
