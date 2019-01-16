package Crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ExtremeUtils.Utils;
import Parsing.Converters;
import Parsing.TripFormat;

public class Spider {
	private static final int MAX_PAGES_TO_SEARCH = 1000;
	private static final int MAX_PAGES_TO_GRAB = 5;
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	private String site;
	private List<TripFormat> trips = new ArrayList<TripFormat>();

	/**
	 * Our main launching point for the Spider's functionality. Internally it
	 * creates spider legs that make an HTTP request and parse the response (the web
	 * page).
	 * 
	 * @param url - The starting point of the spider
	 * @param     searchParms[]0 - The word or string that you are searching for
	 * @throws IOException
	 */
	public List<TripFormat> search(String site, String url, ArrayList<String> searchParams) throws IOException {
		this.site = site;
		String currentUrl;
		String body;
		int numPagesGrabbed = 0;
		while (this.pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
			SpiderLeg leg = new SpiderLeg();
			if (this.pagesToVisit.isEmpty()) {
				currentUrl = url;
				this.pagesVisited.add(url);
				leg.crawl(currentUrl);
			} else {
				currentUrl = this.nextUrl();
				if (checkCrawl(currentUrl, searchParams)) {
					leg.crawl(currentUrl);
				} else {
					continue;
				}
			}
			// Get page if body matches parameters
			if (this.site.equals(Utils.LONELY_PLANET) && currentUrl.contains("/attractions")
					&& !currentUrl.contains("/attractions/a/")
					|| this.site.equals(Utils.STRIDE) && currentUrl.contains("/trips")) {
				body = leg.searchForWord(searchParams);
				if (body != null) // parsing
				{
					TripFormat currTrip = null;
					//System.out.println(String.format("**Success** Word %s found at %s", searchParams, currentUrl));
					numPagesGrabbed++;
					if (this.site.equals(Utils.LONELY_PLANET)) {
						currTrip = Converters.lonleyPlanetConverter(currentUrl);
					} else {
						currTrip = Converters.strideConverter(currentUrl);
					}
					trips.add(currTrip);
					if (numPagesGrabbed == MAX_PAGES_TO_GRAB)
						break;
				}
			}
			this.pagesToVisit.addAll(leg.getLinks());
		}
		System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
		System.out.println("Pages grabbed:" + numPagesGrabbed);
		return trips;
	}

	private boolean checkCrawl(String currentUrl, ArrayList<String> searchParams) {
		if (this.site.equals(Utils.LONELY_PLANET)) {
			return checkLonelyPlanet(currentUrl, searchParams);
		}
		if (this.site.equals(Utils.STRIDE)) {
			return checkStride(currentUrl, searchParams);
		}
		return false;
	}

	private boolean containsParams(String body, ArrayList<String> searchParams) {
		for (String string : searchParams) {
			if (!body.toLowerCase().contains(string.toLowerCase()))
				return false;
		}
		return true;
	}

	private boolean checkStride(String currentUrl, ArrayList<String> searchParams) {
		if (!currentUrl.contains("#") && !currentUrl.contains(".html") && currentUrl.contains("stridetravel.com")) {
			return otherValidations(currentUrl, searchParams);
		}
		return false;
	}

	private boolean checkLonelyPlanet(String currentUrl, ArrayList<String> searchParams) {
		if (!currentUrl.contains("#") && !currentUrl.contains(".html") && !currentUrl.contains("restaurants")
				&& !currentUrl.contains("activities") && !currentUrl.contains("hotels")
				&& !currentUrl.contains("community")) {
			return otherValidations(currentUrl, searchParams);
		}
		return false;
	}

	private boolean otherValidations(String currentUrl, ArrayList<String> searchParams) {
		if (searchParams.size() == 1) {
			if (currentUrl.contains(searchParams.get(0)) && !currentUrl.contains("?"))
				return true;
		} else if (currentUrl.contains(searchParams.get(0))
				|| currentUrl.contains("?") && containsParams(currentUrl, searchParams)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the next URL to visit (in the order that they were found). 
	 * We also check to make sure this method doesn't return a URL that has already been
	 * visited.
	 */
	private String nextUrl() {
		String nextUrl;
		do {
			nextUrl = this.pagesToVisit.remove(0);
		} while (this.pagesVisited.contains(nextUrl));
		this.pagesVisited.add(nextUrl);
		return nextUrl;
	}
}