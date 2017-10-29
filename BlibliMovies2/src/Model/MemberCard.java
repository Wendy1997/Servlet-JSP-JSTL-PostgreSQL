package Model;

public class MemberCard {
    private int id;
    private String storename;
    private String fullname;
    private String gender;
    private String birthDate;
    private String phoneNumber;
    private String email;

    public MemberCard(int id, String storename, String fullname, String gender, String birthDate, String phoneNumber, String email) {
        this.id = id;
        this.storename = storename;
        this.fullname = fullname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public MemberCard(String storename, String fullname, String gender, String birthDate, String phoneNumber, String email) {
        this.storename = storename;
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

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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
}
