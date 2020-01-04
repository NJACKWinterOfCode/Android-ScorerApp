package com.example.anmol.courtcounter;

public class menuItem {
    private int mImageResourceID;
    private int title;

    public menuItem (int ImageResourceID, int Title)
    {
        mImageResourceID = ImageResourceID;
        title = Title;

    }

    public int getImageResourceID()
    {
        return mImageResourceID;
    }
    public int getTitle()
    {
        return title;
    }
}
