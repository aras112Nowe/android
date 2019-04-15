package com.example.aras1.calculator.utils;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aras1.calculator.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class KittyKit
    {
    public static <T extends AppCompatActivity> void initFieldByReflection(T object)
        {
        for (Field field : object.getClass().getDeclaredFields())
            {
            if (field.isAnnotationPresent(AutoWiredXML.class))
                {
                try
                    {
                    field.setAccessible(true);
                    field.set(object, object.findViewById(R.id.class.getField(field.getName()).getInt(Integer.class)));
                    }
                catch (Exception e)
                    {
                    Log.w("autowired", "ERROR___________ in autowired" + field.getName() + " !!!check this field- this name has to be\n" +
                            "the same like in XML ", e);
                    }
                }
            }
        }

    public static <T extends AppCompatActivity> void injectListener(T object,
                                                                    View.OnClickListener listener)
        {
        for (Field field : object.getClass().getDeclaredFields())
            {
            if (field.isAnnotationPresent(InjectOnClickListener.class))
                {
                try
                    {
                    field.setAccessible(true);
                    Button button = (Button) field.get(object);
                    button.setOnClickListener(listener);
                    }
                catch (Exception e)
                    {
                    Log.w("autowired", "check field name - this name has to be\n" +
                            "the same like in XML ", e);
                    }
                }
            }
        }

    public static <T extends AppCompatActivity> ArrayList<Button> setDefaultListener(T object, String nameOfResult)
        {
        ArrayList<Button> buttons = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields())
            {
            if (field.isAnnotationPresent(DefaultKeyboardOnClickListener.class))
                {
                try
                    {
                    field.setAccessible(true);
                    Button button = (Button) field.get(object);
                    buttons.add(button);
                    button.setOnClickListener((v) ->
                            {
                            try
                                {
                                Field field1 = object.getClass().getDeclaredField(nameOfResult);
                                field1.setAccessible(true);
                                TextView result = (TextView) field1.get(object);
                                result.append(field.getName().substring(field.getName().length() - 1));
                                }
                            catch (Exception e)
                                {
                                e.printStackTrace();
                                }
                            }
                    );
                    }
                catch (Exception e)
                    {
                    Log.w("autowired", "check field name - this name has to be\n" +
                            "the same like in XML ", e);
                    }
                }

            }
        return buttons;
        }
    }

