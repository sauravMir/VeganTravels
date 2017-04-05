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


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

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
        addCriuzeTemporary(schema);
        addExcursion(schema);
        addExcursionTemporary(schema);
        addGuest(schema);
        addGuestTemporary(schema);
    }

    /**
     * Create user's Properties
     *
     * @return DBUser entity
     */
    private static Entity addCriuze(Schema schema) {
        Entity cruize = schema.addEntity("Criuze");
        cruize.addIdProperty().primaryKey().autoincrement();
        cruize.addStringProperty("cruizeName").notNull();
        cruize.addStringProperty("shipName").notNull();
        cruize.addDateProperty("dateFrom").notNull();
        cruize.addDateProperty("dateTo").notNull();
        cruize.addLongProperty("cruizeKey").notNull();
        return cruize;
    }

    private static Entity addCriuzeTemporary(Schema schema) {
        Entity cruize = schema.addEntity("CriuzeTemporary");
        cruize.addIdProperty().primaryKey().autoincrement();
        cruize.addStringProperty("cruizeName").notNull();
        cruize.addStringProperty("shipName").notNull();
        cruize.addDateProperty("dateFrom").notNull();
        cruize.addDateProperty("dateTo").notNull();
        cruize.addLongProperty("cruizeKey").notNull();
        return cruize;
    }

    private static Entity addExcursion(Schema schema) {
        Entity excursion = schema.addEntity("Excursion");
        excursion.addIdProperty().primaryKey().autoincrement();
        excursion.addDateProperty("dateFrom").notNull();
        excursion.addDateProperty("dateTo").notNull();
        excursion.addStringProperty("excursioneName").notNull();
        excursion.addLongProperty("pricePerPerson").notNull();
        excursion.addIntProperty("maxGuest").notNull();
        excursion.addLongProperty("cruizeKey").notNull();
        return excursion;
    }

    private static Entity addExcursionTemporary(Schema schema) {
        Entity excursion = schema.addEntity("ExcursionTemporary");
        excursion.addIdProperty().primaryKey().autoincrement();
        excursion.addDateProperty("dateFrom").notNull();
        excursion.addDateProperty("dateTo").notNull();
        excursion.addStringProperty("excursioneName").notNull();
        excursion.addLongProperty("pricePerPerson").notNull();
        excursion.addIntProperty("maxGuest").notNull();
        excursion.addLongProperty("cruizeKey").notNull();
        return excursion;
    }


    private static Entity addGuest(Schema schema) {
        Entity guest = schema.addEntity("Guest");
        guest.addIdProperty().primaryKey().autoincrement();
        guest.addStringProperty("guestName").notNull();
        guest.addStringProperty("cabin").notNull();
        guest.addLongProperty("guestKey").notNull();
        return guest;
    }

    private static Entity addGuestTemporary(Schema schema) {
        Entity guest = schema.addEntity("GuestTemporary");
        guest.addIdProperty().primaryKey().autoincrement();
        guest.addStringProperty("guestName").notNull();
        guest.addStringProperty("cabin").notNull();
        guest.addLongProperty("guestKey").notNull();
        return guest;
    }

}
