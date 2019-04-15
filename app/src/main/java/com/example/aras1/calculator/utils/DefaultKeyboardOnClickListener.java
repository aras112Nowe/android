package com.example.aras1.calculator.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * This annotation allow you link your button with default listener.
 *
 * @author Arkadiusz Kikosicki;
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {FIELD})
public @interface DefaultKeyboardOnClickListener
    {
    }
