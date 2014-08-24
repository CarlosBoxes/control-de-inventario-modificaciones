/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import EntidadesJPA.Clientes;
import EntidadesJPA.Pedido;
import EntidadesJPA.Productos;

/**
 *
 * @author Ch470
 */
public class DescripcionPedidoAClientes {
    private Productos producto;
    private Clientes cliente;
    private Pedido pedido;
    private float cantidad;
    private float subTotal;
    private float precio;
    
    public DescripcionPedidoAClientes(){
        producto = new Productos();
        cliente = new Clientes();
        pedido = new Pedido();
        cantidad =0;
        subTotal =0;
        precio =0;
    }
    
    public void setearValores (Productos pro,Clientes cli,Pedido pe,float cantidad,float subTotal,float precio)
    {
        this.producto=pro;
        this.cantidad=cantidad;
        this.cliente =cli;
        this.pedido=pe;
        this.subTotal= subTotal;
        this.precio = precio;
    }
    
    public Productos getProducto()
    {
        return this.producto;
    }
    public Clientes getCliente()
    {
        return this.cliente;
    }
    public Pedido getPedido()
    {
        return this.pedido;
    }
    
    public float getCantidad()
    {
        return this.cantidad;
    }
    public float getSubTotal()
    {
        return this.subTotal;
    }
    
    public float getPrecio ()
    {
        return this.precio;
    }
}
