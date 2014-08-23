/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarChequeClienteAdministrador;
import EntidadesJPA.Bancos;
import EntidadesJPA.ChequesClientes;
import Especiales.Validaciones;
import GestorDeTablasJPA.IBancos;
import GestorDeTablasJPA.IChequesClientes;
import GestorDeTablasJPA.IClientes;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class EditarChequeClienteControlador
{
    
    EditarChequeClienteAdministrador EditarChequeClienteAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFMonto;
    public TextField TFNumeroCheque;
    public TextField TFFecha;
    public TextField TFNombreCliente;
    public ComboBox ComBoBanco;      
    private Validaciones validar;
    private int ContadorTecla;
    
    public void initialize() {}
  
    public void initManager(final EditarChequeClienteAdministrador EditarChequeClienteAdministrador) {
        this.EditarChequeClienteAdminsitrador = EditarChequeClienteAdministrador;
        this.TFMonto.setText(String.valueOf(EditarChequeClienteAdministrador.ChequeCliente.getMonto()));
        this.TFNumeroCheque.setText(String.valueOf(EditarChequeClienteAdministrador.ChequeCliente.getNumero()));
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(EditarChequeClienteAdministrador.ChequeCliente.getFecha());
        this.TFFecha.setText(fecha);
        this.TFNombreCliente.setText(EditarChequeClienteAdministrador.ChequeCliente.getClientesidCliente().getNombre());
        this.ComBoBanco.setValue(EditarChequeClienteAdminsitrador.ChequeCliente.getBancosIdbancos().getNombre());
        this.validar = new Validaciones();
        llenarBancos();
        if (this.ComBoBanco.getItems().isEmpty())
        {
            this.EditarChequeClienteAdminsitrador.showMensajes("Lista de Bancos Vacia, Diríjase al área de Bancos");
            this.Cancelar();
        }
    }
    
    public void EditarChequeCliente(ActionEvent event)
    {
        if ((this.TFMonto.getText().isEmpty())||(!this.validar.ValidarMontos(this.TFMonto.getText())))
        {
            this.EditarChequeClienteAdminsitrador.showMensajes("Verificar Monto del Cheque");
        }
        else
            if ((this.TFNumeroCheque.getText().isEmpty())||(!this.validar.ValidarNumeros(this.TFNumeroCheque.getText())))
            {
                this.EditarChequeClienteAdminsitrador.showMensajes("Verificar Numero del Cheque");
            }
        else
                if (this.TFNombreCliente.getText().isEmpty())
                {
                    this.EditarChequeClienteAdminsitrador.showMensajes("Debe ingresar el nombre del Cliente");
                }
        else
                    if ((String)this.ComBoBanco.getValue() == null)
                            {
                                this.EditarChequeClienteAdminsitrador.showMensajes("Debe Seleccionar un Banco");
                            }
        else
                    {
               
                            IChequesClientes busqueda = new IChequesClientes();
                            ChequesClientes Editar = new ChequesClientes();
                            Editar = busqueda.buscarChequesNumeroChueque(this.EditarChequeClienteAdminsitrador.ChequeCliente.getNumero());
                            Editar.setMonto(Float.parseFloat(TFMonto.getText()));
                            Editar.setNumero(Integer.parseInt(TFNumeroCheque.getText()));
                            Editar.setClientesidCliente(new IClientes().buscarClientesPorNombre(TFNombreCliente.getText()));
                            Editar.setBancosIdbancos(new IBancos().buscarBancoPorNombre((String)ComBoBanco.getValue()));
                            String Mensaje = busqueda.modificar(Editar);
                            this.EditarChequeClienteAdminsitrador.showMensajes(Mensaje);
                            this.TFMonto.setText("");
                            this.TFNumeroCheque.setText("");
                            this.TFFecha.setText("");
                            this.ContadorTecla = 0;
                            this.TFNombreCliente.setText("");
                    }
    }
    
    public void llenarBancos()
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
    
    public void Cancelar(ActionEvent event)
    {
        this.EditarChequeClienteAdminsitrador.cerrarEditarChequeCliente();
    }
    public void Cancelar()
    {
        this.EditarChequeClienteAdminsitrador.cerrarEditarChequeCliente();
    }
    
    public void SoltoTecla(KeyEvent e)
    {
        String Tecla = e.getText();
        int Key;
        Key = e.getCode().impl_getCode();
        boolean Valido = validar.ValidarNumero(Tecla);
        if(Key == 8)
        {
            ComprobarTamaño();
        }
        else if(Key == 111 || Key == 55)
        {
            AgregarCero();
        }
        else
        {
            if((Key > 46 && Key < 106))
            {
                if(!Valido)
                {
                    TFFecha.deletePreviousChar();
                }
                else
                {

                    ContadorTecla++;
                    if(TFFecha.getText().length()<6)
                    {
                        if(ContadorTecla == 2)
                        {
                            TFFecha.setText(TFFecha.getText()+"/");
                            TFFecha.end();
                            ComprobarTamaño();
                        }
                    }
                    else if(TFFecha.getText().length() > 10)
                    {
                        TFFecha.deletePreviousChar();
                    }
                    ComprobarTamaño();
                }
            }
        }
    }
    
    private void ComprobarTamaño()
    {
        if(TFFecha.getText().length() == 0 || TFFecha.getText().length() == 3 || TFFecha.getText().length() == 5)
        {
            ContadorTecla = 0;
        }
        if(TFFecha.getText().length() == 1 || TFFecha.getText().length() == 4)
        {
            ContadorTecla = 1;
        }
    }
    
    private void AgregarCero()
    {
        if(TFFecha.getText().length() == 2)
        {
            TFFecha.setText("0"+TFFecha.getText());
            TFFecha.end();
            ContadorTecla = 0;
        }
        else if(TFFecha.getText().length() == 5)
        {
            TFFecha.insertText(3, "0");
            TFFecha.end();
            ContadorTecla = 0;
        }
        else if(TFFecha.getText().length() < 3)
        {
            TFFecha.deletePreviousChar();
        }
        else if(TFFecha.getText().length() > 3 && TFFecha.getText().length() < 6)
        {
            TFFecha.deletePreviousChar();
        }
        else if(TFFecha.getText().length() > 6)
        {
            TFFecha.deletePreviousChar();
        }
    }
}
