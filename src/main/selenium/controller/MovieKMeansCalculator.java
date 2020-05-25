package controller;

import bean.Movie;
import bean.MovieKMeans;
import service.MovieService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MovieKMeansCalculator {
    private ArrayList<MovieKMeans> allMovies;

    public MovieKMeansCalculator() throws SQLException {
        allMovies = MovieService.movie_RA_kMeans();
    }

    public ArrayList<MovieKMeans> getRandCentroids(Integer k){
        ArrayList<MovieKMeans> centroids = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < k; i++){
            MovieKMeans temp = new MovieKMeans();
            temp.setTitle("Centroid " + i);
            temp.setEmotionPleasantness(rand.nextDouble()*10.0);
            temp.setEmotionIntensity(rand.nextDouble()*10.0);
            temp.setLength(rand.nextDouble()*3.0);
            temp.setMetascore(rand.nextDouble()*3.0);
            temp.setImdbRating(rand.nextDouble()*3.0);
            centroids.add(temp);
        }
        return centroids;
    }

    public HashMap<MovieKMeans,ArrayList<MovieKMeans>> clusterMovies(ArrayList<MovieKMeans> centroids){
        HashMap<MovieKMeans,ArrayList<MovieKMeans>> map = new HashMap<>();
        for (MovieKMeans centroid : centroids){
            map.put(centroid, new ArrayList<MovieKMeans>());
        }

        for (MovieKMeans movie: allMovies){
            Double distance = Double.MAX_VALUE;
            MovieKMeans nearestCentroid = null;
            for (MovieKMeans centroid: centroids){
                Double tempDistance = MovieKMeans.distance(movie,centroid);
                if (tempDistance<distance){
                    distance = tempDistance;
                    nearestCentroid = centroid;
                }
            }
            map.get(nearestCentroid).add(movie);
        }
        return map;
    }

    public ArrayList<MovieKMeans> findCentroids(HashMap<MovieKMeans,ArrayList<MovieKMeans>> map){
        ArrayList<MovieKMeans> newCentroids = new ArrayList<>();
        for (MovieKMeans centroid: map.keySet()){
            ArrayList<MovieKMeans> moviesInCluster = map.get(centroid);
            if (!moviesInCluster.isEmpty()) {
                //CALCULATE CENTER
                Double tempP = 0.0, tempI = 0.0, tempLength = 0.0, tempMeta = 0.0, tempImbdRating = 0.0;
                for (MovieKMeans movie : moviesInCluster) {
                    tempP += movie.getEmotionPleasantness();
                    tempI += movie.getEmotionIntensity();
                    tempLength += movie.getLength();
                    tempMeta += movie.getMetascore();
                    tempImbdRating += movie.getImdbRating();
                }
                tempP /= moviesInCluster.size();
                tempI /= moviesInCluster.size();
                tempLength /= moviesInCluster.size();
                tempMeta /= moviesInCluster.size();
                tempImbdRating /= moviesInCluster.size();

                //NEW CENTROID
                MovieKMeans tempCentroid = new MovieKMeans();
                tempCentroid.setTitle(centroid.getTitle());
                tempCentroid.setEmotionPleasantness(tempP);
                tempCentroid.setEmotionIntensity(tempI);
                tempCentroid.setLength(tempLength);
                tempCentroid.setMetascore(tempMeta);
                tempCentroid.setImdbRating(tempImbdRating);
                newCentroids.add(tempCentroid);
            } else {
                MovieKMeans tempCentroid = new MovieKMeans();
                tempCentroid.setTitle(centroid.getTitle());
                tempCentroid.setEmotionPleasantness(centroid.getEmotionPleasantness());
                tempCentroid.setEmotionIntensity(centroid.getEmotionIntensity());
                tempCentroid.setLength(centroid.getLength());
                tempCentroid.setMetascore(centroid.getMetascore());
                tempCentroid.setImdbRating(centroid.getImdbRating());
                newCentroids.add(tempCentroid);
            }
        }
        return newCentroids;
    }

    public HashMap<MovieKMeans,ArrayList<MovieKMeans>> kMeans(Integer k, Integer maxIterations, boolean detailed){
        ArrayList<MovieKMeans> centroids = getRandCentroids(k);
        HashMap<MovieKMeans, ArrayList<MovieKMeans>> clusters, previousCluster = new HashMap<>();

        for (int i = 0; i<maxIterations; i++){
            boolean isLastTime = i == maxIterations-1;
            clusters = clusterMovies(centroids);

            boolean shouldTerminate = isLastTime || clusters.equals(previousCluster);
            previousCluster = clusters;
            if (shouldTerminate)break;

            if (detailed){
                for (MovieKMeans key : clusters.keySet()){
                    System.out.println("///////////////////////////////////////////////////////////////////////////////");
                    System.out.println("GENERATION: " + i + ", CENTROID ID: " + key.getTitle());
                    System.out.println(key.toString());
                    System.out.println("///////////////////////////////////////////////////////////////////////////////");
                    for (MovieKMeans movie : clusters.get(key)){
                        System.out.println(movie.toString());
                    }
                    System.out.println();
                }
            }

            centroids = findCentroids(clusters);
        }

        return previousCluster;
    }
}
