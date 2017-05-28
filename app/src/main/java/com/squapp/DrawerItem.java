package com.squapp;

/**
 * Created by JdRod on 27/05/2017.
 */

public class DrawerItem {
    String drawerText;
    int drawerIconID;

    public DrawerItem(String drawerText, int drawerIconID){
        this.drawerText = drawerText;
        this.drawerIconID = drawerIconID;
    }

    public String getDrawerText() {
        return drawerText;
    }

    public int getDrawerIconID() {
        return drawerIconID;
    }

    public void setDrawerText(String drawerText) {
        this.drawerText = drawerText;
    }

    public void setDrawerIconID(int drawerIconID) {
        this.drawerIconID = drawerIconID;
    }


}
