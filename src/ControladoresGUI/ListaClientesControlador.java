/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.ListaClientesAdministrador;
import EdicionTablas.EditarPrecio;
import EntidadesJPA.Clientes;
import EntidadesJPA.ListaClientes;
import Especiales.Validaciones;
import GestorDeTablasJPA.IClientes;
import GestorDeTablasJPA.IListaClientes;
import Modelos.ListaClientesModelo;
import Modelos.ListaProductosModelo;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class ListaClientesControlador
{
    
    ListaClientesAdministrador Administrador;
    @FXML
    public TextField TFId;
    public TableView TablaLista;
    public TableColumn Id;
    public TableColumn Cliente;
    private ObservableList<ListaClientesModelo> data;
    private Validaciones Validar;
    Callback<TableColumn, TableCell> cellFactory;

    public void initialize() {}
  
    public void initManager(final ListaClientesAdministrador ListaClientesAdministrador) {
        this.Administrador = ListaClientesAdministrador;
        this.Id = new TableColumn();
        this.Cliente = new TableColumn();
        this.data = FXCollections.observableArrayList();
        Validar = new Validaciones();
        cellFactory = new Callback<TableColumn, TableCell>() 
        {
            public TableCell call(TableColumn p) {
                return new EditarPrecio();
            }
	};
        CargarColumnas();
        LlenarTabla();
    }
    
    private void CargarColumnas()
    {
        Id.setCellValueFactory(new PropertyValueFactory<ListaProductosModelo,Integer>("IdCliente"));
        Cliente.setCellValueFactory(new PropertyValueFactory<ListaProductosModelo,String>("Nombre"));
        Id.setText("Id");
        Cliente.setText("Nombre");
        Id.setPrefWidth(50);
        Cliente.setPrefWidth(400);
        TablaLista.getColumns().add(Id);
        TablaLista.getColumns().add(Cliente);
        TFId.requestFocus();
    }
    
    private void LlenarTabla()
    {
       if(!this.Administrador.Vendedor.getListaclientesCollection().isEmpty())
       {
           for(ListaClientes Lista:this.Administrador.Vendedor.getListaclientesCollection())
           {
               ListaClientesModelo Clientes = new ListaClientesModelo(Lista.getIdlistaclientes(), Lista.getClientesidCliente().getIdCliente(), Lista.getClientesidCliente().getNombre());
               data.add(Clientes);
           }
       }
       TablaLista.setItems(data);
    }
    
    public void AgregarCliente(ActionEvent event)
    {
        if(Validar.ValidarNumeros(TFId.getText()))
        {
            if(VerificarCliente(TFId.getText()))
            {
                Clientes Cliente = new IClientes().buscarClientesPorId(Integer.parseInt(TFId.getText()));
                ListaClientesModelo Modelo = new ListaClientesModelo(0, Cliente.getIdCliente(), Cliente.getNombre());
                data.add(Modelo);
                TablaLista.setItems(data);
                TFId.setText("");
                TFId.requestFocus();
            }
            else
            {
                this.Administrador.showMensajes("El Producto ya está en la Lista");
                TFId.requestFocus();
            }
        }
        else if(!Validar.ValidarNumeros(TFId.getText()))
        {
            this.Administrador.showMensajes("El Id No es Valido");
        }
    }
    
    private boolean VerificarCliente(String Id)
    {
        for(ListaClientesModelo Lista: data)
        {
            if(Integer.parseInt(Id) == Lista.getIdCliente())
            {
                return false;
            }
        }
        return true;
    }
    
    public void Guardar(ActionEvent event)
    {
        List<ListaClientes> Guardar = new ArrayList();
        for(ListaClientesModelo Lista: data)
        {
            if (!this.datosNuevo(Lista.getIdCliente()))
            {
            if(Lista.getId() == 0)
            {
                ListaClientes Clientes = new ListaClientes();
                Clientes Cliente = new IClientes().buscarClientesPorId(Lista.getIdCliente());
                Clientes.setClientesidCliente(Cliente);
                Clientes.setVendedoresIdvendedores(this.Administrador.Vendedor);
                Guardar.add(Clientes);
            }
            }
        }
        new IListaClientes().guardar(Guardar);
        this.Administrador.showMensajes("Lista de Clientes Guardada");
    }
    
    public void Eliminar(ActionEvent event)
    {
        try
        {
        ListaClientesModelo Seleccionado = (ListaClientesModelo) TablaLista.getSelectionModel().getSelectedItem();
        int indice = TablaLista.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.Administrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            if(Seleccionado.getId() > 0)
            {
                String Mensaje = new IListaClientes().eliminar(Seleccionado.getId());
                this.Administrador.showMensajes(Mensaje);
                if(Mensaje.equals("Cliente Eliminado Correctamente"))
                {
                    data.remove(indice);
                    TablaLista.setItems(data);
                }
            }
            else
            {
                data.remove(indice);
                TablaLista.setItems(data);
            }
        }      
        }
           catch (Exception e)
                {
                    System.out.println(e);
                }
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.Administrador.cerrarListaClientes();
    }
    
    public boolean datosNuevo(int idCliente)
    {
        for(ListaClientes Lista:this.Administrador.Vendedor.getListaclientesCollection())
           {
               if (Lista.getClientesidCliente().getIdCliente()==idCliente)
               {
                   return true;
               }
           }
        return false;
    }
}
