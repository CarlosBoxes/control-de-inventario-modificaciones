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
@Table(name = "tipo clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoClientes.findAll", query = "SELECT t FROM TipoClientes t"),
    @NamedQuery(name = "TipoClientes.findByIdTipoClientes", query = "SELECT t FROM TipoClientes t WHERE t.idTipoClientes = :idTipoClientes"),
    @NamedQuery(name = "TipoClientes.findByNombre", query = "SELECT t FROM TipoClientes t WHERE t.nombre = :nombre AND t.eliminado = false"),
    @NamedQuery(name = "TipoClientes.findByListaNombre", query = "SELECT t FROM TipoClientes t WHERE t.eliminado = false"),
    @NamedQuery(name = "TipoClientes.findByNombreLike", query = "SELECT t FROM TipoClientes t WHERE t.nombre LIKE :nombre AND t.eliminado = false"),
    @NamedQuery(name = "TipoClientes.findByEliminado", query = "SELECT t FROM TipoClientes t WHERE t.eliminado = :eliminado")})
public class TipoClientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipo_Clientes")
    private Integer idTipoClientes;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoClientesidTipoClientes")
    private Collection<Clientes> clientesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoclientesidTipoClientes")
    private Collection<ProductosClientes> productosclientesCollection;

    public TipoClientes() {
    }

    public TipoClientes(Integer idTipoClientes) {
        this.idTipoClientes = idTipoClientes;
    }

    public TipoClientes(Integer idTipoClientes, String nombre, boolean eliminado) {
        this.idTipoClientes = idTipoClientes;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Integer getIdTipoClientes() {
        return idTipoClientes;
    }

    public void setIdTipoClientes(Integer idTipoClientes) {
        this.idTipoClientes = idTipoClientes;
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
    public Collection<Clientes> getClientesCollection() {
        return clientesCollection;
    }

    public void setClientesCollection(Collection<Clientes> clientesCollection) {
        this.clientesCollection = clientesCollection;
    }

    @XmlTransient
    public Collection<ProductosClientes> getProductosclientesCollection() {
        return productosclientesCollection;
    }

    public void setProductosclientesCollection(Collection<ProductosClientes> productosclientesCollection) {
        this.productosclientesCollection = productosclientesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoClientes != null ? idTipoClientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoClientes)) {
            return false;
        }
        TipoClientes other = (TipoClientes) object;
        if ((this.idTipoClientes == null && other.idTipoClientes != null) || (this.idTipoClientes != null && !this.idTipoClientes.equals(other.idTipoClientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TipoClientes[ idTipoClientes=" + idTipoClientes + " ]";
    }
    
}
