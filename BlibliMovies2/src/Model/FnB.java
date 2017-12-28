package Model;

public class FnB {
    private int id;
    private int storeid;
    private String cover;
    private String name;
    private String type;
    private String size;
    private int price;

    public FnB(int id, int storeid, String cover, String name, String type, String size, int price) {
        this.id = id;
        this.storeid = storeid;
        this.cover = cover;
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
    }

    public FnB(int storeid, String cover, String name, String type, String size, int price) {
        this.storeid = storeid;
        this.cover = cover;
        this.name = name;
        this.type = type;
        this.size = size;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
