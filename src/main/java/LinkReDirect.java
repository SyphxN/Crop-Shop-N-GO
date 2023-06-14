package main.java;

import java.net.URI;
import java.awt.Desktop;

public class LinkReDirect {
    public static void main(String args[]){
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI("https://poshmark.com/listing/nike-sweater-great-condition-63e5a302bd66cdb8d06abc43");
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}