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
 * @author Ch470
 */
public class EliminarPedidoTest {
    
    public EliminarPedidoTest() {
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
     * Test of eliminarPedido method, of class EliminarPedido.
     */
    @Test
    public void testEliminarPedido() {
        System.out.println("eliminarPedido");
        int idPedido = 107;
        EliminarPedido instance = new EliminarPedido();
        String expResult = "";
        String result = instance.eliminarPedido(idPedido);
        // TODO review the generated test code and remove the default call to fail./
    }

    /**
     * Test of borrarDescripciones method, of class EliminarPedido.
     */
   
}