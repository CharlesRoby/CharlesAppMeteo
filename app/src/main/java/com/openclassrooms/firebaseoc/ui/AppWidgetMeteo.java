package com.openclassrooms.firebaseoc.ui;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.openclassrooms.firebaseoc.R;

import com.openclassrooms.firebaseoc.R;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidgetMeteo extends AppWidgetProvider {

    ImageView weatherIcon;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        CharSequence widgetText = context.getString(R.string.appwidget_text);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String temp = prefs.getString("TEMP", null);
        String city = prefs.getString("CITY", null);
        String state = prefs.getString("STATE", null);
        int image = prefs.getInt("IMAGE", 0);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_meteo);
        views.setImageViewResource(R.id.weatherIcon, image);
        views.setTextViewText(R.id.temperature, temp);
        views.setTextViewText(R.id.weatherCondition, city);
        views.setTextViewText(R.id.cityName, state);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}



