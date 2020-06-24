/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.junit;

/**
 *
 * @author Jursh
 */
public class Search {

    public Song binarySearch(String searchTerm, Song[] songs) {
        Song result = null;

        int left = 0;
        int right = songs.length - 1;

        while (left <= right) {
            int mid = left + (right) / 2;

            if (songs[mid].getSongName().equals(searchTerm)) {
                result = songs[mid];
                break;
            } else if ((stringCompare(songs[mid].getSongName(), searchTerm) != 0) && left == right) {
                break;
            } else if (stringCompare(songs[mid].getSongName(), searchTerm) > 0) {
                right = mid;

            } else {
                left = mid;
            }
        }

        return result;
    }

    public int stringCompare(String str1, String str2) {
        int length1 = str1.length();
        int length2 = str2.length();
        int lengthMin = Math.min(length1, length2);
        //int i = 0;
        boolean resolved = false; //if this is true, then exits the loop
        int result = 0; //if this is positive, str1 is after str2

        for (int i = 0; i < lengthMin; i++) {
            int str1CharVal = (int) str1.charAt(i);
            int str2CharVal = (int) str2.charAt(i);

            if (str1CharVal != str2CharVal) {
                return str1CharVal - str2CharVal;
            }
        }

        if (length1 != length2) {
            return length1 - length2;
        } else {
            return 0;
        }
    }
}
