package Model;

public class MemberGender {
    private String id;
    private String gender;
    private String storename;

    public MemberGender(String id, String gender, String storename) {
        this.id = id;
        this.gender = gender;
        this.storename = storename;
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

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }
}
