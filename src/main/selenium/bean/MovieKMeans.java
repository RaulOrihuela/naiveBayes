package bean;

import java.util.Objects;

public class MovieKMeans {
    private String title;
    private Double emotionPleasantness, emotionIntensity, length, metascore, imdbRating;

    public MovieKMeans() {
        this.emotionPleasantness = 0.0;
        this.emotionIntensity = 0.0;
        this.length = 0.0;
        this.metascore = 0.0;
        this.imdbRating = 0.0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getEmotionPleasantness() {
        return emotionPleasantness;
    }

    public void setEmotionPleasantness(Double emotionPleasantness) {
        this.emotionPleasantness = emotionPleasantness;
    }

    public Double getEmotionIntensity() {
        return emotionIntensity;
    }

    public void setEmotionIntensity(Double emotionIntensity) {
        this.emotionIntensity = emotionIntensity;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getMetascore() {
        return metascore;
    }

    public void setMetascore(Double metascore) {
        this.metascore = metascore;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString() {
        return "MovieKMeans{" +
                "title='" + title + '\'' +
                ", emotionPleasantness=" + emotionPleasantness +
                ", emotionIntensity=" + emotionIntensity +
                ", length=" + length +
                ", metascore=" + metascore +
                ", imdbRating=" + imdbRating +
                '}';
    }

    public static Double distance(MovieKMeans movie1, MovieKMeans movie2){
        Double sum = 0.0;
        sum += (movie1.emotionPleasantness - movie2.emotionPleasantness) * (movie1.emotionPleasantness - movie2.emotionPleasantness);
        sum += (movie1.emotionIntensity - movie2.emotionIntensity) * (movie1.emotionIntensity - movie2.emotionIntensity);
        sum += (movie1.length - movie2.length) * (movie1.length - movie2.length);
        sum += (movie1.metascore - movie2.metascore) * (movie1.metascore - movie2.metascore);
        sum += (movie1.imdbRating - movie2.imdbRating) * (movie1.imdbRating - movie2.imdbRating);
        return Math.sqrt(sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieKMeans)) return false;
        MovieKMeans that = (MovieKMeans) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(emotionPleasantness, that.emotionPleasantness) &&
                Objects.equals(emotionIntensity, that.emotionIntensity) &&
                Objects.equals(length, that.length) &&
                Objects.equals(metascore, that.metascore) &&
                Objects.equals(imdbRating, that.imdbRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, emotionPleasantness, emotionIntensity, length, metascore, imdbRating);
    }
}
