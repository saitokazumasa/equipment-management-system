package jp.ac.morijyobi.equipmentmanagementsystem.util;

public class IntUtil {
    private IntUtil() {}

    public static int TryToInt(final String str) {
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException e) {
            return -1;
        }
    }
}
