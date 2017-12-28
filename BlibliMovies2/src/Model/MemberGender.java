package Model;

public class MemberGender {
    private int id;
    private String gender;
    private int storeid;

    public MemberGender(int id, String gender, int storeid) {
        this.id = id;
        this.gender = gender;
        this.storeid = storeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
