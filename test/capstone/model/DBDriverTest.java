/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.model;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dylan
 */
public class DBDriverTest {
    
    public DBDriverTest() {
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
     * Test of insertEvent method, of class DBDriver.
     */
    @Test
    public void testInsertEvent() {
        System.out.println("insertEvent");
        String title = "Event Test";
        LocalDate date = LocalDate.now();
        int maxCap = 50;
        int result = DBDriver.insertEvent(title, date, maxCap);
        BusinessEvent expEvent = DBDriver.getEvent(result);
        DBDriver.deleteEvent(result);
        assertEquals(title, expEvent.getTitle());
        assertEquals(date, expEvent.getDate());
        assertEquals(maxCap, expEvent.getMaxCapacity());
    }
    
}
