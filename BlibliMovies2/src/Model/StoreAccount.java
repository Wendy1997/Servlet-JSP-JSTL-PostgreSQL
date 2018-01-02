package Model;

public class StoreAccount {
    private String username;
    private String password;
    private String nama;
    private int id;
    private boolean status;

    public StoreAccount(String username, String password, String nama, int id, boolean status) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.id = id;
        this.status = status;
    }

    public StoreAccount(String username, String password, String nama, int id){
        setUsername(username);
        setPassword(password);
        setNama(nama);
        setId(id);
    }

    public StoreAccount(String username, String password, String nama){
        setUsername(username);
        setPassword(password);
        setNama(nama);
     }

    public StoreAccount(String username, String nama, int id) {
        this.username = username;
        this.nama = nama;
        this.id = id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getNama(){
        return this.nama;
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

    public void setNama(String nama){
        this.nama = nama;
    }
}
