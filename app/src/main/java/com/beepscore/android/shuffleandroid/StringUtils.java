package com.beepscore.android.shuffleandroid;

/**
 * Created by stevebaker on 6/12/15.
 */
public class StringUtils {

    /** Method is "safe" in that it avoids out of bounds exceptions
     * Unlike standard string.substring(), range from startIndex to endIndex is inclusive.
     * @param aString
     * @param endIndex may be in middle, at end, or past end of aString.
     * @param startIndex
     * @return substring from startIndex to endIndex inclusive.
     *                 if endIndex >= aString.length return substring to endIndex
     */
    protected static String getSafeSubstringInclusive(String aString,
                                                      int startIndex, int endIndex) {
        String substring = "";
        if (endIndex == aString.length() - 1) {
            // endIndex is at end of aString
            substring = aString.substring(startIndex);
        } else {
            substring = aString.substring(startIndex, endIndex + 1);
        }
        return substring;
    }

    public static boolean isStringNullOrEmpty(String string) {
        if ((string == null) || (string.length() == 0)) {
            return true;
        } else {
            return false;
        }
    }

    /** Method is "safe" in that it avoids out of bounds exceptions
     * @param aString
     * @param index
     * @return substring of length one at index.
     * return empty string "" if aString is null or empty or index is out of range.
     */
    public static String getSafeSubstringLengthOneAtIndex(String aString, int index) {
        if (isStringNullOrEmpty(aString)
                || (index < 0)
                || (index >= aString.length())) {
            return "";
        }
        return getSafeSubstringInclusive(aString, index, index);
    }

}
