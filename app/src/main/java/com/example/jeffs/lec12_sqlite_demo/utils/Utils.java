package com.example.jeffs.lec12_sqlite_demo.utils;

import android.content.Context;
import android.support.compat.BuildConfig;

import com.facebook.stetho.Stetho;

/**
 * Created by jeffs on 3/1/2017.
 */

public class Utils {

    public static void setStethoWatch(Context c){

        if(BuildConfig.DEBUG){
            Stetho.initialize(
                    Stetho.newInitializerBuilder(c)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(c))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(c))
                    .build()
            );
        }
    }
}
