package Model;

public class Promo {
    private int id;
    private String storename;
    private String name;
    private String description;
    private boolean status;
    private int discountAmount;

    public Promo(int id, String storename, String name, String description, boolean status, int discountAmount) {
        this.id = id;
        this.storename = storename;
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
