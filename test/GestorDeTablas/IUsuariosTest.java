/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablas;

import GestorDeTablasJPA.IPermisos;
import GestorDeTablasJPA.IUsuarios;
import java.util.ArrayList;
import java.util.List;
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
public class IUsuariosTest {
    
    public IUsuariosTest() {
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
     * Test of guardar method, of class IUsuarios.
     */
    @Test
    public void testGuardar() throws Exception {
        System.out.println("guardar");
        String nombre = "Administrador";
        String contraseña = "123";
        String puesto = "Gerente";
        IPermisos permisos = new IPermisos();
        
        List<String> listaDePermisos = new ArrayList<String> ();
        listaDePermisos.add("Acceso a Pedidos");
        listaDePermisos.add("Acceso a Producción");
        listaDePermisos.add("Acceso a Bodegas");
        listaDePermisos.add("Acceso a Liquidaciones");
        listaDePermisos.add("Acceso a Finanzas");
        listaDePermisos.add("Modificar Usuarios");
        IUsuarios instance = new IUsuarios();
        try
        {
            String result = instance.guardar(nombre, contraseña, puesto, listaDePermisos); 
        }
        catch (Exception e)
        {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of retornarUsuario method, of class IUsuarios.
     */
//    @Test
//    public void testRetornarUsuario() {
//        System.out.println("retornarUsuario");
//        int id = 0;
//        IUsuarios instance = new IUsuarios();
//        Usuario expResult = null;
//        Usuario result = instance.retornarUsuario(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listaDeUsuarios method, of class IUsuarios.
//     */
//    @Test
//    public void testListaDeUsuarios() {
//        System.out.println("listaDeUsuarios");
//        IUsuarios instance = new IUsuarios();
//        List expResult = null;
//        List result = instance.listaDeUsuarios();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of asignarPermisos method, of class IUsuarios.
//     */
//    @Test
//    public void testAsignarPermisos() {
//        System.out.println("asignarPermisos");
//        Usuario usuarios = null;
//        List<Permisos> permisos = null;
//        IUsuarios instance = new IUsuarios();
//        String expResult = "";
//        String result = instance.asignarPermisos(usuarios, permisos);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of modificar method, of class IUsuarios.
//     */
//    @Test
//    public void testModificar() {
//        System.out.println("modificar");
//        IUsuarios usu = new IUsuarios ();
//        Usuario usuario = usu.listaDeUsuarios().get(0) ;
//        usuario.setNombre("juan");
//        usuario.setContraseña("jejeje");
//        IUsuarios instance = new IUsuarios();
//        
//        try
//        {
//            String result = instance.modificar(usuario);
//        }
//        catch (Exception e){
//         TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//        }
//    }
//
//    /**
//     * Test of eliminar method, of class IUsuarios.
//     */
//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar");
//        int id = 1;    
//        IUsuarios instance = new IUsuarios();
//        try{   
//        String result = instance.eliminar(id);
//        }
//     catch (Exception e)
//     {
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//     }
//    }

}
