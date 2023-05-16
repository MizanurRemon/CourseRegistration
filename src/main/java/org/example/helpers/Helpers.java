package org.example.helpers;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {

    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = new Date();

        return formatter.format(date);
    }

    public static byte[] imageToByte(MultipartFile file) throws IOException {
        return file.getBytes();
    }
}
