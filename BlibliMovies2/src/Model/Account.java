package Model;

public class Account {
    private String username;
    private String password;
    private int storeid;
    private int roleid;
    private int id;
    private boolean status;

    public Account(String username, int storeid, String password, int roleid, int id, boolean status){
        setUsername(username);
        setPassword(password);
        setRoleid(roleid);
        setStoreid(storeid);
        setid(id);
        setStatus(status);
    }

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

    public Account(String username, int storeid, int roleid){
        setUsername(username);
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
