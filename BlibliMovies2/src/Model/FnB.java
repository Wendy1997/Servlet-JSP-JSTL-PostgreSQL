package Model;

public class FnB {
    private int id;
    private int storeid;
    private String cover;
    private String name;
    private int type;
    private int size;
    private int price;
    private boolean status;

    public FnB(int id, int storeid, String cover, String name, int type, int size, int price, boolean status) {
        this.id = id;
        this.storeid = storeid;
        this.cover = cover;
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
        this.status = status;
    }

    public FnB(int id, int storeid, String cover, String name, int type, int size, int price) {
        this.id = id;
        this.storeid = storeid;
        this.cover = cover;
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
    }

    public FnB() {

    }

    public FnB(int storeid, String cover, String name, int type, int size, int price) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
