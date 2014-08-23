/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.ChequesAdministrador;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.ChequesProveedores;
import GestorDeTablasJPA.IBancos;
import GestorDeTablasJPA.IChequesClientes;
import GestorDeTablasJPA.IChequesProveedores;
import GestorDeTablasJPA.IClientes;
import GestorDeTablasJPA.IProveedores;
import Modelos.ChequeModelo;
import java.text.SimpleDateFormat;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author luis__000
 */
public class ChequesControlador 
{
    ChequesAdministrador ChequesAdministrador;
    @FXML
    public TableView TablaChequesCliente;
    public TableColumn MontoCliente;
    public TableColumn NumeroChequeCliente;
    public TableColumn FechaCliente;
    public TableColumn Cliente;
    public TableColumn BancoCliente;
    public TextField TFBusquedaChequesCliente;
    private ObservableList<ChequeModelo> dataChequesCliente;
    public TableView TablaChequesProveedor;
    public TableColumn MontoProveedor;
    public TableColumn NumeroChequeProveedor;
    public TableColumn FechaProveedor;
    public TableColumn Proveedor;
    public TableColumn BancoProveedor;
    public TextField TFBusquedaChequesProveedor;
    private ObservableList<ChequeModelo> dataChequesProveedor;
    public Button BtnEliminarP;
    public Button BtnEditarP;
    public Button BtnEliminarC;
    public Button BtnEditarC;
    
    public void initialize() {}
  
    public void initManager(final ChequesAdministrador ChequesAdministrador)
    {
        this.ChequesAdministrador = ChequesAdministrador;
        dataChequesCliente = FXCollections.observableArrayList();
        MontoCliente = new TableColumn();
        NumeroChequeCliente = new TableColumn();
        FechaCliente = new TableColumn();
        Cliente = new TableColumn();
        BancoCliente = new TableColumn();
        CargarColumnasChequesCliente();
        dataChequesProveedor = FXCollections.observableArrayList();
        MontoProveedor = new TableColumn();
        NumeroChequeProveedor = new TableColumn();
        FechaProveedor = new TableColumn();
        Proveedor = new TableColumn();
        BancoProveedor = new TableColumn();
        CargarColumnasChequesProveedor();
        if(this.ChequesAdministrador.Usuario.getPuesto().equals("Administrador") || this.ChequesAdministrador.Usuario.getPuesto().equals("Gerente"))
        {
            BtnEliminarP.setDisable(false);
            BtnEditarP.setDisable(false);
            BtnEliminarC.setDisable(false);
            BtnEditarC.setDisable(false);
        }
        else
        {
            BtnEliminarP.setDisable(true);
            BtnEditarP.setDisable(true);
            BtnEliminarC.setDisable(true);
            BtnEditarC.setDisable(true);
        }
    }
    
    private void CargarColumnasChequesCliente() 
    {
        MontoCliente.setCellValueFactory(new PropertyValueFactory<ChequeModelo,Float>("Monto"));
        NumeroChequeCliente.setCellValueFactory(new PropertyValueFactory<ChequeModelo,Integer>("NumeroCheque"));
        Cliente.setCellValueFactory(new PropertyValueFactory<ChequeModelo,String>("Nombre"));
        FechaCliente.setCellValueFactory(new PropertyValueFactory<ChequeModelo,String>("Fecha"));
        BancoCliente.setCellValueFactory(new PropertyValueFactory<ChequeModelo,String>("Banco"));
        MontoCliente.setText("Monto");
        NumeroChequeCliente.setText("No. de Cheque");
        Cliente.setText("Cliente");
        FechaCliente.setText("Fecha");
        BancoCliente.setText("Banco");
        MontoCliente.setPrefWidth(100);
        NumeroChequeCliente.setPrefWidth(100);
        Cliente.setPrefWidth(200);
        FechaCliente.setPrefWidth(100);
        BancoCliente.setPrefWidth(100);
        TablaChequesCliente.getColumns().add(NumeroChequeCliente);
        TablaChequesCliente.getColumns().add(Cliente);
        TablaChequesCliente.getColumns().add(MontoCliente);
        TablaChequesCliente.getColumns().add(FechaCliente);
        TablaChequesCliente.getColumns().add(BancoCliente);
    }
    
    private void CargarColumnasChequesProveedor() 
    {
        MontoProveedor.setCellValueFactory(new PropertyValueFactory<ChequeModelo,Float>("Monto"));
        NumeroChequeProveedor.setCellValueFactory(new PropertyValueFactory<ChequeModelo,Integer>("NumeroCheque"));
        Proveedor.setCellValueFactory(new PropertyValueFactory<ChequeModelo,String>("Nombre"));
        FechaProveedor.setCellValueFactory(new PropertyValueFactory<ChequeModelo,String>("Fecha"));
        BancoProveedor.setCellValueFactory(new PropertyValueFactory<ChequeModelo,String>("Banco"));
        MontoProveedor.setText("Monto");
        NumeroChequeProveedor.setText("No. de Cheque");
        Proveedor.setText("Proveedor");
        FechaProveedor.setText("Fecha");
        BancoProveedor.setText("Banco");
        MontoProveedor.setPrefWidth(100);
        NumeroChequeProveedor.setPrefWidth(100);
        Proveedor.setPrefWidth(200);
        FechaProveedor.setPrefWidth(100);
        BancoProveedor.setPrefWidth(100);
        TablaChequesProveedor.getColumns().add(NumeroChequeProveedor);
        TablaChequesProveedor.getColumns().add(Proveedor);
        TablaChequesProveedor.getColumns().add(MontoProveedor);
        TablaChequesProveedor.getColumns().add(FechaProveedor);
        TablaChequesProveedor.getColumns().add(BancoProveedor);
    }
    
    public void LLenarTablaChequesCliente()
    {
        if("".equals(TFBusquedaChequesCliente.getText()))
        {
            
            IChequesClientes busqueda = new IChequesClientes ();
            List<ChequesClientes> Cheque = busqueda.retornarListaDeChequesClientes();
            if(Cheque != null)
            {
                for(ChequesClientes nuevo:Cheque )
                {
                    SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    String fecha = formato.format(nuevo.getFecha());
                    ChequeModelo ChequeModelo = new ChequeModelo(nuevo.getMonto(), nuevo.getNumero(), fecha, nuevo.getClientesidCliente().getNombre(), nuevo.getBancosIdbancos().getNombre());
                    dataChequesCliente.add(ChequeModelo);
                }
            }
            else
            {
                this.ChequesAdministrador.showMensajes("No Existen Cheques");
            }
        }
        else
        {
            IChequesClientes busqueda = new IChequesClientes ();            
            ChequesClientes Cheque = busqueda.buscarChequesNumeroChueque(Integer.parseInt(TFBusquedaChequesCliente.getText()));
            if (Cheque == null)
            {
                this.ChequesAdministrador.showMensajes("No Existen Cheques con ese Numero");
            }
            else
            {
                ChequeModelo TipoClienteModelo = new ChequeModelo(Cheque.getMonto(), Cheque.getNumero(), Cheque.getFecha().getDay()+"/"+Cheque.getFecha().getMonth()+"/"+Cheque.getFecha().getYear(), Cheque.getClientesidCliente().getNombre(), Cheque.getBancosIdbancos().getNombre());
                dataChequesCliente.add(TipoClienteModelo);
            }
        }
    }
    
    public void LLenarTablaChequesProveedor()
    {
        if("".equals(TFBusquedaChequesProveedor.getText()))
        {
            
            IChequesProveedores busqueda = new IChequesProveedores ();
            List<ChequesProveedores> Cheque = busqueda.retornarListaDeChequesProveedores();
            if(Cheque != null)
            {
                for(ChequesProveedores nuevo:Cheque )
                {
                    java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    String fecha = formato.format(nuevo.getFecha());
                    ChequeModelo ChequesModelo = new ChequeModelo(nuevo.getMonto(), nuevo.getNumero(), fecha, nuevo.getProveedoresidProveedores().getNombre(), nuevo.getBancosIdbancos().getNombre());
                    dataChequesProveedor.add(ChequesModelo);
                }
            }
            else
            {
                this.ChequesAdministrador.showMensajes("No Existen Cheques");
            }
        }
        else
        {
            IChequesProveedores busqueda = new IChequesProveedores ();
            ChequesProveedores Cheque = busqueda.buscarChequesNumeroChueque(Integer.parseInt(TFBusquedaChequesCliente.getText()));
            if (Cheque == null)
            {
                this.ChequesAdministrador.showMensajes("No Existen Cheques con ese Numero");
            }
            else
            {
                ChequeModelo TipoClienteModelo = new ChequeModelo(Cheque.getMonto(), Cheque.getNumero(), Cheque.getFecha().getDay()+"/"+Cheque.getFecha().getMonth()+"/"+Cheque.getFecha().getYear(), Cheque.getProveedoresidProveedores().getNombre(), Cheque.getBancosIdbancos().getNombre());
                dataChequesProveedor.add(TipoClienteModelo);
            }
        }
    }
    
    public void BuscarChequesCliente(ActionEvent event)
    {
        
            dataChequesCliente.clear();
            TablaChequesCliente.setItems(dataChequesCliente);
            LLenarTablaChequesCliente();
            TablaChequesCliente.setItems(dataChequesCliente);
        
    }
    
    public void BuscarChequesProveedor(ActionEvent event)
    {
        
            dataChequesProveedor.clear();
            TablaChequesProveedor.setItems(dataChequesProveedor);
            LLenarTablaChequesProveedor();
            TablaChequesProveedor.setItems(dataChequesProveedor);
        
    }
    
    public void BorrarChequeCliente(ActionEvent event)
    {
        ChequeModelo Seleccionado = (ChequeModelo)TablaChequesCliente.getSelectionModel().getSelectedItem();
        int indice = TablaChequesCliente.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.ChequesAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            ChequesClientes Cheque = new IChequesClientes().buscarChequesNumeroChueque(Seleccionado.getNumeroCheque());
            Cheque.setEliminado(true);
            String Mensaje = new IChequesClientes().elimiar(Cheque);
            this.ChequesAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Cheque Eliminado Correctamente"))
            {
                dataChequesCliente.remove(indice);
                TablaChequesCliente.setItems(dataChequesCliente);
            }
        }
    }
    
    public void BorrarChequeProveedor(ActionEvent event)
    {
        ChequeModelo Seleccionado = (ChequeModelo)TablaChequesProveedor.getSelectionModel().getSelectedItem();
        int indice = TablaChequesProveedor.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.ChequesAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            ChequesProveedores Cheque = new IChequesProveedores().buscarChequesNumeroChueque(Seleccionado.getNumeroCheque());
            Cheque.setEliminado(true);
            String Mensaje = new IChequesProveedores().eliminar(Cheque);
            this.ChequesAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Cheque Eliminado Correctamente"))
            {
                dataChequesCliente.remove(indice);
                TablaChequesCliente.setItems(dataChequesCliente);
            }
        }
    }
    
    public void abrirNuevoChequeCliente()
    {
        if(new IBancos().retornarListaDeBancos() != null && new IClientes().listaDeClientes() != null)
        {
            this.ChequesAdministrador.showNuevoChequeCliente();
        }
        else if(new IBancos().retornarListaDeBancos() == null && new IClientes().listaDeClientes() != null)
        {
            this.ChequesAdministrador.showMensajes("No hay Bancos Ingresados, Diríjase a Bancos");
        }
        else if(new IBancos().retornarListaDeBancos() != null && new IClientes().listaDeClientes() == null)
        {
            this.ChequesAdministrador.showMensajes("No hay Clientes Ingresados, Diríjase a Clientes");
        }
    }
    
    public void abrirNuevoChequeProveedor()
    {
        if(new IBancos().retornarListaDeBancos() != null && new IProveedores().listaDeProveedores() != null)
        {
            this.ChequesAdministrador.showNuevoChequeProveedor();
        }
        else if(new IBancos().retornarListaDeBancos() == null && new IProveedores().listaDeProveedores() != null)
        {
            this.ChequesAdministrador.showMensajes("No hay Bancos Ingresados, Diríjase a Bancos");
        }
        else if(new IBancos().retornarListaDeBancos() != null && new IProveedores().listaDeProveedores() == null)
        {
            this.ChequesAdministrador.showMensajes("No hay Proveedores Ingresados, Diríjase a Proveedores");
        }
        
    }
    
    public void EditarChequeCliente(ActionEvent Event)
    {
        ChequeModelo Seleccionado = (ChequeModelo)TablaChequesCliente.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.ChequesAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            ChequesClientes Editar = new ChequesClientes();
            Editar = new IChequesClientes().buscarChequesNumeroChueque(Seleccionado.getNumeroCheque());
            this.ChequesAdministrador.showEditarChequeCliente(Editar);
        }
    }
    
    public void EditarChequeProveedor(ActionEvent Event)
    {
        ChequeModelo Seleccionado = (ChequeModelo)TablaChequesProveedor.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.ChequesAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            ChequesProveedores Editar = new ChequesProveedores();
            Editar = new IChequesProveedores().buscarChequesNumeroChueque(Seleccionado.getNumeroCheque());
            this.ChequesAdministrador.showEditarChequeProveedor(Editar);
        }
    }

    
}

