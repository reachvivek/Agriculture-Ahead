package com.vivek.agricultureahead.viewmodels;

import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.vivek.agricultureahead.Constants;


public class MapViewModel extends ViewModel {
    public SharedPreferences sharedPreferences;
    public String apiKey;
    public double mapLat = Double.valueOf(Constants.DEFAULT_LAT);
    public double mapLon = Double.valueOf(Constants.DEFAULT_LON);
    public int mapZoom = Constants.DEFAULT_ZOOM_LEVEL;
    public int tabPosition = 0;
}
