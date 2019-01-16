package UI;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Crawling.Spider;
import Parsing.TripFormat;

public class MultipleSerachThread implements Runnable {

	private String siteName;
	private String siteUrl;
	private String prefix;
	private String country;
	private PrintWriter pw;
	
	public MultipleSerachThread(String country ,String siteName, String siteURL, String prefix, PrintWriter pw) {
		this.siteName = siteName;
		this.siteUrl = siteURL;
		this.prefix = prefix;
		this.country = country;
		this.pw = pw;
	}
	
	@Override
	public void run() {
		Spider spider = new Spider();
		List<TripFormat> trips = new ArrayList<TripFormat>();
    	ArrayList<String> searchParams = new ArrayList<String>();
    	searchParams.add(country);
    	try {
			trips = spider.search(siteName, siteUrl+searchParams.get(0) + prefix, searchParams);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	synchronized (pw) {
    		for (TripFormat trip : trips) {
    			pw.println(trip);
    			pw.println();
    		}
		}
    	
    /*	for (TripFormat trip : trips) {
			System.out.println(trip);
			System.out.println();
		}*/
	}

}
