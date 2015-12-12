package com.app.pebkar.Tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Lyyn on 11-12-15.
 */
public abstract class NetTools {
    /**
     * Vérifie si l'application dispose d'une connexion à internet
     * http://developer.android.com/training/monitoring-device-state/connectivity-monitoring.html
     * @param context Contexte de l'application
     * @return true si connecté, false si pas de connexion
     */
    public static boolean checkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return (ni != null && ni.isConnectedOrConnecting());
    }
}
