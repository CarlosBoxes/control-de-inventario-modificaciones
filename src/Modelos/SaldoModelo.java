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
public class SaldoModelo
{
    public SimpleIntegerProperty Id;
    public SimpleStringProperty NoFactura;
    public SimpleStringProperty Fecha;
    public SimpleFloatProperty Saldo;

    public SaldoModelo(Integer Id, String NoFactura, String Fecha, Float Saldo)
    {
        this.Id = new SimpleIntegerProperty(Id);
        this.NoFactura = new SimpleStringProperty(NoFactura);
        this.Fecha = new SimpleStringProperty(Fecha);
        this.Saldo = new SimpleFloatProperty(Saldo);
    }

    public Integer getId()
    {
        return Id.get();
    }
    
    public String getNoFactura()
    {
        return NoFactura.get();
    }

    public String getFecha()
    {
        return Fecha.get();
    }

    public Float getSaldo()
    {
        return Saldo.get();
    }
}
