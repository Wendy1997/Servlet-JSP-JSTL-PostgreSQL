package Model;

public class Ledger {
    private int id;
    private int invoiceId;
    private int memberId;
    private String storename;
    private String date;
    private String itemName;
    private int quantity;
    private int price;

    public Ledger(int id, int invoiceId, int memberId, String storename, String date, String itemName, int quantity, int price) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.memberId = memberId;
        this.storename = storename;
        this.date = date;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
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

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
