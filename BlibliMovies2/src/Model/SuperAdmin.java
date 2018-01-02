package Model;

public class SuperAdmin {
    private String username;
    private String password;
    private int id;
    private boolean status;

    public SuperAdmin(String username, String password, int id, boolean status) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.status = status;
    }

    public SuperAdmin(String username, String password, int id){
        setUsername(username);
        setPassword(password);
        setId(id);
    }

    public SuperAdmin(String username, String password){
        setUsername(username);
        setPassword(password);
     }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public boolean getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
