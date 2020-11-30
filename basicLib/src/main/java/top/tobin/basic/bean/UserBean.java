package top.tobin.basic.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * @author lijunbin
 * @date 2020/9/16
 * @description UserBean
 */
@Entity
public class UserBean implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int userId;

    private String userName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
