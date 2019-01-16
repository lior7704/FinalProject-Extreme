package UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Crawling.Spider;
import ExtremeUtils.Utils;
import Parsing.TripFormat;

public class ConsoleInterface {

	public static void main(String[] args) throws IOException {
		
		Scanner s = new Scanner(System.in);
		List<TripFormat> trips;
		
		System.out.println("**********************************");
		System.out.println("******* Welcome to EXTREME *******");
		System.out.println("**********************************");
		System.out.println();
		System.out.println("Choose a trip site to search in:");
		System.out.println("Lonely Planet");
		System.out.println("Stride");
		
	
		String siteName = s.nextLine();
		String siteUrl, prefix = "";
		
		System.out.println();
		
		if(siteName.toLowerCase().equals(Utils.LONELY_PLANET.toLowerCase())) {
			siteName = Utils.LONELY_PLANET;
			siteUrl = Utils.LONELYPLANET_SITE;
			prefix = Utils.LONELYPLANET_PREFIX;
		}
		else {
			siteName = Utils.STRIDE;
			siteUrl = Utils.STRIDE_SITE;
		}
		
		System.out.println("Enter a state name to search in");
		String state = s.nextLine().toLowerCase();
		
		System.out.println();
		System.out.println("Please wait, performing search..");
		
		Spider spider = new Spider();
    	ArrayList<String> searchParams = new ArrayList<String>();
    	searchParams.add(state);
    	trips = spider.search(siteName, siteUrl+searchParams.get(0) + prefix, searchParams);
    	
    	for (TripFormat trip : trips) {
			System.out.println(trip);
			System.out.println();
		}
		
//		String [] countries = Locale.getISOCountries();
//		for (String string : countries) {
//			System.out.println(new Locale("", string).getDisplayCountry());
//		}
	}

}
