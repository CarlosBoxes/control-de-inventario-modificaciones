/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author luis__000
 */
public class DevolucionModelo
{
        public SimpleStringProperty No;
        public SimpleStringProperty Nombre;
        public SimpleStringProperty Cantidad;
        public SimpleStringProperty Monto;
        public SimpleFloatProperty Precio;
        
        public DevolucionModelo(String nombre,String cantidad,String monto,String num,Float precio)
        {
            this.No = new SimpleStringProperty(num);
            this.Nombre = new SimpleStringProperty(nombre);
            this.Cantidad = new SimpleStringProperty(cantidad);
            this.Monto = new SimpleStringProperty(monto);
            this.Precio = new SimpleFloatProperty(precio);
        }
        
        public String getNo()
        {
            return No.get();
        }
        public String getNombre()
        {
            return Nombre.get();
        }
        public String getCantidad()
        {
            return Cantidad.get();
        }
        public String getMonto()
        {
            return Monto.get();
        }
        public Float getPrecio()
        {
            return Precio.get();
        }
}
