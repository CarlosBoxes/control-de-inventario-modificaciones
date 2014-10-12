/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "pedido proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoProveedores.findAll", query = "SELECT p FROM PedidoProveedores p"),
    @NamedQuery(name = "PedidoProveedores.findByLista", query = "SELECT p FROM PedidoProveedores p WHERE p.almacenado = false OR p.aplicado = false"),
    @NamedQuery(name = "PedidoProveedores.findByIdpedidoProveedores", query = "SELECT p FROM PedidoProveedores p WHERE p.idpedidoProveedores = :idpedidoProveedores"),
    @NamedQuery(name = "PedidoProveedores.findByIdProveedor", query = "SELECT p FROM PedidoProveedores p WHERE p.proveedoresidProveedores = :idProveedor AND p.saldo > 0f"),
    @NamedQuery(name = "PedidoProveedores.findByFecha", query = "SELECT p FROM PedidoProveedores p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "PedidoProveedores.findByAplicado", query = "SELECT p FROM PedidoProveedores p WHERE p.aplicado = :aplicado"),
    @NamedQuery(name = "PedidoProveedores.findByNoFactura", query = "SELECT p FROM PedidoProveedores p WHERE p.noFactura = :noFactura"),
    @NamedQuery(name = "PedidoProveedores.findByTotal", query = "SELECT p FROM PedidoProveedores p WHERE p.total = :total"),
    @NamedQuery(name = "PedidoProveedores.findByFechaVencimiento", query = "SELECT p FROM PedidoProveedores p WHERE p.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "PedidoProveedores.findBySaldo", query = "SELECT p FROM PedidoProveedores p WHERE p.saldo = :saldo"),
    @NamedQuery(name = "PedidoProveedores.findByAlmacenado", query = "SELECT p FROM PedidoProveedores p WHERE p.almacenado = :almacenado")})
public class PedidoProveedores implements Serializable {
    @Basic(optional = false)
    @Column(name = "subTotal")
    private float subTotal;
    @Basic(optional = false)
    @Column(name = "descuento")
    private float descuento;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedido_proveedores")
    private Integer idpedidoProveedores;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "aplicado")
    private Boolean aplicado;
    @Column(name = "NoFactura")
    private String noFactura;
    @Basic(optional = false)
    @Column(name = "total")
    private float total;
    @Column(name = "FechaVencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Basic(optional = false)
    @Column(name = "Saldo")
    private float saldo;
    @Column(name = "almacenado")
    private Boolean almacenado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoProveedoresIdpedidoProveedores")
    private Collection<DescripcionPedidoProveedores> descripcionPedidoProveedoresCollection;
    @JoinColumn(name = "Proveedores_idProveedores", referencedColumnName = "idProveedores")
    @ManyToOne(optional = false)
    private Proveedores proveedoresidProveedores;

    public PedidoProveedores() {
    }

    public PedidoProveedores(Integer idpedidoProveedores) {
        this.idpedidoProveedores = idpedidoProveedores;
    }

    public PedidoProveedores(Integer idpedidoProveedores, Date fecha, float total, float saldo) {
        this.idpedidoProveedores = idpedidoProveedores;
        this.fecha = fecha;
        this.total = total;
        this.saldo = saldo;
    }

    public Integer getIdpedidoProveedores() {
        return idpedidoProveedores;
    }

    public void setIdpedidoProveedores(Integer idpedidoProveedores) {
        this.idpedidoProveedores = idpedidoProveedores;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getAplicado() {
        return aplicado;
    }

    public void setAplicado(Boolean aplicado) {
        this.aplicado = aplicado;
    }

    public String getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(String noFactura) {
        this.noFactura = noFactura;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Boolean getAlmacenado() {
        return almacenado;
    }

    public void setAlmacenado(Boolean almacenado) {
        this.almacenado = almacenado;
    }

    @XmlTransient
    public Collection<DescripcionPedidoProveedores> getDescripcionPedidoProveedoresCollection() {
        return descripcionPedidoProveedoresCollection;
    }

    public void setDescripcionPedidoProveedoresCollection(Collection<DescripcionPedidoProveedores> descripcionPedidoProveedoresCollection) {
        this.descripcionPedidoProveedoresCollection = descripcionPedidoProveedoresCollection;
    }

    public Proveedores getProveedoresidProveedores() {
        return proveedoresidProveedores;
    }

    public void setProveedoresidProveedores(Proveedores proveedoresidProveedores) {
        this.proveedoresidProveedores = proveedoresidProveedores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedidoProveedores != null ? idpedidoProveedores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoProveedores)) {
            return false;
        }
        PedidoProveedores other = (PedidoProveedores) object;
        if ((this.idpedidoProveedores == null && other.idpedidoProveedores != null) || (this.idpedidoProveedores != null && !this.idpedidoProveedores.equals(other.idpedidoProveedores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PedidoProveedores[ idpedidoProveedores=" + idpedidoProveedores + " ]";
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
    
}
