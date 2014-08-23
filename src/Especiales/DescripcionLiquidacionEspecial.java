/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import EntidadesJPA.Clientes;
import EntidadesJPA.Pedido;
import GestorDeTablasJPA.IClientes;
import GestorDeTablasJPA.IPedido;

/**
 *
 * @author Ch470
 */
public class DescripcionLiquidacionEspecial {
    //variables para la clases, son atributos propios
    private Clientes cliente;
    private Pedido pedido;
    private float totalCliente;
    private float TotalDelPedido;
    private float abono;
    private float nuevoSaldo;
    private boolean pago;
    // manejadores para los clientes y pedidos
    private IClientes manejadorCliente=new IClientes();
    private IPedido manejadorPedido = new IPedido ();
    
    public DescripcionLiquidacionEspecial()
    {
        cliente = new Clientes();
        pedido = new Pedido();
        totalCliente = 0;
        TotalDelPedido = 0;
        abono =0;
        nuevoSaldo = 0;
        pago = false;
    }
    
    public void guardarDatos (int idCliente,int idPedido,float totalDelPedido)
    {
        this.cliente = manejadorCliente.buscarClientesPorId(idCliente);
        this.pedido = manejadorPedido.buscarPedidoPorId(idPedido);
        this.totalCliente = cliente.getSaldo()+totalDelPedido;
        this.TotalDelPedido= totalDelPedido;
        this.ActualizarNuevoSaldo();
    }
    
    public void sumarATotalPedido(float suma)
    {
        this.TotalDelPedido = this.TotalDelPedido+suma;
        this.totalCliente = this.totalCliente+ suma;
        this.ActualizarNuevoSaldo();
    }
    
    public  Clientes getCliente ()
    {
        return this.cliente;
    }
    
    
    public Pedido getPedido()
    {
        return this.pedido;
    }
    
    public float getTotalCliente ()
    {
        return this.totalCliente;
    }
    
     public float getTotalPedidoCliente ()
    {
        return this.TotalDelPedido;
    }
    
    public float getAbono ()
    {
        return this.abono;
    }
    
    public float getNuevoSaldo ()
    {
        return this.nuevoSaldo;
    }
    
    public boolean getPago ()
    {
        return this.pago;
    }
    
     public  void setCliente (Clientes clientes)
    {
        this.cliente=clientes;
    }
    
    
    public void setPedido(Pedido pedidos)
    {
        this.pedido = pedidos;
    }
    
    public void setTotalCliente (float total)
    {
        this.totalCliente=total;
    }
    
    public void setTotalPedidoCliente (float total)
    {
        this.TotalDelPedido=total;
    }
    
    public void Abonar (float abonos)
    {
        this.abono = abonos;
        this.ActualizarNuevoSaldo();
    }
    
    
    public void setPago (boolean pagos)
    {
        this.pago=pagos;
    }
    
    public void ActualizarNuevoSaldo ()
    {
        this.nuevoSaldo = this.totalCliente-this.abono;
    }
}
