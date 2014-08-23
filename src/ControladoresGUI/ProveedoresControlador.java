/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.ProveedoresAdministrador;
import EntidadesJPA.PedidoProveedores;
import EntidadesJPA.Proveedores;
import GestorDeTablasJPA.IPedidoProveedores;
import GestorDeTablasJPA.IProveedores;
import Modelos.ProveedorModelo;
import Modelos.SaldoModelo;
import java.text.SimpleDateFormat;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;


/**
 *
 * @author luis__000
 */
public class ProveedoresControlador 
{
    ProveedoresAdministrador ProveedoresAdministrador;
    @FXML
    public TableView TablaProveedores;
    public TableView TablaSaldos;
    public TableColumn Id;
    public TableColumn Nombre;
    public TableColumn Nit;
    public TableColumn Direccion;
    public TableColumn Telefono;
    public TableColumn Celular;
    public TableColumn Saldo;
    public TableColumn NoFactura;
    public TableColumn Fecha;
    public TableColumn SaldoFactura;
    public TableColumn IdPedido;
    public TextField TFBusqueda;
    public TextField TFProveedor;
    public TextField TFFactura;
    public TextField TFTotal;
    public HBox HBAbono;
    public TextField TFAbono;
    private ObservableList<ProveedorModelo> data;
    private ObservableList<SaldoModelo> dataSaldo;
    private Proveedores Proveedor;
    private PedidoProveedores PedidoProveedores;
    private float total;
    
    public void initialize() {}
  
    public void initManager(final ProveedoresAdministrador ProveedoresAdministrador)
    {
        this.ProveedoresAdministrador = ProveedoresAdministrador;
        data = FXCollections.observableArrayList();
        dataSaldo = FXCollections.observableArrayList();
        Id = new TableColumn();
        Nombre = new TableColumn();
        Nit = new TableColumn();
        Direccion = new TableColumn();
        Telefono = new TableColumn();
        Celular = new TableColumn();
        Saldo = new TableColumn();
        NoFactura = new TableColumn();
        Fecha = new TableColumn();
        SaldoFactura = new TableColumn();
        IdPedido = new TableColumn();
        total = 0;
        CargarColumnas();
        CargarColumnasSaldos();
    }
    
    public void abrirNuevoProveedor()
    {
        this.ProveedoresAdministrador.showNuevoProveedor();
    }
  
    private void CargarColumnas() 
    {
        Id.setCellValueFactory(new PropertyValueFactory<ProveedorModelo,Integer>("Id"));
        Nombre.setCellValueFactory(new PropertyValueFactory<ProveedorModelo,String>("Nombre"));
        Nit.setCellValueFactory(new PropertyValueFactory<ProveedorModelo,String>("Nit")); 
        Direccion.setCellValueFactory(new PropertyValueFactory<ProveedorModelo,String>("Direccion")); 
        Telefono.setCellValueFactory(new PropertyValueFactory<ProveedorModelo,String>("Telefono")); 
        Celular.setCellValueFactory(new PropertyValueFactory<ProveedorModelo,String>("Celular"));
        Saldo.setCellValueFactory(new PropertyValueFactory<ProveedorModelo,Float>("Saldo"));
        Id.setText("Id");
        Nombre.setText("Nombre");
        Nit.setText("Nit");
        Direccion.setText("Direccion");
        Telefono.setText("Telefono");
        Celular.setText("Celular");
        Saldo.setText("Saldo");
        Id.setPrefWidth(50);
        Nombre.setPrefWidth(200);
        Nit.setPrefWidth(200);
        Direccion.setPrefWidth(300);
        Telefono.setPrefWidth(150);
        Celular.setPrefWidth(150);
        Saldo.setPrefWidth(150);
        TablaProveedores.getColumns().add(Id);
        TablaProveedores.getColumns().add(Nombre);
        TablaProveedores.getColumns().add(Nit);
        TablaProveedores.getColumns().add(Direccion);
        TablaProveedores.getColumns().add(Telefono);
        TablaProveedores.getColumns().add(Celular);
        TablaProveedores.getColumns().add(Saldo);
    }
    
    private void CargarColumnasSaldos() 
    {
        IdPedido.setCellValueFactory(new PropertyValueFactory<ProveedorModelo,Integer>("Id"));
        NoFactura.setCellValueFactory(new PropertyValueFactory<ProveedorModelo,Integer>("NoFactura"));
        Fecha.setCellValueFactory(new PropertyValueFactory<ProveedorModelo,String>("Fecha"));
        SaldoFactura.setCellValueFactory(new PropertyValueFactory<ProveedorModelo,Float>("Saldo")); 
        IdPedido.setText("No Pedido");
        NoFactura.setText("No Factura");
        Fecha.setText("Fecha Vencimiento");
        SaldoFactura.setText("Saldo");
        NoFactura.setPrefWidth(100);
        IdPedido.setPrefWidth(200);
        Fecha.setPrefWidth(200);
        Saldo.setPrefWidth(200);
        TablaSaldos.getColumns().add(IdPedido);
        TablaSaldos.getColumns().add(NoFactura);
        TablaSaldos.getColumns().add(Fecha);
        TablaSaldos.getColumns().add(SaldoFactura);
    }
    
   
 
    public void LLenarTabla()
    {
        total =0;
        if("".equals(TFBusqueda.getText()))
        {
            List<Proveedores> Proveedores = new IProveedores().listaDeProveedores();
            if(Proveedores != null)
            {
                for(Proveedores nuevo: Proveedores)
                {
                    ProveedorModelo ProveedorModelo = new ProveedorModelo(nuevo.getIdProveedores(), nuevo.getNombre(), nuevo.getNit(), nuevo.getDireccion(), nuevo.getTelefono1(), nuevo.getTelefono2(), nuevo.getSaldo());
                    data.add(ProveedorModelo);
                    total = total+nuevo.getSaldo();
                }
                this.TFTotal.setText(String.valueOf(total));
            }
            else
            {
                this.ProveedoresAdministrador.showMensajes("No Existen Proveedores");
            }
        }
        else
        {
            List<Proveedores> Proveedores = new IProveedores().buscarListaProveedorPorNombre(TFBusqueda.getText());
            if(Proveedores != null)
            {
                for(Proveedores nuevo: Proveedores)
                {
                    ProveedorModelo ProveedorModelo = new ProveedorModelo(nuevo.getIdProveedores(), nuevo.getNombre(), nuevo.getNit(), nuevo.getDireccion(), nuevo.getTelefono1(), nuevo.getTelefono2(), nuevo.getSaldo());
                    data.add(ProveedorModelo); 
                    total = total+nuevo.getSaldo();
                }
                this.TFTotal.setText(String.valueOf(total));
            }
            else
            {
                this.ProveedoresAdministrador.showMensajes("No Existen Proveedores que Contenga(n) esa(s) Letra(s)");
            }
        }
    }
    
     public void Buscar(ActionEvent event)
    {
        data.clear();
        TablaProveedores.setItems(data);
        LLenarTabla();
        TablaProveedores.setItems(data);
    }
     
    public void BorrarProveedores(ActionEvent event)
    {
        ProveedorModelo Seleccionado = (ProveedorModelo) TablaProveedores.getSelectionModel().getSelectedItem();
        int indice = TablaProveedores.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.ProveedoresAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            Proveedores Proveedores = new IProveedores().buscarProveedorPorId(Seleccionado.getId());
            Proveedores.setEliminado(true);
            String Mensaje = new IProveedores().eliminar(Proveedores);
            this.ProveedoresAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Proveedor Eliminado Correctamente"))
            {
                data.remove(indice);
                TablaProveedores.setItems(data);
            }
        }
    }
    
    public void EditarProveedor(ActionEvent event)
    {
        ProveedorModelo Seleccionado = (ProveedorModelo)TablaProveedores.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.ProveedoresAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            Proveedores Editar = new Proveedores();
            Editar.setIdProveedores(Seleccionado.getId());
            Editar.setNombre(Seleccionado.getNombre());
            Editar.setNit(Seleccionado.getNit());
            Editar.setDireccion(Seleccionado.getDireccion());
            Editar.setTelefono1(Seleccionado.getTelefono());
            Editar.setTelefono2(Seleccionado.getCelular());
            this.ProveedoresAdministrador.showEditarProveedor(Editar);
        }
    }
    
    public void BusacrDeudas(ActionEvent Event)
    {
        dataSaldo.clear();
        TablaSaldos.setItems(dataSaldo);
        ProveedorModelo Seleccionado = (ProveedorModelo)TablaProveedores.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.ProveedoresAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            Proveedores Proveedores = new IProveedores().buscarProveedorPorId(Seleccionado.getId());
            List<PedidoProveedores> Lista = new IPedidoProveedores().buscarPedidoPorProveedor(Proveedores);
            if(Lista != null)
            {
                for(PedidoProveedores nuevo:  Lista)
                {
                    SimpleDateFormat Formato = new SimpleDateFormat("dd/MM/yyyy");
                    String FechaVencimiento = Formato.format(nuevo.getFechaVencimiento());
                    SaldoModelo Modelo = new SaldoModelo(nuevo.getIdpedidoProveedores(), nuevo.getNoFactura(), FechaVencimiento, nuevo.getSaldo());
                    dataSaldo.add(Modelo);
                }
                Proveedor = new IProveedores().buscarProveedorPorId(Seleccionado.getId());
                TFProveedor.setText(Proveedor.getNombre());
                TablaSaldos.setItems(dataSaldo);
            }
            
        }
    }
    
    public void SeleccionarFactura(ActionEvent e)
    {
        SaldoModelo Seleccionado = (SaldoModelo)TablaSaldos.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.ProveedoresAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            HBAbono.setDisable(false);
            TFFactura.setText(String.valueOf(Seleccionado.getNoFactura()));
            PedidoProveedores = new IPedidoProveedores().buscarPedidoPorId(Seleccionado.getId());
        }
    }
    
    public void Abonar(ActionEvent e)
    {
        Float NuevoSaldoPedido = PedidoProveedores.getSaldo() - Float.parseFloat(TFAbono.getText());
        PedidoProveedores.setSaldo(NuevoSaldoPedido);
        new IPedidoProveedores().modificar(PedidoProveedores);
        Float NuevoSaldoProveedor = Proveedor.getSaldo() - Float.parseFloat(TFAbono.getText());
        Proveedor.setSaldo(NuevoSaldoProveedor);
        new IProveedores().modificar(Proveedor);
        NuevoSaldoProveedor(NuevoSaldoProveedor);
        NuevoSaldoPedido(NuevoSaldoPedido);
        PedidoProveedores = null;
        HBAbono.setDisable(true);
        TFAbono.setText("");
        TFFactura.setText("");
    }
    
    private void NuevoSaldoProveedor(Float NuevoSaldo)
    {
        for(int i = 0; i < data.size();i++)
        {
            if(data.get(i).getId() == Proveedor.getIdProveedores())
            {
                if(NuevoSaldo == 0)
                {
                    this.ProveedoresAdministrador.showMensajes("La Deuda al Proveedor a sido Cancelada");
                }
                else
                {
                    ProveedorModelo Modelo = new ProveedorModelo(Proveedor.getIdProveedores(), Proveedor.getNombre(), Proveedor.getNit(), Proveedor.getDireccion(), Proveedor.getTelefono1(), Proveedor.getTelefono2(), NuevoSaldo);
                    data.set(i, Modelo);
                    TablaProveedores.setItems(data);
                    this.ProveedoresAdministrador.showMensajes("La Deuda al Proveedor es de: Q. "+String.valueOf(NuevoSaldo));
                }
            }
        }
    }
    
    private void NuevoSaldoPedido(Float NuevoSaldo)
    {
        for(int i = 0; i < dataSaldo.size();i++)
        {
            if(dataSaldo.get(i).getId() == PedidoProveedores.getIdpedidoProveedores())
            {
                if(NuevoSaldo == 0)
                {
                    dataSaldo.remove(i);
                    TablaSaldos.setItems(dataSaldo);
                    this.ProveedoresAdministrador.showMensajes("La Factura se a Pagado");
                }
                else
                {
                    SimpleDateFormat Formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    String FechaVencimiento = Formato.format(PedidoProveedores.getFecha());
                    SaldoModelo Modelo = new SaldoModelo(PedidoProveedores.getIdpedidoProveedores(), PedidoProveedores.getNoFactura(), FechaVencimiento, NuevoSaldo);
                    dataSaldo.set(i, Modelo);
                    TablaSaldos.setItems(dataSaldo);
                    this.ProveedoresAdministrador.showMensajes("La Deuda de la Factura es de: Q. "+String.valueOf(NuevoSaldo));
                }
            }
        }
    }
    
    public void Cancelar(ActionEvent Evnet)
    {
        TFAbono.setText("");
        TFProveedor.setText("");
        TFFactura.setText("");
        HBAbono.setDisable(true);
        Proveedor = null;
    }
}

