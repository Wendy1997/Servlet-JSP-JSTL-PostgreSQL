package Model;

public class FilmGenre {
    private String id;
    private String genre;
    private int storeid;

    public FilmGenre(String id, String genre, int storeid) {
        this.id = id;
        this.genre = genre;
        this.storeid = storeid;
    }

    public FilmGenre(String genre, int storeid) {
        this.genre = genre;
        this.storeid = storeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getStoreID() {
        return storeid;
    }

    public void setStoreID(int storeid) {
        this.storeid = storeid;
    }
}
