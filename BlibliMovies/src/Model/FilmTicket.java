package Model;

public class FilmTicket {
    private int id;
    private int filmId;
    private int studioId;
    private int seatNumber;
    private int screeningId;
    private int price;

    public FilmTicket(int id, int filmId, int studioId, int seatNumber, int screeningId, int price) {
        this.id = id;
        this.filmId = filmId;
        this.studioId = studioId;
        this.seatNumber = seatNumber;
        this.screeningId = screeningId;
        this.price = price;
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

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
