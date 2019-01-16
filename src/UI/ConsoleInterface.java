package UI;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Crawling.Spider;
import ExtremeUtils.Utils;
import Parsing.TripFormat;

public class ConsoleInterface {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Scanner s = new Scanner(System.in);
		List<TripFormat> trips;
		HashSet<String> countries = getCountries();
		boolean runMultipleSites = false;
		PrintWriter pw = new PrintWriter(new File("results.txt"));
		
		System.out.println("**********************************");
		System.out.println("******* Welcome to EXTREME *******");
		System.out.println("**********************************");
		System.out.println();
		System.out.println("Choose a trip site to search in:");
		System.out.println("Lonely Planet");
		System.out.println("Stride");
		System.out.println("Both sites");
		
	
		String siteName = s.nextLine();
		String siteUrl, prefix = "", otherName = "", otherSite = "", otherPrefix = "";
		
		System.out.println();
		
		if(siteName.toLowerCase().equals(Utils.LONELY_PLANET.toLowerCase())) {
			siteName = Utils.LONELY_PLANET;
			siteUrl = Utils.LONELYPLANET_SITE;
			prefix = Utils.LONELYPLANET_PREFIX;
		}
		else if(siteName.toLowerCase().equals(Utils.STRIDE.toLowerCase())){
			siteName = Utils.STRIDE;
			siteUrl = Utils.STRIDE_SITE;
		}
		else {
			siteName = Utils.LONELY_PLANET;
			siteUrl = Utils.LONELYPLANET_SITE;
			prefix = Utils.LONELYPLANET_PREFIX;
			
			otherName = Utils.STRIDE;
			otherSite = Utils.STRIDE_SITE;
			
			runMultipleSites = true;
		}
		
		String country = "";
		
		while(true) {
			System.out.println("Enter a country name to search in");
			country = s.nextLine().toLowerCase();
			System.out.println();
			if(countries.contains(country.toLowerCase()))
				break;
			System.out.println("Country name is not valid, please enter again");
			System.out.println();
		}
		
		System.out.println();
		System.out.println("Please wait, performing search..");
		
//		Spider spider = new Spider();
//    	ArrayList<String> searchParams = new ArrayList<String>();
//    	searchParams.add(country);
//    	trips = spider.search(siteName, siteUrl+searchParams.get(0) + prefix, searchParams);
//    	
//    	for (TripFormat trip : trips) {
//			System.out.println(trip);
//			System.out.println();
//		}
		
		Thread search = new Thread(new MultipleSerachThread(country, siteName, siteUrl, prefix, pw));
		search.start();
		
		if(runMultipleSites) {
			Thread search2 = new Thread(new MultipleSerachThread(country, otherName, otherSite, otherPrefix, pw));
			search2.start();
			search2.join();
		}
		
		search.join();
		pw.close();

	}
	
	public static HashSet<String> getCountries() {
		
		HashSet<String> countries = new HashSet<String>();
		for (String country : Locale.getISOCountries()) {
			countries.add(new Locale("", country).getDisplayCountry().toLowerCase());
		}
		return countries;
	}

}
