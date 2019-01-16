package UI;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

import ExtremeUtils.Utils;

public class ConsoleInterface {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Scanner s = new Scanner(System.in);
		HashSet<String> countries = getCountries();
		boolean runMultipleSites = false;
		PrintWriter pw = new PrintWriter(new File("results.txt"));
		
		System.out.println("**********************************");
		System.out.println("******* Welcome to EXTREME *******");
		System.out.println("**********************************");
		System.out.println();
		System.out.println("Choose a trip site to search in:");
		System.out.println("1) Lonely Planet");
		System.out.println("2) Stride");
		System.out.println("3) Both sites");
		
	
		String siteName = s.nextLine();
		String siteUrl = null, prefix = "", otherName = "", otherSite = "", otherPrefix = "";
		
		System.out.println();
		
		if(siteName.equals("1")) {
			siteName = Utils.LONELY_PLANET;
			siteUrl = Utils.LONELYPLANET_SITE;
			prefix = Utils.LONELYPLANET_PREFIX;
		}
		else if(siteName.equals("2")){
			siteName = Utils.STRIDE;
			siteUrl = Utils.STRIDE_SITE;
		}
		else if(siteName.equals("3")){
			siteName = Utils.LONELY_PLANET;
			siteUrl = Utils.LONELYPLANET_SITE;
			prefix = Utils.LONELYPLANET_PREFIX;
			
			otherName = Utils.STRIDE;
			otherSite = Utils.STRIDE_SITE;
			
			runMultipleSites = true;
		}else {
			System.out.println("Wrong input. Shutting down");
			System.exit(0);
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
		
		System.out.println("Search is complete. Check \"results.txt\"");
		pw.close();
		s.close();
	}
	
	public static HashSet<String> getCountries() {
		
		HashSet<String> countries = new HashSet<String>();
		for (String country : Locale.getISOCountries()) {
			countries.add(new Locale("", country).getDisplayCountry().toLowerCase());
		}
		return countries;
	}

}
