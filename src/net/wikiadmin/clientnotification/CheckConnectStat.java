package net.wikiadmin.clientnotification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CheckConnectStat, проверяет наличие соединения с интернетом.
 * @author aleksdem  (Aleksey Ubozhenko)
 */

class CheckConnectStat {

    /**
    * параметр, указывающий url.
    * @param urlUpdate url for check update
    */
    private final String urlUpdate = "http://pi.aleksdem.com/piwik.php?idsite=3&rec=1&action_name=clientnotification";

    final int answ() throws IOException {
        URL urlUpd = null;
        try {

                urlUpd = new URL(urlUpdate);
            } catch (MalformedURLException ex) {
                Logger.getLogger(CheckConnectStat.class.getName()).log(Level.SEVERE, null, ex);
            }
            String upd;
            try (BufferedReader in =
                    new BufferedReader(new InputStreamReader(urlUpd.openStream()))) {
                upd = in.readLine();
            }
            cnGui.setText("connected");
            return 0;
                }

        }
