package Model;

public class StudioType {
    private String id;
    private String type;

    public StudioType(String id, String type, String storename) {
        this.id = id;
        this.type = type;
        this.storename = storename;
    }

    private String storename;

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

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }
}