package com.vegantravels.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.vegantravels.dao.Cabins;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CABINS".
*/
public class CabinsDao extends AbstractDao<Cabins, Long> {

    public static final String TABLENAME = "CABINS";

    /**
     * Properties of entity Cabins.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CabinNumber = new Property(1, int.class, "cabinNumber", false, "CABIN_NUMBER");
        public final static Property NumberOfGuest = new Property(2, int.class, "numberOfGuest", false, "NUMBER_OF_GUEST");
        public final static Property GuestVT_Id = new Property(3, int.class, "guestVT_Id", false, "GUEST_VT__ID");
        public final static Property PaymentStatus = new Property(4, int.class, "paymentStatus", false, "PAYMENT_STATUS");
        public final static Property DeviceDate = new Property(5, String.class, "deviceDate", false, "DEVICE_DATE");
        public final static Property CabinUniqueId = new Property(6, long.class, "CabinUniqueId", false, "CABIN_UNIQUE_ID");
        public final static Property CreatedAt = new Property(7, Long.class, "createdAt", false, "CREATED_AT");
        public final static Property UpdatedAt = new Property(8, Long.class, "updatedAt", false, "UPDATED_AT");
    };


    public CabinsDao(DaoConfig config) {
        super(config);
    }
    
    public CabinsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CABINS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CABIN_NUMBER\" INTEGER NOT NULL ," + // 1: cabinNumber
                "\"NUMBER_OF_GUEST\" INTEGER NOT NULL ," + // 2: numberOfGuest
                "\"GUEST_VT__ID\" INTEGER NOT NULL ," + // 3: guestVT_Id
                "\"PAYMENT_STATUS\" INTEGER NOT NULL ," + // 4: paymentStatus
                "\"DEVICE_DATE\" TEXT NOT NULL ," + // 5: deviceDate
                "\"CABIN_UNIQUE_ID\" INTEGER NOT NULL ," + // 6: CabinUniqueId
                "\"CREATED_AT\" INTEGER," + // 7: createdAt
                "\"UPDATED_AT\" INTEGER);"); // 8: updatedAt
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CABINS\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Cabins entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCabinNumber());
        stmt.bindLong(3, entity.getNumberOfGuest());
        stmt.bindLong(4, entity.getGuestVT_Id());
        stmt.bindLong(5, entity.getPaymentStatus());
        stmt.bindString(6, entity.getDeviceDate());
        stmt.bindLong(7, entity.getCabinUniqueId());
 
        Long createdAt = entity.getCreatedAt();
        if (createdAt != null) {
            stmt.bindLong(8, createdAt);
        }
 
        Long updatedAt = entity.getUpdatedAt();
        if (updatedAt != null) {
            stmt.bindLong(9, updatedAt);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Cabins readEntity(Cursor cursor, int offset) {
        Cabins entity = new Cabins( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // cabinNumber
            cursor.getInt(offset + 2), // numberOfGuest
            cursor.getInt(offset + 3), // guestVT_Id
            cursor.getInt(offset + 4), // paymentStatus
            cursor.getString(offset + 5), // deviceDate
            cursor.getLong(offset + 6), // CabinUniqueId
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7), // createdAt
            cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8) // updatedAt
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Cabins entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCabinNumber(cursor.getInt(offset + 1));
        entity.setNumberOfGuest(cursor.getInt(offset + 2));
        entity.setGuestVT_Id(cursor.getInt(offset + 3));
        entity.setPaymentStatus(cursor.getInt(offset + 4));
        entity.setDeviceDate(cursor.getString(offset + 5));
        entity.setCabinUniqueId(cursor.getLong(offset + 6));
        entity.setCreatedAt(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
        entity.setUpdatedAt(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Cabins entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Cabins entity) {
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
