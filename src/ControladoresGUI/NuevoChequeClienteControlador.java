/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;
import AdministradoresGUI.NuevoChequeClienteAdministrador;
import EntidadesJPA.Bancos;
import EntidadesJPA.Clientes;
import Especiales.Validaciones;
import GestorDeTablasJPA.IBancos;
import GestorDeTablasJPA.IChequesClientes;
import GestorDeTablasJPA.IClientes;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
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
public class NuevoChequeClienteControlador
{
    
    NuevoChequeClienteAdministrador NuevoChequeClienteAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFMonto;
    public TextField TFNumCheque;
    public TextField TFFecha;
    public TextField TFNombreCliente;
    public ComboBox ComBoBanco;
    private IChequesClientes chequeCliente;
    private Validaciones validar;
    private int ContadorTecla;
    
    
    public void initialize() {}
  
    public void initManager(final NuevoChequeClienteAdministrador NuevoChequeClienteAdministrador) {
        this.NuevoChequeClienteAdminsitrador = NuevoChequeClienteAdministrador;
        this.chequeCliente = new IChequesClientes();        
        this.validar = new Validaciones();
        this.llenarBancos();
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.NuevoChequeClienteAdminsitrador.cerrarNuevoChequeCliente();
    }
    public void Cancelar()
    {
        this.NuevoChequeClienteAdminsitrador.cerrarNuevoChequeCliente();
    }
    
    public void crearNuevoChequeCliente (ActionEvent event)
    {
        Bancos banco = new Bancos ();
        IBancos bancoManejador = new IBancos();
        Clientes cliente = new Clientes();
       
        if ((this.TFMonto.getText().isEmpty())||(!this.validar.ValidarMontos(this.TFMonto.getText())))
        {
            this.NuevoChequeClienteAdminsitrador.showMensajes("Verificar Monto del Cheque");
        }
        else
            if ((this.TFNumCheque.getText().isEmpty())||(!this.validar.ValidarNumeros(this.TFNumCheque.getText())))
            {
                this.NuevoChequeClienteAdminsitrador.showMensajes("Verificar Numero del Cheque");
            }
        else
                if (this.TFNombreCliente.getText().isEmpty())
                {
                    this.NuevoChequeClienteAdminsitrador.showMensajes("Debe ingresar el nombre del Cliente");
                }
        else
                    if ((String)this.ComBoBanco.getValue() == null)
                            {
                                this.NuevoChequeClienteAdminsitrador.showMensajes("Debe Seleccionar un Banco");
                            }
        else
                    {
                            banco = bancoManejador.buscarBancoPorNombre((String)ComBoBanco.getValue());
                            cliente = this.buscarCliente(TFNombreCliente.getText()); 
                            float monto = Float.parseFloat(TFMonto.getText());        
                            int chequeNum = Integer.parseInt(TFNumCheque.getText());
                            String mensaje = "";
                            // metodos para tomar la fecha
                            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
                            java.util.Date fecha = null;
                            try {
                                fecha = df.parse(TFFecha.getText());
                            } catch (ParseException ex) {
                                Logger.getLogger(NuevoProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
                            }  
                            //fin de metodos para fecha

                            if (cliente!=null)
                            {
                                mensaje = chequeCliente.guardar(chequeNum,monto,fecha,banco,cliente);
                                this.NuevoChequeClienteAdminsitrador.showMensajes(mensaje);
                                TFMonto.setText("");
                                TFNumCheque.setText("");
                                TFMonto.requestFocus();
                                TFFecha.setText("");
                                ContadorTecla = 0;

                            }
                            else
                            {
                                this.NuevoChequeClienteAdminsitrador.showMensajes("Cliente No Encontrado");
                            }
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
    
    public Clientes buscarCliente (String nombre)
    {
        Clientes cliente = new Clientes();
        IClientes clienteManejador = new IClientes();
        cliente = clienteManejador.buscarClientesPorNombre(nombre);
        return cliente;
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
