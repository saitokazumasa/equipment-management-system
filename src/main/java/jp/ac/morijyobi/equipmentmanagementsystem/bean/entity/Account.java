package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    public int id;
    public String name;
    public String mail;
    public String password;
    public String salt;
    public AccountCategory category;
    public Boolean state;
}
