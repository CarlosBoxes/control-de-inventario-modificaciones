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
@Table(name = "bodegaproduccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bodegaproduccion.findAll", query = "SELECT b FROM BodegaProduccion b"),
    @NamedQuery(name = "Bodegaproduccion.findByIdbodegaProduccion", query = "SELECT b FROM BodegaProduccion b WHERE b.idbodegaProduccion = :idbodegaProduccion"),
    @NamedQuery(name = "Bodegaproduccion.findByNombre", query = "SELECT b FROM BodegaProduccion b WHERE b.nombre = :nombre AND b.eliminado = false"),
    @NamedQuery(name = "Bodegaproduccion.findByNombreLike", query = "SELECT b FROM BodegaProduccion b WHERE b.nombre LIKE :nombre AND b.eliminado = false"),
    @NamedQuery(name = "Bodegaproduccion.findByListaNombre", query = "SELECT b FROM BodegaProduccion b WHERE b.eliminado = false"),
    @NamedQuery(name = "Bodegaproduccion.findByEliminado", query = "SELECT b FROM BodegaProduccion b WHERE b.eliminado = :eliminado")})
public class BodegaProduccion implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodegaProduccionidbodegaProduccion")
    private Collection<InventarioMateriaPrima> inventarioMateriaPrimaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbodegaProduccion")
    private Integer idbodegaProduccion;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;

    public BodegaProduccion() {
    }

    public BodegaProduccion(Integer idbodegaProduccion) {
        this.idbodegaProduccion = idbodegaProduccion;
    }

    public BodegaProduccion(Integer idbodegaProduccion, String nombre, boolean eliminado) {
        this.idbodegaProduccion = idbodegaProduccion;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Integer getIdbodegaProduccion() {
        return idbodegaProduccion;
    }

    public void setIdbodegaProduccion(Integer idbodegaProduccion) {
        this.idbodegaProduccion = idbodegaProduccion;
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
        hash += (idbodegaProduccion != null ? idbodegaProduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BodegaProduccion)) {
            return false;
        }
        BodegaProduccion other = (BodegaProduccion) object;
        if ((this.idbodegaProduccion == null && other.idbodegaProduccion != null) || (this.idbodegaProduccion != null && !this.idbodegaProduccion.equals(other.idbodegaProduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.BodegaProduccion[ idbodegaProduccion=" + idbodegaProduccion + " ]";
    }

    @XmlTransient
    public Collection<InventarioMateriaPrima> getInventarioMateriaPrimaCollection() {
        return inventarioMateriaPrimaCollection;
    }

    public void setInventarioMateriaPrimaCollection(Collection<InventarioMateriaPrima> inventarioMateriaPrimaCollection) {
        this.inventarioMateriaPrimaCollection = inventarioMateriaPrimaCollection;
    }
    
}
