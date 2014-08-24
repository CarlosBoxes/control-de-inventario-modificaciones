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
@Table(name = "inventario producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioProducto.findAll", query = "SELECT i FROM InventarioProducto i"),
    @NamedQuery(name = "InventarioProducto.findByIdInventarioProducto", query = "SELECT i FROM InventarioProducto i WHERE i.idInventarioProducto = :idInventarioProducto"),
    @NamedQuery(name = "InventarioProducto.findByInventario", query = "SELECT i FROM InventarioProducto i WHERE i.productosidProductos = :idProducto"),
    @NamedQuery(name = "InventarioProducto.findByCantidad", query = "SELECT i FROM InventarioProducto i WHERE i.cantidad = :cantidad")})
public class InventarioProducto implements Serializable {
    @Basic(optional = false)
    @Column(name = "cantidad")
    private float cantidad;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInventarioProducto")
    private Integer idInventarioProducto;
    @JoinColumn(name = "Productos_idProductos", referencedColumnName = "idProductos")
    @ManyToOne(optional = false)
    private Productos productosidProductos;
    @JoinColumn(name = "bodegaProductos_idbodega", referencedColumnName = "idbodega")
    @ManyToOne(optional = false)
    private BodegaProductos bodegaProductosidbodega;

    public InventarioProducto() {
    }

    public InventarioProducto(Integer idInventarioProducto) {
        this.idInventarioProducto = idInventarioProducto;
    }

    public InventarioProducto(Integer idInventarioProducto, int cantidad) {
        this.idInventarioProducto = idInventarioProducto;
        this.cantidad = cantidad;
    }

    public Integer getIdInventarioProducto() {
        return idInventarioProducto;
    }

    public void setIdInventarioProducto(Integer idInventarioProducto) {
        this.idInventarioProducto = idInventarioProducto;
    }

    public Productos getProductosidProductos() {
        return productosidProductos;
    }

    public void setProductosidProductos(Productos productosidProductos) {
        this.productosidProductos = productosidProductos;
    }

    public BodegaProductos getBodegaProductosidbodega() {
        return bodegaProductosidbodega;
    }

    public void setBodegaProductosidbodega(BodegaProductos bodegaProductosidbodega) {
        this.bodegaProductosidbodega = bodegaProductosidbodega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventarioProducto != null ? idInventarioProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioProducto)) {
            return false;
        }
        InventarioProducto other = (InventarioProducto) object;
        if ((this.idInventarioProducto == null && other.idInventarioProducto != null) || (this.idInventarioProducto != null && !this.idInventarioProducto.equals(other.idInventarioProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.InventarioProducto[ idInventarioProducto=" + idInventarioProducto + " ]";
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
}
