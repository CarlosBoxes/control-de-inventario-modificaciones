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
public class DescripcionFModelo
{
        public SimpleIntegerProperty IdProducto;
        public SimpleStringProperty Nombre;
        public SimpleIntegerProperty Cantidad;
        public SimpleStringProperty Precio;
        public SimpleFloatProperty SubTotal;
        
        public DescripcionFModelo(Integer Id, String Nombre, Integer Cantidad,String precio, float SubTotal)
        {
            this.IdProducto = new SimpleIntegerProperty(Id);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Cantidad = new SimpleIntegerProperty(Cantidad);
            this.Precio = new SimpleStringProperty(precio);
            this.SubTotal = new SimpleFloatProperty(SubTotal);
        }
        
        public Integer getIdProducto()
        {
            return IdProducto.get();
        }
        
        public String getNombre()
        {
            return Nombre.get();
        }
        
        public Integer getCantidad()
        {
            return Cantidad.get();
        }
        
        public String getPrecio()
        {
            return Precio.get();
        }
        
        public void setPrecio(String precio)
        {
            Precio.set(precio);
        }
        
        public float getSubTotal()
        {
            return SubTotal.get();
        }
        
        public void setSubTotal(Float SubT)
        {
            SubTotal.set(SubT);
        }
}
