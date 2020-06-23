/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanced.music.player;

import java.util.LinkedList;

/**
 *
 * @author Jursh
 */
class SongSort {

    public String[] MergeSort(String[] arr) {
        String[] left;
        String[] right;
        String[] result = null;
        int midPoint;

        //if theres only 1 in split then it is base case
        if (arr.length <= 1) {
            result = arr;
        } else {
            midPoint = arr.length / 2;

            left = new String[midPoint];

            if (arr.length % 2 == 0) {
                right = new String[midPoint];
            } else {
                right = new String[midPoint + 1];
            }

            for (int i = 0; i < midPoint; i++) {
                left[i] = arr[i];
            }

            int x = 0;

            for (int i = midPoint; i < arr.length; i++) {
                right[x] = arr[i];
                x++;
            }
            left = MergeSort(left);
            right = Merge(left, right);

        }
        return result;
    }

    private String[] Merge(String[] left, String[] right) {
        int resultlength = right.length + left.length;
        String[] result = new String[resultlength];

        int indexLeft = 0, indexRight = 0, indexResult = 0;

        while (indexLeft < left.length || indexRight < right.length) {
            if (indexLeft < left.length && indexRight < right.length) {
                if (stringCompare(left[indexLeft], right[indexRight]) >= 0) {
                    result[indexResult] = left[indexLeft];
                    indexLeft++;
                    indexResult++;
                } else {
                    result[indexResult] = right[indexRight];
                    indexRight++;
                    indexResult++;
                }

            } else if (indexLeft < left.length) {
                result[indexResult] = left[indexLeft];
                indexLeft++;
                indexResult++;
            } else if (indexRight < right.length) {
                result[indexResult] = right[indexRight];
                indexRight++;
                indexResult++;
            }
        }
        return result;
    }

    public Song[] MergeSortSong(Song[] arr) {
        
        
        Song[] left;
        Song[] right;
        Song[] result = null;
        int midPoint;

        //if theres only 1 in split then it is base case
        if (arr.length <= 1) {
            result = arr;
        } else {
            midPoint = arr.length / 2;

            left = new Song[midPoint];

            if (arr.length % 2 == 0) {
                right = new Song[midPoint];
            } else {
                right = new Song[midPoint + 1];
            }

            for (int i = 0; i < midPoint; i++) {
                left[i] = arr[i];
            }

            int x = 0;

            for (int i = midPoint; i < arr.length; i++) {
                right[x] = arr[i];
                x++;
            }
            left = MergeSortSong(left);
            right = MergeSortSong(right);

            result = MergeSong(left, right);

        }
        return result;
    }

    private Song[] MergeSong(Song[] left, Song[] right) {
        int resultlength = right.length + left.length;
        Song[] result = new Song[resultlength];

        int indexLeft = 0, indexRight = 0, indexResult = 0;

        while (indexLeft < left.length || indexRight < right.length) {
            if (indexLeft < left.length && indexRight < right.length) {
                if (stringCompare(left[indexLeft].getSongName(), right[indexRight].getSongName()) <= 0) {
                    result[indexResult] = left[indexLeft];
                    indexLeft++;
                    indexResult++;
                } else {
                    result[indexResult] = right[indexRight];
                    indexRight++;
                    indexResult++;
                }

            } else if (indexLeft < left.length) {
                result[indexResult] = left[indexLeft];
                indexLeft++;
                indexResult++;
            } else if (indexRight < right.length) {
                result[indexResult] = right[indexRight];
                indexRight++;
                indexResult++;
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
