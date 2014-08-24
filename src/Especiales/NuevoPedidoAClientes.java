/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import EntidadesJPA.Clientes;
import EntidadesJPA.Pedido;
import EntidadesJPA.Productos;
import EntidadesJPA.Vendedores;
import GestorDeTablasJPA.IDescripcionClientes;
import GestorDeTablasJPA.IDescripcionPedido;
import GestorDeTablasJPA.IInventarioProducto;
import GestorDeTablasJPA.IPedido;
import GestorDeTablasJPA.IProductos;
import Modelos.DescripcionModelo;
import Modelos.DescripcionModeloResumen;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ch470
 */
public class NuevoPedidoAClientes {
    private Pedido pedido;
    private Vendedores vendedor;
    private float subTotal;
    private float total;
    private String observaciones;
    List<DescripcionPedidoAClientes> listaDeDescripcionCompleta;
    IDescripcionClientes gestorDescripcionClientes = new IDescripcionClientes();
    IPedido gestorPedido = new IPedido ();
    IInventarioProducto gestorInventario = new IInventarioProducto();
    //gestores para tablas
    
    private IProductos gestorProductos = new IProductos ();
    
    public NuevoPedidoAClientes(){
        pedido = new Pedido();
        vendedor = new Vendedores();
        subTotal = 0;
        total = 0;
        observaciones="";
        listaDeDescripcionCompleta = new ArrayList<DescripcionPedidoAClientes> ();
    }
    
    public void IniciarPedido (Date fecha, Vendedores vendedores)
    {
        this.pedido.setAplicado(false);
        this.pedido.setFecha(fecha);
        this.pedido.setSubtotal(subTotal);
        this.pedido.setTotal(total);
        this.pedido.setVendedoresIdvendedores(vendedor);
        this.pedido.setObservaciones(observaciones);
        this.vendedor = vendedores;
    }
    
    public void AgregarDescripciones(ObservableList<DescripcionModelo> descripcion,Clientes cliente)
    {        
        for (DescripcionModelo modelo:descripcion)
        {
            if (!this.verificarDescripcionExistente(modelo, cliente))
            {
                DescripcionPedidoAClientes gestorDescripcionClientesPor = new DescripcionPedidoAClientes();
                gestorDescripcionClientesPor.setearValores(gestorProductos.buscarProductoPorId(modelo.getIdProducto()),cliente, pedido, modelo.getCantidad(), modelo.getSubTotal(),modelo.getPrecio());
                this.listaDeDescripcionCompleta.add(gestorDescripcionClientesPor);
            }
        }
        this.calcularTotalYSubTotal();
    }
    
    public String guardarDescripciones (String observacion)
    {
        try
        {
            calcularTotalYSubTotal();
            this.gestorPedido.guardar(this.pedido.getFecha(),this.pedido.getAplicado(),this.pedido.getSubtotal(),this.pedido.getTotal(),this.vendedor,observacion);
            this.pedido = this.gestorPedido.retornarUltimoPedidoIngresado();
        for (DescripcionPedidoAClientes descripcion:this.listaDeDescripcionCompleta)
        {
            try
            {
                this.gestorDescripcionClientes.guardar(descripcion.getProducto(), descripcion.getCliente(),pedido, descripcion.getCantidad(), descripcion.getSubTotal(),descripcion.getPrecio());            
                this.gestorInventario.sacarDeInventario(descripcion.getProducto(),descripcion.getCantidad());
            }
            catch (Exception e)
                    {
                        return "Error Al terminar de guardar el pedido, Descripción.";
                    }
        }
        this.guardarDescripcionPedido();
        return "Pedido Creado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Crear el Pedido";
        }
    }
    
    public ObservableList<DescripcionModelo> descripcionCliente (Clientes cliente)
    {
        ObservableList<DescripcionModelo> data =  FXCollections.observableArrayList();
        for (DescripcionPedidoAClientes descripcion: this.listaDeDescripcionCompleta)
        {
            if (cliente.equals(descripcion.getCliente()))
            {
                DescripcionModelo DescripcionModelo = new DescripcionModelo(descripcion.getProducto().getIdProductos(),descripcion.getProducto().getNombre()+descripcion.getProducto().getPresentacion(),descripcion.getCantidad(),descripcion.getPrecio(),descripcion.getSubTotal());
                data.add(DescripcionModelo);
            }
        }
        return data;
    }
    
     public ObservableList<DescripcionModeloResumen> descripcionResumen ()
    {
        ObservableList<DescripcionModeloResumen> data =  FXCollections.observableArrayList();
        for (DescripcionPedidoAClientes descripcion: this.listaDeDescripcionCompleta)
        {
            if (data.isEmpty())
            {
                DescripcionModeloResumen descripcionModeloResumen = new DescripcionModeloResumen(descripcion.getProducto().getIdProductos(),descripcion.getProducto().getNombre()+descripcion.getProducto().getPresentacion(),descripcion.getCantidad(),descripcion.getPrecio(),descripcion.getSubTotal());
                data.add(descripcionModeloResumen);
            }
            else            
            {       
                if (this.verificarDescripcionExistenteEnResumen(data, descripcion))
                {
                for (DescripcionModeloResumen descripciones:data)
                {
                    if ((descripciones.getIdProducto()==descripcion.getProducto().getIdProductos())&&(descripciones.getPrecio()==descripcion.getPrecio()))
                    {
                        float cantidad = descripcion.getCantidad() + descripciones.getCantidad();
                        float subTotalNuevo = descripcion.getSubTotal() + descripciones.getSubTotal();
                        descripciones.EditarDescripcionModeloResumen(cantidad,subTotalNuevo);
                        break;
                    }
                }
                }                 
                    else
                    {
                        DescripcionModeloResumen descripcionModeloResumen = new DescripcionModeloResumen(descripcion.getProducto().getIdProductos(),descripcion.getProducto().getNombre()+descripcion.getProducto().getPresentacion(),descripcion.getCantidad(),descripcion.getPrecio(),descripcion.getSubTotal());
                        data.add(descripcionModeloResumen);
                    }
            }
        }
        return data;
    }
     
     
     
       public boolean verificarDescripcionExistenteEnResumen(ObservableList<DescripcionModeloResumen> data,DescripcionPedidoAClientes descripcion)//retornara verdadero si esa descripcion ya existe
    {
        for (DescripcionModeloResumen descripciones:data)
                {
                if ((descripciones.getIdProducto()==descripcion.getProducto().getIdProductos())&&(descripciones.getPrecio()==descripcion.getPrecio()))
                {
                    return true;
                }
            
        }
        return false;
    }
     
     IDescripcionPedido gestorDeDescripcionPedido = new IDescripcionPedido ();
     
     
     
     
     
     public void guardarDescripcionPedido ()
     {
         float cantidad =0;
         float subTotalDesc = 0;
         float precio =0;
         Productos producto = new Productos ();
         for (DescripcionModeloResumen descripcion:this.descripcionResumen())
         {
             cantidad = descripcion.getCantidad();
             subTotalDesc = descripcion.getSubTotal();
             precio = descripcion.getPrecio();
             producto = gestorProductos.buscarProductoPorId(descripcion.getIdProducto());
             gestorDeDescripcionPedido.guardar(cantidad, subTotalDesc, producto, this.pedido, precio);
         }
     }
    
    public List<DescripcionPedidoAClientes> getListaDeDescripcionCompleta ()
    {
        return this.listaDeDescripcionCompleta;
    }
    
    private void calcularTotalYSubTotal()
    {
        subTotal =0;
        for (DescripcionPedidoAClientes des:this.listaDeDescripcionCompleta)
        {
            subTotal = subTotal + des.getSubTotal();
        }
        
        total = subTotal;
        this.pedido.setSubtotal(subTotal);
        this.pedido.setTotal(total);
    }
    
    public float getTotal ()
    {
        return this.total;
    }
    public float getSubTotal ()
    {
        return this.subTotal;
    }
    
    
    public Pedido getPedido ()
    {
        return this.pedido;
    }
 
    
    
    public boolean verificarDescripcionExistente(DescripcionModelo descripcion,Clientes cliente)//retornara verdadero si esa descripcion ya existe
    {
        for (DescripcionPedidoAClientes descripciones:this.listaDeDescripcionCompleta)
        {
                if ((descripciones.getCliente().equals(cliente))&&(descripciones.getProducto().getIdProductos()==descripcion.getIdProducto()&&(descripciones.getCantidad()==descripcion.getCantidad()))&&(descripciones.getPrecio()==descripcion.getPrecio()))
                {
                    return true;
                }
            
        }
        return false;
    }
    
    
  
    
    public boolean borrarDescripcionExistente(DescripcionModelo descripcion,Clientes cliente)
    {
        int indice=0;
        for (DescripcionPedidoAClientes descripciones:this.listaDeDescripcionCompleta)
        {               
                if ((descripciones.getCliente().equals(cliente))&&(descripciones.getProducto().getIdProductos()==descripcion.getIdProducto()&&(descripciones.getCantidad()==descripcion.getCantidad()))&&(descripciones.getPrecio()==descripcion.getPrecio()))
                {
                    break;
                }
                indice++;
        }
        this.listaDeDescripcionCompleta.remove(indice);
        return false;
    }
}
