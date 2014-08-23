/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevoDepositoAdministrador;
import EntidadesJPA.Bancos;
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
public class NuevoDepositoControlador
{
    
    NuevoDepositoAdministrador NuevoDepositoAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFMonto;
    public TextField TFNumeroBoleta;
    public ComboBox ComBoBanco;
    private IDepositos deposito;
    private Validaciones validar;
    
    
    
    public void initialize() {}
  
    public void initManager(final NuevoDepositoAdministrador NuevoDepositoAdministrador) {
        this.NuevoDepositoAdminsitrador = NuevoDepositoAdministrador;
        this.deposito = new IDepositos();
        this.llenarBancos();
        this.validar = new Validaciones();
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.NuevoDepositoAdminsitrador.cerrarNuevoDeposito();
    }
    public void Cancelar()
    {
        this.NuevoDepositoAdminsitrador.cerrarNuevoDeposito();
    }
    
    public void crearNuevoDeposito (ActionEvent event)
    {
        if ((this.TFMonto.getText().isEmpty())||(!this.validar.ValidarMontos(this.TFMonto.getText())))
        {
            this.NuevoDepositoAdminsitrador.showMensajes("Verifique El Monto");
        }
        else
            if((this.TFNumeroBoleta.getText().isEmpty())||(!this.validar.ValidarNumeros(this.TFNumeroBoleta.getText())))
            {
                this.NuevoDepositoAdminsitrador.showMensajes("Verifique El Numero de Boleta");
            }
        else
                 if ((String)this.ComBoBanco.getValue() == null)
                        {
                            this.NuevoDepositoAdminsitrador.showMensajes("Debe Seleccionar Un Banco");
                        }
        else
                    
            {
                Bancos banco = new Bancos ();
                IBancos bancoManejador = new IBancos();
                banco = bancoManejador.buscarBancoPorNombre((String)ComBoBanco.getValue());
                float monto = Float.parseFloat(TFMonto.getText());
                String boletaNum = TFNumeroBoleta.getText();
                String mensaje = "";

                mensaje = deposito.guardar(monto,boletaNum , banco);
                this.NuevoDepositoAdminsitrador.showMensajes(mensaje);
                TFMonto.setText("");
                TFNumeroBoleta.setText("");
                TFMonto.requestFocus();
            }
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
    } 
}
