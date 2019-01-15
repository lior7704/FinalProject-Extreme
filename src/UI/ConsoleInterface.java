package UI;

import java.util.Locale;
import java.util.Scanner;

import ExtremeUtils.Utils;

public class ConsoleInterface {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("**********************************");
		System.out.println("******* Welcome to EXTREME *******");
		System.out.println("**********************************");
		System.out.println();
		System.out.println("Choose a trip site to search in:");
		System.out.println("Lonely Planet");
		System.out.println("Stride");
		
	
		String siteName = s.nextLine();
		String siteUrl;
		
		if(siteName.toLowerCase().equals(Utils.LONELY_PLANET)) {
			siteName = Utils.LONELY_PLANET;
			siteUrl = Utils.LONELYPLANET_SITE;
		}
		else {
			siteName = Utils.STRIDE;
			siteUrl = Utils.STRIDE_SITE;
		}
		
		System.out.println("Enter a state name to search in");
		String state = s.nextLine().toLowerCase();
		
		 
		
//		String [] countries = Locale.getISOCountries();
//		for (String string : countries) {
//			System.out.println(new Locale("", string).getDisplayCountry());
//		}
	}

}
