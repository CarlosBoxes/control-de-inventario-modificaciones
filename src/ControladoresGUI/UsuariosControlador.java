/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.IniciorSesionAdministrador;
import AdministradoresGUI.UsuariosAdministrador;
import EntidadesJPA.Usuario;
import GestorDeTablasJPA.IUsuarios;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author luis__000
 */
public class UsuariosControlador 
{
    UsuariosAdministrador UsuariosAdministrador;
    Dimension TamañoVentana = Toolkit.getDefaultToolkit().getScreenSize();
    
    @FXML
    public VBox pnlPrincipal;
    public VBox pnlModificar;
    public TextField TFNombre;
    public TextField TFNuevoNombre;
    public TextField TFNuevaContraseña;
    public Usuario Usuario;
    
    public void initialize() {}
  
    public void initManager(final UsuariosAdministrador UsuariosAdministrador)
    {
        this.UsuariosAdministrador = UsuariosAdministrador;
        this.pnlPrincipal.setPrefSize(TamañoVentana.getWidth()-180, TamañoVentana.getWidth()-90);
        this.Usuario = UsuariosAdministrador.OpcionesAdministrador.Usuario;
        this.TFNombre.setText(Usuario.getNombre());
        this.pnlModificar.setVisible(false);
        this.TFNuevoNombre.setText(Usuario.getNombre());
    }
    
    public void ActivarModificarUsuario(ActionEvent event)
    {
        this.pnlModificar.setVisible(true);   
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.pnlModificar.setVisible(false);         
    }
    
    public void ModificarUsuario(ActionEvent event)
    {
        if ("".equals(TFNuevoNombre.getText()))
            {
                this.UsuariosAdministrador.showMensajes("Ingrese El Nuevo Nombre Del Usuario");
            }
        else
        if("".equals(TFNuevaContraseña.getText()))
        {
            this.UsuariosAdministrador.showMensajes("Ingrese la Nueva Contraseña");
        }
        else
        {
            IUsuarios Modificar = new IUsuarios();
            Usuario.setNombre(TFNuevoNombre.getText());
            Usuario.setContraseña(TFNuevaContraseña.getText());
            String Mensaje = Modificar.modificar(Usuario);
            this.UsuariosAdministrador.showMensajes(Mensaje);            
            this.TFNombre.setText(this.Usuario.getNombre());
        }
    }
    
    public void start(ActionEvent Event)
    {
        Scene scene = new Scene(new StackPane());
        Stage stage = new Stage();
        IniciorSesionAdministrador loginManager = new IniciorSesionAdministrador(scene, stage);
        loginManager.showLoginScreen();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        this.UsuariosAdministrador.stage.close();
    }
}

