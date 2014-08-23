/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;
import AdministradoresGUI.EditarDepositoAdministrador;
import EntidadesJPA.Bancos;
import EntidadesJPA.Depositos;
import Especiales.Validaciones;
import GestorDeTablasJPA.IBancos;
import GestorDeTablasJPA.IDepositos;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class EditarDepositoControlador
{
    
    EditarDepositoAdministrador EditarDepositoAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFMonto;
    public TextField TFNuemeroBoleta;
    public ComboBox ComBoBanco;
    private Validaciones validar;
    
    public void initialize() {}
  
    public void initManager(final EditarDepositoAdministrador EditarDepositoAdministrador) {
        this.EditarDepositoAdminsitrador = EditarDepositoAdministrador;
        this.TFMonto.setText(String.valueOf(EditarDepositoAdministrador.Deposito.getMonto()));
        this.TFNuemeroBoleta.setText(EditarDepositoAdministrador.Deposito.getNumeroDeBoleta());
        this.ComBoBanco.setValue(EditarDepositoAdministrador.Deposito.getBancosIdbancos().getNombre());
        this.llenarBancos();
        this.validar = new Validaciones();
    }
    
    public void llenarBancos ()
    {         
         IBancos bancoManejador = new IBancos();
         List<String> list = new ArrayList<String>();
         for (Bancos tipo:bancoManejador.retornarListaDeBancos())
         {
             list.add(tipo.getNombre());
         }
         ObservableList<String> observableList = FXCollections.observableList(list);
         ComBoBanco.setItems(observableList);
         if (this.ComBoBanco.getItems().isEmpty())
         {
             this.EditarDepositoAdminsitrador.showMensajes("No Existen Bancos, Diríjase al área de Bancos");
             this.Cancelar();
         }
    } 
    public void EditarDeposito(ActionEvent event)
    {
        String hola = (String)this.ComBoBanco.getValue();
        if ((this.TFMonto.getText().isEmpty())||(!this.validar.ValidarMontos(this.TFMonto.getText())))
        {
            this.EditarDepositoAdminsitrador.showMensajes("Verifique El Monto");
        }
        else
            if((this.TFNuemeroBoleta.getText().isEmpty())||(!this.validar.ValidarNumeros(this.TFNuemeroBoleta.getText())))
            {
                this.EditarDepositoAdminsitrador.showMensajes("Verifique El Numero de Boleta");
            }
        else
                if ((String)this.ComBoBanco.getValue() == null)
                        {
                            this.EditarDepositoAdminsitrador.showMensajes("Debe Seleccionar Un Banco");
                        }
        else
            {
                IDepositos busqueda = new IDepositos();
                Depositos Editar = new Depositos();
                Editar = busqueda.buscarDepositoPorNumeroBoleta(this.EditarDepositoAdminsitrador.Deposito.getNumeroDeBoleta());
                Editar.setMonto(Float.parseFloat(TFMonto.getText()));
                Editar.setNumeroDeBoleta(TFNuemeroBoleta.getText());
                Editar.setBancosIdbancos(new IBancos().buscarBancoPorNombre((String)ComBoBanco.getValue()));
                String Mensaje = busqueda.modificar(Editar);
                this.EditarDepositoAdminsitrador.showMensajes(Mensaje);
            }
    }
        
    public void Cancelar(ActionEvent event)
    {
        this.EditarDepositoAdminsitrador.cerrarEditarDeposito();
    }
    public void Cancelar()
    {
        this.EditarDepositoAdminsitrador.cerrarEditarDeposito();
    }
}
