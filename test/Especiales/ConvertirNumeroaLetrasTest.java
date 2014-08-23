/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luis
 */
public class ConvertirNumeroaLetrasTest {
    
    public ConvertirNumeroaLetrasTest() {
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
     * Test of convertNumberToLetter method, of class ConvertirNumeroaLetras.
     */
    @Test
    public void testConvertNumberToLetter_String() {
        System.out.println("convertNumberToLetter");
        String number = "1";
        String expResult = "";
        String result = ConvertirNumeroaLetras.convertNumberToLetter(number);
        System.out.println(result);
    }
}