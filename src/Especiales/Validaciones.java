/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Luis
 */
public class Validaciones 
{
    public Validaciones(){}
    
    public boolean ValidarNumero(String Numero)
    {
        Pattern Patron = Pattern.compile("[0-9]");
        Matcher Matcher = Patron.matcher(Numero);
        boolean Validar = Matcher.matches();
        return Validar;
    }
    public boolean ValidarMontos (String Numero)
    {
        Pattern Patron = Pattern.compile("[0-9]+(.[0-9][0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?)?");
        Matcher Matcher = Patron.matcher(Numero);
        boolean Validar = Matcher.matches();
        return Validar;
    }
    
    public boolean ValidarNumeros(String Numeros)
    {
        Pattern Patron = Pattern.compile("[0-9]+");
        Matcher Matcher = Patron.matcher(Numeros);
        boolean Validar = Matcher.matches();
        return Validar;
    }
    public boolean ValidarNumerosTelefonicos(String Numeros)
    {
        if (Numeros == null)
        {
            Numeros ="";
        }
        Pattern Patron = Pattern.compile("[0-9]*");
        Matcher Matcher = Patron.matcher(Numeros);
        boolean Validar = Matcher.matches();
        return Validar;
    }
    public boolean ValidarNumerosDecimales(String Numeros)
    {
        Pattern Patron = Pattern.compile("[0-9]+[.[0-9]*]");
        Matcher Matcher = Patron.matcher(Numeros);
        boolean Validar = Matcher.matches();
        return Validar;
    }
    
    public boolean FormatoFecha(String Fecha)
    {
        Pattern Patron = Pattern.compile("([0][1-9]|[1-2][0-9]|[3][0-1])/([0][1-9]|[1][0-2])/([2][0][1-9][0-9])");
        Matcher Matcher = Patron.matcher(Fecha);
        boolean Validar = Matcher.matches();
        return Validar;
    }
    
    public boolean FormatoNit(String Nit)
    {
        Pattern Patron = Pattern.compile("[0-9]*-([0-9]|[A-Z]|[a-z])");
        Matcher Matcher = Patron.matcher(Nit);
        boolean Validar = Matcher.matches();
        return Validar;
    }
    
}
