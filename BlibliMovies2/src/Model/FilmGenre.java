package Model;

public class FilmGenre {
    private String id;
    private String genre;
    private String storename;

    public FilmGenre(String id, String genre, String storename) {
        this.id = id;
        this.genre = genre;
        this.storename = storename;
    }

    public FilmGenre(String genre, String storename) {
        this.genre = genre;
        this.storename = storename;
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

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }
}
