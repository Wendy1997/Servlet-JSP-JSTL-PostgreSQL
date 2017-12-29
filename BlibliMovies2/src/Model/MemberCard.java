package Model;

public class MemberCard {
    private int id;
    private int storeid;
    private String fullname;
    private int gender;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private boolean status;

    public MemberCard(int id, int storeid, String fullname, int gender, String birthDate, String phoneNumber, String email, boolean status) {
        this.id = id;
        this.storeid = storeid;
        this.fullname = fullname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
    }

    public MemberCard(int id, int storeid, String fullname, int gender, String birthDate, String phoneNumber, String email) {
        this.id = id;
        this.storeid = storeid;
        this.fullname = fullname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public MemberCard(int storeid, String fullname, int gender, String birthDate, String phoneNumber, String email) {
        this.storeid = storeid;
        this.fullname = fullname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreID() {
        return storeid;
    }

    public void setStoreID(int storeid) {
        this.storeid = storeid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
