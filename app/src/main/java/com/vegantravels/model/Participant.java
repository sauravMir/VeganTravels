package com.vegantravels.model;

import com.google.gson.annotations.SerializedName;
import com.vegantravels.utilities.StaticAccess;

/**
 * Created by RAFI on 4/4/2017.
 */

public class Participant {

    @SerializedName(StaticAccess.KEY_PARTICIPANT_GUEST_ID)
    String participantId;

    @SerializedName(StaticAccess.KEY_PARTICIPANT_GUEST_NAME)
    String participantName;

   /* public Participant(String participantId, String participantName) {
        this.participantId = participantId;
        this.participantName = participantName;
    }*/

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }



/*
    @SerializedName(StaticAccess.KEY_PARTICIPANT_GUEST_FIRST_NAME)
    String participantFirstName;

    @SerializedName(StaticAccess.KEY_PARTICIPANT_GUEST_LAST_NAME)
    String participantLastName;
*/

}
