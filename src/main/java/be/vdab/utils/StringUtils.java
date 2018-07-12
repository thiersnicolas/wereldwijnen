package be.vdab.utils;

import java.math.BigDecimal;

public class StringUtils {
	public static boolean isLong(String string) {
		try {
			Long.parseLong(string);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static boolean isBigDecimal(String string) {
		try {
			new BigDecimal(string);
			return true;
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
	}
	
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
		if (s == null) return false;
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
}
