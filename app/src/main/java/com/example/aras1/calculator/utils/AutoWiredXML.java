package com.example.aras1.calculator.utils;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;


/**
 * This annotation allow you link your var name with this same name from xml ID.
 * @author  Arkadiusz Kikosicki;
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={FIELD})
public @interface AutoWiredXML
    {

    }
