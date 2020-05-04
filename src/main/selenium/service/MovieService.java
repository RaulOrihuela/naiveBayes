package service;

import bean.Movie;
import util.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String result = "";
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
            result += "Critics Score : " + rs.getString("criticsScore") + "\n";
            result += "Users Score : " + rs.getString("userScore");
        }
        rs.close();
        statement.close();
        connection.close();
        return result;
    }

}
