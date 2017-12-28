package Model;

public class FnBSize {
    private String id;
    private String size;
    private int storeid;

    public FnBSize(String id, String size, int storeid) {
        this.id = id;
        this.size = size;
        this.storeid = storeid;
    }

    public FnBSize(String size, int storeid) {
        this.size = size;
        this.storeid = storeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStoreID() {
        return storeid;
    }

    public void setStoreID(int storeid) {
        this.storeid = storeid;
    }
}
