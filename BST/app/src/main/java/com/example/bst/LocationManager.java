package com.example.bst;

import android.os.Bundle;

import androidx.annotation.Nullable;

public interface LocationManager {
    void onStatusChanged(String provider, int status, Bundle extras);

    void onProviderEnabled(String provider);

    void onProviderDisabled(String provider);

    void onConnected(@Nullable Bundle bundle);

    void onConnectionSuspended(int i);

    void requestLocationUpdates();
}
