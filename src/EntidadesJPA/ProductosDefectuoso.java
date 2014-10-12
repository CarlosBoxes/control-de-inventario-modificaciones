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
@Table(name = "productos defectuoso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosDefectuoso.findAll", query = "SELECT p FROM ProductosDefectuoso p"),
    @NamedQuery(name = "ProductosDefectuoso.findByIdProductoDefectuoso", query = "SELECT p FROM ProductosDefectuoso p WHERE p.idProductoDefectuoso = :idProductoDefectuoso"),
    @NamedQuery(name = "ProductosDefectuoso.findByCantidad", query = "SELECT p FROM ProductosDefectuoso p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "ProductosDefectuoso.findByDescripcion", query = "SELECT p FROM ProductosDefectuoso p WHERE p.descripcion = :descripcion")})
public class ProductosDefectuoso implements Serializable {
    @Basic(optional = false)
    @Column(name = "cantidad")
    private float cantidad;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProductoDefectuoso")
    private Integer idProductoDefectuoso;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "vendedores_idvendedores", referencedColumnName = "idvendedores")
    @ManyToOne(optional = false)
    private Vendedores vendedoresIdvendedores;
    @JoinColumn(name = "Productos_idProductos", referencedColumnName = "idProductos")
    @ManyToOne(optional = false)
    private Productos productosidProductos;

    public ProductosDefectuoso() {
    }

    public ProductosDefectuoso(Integer idProductoDefectuoso) {
        this.idProductoDefectuoso = idProductoDefectuoso;
    }

    public ProductosDefectuoso(Integer idProductoDefectuoso, int cantidad) {
        this.idProductoDefectuoso = idProductoDefectuoso;
        this.cantidad = cantidad;
    }

    public Integer getIdProductoDefectuoso() {
        return idProductoDefectuoso;
    }

    public void setIdProductoDefectuoso(Integer idProductoDefectuoso) {
        this.idProductoDefectuoso = idProductoDefectuoso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Vendedores getVendedoresIdvendedores() {
        return vendedoresIdvendedores;
    }

    public void setVendedoresIdvendedores(Vendedores vendedoresIdvendedores) {
        this.vendedoresIdvendedores = vendedoresIdvendedores;
    }

    public Productos getProductosidProductos() {
        return productosidProductos;
    }

    public void setProductosidProductos(Productos productosidProductos) {
        this.productosidProductos = productosidProductos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductoDefectuoso != null ? idProductoDefectuoso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosDefectuoso)) {
            return false;
        }
        ProductosDefectuoso other = (ProductosDefectuoso) object;
        if ((this.idProductoDefectuoso == null && other.idProductoDefectuoso != null) || (this.idProductoDefectuoso != null && !this.idProductoDefectuoso.equals(other.idProductoDefectuoso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ProductosDefectuoso[ idProductoDefectuoso=" + idProductoDefectuoso + " ]";
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
}
