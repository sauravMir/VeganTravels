package com.vegantravels;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

//1.To built the "Green Dao" library it need a custom builder.
//To create a library builder try
// -> Edit configuration from "Setup Run/Debug Configuration"
// -> "+"
// -> Application
// -> Put a name ex. "Dao"
// -> Mainclass - "Generator"
// -> Use classpath of Module
// -> "dao"
// -> Save changes.
//2.To add a new table, use "addTables" method.
//3.To add a new column just "addDateProperty" in the following table and build the dao library. It will automatically
// generate the model class for the table.

public class Generator {
    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final String OUT_DIR = PROJECT_DIR + "/app/src/main/java";

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.vegantravels.dao");
        addTables(schema);
        new DaoGenerator().generateAll(schema, OUT_DIR);
    }

    /**
     * Create tables and the relationships between them
     */
    private static void addTables(Schema schema) {
        /* entities */
        addCriuze(schema);
        addCriuzeTemp(schema);

        addCabin(schema);
        addCabinTemp(schema);

        addExcursion(schema);
        addExcursionTemp(schema);

        addGuest(schema);
        addGuestTemp(schema);
    }

    /**
     * Create user's Properties
     *
     * @return DBUser entity
     */
    private static Entity addCriuze(Schema schema) {
        Entity cruize = schema.addEntity(StaticAccess.Table_CRUIZE);
        cruize.addIdProperty().primaryKey().autoincrement();
        cruize.addStringProperty(StaticAccess.Column_name).notNull();
        cruize.addStringProperty(StaticAccess.Column_shipName).notNull();
        cruize.addStringProperty(StaticAccess.Column_from).notNull();
        cruize.addStringProperty(StaticAccess.Column_to);
        cruize.addLongProperty(StaticAccess.Cruize_unique_id).notNull();
        cruize.addLongProperty(StaticAccess.Column_createdAt);
        cruize.addLongProperty(StaticAccess.Column_updatedAt);
        return cruize;
    }

    private static Entity addCriuzeTemp(Schema schema) {
        Entity cruize = schema.addEntity(StaticAccess.Table_CRUIZE_TMP);
        cruize.addIdProperty().primaryKey().autoincrement();
        cruize.addStringProperty(StaticAccess.Column_name).notNull();
        cruize.addStringProperty(StaticAccess.Column_shipName).notNull();
        cruize.addStringProperty(StaticAccess.Column_from).notNull();
        cruize.addStringProperty(StaticAccess.Column_to);
        cruize.addLongProperty(StaticAccess.Cruize_unique_id).notNull();
        cruize.addLongProperty(StaticAccess.Column_createdAt);
        cruize.addLongProperty(StaticAccess.Column_updatedAt);
        return cruize;
    }


    private static Entity addCabin(Schema schema) {
        Entity cabin = schema.addEntity(StaticAccess.Table_CABIN);
        cabin.addIdProperty().primaryKey().autoincrement();
        cabin.addIntProperty(StaticAccess.Column_occupancy).notNull();
        cabin.addIntProperty(StaticAccess.Column_cabinNumber).notNull();
        cabin.addStringProperty(StaticAccess.Column_guest_VT_Id).notNull();
        cabin.addLongProperty(StaticAccess.Column_Cruizes).notNull();
        cabin.addIntProperty(StaticAccess.Column_paymentStatus);
        cabin.addLongProperty(StaticAccess.Column_EXCURSION);
        cabin.addLongProperty(StaticAccess.Cabin_unique_id).notNull();
        cabin.addLongProperty(StaticAccess.Column_createdAt);
        cabin.addLongProperty(StaticAccess.Column_updatedAt);
        return cabin;
    }

    private static Entity addCabinTemp(Schema schema) {
        Entity cabin = schema.addEntity(StaticAccess.Table_CABIN_TMP);
        cabin.addIdProperty().primaryKey().autoincrement();
        cabin.addIntProperty(StaticAccess.Column_occupancy).notNull();
        cabin.addIntProperty(StaticAccess.Column_cabinNumber).notNull();
        cabin.addStringProperty(StaticAccess.Column_guest_VT_Id).notNull();
        cabin.addLongProperty(StaticAccess.Column_Cruizes).notNull();
        cabin.addIntProperty(StaticAccess.Column_paymentStatus);
        cabin.addLongProperty(StaticAccess.Column_EXCURSION);
        cabin.addLongProperty(StaticAccess.Cabin_unique_id).notNull();
        cabin.addLongProperty(StaticAccess.Column_createdAt);
        cabin.addLongProperty(StaticAccess.Column_updatedAt);
        return cabin;
    }


    private static Entity addExcursion(Schema schema) {
        Entity excursion = schema.addEntity(StaticAccess.Table_EXCURSIONS);
        excursion.addIdProperty().primaryKey().autoincrement();
        excursion.addIntProperty(StaticAccess.Column_cruzeId).notNull();
        excursion.addStringProperty(StaticAccess.Column_title).notNull();
        excursion.addStringProperty(StaticAccess.Column_from).notNull();
        excursion.addStringProperty(StaticAccess.Column_to);
        excursion.addStringProperty(StaticAccess.Column_time).notNull();
        excursion.addStringProperty(StaticAccess.Column_price).notNull();
        excursion.addIntProperty(StaticAccess.Column_maxNumberOfGuest).notNull();
        excursion.addLongProperty(StaticAccess.Excursion_unique_id).notNull();
        excursion.addLongProperty(StaticAccess.Column_createdAt);
        excursion.addLongProperty(StaticAccess.Column_updatedAt);
        return excursion;
    }

    private static Entity addExcursionTemp(Schema schema) {
        Entity excursion = schema.addEntity(StaticAccess.Table_EXCURSIONS_TMP);
        excursion.addIdProperty().primaryKey().autoincrement();
        excursion.addIntProperty(StaticAccess.Column_cruzeId).notNull();
        excursion.addStringProperty(StaticAccess.Column_title).notNull();
        excursion.addStringProperty(StaticAccess.Column_from).notNull();
        excursion.addStringProperty(StaticAccess.Column_to);
        excursion.addStringProperty(StaticAccess.Column_time).notNull();
        excursion.addStringProperty(StaticAccess.Column_price).notNull();
        excursion.addIntProperty(StaticAccess.Column_maxNumberOfGuest).notNull();
        excursion.addLongProperty(StaticAccess.Excursion_unique_id).notNull();
        excursion.addLongProperty(StaticAccess.Column_createdAt);
        excursion.addLongProperty(StaticAccess.Column_updatedAt);
        return excursion;
    }


    private static Entity addGuest(Schema schema) {
        Entity guest = schema.addEntity(StaticAccess.Table_GUESTS);
        guest.addIdProperty().primaryKey().autoincrement();
        guest.addStringProperty(StaticAccess.Column_guest_VT_Id).notNull();
        guest.addStringProperty(StaticAccess.Column_fname).notNull();
        guest.addStringProperty(StaticAccess.Column_lName).notNull();
        guest.addLongProperty(StaticAccess.Guest_unique_id).notNull();
        guest.addIntProperty(StaticAccess.Column_cabinNumber).notNull();
        guest.addLongProperty(StaticAccess.Column_createdAt);
        guest.addLongProperty(StaticAccess.Column_updatedAt);
        return guest;
    }

    private static Entity addGuestTemp(Schema schema) {
        Entity guest = schema.addEntity(StaticAccess.Table_GUESTS_TMP);
        guest.addIdProperty().primaryKey().autoincrement();
        guest.addStringProperty(StaticAccess.Column_guest_VT_Id).notNull();
        guest.addStringProperty(StaticAccess.Column_fname).notNull();
        guest.addStringProperty(StaticAccess.Column_lName).notNull();
        guest.addIntProperty(StaticAccess.Column_cabinNumber).notNull();
        guest.addLongProperty(StaticAccess.Guest_unique_id).notNull();
        guest.addLongProperty(StaticAccess.Column_createdAt);
        guest.addLongProperty(StaticAccess.Column_updatedAt);
        return guest;
    }

}
