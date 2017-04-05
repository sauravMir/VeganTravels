package com.vegantravels.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.vegantravels.dao.Criuzes;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CRIUZES".
*/
public class CriuzesDao extends AbstractDao<Criuzes, Long> {

    public static final String TABLENAME = "CRIUZES";

    /**
     * Properties of entity Criuzes.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property ShipName = new Property(2, String.class, "shipName", false, "SHIP_NAME");
        public final static Property From = new Property(3, String.class, "from", false, "FROM");
        public final static Property To = new Property(4, String.class, "to", false, "TO");
        public final static Property CreatedAt = new Property(5, long.class, "createdAt", false, "CREATED_AT");
        public final static Property UpdatedAt = new Property(6, long.class, "updatedAt", false, "UPDATED_AT");
    };


    public CriuzesDao(DaoConfig config) {
        super(config);
    }
    
    public CriuzesDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CRIUZES\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"SHIP_NAME\" TEXT NOT NULL ," + // 2: shipName
                "\"FROM\" TEXT NOT NULL ," + // 3: from
                "\"TO\" TEXT NOT NULL ," + // 4: to
                "\"CREATED_AT\" INTEGER NOT NULL ," + // 5: createdAt
                "\"UPDATED_AT\" INTEGER NOT NULL );"); // 6: updatedAt
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CRIUZES\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Criuzes entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getShipName());
        stmt.bindString(4, entity.getFrom());
        stmt.bindString(5, entity.getTo());
        stmt.bindLong(6, entity.getCreatedAt());
        stmt.bindLong(7, entity.getUpdatedAt());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Criuzes readEntity(Cursor cursor, int offset) {
        Criuzes entity = new Criuzes( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.getString(offset + 2), // shipName
            cursor.getString(offset + 3), // from
            cursor.getString(offset + 4), // to
            cursor.getLong(offset + 5), // createdAt
            cursor.getLong(offset + 6) // updatedAt
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Criuzes entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setShipName(cursor.getString(offset + 2));
        entity.setFrom(cursor.getString(offset + 3));
        entity.setTo(cursor.getString(offset + 4));
        entity.setCreatedAt(cursor.getLong(offset + 5));
        entity.setUpdatedAt(cursor.getLong(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Criuzes entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Criuzes entity) {
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