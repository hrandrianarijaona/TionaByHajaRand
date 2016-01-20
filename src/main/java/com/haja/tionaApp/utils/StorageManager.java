package com.haja.tionaApp.utils;

import java.io.File;

public class StorageManager {

    public static boolean exist(String filename){ // TODO
        File file = new File(filename);
        return file.exists();
    }

}
