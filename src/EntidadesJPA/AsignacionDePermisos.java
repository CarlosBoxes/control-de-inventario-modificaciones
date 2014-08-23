/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "asignacion de permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignacionDePermisos.findAll", query = "SELECT a FROM AsignacionDePermisos a"),
    @NamedQuery(name = "AsignacionDePermisos.findByIdAsignacionDePermisos", query = "SELECT a FROM AsignacionDePermisos a WHERE a.idAsignacionDePermisos = :idAsignacionDePermisos")})
public class AsignacionDePermisos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAsignacionDePermisos")
    private Integer idAsignacionDePermisos;
    @JoinColumn(name = "permisos_idPermisos", referencedColumnName = "idPermisos")
    @ManyToOne
    private Permisos permisosidPermisos;
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario usuarioidUsuario;

    public AsignacionDePermisos() {
    }

    public AsignacionDePermisos(Integer idAsignacionDePermisos) {
        this.idAsignacionDePermisos = idAsignacionDePermisos;
    }

    public Integer getIdAsignacionDePermisos() {
        return idAsignacionDePermisos;
    }

    public void setIdAsignacionDePermisos(Integer idAsignacionDePermisos) {
        this.idAsignacionDePermisos = idAsignacionDePermisos;
    }

    public Permisos getPermisosidPermisos() {
        return permisosidPermisos;
    }

    public void setPermisosidPermisos(Permisos permisosidPermisos) {
        this.permisosidPermisos = permisosidPermisos;
    }

    public Usuario getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionDePermisos != null ? idAsignacionDePermisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionDePermisos)) {
            return false;
        }
        AsignacionDePermisos other = (AsignacionDePermisos) object;
        if ((this.idAsignacionDePermisos == null && other.idAsignacionDePermisos != null) || (this.idAsignacionDePermisos != null && !this.idAsignacionDePermisos.equals(other.idAsignacionDePermisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AsignacionDePermisos[ idAsignacionDePermisos=" + idAsignacionDePermisos + " ]";
    }
    
}
