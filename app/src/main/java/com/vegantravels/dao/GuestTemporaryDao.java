package com.vegantravels.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.vegantravels.dao.GuestTemporary;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GUEST_TEMPORARY".
*/
public class GuestTemporaryDao extends AbstractDao<GuestTemporary, Long> {

    public static final String TABLENAME = "GUEST_TEMPORARY";

    /**
     * Properties of entity GuestTemporary.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property GuestName = new Property(1, String.class, "guestName", false, "GUEST_NAME");
        public final static Property Cabin = new Property(2, String.class, "cabin", false, "CABIN");
        public final static Property GuestKey = new Property(3, long.class, "guestKey", false, "GUEST_KEY");
    };


    public GuestTemporaryDao(DaoConfig config) {
        super(config);
    }
    
    public GuestTemporaryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GUEST_TEMPORARY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"GUEST_NAME\" TEXT NOT NULL ," + // 1: guestName
                "\"CABIN\" TEXT NOT NULL ," + // 2: cabin
                "\"GUEST_KEY\" INTEGER NOT NULL );"); // 3: guestKey
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GUEST_TEMPORARY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, GuestTemporary entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getGuestName());
        stmt.bindString(3, entity.getCabin());
        stmt.bindLong(4, entity.getGuestKey());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public GuestTemporary readEntity(Cursor cursor, int offset) {
        GuestTemporary entity = new GuestTemporary( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // guestName
            cursor.getString(offset + 2), // cabin
            cursor.getLong(offset + 3) // guestKey
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, GuestTemporary entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGuestName(cursor.getString(offset + 1));
        entity.setCabin(cursor.getString(offset + 2));
        entity.setGuestKey(cursor.getLong(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(GuestTemporary entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(GuestTemporary entity) {
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
