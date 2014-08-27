/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "productoacambiar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productoacambiar.findAll", query = "SELECT p FROM ProductoaCambiar p"),
    @NamedQuery(name = "Productoacambiar.findByIdproductoacambiar", query = "SELECT p FROM ProductoaCambiar p WHERE p.idproductoacambiar = :idproductoacambiar"),
    @NamedQuery(name = "Productoacambiar.findByProducto", query = "SELECT p FROM ProductoaCambiar p WHERE p.producto = :producto"),
    @NamedQuery(name = "Productoacambiar.findByCantidad", query = "SELECT p FROM ProductoaCambiar p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Productoacambiar.findByPrecio", query = "SELECT p FROM ProductoaCambiar p WHERE p.precio = :precio")})
public class ProductoaCambiar implements Serializable {
    @Basic(optional = false)
    @Column(name = "Cantidad")
    private float cantidad;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproductoacambiar")
    private Integer idproductoacambiar;
    @Basic(optional = false)
    @Column(name = "Producto")
    private String producto;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @JoinColumn(name = "liquidacion_idliquidacion", referencedColumnName = "idliquidacion")
    @ManyToOne(optional = false)
    private Liquidacion liquidacionIdliquidacion;

    public ProductoaCambiar() {
    }

    public ProductoaCambiar(Integer idproductoacambiar) {
        this.idproductoacambiar = idproductoacambiar;
    }

    public ProductoaCambiar(Integer idproductoacambiar, String producto, int cantidad, float precio) {
        this.idproductoacambiar = idproductoacambiar;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getIdproductoacambiar() {
        return idproductoacambiar;
    }

    public void setIdproductoacambiar(Integer idproductoacambiar) {
        this.idproductoacambiar = idproductoacambiar;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Liquidacion getLiquidacionIdliquidacion() {
        return liquidacionIdliquidacion;
    }

    public void setLiquidacionIdliquidacion(Liquidacion liquidacionIdliquidacion) {
        this.liquidacionIdliquidacion = liquidacionIdliquidacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproductoacambiar != null ? idproductoacambiar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoaCambiar)) {
            return false;
        }
        ProductoaCambiar other = (ProductoaCambiar) object;
        if ((this.idproductoacambiar == null && other.idproductoacambiar != null) || (this.idproductoacambiar != null && !this.idproductoacambiar.equals(other.idproductoacambiar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ProductoaCambiar[ idproductoacambiar=" + idproductoacambiar + " ]";
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
}
