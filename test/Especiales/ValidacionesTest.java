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
public class ValidacionesTest {
    
    public ValidacionesTest() {
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
     * Test of ValidarNumeros method, of class Validaciones.
     */
    @Test
    public void testValidarNumero() {
        System.out.println("ValidarNumero");
        String Numero = "1";
        Validaciones instance = new Validaciones();
        boolean expResult = true;
        boolean result = instance.ValidarNumero(Numero);
        if(result)
        {
            assertEquals(expResult, result);
        }
        else
        {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of ValidarNumeros method, of class Validaciones.
     */
    @Test
    public void testValidarNumeros() {
        System.out.println("ValidarNumeros");
        String Numeros = "12";
        Validaciones instance = new Validaciones();
        boolean expResult = true;
        boolean result = instance.ValidarNumeros(Numeros);
        if(result)
        {
            assertEquals(expResult, result);
        }
        else
        {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of FormatoFecha method, of class Validaciones.
     */
    @Test
    public void testFormatoFecha() {
        System.out.println("FormatoFecha");
        String Fecha = "02/12/2013";
        Validaciones instance = new Validaciones();
        boolean expResult = true;
        boolean result = instance.FormatoFecha(Fecha);
        if(result)
        {
            assertEquals(expResult, result);
        }
        else
        {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of FormatoNit method, of class Validaciones.
     */
    @Test
    public void testFormatoNit() {
        System.out.println("FormatoNit");
        String Nit = "123456-K";
        Validaciones instance = new Validaciones();
        boolean expResult = true;
        boolean result = instance.FormatoNit(Nit);
        if(result)
        {
            assertEquals(expResult, result);
        }
        else
        {
            fail("The test case is a prototype.");
        }
    }
}