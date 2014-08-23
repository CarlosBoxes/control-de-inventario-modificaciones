/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import AdministradoresGUI.IniciorSesionAdministrador;
import static javafx.application.Application.launch;

/**
 *
 * @author luis__000
 * hola mundo
 */
public class Aplicacion extends Application {
    
    public static void main(String[] args) { launch(args); }
    @Override 
    public void start(Stage stage) throws IOException {
        //cambio
        Scene scene = new Scene(new StackPane());
        IniciorSesionAdministrador loginManager = new IniciorSesionAdministrador(scene, stage);
        loginManager.showLoginScreen();
        stage.setTitle("Corporación del Campo");  
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}