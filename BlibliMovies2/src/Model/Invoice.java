package Model;

public class Invoice {
    private int id;
    private int memberId;
    private int accountID;
    private int storeid;
    private int promoId;
    private String orderDate;
    private double totalPrice;

    public Invoice(int id, int memberId, int accountID, int storeid, int promoId, String orderDate, double totalPrice) {
        this.id = id;
        this.memberId = memberId;
        this.accountID = accountID;
        this.storeid = storeid;
        this.promoId = promoId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public Invoice(int memberId, int accountID, int storeid, int promoId, String orderDate, double totalPrice) {
        this.memberId = memberId;
        this.accountID = accountID;
        this.storeid = storeid;
        this.promoId = promoId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public Invoice(int accountID, int storeid, String orderDate, double totalPrice) {
        this.accountID = accountID;
        this.storeid = storeid;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getStoreID() {
        return storeid;
    }

    public void setStoreID(int storeid) {
        this.storeid = storeid;
    }

    public int getPromoId() {
        return promoId;
    }

    public void setPromoId(int promoId) {
        this.promoId = promoId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
