package com.vegantravels.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.vegantravels.dao.Guests;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GUESTS".
*/
public class GuestsDao extends AbstractDao<Guests, Long> {

    public static final String TABLENAME = "GUESTS";

    /**
     * Properties of entity Guests.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property GuestId = new Property(1, int.class, "guestId", false, "GUEST_ID");
        public final static Property Fname = new Property(2, String.class, "fname", false, "FNAME");
        public final static Property LName = new Property(3, String.class, "lName", false, "L_NAME");
        public final static Property CreatedAt = new Property(4, long.class, "createdAt", false, "CREATED_AT");
        public final static Property UpdatedAt = new Property(5, long.class, "updatedAt", false, "UPDATED_AT");
    };


    public GuestsDao(DaoConfig config) {
        super(config);
    }
    
    public GuestsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GUESTS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"GUEST_ID\" INTEGER NOT NULL ," + // 1: guestId
                "\"FNAME\" TEXT NOT NULL ," + // 2: fname
                "\"L_NAME\" TEXT NOT NULL ," + // 3: lName
                "\"CREATED_AT\" INTEGER NOT NULL ," + // 4: createdAt
                "\"UPDATED_AT\" INTEGER NOT NULL );"); // 5: updatedAt
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GUESTS\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Guests entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getGuestId());
        stmt.bindString(3, entity.getFname());
        stmt.bindString(4, entity.getLName());
        stmt.bindLong(5, entity.getCreatedAt());
        stmt.bindLong(6, entity.getUpdatedAt());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Guests readEntity(Cursor cursor, int offset) {
        Guests entity = new Guests( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // guestId
            cursor.getString(offset + 2), // fname
            cursor.getString(offset + 3), // lName
            cursor.getLong(offset + 4), // createdAt
            cursor.getLong(offset + 5) // updatedAt
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Guests entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGuestId(cursor.getInt(offset + 1));
        entity.setFname(cursor.getString(offset + 2));
        entity.setLName(cursor.getString(offset + 3));
        entity.setCreatedAt(cursor.getLong(offset + 4));
        entity.setUpdatedAt(cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Guests entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Guests entity) {
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
