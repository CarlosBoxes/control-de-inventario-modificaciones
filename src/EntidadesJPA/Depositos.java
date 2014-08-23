/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "depositos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Depositos.findAll", query = "SELECT d FROM Depositos d"),
    @NamedQuery(name = "Depositos.findByIdDepositos", query = "SELECT d FROM Depositos d WHERE d.idDepositos = :idDepositos"),
    @NamedQuery(name = "Depositos.findByMonto", query = "SELECT d FROM Depositos d WHERE d.monto = :monto"),
    @NamedQuery(name = "Depositos.findByNumeroDeBoleta", query = "SELECT d FROM Depositos d WHERE d.numeroDeBoleta = :numeroDeBoleta AND d.eliminado = false"),
    @NamedQuery(name = "Depositos.findByNumeroDeBoletaUsada", query = "SELECT d FROM Depositos d WHERE d.numeroDeBoleta = :numeroDeBoleta AND d.eliminado = false AND d.usado = false"),
    @NamedQuery(name = "Depositos.findByListaNumeroDeBoleta", query = "SELECT d FROM Depositos d WHERE d.eliminado = false"),
    @NamedQuery(name = "Depositos.findByEliminado", query = "SELECT d FROM Depositos d WHERE d.eliminado = :eliminado"),
    @NamedQuery(name = "Depositos.findByUsado", query = "SELECT d FROM Depositos d WHERE d.usado = :usado")})
public class Depositos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDepositos")
    private Integer idDepositos;
    @Basic(optional = false)
    @Column(name = "monto")
    private float monto;
    @Basic(optional = false)
    @Column(name = "numero_de_boleta")
    private String numeroDeBoleta;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    @Basic(optional = false)
    @Column(name = "usado")
    private boolean usado;
    @OneToMany(mappedBy = "depositosidDepositos")
    private Collection<DescripcionLiquidacion> descripcionLiquidacionCollection;
    @OneToMany(mappedBy = "depositosidDepositos")
    private Collection<CuentaporCobrar> cuentaporcobrarCollection;
    @JoinColumn(name = "bancos_idbancos", referencedColumnName = "idbancos")
    @ManyToOne(optional = false)
    private Bancos bancosIdbancos;

    public Depositos() {
    }

    public Depositos(Integer idDepositos) {
        this.idDepositos = idDepositos;
    }

    public Depositos(Integer idDepositos, float monto, String numeroDeBoleta, boolean eliminado, boolean usado) {
        this.idDepositos = idDepositos;
        this.monto = monto;
        this.numeroDeBoleta = numeroDeBoleta;
        this.eliminado = eliminado;
        this.usado = usado;
    }

    public Integer getIdDepositos() {
        return idDepositos;
    }

    public void setIdDepositos(Integer idDepositos) {
        this.idDepositos = idDepositos;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getNumeroDeBoleta() {
        return numeroDeBoleta;
    }

    public void setNumeroDeBoleta(String numeroDeBoleta) {
        this.numeroDeBoleta = numeroDeBoleta;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public boolean getUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    @XmlTransient
    public Collection<DescripcionLiquidacion> getDescripcionLiquidacionCollection() {
        return descripcionLiquidacionCollection;
    }

    public void setDescripcionLiquidacionCollection(Collection<DescripcionLiquidacion> descripcionLiquidacionCollection) {
        this.descripcionLiquidacionCollection = descripcionLiquidacionCollection;
    }

    @XmlTransient
    public Collection<CuentaporCobrar> getCuentaporcobrarCollection() {
        return cuentaporcobrarCollection;
    }

    public void setCuentaporcobrarCollection(Collection<CuentaporCobrar> cuentaporcobrarCollection) {
        this.cuentaporcobrarCollection = cuentaporcobrarCollection;
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
        hash += (idDepositos != null ? idDepositos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Depositos)) {
            return false;
        }
        Depositos other = (Depositos) object;
        if ((this.idDepositos == null && other.idDepositos != null) || (this.idDepositos != null && !this.idDepositos.equals(other.idDepositos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Depositos[ idDepositos=" + idDepositos + " ]";
    }
    
}
