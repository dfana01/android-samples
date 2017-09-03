package dfb.com.greendaoexample;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import dfb.com.greendaoexample.model.DaoMaster;
import dfb.com.greendaoexample.model.DaoSession;

/**
 * Created by Dante on 8/12/2017.
 */

public class App extends Application {
    private DaoSession daoSession;
    @Override
    public void onCreate(){
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
