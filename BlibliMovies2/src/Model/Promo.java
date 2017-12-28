package Model;

public class Promo {
    private int id;
    private int storeid;
    private String name;
    private String description;
    private boolean status;
    private int discountAmount;

    public Promo(int id, int storeid, String name, String description, boolean status, int discountAmount) {
        this.id = id;
        this.storeid = storeid;
        this.name = name;
        this.description = description;
        this.status = status;
        this.discountAmount = discountAmount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }
}
