package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

public class Account {
    public int id;
    public final String name;
    public final String mail;
    public final String password;
    public final String salt;
    public final AccountCategory category;
    public final Boolean state;

    public Account(final int id, final String name, final String mail, final String password, final String salt, final AccountCategory category, final Boolean state) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.salt = salt;
        this.category = category;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public AccountCategory getCategory() {
        return category;
    }

    public Boolean getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }
}
