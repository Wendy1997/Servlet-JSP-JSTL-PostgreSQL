package Model;

public class Account {
    private String username;
    private String password;
    private int storeid;
    private int roleid;
    private int id;

    public Account(String username, int storeid, String password, int roleid, int id){
        setUsername(username);
        setPassword(password);
        setRoleid(roleid);
        setStoreid(storeid);
        setid(id);
    }

    public Account(String username, int storeid, String password, int roleid){
        setUsername(username);
        setPassword(password);
        setRoleid(roleid);
        setStoreid(storeid);
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public int getid(){
        return this.id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setid(int id){
        this.id = id;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }
}
