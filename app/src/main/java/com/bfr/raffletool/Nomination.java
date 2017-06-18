package com.bfr.raffletool;

import java.io.Serializable;

/**
 * Created by BFR on 6/17/17.
 */

public class Nomination implements Serializable {

    private String mDate;
    private String mNominee;
    private String mComment;

    public Nomination(String date, String nominee, String comment) {
        this.mDate = date;
        this.mNominee = nominee;
        this.mComment = comment;
    }

    public String getDate() {
        return mDate;
    }

    public String getNominee() {
        return mNominee;
    }

    public String getComment() {
        return mComment;
    }



}
