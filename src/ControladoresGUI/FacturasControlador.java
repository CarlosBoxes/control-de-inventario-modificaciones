/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;
import AdministradoresGUI.FacturasAdministrador;
import EntidadesJPA.DescripcionFactura;
import EntidadesJPA.DescripcionPedido;
import EntidadesJPA.Facturas;
import Especiales.Validaciones;
import GestorDeTablasJPA.IDescripcionPedido;
import GestorDeTablasJPA.IFacturas;
import Modelos.FacturaModelo;
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
public class FacturasControlador 
{
    FacturasAdministrador FacturasAdministrador;
    @FXML
    public TableView TablaFacturas;
    public TableColumn Numero;
    public TableColumn Serie;
    public TableColumn Nombre;
    public TableColumn Total;
    public TableColumn Anulada;
    public TextField TFBusqueda;
    public Button BtnAnular;
    private ObservableList<FacturaModelo> data;
    private Validaciones validar ;
    
    public void initialize() {}
  
    public void initManager(final FacturasAdministrador FacturasAdministrador)
    {
        this.FacturasAdministrador = FacturasAdministrador;
        data = FXCollections.observableArrayList();
        Numero = new TableColumn();
        Serie = new TableColumn();
        Nombre = new TableColumn();
        Total = new TableColumn();
        Anulada = new TableColumn();
        CargarColumnas();
        LLenarTabla();
        this.validar = new Validaciones();
        if(this.FacturasAdministrador.FinanzasAdministrador.Usuario.getPuesto().equals("Administrador") || this.FacturasAdministrador.FinanzasAdministrador.Usuario.getPuesto().equals("Gerente"))
        {
            BtnAnular.setDisable(false);
        }
        else
        {
            BtnAnular.setDisable(true);
        }
    }
    
    private void CargarColumnas() 
    {
        Numero.setCellValueFactory(new PropertyValueFactory<FacturaModelo,Integer>("Numero"));
        Nombre.setCellValueFactory(new PropertyValueFactory<FacturaModelo,String>("Nombre"));
        Serie.setCellValueFactory(new PropertyValueFactory<FacturaModelo,String>("Serie"));
        Total.setCellValueFactory(new PropertyValueFactory<FacturaModelo,Float>("Total"));
        Anulada.setCellValueFactory(new PropertyValueFactory<FacturaModelo,String>("Anulada"));
        Numero.setText("No. Factura");
        Nombre.setText("Nombre");
        Serie.setText("Serie");
        Total.setText("Total");
        Anulada.setText("Anulada");
        Numero.setPrefWidth(100);
        Nombre.setPrefWidth(300);
        Serie.setPrefWidth(50);
        Total.setPrefWidth(140);
        Anulada.setPrefWidth(60);
        TablaFacturas.getColumns().add(Numero);
        TablaFacturas.getColumns().add(Serie);
        TablaFacturas.getColumns().add(Nombre);
        TablaFacturas.getColumns().add(Total);
        TablaFacturas.getColumns().add(Anulada);
    }
    
    public void LLenarTabla()
    {
        
        for(Facturas Factura: new IFacturas().listaDeFacturas())
        {
            String Anulada = "No";
            if(Factura.getAnulada())
            {
                Anulada = "Si";
            }
            FacturaModelo Modelo = new FacturaModelo(Factura.getNumero(), Factura.getSerie(), Factura.getClientesidCliente().getNombre(), Factura.getTotal(),Anulada);
            data.add(Modelo);
        }
        TablaFacturas.setItems(data);
    }
    
    public void BuscarFactura(ActionEvent event)
    {
        data.clear();
        Facturas Factura = new Facturas();
         if (!this.TFBusqueda.getText().isEmpty())
        {
            if ((validar.ValidarNumeros(this.TFBusqueda.getText())))
            {
                Factura = new IFacturas().buscarFacturaPorNumero(Integer.parseInt(TFBusqueda.getText()));       
            }
            else
            {
                this.FacturasAdministrador.showMensajes("Debe Ingresar un Numero");
            }
            if(Factura == null)
            {
                this.FacturasAdministrador.showMensajes("Factura no Encontrada");
            }
            else
            {
                String Anulada = "No";
                if(Factura.getAnulada())
                {
                    Anulada = "Si";
                }
                FacturaModelo Modelo = new FacturaModelo(Factura.getNumero(), Factura.getSerie(), Factura.getClientesidCliente().getNombre(), Factura.getTotal(),Anulada);
                data.add(Modelo);
                TablaFacturas.setItems(data);
            }
        }
        else 
        {
            LLenarTabla();
        }
    }
    
    public void AnularFactura(ActionEvent event)
    {
        FacturaModelo Seleccionado = (FacturaModelo) TablaFacturas.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.FacturasAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            if(Seleccionado.getAnulada().equals("No"))
            {
                Facturas Factura = new IFacturas().buscarFacturaPorNumero(Seleccionado.getNumero());
                Factura.setAnulada(true);
                new IFacturas().modificar(Factura);
                for(DescripcionFactura Descripcion: Factura.getDescripcionFacturaCollection())
                {
                    DescripcionPedido DP = new IDescripcionPedido().BuscarDescripcion(Factura.getPedidoidPedido(), Descripcion.getProductosidProductos(),Descripcion.getPrecio());
                    int Facturado = DP.getFacturado() - Descripcion.getCantidad();
                    DP.setFacturado(Facturado);
                    new IDescripcionPedido().modificar(DP);
                }
                this.FacturasAdministrador.showMensajes("Factura Anulada");
            }
            else
            {
                this.FacturasAdministrador.showMensajes("La Factura Ya Está Anulada");
            }
        }
    }
}

