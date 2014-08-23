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
@Table(name = "vendedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendedores.findAll", query = "SELECT v FROM Vendedores v"),
    @NamedQuery(name = "Vendedores.findByIdvendedores", query = "SELECT v FROM Vendedores v WHERE v.idvendedores = :idvendedores"),
    @NamedQuery(name = "Vendedores.findByNombre", query = "SELECT v FROM Vendedores v WHERE CONCAT(v.nombre,' ',v.apellido) LIKE :nombre AND v.eliminado = false"),
    @NamedQuery(name = "Vendedores.findByListaNombre", query = "SELECT v FROM Vendedores v WHERE v.eliminado = false"),
    @NamedQuery(name = "Vendedores.findByApellido", query = "SELECT v FROM Vendedores v WHERE v.apellido = :apellido"),
    @NamedQuery(name = "Vendedores.findByTelefonoCelular", query = "SELECT v FROM Vendedores v WHERE v.telefonoCelular = :telefonoCelular"),
    @NamedQuery(name = "Vendedores.findByTelefonoCasa", query = "SELECT v FROM Vendedores v WHERE v.telefonoCasa = :telefonoCasa"),
    @NamedQuery(name = "Vendedores.findBySaldoAnterior", query = "SELECT v FROM Vendedores v WHERE v.saldoAnterior = :saldoAnterior"),
    @NamedQuery(name = "Vendedores.findByEliminado", query = "SELECT v FROM Vendedores v WHERE v.eliminado = :eliminado"),
    @NamedQuery(name = "Vendedores.findByDpi", query = "SELECT v FROM Vendedores v WHERE v.dpi = :dpi")})
public class Vendedores implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedoridVendedor")
    private Collection<Facturas> facturasCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvendedores")
    private Integer idvendedores;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "Apellido")
    private String apellido;
    @Column(name = "Telefono_Celular")
    private String telefonoCelular;
    @Column(name = "Telefono_Casa")
    private String telefonoCasa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldo_Anterior")
    private Float saldoAnterior;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    @Basic(optional = false)
    @Column(name = "DPI")
    private String dpi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedoresIdvendedores")
    private Collection<Pedido> pedidoCollection;
    @JoinColumn(name = "Tipo_Vendedores_idTipo_Vendedores", referencedColumnName = "idTipo_Vendedores")
    @ManyToOne(optional = false)
    private TipoVendedores tipoVendedoresidTipoVendedores;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedoresIdvendedores")
    private Collection<ListaClientes> listaclientesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedoresIdvendedores")
    private Collection<ProductosDefectuoso> productosDefectuosoCollection;

    public Vendedores() {
    }

    public Vendedores(Integer idvendedores) {
        this.idvendedores = idvendedores;
    }

    public Vendedores(Integer idvendedores, String nombre, boolean eliminado, String dpi) {
        this.idvendedores = idvendedores;
        this.nombre = nombre;
        this.eliminado = eliminado;
        this.dpi = dpi;
    }

    public Integer getIdvendedores() {
        return idvendedores;
    }

    public void setIdvendedores(Integer idvendedores) {
        this.idvendedores = idvendedores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public Float getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(Float saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    public TipoVendedores getTipoVendedoresidTipoVendedores() {
        return tipoVendedoresidTipoVendedores;
    }

    public void setTipoVendedoresidTipoVendedores(TipoVendedores tipoVendedoresidTipoVendedores) {
        this.tipoVendedoresidTipoVendedores = tipoVendedoresidTipoVendedores;
    }

    @XmlTransient
    public Collection<ListaClientes> getListaclientesCollection() {
        return listaclientesCollection;
    }

    public void setListaclientesCollection(Collection<ListaClientes> listaclientesCollection) {
        this.listaclientesCollection = listaclientesCollection;
    }

    @XmlTransient
    public Collection<ProductosDefectuoso> getProductosDefectuosoCollection() {
        return productosDefectuosoCollection;
    }

    public void setProductosDefectuosoCollection(Collection<ProductosDefectuoso> productosDefectuosoCollection) {
        this.productosDefectuosoCollection = productosDefectuosoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvendedores != null ? idvendedores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendedores)) {
            return false;
        }
        Vendedores other = (Vendedores) object;
        if ((this.idvendedores == null && other.idvendedores != null) || (this.idvendedores != null && !this.idvendedores.equals(other.idvendedores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Vendedores[ idvendedores=" + idvendedores + " ]";
    }

    @XmlTransient
    public Collection<Facturas> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<Facturas> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }
    
}
