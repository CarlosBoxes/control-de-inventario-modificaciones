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
@Table(name = "tipo vendedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVendedores.findAll", query = "SELECT t FROM TipoVendedores t"),
    @NamedQuery(name = "TipoVendedores.findByIdTipoVendedores", query = "SELECT t FROM TipoVendedores t WHERE t.idTipoVendedores = :idTipoVendedores"),
    @NamedQuery(name = "TipoVendedores.findByNombre", query = "SELECT t FROM TipoVendedores t WHERE t.nombre = :nombre AND t.eliminado = false"),
    @NamedQuery(name = "TipoVendedores.findByNombreLike", query = "SELECT t FROM TipoVendedores t WHERE t.nombre LIKE :nombre AND t.eliminado = false"),
    @NamedQuery(name = "TipoVendedores.findByListaNombre", query = "SELECT t FROM TipoVendedores t WHERE t.eliminado = false"),
    @NamedQuery(name = "TipoVendedores.findByEliminado", query = "SELECT t FROM TipoVendedores t WHERE t.eliminado = :eliminado")})
public class TipoVendedores implements Serializable {
    @Basic(optional = false)
    @Column(name = "listaproductos")
    private boolean listaproductos;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipo_Vendedores")
    private Integer idTipoVendedores;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoVendedoresidTipoVendedores")
    private Collection<Vendedores> vendedoresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipovendedoresidTipoVendedores")
    private Collection<ProductosVendedores> productosvendedoresCollection;

    public TipoVendedores() {
    }

    public TipoVendedores(Integer idTipoVendedores) {
        this.idTipoVendedores = idTipoVendedores;
    }

    public TipoVendedores(Integer idTipoVendedores, String nombre, boolean eliminado) {
        this.idTipoVendedores = idTipoVendedores;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Integer getIdTipoVendedores() {
        return idTipoVendedores;
    }

    public void setIdTipoVendedores(Integer idTipoVendedores) {
        this.idTipoVendedores = idTipoVendedores;
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

    @XmlTransient
    public Collection<Vendedores> getVendedoresCollection() {
        return vendedoresCollection;
    }

    public void setVendedoresCollection(Collection<Vendedores> vendedoresCollection) {
        this.vendedoresCollection = vendedoresCollection;
    }

    @XmlTransient
    public Collection<ProductosVendedores> getProductosvendedoresCollection() {
        return productosvendedoresCollection;
    }

    public void setProductosvendedoresCollection(Collection<ProductosVendedores> productosvendedoresCollection) {
        this.productosvendedoresCollection = productosvendedoresCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoVendedores != null ? idTipoVendedores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVendedores)) {
            return false;
        }
        TipoVendedores other = (TipoVendedores) object;
        if ((this.idTipoVendedores == null && other.idTipoVendedores != null) || (this.idTipoVendedores != null && !this.idTipoVendedores.equals(other.idTipoVendedores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TipoVendedores[ idTipoVendedores=" + idTipoVendedores + " ]";
    }

    public boolean getListaproductos() {
        return listaproductos;
    }

    public void setListaproductos(boolean listaproductos) {
        this.listaproductos = listaproductos;
    }
    
}
