package Imdb;

import bean.Movie;
import bean.MovieKMeans;
import controller.MovieKMeansCalculator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import service.MovieService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class KMeansTest {
    private static ArrayList<MovieKMeans> moviesK;
    private static MovieKMeansCalculator kMeansCalculator;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        moviesK = MovieService.movie_RA_kMeans();
        kMeansCalculator = new MovieKMeansCalculator();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        moviesK = null;
        kMeansCalculator = null;
    }

    @Test
    public void distanceAxioms() throws InterruptedException {

        MovieKMeans it = moviesK.get(16), alita = moviesK.get(49), frozen = moviesK.get(30);

        System.out.println(it.toString());
        System.out.println(alita.toString());
        System.out.println(frozen.toString());

        assertThat("Let it float, let it float",
                MovieKMeans.distance(it,frozen),
                is(greaterThan(MovieKMeans.distance(it,alita)))
        );

        System.out.println(MovieKMeans.distance(it,frozen));
        System.out.println(MovieKMeans.distance(it,alita));
        System.out.println(MovieKMeans.distance(alita,frozen));

        assertThat("You'll float too",
                MovieKMeans.distance(it,it),
                is(equalTo(0.0))
        );

        System.out.println(MovieKMeans.distance(it,it));

        assertThat("Reverse float",
                MovieKMeans.distance(it,frozen),
                is(equalTo(MovieKMeans.distance(frozen,it)))
        );
    }

    @Ignore
    public void singleIteration() throws InterruptedException, SQLException {
        HashMap<MovieKMeans, ArrayList<MovieKMeans>> clusters =
                kMeansCalculator.clusterMovies(kMeansCalculator.getRandCentroids(5));
        for (MovieKMeans key : clusters.keySet()){
            System.out.println("///////////////////////////////////////////////////////////////////////////////");
            System.out.println(key.toString());
            for (MovieKMeans movie : clusters.get(key)){
                System.out.println(movie.toString());
            }
            System.out.println();
        }

        System.out.println(kMeansCalculator.findCentroids(clusters));
    }

    @Test
    public void fullRun() throws InterruptedException, SQLException {
        HashMap<MovieKMeans, ArrayList<MovieKMeans>> clusters = kMeansCalculator.kMeans(5,3,true);

        for (MovieKMeans key : clusters.keySet()){
            System.out.println("///////////////////////////////////////////////////////////////////////////////");
            System.out.println("FINAL GENERATION, CENTROID ID: " + key.getTitle());
            System.out.println(key.toString());
            System.out.println("///////////////////////////////////////////////////////////////////////////////");
            for (MovieKMeans movie : clusters.get(key)){
                System.out.println(movie.getTitle());
            }
            System.out.println();
        }
    }

}
