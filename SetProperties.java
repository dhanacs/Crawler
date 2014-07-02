import java.io.*;
import java.util.Properties;

public class SetProperties
{
  public SetProperties()
  {
    Properties prop = new Properties();
    OutputStream output = null;

    try
    {
      output = new FileOutputStream("config.properties");

      // Set the properties.
      prop.setProperty("CRAWLERS_COUNT", "10");
      prop.setProperty("STORAGE_FOLDER", "/home/indix/CrawlerData");
      prop.setProperty("SEED_URL1", "http://www.chess.com/home/game_archive?sortby=&show=live_blitz&member=Hikaru");
      prop.setProperty("STARTS_WITH", "http://www.chess.com/");
      prop.setProperty("QUERY1", "Hikaru won");
      prop.setProperty("FILTER1", ".*(\\.(css|js|bmp|gif|jpe?g");
      prop.setProperty("FILTER2", "|png|tiff?|mid|mp2|mp3|mp4");
      prop.setProperty("FILTER3", "|wav|avi|mov|mpeg|ram|m4v|pdf");
      prop.setProperty("FILTER4", "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

      // Save the properties in project's root folder.
      prop.store(output, null);
    }
    catch (Exception e)
    {
      System.out.println("Exception while setting properties !");
    }
  }
}
