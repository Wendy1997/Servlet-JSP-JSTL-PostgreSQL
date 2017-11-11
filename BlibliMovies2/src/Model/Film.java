package Model;

import java.util.List;

public class Film {
    private int id;
    private String storename;
    private String cover;
    private String title;
    private String genre;
    private int duration;
    private String director;
    private double rating;
    private int reviewTotal;
    private String startTime;
    private String endTime;
    private String language;
    private String subtitle;
    private String actor;
    private String sinopsis;
    private List<ScreeningTime> screeningTimes;

    public Film(String storename, String cover, String title, String genre, int duration, String director, double rating, int reviewTotal, String startTime, String endTime, String language, String subtitle, String actor, String sinopsis) {
        this.storename = storename;
        this.cover = cover;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.director = director;
        this.rating = rating;
        this.reviewTotal = reviewTotal;
        this.startTime = startTime;
        this.endTime = endTime;
        this.language = language;
        this.subtitle = subtitle;
        this.actor = actor;
        this.sinopsis = sinopsis;
    }

    public Film(int id, String storename, String cover, String title, String genre, int duration, String director, double rating, int reviewTotal, String startTime, String endTime, String language, String subtitle, String actor, String sinopsis) {
        this.id = id;
        this.storename = storename;
        this.cover = cover;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.director = director;
        this.rating = rating;
        this.reviewTotal = reviewTotal;
        this.startTime = startTime;
        this.endTime = endTime;
        this.language = language;
        this.subtitle = subtitle;
        this.actor = actor;
        this.sinopsis = sinopsis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewTotal() {
        return reviewTotal;
    }

    public void setReviewTotal(int reviewTotal) {
        this.reviewTotal = reviewTotal;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public List<ScreeningTime> getScreeningTimes(){
        return this.screeningTimes;
    }

    public void setScreeningTimes(List<ScreeningTime> screeningTimes){
        this.screeningTimes = screeningTimes;
    }
}
