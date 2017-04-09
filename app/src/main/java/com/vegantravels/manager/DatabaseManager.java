package com.vegantravels.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.dao.Cabins_TMPDao;
import com.vegantravels.dao.Criuzes;
import com.vegantravels.dao.CriuzesDao;
import com.vegantravels.dao.Criuzes_TMP;
import com.vegantravels.dao.Criuzes_TMPDao;
import com.vegantravels.dao.DaoMaster;
import com.vegantravels.dao.DaoSession;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dao.Excursions_TMPDao;
import com.vegantravels.dao.Guests;
import com.vegantravels.dao.GuestsDao;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dao.Guests_TMPDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.dao.async.AsyncOperationListener;
import de.greenrobot.dao.async.AsyncSession;
import de.greenrobot.dao.query.QueryBuilder;

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
     * @param context The Android {@link Context}.
     */
    public DatabaseManager(final Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(this.context, "sample-database", null);
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
    }

    /**
     * @param context The Android {@link Context}.
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
     * CRUIZE Table Operation
     ************************************/
    @Override
    public synchronized Criuzes insertCruises(Criuzes cruises) {
        try {
            if (cruises != null) {
                openWritableDb();
                CriuzesDao cruizeDao = daoSession.getCriuzesDao();
                cruizeDao.insert(cruises);
                daoSession.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cruises;
    }

    @Override
    public synchronized ArrayList<Criuzes> listCriuzes() {
        List<Criuzes> listCriuzes = null;
        try {
            openReadableDb();
            CriuzesDao cruizeDao = daoSession.getCriuzesDao();
            listCriuzes = cruizeDao.loadAll();

            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listCriuzes != null) {
            return new ArrayList<>(listCriuzes);
        }
        return null;
    }


    @Override
    public synchronized Long updateCriuze(Criuzes criuze) {
        Long criuzeKey = null;
        try {
            if (criuze != null) {
                openWritableDb();
                daoSession.update(criuze);
//                Log.d(TAG, "Updated user: " + Criuze.getName() + " from the schema.");
                criuzeKey = criuze.getId();
                daoSession.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return criuzeKey;
    }

    /***************************************
     * Cruize Temporary Table Operation
     ************************************/
    @Override
    public Criuzes_TMP insertCriuzeTemporary(Criuzes_TMP criuzeTemporary) {
        try {
            if (criuzeTemporary != null) {
                openWritableDb();
                Criuzes_TMPDao cruzeTempDao = daoSession.getCriuzes_TMPDao();
                long id = cruzeTempDao.insert(criuzeTemporary);
                Log.d("insert", "Cruise Is" + String.valueOf(id));
                daoSession.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return criuzeTemporary;
    }

    @Override
    public ArrayList<Criuzes_TMP> listCriuzeTemporary() {
        List<Criuzes_TMP> cruizTempoList = null;
        try {
            openReadableDb();
            Criuzes_TMPDao cruzeTempDao = daoSession.getCriuzes_TMPDao();
            cruizTempoList = cruzeTempDao.loadAll();

            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cruizTempoList != null) {
            return new ArrayList<>(cruizTempoList);
        }
        return null;
    }

    @Override
    public Long updateCriuzeTemporary(Criuzes_TMP criuzeTemporary) {
        Long criuzeTemporaryKey = null;
        try {
            if (criuzeTemporary != null) {
                openWritableDb();
                daoSession.update(criuzeTemporary);
                Log.d(TAG, "Updated Cruize: " + criuzeTemporary.getName() + " from the schema.");
                criuzeTemporaryKey = criuzeTemporary.getId();
                daoSession.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return criuzeTemporaryKey;

    }

    @Override
    public Criuzes_TMP getCruiseTempById(long id) {
        Criuzes_TMP criuzes_TMP = null;
        try {
            openWritableDb();
            Criuzes_TMPDao criuzes_tmpDao = daoSession.getCriuzes_TMPDao();
            criuzes_TMP = criuzes_tmpDao.load(id);
            daoSession.clear();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return criuzes_TMP;
    }


    ///*****GUEST TEMPORARY CRUID OPERATION METHOD ************////
    @Override
    public Guests_TMP insertGuestTemporary(Guests_TMP guests_tmp) {
        try {
            if (guests_tmp != null) {
                openWritableDb();
                Guests_TMPDao guests_tmpDao = daoSession.getGuests_TMPDao();
                long id = guests_tmpDao.insert(guests_tmp);
                Log.d("insert", "guest Id" + String.valueOf(id));
                daoSession.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return guests_tmp;
    }

    @Override
    public Guests_TMP getGuestTempById(long id) {
        Guests_TMP guests_TMP = null;
        try {
            openWritableDb();
            Guests_TMPDao guest_tmpDao = daoSession.getGuests_TMPDao();
            guests_TMP = guest_tmpDao.load(id);
            daoSession.clear();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return guests_TMP;
    }


    @Override
    public ArrayList<Guests_TMP> listGuestTemporary() {
        List<Guests_TMP> guests_tmpList = null;
        try {
            openReadableDb();
            Guests_TMPDao guests_tmpDao = daoSession.getGuests_TMPDao();
            guests_tmpList = guests_tmpDao.loadAll();

            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (guests_tmpList != null) {
            return new ArrayList<>(guests_tmpList);
        }
        return null;
    }

    @Override
    public ArrayList<Guests_TMP> listGuestByUniqueId(long cruiseUniqueId) {
        List<Guests_TMP> guests_tmpList = null;
        try {
            openReadableDb();
            Guests_TMPDao guests_tmpDao = daoSession.getGuests_TMPDao();

            QueryBuilder<Guests_TMP> queryBuilder = guests_tmpDao.queryBuilder().where(Guests_TMPDao.Properties.GuestUniqueId.eq(cruiseUniqueId));
            guests_tmpList = queryBuilder.list();
//            guests_tmpList = guests_tmpDao.loadAll();

            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (guests_tmpList != null) {
            return new ArrayList<>(guests_tmpList);
        }
        return null;
    }

    @Override
    public Long updateGuestTemporary(Guests_TMP guests_tmp) {
        Long guestTemporaryKey = null;
        try {
            if (guests_tmp != null) {
                openWritableDb();
                daoSession.update(guests_tmp);
//                Log.d(TAG, "Updated user: " + Criuze.getName() + " from the schema.");
                guestTemporaryKey = guests_tmp.getId();
                daoSession.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return guestTemporaryKey;
    }

    @Override
    public Guests insertGuest(Guests guests) {
        return null;
    }

    @Override
    public ArrayList<Guests> guestList() {
        return null;
    }

    @Override
    public Long updateGuest(Guests guests) {
        return null;
    }

    @Override
    public synchronized Criuzes updateCruize(Criuzes criuze) {

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

    @Override
    public List<Guests_TMP> getSearchByNameCabin(String name) {
        List<Guests_TMP> guestsList = null;
        try {
            openReadableDb();
            Guests_TMPDao guestTempDao = daoSession.getGuests_TMPDao();
            QueryBuilder<Guests_TMP> queryBuilder = guestTempDao.queryBuilder().whereOr(Guests_TMPDao.Properties.Fname.like("%" + name + "%"),
                    Guests_TMPDao.Properties.LName.like("%" + name + "%"), Guests_TMPDao.Properties.CabinNumber.like("%" + name + "%")).orderAsc(Guests_TMPDao.Properties.CabinNumber);
            guestsList = queryBuilder.list();
            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (guestsList != null) {
            return guestsList = new ArrayList<>(guestsList);
        }
        return guestsList;
    }

    /////************** CABIN CRUID OPERATION METHOD ***********************////
    @Override
    public Cabins_TMP insertCabinTemp(Cabins_TMP cabins_tmp) {
        try {
            if (cabins_tmp != null) {
                openWritableDb();
                Cabins_TMPDao cabins_tmpDao = daoSession.getCabins_TMPDao();
                long id = cabins_tmpDao.insert(cabins_tmp);
                Log.d("insert", "Cabin Id" + String.valueOf(id));
                daoSession.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cabins_tmp;
    }

    @Override
    public ArrayList<Cabins_TMP> cabinTempList() {
        List<Cabins_TMP> cabins_tmpList = null;
        try {
            openReadableDb();
            Cabins_TMPDao cabins_tmpDao = daoSession.getCabins_TMPDao();
            cabins_tmpList = cabins_tmpDao.loadAll();

            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cabins_tmpList != null) {
            return new ArrayList<>(cabins_tmpList);
        }
        return null;
    }

    @Override
    public Long updateCabinTemp(Cabins_TMP cabins_tmp) {
        Long cabinTempKay = null;
        try {
            if (cabins_tmp != null) {
                openWritableDb();
                daoSession.update(cabins_tmp);
//                Log.d(TAG, "Updated user: " + Criuze.getName() + " from the schema.");
                cabinTempKay = cabins_tmp.getId();
                daoSession.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return cabinTempKay;
    }

    ////***************     EXCURSION CRUD OPERATION METHODS        ************************//
    @Override
    public Excursions_TMP insertExcursionTemp(Excursions_TMP excursions_tmp) {
        try {
            if (excursions_tmp != null) {
                openWritableDb();
                Excursions_TMPDao excursions_tmpDao = daoSession.getExcursions_TMPDao();
                long id = excursions_tmpDao.insert(excursions_tmp);
                Log.d("insert", "Excursion Id" + String.valueOf(id));
                daoSession.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excursions_tmp;
    }

    @Override
    public ArrayList<Excursions_TMP> excursionTempList() {
        List<Excursions_TMP> excursions_tmpList = null;
        try {
            openReadableDb();
            Excursions_TMPDao excursionsTmpDao = daoSession.getExcursions_TMPDao();
            excursions_tmpList = excursionsTmpDao.loadAll();

            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (excursions_tmpList != null) {
            return new ArrayList<>(excursions_tmpList);
        }
        return null;
    }

    @Override
    public ArrayList<Excursions_TMP> excursionTempListByCruiseUniqueId(long CruiseUniqueId) {
        List<Excursions_TMP> excursions_tmpList = null;
        try {
            openReadableDb();
            Excursions_TMPDao excursionsTmpDao = daoSession.getExcursions_TMPDao();
            QueryBuilder<Excursions_TMP> queryBuilder = excursionsTmpDao.queryBuilder().where(Excursions_TMPDao.Properties.ExcursionUniqueId.eq(CruiseUniqueId)).orderAsc(Excursions_TMPDao.Properties.Id);
            excursions_tmpList = queryBuilder.list();
            daoSession.clear();

            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (excursions_tmpList != null) {
            return new ArrayList<>(excursions_tmpList);
        }
        return null;
    }

    @Override
    public Long updateExcursionTemp(Excursions_TMP excursions_tmp) {
        Long excursionTempKay = null;
        try {
            if (excursions_tmp != null) {
                openWritableDb();
                daoSession.update(excursions_tmp);
//                Log.d(TAG, "Updated user: " + Criuze.getName() + " from the schema.");
                excursionTempKay = excursions_tmp.getId();
                daoSession.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return excursionTempKay;
    }
}
