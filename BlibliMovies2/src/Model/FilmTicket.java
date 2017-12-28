package Model;

public class FilmTicket {
    private int id;
    private int filmId;
    private int studioId;
    private String seatNumber;
    private int screeningId;
    private int price;
    private int storeid;

    public FilmTicket(int id, int filmId, int studioId, String seatNumber, int screeningId, int price, int storeid) {
        this.id = id;
        this.filmId = filmId;
        this.studioId = studioId;
        this.seatNumber = seatNumber;
        this.screeningId = screeningId;
        this.price = price;
        this.storeid = storeid;
    }

    public FilmTicket(int filmId, int studioId, String seatNumber, int screeningId, int price, int storeid) {
        this.filmId = filmId;
        this.studioId = studioId;
        this.seatNumber = seatNumber;
        this.screeningId = screeningId;
        this.price = price;
        this.storeid = storeid;
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

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
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

    public int getStoreID(){
        return this.storeid;
    }

    public void setStoreID(int storeid){
        this.storeid = storeid;
    }
}
