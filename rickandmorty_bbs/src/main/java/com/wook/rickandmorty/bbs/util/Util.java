package com.wook.rickandmorty.bbs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public Boolean validUrl(String url) {
        Pattern pattern = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

}
