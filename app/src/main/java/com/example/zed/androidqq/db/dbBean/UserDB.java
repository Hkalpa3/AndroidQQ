package com.example.zed.androidqq.db.dbBean;

/**
 * Created by zed on 2017/12/27.
 */

public class UserDB {
    private int userDb_id;
    private String userDb_name;
    private String userDb_psd;


    public UserDB(int userDb_id, String userDb_name, String userDb_psd) {
        this.userDb_id = userDb_id;
        this.userDb_name = userDb_name;
        this.userDb_psd = userDb_psd;
    }
    public UserDB(){

    }

    public int getUserDb_id() {
        return userDb_id;
    }

    public void setUserDb_id(int userDb_id) {
        this.userDb_id = userDb_id;
    }

    public String getUserDb_name() {
        return userDb_name;
    }

    public void setUserDb_name(String userDb_name) {
        this.userDb_name = userDb_name;
    }

    public String getUserDb_psd() {
        return userDb_psd;
    }

    public void setUserDb_psd(String userDb_psd) {
        this.userDb_psd = userDb_psd;
    }
    //    String user_head_img;



}
