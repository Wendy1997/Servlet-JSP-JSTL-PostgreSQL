package Model;

public class Invoice {
    private int id;
    private int memberId;
    private String accountUsername;
    private String storeName;
    private int promoId;
    private String orderDate;
    private double totalPrice;

    public Invoice(int id, int memberId, String accountUsername, String storeName, int promoId, String orderDate, double totalPrice) {
        this.id = id;
        this.memberId = memberId;
        this.accountUsername = accountUsername;
        this.storeName = storeName;
        this.promoId = promoId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public Invoice(int memberId, String accountUsername, String storeName, int promoId, String orderDate, double totalPrice) {
        this.memberId = memberId;
        this.accountUsername = accountUsername;
        this.storeName = storeName;
        this.promoId = promoId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public Invoice(String accountUsername, String storeName, String orderDate, double totalPrice) {
        this.accountUsername = accountUsername;
        this.storeName = storeName;
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

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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
