/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import EntidadesJPA.Clientes;
import EntidadesJPA.DescripcionClientes;
import EntidadesJPA.Pedido;
import GestorDeTablasJPA.IClientes;
import GestorDeTablasJPA.IPedido;
import Modelos.EspecialModelo;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ch470
 */
public class liquidacionEspecial {
    
    Pedido pedido;
    List<DescripcionLiquidacionEspecial> listaDeDescripcionResumida;
    
    IPedido gestorPedido;
    
    public liquidacionEspecial ()
    {
        pedido = new Pedido();
        listaDeDescripcionResumida = new ArrayList<DescripcionLiquidacionEspecial> ();
        gestorPedido = new IPedido();
    }
    
    public void iniciarLiquidacion (int idPedido)
    {
        this.pedido = this.gestorPedido.buscarPedidoPorId(idPedido);
        for (DescripcionClientes descripcion:this.pedido.getDescripcionClientesCollection())
        {
            if (this.listaDeDescripcionResumida.isEmpty())
            {
                DescripcionLiquidacionEspecial descripcionTemporal = new DescripcionLiquidacionEspecial ();
                descripcionTemporal.guardarDatos(descripcion.getClientesidCliente().getIdCliente(),idPedido,descripcion.getTotal());
                this.listaDeDescripcionResumida.add(descripcionTemporal);
            }
            else
            if (verificarSiExisteEnListaResumen(descripcion.getClientesidCliente().getIdCliente()))
            {
                for (DescripcionLiquidacionEspecial descripcionPropia:this.listaDeDescripcionResumida)
                {
                    if (descripcionPropia.getCliente().getIdCliente()==descripcion.getClientesidCliente().getIdCliente()                        )
                    {
                        buscarEnListaYSumar (descripcion.getClientesidCliente().getIdCliente(),descripcion.getTotal());
                        //descripcionPropia.sumarATotalPedido(descripcion.getTotal());
                        break;
                    }                
                }
            }
            else
            {
                    DescripcionLiquidacionEspecial descripcionTemporal = new DescripcionLiquidacionEspecial ();
                    descripcionTemporal.guardarDatos(descripcion.getClientesidCliente().getIdCliente(),idPedido,descripcion.getTotal());
                    this.listaDeDescripcionResumida.add(descripcionTemporal);
            }
        }
    }
    
    public void buscarEnListaYSumar (int idCliente,Float suma)
    {
        for (DescripcionLiquidacionEspecial descripcionPropia:this.listaDeDescripcionResumida)
        {
            if (descripcionPropia.getCliente().getIdCliente()==idCliente)
            {
                descripcionPropia.sumarATotalPedido(suma);
            }
        }
    }
    
    public boolean verificarSiExisteEnListaResumen(int idCliente)
    {
        boolean bandera=false;
         for (DescripcionLiquidacionEspecial descripcionPropia:this.listaDeDescripcionResumida)
            {
                if (idCliente==descripcionPropia.getCliente().getIdCliente())
                {
                    bandera = true;
                }
                else
                    bandera = false;
            }
         return bandera;
    }
    
     public ObservableList<EspecialModelo> descripcionCliente ()
    {
        
        int idCliente = 0;
        String NombreCliente ="";
        String RazonSocial ="";
        String TotalDelPedido ="";
        String Cantidad  ="1";
        ObservableList<EspecialModelo> data =  FXCollections.observableArrayList();
        for (DescripcionLiquidacionEspecial descripcion:this.listaDeDescripcionResumida)
        {
            idCliente = descripcion.getCliente().getIdCliente();
            NombreCliente = descripcion.getCliente().getNombre();
            RazonSocial = descripcion.getCliente().getRazonsocial();
            TotalDelPedido = String.valueOf(descripcion.getTotalPedidoCliente());
            EspecialModelo modelo = new EspecialModelo (idCliente,NombreCliente+" "+"("+RazonSocial+")",descripcion.getCliente().getSaldo(),String.valueOf(descripcion.getAbono()),descripcion.getNuevoSaldo(),descripcion.getTotalPedidoCliente());
            data.add(modelo);
        }
        return data;
    }
     
    public DescripcionLiquidacionEspecial buscarDescripcionPorCliente (int idClienteA)
    {
        for (DescripcionLiquidacionEspecial descripcionPropia:this.listaDeDescripcionResumida)
        {
            if (descripcionPropia.getCliente().getIdCliente()==idClienteA)
            {
                return descripcionPropia;
            }
        }
        return null;
    }
    
    public void AbonoDeCliente (Float abono,int idClienteA)
    {
        DescripcionLiquidacionEspecial descripcionLiquidacion = new DescripcionLiquidacionEspecial();
        descripcionLiquidacion = this.buscarDescripcionPorCliente(idClienteA);
        descripcionLiquidacion.Abonar(abono);
    }
    
    public Float getTotalPedido ()
    {
        return this.pedido.getTotal();
    }
    
    public Float generarTotalAPagar()
    {
        float totalNuevo = 0;
        for (DescripcionLiquidacionEspecial descripcionPropia:this.listaDeDescripcionResumida)
        {
            totalNuevo = totalNuevo + descripcionPropia.getAbono();
        }
        return totalNuevo;
    }
    
    public void guardarNuevosSaldosAClientes()
    {
        Clientes clienteAModificar;
        IClientes gestorClientes = new IClientes();
        for (DescripcionLiquidacionEspecial descripcionPropia:this.listaDeDescripcionResumida)
        {
            clienteAModificar = new Clientes();
            clienteAModificar = gestorClientes.buscarClientesPorId(descripcionPropia.getCliente().getIdCliente());
            clienteAModificar.setSaldo(descripcionPropia.getNuevoSaldo());
            gestorClientes.modificar(clienteAModificar);
        }
    }
}
