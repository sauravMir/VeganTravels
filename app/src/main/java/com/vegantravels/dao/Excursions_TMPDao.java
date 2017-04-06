package com.vegantravels.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.vegantravels.dao.Excursions_TMP;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "EXCURSIONS__TMP".
*/
public class Excursions_TMPDao extends AbstractDao<Excursions_TMP, Long> {

    public static final String TABLENAME = "EXCURSIONS__TMP";

    /**
     * Properties of entity Excursions_TMP.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CruzeId = new Property(1, int.class, "cruzeId", false, "CRUZE_ID");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property From = new Property(3, String.class, "from", false, "FROM");
        public final static Property To = new Property(4, String.class, "to", false, "TO");
        public final static Property Time = new Property(5, String.class, "time", false, "TIME");
        public final static Property Price = new Property(6, String.class, "price", false, "PRICE");
        public final static Property MaxNumberOfGuest = new Property(7, int.class, "maxNumberOfGuest", false, "MAX_NUMBER_OF_GUEST");
        public final static Property ExcursionUniqueId = new Property(8, long.class, "ExcursionUniqueId", false, "EXCURSION_UNIQUE_ID");
        public final static Property CreatedAt = new Property(9, long.class, "createdAt", false, "CREATED_AT");
        public final static Property UpdatedAt = new Property(10, long.class, "updatedAt", false, "UPDATED_AT");
    };


    public Excursions_TMPDao(DaoConfig config) {
        super(config);
    }
    
    public Excursions_TMPDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"EXCURSIONS__TMP\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CRUZE_ID\" INTEGER NOT NULL ," + // 1: cruzeId
                "\"TITLE\" TEXT NOT NULL ," + // 2: title
                "\"FROM\" TEXT NOT NULL ," + // 3: from
                "\"TO\" TEXT," + // 4: to
                "\"TIME\" TEXT NOT NULL ," + // 5: time
                "\"PRICE\" TEXT NOT NULL ," + // 6: price
                "\"MAX_NUMBER_OF_GUEST\" INTEGER NOT NULL ," + // 7: maxNumberOfGuest
                "\"EXCURSION_UNIQUE_ID\" INTEGER NOT NULL ," + // 8: ExcursionUniqueId
                "\"CREATED_AT\" INTEGER NOT NULL ," + // 9: createdAt
                "\"UPDATED_AT\" INTEGER NOT NULL );"); // 10: updatedAt
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EXCURSIONS__TMP\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Excursions_TMP entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCruzeId());
        stmt.bindString(3, entity.getTitle());
        stmt.bindString(4, entity.getFrom());
 
        String to = entity.getTo();
        if (to != null) {
            stmt.bindString(5, to);
        }
        stmt.bindString(6, entity.getTime());
        stmt.bindString(7, entity.getPrice());
        stmt.bindLong(8, entity.getMaxNumberOfGuest());
        stmt.bindLong(9, entity.getExcursionUniqueId());
        stmt.bindLong(10, entity.getCreatedAt());
        stmt.bindLong(11, entity.getUpdatedAt());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Excursions_TMP readEntity(Cursor cursor, int offset) {
        Excursions_TMP entity = new Excursions_TMP( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // cruzeId
            cursor.getString(offset + 2), // title
            cursor.getString(offset + 3), // from
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // to
            cursor.getString(offset + 5), // time
            cursor.getString(offset + 6), // price
            cursor.getInt(offset + 7), // maxNumberOfGuest
            cursor.getLong(offset + 8), // ExcursionUniqueId
            cursor.getLong(offset + 9), // createdAt
            cursor.getLong(offset + 10) // updatedAt
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Excursions_TMP entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCruzeId(cursor.getInt(offset + 1));
        entity.setTitle(cursor.getString(offset + 2));
        entity.setFrom(cursor.getString(offset + 3));
        entity.setTo(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTime(cursor.getString(offset + 5));
        entity.setPrice(cursor.getString(offset + 6));
        entity.setMaxNumberOfGuest(cursor.getInt(offset + 7));
        entity.setExcursionUniqueId(cursor.getLong(offset + 8));
        entity.setCreatedAt(cursor.getLong(offset + 9));
        entity.setUpdatedAt(cursor.getLong(offset + 10));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Excursions_TMP entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Excursions_TMP entity) {
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
