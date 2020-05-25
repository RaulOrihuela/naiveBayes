package service;

import bean.Movie;
import bean.MovieKMeans;
import util.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class MovieService {
    public static void movie_C(Movie movie) throws SQLException {
        Connection connection = MySQLConnection.getConnection("imdb", "root","5th1ra5ukham45anam");
        CallableStatement statement = connection.prepareCall("CALL movie_C(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        statement.setString("in_title", movie.getTitle());
        statement.setBoolean("in_isAction", movie.getAction());
        statement.setBoolean("in_isAdventure", movie.getAdventure());
        statement.setBoolean("in_isComedy", movie.getComedy());
        statement.setBoolean("in_isCrime", movie.getCrime());
        statement.setBoolean("in_isDrama", movie.getDrama());
        statement.setBoolean("in_isFantasy", movie.getFantasy());
        statement.setBoolean("in_isHorror", movie.getHorror());
        statement.setBoolean("in_isMystery", movie.getMystery());
        statement.setBoolean("in_isRomance", movie.getRomance());
        statement.setBoolean("in_isSciFi", movie.getSciFi());
        statement.setBoolean("in_isThriller", movie.getThriller());
        statement.setString("in_criticsScore", movie.getMetascore());
        statement.setString("in_userScore", movie.getImdbRating());
        statement.setString("in_length", movie.getLength());

        statement.execute();
        statement.close();
        connection.close();
    }

    public static String movie_predict(Movie movie) throws SQLException {
        String result = "", temp1 = "", temp2 = "";
        Connection connection = MySQLConnection.getConnection("imdb", "root","5th1ra5ukham45anam");
        CallableStatement statement = connection.prepareCall("CALL predict(?,?,?,?,?,?,?,?,?,?,?,?)");
        statement.setBoolean("in_isAction", movie.getAction());
        statement.setBoolean("in_isAdventure", movie.getAdventure());
        statement.setBoolean("in_isComedy", movie.getComedy());
        statement.setBoolean("in_isCrime", movie.getCrime());
        statement.setBoolean("in_isDrama", movie.getDrama());
        statement.setBoolean("in_isFantasy", movie.getFantasy());
        statement.setBoolean("in_isHorror", movie.getHorror());
        statement.setBoolean("in_isMystery", movie.getMystery());
        statement.setBoolean("in_isRomance", movie.getRomance());
        statement.setBoolean("in_isSciFi", movie.getSciFi());
        statement.setBoolean("in_isThriller", movie.getThriller());
        statement.setString("in_length", movie.getLength());

        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            temp1 = rs.getString("criticsScore");
            temp2 = rs.getString("userScore");

            movie.setMetascore(temp1);
            movie.setImdbRating(temp2);

            result += "Critics Score : " + temp1 + "\n";
            result += "Users Score : " + temp2;
        }
        rs.close();
        statement.close();
        connection.close();
        return result;
    }

    public static ArrayList<Movie> movie_RA() throws SQLException {
        ArrayList<Movie> allMovies = new ArrayList<>();
        Connection connection = MySQLConnection.getConnection("imdb", "root","5th1ra5ukham45anam");
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM movie");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Movie temp = new Movie();
            temp.setTitle(rs.getString(2));
            temp.setAction(rs.getBoolean(3));
            temp.setAdventure(rs.getBoolean(4));
            temp.setComedy(rs.getBoolean(5));
            temp.setCrime(rs.getBoolean(6));
            temp.setDrama(rs.getBoolean(7));
            temp.setFantasy(rs.getBoolean(8));
            temp.setHorror(rs.getBoolean(9));
            temp.setMystery(rs.getBoolean(10));
            temp.setRomance(rs.getBoolean(11));
            temp.setSciFi(rs.getBoolean(12));
            temp.setThriller(rs.getBoolean(13));
            temp.setMetascore(rs.getString(14));
            temp.setImdbRating(rs.getString(15));
            temp.setLength(rs.getString(16));
            allMovies.add(temp);
        }
        rs.close();
        ps.close();
        connection.close();

        return allMovies;
    }

    public static ArrayList<MovieKMeans> movie_RA_kMeans() throws SQLException {
        ArrayList<MovieKMeans> result = new ArrayList<>();
        ArrayList<Movie> list = MovieService.movie_RA();
        for (Movie movie : list){
            result.add(movie.prepareKMeans());
        }
        return result;
    }


}
