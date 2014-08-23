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
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByIdpedido", query = "SELECT p FROM Pedido p WHERE p.idpedido = :idpedido"),
    @NamedQuery(name = "Pedido.findByFecha", query = "SELECT p FROM Pedido p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Pedido.findByLista", query = "SELECT p FROM Pedido p WHERE p.fecha >= :fecha AND p.eliminado = false"),
    @NamedQuery(name = "Pedido.findByAplicado", query = "SELECT p FROM Pedido p WHERE p.aplicado = :aplicado"),
    @NamedQuery(name = "Pedido.findBySubtotal", query = "SELECT p FROM Pedido p WHERE p.subtotal = :subtotal"),
    @NamedQuery(name = "Pedido.findByTotal", query = "SELECT p FROM Pedido p WHERE p.total = :total"),
    @NamedQuery(name = "Pedido.findByLiquidado", query = "SELECT p FROM Pedido p WHERE p.liquidado = :liquidado"),
    @NamedQuery(name = "Pedido.findByIsr", query = "SELECT p FROM Pedido p WHERE p.isr = :isr"),
    @NamedQuery(name = "Pedido.findByObservaciones", query = "SELECT p FROM Pedido p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "Pedido.findByContrase\u00f1as", query = "SELECT p FROM Pedido p WHERE p.contrase\u00f1as = :contrase\u00f1as")})
public class Pedido implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "descuento")
    private Float descuento;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoIdpedido")
    private Collection<DescripcionPedido> descripcionPedidoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoIdpedido")
    private Collection<DescripcionClientes> descripcionClientesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoidPedido")
    private Collection<Facturas> facturasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoIdpedido")
    private Collection<Liquidacion> liquidacionCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedido")
    private Integer idpedido;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "aplicado")
    private Boolean aplicado;
    @Basic(optional = false)
    @Column(name = "subtotal")
    private float subtotal;
    @Basic(optional = false)
    @Column(name = "total")
    private float total;
    @Column(name = "liquidado")
    private Boolean liquidado;
    @Basic(optional = false)
    @Column(name = "ISR")
    private float isr;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "contrase\u00f1as")
    private float contraseñas;
    @JoinColumn(name = "vendedores_idvendedores", referencedColumnName = "idvendedores")
    @ManyToOne(optional = false)
    private Vendedores vendedoresIdvendedores;

    public Pedido() {
    }

    public Pedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Pedido(Integer idpedido, Date fecha, float subtotal, float total, float isr, float contraseñas) {
        this.idpedido = idpedido;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.total = total;
        this.isr = isr;
       
        this.contraseñas = contraseñas;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
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

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Boolean getLiquidado() {
        return liquidado;
    }

    public void setLiquidado(Boolean liquidado) {
        this.liquidado = liquidado;
    }

    public float getIsr() {
        return isr;
    }

    public void setIsr(float isr) {
        this.isr = isr;
    }

   

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public float getContraseñas() {
        return contraseñas;
    }

    public void setContraseñas(float contraseñas) {
        this.contraseñas = contraseñas;
    }

    public Vendedores getVendedoresIdvendedores() {
        return vendedoresIdvendedores;
    }

    public void setVendedoresIdvendedores(Vendedores vendedoresIdvendedores) {
        this.vendedoresIdvendedores = vendedoresIdvendedores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedido != null ? idpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idpedido == null && other.idpedido != null) || (this.idpedido != null && !this.idpedido.equals(other.idpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesJPA.Pedido[ idpedido=" + idpedido + " ]";
    }

    @XmlTransient
    public Collection<DescripcionPedido> getDescripcionPedidoCollection() {
        return descripcionPedidoCollection;
    }

    public void setDescripcionPedidoCollection(Collection<DescripcionPedido> descripcionPedidoCollection) {
        this.descripcionPedidoCollection = descripcionPedidoCollection;
    }

    @XmlTransient
    public Collection<DescripcionClientes> getDescripcionClientesCollection() {
        return descripcionClientesCollection;
    }

    public void setDescripcionClientesCollection(Collection<DescripcionClientes> descripcionClientesCollection) {
        this.descripcionClientesCollection = descripcionClientesCollection;
    }

    @XmlTransient
    public Collection<Facturas> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<Facturas> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    @XmlTransient
    public Collection<Liquidacion> getLiquidacionCollection() {
        return liquidacionCollection;
    }

    public void setLiquidacionCollection(Collection<Liquidacion> liquidacionCollection) {
        this.liquidacionCollection = liquidacionCollection;
    }

   

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
}
