package top.tobin.top.room;

import android.content.Context;

import top.tobin.basic.base.BaseApplication;
import top.tobin.top.bean.UserBean;
import top.tobin.top.room.dao.UserDao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import top.tobin.top.room.dao.UserDao;

@Database(entities = {UserBean.class}, version = 1, exportSchema = false)
public abstract class AppDataBases extends RoomDatabase {

    private static volatile AppDataBases dataBases;

    private static final String DATA_DB_NAME ="TobinStudy.db";

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public abstract UserDao userDao();

    public static AppDataBases getInstance(){
        if (dataBases==null){
            synchronized (AppDataBases.class){
                if (dataBases==null){
                    dataBases=buildDatabase(BaseApplication.getInstance().getApplicationContext());
                    dataBases.updateDatabaseCreated(BaseApplication.getInstance().getApplicationContext());
                }
            }
        }
        return dataBases;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDataBases buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, AppDataBases.class, DATA_DB_NAME)
                .build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATA_DB_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            //执行升级相关操作
////            database.execSQL("ALTER TABLE AdBean ADD COLUMN adType INTEGER NOT NULL DEFAULT 0 ");
//        }
//    };
}
