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

    /**
     * @param aString
     * @param index
     * @return substring of length one at index.
     * return empty string "" if aString is null or empty or index is out of range.
     */
    public static String getSubstringLengthOneAtIndex(String aString, int index) {
        if ((aString == null)
                || (aString.length() == 0)
                || (index < 0)
                || (index >= aString.length())) {
            return "";
        }
        return getSubstringInclusive(aString, index, index);
    }

    /** For each character in remove, if string contains the character
     *  the method removes the first occurrence.
     * @param string
     * @param remove
     * @return
     */
    public static String stringByRemovingLettersInString(String string, String remove) {

        if (remove == null) {
            return string;
        }

        String result = string;
        for (int index = 0; index < remove.length(); ++index) {
            String removeAtIndex = StringUtils.getSubstringLengthOneAtIndex(remove, index);
            result = result.replaceFirst(removeAtIndex, "");
        }
        return result;
    }

}
