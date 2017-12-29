package Model;

public class StudioType {
    private int id;
    private String type;
    private int storeid;
    private boolean status;

    public StudioType(int id, String type, int storeid, boolean status) {
        this.id = id;
        this.type = type;
        this.storeid = storeid;
        this.status = status;
    }

    public StudioType(int id, String type, int storeid) {
        this.id = id;
        this.type = type;
        this.storeid = storeid;
    }

    public StudioType(String type, int storeid) {
        this.type = type;
        this.storeid = storeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
