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
@Table(name = "inventario materia prima")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioMateriaPrima.findAll", query = "SELECT i FROM InventarioMateriaPrima i"),
    @NamedQuery(name = "InventarioMateriaPrima.findByIdInventarioMateriaPrima", query = "SELECT i FROM InventarioMateriaPrima i WHERE i.idInventarioMateriaPrima = :idInventarioMateriaPrima"),
    @NamedQuery(name = "InventarioMateriaPrima.findByInventario", query = "SELECT i FROM InventarioMateriaPrima i WHERE i.materiaPrimaidmateriaPrima = :idMateriaPrima"),
    @NamedQuery(name = "InventarioMateriaPrima.findByCantidad", query = "SELECT i FROM InventarioMateriaPrima i WHERE i.cantidad = :cantidad")})
public class InventarioMateriaPrima implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInventarioMateriaPrima")
    private Integer idInventarioMateriaPrima;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private float cantidad;
    @JoinColumn(name = "materiaPrima_idmateriaPrima", referencedColumnName = "idmateriaPrima")
    @ManyToOne(optional = false)
    private MateriaPrima materiaPrimaidmateriaPrima;
    @JoinColumn(name = "bodegaProduccion_idbodegaProduccion", referencedColumnName = "idbodegaProduccion")
    @ManyToOne(optional = false)
    private BodegaProduccion bodegaProduccionidbodegaProduccion;

    public InventarioMateriaPrima() {
    }

    public InventarioMateriaPrima(Integer idInventarioMateriaPrima) {
        this.idInventarioMateriaPrima = idInventarioMateriaPrima;
    }

    public InventarioMateriaPrima(Integer idInventarioMateriaPrima, float cantidad) {
        this.idInventarioMateriaPrima = idInventarioMateriaPrima;
        this.cantidad = cantidad;
    }

    public Integer getIdInventarioMateriaPrima() {
        return idInventarioMateriaPrima;
    }

    public void setIdInventarioMateriaPrima(Integer idInventarioMateriaPrima) {
        this.idInventarioMateriaPrima = idInventarioMateriaPrima;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public MateriaPrima getMateriaPrimaidmateriaPrima() {
        return materiaPrimaidmateriaPrima;
    }

    public void setMateriaPrimaidmateriaPrima(MateriaPrima materiaPrimaidmateriaPrima) {
        this.materiaPrimaidmateriaPrima = materiaPrimaidmateriaPrima;
    }

    public BodegaProduccion getBodegaProduccionidbodegaProduccion() {
        return bodegaProduccionidbodegaProduccion;
    }

    public void setBodegaProduccionidbodegaProduccion(BodegaProduccion bodegaProduccionidbodegaProduccion) {
        this.bodegaProduccionidbodegaProduccion = bodegaProduccionidbodegaProduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventarioMateriaPrima != null ? idInventarioMateriaPrima.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioMateriaPrima)) {
            return false;
        }
        InventarioMateriaPrima other = (InventarioMateriaPrima) object;
        if ((this.idInventarioMateriaPrima == null && other.idInventarioMateriaPrima != null) || (this.idInventarioMateriaPrima != null && !this.idInventarioMateriaPrima.equals(other.idInventarioMateriaPrima))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.InventarioMateriaPrima[ idInventarioMateriaPrima=" + idInventarioMateriaPrima + " ]";
    }
    
}
