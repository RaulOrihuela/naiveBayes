package bean;

public class Movie {
    String title;
    Boolean isAction, isAdventure, isComedy, isCrime, isDrama, isFantasy,
            isHorror, isMystery, isRomance, isSciFi, isThriller;

    String metascore, imdbRating, length;

    public Movie() {
        this.isAction = false;
        this.isAdventure = false;
        this.isComedy = false;
        this.isCrime = false;
        this.isDrama = false;
        this.isFantasy = false;
        this.isHorror = false;
        this.isMystery = false;
        this.isRomance = false;
        this.isSciFi = false;
        this.isThriller = false;
    }

    public Movie(String title, Boolean isAction, Boolean isAdventure, Boolean isComedy, Boolean isCrime, Boolean isDrama, Boolean isFantasy, Boolean isHorror, Boolean isMystery, Boolean isRomance, Boolean isSciFi, Boolean isThriller, String metascore, String imdbRating, String length) {
        this.title = title;
        this.isAction = isAction;
        this.isAdventure = isAdventure;
        this.isComedy = isComedy;
        this.isCrime = isCrime;
        this.isDrama = isDrama;
        this.isFantasy = isFantasy;
        this.isHorror = isHorror;
        this.isMystery = isMystery;
        this.isRomance = isRomance;
        this.isSciFi = isSciFi;
        this.isThriller = isThriller;
        this.metascore = metascore;
        this.imdbRating = imdbRating;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getAction() {
        return isAction;
    }

    public void setAction(Boolean action) {
        isAction = action;
    }

    public Boolean getAdventure() {
        return isAdventure;
    }

    public void setAdventure(Boolean adventure) {
        isAdventure = adventure;
    }

    public Boolean getComedy() {
        return isComedy;
    }

    public void setComedy(Boolean comedy) {
        isComedy = comedy;
    }

    public Boolean getCrime() {
        return isCrime;
    }

    public void setCrime(Boolean crime) {
        isCrime = crime;
    }

    public Boolean getDrama() {
        return isDrama;
    }

    public void setDrama(Boolean drama) {
        isDrama = drama;
    }

    public Boolean getFantasy() {
        return isFantasy;
    }

    public void setFantasy(Boolean fantasy) {
        isFantasy = fantasy;
    }

    public Boolean getHorror() {
        return isHorror;
    }

    public void setHorror(Boolean horror) {
        isHorror = horror;
    }

    public Boolean getMystery() {
        return isMystery;
    }

    public void setMystery(Boolean mystery) {
        isMystery = mystery;
    }

    public Boolean getRomance() {
        return isRomance;
    }

    public void setRomance(Boolean romance) {
        isRomance = romance;
    }

    public Boolean getSciFi() {
        return isSciFi;
    }

    public void setSciFi(Boolean sciFi) {
        isSciFi = sciFi;
    }

    public Boolean getThriller() {
        return isThriller;
    }

    public void setThriller(Boolean thriller) {
        isThriller = thriller;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", isAction=" + isAction +
                ", isAdventure=" + isAdventure +
                ", isComedy=" + isComedy +
                ", isCrime=" + isCrime +
                ", isDrama=" + isDrama +
                ", isFantasy=" + isFantasy +
                ", isHorror=" + isHorror +
                ", isMystery=" + isMystery +
                ", isRomance=" + isRomance +
                ", isSciFi=" + isSciFi +
                ", isThriller=" + isThriller +
                ", metascore='" + metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", length='" + length + '\'' +
                '}';
    }

    public static String categorizeLength(int lengthMin){
        if (lengthMin < 100) return ("short");
        else if (lengthMin < 150) return ("medium");
        else return ("long");
    }

    public void compareGenre(String genre){
        switch (genre){
            case "Action":
                this.isAction = true;
                break;
            case "Adventure":
                this.isAdventure = true;
                break;
            case "Comedy":
                this.isComedy = true;
                break;
            case "Crime":
                this.isCrime = true;
                break;
            case "Drama":
                this.isDrama = true;
                break;
            case "Fantasy":
                this.isFantasy = true;
                break;
            case "Horror":
                this.isHorror = true;
                break;
            case "Mystery":
                this.isMystery = true;
                break;
            case "Romance":
                this.isRomance = true;
                break;
            case "Sci-Fi":
                this.isSciFi = true;
                break;
            case "Thriller":
                this.isThriller = true;
                break;
        }
    }

    public MovieKMeans prepareKMeans(){
        MovieKMeans transform = new MovieKMeans();
        transform.setTitle(this.title);
        Double pleasantness = 0.0, intensity = 0.0;
        Integer attributeCount = 0;

        if (this.isAction) { pleasantness += 7.41; intensity += 8.41; attributeCount ++;}
        if (this.isAdventure) { pleasantness += 8.83; intensity += 7.83; attributeCount ++;}
        if (this.isComedy) { pleasantness += 7.33; intensity += 7.16; attributeCount ++;}
        if (this.isCrime) { pleasantness += 5.3; intensity += 8.4; attributeCount ++;}
        if (this.isDrama) { pleasantness += 4.25; intensity += 7.5; attributeCount ++;}
        if (this.isFantasy) { pleasantness += 8.16; intensity += 9.16; attributeCount ++;}
        if (this.isHorror) { pleasantness += 2.33; intensity += 8.5; attributeCount ++;}
        if (this.isMystery) { pleasantness += 4; intensity += 7.8; attributeCount ++;}
        if (this.isRomance) { pleasantness += 6.83; intensity += 4.83; attributeCount ++;}
        if (this.isSciFi) { pleasantness += 7.25; intensity += 8; attributeCount ++;}
        if (this.isThriller) { pleasantness += 4.16; intensity += 9.5; attributeCount ++;}

        if (attributeCount!=0){
            pleasantness /= attributeCount;
            intensity /= attributeCount;
        }

        transform.setEmotionPleasantness(pleasantness);
        transform.setEmotionIntensity(intensity);

        switch (this.metascore) {
            case "NA":
                transform.setMetascore(0.0);
                break;
            case "unfavorable":
                transform.setMetascore(1.0);
                break;
            case "mixed":
                transform.setMetascore(2.0);
                break;
            case "favorable":
                transform.setMetascore(3.0);
                break;
        }

        switch (this.imdbRating) {
            case "NA":
                transform.setImdbRating(0.0);
                break;
            case "unfavorable":
                transform.setImdbRating(1.0);
                break;
            case "mixed":
                transform.setImdbRating(2.0);
                break;
            case "favorable":
                transform.setImdbRating(3.0);
                break;
        }

        switch (this.length) {
            case "NA":
                transform.setLength(0.0);
                break;
            case "short":
                transform.setLength(1.0);
                break;
            case "medium":
                transform.setLength(2.0);
                break;
            case "long":
                transform.setLength(3.0);
                break;
        }

        return transform;
    }
}
