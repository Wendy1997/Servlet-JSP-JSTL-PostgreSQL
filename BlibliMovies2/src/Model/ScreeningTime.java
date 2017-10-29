package Model;

public class ScreeningTime {
    private int id;
    private int filmId;
    private int studioId;
    private String storename;
    private String time;
    private int duration;

    public ScreeningTime(int id, int filmId, int studioId, String storename, String time, int duration) {
        this.id = id;
        this.filmId = filmId;
        this.studioId = studioId;
        this.storename = storename;
        this.time = time;
        this.duration = duration;
    }

    public ScreeningTime(int filmId, int studioId, String storename, String time, int duration) {
        this.filmId = filmId;
        this.studioId = studioId;
        this.storename = storename;
        this.time = time;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
