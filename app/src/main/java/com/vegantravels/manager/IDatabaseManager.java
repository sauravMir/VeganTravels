package com.vegantravels.manager;


import com.vegantravels.dao.Criuzes;
import com.vegantravels.dao.Criuzes_TMP;
import com.vegantravels.dao.Guests;
import com.vegantravels.dao.Guests_TMP;

import java.util.ArrayList;

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
     * @param cruises be inserted
     */
    Criuzes insertCruises(Criuzes cruises);

    ArrayList<Criuzes> listCriuzes();

    Long updateCriuze(Criuzes criuze);

    Criuzes updateCruize(Criuzes criuze);

    /// temporary cruize methods
    Criuzes_TMP insertCriuzeTemporary(Criuzes_TMP criuzeTemporary);

    ArrayList<Criuzes_TMP> listCriuzeTemporary();

    Long updateCriuzeTemporary(Criuzes_TMP criuzeTemporary);


    ///***** temporary guest methods *****///
    Guests_TMP insertGuestTemporary(Guests_TMP guests_tmp);

    ArrayList<Guests_TMP> listGuestTemporary();

    Long updateGuestTemporary(Guests_TMP guests_tmp);

    ///***** GUEST METHOD *****///
    Guests insertGuest(Guests guests);

    ArrayList<Guests> guestList();

    Long updateGuest(Guests guests);

}
