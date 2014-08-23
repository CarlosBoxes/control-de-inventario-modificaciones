/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;


import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import AdministradoresGUI.IniciorSesionAdministrador;
import EntidadesJPA.Usuario;
import GestorDeTablasJPA.IProductos;
import GestorDeTablasJPA.IUsuarios;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class InicioSesionControlador{

    /**
     * Initializes the controller class.
     */
    IniciorSesionAdministrador loginManager;
    @FXML private TextField TFNombre;
    @FXML private PasswordField PWContraseña;
    
    public void initialize() {}
  
    public void initManager(final IniciorSesionAdministrador loginManager) {
        this.loginManager = loginManager;
    }
    
    public void iniciarSesion(ActionEvent event) {
            IUsuarios logear = new IUsuarios ();
            try
            {
            if(logear.comprobarContraseña(TFNombre.getText(), PWContraseña.getText()))
            {
                Usuario Logeado = logear.buscarUsuarioPorNombre(TFNombre.getText());
                System.out.println("Bienvenido");
                this.loginManager.showMensajes("Bienvenido"+" "+TFNombre.getText());
                this.loginManager.showVentanaPrincipal(Logeado);
                if(new IProductos().ListaProductosVencidos() != null)
                {
                    this.loginManager.showMensajes("Hay Productos Vencidos.");
                }
            }
            else
            {
               this.loginManager.showMensajes("Datos Incorrectos Intente De Nuevo");
            
            }
            }
            catch (Exception e)
            {
                System.out.print(e);
            }
    }   
}
