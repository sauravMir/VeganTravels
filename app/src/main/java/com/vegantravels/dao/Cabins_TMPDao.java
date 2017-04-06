package com.vegantravels.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.vegantravels.dao.Cabins_TMP;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CABINS__TMP".
*/
public class Cabins_TMPDao extends AbstractDao<Cabins_TMP, Long> {

    public static final String TABLENAME = "CABINS__TMP";

    /**
     * Properties of entity Cabins_TMP.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Occupancy = new Property(1, int.class, "occupancy", false, "OCCUPANCY");
        public final static Property CabinNumber = new Property(2, int.class, "cabinNumber", false, "CABIN_NUMBER");
        public final static Property NumberOfGuest = new Property(3, int.class, "numberOfGuest", false, "NUMBER_OF_GUEST");
        public final static Property GuestVT_Id = new Property(4, String.class, "guestVT_Id", false, "GUEST_VT__ID");
        public final static Property PaymentStatus = new Property(5, Integer.class, "paymentStatus", false, "PAYMENT_STATUS");
        public final static Property Excursion = new Property(6, Long.class, "excursion", false, "EXCURSION");
        public final static Property DeviceDate = new Property(7, String.class, "deviceDate", false, "DEVICE_DATE");
        public final static Property CabinUniqueId = new Property(8, long.class, "CabinUniqueId", false, "CABIN_UNIQUE_ID");
        public final static Property CreatedAt = new Property(9, Long.class, "createdAt", false, "CREATED_AT");
        public final static Property UpdatedAt = new Property(10, Long.class, "updatedAt", false, "UPDATED_AT");
    };


    public Cabins_TMPDao(DaoConfig config) {
        super(config);
    }
    
    public Cabins_TMPDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CABINS__TMP\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"OCCUPANCY\" INTEGER NOT NULL ," + // 1: occupancy
                "\"CABIN_NUMBER\" INTEGER NOT NULL ," + // 2: cabinNumber
                "\"NUMBER_OF_GUEST\" INTEGER NOT NULL ," + // 3: numberOfGuest
                "\"GUEST_VT__ID\" TEXT NOT NULL ," + // 4: guestVT_Id
                "\"PAYMENT_STATUS\" INTEGER," + // 5: paymentStatus
                "\"EXCURSION\" INTEGER," + // 6: excursion
                "\"DEVICE_DATE\" TEXT NOT NULL ," + // 7: deviceDate
                "\"CABIN_UNIQUE_ID\" INTEGER NOT NULL ," + // 8: CabinUniqueId
                "\"CREATED_AT\" INTEGER," + // 9: createdAt
                "\"UPDATED_AT\" INTEGER);"); // 10: updatedAt
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CABINS__TMP\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Cabins_TMP entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getOccupancy());
        stmt.bindLong(3, entity.getCabinNumber());
        stmt.bindLong(4, entity.getNumberOfGuest());
        stmt.bindString(5, entity.getGuestVT_Id());
 
        Integer paymentStatus = entity.getPaymentStatus();
        if (paymentStatus != null) {
            stmt.bindLong(6, paymentStatus);
        }
 
        Long excursion = entity.getExcursion();
        if (excursion != null) {
            stmt.bindLong(7, excursion);
        }
        stmt.bindString(8, entity.getDeviceDate());
        stmt.bindLong(9, entity.getCabinUniqueId());
 
        Long createdAt = entity.getCreatedAt();
        if (createdAt != null) {
            stmt.bindLong(10, createdAt);
        }
 
        Long updatedAt = entity.getUpdatedAt();
        if (updatedAt != null) {
            stmt.bindLong(11, updatedAt);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Cabins_TMP readEntity(Cursor cursor, int offset) {
        Cabins_TMP entity = new Cabins_TMP( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // occupancy
            cursor.getInt(offset + 2), // cabinNumber
            cursor.getInt(offset + 3), // numberOfGuest
            cursor.getString(offset + 4), // guestVT_Id
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // paymentStatus
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6), // excursion
            cursor.getString(offset + 7), // deviceDate
            cursor.getLong(offset + 8), // CabinUniqueId
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // createdAt
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10) // updatedAt
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Cabins_TMP entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOccupancy(cursor.getInt(offset + 1));
        entity.setCabinNumber(cursor.getInt(offset + 2));
        entity.setNumberOfGuest(cursor.getInt(offset + 3));
        entity.setGuestVT_Id(cursor.getString(offset + 4));
        entity.setPaymentStatus(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setExcursion(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.setDeviceDate(cursor.getString(offset + 7));
        entity.setCabinUniqueId(cursor.getLong(offset + 8));
        entity.setCreatedAt(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setUpdatedAt(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Cabins_TMP entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Cabins_TMP entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
