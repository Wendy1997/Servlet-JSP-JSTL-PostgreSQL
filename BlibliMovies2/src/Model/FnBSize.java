package Model;

public class FnBSize {
    private String id;
    private String size;
    private String storename;

    public FnBSize(String id, String size, String storename) {
        this.id = id;
        this.size = size;
        this.storename = storename;
    }

    public FnBSize(String size, String storename) {
        this.size = size;
        this.storename = storename;
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

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }
}
