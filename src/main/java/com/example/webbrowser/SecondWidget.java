package com.example.webbrowser;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class SecondWidget extends AppWidgetProvider {

    private  static  final String SYNC_CLICKED = "WidgetImageClick";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        super.onUpdate(context,appWidgetManager,appWidgetIds);
        Random r = new Random();
        RemoteViews remoteViews;
        ComponentName componentName;

        remoteViews = new RemoteViews(context.getPackageName(),R.layout.second_widget);
        componentName= new ComponentName(context,SecondWidget.class);
        remoteViews.setImageViewResource(R.id.imageView,setImage(r.nextInt(2)));
        remoteViews.setOnClickPendingIntent(R.id.imageView,getPendingSelfIntent(context,SYNC_CLICKED));
        appWidgetManager.updateAppWidget(componentName,remoteViews);
    }


    @Override
    public void onReceive(Context context,Intent intent){
        super.onReceive(context,intent);
        if(SYNC_CLICKED.equals(intent.getAction())){
            Random i = new Random();
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews remoteViews;
            ComponentName componentName;
            remoteViews = new RemoteViews(context.getPackageName(),R.layout.second_widget);
            componentName= new ComponentName(context,SecondWidget.class);
            remoteViews.setImageViewResource(R.id.imageView,setImage(i.nextInt(2)));
            appWidgetManager.updateAppWidget(componentName,remoteViews);

        }
    }

    public int setImage(int num){
        int image=0;
        switch (num){
            case 0:
                image = R.drawable.r5;
                break;
            case 1:
                image = R.drawable.a5;
                break;
        }
        return image;
    }

    protected PendingIntent getPendingSelfIntent(Context context,String action){
        Intent intent= new Intent(context,getClass());
        intent.setAction(action);
        return  PendingIntent.getBroadcast(context,0,intent,0);
    }
}

