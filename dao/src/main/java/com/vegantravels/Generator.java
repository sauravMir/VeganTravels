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
        Entity user = addUser(schema);
    }

    /**
     * Create user's Properties
     *
     * @return DBUser entity
     */
    private static Entity addUser(Schema schema) {
        Entity user = schema.addEntity("User");
        user.addIdProperty().primaryKey().autoincrement();
        user.addStringProperty("name").notNull();
        user.addIntProperty("age").notNull();
        user.addStringProperty("password").notNull();
        user.addStringProperty("proPic").notNull();
        user.addIntProperty("point").notNull();
        user.addBooleanProperty("active").notNull();
        return user;
    }


}
