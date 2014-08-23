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
public class ProductoModelo
{
        public SimpleIntegerProperty Id;
        public SimpleStringProperty Nombre;
        public SimpleIntegerProperty Cantidad;
        public SimpleStringProperty Presentacion;
        public SimpleIntegerProperty UnidadMedida;
        public SimpleFloatProperty PrecioCosto;
        public SimpleFloatProperty PrecioVenta;
        public SimpleStringProperty FechaVencimiento;
        public SimpleStringProperty Descripcion;
        public SimpleStringProperty Categoria;
        
        public ProductoModelo(Integer Id, String Nombre, int Cantidad , String Presentacion, int UnidadMedida, float PrecioCosto, float PrecioVenta, String FechaVencimiento, String Descripcion, String Categoria)
        {
            this.Id = new SimpleIntegerProperty(Id);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Cantidad = new SimpleIntegerProperty(Cantidad);
            this.Presentacion = new SimpleStringProperty(Presentacion);
            this.UnidadMedida = new SimpleIntegerProperty(UnidadMedida);
            this.PrecioCosto = new SimpleFloatProperty(PrecioCosto);
            this.PrecioVenta = new SimpleFloatProperty(PrecioVenta);
            this.FechaVencimiento = new SimpleStringProperty(FechaVencimiento);
            this.Descripcion = new SimpleStringProperty(Descripcion);
            this.Categoria = new SimpleStringProperty(Categoria);
        }
        
        public Integer getId()
        {
            return Id.get();
        }
        
        public String getNombre()
        {
            return Nombre.get();
        }
        
        public int getCantidad()
        {
            return Cantidad.get();
        }
        
        public String getPresentacion()
        {
            return Presentacion.get();
        }
        
        public int getUnidadMedida()
        {
            return UnidadMedida.get();
        }
        
        public float getPrecioCosto()
        {
            return PrecioCosto.get();
        }
        
        public float getPrecioVenta()
        {
            return PrecioVenta.get();
        }
        
        public String getFechaVencimiento()
        {
            return FechaVencimiento.get();
        }
        
        public String getDescripcion()
        {
            return Descripcion.get();
        }
        
        public String getCategoria()
        {
            return Categoria.get();
        }
}
