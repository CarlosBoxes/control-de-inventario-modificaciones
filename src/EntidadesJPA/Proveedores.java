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
@Table(name = "proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedores.findAll", query = "SELECT p FROM Proveedores p"),
    @NamedQuery(name = "Proveedores.findByIdProveedores", query = "SELECT p FROM Proveedores p WHERE p.idProveedores = :idProveedores"),
    @NamedQuery(name = "Proveedores.findByNombre", query = "SELECT p FROM Proveedores p WHERE p.nombre = :nombre AND p.eliminado = false"),
    @NamedQuery(name = "Proveedores.findByNombreLike", query = "SELECT p FROM Proveedores p WHERE p.nombre LIKE :nombre AND p.eliminado = false"),
    @NamedQuery(name = "Proveedores.findByListaNombre", query = "SELECT p FROM Proveedores p WHERE p.eliminado = false"),
    @NamedQuery(name = "Proveedores.findByNit", query = "SELECT p FROM Proveedores p WHERE p.nit = :nit"),
    @NamedQuery(name = "Proveedores.findByDireccion", query = "SELECT p FROM Proveedores p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Proveedores.findByTelefono1", query = "SELECT p FROM Proveedores p WHERE p.telefono1 = :telefono1"),
    @NamedQuery(name = "Proveedores.findByTelefono2", query = "SELECT p FROM Proveedores p WHERE p.telefono2 = :telefono2"),
    @NamedQuery(name = "Proveedores.findBySaldo", query = "SELECT p FROM Proveedores p WHERE p.saldo = :saldo"),
    @NamedQuery(name = "Proveedores.findByEliminado", query = "SELECT p FROM Proveedores p WHERE p.eliminado = :eliminado")})
public class Proveedores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProveedores")
    private Integer idProveedores;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "nit")
    private String nit;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono1")
    private String telefono1;
    @Column(name = "telefono2")
    private String telefono2;
    @Basic(optional = false)
    @Column(name = "Saldo")
    private float saldo;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedoresidProveedores")
    private Collection<PedidoMateriaPrima> pedidoMateriaPrimaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedoresidProveedores")
    private Collection<ChequesProveedores> chequesProveedoresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedoresidProveedores")
    private Collection<PedidoProveedores> pedidoProveedoresCollection;

    public Proveedores() {
    }

    public Proveedores(Integer idProveedores) {
        this.idProveedores = idProveedores;
    }

    public Proveedores(Integer idProveedores, String nombre, String nit, float saldo, boolean eliminado) {
        this.idProveedores = idProveedores;
        this.nombre = nombre;
        this.nit = nit;
        this.saldo = saldo;
        this.eliminado = eliminado;
    }

    public Integer getIdProveedores() {
        return idProveedores;
    }

    public void setIdProveedores(Integer idProveedores) {
        this.idProveedores = idProveedores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<PedidoMateriaPrima> getPedidoMateriaPrimaCollection() {
        return pedidoMateriaPrimaCollection;
    }

    public void setPedidoMateriaPrimaCollection(Collection<PedidoMateriaPrima> pedidoMateriaPrimaCollection) {
        this.pedidoMateriaPrimaCollection = pedidoMateriaPrimaCollection;
    }

    @XmlTransient
    public Collection<ChequesProveedores> getChequesProveedoresCollection() {
        return chequesProveedoresCollection;
    }

    public void setChequesProveedoresCollection(Collection<ChequesProveedores> chequesProveedoresCollection) {
        this.chequesProveedoresCollection = chequesProveedoresCollection;
    }

    @XmlTransient
    public Collection<PedidoProveedores> getPedidoProveedoresCollection() {
        return pedidoProveedoresCollection;
    }

    public void setPedidoProveedoresCollection(Collection<PedidoProveedores> pedidoProveedoresCollection) {
        this.pedidoProveedoresCollection = pedidoProveedoresCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedores != null ? idProveedores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.idProveedores == null && other.idProveedores != null) || (this.idProveedores != null && !this.idProveedores.equals(other.idProveedores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Proveedores[ idProveedores=" + idProveedores + " ]";
    }
    
}
