package org.example.helpers;

import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Pattern;

public class Validation {
    public static boolean validateImage(MultipartFile file) {


        String fileName = file.getOriginalFilename();
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);

        if (fileExt.toLowerCase().equals("png") || fileExt.toLowerCase().equals("jpg") || fileExt.toLowerCase().equals("jpeg") || fileExt.toLowerCase().equals("webp") || fileExt.toLowerCase().isEmpty()) {
            return true;
        }

        return false;
    }

    public static boolean phoneValidation(String phone) {

        String contactRegex = "[0-1]{2}[^012]{1}[0-9]{8}";

        return Pattern.matches(contactRegex, phone);
    }
}
