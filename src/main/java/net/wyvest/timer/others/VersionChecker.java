package net.wyvest.timer.others;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class VersionChecker {

    public String verJSON;
    public JsonObject verOBJ;
    private String version;
    private boolean emergency;
    private BufferedReader reader;

    {
        try {
            reader = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/wyvest/wyvest.net/master/timermod.json").openStream()));
            verJSON = reader.readLine();
            verOBJ = (JsonObject) new JsonParser().parse(verJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getVersion() {
        try {
            JsonObject obj = (JsonObject) new JsonParser().parse(verJSON);
            version = String.valueOf(obj.get("latest"));
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean getEmergencyStatus() {

        try {

            JsonObject obj = (JsonObject) new JsonParser().parse(verJSON);

            if (isNull(obj.get("emergency_update_" + Constants.VER)))
                emergency = false;
            else emergency = obj.get("emergency_update_" + Constants.VER).getAsBoolean();

            return emergency;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }


    private boolean isNull(Object o) {
        return o == null;
    }

}