/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevaLiquidacionAdministrador;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.Depositos;
import EntidadesJPA.Productos;
import Especiales.GeneradordeReportes;
import Especiales.NuevaLiquidacionVariante;
import Especiales.ProductoACambiar;
import Especiales.Validaciones;
import GestorDeTablasJPA.IChequesClientes;
import GestorDeTablasJPA.IDepositos;
import GestorDeTablasJPA.ILiquidacion;
import GestorDeTablasJPA.IPedido;
import GestorDeTablasJPA.IProductos;
import Modelos.DefectuososModelo;
import Modelos.DescripcionLiquidacionModelo;
import Modelos.DevolucionModelo;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 *
 * @author luis__000
 */
public class NuevaLiquidacionControlador 
{
    NuevaLiquidacionAdministrador NuevaLiquidacionAdministrador;
    @FXML
    public TableView TablaDescripciones;
    public TableView TablaDevoluciones;
    public TableView TablaDefectuosos;
    
    public TableColumn NumeroBoleta;
    public TableColumn NumeroCheque;
    public TableColumn Monto;
    
    public TableColumn Producto;
    
       public TableColumn Nombre;
    public TableColumn CantidadD;
    public TableColumn MontoD;
    public TableColumn NoD;
    
    public TableColumn Cantidad;
    public TableColumn Descripcion;
    
    
    public TextField TFNumeroCheque;
    public TextField TFNumeroBoleta;
    public TextField TFIdLiquidacion;
    public TextField TFSubTotal;
    public TextField TFTotal;
    public TextField TFSaldoAnterior;
    public TextField TFLiquidacion;
    public TextField TFCantidad2;
    public TextField TFSaldoTotal;
    public TextField TFSaldoAnteriorNuevo;
    public TextField TFProducto;
    public TextField TFCantidad;
    public TextArea TADescripcion;
    public TextField TFOtro;
    public TextField TFISR;
    public TextField TFTotalContraseñas;
    public Label TFNombreVendedor;
    public Button BtnDevolucion;
    
    public Validaciones validar ;
    public IPedido pedido;
    
    public ObservableList<DescripcionLiquidacionModelo> data;
    public ObservableList<DevolucionModelo> dataDevolucion;
    public ObservableList<DefectuososModelo> dataDefectuosos;
    public NuevaLiquidacionVariante nuevaLiquidacionVariante;
    
    
    public void initialize() {}
  
    public void initManager(final NuevaLiquidacionAdministrador NuevaLiquidacionAdministrador)
    {
        this.NuevaLiquidacionAdministrador = NuevaLiquidacionAdministrador;
        this.nuevaLiquidacionVariante = new NuevaLiquidacionVariante();
        data = FXCollections.observableArrayList();
        dataDevolucion = FXCollections.observableArrayList();
        dataDefectuosos = FXCollections.observableArrayList();
        NumeroBoleta = new TableColumn();
        NumeroCheque = new TableColumn();
        Monto = new TableColumn();
        
        Nombre = new TableColumn();      
        CantidadD = new TableColumn();
        MontoD = new TableColumn();
        NoD = new TableColumn();

        Producto = new TableColumn();    
        Cantidad = new TableColumn();
        Descripcion = new TableColumn();
        this.pedido = new IPedido ();
        this.validar = new Validaciones();
        CargarColumnas();
        CargarColumnasDevolucion();
        CargarColumnasDefectuosos();
    }
    
    private void CargarColumnas() 
    {
        NumeroBoleta.setCellValueFactory(new PropertyValueFactory<DescripcionLiquidacionModelo,String>("NumeroBoleta"));
        NumeroCheque.setCellValueFactory(new PropertyValueFactory<DescripcionLiquidacionModelo,String>("NumeroCheque"));
        Monto.setCellValueFactory(new PropertyValueFactory<DescripcionLiquidacionModelo,Float>("Monto")); 
        NumeroBoleta.setText("Numero Boleta");
        NumeroCheque.setText("Numero Cheque");
        Monto.setText("Monto");
        NumeroBoleta.setPrefWidth(200);
        NumeroCheque.setPrefWidth(200);
        Monto.setPrefWidth(200);
        TablaDescripciones.getColumns().add(NumeroBoleta);
        TablaDescripciones.getColumns().add(NumeroCheque);
        TablaDescripciones.getColumns().add(Monto);
    }
    
    private void CargarColumnasDevolucion()
    {
        NoD.setCellValueFactory(new PropertyValueFactory<DevolucionModelo,String>("No"));
        NoD.setText("No.");
        NoD.setPrefWidth(40);
        TablaDevoluciones.getColumns().add(NoD);
        
        Nombre.setCellValueFactory(new PropertyValueFactory<DevolucionModelo,String>("Nombre"));
        Nombre.setText("Nombre");
        Nombre.setPrefWidth(152);
        TablaDevoluciones.getColumns().add(Nombre);
        
        MontoD.setCellValueFactory(new PropertyValueFactory<DevolucionModelo,String>("Monto"));
        MontoD.setText("Monto (Q.)");
        MontoD.setPrefWidth(152);
        TablaDevoluciones.getColumns().add(MontoD);
        
        CantidadD.setCellValueFactory(new PropertyValueFactory<DevolucionModelo,String>("Cantidad"));
        CantidadD.setText("Cantidad");
        CantidadD.setPrefWidth(152);
        TablaDevoluciones.getColumns().add(CantidadD);
    }
    
    private void CargarColumnasDefectuosos()
    {
        Producto.setCellValueFactory(new PropertyValueFactory<DefectuososModelo,String>("Nombre"));
        Cantidad.setCellValueFactory(new PropertyValueFactory<DefectuososModelo,Integer>("Cantidad"));
        Descripcion.setCellValueFactory(new PropertyValueFactory<DefectuososModelo,String>("Descripcion"));
        Producto.setText("Nombre");
        Cantidad.setText("Cantidad");
        Descripcion.setText("Descripcion");
        Producto.setPrefWidth(300);
        Cantidad.setPrefWidth(100);
        Descripcion.setPrefWidth(300);
        TablaDefectuosos.getColumns().add(Producto);
        TablaDefectuosos.getColumns().add(Cantidad);
        TablaDefectuosos.getColumns().add(Descripcion);
    }
    
    public void LLenarTablaDescripcionCheque()
    {
        IChequesClientes busqueda = new IChequesClientes();
        ChequesClientes Cheques = busqueda.buscarChequesNumeroChueque(Integer.parseInt(TFNumeroCheque.getText()));
        DescripcionLiquidacionModelo DescripcionModelo = new DescripcionLiquidacionModelo(null,String.valueOf(Cheques.getNumero()),Cheques.getMonto());
        data.add(DescripcionModelo);
    }
    
    public void AgregarCheque(ActionEvent event)
    {
        IChequesClientes busqueda = new IChequesClientes();
        if (!this.validar.ValidarNumeros(this.TFNumeroCheque.getText())||(busqueda.buscarChequesNumeroChueque(Integer.parseInt(this.TFNumeroCheque.getText()))==null))
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Verifique numero de Cheque");
        }
        else
        if (this.verificarChequeIngresado(this.TFNumeroCheque.getText()))
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Este Cheque Ya Fue Ingresado");
        }
        else
        if (this.verificarChequeIngresado(this.TFNumeroCheque.getText()))
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Este Cheque Ya Fue Ingresado");
        }
        else
            if(busqueda.buscarChequesNumeroChueque(Integer.parseInt(this.TFNumeroCheque.getText())).getUsado())
            {
                this.NuevaLiquidacionAdministrador.showMensajes("Este Cheque Ya Fue Usado En Una Liquidación");
            }
        else
        {
            LLenarTablaDescripcionCheque();
            TablaDescripciones.setItems(data);
            this.generarLiquidacion();
            this.verificacionDeCuadre ();
        }
    }
    
    private void LLenarTablaDescripcionDeposito()
    {
        IDepositos busqueda = new IDepositos();
        Depositos Deposito = busqueda.buscarDepositoPorNumeroBoleta(TFNumeroBoleta.getText());
        DescripcionLiquidacionModelo DescripcionModelo = new DescripcionLiquidacionModelo(Deposito.getNumeroDeBoleta(),null,Deposito.getMonto());
        data.add(DescripcionModelo);
    }
    
    public void AgregarDeposito(ActionEvent event)
    {
        IDepositos busqueda = new IDepositos();
        if (!this.validar.ValidarNumeros(this.TFNumeroBoleta.getText())||(busqueda.buscarDepositoPorNumeroBoleta(this.TFNumeroBoleta.getText())==null))
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Verifique numero de Boleta");
        }
        else
        if (this.verificarDepositoIngresado(this.TFNumeroBoleta.getText()))
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Este Deposito Ya fue Ingresado");
        }
        else
            if (busqueda.buscarDepositoPorNumeroBoleta(this.TFNumeroBoleta.getText()).getUsado())
            {
                 this.NuevaLiquidacionAdministrador.showMensajes("Este Deposito Ya fue Usado En Una Liquidación");
            }
        else
        {
            LLenarTablaDescripcionDeposito();
            TablaDescripciones.setItems(data);
            this.generarLiquidacion();
            this.verificacionDeCuadre ();
        }
        
    }
    
    public void SacarDecripcion(ActionEvent event)
    {
        DescripcionLiquidacionModelo Seleccionado = (DescripcionLiquidacionModelo)TablaDescripciones.getSelectionModel().getSelectedItem();
        int indice = TablaDescripciones.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            data.remove(indice);
            TablaDescripciones.setItems(data);
            this.generarLiquidacion();
            this.verificacionDeCuadre ();
        }
        
    }
    
    public void verificacionDeCuadre ()
    {
        float saldoTotal = Float.parseFloat(this.TFSaldoTotal.getText());
        float saldoNuevo = 0;
        float subLiquidacion = Float.parseFloat(this.TFLiquidacion.getText());
        if (subLiquidacion==saldoTotal)
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Liquidación Cuadrada");
        }
        else  if (subLiquidacion>saldoTotal)
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Se Esta Pagando Mas De Lo que Se Necesita");
        }
        else 
        {
            saldoNuevo = this.nuevaLiquidacionVariante.getNuevoSaldoAnterior();
        }
        this.TFSaldoAnteriorNuevo.setText(String.valueOf(this.nuevaLiquidacionVariante.getNuevoSaldoAnterior()));
        
    }

    public void generarLiquidacion ()
    {
        
        {
            float liquidacion=0;
            for (DescripcionLiquidacionModelo modelo:this.data)
            {
                liquidacion = liquidacion+modelo.getMonto();
            }
            liquidacion = liquidacion+Float.parseFloat(this.TFOtro.getText());
            this.nuevaLiquidacionVariante.actualizarLiquidacion(liquidacion);
            this.nuevaLiquidacionVariante.actualizarSaldoNuevoAnterior();
            TFLiquidacion.setText(String.valueOf(this.nuevaLiquidacionVariante.getLiquidacion()));
            this.verificacionDeCuadre();
        }        
    }
    
    public void generarLiquidacionContraseñas ()
    {
        if ((Float.parseFloat(this.TFOtro.getText())==0)&&(Float.parseFloat(this.TFISR.getText())==0)&&(Float.parseFloat(this.TFTotalContraseñas.getText())==0))
        {
            
        }
        else
        {
        if (!this.validar.ValidarMontos(this.TFOtro.getText())||(!this.validar.ValidarMontos(this.TFISR.getText())||!this.validar.ValidarMontos(this.TFTotalContraseñas.getText())))
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Verifique los Datos de Las Contraseñas");
        }
        else
        {
            float liquidacion=0;
            for (DescripcionLiquidacionModelo modelo:this.data)
            {
                liquidacion = liquidacion+modelo.getMonto();
            }
            liquidacion = liquidacion+Float.parseFloat(this.TFOtro.getText());
            this.nuevaLiquidacionVariante.actualizarLiquidacion(liquidacion);
            this.nuevaLiquidacionVariante.actualizarSaldoNuevoAnterior();
            TFLiquidacion.setText(String.valueOf(this.nuevaLiquidacionVariante.getLiquidacion()));
            this.verificacionDeCuadre();
        }
        }
    }   
    
    public void LLenarTablaDevoluciones()
    {
        dataDevolucion.clear();
        int contadorLista = 0;
        String nombreDes;
        String nombreDescrip;
        String nombre;
        for(ProductoACambiar nuevo: this.nuevaLiquidacionVariante.getistaDeProductosACambiar())
        {
            Productos pro= new Productos();
            pro = nuevo.getProducto();
            nombreDescrip = pro.getPresentacion();
            nombre = pro.getNombre();
            nombreDes = nombre + " "+ nombreDescrip;
            
            DevolucionModelo Modelo = new DevolucionModelo(nombreDes,String.valueOf(nuevo.getCantidadPuedeDevolver()),String.valueOf(nuevo.getMontoPuedeDevolver()),String.valueOf(contadorLista),nuevo.precioAdevolver());
            dataDevolucion.add(Modelo);
            contadorLista++;
        }           
    }
    
   
    
    public void SeleccinarProducto(ActionEvent evnet)
    {
        if (!this.validar.ValidarMontos(this.TFCantidad2.getText()))
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Verifique La cantidad que Desea  Devolver");
        }
        else
        {
            if (Float.parseFloat(this.TFCantidad2.getText())>0)
            {
                DevolucionModelo Seleccionado = (DevolucionModelo)TablaDevoluciones.getSelectionModel().getSelectedItem();
                int Index = TablaDevoluciones.getSelectionModel().getSelectedIndex();
                if(Seleccionado == null)
                {
                    this.NuevaLiquidacionAdministrador.showMensajes("Debe Seleccionar un Dato");
                }
                else
                {
                    if(TFCantidad2.getText().equals(""))
                    {
                        this.NuevaLiquidacionAdministrador.showMensajes("No Ingreso la Cantidad");
                    }
                    else
                    {

                        if (Float.parseFloat(Seleccionado.getMonto())>=this.nuevaLiquidacionVariante.cambioARealizar(Integer.parseInt(Seleccionado.getNo()), Float.parseFloat(this.TFCantidad2.getText())))
                        {                            
                            this.nuevaLiquidacionVariante.actualizarTotalDePedidoPorDevolucion(this.nuevaLiquidacionVariante.cambioARealizar(Integer.parseInt(Seleccionado.getNo()), Float.parseFloat(this.TFCantidad2.getText())));
                            this.TFSubTotal.setText(String.valueOf(this.nuevaLiquidacionVariante.getTotalDelPedido()));
                            this.TFSaldoTotal.setText(String.valueOf(this.nuevaLiquidacionVariante.getTotal()));    
                            dataDevolucion.remove(Index);
                            TablaDevoluciones.setItems(dataDevolucion);
                            this.verificacionDeCuadre ();
                            this.nuevaLiquidacionVariante.ingresarAlistaDeCambios(Seleccionado.getNombre(),Float.parseFloat(TFCantidad2.getText()),Seleccionado.getPrecio());
                            this.TFCantidad2.setText("");
                            if (this.dataDevolucion.isEmpty())
                            {
                                this.NuevaLiquidacionAdministrador.showMensajes("Cambios Completados");
                                this.TablaDevoluciones.isDisable();
                            }
                        }
                        else
                        {
                            this.NuevaLiquidacionAdministrador.showMensajes("No se Puede Devolver Esta Cantidad De Producto");
                        }
                    }
                    }               
            }
            else
            {
                this.NuevaLiquidacionAdministrador.showMensajes("Debe Ingresar Una Cantidad Para La Devolución");
            }
        }
    }
    
    public void restarVales ()
    {
        if ((Float.parseFloat(this.TFOtro.getText())==0)&&(Float.parseFloat(this.TFISR.getText())==0)&&(Float.parseFloat(this.TFTotalContraseñas.getText())==0))
        {
            
        }
        else
        if (!this.validar.ValidarMontos(this.TFOtro.getText())||(!this.validar.ValidarMontos(this.TFISR.getText())||!this.validar.ValidarMontos(this.TFTotalContraseñas.getText())))
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Verifique los Datos de Las Contraseñas");
        }
        else
        {
            this.nuevaLiquidacionVariante.actualizarTotalDePedidoPorDevolucionSumarISR(Float.parseFloat(this.TFTotalContraseñas.getText()),Float.parseFloat(this.TFISR.getText()),Float.parseFloat(this.TFOtro.getText()));
            this.TFSubTotal.setText(String.valueOf(this.nuevaLiquidacionVariante.getTotalDelPedido()));
            this.TFSaldoTotal.setText(String.valueOf(this.nuevaLiquidacionVariante.getTotal()));   
            this.verificacionDeCuadre ();
            this.nuevaLiquidacionVariante.actualizarSaldoNuevoAnterior();
            this.TFSaldoAnteriorNuevo.setText(String.valueOf(this.nuevaLiquidacionVariante.getNuevoSaldoAnterior()));
        }
    }
    public void AplicarISR()
    {
        if ((Float.parseFloat(this.TFOtro.getText())==0)&&(Float.parseFloat(this.TFISR.getText())==0)&&(Float.parseFloat(this.TFTotalContraseñas.getText())==0))
        {
            
        }
        else
        if (!this.validar.ValidarMontos(this.TFOtro.getText())||(!this.validar.ValidarMontos(this.TFISR.getText())||!this.validar.ValidarMontos(this.TFTotalContraseñas.getText())))
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Verifique los Datos de Las Contraseñas");
        }
        else
        {
        float contraseñas=Float.parseFloat(this.TFOtro.getText());
        float ISR =Float.parseFloat(this.TFISR.getText());
        float total =(contraseñas*ISR);
        this.TFTotalContraseñas.setText(String.valueOf(total));
        this.verificacionDeCuadre ();
        this.nuevaLiquidacionVariante.actualizarSaldoNuevoAnterior();
        this.TFSaldoAnteriorNuevo.setText(String.valueOf(this.nuevaLiquidacionVariante.getNuevoSaldoAnterior()));
        }
    }
 
    public void limpiarTodo (){
        this.nuevaLiquidacionVariante.cancelar();
        this.TFIdLiquidacion.setText("");
        this.TFNumeroBoleta.setText("");
        this.TFNumeroCheque.setText("");
        this.TFCantidad2.setText("");
        this.TFSaldoAnterior.setText("0.00");
        this.TFLiquidacion.setText("0.00");
        this.TFSaldoAnteriorNuevo.setText("0.00");
        this.TFSaldoTotal.setText("0.00");
        this.TFSubTotal.setText("0.00");
        this.TFISR.setText("0.00");
        this.TFTotalContraseñas.setText("0.00");
        this.TFOtro.setText("0.00");
        this.TFProducto.setText("");
        this.TFCantidad.setText("");
        this.TADescripcion.setText("");
        this.dataDefectuosos.clear();
        this.TablaDefectuosos.setItems(dataDefectuosos);
        this.data.clear();
        this.TablaDescripciones.setItems(data);
        this.dataDevolucion.clear();
        this.TablaDevoluciones.setItems(dataDevolucion);
        this.BtnDevolucion.setDisable(false);
        this.TFCantidad2.setDisable(false);
        this.TFIdLiquidacion.requestFocus();
        
    }
    public void limpiarTodoPorError (){
        this.nuevaLiquidacionVariante.cancelar();
        this.TFNumeroBoleta.setText("");
        this.TFNumeroCheque.setText("");
        this.TFCantidad2.setText("");
        this.TFSaldoAnterior.setText("0.00");
        this.TFLiquidacion.setText("0.00");
        this.TFSaldoAnteriorNuevo.setText("0.00");
        this.TFSaldoTotal.setText("0.00");
        this.TFSubTotal.setText("0.00");
        this.TFProducto.setText("");
        this.TFCantidad.setText("");
        this.TADescripcion.setText("");
        this.dataDefectuosos.clear();
        this.TablaDefectuosos.setItems(dataDefectuosos);
        this.data.clear();
        this.TablaDescripciones.setItems(data);
        this.dataDevolucion.clear();
        this.TablaDevoluciones.setItems(dataDevolucion);
        this.BtnDevolucion.setDisable(false);
        this.TFCantidad2.setDisable(false);
        this.TFIdLiquidacion.requestFocus();
        
    }
    
    // desde aca empieza a realizarse los procedimientos con las modificaciondees de la liquidacion
    public void iniciarLiquidacion (ActionEvent event)
    {
        if (!this.validar.ValidarNumeros(this.TFIdLiquidacion.getText()))
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Verifique El Id del Pedido");
            this.limpiarTodoPorError();
        }
        
        else
        {
            this.limpiarTodoPorError();
            this.TFNombreVendedor.setText(
            this.nuevaLiquidacionVariante.iniciarLiquidacion(Integer.parseInt(this.TFIdLiquidacion.getText()))); // inicia la liquidacion con sus valores respectivos
            this.TFSaldoAnterior.setText(String.valueOf(this.nuevaLiquidacionVariante.getSaldoAnterior()));
            this.TFSubTotal.setText(String.valueOf(this.nuevaLiquidacionVariante.getTotalDelPedido()));
            this.TFSaldoTotal.setText(String.valueOf(this.nuevaLiquidacionVariante.getTotal()));
            this.TFSaldoAnteriorNuevo.setText(String.valueOf(this.nuevaLiquidacionVariante.getNuevoSaldoAnterior()));
            
            this.generarLiquidacion();
            this.verificacionDeCuadre ();            
            LLenarTablaDevoluciones();
            this.TFNumeroBoleta.requestFocus();
        }
    }
    
    public ObservableList convertirAObservableList (List<Object> lista)
    {
        List<String> listaCadena = new ArrayList<String>();
        for (Object valor:lista)
        {
            listaCadena.add(valor.toString());
        }
        ObservableList<String> observableList = FXCollections.observableList(listaCadena);
        return observableList;
    }
    
    
    
    public void aceptar (ActionEvent event)
    {
        try{    
            if (this.TFLiquidacion.getText()=="0.00"||this.TFLiquidacion.getText()=="0.0"||this.TFLiquidacion.getText()=="0")
            {
                this.NuevaLiquidacionAdministrador.showMensajes("No se Ingresaron Cheques Para Liquidar");
            }
            else
            {
                this.nuevaLiquidacionVariante.guardar(data);
                this.nuevaLiquidacionVariante.GuardarProductosDefectuosos(dataDefectuosos);
                GeneradordeReportes Generar = new GeneradordeReportes();
                ILiquidacion liquidacionUltima = new ILiquidacion ();                
                Generar.AbrirReporte("Liquidacion.jasper",liquidacionUltima.retornarUltimaIngresada().getIdliquidacion(), this.nuevaLiquidacionVariante.getSaldoAnteriorSinActualizar());
                this.NuevaLiquidacionAdministrador.showMensajes("Pedido Liquidado Correctamente.");
                this.nuevaLiquidacionVariante.cancelar();
                this.limpiarTodo();
            }
        }
        catch (Exception e)
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Error Liquidando.");
            this.nuevaLiquidacionVariante.cancelar();
            this.BtnDevolucion.setDisable(false);
            this.TFCantidad2.setDisable(false);
        }
    }
    
    
    
    public void AgregarDefectuoso(ActionEvent Event)
    {
        Validaciones Validar = new Validaciones();
        if(Validar.ValidarNumeros(TFProducto.getText()) && Validar.ValidarMontos(TFCantidad.getText()))
        {
            Productos Productos = new IProductos().buscarProductoPorId(Integer.parseInt(TFProducto.getText()));
            if(Productos != null)
            {
                DefectuososModelo Modelo = new DefectuososModelo(Productos.getIdProductos(), Productos.getNombre(), Float.parseFloat(TFCantidad.getText()), null, TADescripcion.getText());
                dataDefectuosos.add(Modelo);
                TablaDefectuosos.setItems(dataDefectuosos);
                TFProducto.setText("");
                TFCantidad.setText("");
                TADescripcion.setText("");
            }
            else
            {
                this.NuevaLiquidacionAdministrador.showMensajes("Producto no Encontrado");
            }
        }
        else if(!Validar.ValidarNumeros(TFProducto.getText()) && Validar.ValidarNumeros(TFCantidad.getText()))
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Id del Producto no Valido");
        }
        else if(!Validar.ValidarMontos(TFCantidad.getText()) && Validar.ValidarNumeros(TFProducto.getText()))
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Cantidad no Valida");
        }
        else
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Id del Producto y Cantidad no Validos");
        }
        
    }
    
    public void QuitarDefectuoso(ActionEvent Event)
    {
        DefectuososModelo Seleccionado = (DefectuososModelo)TablaDefectuosos.getSelectionModel().getSelectedItem();
        int Index = TablaDefectuosos.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            dataDefectuosos.remove(Index);
            TablaDefectuosos.setItems(dataDefectuosos);
        }
    }
    
     private boolean verificarChequeIngresado (String numero)            
    {
        for (DescripcionLiquidacionModelo DescripcionModelo:this.data)
        {
            String num = DescripcionModelo.getNumeroCheque();
            try {
                if (DescripcionModelo.getNumeroBoleta()==null)
                {
                    if (DescripcionModelo.getNumeroCheque().equals(numero))
                    {
                        return true;                
                    }
                }
            }
                
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        return false;
    }
    private boolean verificarDepositoIngresado (String numero)            
    {
        String numeroDeBoleta ="";
        for (DescripcionLiquidacionModelo DescripcionModelo:this.data)
        {
            if (DescripcionModelo.getNumeroCheque()==null)
            {
                numeroDeBoleta = DescripcionModelo.getNumeroBoleta();
                if (numeroDeBoleta.equals(numero))
                {
                    return true;                
                }
            }
        }      
    
        return false;

    }
}

