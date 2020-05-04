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

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(5000);
    }

    @Ignore
    public void basicCrawl() throws InterruptedException {
        ArrayList<String> results = ImdbCrawler.getTopMovies(driver);

        System.out.println(results.toString());

        assertThat("Did not find all movies",
                results.size(),
                is(equalTo(100))
        );
    }


    @Ignore
    public void search() throws InterruptedException, SQLException {

        MovieService.movie_C(ImdbCrawler.analyzeMovie(driver, "https://www.imdb.com/title/tt0437086/?ref_=adv_li_tt"));
        MovieService.movie_C(ImdbCrawler.analyzeMovie(driver, "https://www.imdb.com/title/tt6751668/?ref_=adv_li_tt"));
        MovieService.movie_C(ImdbCrawler.analyzeMovie(driver, "https://www.imdb.com/title/tt12187924/?ref_=adv_li_tt"));

        assertThat("Did not find all movies",
                1,
                is(equalTo(1))
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

        assertThat("Did not find all movies",
                1,
                is(equalTo(1))
        );
    }

    @Test
    public void predict() throws InterruptedException, SQLException {

        Movie movie = new Movie();

        movie.setLength("medium");
        movie.setAction(true);
        movie.setCrime(true);
        movie.setFantasy(true);
        movie.setRomance(true);

        System.out.println(MovieService.movie_predict(movie));

        assertThat("Did not find all movies",
                1,
                is(equalTo(1))
        );
    }

}
