package Crawling;
import java.io.IOException;
import java.util.ArrayList;

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
        Spider spider = new Spider("http://stridetravel.com/");
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("china");
        searchParams.add("group");
        searchParams.add("panda");
        spider.search("http://stridetravel.com/", searchParams);
    }
}
