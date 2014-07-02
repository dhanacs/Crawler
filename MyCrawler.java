import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

public class MyCrawler extends WebCrawler
{
    private Properties properties;
    private static int urlCount = 0; // This has to be static.
    private static Pattern FILTERS;

    public MyCrawler() throws Exception
    {
      SetProperties properties = new SetProperties();
      loadProperties();
    }

    // Load the properties.
    public void loadProperties() throws Exception
    {
      properties = new Properties();
      InputStream input = new FileInputStream("config.properties");

      properties.load(input);

      // Set the filters.
      FILTERS = Pattern.compile(properties.getProperty("FILTER1") + properties.getProperty("FILTER2")
                              + properties.getProperty("FILTER3") + properties.getProperty("FILTER4"));
    }

    // Implement this function to specify whether the given URL should be crawled or not (based on your crawling logic).
    @Override
    public boolean shouldVisit(WebURL url)
    {
      String href = url.getURL().toLowerCase();
      boolean flag = !FILTERS.matcher(href).matches() && href.startsWith(properties.getProperty("STARTS_WITH"));
      return flag;
    }

    // Called when a page is fetched and ready to be processed by your program.
    @Override
    public void visit(Page page)
    {
      if(page.getParseData() instanceof HtmlParseData)
      {
        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
        String text = htmlParseData.getHtml().toString();
        String query = properties.getProperty("QUERY1");
        String url = page.getWebURL().getURL();

        if (text.contains(query))
        {
          System.out.println("[" + (urlCount + 1) + "] " + url + "");
          ++urlCount;
        }
      }
    }

    // Start the crawlers.
    public void startCrawler() throws Exception
    {
      int numberOfCrawlers = Integer.parseInt(properties.getProperty("CRAWLERS_COUNT"));
      String crawlStorageFolder = properties.getProperty("STORAGE_FOLDER");

      CrawlConfig config = new CrawlConfig();
      config.setCrawlStorageFolder(crawlStorageFolder);
      config.setMaxDepthOfCrawling(5);
      //config.setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1944.0 Safari/537.36");

      // Instantiate the controller for this crawl.
      PageFetcher pageFetcher = new PageFetcher(config);
      RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
      robotstxtConfig.setEnabled(false);
      RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
      CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

      // For each crawl, you need to add some seed URL's. These are the first
      // URLs that are fetched and then the crawler starts following links which are found in these pages.
      String seedURL = properties.getProperty("SEED_URL1");
      controller.addSeed(seedURL);

      // Do not respect "robots.txt".
      robotstxtConfig.setEnabled(false);

      // Start the crawl. This is a blocking operation,
      // meaning that your code will reach the line after this only when crawling is finished.
      controller.start(MyCrawler.class, numberOfCrawlers);
    }
}

