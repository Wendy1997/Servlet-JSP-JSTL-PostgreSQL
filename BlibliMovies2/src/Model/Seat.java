package Model;

public class Seat {
    private int number;
    private int studioId;
    private String name;

    public Seat(int number, int studioId, String name) {
        this.number = number;
        this.studioId = studioId;
        this.name = name;
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

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
