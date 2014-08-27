/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablas;

import GestorDeTablasJPA.IPedidoProveedores;
import GestorDeTablasJPA.IProveedores;
import EntidadesJPA.PedidoProveedores;
import EntidadesJPA.Proveedores;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luis__000
 */
public class IPedidoProveedoresTest {
    
    public IPedidoProveedoresTest() {
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
     * Test of guardar method, of class IPedidoProveedores.
     */
    @Test
    public void testGuardar() {
        System.out.println("guardar");
        Date fecha = null;
        boolean aplicado = false;
        String noFactura = "";
        float total = 0.0F;
        Proveedores proveedor = null;
        IPedidoProveedores instance = new IPedidoProveedores();
        String expResult = "";
        String result = instance.guardar(fecha, aplicado, noFactura, total, proveedor,0,0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaDePedidoProveedores method, of class IPedidoProveedores.
     */
//    @Test
//    public void testListaDePedidoProveedores() {
//        System.out.println("listaDePedidoProveedores");
//        IPedidoProveedores instance = new IPedidoProveedores();
//        List result = instance.buscarPedidoPorProveedor("1");
//        if(result.isEmpty())
//        {
//            fail("The test case is a prototype.");
//        }
//        
//    }

    /**
     * Test of modificar method, of class IPedidoProveedores.
     */
    @Test
    public void testModificar() {
        System.out.println("modificar");
        PedidoProveedores pedidoProveedores = null;
        IPedidoProveedores instance = new IPedidoProveedores();
        String expResult = "";
        String result = instance.modificar(pedidoProveedores);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class IPedidoProveedores.
     */
    @Test
    public void testEliminar() {
        System.out.println("eliminar");
        int id = 0;
        IPedidoProveedores instance = new IPedidoProveedores();
        String expResult = "";
        String result = instance.eliminar(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retornarUltimoIngresado method, of class IPedidoProveedores.
     */
    @Test
    public void testRetornarUltimoIngresado() {
        System.out.println("retornarUltimoIngresado");
        IPedidoProveedores instance = new IPedidoProveedores();
        PedidoProveedores expResult = null;
        PedidoProveedores result = instance.retornarUltimoIngresado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPedidoPorId method, of class IPedidoProveedores.
     */
    @Test
    public void testBuscarPedidoPorId() {
        System.out.println("buscarPedidoPorId");
        int IdPedido = 0;
        IPedidoProveedores instance = new IPedidoProveedores();
        PedidoProveedores expResult = null;
        PedidoProveedores result = instance.buscarPedidoPorId(IdPedido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPedidoPorProveedor method, of class IPedidoProveedores.
     */
    @Test
    public void testBuscarPedidoPorProveedor() {
        System.out.println("buscarPedidoPorProveedor");
        IProveedores progestor = new IProveedores ();
        Proveedores IdProveedor = progestor.listaDeProveedores().get(0);
        IPedidoProveedores instance = new IPedidoProveedores();
        List expResult = null;
        try
        {
        
            
        List result = instance.buscarPedidoPorProveedor(IdProveedor);
        }
        catch (Exception e)
        {
       
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        }
    }
}