package Model;

public class Studio {
    private int id;
    private String storename;
    private String name;
    private String type;
    private int price;

    public Studio(int id, String storename, String name, String type, int price) {
        this.id = id;
        this.storename = storename;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Studio(String storename, String name, String type, int price) {
        this.storename = storename;
        this.name = name;
        this.type = type;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }
}
