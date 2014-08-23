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
public class DescripcionDespachoModelo
{
        public SimpleIntegerProperty IdProducto;
        public SimpleStringProperty Nombre;
        public SimpleIntegerProperty Cantidad;
        public SimpleIntegerProperty Facturado;
        public SimpleFloatProperty SubTotal;
        public SimpleFloatProperty Precio;
        
        public DescripcionDespachoModelo(Integer Id, String Nombre, Integer Cantidad, Integer Facturado, float SubTotal,float Precio)
        {
            this.IdProducto = new SimpleIntegerProperty(Id);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Cantidad = new SimpleIntegerProperty(Cantidad);
            this.Facturado = new SimpleIntegerProperty(Facturado);
            this.SubTotal = new SimpleFloatProperty(SubTotal);
            this.Precio = new SimpleFloatProperty(Precio);
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
        
        public Integer getFacturado()
        {
            return Facturado.get();
        }
        
        public float getSubTotal()
        {
            return SubTotal.get();
        }
        
        public float getPrecio()
        {
            return Precio.get();
        }
}
