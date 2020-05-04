package controller;

import bean.Movie;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.drivers.EnhancedWebDriver;

import java.util.ArrayList;
import java.util.List;

public class ImdbCrawler {

    public static ArrayList<String> getTopMovies(EnhancedWebDriver myDriver) throws InterruptedException {
        ArrayList<String> result = new ArrayList<>();

        //FIND BEST MOVIES
        myDriver.get("https://www.imdb.com/search/title/?title_type=feature&release_date=2019-01-01,2019-12-31");
        List<WebElement> links = myDriver.findDynamicElements(By.xpath("//*[@id=\"main\"]/div/div[3]/div/div/div[3]/h3/a"));

        //ADD TO RESULTS
        if (links != null) for (int i = 0; i < links.size(); i++) {
            //System.out.println(links.get(i).getText());
            //System.out.println(links.get(i).getAttribute("href"));
            result.add(links.get(i).getAttribute("href"));
        }

        //CHANGE TO WORST MOVIES
        String title = myDriver.getTitle();
        myDriver.findDynamicElement(By.xpath("//*[@id=\"main\"]/div/div[2]/a[1]")).click();
        myDriver.waitForTitleChange(title);
        links = myDriver.findDynamicElements(By.xpath("//*[@id=\"main\"]/div/div[3]/div/div/div[3]/h3/a"));

        //ADD TO RESULTS
        if (links != null) for (int i = 0; i < links.size(); i++) {
            //System.out.println(links.get(i).getText());
            //System.out.println(links.get(i).getAttribute("href"));
            result.add(links.get(i).getAttribute("href"));
        }

        return  result;
    }

    public static Movie analyzeMovie(EnhancedWebDriver myDriver, String url) throws InterruptedException{
        myDriver.get(url);
        Movie movie = new Movie();

        //GET TITLE
        movie.setTitle(myDriver.findDynamicElement(By.xpath("//*[@id=\"title-overview-widget\"]/div[1]/div[2]/div/div[2]/div[2]/h1")).getText());

        //GENRES
        List<WebElement> genres = myDriver.findDynamicElements(By.xpath("//*[@id=\"title-overview-widget\"]/div[1]/div[2]/div/div[2]/div[2]/div/a"));
        if (genres != null) for (int i = 0; i < genres.size() - 1; i++) {
            movie.compareGenre(genres.get(i).getText());
        }

        //LENGTH
        WebElement movieLength = myDriver.findDynamicElement(By.xpath("//*[@id=\"title-overview-widget\"]/div[1]/div[2]/div/div[2]/div[2]/div/time"));
        if (movieLength != null){
            String tempString = movieLength.getText();
            Integer lengthMin = Integer.parseInt(tempString.substring(0,tempString.indexOf("h"))) * 60;
            tempString = tempString.substring(tempString.indexOf("h")+2);
            lengthMin += Integer.parseInt(tempString.substring(0,tempString.indexOf("min")));

            movie.setLength(Movie.categorizeLength(lengthMin));
        }


        //METASCORE
        WebElement metaScore = myDriver.findDynamicElement(By.className("metacriticScore"),1);
        if (metaScore != null){
            String tempScore = metaScore.getAttribute("class");
            tempScore = tempScore.substring(tempScore.indexOf("score_")+6);
            tempScore = tempScore.substring(0,tempScore.indexOf(" "));
            movie.setMetascore(tempScore);
        } else movie.setMetascore("NA");

        //IMDB RATING
        WebElement userScore = myDriver.findDynamicElement(By.className("imdbRating"), 1);
        if (userScore != null){
            String tempScore = userScore.getText();
            tempScore = tempScore.substring(0, tempScore.indexOf("/"));
            float tempFloat = Float.parseFloat(tempScore);
            if (tempFloat<4.1) movie.setImdbRating("unfavorable");
            else if (tempFloat < 6.1) movie.setImdbRating("mixed");
            else movie.setImdbRating("favorable");
        } else movie.setImdbRating("NA");

        return movie;
    }

}
