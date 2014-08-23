/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import EntidadesJPA.ChequesClientes;
import EntidadesJPA.Depositos;
import EntidadesJPA.DescripcionPedido;
import EntidadesJPA.Liquidacion;
import EntidadesJPA.Pedido;
import EntidadesJPA.Productos;
import EntidadesJPA.Vendedores;
import GestorDeTablasJPA.IChequesClientes;
import GestorDeTablasJPA.IDepositos;
import GestorDeTablasJPA.IDescripcionLiquidacion;
import GestorDeTablasJPA.ILiquidacion;
import GestorDeTablasJPA.IPedido;
import GestorDeTablasJPA.IProductos;
import GestorDeTablasJPA.IProductosACambiar;
import GestorDeTablasJPA.IProductosDefectuoso;
import GestorDeTablasJPA.IVendedores;
import Modelos.DefectuososModelo;
import Modelos.DescripcionLiquidacionModelo;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Ch470
 */
public class NuevaLiquidacionVariante {
        public NuevaLiquidacionVariante (){
        listaDePorcentajesDeDevoluciones = new ArrayList(); }
    
    //objetos propios de la clasea para el manejo y actualizacion de la liquidacion
    //
        private Pedido pedido = new Pedido ();
        private Liquidacion liquidacion = new Liquidacion();
        public Vendedores vendedor = new Vendedores ();
        
        private float total =0; //este pertenece al subtotal de la liquidacion
        private float liquidacionA =0; //este pertenece al total de la liquidacion
        private float saldoAnterior =0;
        private float nuevoSaldoAnterior = 0; //saldo que se va ir generando cuando se encuentre diferencia en la liquidacion y en el total
        private float contraseñas =0;
        private float saldoAnteriorSinActualizar;
        
    //
    //Manejadores para los objestos de eseta clase
        IPedido pedidoGestor = new IPedido ();
        ILiquidacion liquidacionGestor = new ILiquidacion();
        IVendedores vendedorGestor = new IVendedores ();
        IDescripcionLiquidacion descripcionLiquidacionGestor = new IDescripcionLiquidacion ();
        IProductos productosGestor = new IProductos ();
        IProductosACambiar productoACambiarGestor = new IProductosACambiar();
        ArrayList<ProductoACambiarTemporal> ListaProductoTemporal = new ArrayList<ProductoACambiarTemporal> ();
    //
    //Listas para el uso de las descripciones
        private List<DescripcionPedido> listaDeDescripcionesDePedido = new ArrayList ();
        private List<Float> listaDePorcentajesDeDevoluciones; //lista de porcentajes que tiene el pedido para devoluciones
        private List<ChequesClientes> listaDeCheques = new ArrayList<ChequesClientes>();
        private List<Depositos> listaDeDepositos = new ArrayList<Depositos>();
        public List<ProductoACambiar> listaDeProductosACambiar = new ArrayList<ProductoACambiar>(); //lista de productos a cambiar con sus datos
        
    //
    //procedimientos para el manejo de la liquidacion
        public String iniciarLiquidacion (int idPedido)
        {
            String mensaje ="";
            try
            {
                this.pedido = this.pedidoGestor.buscarPedidoPorId(idPedido);
                if (this.pedido == null)
                {
                    mensaje = "Pedido No Encontrado Verifique Id del Pedido.";
                    return mensaje;
                }
                else
                if (this.pedido.getEliminado()==true)
                {
                    mensaje = "Pedido Fue Eliminado";
                    return mensaje;
                }
                else
                    {
                        if(this.pedido.getLiquidado())
                        {
                            mensaje ="El Pedido Ya Fue Liquidado";
                            this.cancelar();
                            return mensaje;
                        }

                    else
                    {
                        this.vendedor = this.pedido.getVendedoresIdvendedores();
                        this.saldoAnterior = vendedor.getSaldoAnterior();
                        this.saldoAnteriorSinActualizar = vendedor.getSaldoAnterior();
                        this.total = this.pedido.getTotal() + this.saldoAnterior;
                        this.liquidacionA = 0;
                        generarListaDePorcentajes (); //genera la lsita de los porcentajes para el cambio
                        mensaje = this.vendedor.getNombre() +" "+this.vendedor.getApellido();
                        return mensaje;
                    }
                }
            }
            catch (Exception e)
            {
                mensaje = "Error En El Inicio De La Liquidación";
                return mensaje;
            }
        }
        
        public void generarListaDePorcentajes ()
        {
            boolean banderaVerificadora = false;
            for (DescripcionPedido descripcion:this.pedido.getDescripcionPedidoCollection())
            {
                if ((this.listaDePorcentajesDeDevoluciones.isEmpty())&&(descripcion.getProductosidProductos().getDevoluciones()!=0))
                {
                    this.listaDePorcentajesDeDevoluciones.add(descripcion.getProductosidProductos().getDevoluciones());
                    this.listaDeProductosACambiar.add(new ProductoACambiar(descripcion));
                }
                else
                {                    
                    if (descripcion.getProductosidProductos().getDevoluciones()!=0)
                    {
                        this.listaDeProductosACambiar.add(new ProductoACambiar(descripcion));
                        for (float valor:this.listaDePorcentajesDeDevoluciones)
                        {
                            if (descripcion.getProductosidProductos().getDevoluciones()==valor)
                            {
                                banderaVerificadora = true;
                                continue;
                            }
                            else
                            {
                                banderaVerificadora = false;
                            }
                        }
                        if (banderaVerificadora)
                        {
                            continue;
                        }
                        else
                        {
                             this.listaDePorcentajesDeDevoluciones.add(descripcion.getProductosidProductos().getDevoluciones());
                             banderaVerificadora = false;
                        }
                    }
                }
            }
        }
        
       public List<Productos> listaDeProductosAPorcentaje (float porcentaje)
       {
           List<Productos> lista = new ArrayList();
           for (DescripcionPedido descripcion:this.pedido.getDescripcionPedidoCollection())
           {
               if (descripcion.getProductosidProductos().getDevoluciones() == porcentaje)
               {
                   lista.add(descripcion.getProductosidProductos());
               }
           }
           return lista;
       }
       
       public List<ProductoACambiar> getistaDeProductosACambiar()
       {
           return this.listaDeProductosACambiar;
       }
       
       public float cambioARealizar (int posicion,int cantidad)
       {
           float descontar =0;
           float precioVenta = (float)this.listaDeProductosACambiar.get(posicion).precioAdevolver();
           descontar = precioVenta*cantidad;           
           return descontar;
       }
       
       public float derechoADevolver (float porcentaje)
       {
           float derecho =0;
           for (DescripcionPedido desc:this.pedido.getDescripcionPedidoCollection())
           {
               if (desc.getProductosidProductos().getDevoluciones()==porcentaje)
               {
               derecho = derecho+(desc.getCantidad()*desc.getProductosidProductos().getPrecioVenta());
               }
           }
           derecho = derecho * (porcentaje/100);
           return derecho;
           
       }
       
       public float montoADescontarDeDerechoADevolver (String nombre,int cantidad)
    {        
        float montoADescontar =0;
        montoADescontar = this.productosGestor.buscarProductoPorNombre(nombre).getPrecioVenta()*cantidad;
        return montoADescontar;
    }
       
     public void convertirTablaAListasDeChequesYDepositos (ObservableList<DescripcionLiquidacionModelo> data)
     {
         ChequesClientes cheque = new ChequesClientes();
         Depositos deposito = new Depositos ();
         IChequesClientes chequeGestor = new IChequesClientes();
         IDepositos depositoGestor = new IDepositos ();
         String numeroDeCheque = "";
         String numeroDeBoleta ="";
         
         for (DescripcionLiquidacionModelo modelo : data)
         {
             numeroDeCheque = modelo.getNumeroCheque();
             numeroDeBoleta = modelo.getNumeroBoleta();
             if (modelo.getNumeroBoleta()==null)
             {
                 cheque = chequeGestor.buscarChequesNumeroChueque(Integer.parseInt(numeroDeCheque));
                 cheque.setUsado(true);
                 chequeGestor.modificar(cheque);
                 this.listaDeCheques.add(cheque);
                 
             }
             else if (modelo.getNumeroCheque()==null)
             {
                 deposito = depositoGestor.buscarDepositoPorNumeroBoleta(numeroDeBoleta);
                 deposito.setUsado(true);
                 depositoGestor.modificar(deposito);
                 this.listaDeDepositos.add(deposito);
             }
         }
     }
     
     
      public void crearDescripciones ()
    {        
        if (!this.listaDeCheques.isEmpty())
        {
            for (ChequesClientes chequeIngresar:this.listaDeCheques)
            {
                descripcionLiquidacionGestor.guardar(liquidacion, null, chequeIngresar);
            }
        }
        if (!this.listaDeDepositos.isEmpty())
        {
             for (Depositos depositoIngresar : this.listaDeDepositos)
             {
                 descripcionLiquidacionGestor.guardar(liquidacion, depositoIngresar, null);
             }
        }
        
        
    }
      
      public void guardar (ObservableList<DescripcionLiquidacionModelo> data)
      {
         try{
                this.actualizarSaldoNuevoAnterior();
                this.vendedor.setSaldoAnterior(this.nuevoSaldoAnterior);
                this.vendedorGestor.modificar(vendedor);
                this.pedido.setLiquidado(true);
                this.pedidoGestor.modificar(this.pedido);
                liquidacionGestor.guardar(total, liquidacionA, pedido);          
                this.liquidacion = liquidacionGestor.retornarUltimaIngresada();
                this.convertirTablaAListasDeChequesYDepositos(data); 
                this.crearDescripciones();
                this.crearListaProductosADevolver ();
                this.listaDeProductosACambiar.clear();
          }
          catch (Exception e)
          {
              System.out.println(e);
          }
      }
      private void crearListaProductosADevolver ()
      {
          for (ProductoACambiarTemporal temp:this.ListaProductoTemporal)
          {
              this.productoACambiarGestor.guardar(temp.nombre, temp.cantidad, this.liquidacion,temp.precio);
          }
      }
      
      public void ingresarAlistaDeCambios (String nombre,int cantidad,float precio)
      {
          ProductoACambiarTemporal productoTemporal = new ProductoACambiarTemporal ();
          productoTemporal.setProducto(nombre, cantidad,precio);
          this.ListaProductoTemporal.add(productoTemporal);
      }
      
      public void GuardarProductosDefectuosos(ObservableList<DefectuososModelo> data)
      {
          if(!data.isEmpty())
          {
            for(DefectuososModelo Modelo: data)
            {
                 Productos Producto = new IProductos().buscarProductoPorId(Modelo.getId());
                new IProductosDefectuoso().guardar(Producto, Modelo.getCantidad(), Modelo.getDescripcion(), vendedor);
            }
          }
      }
      
      public void cancelar ()
      {
       listaDePorcentajesDeDevoluciones.clear(); 
       this.listaDeProductosACambiar.clear();
       this.ListaProductoTemporal.clear();
       this.listaDeCheques.clear();
       this.listaDeDepositos.clear();
       this.listaDeDescripcionesDePedido.clear();
       this.listaDePorcentajesDeDevoluciones.clear();
    //objetos propios de la clasea para el manejo y actualizacion de la liquidacion
    //
        pedido = new Pedido ();
        liquidacion = new Liquidacion();
        vendedor = new Vendedores ();
        
        total =0; //este pertenece al subtotal de la liquidacion
        liquidacionA =0; //este pertenece al total de la liquidacion
        saldoAnterior =0;
        nuevoSaldoAnterior = 0; //saldo que se va ir generando cuando se encuentre diferencia en la liquidacion y en el total
        this.saldoAnteriorSinActualizar=0;
    //
    
    //Listas para el uso de las descripciones
        listaDeDescripcionesDePedido.clear();
        listaDePorcentajesDeDevoluciones.clear(); 
        listaDeCheques.clear();
        listaDeDepositos.clear();
        listaDeProductosACambiar.clear();
    //
          
      }
        
    //
    //Metodos de Get  y Set
        public float getTotal ()
        {
            return this.total;
        }
        public float getSaldoAnterior ()
        {
            return this.saldoAnterior;
        }
        public float getSaldoAnteriorSinActualizar ()
        {
            return this.saldoAnteriorSinActualizar;
        }
        public float getTotalDelPedido ()
        {
            return this.pedido.getTotal();
        }
        public float getNuevoSaldoAnterior ()
        {
            return this.nuevoSaldoAnterior;
        }
        public float getLiquidacion ()
        {
            return this.liquidacionA;
        }
        public List getListaDePorcentajes ()
        {
            return this.listaDePorcentajesDeDevoluciones;
        }
    //
    //Metodos Para Actualizar los totales
        
        public void actualizarLiquidacion (float nuevaLiquidacion)
        {
            this.liquidacionA = nuevaLiquidacion;
            actualizarSaldoNuevoAnterior ();
        }
        
        
        public void actualizarSaldoNuevoAnterior ()
        {
            if (this.liquidacionA<this.total)
            {
                this.nuevoSaldoAnterior = this.total-this.liquidacionA;
            }
            else 
            {
                this.nuevoSaldoAnterior =0;
            }
        }
        
        public void actualizarTotalDePedidoPorDevolucion (float devolucion)
        {
            float totalDePedido =  0;
            totalDePedido = this.pedido.getTotal();
            totalDePedido = totalDePedido - devolucion;
            this.pedido.setTotal(totalDePedido);            
            this.actualizarTotal();
            actualizarSaldoNuevoAnterior ();
        }
        
        public void actualizarTotalDePedidoPorDevolucionSumarISR(float ISRTotal, float ISRReal, float TotalContraseñas)
        {
            float totalDePedido =  0;
            totalDePedido = this.pedido.getTotal();
            totalDePedido = totalDePedido - (this.pedido.getIsr()*this.pedido.getContraseñas());
            totalDePedido = totalDePedido +ISRTotal;
            this.pedido.setTotal(totalDePedido);    
            this.pedido.setIsr(ISRReal);
            this.pedido.setContraseñas(TotalContraseñas);
            this.actualizarTotal();
            actualizarSaldoNuevoAnterior ();
        }
        
        public void actualizarTotal()
        {
            this.total = this.pedido.getTotal() + this.saldoAnterior;
        }
    
        
        
}
