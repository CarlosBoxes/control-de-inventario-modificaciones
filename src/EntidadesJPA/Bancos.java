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
@Table(name = "bancos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bancos.findAll", query = "SELECT b FROM Bancos b"),
    @NamedQuery(name = "Bancos.findByIdbancos", query = "SELECT b FROM Bancos b WHERE b.idbancos = :idbancos"),
    @NamedQuery(name = "Bancos.findByNombre", query = "SELECT b FROM Bancos b WHERE b.nombre = :nombre AND b.eliminado = false"),
    @NamedQuery(name = "Bancos.findByListaNombre", query = "SELECT b FROM Bancos b WHERE b.eliminado = false"),
    @NamedQuery(name = "Bancos.findByNombreLike", query = "SELECT b FROM Bancos b WHERE b.nombre LIKE :nombre AND b.eliminado = false"),
    @NamedQuery(name = "Bancos.findByTelefono", query = "SELECT b FROM Bancos b WHERE b.telefono = :telefono"),
    @NamedQuery(name = "Bancos.findByEliminado", query = "SELECT b FROM Bancos b WHERE b.eliminado = :eliminado")})
public class Bancos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbancos")
    private Integer idbancos;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bancosIdbancos")
    private Collection<ChequesProveedores> chequesProveedoresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bancosIdbancos")
    private Collection<Depositos> depositosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bancosIdbancos")
    private Collection<ChequesClientes> chequesClientesCollection;

    public Bancos() {
    }

    public Bancos(Integer idbancos) {
        this.idbancos = idbancos;
    }

    public Bancos(Integer idbancos, String nombre, String telefono, boolean eliminado) {
        this.idbancos = idbancos;
        this.nombre = nombre;
        this.telefono = telefono;
        this.eliminado = eliminado;
    }

    public Integer getIdbancos() {
        return idbancos;
    }

    public void setIdbancos(Integer idbancos) {
        this.idbancos = idbancos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<ChequesProveedores> getChequesProveedoresCollection() {
        return chequesProveedoresCollection;
    }

    public void setChequesProveedoresCollection(Collection<ChequesProveedores> chequesProveedoresCollection) {
        this.chequesProveedoresCollection = chequesProveedoresCollection;
    }

    @XmlTransient
    public Collection<Depositos> getDepositosCollection() {
        return depositosCollection;
    }

    public void setDepositosCollection(Collection<Depositos> depositosCollection) {
        this.depositosCollection = depositosCollection;
    }

    @XmlTransient
    public Collection<ChequesClientes> getChequesClientesCollection() {
        return chequesClientesCollection;
    }

    public void setChequesClientesCollection(Collection<ChequesClientes> chequesClientesCollection) {
        this.chequesClientesCollection = chequesClientesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbancos != null ? idbancos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bancos)) {
            return false;
        }
        Bancos other = (Bancos) object;
        if ((this.idbancos == null && other.idbancos != null) || (this.idbancos != null && !this.idbancos.equals(other.idbancos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Bancos[ idbancos=" + idbancos + " ]";
    }
    
}
