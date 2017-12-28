package Model;

public class StoreAccount {
    private String username;
    private String password;
    private String nama;
    private int id;

    public StoreAccount(String username, String password, String nama, int id){
        setUsername(username);
        setPassword(password);
        setNama(nama);
        setID(id);
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

    public int getID(){
        return this.id;
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

    public void setID(int id){
        this.id = id;
    }
}
