/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.RecuperarContraseñasAdministrador;
import EntidadesJPA.Pedido;
import EntidadesJPA.PedidoProveedores;
import EntidadesJPA.Usuario;
import Especiales.EliminarPedido;
import GestorDeTablasJPA.IPedido;
import GestorDeTablasJPA.IPedidoProveedores;
import GestorDeTablasJPA.IUsuarios;
import Modelos.UsuarioModelo;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author luis__000
 */
public class RecuperarContraseñasControlador 
{
    RecuperarContraseñasAdministrador RecuperasrContraseñasAdministrador;
    Dimension TamañoVentana = Toolkit.getDefaultToolkit().getScreenSize();
     
    @FXML
    public VBox pnlPrincipal;
    public TableView TablaUsuarios;
    public TableColumn Id;
    public TableColumn Nombre;
    public TableColumn Tipo;
    private ObservableList<UsuarioModelo> data;
    public TextField TFBusqueda;
    public TextField TFIdPedido;
    public TextField TFTotalPedido;
    public Label LBLVendedor;
    public Button BtnBorrar;
    public CheckBox CBTipo;
    
    IPedido pedidoGestor = new IPedido ();
    IPedidoProveedores pedidoProveedorGestor = new IPedidoProveedores ();
    
    
    
    public void initialize() {}
  
    public void initManager(final RecuperarContraseñasAdministrador RecuperarContraseñasAdministrador)
    {
        this.RecuperasrContraseñasAdministrador = RecuperarContraseñasAdministrador;
        this.pnlPrincipal.setPrefSize(TamañoVentana.getWidth()-180, TamañoVentana.getWidth()-90);
        data = FXCollections.observableArrayList();
        Id = new TableColumn();
        Nombre = new TableColumn();
        Tipo = new TableColumn();
        CargarColumnas();     
    }
    
    public void abrirNuevoUsuario()
    {
        this.RecuperasrContraseñasAdministrador.showNuevoUsuarios();
    }
    
    public void LLenarTabla()
    {
        if("".equals(TFBusqueda.getText()))
        {
            
            IUsuarios busqueda = new IUsuarios();
            List<Usuario> Usuarios = busqueda.listaDeUsuarios();
            for(Usuario nuevo:Usuarios )
            {
                UsuarioModelo UsuarioModelo = new UsuarioModelo(nuevo.getIdUsuario(),nuevo.getNombre(),nuevo.getPuesto());
                data.add(UsuarioModelo);
            }
        }
        else
        {
            IUsuarios busqueda = new IUsuarios();
            List<Usuario> Usuarios = busqueda.buscarListaUsuarioPorNombre(TFBusqueda.getText());
            if(Usuarios != null)
            {
                for(Usuario Usuario:Usuarios)
                {
                    UsuarioModelo UsuarioModelo = new UsuarioModelo(Usuario.getIdUsuario(),Usuario.getNombre(),Usuario.getPuesto());
                    data.add(UsuarioModelo);
                }
            }
        }
    }
    
    private void CargarColumnas()
    {
        Id.setCellValueFactory(new PropertyValueFactory<UsuarioModelo,Integer>("Id"));
        Nombre.setCellValueFactory(new PropertyValueFactory<UsuarioModelo,String>("Nombre"));
        Tipo.setCellValueFactory(new PropertyValueFactory<UsuarioModelo,String>("Tipo"));
        Id.setText("Id");
        Nombre.setText("Nombre");
        Tipo.setText("Tipo");
        Id.setPrefWidth(50);
        Nombre.setPrefWidth(200);
        Tipo.setPrefWidth(200);
        TablaUsuarios.getColumns().add(Id);
        TablaUsuarios.getColumns().add(Nombre);
        TablaUsuarios.getColumns().add(Tipo);
    }
    
    public void Buscar(ActionEvent event)
    {
        data.clear();
        TablaUsuarios.setItems(data);
        LLenarTabla();
        TablaUsuarios.setItems(data);
    }
    
    public void BorrarUsuario(ActionEvent event)
    {
        UsuarioModelo Seleccionado = (UsuarioModelo) TablaUsuarios.getSelectionModel().getSelectedItem();
        int indice = TablaUsuarios.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.RecuperasrContraseñasAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            IUsuarios borrar = new IUsuarios();
            String Mensaje = borrar.eliminar(Seleccionado.getId());
            this.RecuperasrContraseñasAdministrador.showMensajes(Mensaje);
            data.remove(indice);
            TablaUsuarios.setItems(data);
        }
    }
    
    public void EditarUsuario(ActionEvent event)
    {
        UsuarioModelo Seleccionado = (UsuarioModelo)TablaUsuarios.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.RecuperasrContraseñasAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            Usuario Editar = new Usuario();
            Editar.setIdUsuario(Seleccionado.getId());
            Editar.setNombre(Seleccionado.getNombre());
            Editar.setPuesto(Seleccionado.getTipo());
            this.RecuperasrContraseñasAdministrador.showEditarUsuarios(Editar);
        }
    }
    
    public void BuscarPedido (ActionEvent event)
    {
        boolean bandera = false;
        if (this.CBTipo.isSelected())
        {
            bandera = true;
        }
        if (this.TFIdPedido.getText().isEmpty())
        {
            this.RecuperasrContraseñasAdministrador.showMensajes("Ingrese ID del Pedido");
        }
        else
        {
            if (bandera == false)
            {
                Pedido pedido = new Pedido ();
                pedido  = pedidoGestor.buscarPedidoPorId(Integer.parseInt(this.TFIdPedido.getText()));
                if (pedido==null)
                {
                    this.RecuperasrContraseñasAdministrador.showMensajes("Pedido No Encontrado, Verifique Id.");
                    this.LBLVendedor.setText("");
                    this.TFTotalPedido.setText("");
                }
                else
                if (pedido.getLiquidado())
                {
                    this.RecuperasrContraseñasAdministrador.showMensajes("Pedido Ya fue liquidado.");
                    this.LBLVendedor.setText("");
                    this.TFTotalPedido.setText("");
                }
                else
                {    
                    this.LBLVendedor.setText(pedido.getVendedoresIdvendedores().getNombre());
                    this.TFTotalPedido.setText(String.valueOf(pedido.getTotal()));
                    BtnBorrar.setDisable(false);                
                }
            }
            else
            {
                PedidoProveedores pedido = new PedidoProveedores ();
                pedido  = pedidoProveedorGestor.buscarPedidoPorId(Integer.parseInt(this.TFIdPedido.getText()));
                if (pedido==null)
                {
                    this.RecuperasrContraseñasAdministrador.showMensajes("Pedido No Encontrado, Verifique Id.");
                    this.LBLVendedor.setText("");
                    this.TFTotalPedido.setText("");
                }
                else
                if (pedido.getAlmacenado()==true)
                {
                    this.RecuperasrContraseñasAdministrador.showMensajes("Pedido ya fue Almacenado.");
                    this.LBLVendedor.setText("");
                    this.TFTotalPedido.setText("");
                }
                else
                {            

                    this.LBLVendedor.setText(pedido.getProveedoresidProveedores().getNombre());
                    this.TFTotalPedido.setText(String.valueOf(pedido.getTotal()));
                    BtnBorrar.setDisable(false);                
                }
            }
        }
    }
    
    public void EliminarPedido (ActionEvent event)
    {
        EliminarPedido eliminarP = new EliminarPedido ();
        if (!this.CBTipo.isSelected())
        {
            this.RecuperasrContraseñasAdministrador.showMensajes( eliminarP.eliminarPedido(Integer.parseInt(this.TFIdPedido.getText())));
        }
        else
        {
            this.RecuperasrContraseñasAdministrador.showMensajes(eliminarP.elimnarPedidoProveedor(Integer.parseInt(this.TFIdPedido.getText())));
        }
    }
}

