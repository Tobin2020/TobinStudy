package com.tobin.top.room.dao;

import com.tobin.top.bean.UserBean;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserBean entity);

    @Insert
    void insertAll(UserBean... entity);

    @Query("SELECT * FROM UserBean WHERE userId = 1")
    UserBean getAdOne();

    @Update
    void update(UserBean entity);

    @Query("SELECT * FROM UserBean ")
    List<UserBean> getAdInfo();

    @Query("SELECT * FROM UserBean WHERE userId = 1")
    Single<UserBean> getAdOneByRxJava();

    @Query("SELECT * FROM UserBean WHERE userId = 1")
    LiveData<UserBean> getAdByLiveData();

    @Query("SELECT * FROM UserBean WHERE userId = :userId")
    Flowable<UserBean> getAgreeByRxJava(String userId);

    @Query("UPDATE UserBean SET userId = :userId WHERE userName = :userName")
    public abstract int setAdType(String userId, String userName);
}
