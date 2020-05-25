package Imdb;

import bean.Movie;
import controller.ImdbCrawler;
import org.junit.*;
import service.MovieService;
import util.drivers.*;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ImdbCrawlerTest {
    private static EnhancedWebDriver driver;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        driver = new EnhancedWebDriver(BrowserDriver.getDriver(BrowserOption.Chrome));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        driver.quit();
    }

    @Ignore
    public void basicCrawl() throws InterruptedException {
        ArrayList<String> results = ImdbCrawler.getTopMovies(driver);

        System.out.println(results.toString());

        for (int i = 0; i < 3; i ++) System.out.println(ImdbCrawler.analyzeMovie(driver, results.get(i)).toString());

        assertThat("Did not find all movies",
                results.size(),
                is(equalTo(100))
        );
    }


    @Ignore
    public void writeToDb() throws InterruptedException, SQLException {

        Movie movie = new Movie();

        movie.setTitle("Halo");
        movie.setMetascore("favorable");
        movie.setImdbRating("favorable");
        movie.setLength("long");
        movie.setAction(true);
        movie.setSciFi(true);
        movie.setAdventure(true);

        MovieService.movie_C(movie);

    }

    @Ignore
    public void predict() throws InterruptedException, SQLException {

        Movie movie = new Movie();

        movie.setLength(Movie.categorizeLength(120));
        movie.setAction(true);
        movie.setCrime(true);
        movie.setFantasy(true);
        movie.setRomance(true);

        System.out.println(MovieService.movie_predict(movie));

        assertThat("Did not match prediction",
                movie.getImdbRating(),
                is(equalTo("favorable"))
        );
        assertThat("Did not match prediction",
                movie.getMetascore(),
                is(equalTo("mixed"))
        );
    }

}
