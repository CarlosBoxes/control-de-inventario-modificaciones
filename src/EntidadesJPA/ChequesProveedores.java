/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "cheques_proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChequesProveedores.findAll", query = "SELECT c FROM ChequesProveedores c"),
    @NamedQuery(name = "ChequesProveedores.findByIdchequesProveedores", query = "SELECT c FROM ChequesProveedores c WHERE c.idchequesProveedores = :idchequesProveedores"),
    @NamedQuery(name = "ChequesProveedores.findByNumero", query = "SELECT c FROM ChequesProveedores c WHERE c.numero = :numero AND c.eliminado = false"),
    @NamedQuery(name = "ChequesProveedores.findByListaNumero", query = "SELECT c FROM ChequesProveedores c WHERE c.eliminado = false"),
    @NamedQuery(name = "ChequesProveedores.findByMonto", query = "SELECT c FROM ChequesProveedores c WHERE c.monto = :monto"),
    @NamedQuery(name = "ChequesProveedores.findByFecha", query = "SELECT c FROM ChequesProveedores c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "ChequesProveedores.findByEliminado", query = "SELECT c FROM ChequesProveedores c WHERE c.eliminado = :eliminado")})
public class ChequesProveedores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcheques_proveedores")
    private Integer idchequesProveedores;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @Column(name = "monto")
    private float monto;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    @JoinColumn(name = "Proveedores_idProveedores", referencedColumnName = "idProveedores")
    @ManyToOne(optional = false)
    private Proveedores proveedoresidProveedores;
    @JoinColumn(name = "bancos_idbancos", referencedColumnName = "idbancos")
    @ManyToOne(optional = false)
    private Bancos bancosIdbancos;

    public ChequesProveedores() {
    }

    public ChequesProveedores(Integer idchequesProveedores) {
        this.idchequesProveedores = idchequesProveedores;
    }

    public ChequesProveedores(Integer idchequesProveedores, int numero, float monto, Date fecha, boolean eliminado) {
        this.idchequesProveedores = idchequesProveedores;
        this.numero = numero;
        this.monto = monto;
        this.fecha = fecha;
        this.eliminado = eliminado;
    }

    public Integer getIdchequesProveedores() {
        return idchequesProveedores;
    }

    public void setIdchequesProveedores(Integer idchequesProveedores) {
        this.idchequesProveedores = idchequesProveedores;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Proveedores getProveedoresidProveedores() {
        return proveedoresidProveedores;
    }

    public void setProveedoresidProveedores(Proveedores proveedoresidProveedores) {
        this.proveedoresidProveedores = proveedoresidProveedores;
    }

    public Bancos getBancosIdbancos() {
        return bancosIdbancos;
    }

    public void setBancosIdbancos(Bancos bancosIdbancos) {
        this.bancosIdbancos = bancosIdbancos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idchequesProveedores != null ? idchequesProveedores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChequesProveedores)) {
            return false;
        }
        ChequesProveedores other = (ChequesProveedores) object;
        if ((this.idchequesProveedores == null && other.idchequesProveedores != null) || (this.idchequesProveedores != null && !this.idchequesProveedores.equals(other.idchequesProveedores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ChequesProveedores[ idchequesProveedores=" + idchequesProveedores + " ]";
    }
    
}
