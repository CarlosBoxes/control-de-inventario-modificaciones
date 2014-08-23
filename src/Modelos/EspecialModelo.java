

/**
 *
 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author luis__000
 */
public class EspecialModelo
{
        
        public SimpleIntegerProperty IdCliente;
        public SimpleStringProperty Cliente;        
        public SimpleFloatProperty TotalDelPedido;
        public SimpleFloatProperty Saldo;
        public SimpleStringProperty Pago;        
        public SimpleFloatProperty NuevoSaldo;
        
        
        public EspecialModelo( Integer IdCliente, String Cliente, Float Saldo, String Pago, Float NuevoSaldo, Float TotalDelPedido)
        {
            this.IdCliente = new SimpleIntegerProperty(IdCliente);
            this.Cliente = new SimpleStringProperty(Cliente);
            this.Saldo = new SimpleFloatProperty(Saldo);
            this.NuevoSaldo = new SimpleFloatProperty(NuevoSaldo);
            this.Pago = new SimpleStringProperty(Pago);
            this.TotalDelPedido = new SimpleFloatProperty(TotalDelPedido);
        }
        
        
        
        public Integer getIdCliente()
        {
            return IdCliente.get();
        }
        
        public void setIdCliente(Integer IdCliente)
        {
            this.IdCliente.set(IdCliente);
        }
        
        public String getCliente()
        {
            return Cliente.get();
        }
        
        public void setCliene(String Cliente)
        {
            this.Cliente.set(Cliente);
        }
        
        
        public Float getSaldo()
        {
            return Saldo.get();
        }
        
        public void setSaldo(Float Saldo)
        {
            this.Saldo.set(Saldo);
        }
        
        public String getPago()
        {
            return Pago.get();
        }
        
        public void setPago(String Pago)
        {
            this.Pago.set(Pago);
        }
        
        public Float getNuevoSaldo()
        {
            return NuevoSaldo.get();
        }
        
        public void setNuevoSaldo(Float Precio)
        {
            this.NuevoSaldo.set(Precio);
        }
        
        public Float getTotalDelPedido()
        {
            return TotalDelPedido.get();
        }
        
        public void setTotalDelPedido(Float Total)
        {
            this.TotalDelPedido.set(Total);
        }
        
}
