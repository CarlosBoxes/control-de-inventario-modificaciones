/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarChequeProveedorAdministrador;
import EntidadesJPA.Bancos;
import EntidadesJPA.ChequesProveedores;
import Especiales.Validaciones;
import GestorDeTablasJPA.IBancos;
import GestorDeTablasJPA.IChequesProveedores;
import GestorDeTablasJPA.IProveedores;
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
public class EditarChequeProveedorControlador
{
    
    EditarChequeProveedorAdministrador EditarChequeProveedorAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFMonto;
    public TextField TFNumeroCheque;
    public TextField TFFecha;
    public TextField TFNombreProveedor;
    public ComboBox ComBoBanco; 
    private Validaciones validar;
    private int ContadorTecla;
    
    public void initialize() {}
  
    public void initManager(final EditarChequeProveedorAdministrador EditarChequeProveedorAdministrador) {
        this.EditarChequeProveedorAdminsitrador = EditarChequeProveedorAdministrador;
        this.TFMonto.setText(String.valueOf(EditarChequeProveedorAdministrador.ChequeProveedor.getMonto()));
        this.TFNumeroCheque.setText(String.valueOf(EditarChequeProveedorAdministrador.ChequeProveedor.getNumero()));
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(EditarChequeProveedorAdministrador.ChequeProveedor.getFecha());
        this.TFFecha.setText(fecha);
        this.TFNombreProveedor.setText(EditarChequeProveedorAdministrador.ChequeProveedor.getProveedoresidProveedores().getNombre());
        this.ComBoBanco.setValue(EditarChequeProveedorAdminsitrador.ChequeProveedor.getBancosIdbancos().getNombre());
        this.validar = new Validaciones();
        llenarBancos();
        
    }
    
    public void EditarChequeProveedor(ActionEvent event)
    {
         if ((this.TFMonto.getText().isEmpty())||(!this.validar.ValidarMontos(this.TFMonto.getText())))
        {
            this.EditarChequeProveedorAdminsitrador.showMensajes("Verificar Monto del Cheque");
        }
        else
            if ((this.TFNumeroCheque.getText().isEmpty())||(!this.validar.ValidarNumeros(this.TFNumeroCheque.getText())))
            {
                this.EditarChequeProveedorAdminsitrador.showMensajes("Verificar Numero del Cheque");
            }
        else
                if (this.TFNombreProveedor.getText().isEmpty())
                {
                    this.EditarChequeProveedorAdminsitrador.showMensajes("Debe ingresar el nombre del Cliente");
                }
        else
                    if ((String)this.ComBoBanco.getValue() == null)
                            {
                                this.EditarChequeProveedorAdminsitrador.showMensajes("Debe Seleccionar un Banco");
                            }
        else
                    {
                            IChequesProveedores busqueda = new IChequesProveedores();
                            ChequesProveedores Editar = busqueda.buscarChequesNumeroChueque(this.EditarChequeProveedorAdminsitrador.ChequeProveedor.getNumero());
                            Editar.setMonto(Float.parseFloat(TFMonto.getText()));
                            Editar.setNumero(Integer.parseInt(TFNumeroCheque.getText()));
                            Editar.setProveedoresidProveedores(new IProveedores().buscarProveedorPorNombre(TFNombreProveedor.getText()));
                            Editar.setBancosIdbancos(new IBancos().buscarBancoPorNombre((String)ComBoBanco.getValue()));
                            String Mensaje = busqueda.modificar(Editar);
                            this.EditarChequeProveedorAdminsitrador.showMensajes(Mensaje);
                            this.TFMonto.setText("");
                            this.TFNumeroCheque.setText("");
                            this.TFFecha.setText("");
                            this.ContadorTecla = 0;
                            this.TFNombreProveedor.setText("");
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
        this.EditarChequeProveedorAdminsitrador.cerrarEditarChequeProveedor();
    }
    public void Cancelar()
    {
        this.EditarChequeProveedorAdminsitrador.cerrarEditarChequeProveedor();
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
