package com.vegantravels.manager;


import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.dao.Criuzes;
import com.vegantravels.dao.Criuzes_TMP;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dao.Guests;
import com.vegantravels.dao.Guests_TMP;

import java.util.ArrayList;
import java.util.List;

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

    Criuzes_TMP getCruiseTempById(long id);

    ///***** temporary guest methods *****///
    Guests_TMP insertGuestTemporary(Guests_TMP guests_tmp);

    Guests_TMP getGuestTempById(long id);

    ArrayList<Guests_TMP> listGuestTemporary();

    ArrayList<Guests_TMP> listGuestByUniqueId(long cruiseUniqueId);

    Long updateGuestTemporary(Guests_TMP guests_tmp);

    ///***** GUEST METHOD *****///
    Guests insertGuest(Guests guests);

    ArrayList<Guests> guestList();

    Long updateGuest(Guests guests);

    List<Guests_TMP> getSearchByNameCabin(String name, String cabin);

    List<Guests_TMP> getSearchByName(String name);

    List<Guests_TMP> getSearchByCabin(String CabinNum);

    Guests_TMP guestTempFromCabin(String vtId, long cruiseId);
//    ArrayList<Criuzes_TMP> tempCruiseList();

    ///***** CABIN TABLE METHOD **********//////
    Cabins_TMP insertCabinTemp(Cabins_TMP cabins_tmp);

    ArrayList<Cabins_TMP> cabinTempList();

    Cabins_TMP hasGuestExcursion(String guestVT_id);

    Long updateCabinTemp(Cabins_TMP cabins_tmp);

    ///***** EXCURSION TABLE METHOD **********//////
    Excursions_TMP insertExcursionTemp(Excursions_TMP excursions_tmp);

    ArrayList<Excursions_TMP> excursionTempList();

    ArrayList<Excursions_TMP> excursionTempListByCruiseUniqueId(long cruiseUniqueId);


    Excursions_TMP getExcursionById(long id);

    Long updateExcursionTemp(Excursions_TMP excursions_tmp);
    //excurtion List by Excursion Unique Id
    ArrayList<Excursions_TMP> excursionTempListByExcursionId(long excursionId);
    Excursions_TMP getExcursionByExcursionUniqueId(long excursionId);

    boolean isDeleteCruiseTemp(long cruise_uniqueId);
    boolean isDeleteGuestTemp(long cruise_uniqueId);
    boolean isDeleteExcursionTemp(long cruise_uniqueId);
    boolean isDeleteCabinTemp(long cruise_uniqueId);

    boolean isDeleteGuestCabinTemp(String Vtid, long cruise_uniqueId);
    boolean isDeleteSingleGuestTemp(long cruise_uniqueId, String VTId);


}
