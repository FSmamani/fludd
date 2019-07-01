package com.freher.fludd;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    //Email Validation pattern
    //\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}\b
    public static final String regEx = "\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}\b";

    //Fragments Tags
    public static final String Login_Fragment = "FragmentLogin";
    public static final String SignUp_Fragment = "FragmentRegister";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}