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
@Table(name = "materiaprima")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materiaprima.findAll", query = "SELECT m FROM MateriaPrima m"),
    @NamedQuery(name = "Materiaprima.findByIdmateriaPrima", query = "SELECT m FROM MateriaPrima m WHERE m.idmateriaPrima = :idmateriaPrima"),
    @NamedQuery(name = "Materiaprima.findByNombre", query = "SELECT m FROM MateriaPrima m WHERE m.nombre = :nombre AND m.eliminado = false"),
    @NamedQuery(name = "Materiaprima.findByNombreLike", query = "SELECT m FROM MateriaPrima m WHERE m.nombre LIKE :nombre AND m.eliminado = false"),
    @NamedQuery(name = "Materiaprima.findByPrecio", query = "SELECT m FROM MateriaPrima m WHERE m.precio = :precio"),
    @NamedQuery(name = "Materiaprima.findByListaNombre", query = "SELECT m FROM MateriaPrima m WHERE m.eliminado = false"),
    @NamedQuery(name = "Materiaprima.findByPresentacion", query = "SELECT m FROM MateriaPrima m WHERE m.presentacion = :presentacion"),
    @NamedQuery(name = "Materiaprima.findByEliminado", query = "SELECT m FROM MateriaPrima m WHERE m.eliminado = :eliminado")})
public class MateriaPrima implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiaPrimaidmateriaPrima")
    private Collection<DescripcionPedidoMateriaPrima> descripcionPedidoMateriaPrimaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiaPrimaidmateriaPrima")
    private Collection<InventarioMateriaPrima> inventarioMateriaPrimaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmateriaPrima")
    private Integer idmateriaPrima;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "presentacion")
    private String presentacion;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;

    public MateriaPrima() {
    }

    public MateriaPrima(Integer idmateriaPrima) {
        this.idmateriaPrima = idmateriaPrima;
    }

    public MateriaPrima(Integer idmateriaPrima, String nombre, String presentacion, boolean eliminado, float precio) {
        this.idmateriaPrima = idmateriaPrima;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.eliminado = eliminado;
        this.precio = precio;
    }

    public Integer getIdmateriaPrima() {
        return idmateriaPrima;
    }

    public void setIdmateriaPrima(Integer idmateriaPrima) {
        this.idmateriaPrima = idmateriaPrima;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmateriaPrima != null ? idmateriaPrima.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPrima)) {
            return false;
        }
        MateriaPrima other = (MateriaPrima) object;
        if ((this.idmateriaPrima == null && other.idmateriaPrima != null) || (this.idmateriaPrima != null && !this.idmateriaPrima.equals(other.idmateriaPrima))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.MateriaPrima[ idmateriaPrima=" + idmateriaPrima + " ]";
    }

    @XmlTransient
    public Collection<InventarioMateriaPrima> getInventarioMateriaPrimaCollection() {
        return inventarioMateriaPrimaCollection;
    }

    public void setInventarioMateriaPrimaCollection(Collection<InventarioMateriaPrima> inventarioMateriaPrimaCollection) {
        this.inventarioMateriaPrimaCollection = inventarioMateriaPrimaCollection;
    }

    @XmlTransient
    public Collection<DescripcionPedidoMateriaPrima> getDescripcionPedidoMateriaPrimaCollection() {
        return descripcionPedidoMateriaPrimaCollection;
    }

    public void setDescripcionPedidoMateriaPrimaCollection(Collection<DescripcionPedidoMateriaPrima> descripcionPedidoMateriaPrimaCollection) {
        this.descripcionPedidoMateriaPrimaCollection = descripcionPedidoMateriaPrimaCollection;
    }
    
}
