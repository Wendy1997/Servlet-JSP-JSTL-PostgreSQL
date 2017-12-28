package Model;

public class Studio {
    private int id;
    private int storeid;
    private String name;
    private String type;
    private int price;

    public Studio(int id, int storeid, String name, String type, int price) {
        this.id = id;
        this.storeid = storeid;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Studio(int storeid, String name, String type, int price) {
        this.storeid = storeid;
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

    public int getStoreID() {
        return storeid;
    }

    public void setStoreID(int storeid) {
        this.storeid = storeid;
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
