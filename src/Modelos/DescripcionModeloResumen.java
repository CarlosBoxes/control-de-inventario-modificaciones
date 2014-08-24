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
public class DescripcionModeloResumen
{
        public SimpleIntegerProperty IdProducto;
        public SimpleStringProperty Nombre;
        public SimpleFloatProperty Cantidad;
        public SimpleFloatProperty Precio;
        public SimpleFloatProperty SubTotal;
        
        public DescripcionModeloResumen(Integer Id, String Nombre, float Cantidad,float precio, float SubTotal)
        {
            this.IdProducto = new SimpleIntegerProperty(Id);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Cantidad = new SimpleFloatProperty(Cantidad);
            this.Precio = new SimpleFloatProperty(precio);
            this.SubTotal = new SimpleFloatProperty(SubTotal);
        }
        
        public void EditarDescripcionModeloResumen(float Cantidad,float SubTotal)
        {
            this.Cantidad = new SimpleFloatProperty(Cantidad);
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
        
        public float getCantidad()
        {
            return Cantidad.get();
        }
        
        public float getPrecio()
        {
            return Precio.get();
        }
        
        public float getSubTotal()
        {
            return SubTotal.get();
        }
}
