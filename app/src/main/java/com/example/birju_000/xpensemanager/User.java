/**
 * Created by Bpatel0967 on 10/12/2017.
 */
package com.example.birju_000.xpensemanager;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    public  int userId;
    public String userName;
    public String userPassword;

    public User(int id, String userName, String userPassword){
        this.userId = id;
        this.userName = userName;
        this.userPassword = userPassword;
    }

}
