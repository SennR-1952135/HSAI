package com.example.project.DataBase;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.project.Enums.Category;
import com.example.project.Enums.Color;
import com.example.project.Enums.Gender;
import com.example.project.Enums.Size;
import com.example.project.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

public class Converters {

    private static int imageCount = 0;

    //Color
    @TypeConverter
    public static Color stringToColor(String col){
        switch(col){
            case "red": return Color.RED;
            case "green": return Color.GREEN;
            case "blue": return Color.BLUE;
            case "orange": return Color.ORANGE;
            case "yellow": return Color.YELLOW;
            case "white": return Color.WHITE;
            case "black": return Color.BLACK;
            case "purple": return Color.PURPLE;
            case "pink": return Color.PINK;
            default: return null;
        }
    }
    @TypeConverter
    public static String ColorToString(Color col){
        return col.toString().toLowerCase();
    }

    //Gender
    @TypeConverter
    public static Gender stringToGender(String gen){
        switch(gen){
            case "male": return Gender.MALE;
            case "female": return Gender.FEMALE;
            case "child": return Gender.CHILD;
            case "unisex": return Gender.UNISEX;
            default: return null;
        }
    }
    @TypeConverter
    public static String genderToString(Gender gen){
        return gen.toString().toLowerCase();
    }

    //Category
    @TypeConverter
    public static Category stringToCategory(String cat){
        switch(cat){
            case "pants": return Category.PANTS;
            case "tshirt": return Category.TSHIRT;
            case "short": return Category.SHORT;
            case "dress": return Category.DRESS;
            case "coat": return Category.COAT;
            case "skirt": return Category.SKIRT;
            default: return null;
        }
    }
    @TypeConverter
    public static String categoryToString(Category cat){
        return cat.toString().toLowerCase();
    }

    //Size

    @TypeConverter
    public static Size stringToSize(String size){
        switch(size){
            case "xs": return Size.XS;
            case "s" : return Size.S;
            case "m" : return Size.M;
            case "l" : return Size.L;
            case "xl": return Size.XL;
            default: return null;
        }
    }
    @TypeConverter
    public static String sizeToString(Size size){
        return size.toString().toLowerCase();
    }


//    @TypeConverter
//    public static Drawable URIToDrawable(URI uri) {
//        File file = new File(uri);
//        try {
//            FileInputStream inputStream = new FileInputStream(file);
//
//            return (Drawable) new BitmapDrawable(bitmap)
//        } catch (IOException ioe){
//            ioe.printStackTrace();
//        }
//
//    }
//    @TypeConverter
//    public static int drawableToInt(Drawable img) {
//
//    }
}
