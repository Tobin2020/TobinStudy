package top.tobin.basic.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import top.tobin.basic.bean.UserBean;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserBean entity);

    @Insert
    void insertAll(UserBean... entity);

    @Query("SELECT * FROM UserBean WHERE userId = 1")
    UserBean getUserOne();

    @Update
    void update(UserBean entity);

    @Query("SELECT * FROM UserBean ")
    List<UserBean> getUserInfo();

    @Query("SELECT * FROM UserBean WHERE userId = 1")
    Single<UserBean> getUserOneByRxJava();

    @Query("SELECT * FROM UserBean WHERE userId = 1")
    LiveData<UserBean> getUserByLiveData();

    @Query("SELECT * FROM UserBean WHERE userId = :userId")
    Flowable<UserBean> getUserByRxJava(String userId);

    @Query("UPDATE UserBean SET userId = :userId WHERE userName = :userName")
    public abstract int updateUser(String userId, String userName);
}
