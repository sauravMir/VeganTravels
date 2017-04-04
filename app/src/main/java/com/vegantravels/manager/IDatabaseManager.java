package com.vegantravels.manager;


import com.vegantravels.dao.Criuze;

/**
 * Interface that provides methods for managing the database inside the Application.
 *
 * @author Octa
 */
public interface IDatabaseManager {

    /**
     * Closing available connections
     */
    void closeDbConnections();

    /**
     * Delete all tables and content from our database
     */
    void dropDatabase();

    /**
     * Insert a user into the DB
     *
     * @param  cruises be inserted
     */
    Criuze insertCruises(Criuze cruises);


}
