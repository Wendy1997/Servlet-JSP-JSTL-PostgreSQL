package Model;

public class Seat {
    private int number;
    private int studioId;

    public Seat(int number, int studioId) {
        this.number = number;
        this.studioId = studioId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }
}
