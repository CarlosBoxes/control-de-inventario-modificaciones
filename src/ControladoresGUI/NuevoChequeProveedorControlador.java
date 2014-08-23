/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevoChequeProveedorAdministrador;
import EntidadesJPA.Bancos;
import EntidadesJPA.Proveedores;
import Especiales.Validaciones;
import GestorDeTablasJPA.IBancos;
import GestorDeTablasJPA.IChequesProveedores;
import GestorDeTablasJPA.IProveedores;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class NuevoChequeProveedorControlador
{
    
    NuevoChequeProveedorAdministrador NuevoChequeProveedorAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFMonto;
    public TextField TFNumCheque;
    public TextField TFFecha;
    public TextField TFNombreProveedor;
    public ComboBox ComBoBanco;
    private IChequesProveedores chequeProveedor;
    private Validaciones validar;
    private int ContadorTecla;
    
    
    public void initialize() {}
  
    public void initManager(final NuevoChequeProveedorAdministrador NuevoChequeProveedorAdministrador) {
        this.NuevoChequeProveedorAdminsitrador = NuevoChequeProveedorAdministrador;
        this.chequeProveedor = new IChequesProveedores();        
        this.validar = new Validaciones();
        this.llenarBancos();
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.NuevoChequeProveedorAdminsitrador.cerrarNuevoChequeProveedor();
    }
     public void Cancelar()
    {
        this.NuevoChequeProveedorAdminsitrador.cerrarNuevoChequeProveedor();
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
     
     public Proveedores buscarProveedor (String nombre)
    {
        Proveedores proveedor = new Proveedores();
        IProveedores clienteManejador = new IProveedores();
        proveedor = clienteManejador.buscarProveedorPorNombre(nombre);
        return proveedor;
    }
     
     public void crearNuevoChequeProveedor (ActionEvent event)
    {
        Bancos banco = new Bancos ();
        IBancos bancoManejador = new IBancos();
        Proveedores proveedor = new Proveedores();
        
        if ((this.TFMonto.getText().isEmpty())||(!this.validar.ValidarMontos(this.TFMonto.getText())))
        {
            this.NuevoChequeProveedorAdminsitrador.showMensajes("Verificar Monto del Cheque");
        }
        else
        if ((this.TFNumCheque.getText().isEmpty())||(!this.validar.ValidarNumeros(this.TFNumCheque.getText())))
        {
            this.NuevoChequeProveedorAdminsitrador.showMensajes("Verificar Numero del Cheque");
        }
        else
        if (this.TFNombreProveedor.getText().isEmpty())
        {
            this.NuevoChequeProveedorAdminsitrador.showMensajes("Debe ingresar el nombre del Cliente");
        }
        else
        if ((String)this.ComBoBanco.getValue() == null)
        {
            this.NuevoChequeProveedorAdminsitrador.showMensajes("Debe Seleccionar un Banco");
        }
        else
        {
            banco = bancoManejador.buscarBancoPorNombre((String)ComBoBanco.getValue());
            proveedor = this.buscarProveedor(TFNombreProveedor.getText()); 
            float monto = Float.parseFloat(TFMonto.getText());        
            int chequeNum = Integer.parseInt(TFNumCheque.getText());
            String mensaje = "";
            // metodos para tomar la fecha
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
            Date fecha = null;
            try {
                fecha = df.parse(TFFecha.getText());
            } catch (ParseException ex) {
                Logger.getLogger(NuevoProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }  
            //fin de metodos para fecha

            if (proveedor!=null)
            {
                mensaje = chequeProveedor.guardar(chequeNum,monto,fecha,banco,proveedor);
                this.NuevoChequeProveedorAdminsitrador.showMensajes(mensaje);
                TFMonto.setText("");
                TFNumCheque.setText("");
                TFMonto.requestFocus();
                TFFecha.setText("");
                ContadorTecla = 0;
            }
            else
            {
                this.NuevoChequeProveedorAdminsitrador.showMensajes("Proveedor No Encontrado");
            }
        }
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
