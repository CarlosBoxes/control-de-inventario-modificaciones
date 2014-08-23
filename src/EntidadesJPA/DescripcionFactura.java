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
@Table(name = "descripcion Factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescripcionFactura.findAll", query = "SELECT d FROM DescripcionFactura d"),
    @NamedQuery(name = "DescripcionFactura.findByIddescripcionFactura", query = "SELECT d FROM DescripcionFactura d WHERE d.iddescripcionFactura = :iddescripcionFactura"),
    @NamedQuery(name = "DescripcionFactura.findByPrecio", query = "SELECT d FROM DescripcionFactura d WHERE d.precio = :precio"),
    @NamedQuery(name = "DescripcionFactura.findByCantidad", query = "SELECT d FROM DescripcionFactura d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DescripcionFactura.findBySubTotal", query = "SELECT d FROM DescripcionFactura d WHERE d.subTotal = :subTotal")})
public class DescripcionFactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddescripcionFactura")
    private Integer iddescripcionFactura;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @Basic(optional = false)
    @Column(name = "Cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "SubTotal")
    private float subTotal;
    @JoinColumn(name = "productos_idProductos", referencedColumnName = "idProductos")
    @ManyToOne(optional = false)
    private Productos productosidProductos;
    @JoinColumn(name = "Facturas_idFacturas", referencedColumnName = "idFacturas")
    @ManyToOne(optional = false)
    private Facturas facturasidFacturas;

    public DescripcionFactura() {
    }

    public DescripcionFactura(Integer iddescripcionFactura) {
        this.iddescripcionFactura = iddescripcionFactura;
    }

    public DescripcionFactura(Integer iddescripcionFactura, float precio, int cantidad, float subTotal) {
        this.iddescripcionFactura = iddescripcionFactura;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public Integer getIddescripcionFactura() {
        return iddescripcionFactura;
    }

    public void setIddescripcionFactura(Integer iddescripcionFactura) {
        this.iddescripcionFactura = iddescripcionFactura;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public Productos getProductosidProductos() {
        return productosidProductos;
    }

    public void setProductosidProductos(Productos productosidProductos) {
        this.productosidProductos = productosidProductos;
    }

    public Facturas getFacturasidFacturas() {
        return facturasidFacturas;
    }

    public void setFacturasidFacturas(Facturas facturasidFacturas) {
        this.facturasidFacturas = facturasidFacturas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddescripcionFactura != null ? iddescripcionFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescripcionFactura)) {
            return false;
        }
        DescripcionFactura other = (DescripcionFactura) object;
        if ((this.iddescripcionFactura == null && other.iddescripcionFactura != null) || (this.iddescripcionFactura != null && !this.iddescripcionFactura.equals(other.iddescripcionFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesJPA.DescripcionFactura[ iddescripcionFactura=" + iddescripcionFactura + " ]";
    }

    }
