package com.android.systemui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ChargingCurrent {

    public static int cat() {
        BufferedReader reader = null;
        int currentNow = 0;

        try {
            reader = new BufferedReader(new FileReader("/sys/class/power_supply/battery/current_now"));
            String current = reader.readLine();
            if (current.charAt(0) == '-') {
                currentNow = Integer.parseInt(current)*-1/1000;
            } else {
                currentNow = Integer.parseInt(current)/1000;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return currentNow;
    }
}
