package Model;

public class Account {
    private String username;
    private String password;
    private int storeid;
    private String role;
    private int id;

    public Account(String username, int storeid, String password, String role, int id){
        setUsername(username);
        setPassword(password);
        setRole(role);
        setStoreID(storeid);
        setID(id);
    }

    public Account(String username, int storeid, String password, String role){
        setUsername(username);
        setPassword(password);
        setRole(role);
        setStoreID(storeid);
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

    public int getID(){
        return this.id;
    }

    public int getStoreID(){
        return this.storeid;
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

    public void setID(int id){
        this.id = id;
    }

    public void setStoreID(int storeid){
        this.storeid = storeid;
    }
}
