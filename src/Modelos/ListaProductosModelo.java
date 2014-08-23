/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author luis__000
 */
public class ListaProductosModelo
{
        public SimpleIntegerProperty Id;
        public SimpleIntegerProperty IdProducto;
        public SimpleStringProperty Nombre;
        public SimpleStringProperty Precio;
        
        public ListaProductosModelo(Integer Id, Integer IdProducto, String Nombre, String Precio)
        {
            this.Id = new SimpleIntegerProperty(Id);
            this.IdProducto = new SimpleIntegerProperty(IdProducto);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Precio = new SimpleStringProperty(Precio);
        }
        
        public Integer getId()
        {
            return Id.get();
        }
        
        public void setId(Integer Id)
        {
            this.Id.set(Id);
        }
        
        public Integer getIdProducto()
        {
            return IdProducto.get();
        }
        
        public void setIdProducto(Integer Id)
        {
            this.IdProducto.set(Id);
        }
        
        public String getNombre()
        {
            return Nombre.get();
        }
        
        public void setNombre(String Nombre)
        {
            this.Nombre.set(Nombre);
        }
        
        public String getPrecio()
        {
            return Precio.get();
        }
        
        public void setPrecio(String Precio)
        {
            this.Precio.set(Precio);
        }
}
