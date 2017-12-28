package Model;

public class FnBType {
    private String id;
    private String type;
    private int storeid;

    public FnBType(String id, String type, int storeid) {
        this.id = id;
        this.type = type;
        this.storeid = storeid;
    }

    public FnBType(String type, int storeid) {
        this.type = type;
        this.storeid = storeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStoreID() {
        return storeid;
    }

    public void setStoreID(int storeid) {
        this.storeid = storeid;
    }
}
