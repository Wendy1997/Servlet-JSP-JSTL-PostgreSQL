package Model;

public class ScreeningTime {
    private int id;
    private int filmId;
    private int studioId;
    private int storeid;
    private String time;
    private int duration;
    private boolean status;

    public ScreeningTime(int id, int filmId, int studioId, int storeid, String time, int duration, boolean status) {
        this.id = id;
        this.filmId = filmId;
        this.studioId = studioId;
        this.storeid = storeid;
        this.time = time;
        this.duration = duration;
        this.status = status;
    }

    public ScreeningTime(int id, int filmId, int studioId, int storeid, String time, int duration) {
        this.id = id;
        this.filmId = filmId;
        this.studioId = studioId;
        this.storeid = storeid;
        this.time = time;
        this.duration = duration;
    }

    public ScreeningTime(int filmId, int studioId, int storeid, String time, int duration) {
        this.filmId = filmId;
        this.studioId = studioId;
        this.storeid = storeid;
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

    public int getStoreID() {
        return storeid;
    }

    public void setStoreID(int storeid) {
        this.storeid = storeid;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
