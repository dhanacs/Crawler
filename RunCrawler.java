public class RunCrawler
{
  public static void main(String[] args) throws Exception
  {
    MyCrawler crawl = new MyCrawler();

    crawl.loadProperties();
    crawl.startCrawler();

    System.out.println("Finished crawling !");
  }
}
