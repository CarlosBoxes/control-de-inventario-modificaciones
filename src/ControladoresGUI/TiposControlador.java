/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.TipoAdministrador;
import EntidadesJPA.TipoClientes;
import EntidadesJPA.TipoVendedores;
import GestorDeTablasJPA.ITipoClientes;
import GestorDeTablasJPA.ITipoVendedor;
import Modelos.TipoModelo;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 *
 * @author luis__000
 */
public class TiposControlador 
{
    TipoAdministrador TipoAdministrador;
    @FXML
    public TableView TablaTipoClientes;
    public TableView TablaTipoVendedores;
    public TableColumn IdCliente;
    public TableColumn NombreCliente;
    public TableColumn IdVendedor;
    public TableColumn NombreVendedor;
    public TextField TFBusquedaCliente;
    public TextField TFBusquedaVendedor;
    private ObservableList<TipoModelo> dataCliente;
    private ObservableList<TipoModelo> dataVendedor;
    
    
    public void initialize() {}
  
    public void initManager(final TipoAdministrador TipoVendedorAdministrador)
    {
        this.TipoAdministrador = TipoVendedorAdministrador;
        dataCliente = FXCollections.observableArrayList();
        dataVendedor = FXCollections.observableArrayList();
        IdCliente = new TableColumn();
        NombreCliente = new TableColumn();
        IdVendedor = new TableColumn();
        NombreVendedor = new TableColumn();
        CargarColumnasTipoVendedor();
        CargarColumnasTipoCliente();
    }
    
    public void abrirNuevoTipoVendedor()
    {
        this.TipoAdministrador.showNuevoTipoVendedor();
    }
    
    public void abrirNuevoTipoCliente()
    {
        this.TipoAdministrador.showNuevoTipoCliente();
    }
    
    private void CargarColumnasTipoCliente() 
    {
        IdCliente.setCellValueFactory(new PropertyValueFactory<TipoModelo,Integer>("Id"));
        NombreCliente.setCellValueFactory(new PropertyValueFactory<TipoModelo,String>("Nombre"));
        IdCliente.setText("Id");
        NombreCliente.setText("Nombre");
        IdCliente.setPrefWidth(50);
        NombreCliente.setPrefWidth(200);
        TablaTipoClientes.getColumns().add(IdCliente);
        TablaTipoClientes.getColumns().add(NombreCliente);
    }
    
    private void CargarColumnasTipoVendedor() 
    {
        IdVendedor.setCellValueFactory(new PropertyValueFactory<TipoModelo,Integer>("Id"));
        NombreVendedor.setCellValueFactory(new PropertyValueFactory<TipoModelo,String>("Nombre"));
        IdVendedor.setText("Id");
        NombreVendedor.setText("Nombre");
        IdVendedor.setPrefWidth(50);
        NombreVendedor.setPrefWidth(200);
        TablaTipoVendedores.getColumns().add(IdVendedor);
        TablaTipoVendedores.getColumns().add(NombreVendedor);
    }
    
    public void LLenarTablaTipoClientes()
    {
        if("".equals(TFBusquedaCliente.getText()))
        {
            
            ITipoClientes busqueda = new ITipoClientes();
            List<TipoClientes> TipoClientes = busqueda.listaDeTipoDeClientes();
            if(TipoClientes != null)
            {
                for(TipoClientes nuevo:TipoClientes )
                {
                    TipoModelo TipoClienteModelo = new TipoModelo(nuevo.getIdTipoClientes(), nuevo.getNombre());
                    dataCliente.add(TipoClienteModelo);
                }
            }
            else
            {
                this.TipoAdministrador.showMensajes("No Existen Tipos de Clientes");
            }
        }
        else
        {
            ITipoClientes busqueda = new ITipoClientes();
            List<TipoClientes> TipoClientes = busqueda.buscarListaTipoClientePorNombre(TFBusquedaCliente.getText());
            if(TipoClientes != null)
            {
                for(TipoClientes TipoCliente:TipoClientes)
                {
                    TipoModelo TipoClienteModelo = new TipoModelo(TipoCliente.getIdTipoClientes(), TipoCliente.getNombre());
                    dataCliente.add(TipoClienteModelo);
                }
            }
            else
            {
                this.TipoAdministrador.showMensajes("No Existen Tipos de Clientes que Contenga(n) esa(s) Letra(s)");
            }
        }
    }
    
    public void LLenarTablaTipoVendedores()
    {
        if("".equals(TFBusquedaVendedor.getText()))
        {
            
            ITipoVendedor busqueda = new ITipoVendedor();
            List<TipoVendedores> TipoVendedores = busqueda.listaDeTipoDeVendedores();
            if(TipoVendedores != null)
            {
                for(TipoVendedores nuevo:TipoVendedores )
                {
                    TipoModelo TipoVendedoresModelo = new TipoModelo(nuevo.getIdTipoVendedores(), nuevo.getNombre());
                    dataVendedor.add(TipoVendedoresModelo);
                }
            }
            else
            {
                this.TipoAdministrador.showMensajes("No Existen Tipos de Vendedores");
            }
        }
        else
        {
            ITipoVendedor busqueda = new ITipoVendedor();
            List<TipoVendedores> TipoVendedores = busqueda.buscarListaTipoVendedorPorNombre(TFBusquedaVendedor.getText());
            if(TipoVendedores != null)
            {
                for(TipoVendedores TipoVendedor:TipoVendedores)
                {
                    TipoModelo TipoVendedorModelo = new TipoModelo(TipoVendedor.getIdTipoVendedores(), TipoVendedor.getNombre());
                    dataVendedor.add(TipoVendedorModelo);
                }
            }
            else
            {
                this.TipoAdministrador.showMensajes("No Existen Tipos de Vendedores que Contenga(n) esa(s) Letra(s)");
            }
        }
    }
    
    public void BuscarTipoCliente(ActionEvent event)
    {
        dataCliente.clear();
        TablaTipoClientes.setItems(dataCliente);
        LLenarTablaTipoClientes();
        TablaTipoClientes.setItems(dataCliente);
    }
    
    public void BuscarTipoVendedores(ActionEvent event)
    {
        dataVendedor.clear();
        TablaTipoVendedores.setItems(dataVendedor);
        LLenarTablaTipoVendedores();
        TablaTipoVendedores.setItems(dataVendedor);
    }
    
    public void BorrarTipoCliente(ActionEvent event)
    {
        TipoModelo Seleccionado = (TipoModelo) TablaTipoClientes.getSelectionModel().getSelectedItem();
        int indice = TablaTipoClientes.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.TipoAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            TipoClientes TipoCliente = new ITipoClientes().buscarTipoClientePorId(Seleccionado.getId());
            TipoCliente.setEliminado(true);
            String Mensaje = new ITipoClientes().eliminar(TipoCliente);
            this.TipoAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Tipo de Cliente Eliminado Correctamente"))
            {
                dataCliente.remove(indice);
                TablaTipoClientes.setItems(dataCliente);
            }
        }
    }
    
    public void BorrarTipoVendedor(ActionEvent event)
    {
        TipoModelo Seleccionado = (TipoModelo) TablaTipoVendedores.getSelectionModel().getSelectedItem();
        int indice = TablaTipoVendedores.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.TipoAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            TipoVendedores TipoVendedor = new ITipoVendedor().buscarTipoVendedorPorId(Seleccionado.getId());
            TipoVendedor.setEliminado(true);
            String Mensaje = new ITipoVendedor().eliminar(TipoVendedor);
            this.TipoAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Tipo de Vendedor Eliminado Correctamente"))
            dataVendedor.remove(indice);
            TablaTipoVendedores.setItems(dataVendedor);
        }
    }
    
    public void EditarTipoCliente()
    {
        TipoModelo Seleccionado = (TipoModelo)TablaTipoClientes.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.TipoAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            TipoClientes Editar = new TipoClientes();
            Editar.setIdTipoClientes(Seleccionado.getId());
            Editar.setNombre(Seleccionado.getNombre());
            this.TipoAdministrador.showEditarTipoCliente(Editar);
        }
    }
    
    public void EditarTipoVendedor()
    {
        TipoModelo Seleccionado = (TipoModelo)TablaTipoVendedores.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.TipoAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            TipoVendedores Editar = new TipoVendedores();
            Editar.setIdTipoVendedores(Seleccionado.getId());
            Editar.setNombre(Seleccionado.getNombre());
            this.TipoAdministrador.showEditarTipoVendedor(Editar);
        }
    }
    
    public void AbrirListaProductos(ActionEvent Event)
    {
        TipoModelo Seleccionado = (TipoModelo) TablaTipoClientes.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.TipoAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            TipoClientes TipoCliente = new ITipoClientes().buscarTipoClientePorId(Seleccionado.getId());
            this.TipoAdministrador.showListaClientes(TipoCliente);
        }
    }
    
    public void AbrirListaProductos2(ActionEvent Event)
    {
        TipoModelo Seleccionado = (TipoModelo) TablaTipoVendedores.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.TipoAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            TipoVendedores TipoVendedor = new ITipoVendedor().buscarTipoVendedorPorId(Seleccionado.getId());
            if(TipoVendedor.getListaproductos())
            {
                this.TipoAdministrador.showListaVendedores(TipoVendedor);
            }
            else
            {
                this.TipoAdministrador.showMensajes("Este Tipo No Puede Tener Lista de Productos");
            }
        }
    }
}

