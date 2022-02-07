package com.codetriage.scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Our scraper class
 */
public class App {

  /**
   * The main method of our class, which will also house the scraping
   * functionality.
   */
  public static void main(String[] args) {

    try {
      /**
       * Here we create a document object,
       * The we use JSoup to fetch the website.
       */
      Document doc = Jsoup.connect("https://www.metacritic.com/browse/movies/release-date/theaters/date").get();
      
      /**
       * With the document fetched,
       * we use JSoup???s title() method to fetch the title
       */ 
      System.out.printf("\nWebsite Title: %s\n\n", doc.title());


      // Get the list of repositories
      
      Elements repositories = doc.getElementsByClass("clamp-list");
      

    		  /**
       * For each repository, extract the following information:
       * 1. Title
       * 2. Number of issues
       * 3. Description
       * 4. Full name on github
       */
      for (Element repository : repositories) {
        // Extract the title
        Elements repositorySummaries = repository.getElementsByClass("clamp-summary-wrap");
        for (Element repositorySummary: repositorySummaries) {
        	String movieTitle = repositorySummary.getElementsByClass("title").select("a").text();
        	String releaseDate = repositorySummary.getElementsByClass("clamp-details").text();
        

        	
        	System.out.println("Title: "+movieTitle);
        	System.out.println("Release Date: "+releaseDate+"\n");
        	
        	String repositoryTitle = repositorySummary.getElementsByClass("title").text();
        	
        	//System.out.println(repositorySummary.getElementsByTag("a").text()+"\n");
        	//System.out.println(repositoryTitle+"\n");
        }
       // String repositoryTitle = repositorySummaries.text();

        // Extract the number of issues on the repository
       // String repositoryIssues = repository.getElementsByClass("repo-item-issues").text();

        // Extract the description of the repository
       // String repositoryDescription = repository.getElementsByClass("repo-item-description").text();

        // Get the full name of the repository
      //  String repositoryGithubName = repository.getElementsByClass("repo-item-full-name").text();

        /**
         * The repository full name contains brackets that we remove first
         * before generating the valif Github link.
         */
       // String repositoryGithubLink = "https://github.com/" + repositoryGithubName.replaceAll("[()]", "");

        // Format and print the information to the console
       // System.out.println(repositoryTitle+"\n");
        
//        System.out.println(repositoryTitle + " - " + repositoryIssues);
//        System.out.println("\t" + repositoryDescription);
//        System.out.println("\t" + repositoryGithubLink);
//        System.out.println("\n");

      }

    /**
     * Incase of any IO errors, we want the messages 
     * written to the console
     */
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
