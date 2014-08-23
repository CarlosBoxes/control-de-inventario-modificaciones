/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevaLiquidacionEspecialAdministrador;
import EdicionTablas.EditarCantidad;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.Depositos;
import EntidadesJPA.InventarioProducto;
import EntidadesJPA.Liquidacion;
import EntidadesJPA.Pedido;
import EntidadesJPA.Productos;
import Especiales.DescripcionLiquidacionEspecial;
import Especiales.GeneradordeReportes;
import Especiales.Validaciones;
import Especiales.liquidacionEspecial;
import GestorDeTablasJPA.IChequesClientes;
import GestorDeTablasJPA.IDepositos;
import GestorDeTablasJPA.IDescripcionLiquidacion;
import GestorDeTablasJPA.IInventarioProducto;
import GestorDeTablasJPA.ILiquidacion;
import GestorDeTablasJPA.IPedido;
import GestorDeTablasJPA.IProductos;
import Modelos.DescripcionLiquidacionModelo;
import Modelos.EspecialModelo;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
/**
 *
 * @author luis__000
 */
public class NuevaLiquidacionEspecialControlador 
{
    NuevaLiquidacionEspecialAdministrador NuevaLiquidacionAdministrador;
    @FXML
    public TableView TablaLiquidacion;
    public TableView TablaPedido;
    public TableColumn NumeroBoleta;
    public TableColumn NumeroCheque;
    public TableColumn Monto; 
    
    public TableColumn IdCliente;
    public TableColumn Cliente;
    public TableColumn TotalDelPedido;
    public TableColumn Saldo;
    public TableColumn Pago;
    public TableColumn NuevoSaldo;
    
    
    public TextField TFId;
    public Validaciones Validar;
    public VBox PnlLiquidacion;
    public VBox PnlPedido;
    public ComboBox ComBoPago;
    public Label LblPago;
    public TextField TFNumero;
    public TextField TFTotal;
    public TextField TFCredito;
    public TextField TFContado;
    public TextField TFLiquidacion;
    Pedido Pedido;
    public ObservableList<DescripcionLiquidacionModelo> data;
    public ObservableList<EspecialModelo> dataPedido;  
    Callback<TableColumn, TableCell> cellFactory;
    
    public liquidacionEspecial liquidacionEspecial;
    
    public void initialize() {}
  
    public void initManager(final NuevaLiquidacionEspecialAdministrador NuevaLiquidacionAdministrador)
    {
        this.NuevaLiquidacionAdministrador = NuevaLiquidacionAdministrador;
        this.liquidacionEspecial = new liquidacionEspecial();
        data = FXCollections.observableArrayList();
        dataPedido = FXCollections.observableArrayList();
        NumeroBoleta = new TableColumn();
        NumeroCheque = new TableColumn();
        Monto = new TableColumn();        
        IdCliente = new TableColumn();
        Cliente = new TableColumn();
        TotalDelPedido = new TableColumn();
        Saldo = new TableColumn();    
        Pago = new TableColumn();
        NuevoSaldo = new TableColumn();
        
        this.Validar = new Validaciones();
        cellFactory = new Callback<TableColumn, TableCell>() 
        {
            public TableCell call(TableColumn p) {
                return new EditarCantidad();
            }
	};
        CargarColumnas();
        CargarColumnasPedido();
        PnlLiquidacion.setDisable(true);
        TFTotal.setDisable(true);
        TFCredito.setDisable(true);
        TFContado.setDisable(true);
        TFLiquidacion.setDisable(true);
        TFNumero.setDisable(true);
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
        TablaLiquidacion.getColumns().add(NumeroBoleta);
        TablaLiquidacion.getColumns().add(NumeroCheque);
        TablaLiquidacion.getColumns().add(Monto);
    }
    
    private void CargarColumnasPedido()
    {
        IdCliente.setCellValueFactory(new PropertyValueFactory<EspecialModelo,String>("IdCliente"));
        Cliente.setCellValueFactory(new PropertyValueFactory<EspecialModelo,String>("Cliente"));
        TotalDelPedido.setCellValueFactory(new PropertyValueFactory<EspecialModelo,String>("TotalDelPedido"));
        Saldo.setCellValueFactory(new PropertyValueFactory<EspecialModelo,String>("Saldo"));
        Pago.setCellValueFactory(new PropertyValueFactory<EspecialModelo,String>("Pago"));
        Pago.setCellFactory(cellFactory);
        Pago.setOnEditCommit(
                new EventHandler<CellEditEvent<EspecialModelo,String>>()
                {
                    private DescripcionLiquidacionEspecial Descripcion = new DescripcionLiquidacionEspecial();
                    @Override
                    public void handle(CellEditEvent<EspecialModelo,String> t)
                    {
                        EspecialModelo L = (EspecialModelo) t.getTableView().getItems().get(t.getTablePosition().getRow());         
                        Descripcion = liquidacionEspecial.buscarDescripcionPorCliente(L.getIdCliente());
                        if(Validar.ValidarMontos(t.getNewValue()) && Float.parseFloat(t.getNewValue())>=0)
                        {
                          L.setPago(t.getNewValue());
                          liquidacionEspecial.AbonoDeCliente(Float.parseFloat(t.getNewValue()), L.getIdCliente());
                          ActualizarDataPedido();
                          TFContado.setText(String.valueOf(liquidacionEspecial.generarTotalAPagar()));
                          generarCredito ();
                        }
                        if(!Validar.ValidarMontos(t.getNewValue()))
                        {
                            NuevaLiquidacionAdministrador.showMensajes("Cantidad No Valida");
                            L.setPago(t.getNewValue());
                        }
                        else if(Float.parseFloat(t.getNewValue()) < 0)
                        {
                            NuevaLiquidacionAdministrador.showMensajes("La Cantidad No Puede se Menor a 0");
                            L.setPago(t.getNewValue());
                        }           
                    }
                });
        NuevoSaldo.setCellValueFactory(new PropertyValueFactory<EspecialModelo,String>("NuevoSaldo"));
       
        IdCliente.setText("Id");
        Cliente.setText("Cliente");
        TotalDelPedido.setText("Total del Pedido");
        Saldo.setText("Saldo");
        Pago.setText("Pago");
        NuevoSaldo.setText("Nuevo Saldo");
        
        IdCliente.setPrefWidth(30);
        Cliente.setPrefWidth(250);
        TotalDelPedido.setPrefWidth(130);
        Saldo.setPrefWidth(130);
        Pago.setPrefWidth(130);
        NuevoSaldo.setPrefWidth(130);
        
        TablaPedido.getColumns().add(IdCliente);
        TablaPedido.getColumns().add(Cliente);
        TablaPedido.getColumns().add(TotalDelPedido);
        TablaPedido.getColumns().add(Saldo);
        TablaPedido.getColumns().add(Pago);
        TablaPedido.getColumns().add(NuevoSaldo);
    }
    
    private boolean CalcularInventario(Integer Old, Integer New, Integer Id)
    {
        if((Old-New) >= 0)
        {
            return true;
        }
        else
        {
            Productos Productos = new IProductos().buscarProductoPorId(Id);
            InventarioProducto Inventario = new IInventarioProducto().buscarInventarioPorProducto(Productos);
            if(Inventario.getCantidad() - (New - Old) >= 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    
    public void BuscarDescripcion(ActionEvent e)
    {
        this.liquidacionEspecial = new liquidacionEspecial();
        if(Validar.ValidarNumeros(TFId.getText()))
        {
            Pedido = new IPedido().buscarPedidoPorId(Integer.parseInt(TFId.getText()));
            if (this.Pedido.getDescripcionClientesCollection().isEmpty() && !this.Pedido.getDescripcionPedidoCollection().isEmpty())
            {
                this.NuevaLiquidacionAdministrador.showMensajes("Este pedido no puede ser liquidado en esta área");
            
            }
            else
                if(this.Pedido.getLiquidado())
                {
                    this.NuevaLiquidacionAdministrador.showMensajes("Este Pedido ya fue liquidado");
                }
                else
                    if(this.Pedido.getEliminado())
                    {
                        this.NuevaLiquidacionAdministrador.showMensajes("Este Pedido fue Eliminado.");
                    }
                    else
            {               
                this.liquidacionEspecial.iniciarLiquidacion(Integer.parseInt(this.TFId.getText()));
                this.dataPedido = this.liquidacionEspecial.descripcionCliente();
                TablaPedido.setItems(this.liquidacionEspecial.descripcionCliente());              
                TFTotal.setText(String.valueOf(liquidacionEspecial.getTotalPedido()));
            }
        }
        else
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Verifique el Id del Pedido");
        }
    }
    
    public void Cancelar(ActionEvent e)
    {
        TFId.setText("");
        dataPedido.clear();
        TablaPedido.setItems(data);
        Pedido = null;
    }
    
    public void Terminar(ActionEvent e)
    {
        this.recorrerAbonos();
        this.PnlLiquidacion.setDisable(false);
        this.PnlPedido.setDisable(true);
    }
    
    
    public boolean VerificarCantidades()
    {
        for(EspecialModelo Modelo: dataPedido)
        {
            if(!Validar.ValidarNumeros(Modelo.getPago()))
            {    
                return false;
            }
        }
        return true;
    }
    
    public List<Float> CalcularTotales()
    {
        Float Contado = 0f;
        Float Credito = 0f;
        List<Float> Totales = new ArrayList();
        for(EspecialModelo Modelo: dataPedido)
        {
            
        }
        Totales.add(Contado+Credito);
        Totales.add(Credito);
        Totales.add(Contado);
        return Totales;
    }
    
    public void Seleccionado(ActionEvent event)
    {
        if(ComBoPago.getValue().equals("Deposito"))
        {
            LblPago.setText("No. Deposito");
            TFNumero.setText("");
            TFNumero.setDisable(false);
        }
        else if(ComBoPago.getValue().equals("Cheque"))
        {
            LblPago.setText("No. Cheque");
            TFNumero.setText("");
            TFNumero.setDisable(false);
        }
    }
    
    public void AgregarCheque()
    {
        ChequesClientes Cheque = new IChequesClientes().buscarChequesNumeroChueque(Integer.parseInt(TFNumero.getText()));
        if(Cheque != null)
        {
            
            if(Cheque.getUsado())
            {
                this.NuevaLiquidacionAdministrador.showMensajes("El Cheque Ya Fue Usado");
            }
            else if(VerificarCheque(TFNumero.getText()))
            {
                DescripcionLiquidacionModelo DescripcionModelo = new DescripcionLiquidacionModelo(null,String.valueOf(Cheque.getNumero()),Cheque.getMonto());
                data.add(DescripcionModelo);
                TablaLiquidacion.setItems(data);
                TFNumero.setText("");
                TFNumero.setDisable(true);
                ComBoPago.requestFocus();
            }
            else
            {
                this.NuevaLiquidacionAdministrador.showMensajes("Cheque ya Ingresado");
            }
        }
        else
        {
            this.NuevaLiquidacionAdministrador.showMensajes("El Cheque No Existe");
        }
    }
    
    public void AgregarDeposito()
    {
        Depositos Deposito = new IDepositos().buscarDepositoPorNumeroBoleta(TFNumero.getText());
        if(Deposito != null)
        {
            if(Deposito.getUsado())
            {
                this.NuevaLiquidacionAdministrador.showMensajes("El Deposito Ya Fue Usado");
            }
            else if(VerificarDeposito(TFNumero.getText()))
            {
                DescripcionLiquidacionModelo DescripcionModelo = new DescripcionLiquidacionModelo(Deposito.getNumeroDeBoleta(),null,Deposito.getMonto());
                data.add(DescripcionModelo);
                TablaLiquidacion.setItems(data);
                TFNumero.setText("");
                TFNumero.setDisable(true);
                ComBoPago.requestFocus();
            }
            else
            {
                this.NuevaLiquidacionAdministrador.showMensajes("Deposito ya Ingresado");
            }
        }
        else
        {
            this.NuevaLiquidacionAdministrador.showMensajes("El Deposito No Existe");
        }
    }
    
    public void SacarDecripcion(ActionEvent event)
    {
        DescripcionLiquidacionModelo Seleccionado = (DescripcionLiquidacionModelo)TablaLiquidacion.getSelectionModel().getSelectedItem();
        int indice = TablaLiquidacion.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.NuevaLiquidacionAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            data.remove(indice);
            TablaLiquidacion.setItems(data);
            CalcularLiquidacion();
        } 
    }
    
    public void AgregarDescripcion(ActionEvent e)
    {
        if(ComBoPago.getValue().equals("Deposito"))
        {
            AgregarDeposito();
            CalcularLiquidacion();
        }
        else if(ComBoPago.getValue().equals("Cheque"))
        {
            AgregarCheque();
            CalcularLiquidacion();
        }
    }
    
    private void CalcularLiquidacion()
    {
        Float Total = 0f;
        for(DescripcionLiquidacionModelo Modelo: data)
        {
            Total = Total + Modelo.getMonto();
        }
       
        TFLiquidacion.setText(String.valueOf(Total));
        this.NuevaLiquidacionAdministrador.showMensajes(this.verificarCuadreDePagadoConLiquidacion());       
    }
    
    private boolean VerificarDeposito(String NoBoleta)
    {
        for(DescripcionLiquidacionModelo Modelo: data)
        {
            if(Modelo.getNumeroBoleta() != null)
            {
                if(Modelo.getNumeroBoleta().equals(NoBoleta))
                {
                    return false;
                }
            }
        }
        return true;     
    }
    
    private boolean VerificarCheque(String Cheque)
    {
        for(DescripcionLiquidacionModelo Modelo: data)
        {
            if(Modelo.getNumeroCheque() != null)
            {
                if(Modelo.getNumeroCheque().equals(Cheque))
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void CancelarLiquidacion(ActionEvent e)
    {
        PnlLiquidacion.setDisable(true);
        PnlPedido.setDisable(false);
        ComBoPago.setValue("");
        TFNumero.setDisable(true);
        LblPago.setText("");
        data.clear();
        TablaLiquidacion.setItems(data);
        TFLiquidacion.setText("0.00");
        
    }
    
    public void Aceptar(ActionEvent e)
    {
        Float Total = Float.parseFloat(TFContado.getText());
        Float Liquidacion = Float.parseFloat(TFLiquidacion.getText());
        if(Liquidacion>=Total)
        {
            String Mensaje = new ILiquidacion().guardar(Total, Liquidacion, Pedido);
            Liquidacion Liquidaciones = new ILiquidacion().retornarUltimaIngresada();            
            GuardarDescripcion(Liquidaciones);
            GuardarCredito();
            liquidacionEspecial.guardarNuevosSaldosAClientes();
            Pedido = new IPedido().buscarPedidoPorId(Pedido.getIdpedido());
            Pedido.setLiquidado(true);
            new IPedido().modificar(Pedido);
            GeneradordeReportes Reporte = new GeneradordeReportes();
            Reporte.AbrirReporte("LiquidacionEspecial.jasper", Liquidaciones.getIdliquidacion(), Float.parseFloat(TFCredito.getText()));
            this.NuevaLiquidacionAdministrador.showMensajes("Pedido Liquidado Correctamente \n El Crédito de: Q."+TFCredito.getText()+" Se Cargo a los Clientes");
            PnlLiquidacion.setDisable(true);
            PnlPedido.setDisable(false);
            ComBoPago.setValue("");
            TFNumero.setDisable(true);
            LblPago.setText("");
            data.clear();
            TablaLiquidacion.setItems(data);
            dataPedido.clear();
            TablaPedido.setItems(dataPedido);
            TFId.setText("");
            TFTotal.setText("0.00");
            TFCredito.setText("0.00");
            TFContado.setText("0.00");
            TFLiquidacion.setText("0.00");
        }
        else if(Liquidacion < Total)
        {
            this.NuevaLiquidacionAdministrador.showMensajes("No Esta Pagando la Cantidad Necesario");
        }
    }
    IDepositos depositosGestor = new IDepositos ();
    IChequesClientes chequesGestor = new IChequesClientes();
    private void GuardarDescripcion(Liquidacion Liquidaciones)
    {
        
        for(DescripcionLiquidacionModelo Modelo: data)
            {
                if(Modelo.getNumeroBoleta() != null)
                {
                    Depositos Deposito = new IDepositos().buscarDepositoPorNumeroBoleta(Modelo.getNumeroBoleta());
                    Deposito.setUsado(true);
                    depositosGestor.modificar(Deposito);
                    new IDescripcionLiquidacion().guardar(Liquidaciones, Deposito, null);
                }
                else
                {
                    ChequesClientes Cheque = new IChequesClientes().buscarChequesNumeroChueque(Integer.parseInt(Modelo.getNumeroCheque()));
                    Cheque.setUsado(true);
                    chequesGestor.modificar(Cheque);
                    new IDescripcionLiquidacion().guardar(Liquidaciones, null, Cheque);
                }
            }
    }
    
    private void GuardarCredito()
    {
        for(EspecialModelo Modelo: dataPedido)
        {
            
        }
    }
    
    private void recorrerAbonos ()
    {
        for (EspecialModelo descripcion: this.dataPedido)
        {
            System.out.print(descripcion.getPago());
        }
    }
    
    public void ActualizarDataPedido()
    {
        TablaPedido.setItems(this.liquidacionEspecial.descripcionCliente());
    }
    
    public String verificarCuadreDePagadoConLiquidacion()
    {
        if (Float.parseFloat(TFContado.getText())==Float.parseFloat(TFLiquidacion.getText()))
        {
            return "Liquidación y Pagos Cuadrados";
        }
        else
            if (Float.parseFloat(TFContado.getText())<=Float.parseFloat(TFLiquidacion.getText()))
            {
                return "Se esta liquidando mas de lo que los clientes pagaron";
            }
        else
            if (Float.parseFloat(TFContado.getText())>=Float.parseFloat(TFLiquidacion.getText()))
            {
                return "Aun falta efectivo para poder liquidar correctamente";
            }
        else
            {
                return "Error";
            }
    }
    
    public void generarCredito ()
    {
      Float Total = 0f;
      Float Pagado = 0f;
      Float Credito = 0f;
      Total = Float.parseFloat(TFTotal.getText());
      Pagado = Float.parseFloat(TFContado.getText());
      Credito = Total-Pagado;
      if (Credito<0)
      {
          this.NuevaLiquidacionAdministrador.showMensajes("Se esta Abonando mas del total del pedido");
      }
      else
      {
        TFCredito.setText(String.valueOf(Credito));   
      }
    }
}
