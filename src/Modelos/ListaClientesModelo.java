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
public class ListaClientesModelo
{
        public SimpleIntegerProperty Id;
        public SimpleIntegerProperty IdCliente;
        public SimpleStringProperty Nombre;
        
        public ListaClientesModelo(Integer Id, Integer IdProducto, String Nombre)
        {
            this.Id = new SimpleIntegerProperty(Id);
            this.IdCliente = new SimpleIntegerProperty(IdProducto);
            this.Nombre = new SimpleStringProperty(Nombre);
        }
        
        public Integer getId()
        {
            return Id.get();
        }
        
        public void setId(Integer Id)
        {
            this.Id.set(Id);
        }
        
        public Integer getIdCliente()
        {
            return IdCliente.get();
        }
        
        public void setIdCliente(Integer Id)
        {
            this.IdCliente.set(Id);
        }
        
        public String getNombre()
        {
            return Nombre.get();
        }
        
        public void setNombre(String Nombre)
        {
            this.Nombre.set(Nombre);
        }
        
}
