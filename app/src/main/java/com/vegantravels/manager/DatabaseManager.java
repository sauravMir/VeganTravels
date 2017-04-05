package com.vegantravels.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.vegantravels.dao.Criuzes;
import com.vegantravels.dao.CriuzesDao;
import com.vegantravels.dao.DaoMaster;
import com.vegantravels.dao.DaoSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.dao.async.AsyncOperationListener;
import de.greenrobot.dao.async.AsyncSession;

/**
 * @author Octa
 */
public class DatabaseManager implements IDatabaseManager, AsyncOperationListener {

    /**
     * Class tag. Used for debug.
     */
    private static final String TAG = DatabaseManager.class.getCanonicalName();
    /**
     * Instance of DatabaseManager
     */
    private static DatabaseManager instance;
    /**
     * The Android Activity reference for access to DatabaseManager.
     */
    private Context context;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase database;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private AsyncSession asyncSession;
    private List<AsyncOperation> completedOperations;

    /**
     * Constructs a new DatabaseManager with the specified arguments.
     *
     * @param context The Android {@link android.content.Context}.
     */
    public DatabaseManager(final Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(this.context, "sample-database", null);
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
    }

    /**
     * @param context The Android {@link android.content.Context}.
     * @return this.instance
     */
    public static DatabaseManager getInstance(Context context) {

        if (instance == null) {
            instance = new DatabaseManager(context);
        }

        return instance;
    }

    @Override
    public void onAsyncOperationCompleted(AsyncOperation operation) {
        completedOperations.add(operation);
    }

    private void assertWaitForCompletion1Sec() {
        asyncSession.waitForCompletion(1000);
        asyncSession.isCompleted();
    }

    /**
     * Query for readable DB
     */
    public void openReadableDb() throws SQLiteException {
        database = mHelper.getReadableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener(this);
    }

    /**
     * Query for writable DB
     */
    public void openWritableDb() throws SQLiteException {
        database = mHelper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener(this);
    }

    @Override
    public void closeDbConnections() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
        if (database != null && database.isOpen()) {
            database.close();
        }
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
        if (instance != null) {
            instance = null;
        }
    }

    @Override
    public synchronized void dropDatabase() {
        try {
            openWritableDb();
            DaoMaster.dropAllTables(database, true); // drops all tables
            mHelper.onCreate(database);              // creates the tables
//            asyncSession.deleteAll(User.class);    // clear all elements from a table
//            asyncSession.deleteAll(Task.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***************************************
     * USER Table Operation
     ************************************/
    @Override
    public synchronized Criuzes insertCruises(Criuzes cruises) {
        try {
            if (cruises != null) {
                openWritableDb();
                CriuzesDao userDao = daoSession.getCriuzesDao();
                userDao.insert(cruises);
                daoSession.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cruises;
    }

    @Override
    public synchronized ArrayList<Criuzes> listCriuzes() {
        List<Criuzes> users = null;
        try {
            openReadableDb();
            CriuzesDao userDao = daoSession.getCriuzesDao();
            users = userDao.loadAll();

            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (users != null) {
            return new ArrayList<>(users);
        }
        return null;
    }


    @Override
    public synchronized Long updateCriuze(Criuzes criuze) {
        Long userKey = null;
        try {
            if (criuze != null) {
                openWritableDb();
                daoSession.update(criuze);
               /* Log.d(TAG, "Updated user: " + Criuze.getName() + " from the schema.");
                userKey = criuze.getKey();*/
                daoSession.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return userKey;
    }

    @Override
    public synchronized Criuzes updateUsers(Criuzes criuze) {

        try {
            if (criuze != null) {
                openWritableDb();
                daoSession.update(criuze);
//                Log.d(TAG, "Updated user: " + Criuze.g() + " from the schema.");
                daoSession.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return criuze;
    }

}
