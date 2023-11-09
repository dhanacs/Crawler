## MyCrawler Readme

### Overview
This Java program utilizes the Crawler4j library to crawl web pages and extract information based on specified criteria. The crawler is designed to search for a specific query within the HTML content of the pages and print the URLs that contain the query.

### Dependencies
- [Crawler4j library](https://github.com/yasserg/crawler4j)

### Configuration
1. Ensure that the Crawler4j library is included in your project.
2. Create a properties file named `config.properties` with the following properties:
   - `FILTER1`, `FILTER2`, `FILTER3`, `FILTER4`: Regular expressions to filter URLs.
   - `STARTS_WITH`: Prefix for URLs to be considered.
   - `QUERY1`: The query to search for within the HTML content.
   - `CRAWLERS_COUNT`: Number of crawler threads to run simultaneously.
   - `STORAGE_FOLDER`: Folder to store crawl data.
   - `SEED_URL1`: Initial URL(s) to start crawling.

### Usage
1. Instantiate the `MyCrawler` class.
   ```java
   MyCrawler myCrawler = new MyCrawler();
