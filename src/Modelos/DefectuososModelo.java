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
public class DefectuososModelo
{
        public SimpleIntegerProperty Id;
        public SimpleStringProperty Nombre;
        public SimpleIntegerProperty Cantidad;
        public SimpleStringProperty Vendedor;
        public SimpleStringProperty Descripcion;
        
        public DefectuososModelo(Integer Id, String Nombre, Integer Cantidad,String Vendedor, String Descripcion)
        {
            this.Id = new SimpleIntegerProperty(Id);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Cantidad = new SimpleIntegerProperty(Cantidad);
            this.Vendedor = new SimpleStringProperty(Vendedor);
            this.Descripcion = new SimpleStringProperty(Descripcion);
        }
        
        public Integer getId()
        {
            return Id.get();
        }
        
        public String getNombre()
        {
            return Nombre.get();
        }
        
        public Integer getCantidad()
        {
            return Cantidad.get();
        }
        
        public String getVendedor()
        {
            return Vendedor.get();
        }
        
        public String getDescripcion()
        {
            return Descripcion.get();
        }
}
