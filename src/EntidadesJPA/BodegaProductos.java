/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "bodegaproductos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bodegaproductos.findAll", query = "SELECT b FROM BodegaProductos b"),
    @NamedQuery(name = "Bodegaproductos.findByIdbodega", query = "SELECT b FROM BodegaProductos b WHERE b.idbodega = :idbodega"),
    @NamedQuery(name = "Bodegaproductos.findByNombre", query = "SELECT b FROM BodegaProductos b WHERE b.nombre = :nombre AND b.eliminado = false"),
    @NamedQuery(name = "Bodegaproductos.findByNombreLike", query = "SELECT b FROM BodegaProductos b WHERE b.nombre LIKE :nombre AND b.eliminado = false"),
    @NamedQuery(name = "Bodegaproductos.findByListaNombre", query = "SELECT b FROM BodegaProductos b WHERE b.eliminado = false"),
    @NamedQuery(name = "Bodegaproductos.findByEliminado", query = "SELECT b FROM BodegaProductos b WHERE b.eliminado = :eliminado")})
public class BodegaProductos implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodegaProductosidbodega")
    private Collection<InventarioProducto> inventarioProductoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbodega")
    private Integer idbodega;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;

    public BodegaProductos() {
    }

    public BodegaProductos(Integer idbodega) {
        this.idbodega = idbodega;
    }

    public BodegaProductos(Integer idbodega, String nombre, boolean eliminado) {
        this.idbodega = idbodega;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Integer getIdbodega() {
        return idbodega;
    }

    public void setIdbodega(Integer idbodega) {
        this.idbodega = idbodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbodega != null ? idbodega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BodegaProductos)) {
            return false;
        }
        BodegaProductos other = (BodegaProductos) object;
        if ((this.idbodega == null && other.idbodega != null) || (this.idbodega != null && !this.idbodega.equals(other.idbodega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.BodegaProductos[ idbodega=" + idbodega + " ]";
    }

    @XmlTransient
    public Collection<InventarioProducto> getInventarioProductoCollection() {
        return inventarioProductoCollection;
    }

    public void setInventarioProductoCollection(Collection<InventarioProducto> inventarioProductoCollection) {
        this.inventarioProductoCollection = inventarioProductoCollection;
    }
    
}
