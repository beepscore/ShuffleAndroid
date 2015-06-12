package com.beepscore.android.shuffleandroid;

/**
 * Created by stevebaker on 6/12/15.
 */
public class StringUtils {

    /** Unlike standard string.substring(), range from startIndex to endIndex is inclusive.
     * @param aString
     * @param endIndex may be in middle or at end of aString. Method handles both cases.
     * @param startIndex
     * @return substring from startIndex to endIndex inclusive.
     */
    protected static String getSubstringInclusive(String aString, int startIndex, int endIndex) {
        String substring = "";
        if (endIndex == aString.length() - 1) {
            // endIndex is at end of aString
            substring = aString.substring(startIndex);
        } else {
            substring = aString.substring(startIndex, endIndex + 1);
        }
        return substring;
    }

    public static String getSubstringLengthOneAtIndex(String aString, int index) {
        return getSubstringInclusive(aString, index, index);
    }

}
