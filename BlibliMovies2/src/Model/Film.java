package Model;

import java.util.List;
import java.util.Map;

public class Film {
    private int id;
    private int storeid;
    private String cover;
    private String title;
    private int genreid;
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
    private boolean status;
    private Map<String, List<ScreeningTime>> screeningList;

    public Film(int id, int storeid, String cover, String title, int genreid, int duration, String director, double rating, int reviewTotal, String startTime, String endTime, String language, String subtitle, String actor, String sinopsis, boolean status) {
        this.id = id;
        this.storeid = storeid;
        this.cover = cover;
        this.title = title;
        this.genreid = genreid;
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
        this.status = status;
    }

    public Film(int storeid, String cover, String title, int genreid, int duration, String director, double rating, int reviewTotal, String startTime, String endTime, String language, String subtitle, String actor, String sinopsis) {
        this.storeid = storeid;
        this.cover = cover;
        this.title = title;
        this.genreid = genreid;
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

    public Film(int id, int storeid, String cover, String title, int genreid, int duration, String director, double rating, int reviewTotal, String startTime, String endTime, String language, String subtitle, String actor, String sinopsis) {
        this.id = id;
        this.storeid = storeid;
        this.cover = cover;
        this.title = title;
        this.genreid = genreid;
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

    public Film(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreID() {
        return storeid;
    }

    public void setStoreID(int storeid) {
        this.storeid = storeid;
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

    public int getGenre() {
        return genreid;
    }

    public void setGenre(int genreidid) {
        this.genreid = genreid;
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

    public Map<String, List<ScreeningTime>> getScreeningList() {
        return screeningList;
    }

    public void setScreeningList(Map<String, List<ScreeningTime>> screeningList) {
        this.screeningList = screeningList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
