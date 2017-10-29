package Model;

public class Account {
    private String username;
    private String password;
    private String storename;
    private String role;

    public Account(String username, String storename, String password, String role){
        setUsername(username);
        setPassword(password);
        setRole(role);
        setStorename(storename);
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getRole(){
        return this.role;
    }

    public String getStorename(){
        return this.storename;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRole(String role){
        this.role = role;
    }

    public void setStorename(String storename){
        this.storename = storename;
    }
}
