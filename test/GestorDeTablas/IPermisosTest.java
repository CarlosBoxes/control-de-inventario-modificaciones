/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablas;

import GestorDeTablasJPA.IPermisos;
import EntidadesJPA.Permisos;
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
public class IPermisosTest {
    
    public IPermisosTest() {
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
     * Test of guardar method, of class IPermisos.
     */
    @Test
    public void testGuardar() {
        System.out.println("guardar");
        List<String> listaDePermisos = new ArrayList<String> ();
        listaDePermisos.add("Acceso a Pedidos");
        listaDePermisos.add("Acceso a Bodegas");
        listaDePermisos.add("Acceso a Producción");
        listaDePermisos.add("Acceso a Liquidaciones");
        listaDePermisos.add("Acceso a Finanzas");
        listaDePermisos.add("Modificar Usuarios");
        //listaDePermisos.add("Gestión de Inventario");
        IPermisos instance = new IPermisos();
        for (String perm:listaDePermisos)
        {
        String result = instance.guardar(perm);
        }
    }

    /**
     * Test of listaDePermisos method, of class IPermisos.
     */
   
}