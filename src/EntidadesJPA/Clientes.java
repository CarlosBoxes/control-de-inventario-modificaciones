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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c"),
    @NamedQuery(name = "Clientes.findByIdCliente", query = "SELECT c FROM Clientes c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Clientes.findByNombre", query = "SELECT c FROM Clientes c WHERE c.nombre = :nombre AND c.eliminado = false"),
    @NamedQuery(name = "Clientes.findByListaNombre", query = "SELECT c FROM Clientes c WHERE c.eliminado = false"),
    @NamedQuery(name = "Clientes.findByNombreLike", query = "SELECT c FROM Clientes c WHERE c.nombre LIKE :nombre AND c.eliminado = false"),
    @NamedQuery(name = "Clientes.findByDireccion", query = "SELECT c FROM Clientes c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Clientes.findByNit", query = "SELECT c FROM Clientes c WHERE c.nit = :nit AND c.nombre = :nombre "),
    @NamedQuery(name = "Clientes.findByNitSolo", query = "SELECT c FROM Clientes c WHERE c.nit = :nit"),
    @NamedQuery(name = "Clientes.findByTelefonoCelular", query = "SELECT c FROM Clientes c WHERE c.telefonoCelular = :telefonoCelular"),
    @NamedQuery(name = "Clientes.findByTelefonoCasa", query = "SELECT c FROM Clientes c WHERE c.telefonoCasa = :telefonoCasa"),
    @NamedQuery(name = "Clientes.findByEliminado", query = "SELECT c FROM Clientes c WHERE c.eliminado = :eliminado"),
    @NamedQuery(name = "Clientes.findBySaldo", query = "SELECT c FROM Clientes c WHERE c.saldo = :saldo")})
public class Clientes implements Serializable {
    @Basic(optional = false)
    @Column(name = "razonsocial")
    private String razonsocial;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCliente")
    private Integer idCliente;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "nit")
    private String nit;
    @Column(name = "Telefono_Celular")
    private String telefonoCelular;
    @Column(name = "Telefono_Casa")
    private String telefonoCasa;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    @Basic(optional = false)
    @Column(name = "Saldo")
    private float saldo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientesidCliente")
    private Collection<DescripcionClientes> descripcionclientesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientesidCliente")
    private Collection<CuentaporCobrar> cuentaporcobrarCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientesidCliente")
    private Collection<Facturas> facturasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientesidCliente")
    private Collection<ListaClientes> listaclientesCollection;
    @JoinColumn(name = "Tipo_Clientes_idTipo_Clientes", referencedColumnName = "idTipo_Clientes")
    @ManyToOne(optional = false)
    private TipoClientes tipoClientesidTipoClientes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientesidCliente")
    private Collection<CargosClientes> cargosclientesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientesidCliente")
    private Collection<ChequesClientes> chequesClientesCollection;

    public Clientes() {
    }

    public Clientes(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Clientes(Integer idCliente, String nombre, String direccion, String nit, boolean eliminado, float saldo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
        this.eliminado = eliminado;
        this.saldo = saldo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getTelefonoCasa() {
        return telefonoCasa;
    }

    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @XmlTransient
    public Collection<DescripcionClientes> getDescripcionclientesCollection() {
        return descripcionclientesCollection;
    }

    public void setDescripcionclientesCollection(Collection<DescripcionClientes> descripcionclientesCollection) {
        this.descripcionclientesCollection = descripcionclientesCollection;
    }

    @XmlTransient
    public Collection<CuentaporCobrar> getCuentaporcobrarCollection() {
        return cuentaporcobrarCollection;
    }

    public void setCuentaporcobrarCollection(Collection<CuentaporCobrar> cuentaporcobrarCollection) {
        this.cuentaporcobrarCollection = cuentaporcobrarCollection;
    }

    @XmlTransient
    public Collection<Facturas> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<Facturas> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    @XmlTransient
    public Collection<ListaClientes> getListaclientesCollection() {
        return listaclientesCollection;
    }

    public void setListaclientesCollection(Collection<ListaClientes> listaclientesCollection) {
        this.listaclientesCollection = listaclientesCollection;
    }

    public TipoClientes getTipoClientesidTipoClientes() {
        return tipoClientesidTipoClientes;
    }

    public void setTipoClientesidTipoClientes(TipoClientes tipoClientesidTipoClientes) {
        this.tipoClientesidTipoClientes = tipoClientesidTipoClientes;
    }

    @XmlTransient
    public Collection<CargosClientes> getCargosclientesCollection() {
        return cargosclientesCollection;
    }

    public void setCargosclientesCollection(Collection<CargosClientes> cargosclientesCollection) {
        this.cargosclientesCollection = cargosclientesCollection;
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
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Clientes[ idCliente=" + idCliente + " ]";
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }
    
}
