package Crawling;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ExtremeUtils.Utils;
import Parsing.TripFormat;

public class Test
{
    /**
     * This is our test. It creates a spider (which creates spider legs) and crawls the web.
     * 
     * @param args
     *            - not used
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException
    {
    	List<TripFormat> trips;
        Spider spider = new Spider();
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("france");
        
        // Lonely Planet Search
        //trips = spider.search(Utils.LONELY_PLANET, Utils.LONELYPLANET_SITE+searchParams.get(0)+Utils.LONELYPLANET_PREFIX, searchParams); 
    	
    	/// Stride Travel Search
    	trips = spider.search(Utils.STRIDE, Utils.STRIDE_SITE+searchParams.get(0), searchParams);
    	
    	System.out.println(trips);
    }
}
