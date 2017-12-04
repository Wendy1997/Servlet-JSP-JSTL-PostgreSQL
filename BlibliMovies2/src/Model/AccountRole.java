package Model;

public class AccountRole {
    private String id;
    private String role;
    private String storename;

    public AccountRole(String id, String role, String storename) {
        this.id = id;
        this.role = role;
        this.storename = storename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }
}
