package Model;

public class OrderDetail {
    private int id;
    private int invoiceId;
    private String storename;
    private String itemName;
    private int quantity;
    private int price;
    private boolean discountStatus;

    public OrderDetail(int id, int invoiceId, String storename, String itemName, int quantity, int price, boolean discountStatus) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.storename = storename;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.discountStatus = discountStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isDiscountStatus() {
        return discountStatus;
    }

    public void setDiscountStatus(boolean discountStatus) {
        this.discountStatus = discountStatus;
    }
}
