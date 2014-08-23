/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablas;

import GestorDeTablasJPA.IProductos;
import EntidadesJPA.Productos;
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
public class IProductosTest {
    
    public IProductosTest() {
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
     * Test of guardar method, of class IProductos.
     */
//    @Test
//    public void testGuardar() {
//        System.out.println("guardar");
//        String nombre = "";
//        String presentacion = "";
//        int unidadDeMedida = 0;
//        float precioCosto = 0.0F;
//        float precioVenta = 0.0F;
//        Date fechaDeVencimiento = null;
//        String descripcion = "";
//        String categoria = "";
//        boolean anulado = false;
//        boolean cambio = false;
//        float devoluciones = 0.0F;
//        IProductos instance = new IProductos();
//        String expResult = "";
//        String result = instance.guardar(nombre, presentacion, unidadDeMedida, precioCosto, precioVenta, fechaDeVencimiento, descripcion, categoria, anulado, cambio, devoluciones);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listaDeProductos method, of class IProductos.
//     */
//    @Test
//    public void testListaDeProductos() {
//        System.out.println("listaDeProductos");
//        IProductos instance = new IProductos();
//        List expResult = null;
//        List result = instance.listaDeProductos();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of modificar method, of class IProductos.
//     */
//    @Test
//    public void testModificar() {
//        System.out.println("modificar");
//        Productos productos = null;
//        IProductos instance = new IProductos();
//        String expResult = "";
//        String result = instance.modificar(productos);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of eliminar method, of class IProductos.
//     */
//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar");
//        int id = 0;
//        IProductos instance = new IProductos();
//        String expResult = "";
//        String result = instance.eliminar(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of retornarUltimoIngresado method, of class IProductos.
//     */
//    @Test
//    public void testRetornarUltimoIngresado() {
//        System.out.println("retornarUltimoIngresado");
//        IProductos instance = new IProductos();
//        Productos expResult = null;
//        Productos result = instance.retornarUltimoIngresado();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of buscarProductoPorId method, of class IProductos.
//     */
//    @Test
//    public void testBuscarProductoPorId() {
//        System.out.println("buscarProductoPorId");
//        int idProducto = 0;
//        IProductos instance = new IProductos();
//        Productos expResult = null;
//        Productos result = instance.buscarProductoPorId(idProducto);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of buscarListaProductoPorNombre method, of class IProductos.
//     */
//    @Test
//    public void testBuscarListaProductoPorNombre() {
//        System.out.println("buscarListaProductoPorNombre");
//        String nombreProducto = "";
//        IProductos instance = new IProductos();
//        List expResult = null;
//        List result = instance.buscarListaProductoPorNombre(nombreProducto);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of buscarProductoPorNombre method, of class IProductos.
//     */
//    @Test
//    public void testBuscarProductoPorNombre() {
//        System.out.println("buscarProductoPorNombre");
//        String Producto = "";
//        IProductos instance = new IProductos();
//        Productos expResult = null;
//        Productos result = instance.buscarProductoPorNombre(Producto);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of ListaProductosVencidos method, of class IProductos.
//     */
    @Test
    public void testListaProductosVencidos() {
        System.out.println("ListaProductosVencidos");
        IProductos instance = new IProductos();
        List result = instance.ListaProductosVencidos();
        if(result.isEmpty())
        {
            System.out.println("No hay Productos");
        }
        else
        {
            System.out.println("Hay Productos");
        }
    }
}