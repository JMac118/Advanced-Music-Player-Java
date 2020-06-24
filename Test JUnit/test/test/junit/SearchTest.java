/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jursh
 */
public class SearchTest {
    
    public SearchTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of binarySearch method, of class Search.
     */
    @Test
    public void testBinarySearch() {
        System.out.println("binarySearch");
        String searchTerm = "song1.mp3";
        Song[] songs = new Song[3];
        songs[0] = new Song("song1.mp3", "fake/location");
        songs[1] = new Song("song2.mp3", "fake/location");
        songs[2] = new Song("song3.mp3", "fake/location");

        Search instance = new Search();

        Song result = instance.binarySearch(searchTerm, songs);
        assertEquals("song1.mp3", result.getSongName());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of stringCompare method, of class Search.
     */
    @Test
    public void testStringCompare() {
        System.out.println("stringCompare");
        String str1 = "aaa";
        String str2 = "ccc";
        Search instance = new Search();
        boolean expResult = true;
        int result = instance.stringCompare(str1, str2);
        if(result>0){expResult = true;}
        assertEquals(true, expResult);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
