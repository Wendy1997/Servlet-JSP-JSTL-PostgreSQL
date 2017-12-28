package Model;

public class AccountRole {
    private int id;
    private String role;
    private int storeid;

    public AccountRole(int id, String role, int storeid) {
        this.id = id;
        this.role = role;
        this.storeid = storeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getStoreID() {
        return storeid;
    }

    public void setStoreID(int storeid) {
        this.storeid = storeid;
    }
}
