package Model;

public class MemberGender {
    private String id;
    private String gender;
    private int storeid;

    public MemberGender(String id, String gender, int storeid) {
        this.id = id;
        this.gender = gender;
        this.storeid = storeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getStoreID() {
        return storeid;
    }

    public void setStoreID(int storeid) {
        this.storeid = storeid;
    }
}
