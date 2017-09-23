package Model;

public class StoreAccount {
    private String username;
    private String password;
    private String nama;

    public StoreAccount(String username, String password, String nama){
        setUsername(username);
        setPassword(password);
        setNama(nama);
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
