/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.NonexistentEntityException;
import EntidadesJPA.AsignacionDePermisos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.Permisos;
import EntidadesJPA.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class AsignacionDePermisosJpaController implements Serializable {

    public AsignacionDePermisosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionDePermisos asignacionDePermisos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Permisos permisosidPermisos = asignacionDePermisos.getPermisosidPermisos();
            if (permisosidPermisos != null) {
                permisosidPermisos = em.getReference(permisosidPermisos.getClass(), permisosidPermisos.getIdPermisos());
                asignacionDePermisos.setPermisosidPermisos(permisosidPermisos);
            }
            Usuario usuarioidUsuario = asignacionDePermisos.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario = em.getReference(usuarioidUsuario.getClass(), usuarioidUsuario.getIdUsuario());
                asignacionDePermisos.setUsuarioidUsuario(usuarioidUsuario);
            }
            em.persist(asignacionDePermisos);
            if (permisosidPermisos != null) {
                permisosidPermisos.getAsignacionDePermisosCollection().add(asignacionDePermisos);
                permisosidPermisos = em.merge(permisosidPermisos);
            }
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getAsignacionDePermisosCollection().add(asignacionDePermisos);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionDePermisos asignacionDePermisos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionDePermisos persistentAsignacionDePermisos = em.find(AsignacionDePermisos.class, asignacionDePermisos.getIdAsignacionDePermisos());
            Permisos permisosidPermisosOld = persistentAsignacionDePermisos.getPermisosidPermisos();
            Permisos permisosidPermisosNew = asignacionDePermisos.getPermisosidPermisos();
            Usuario usuarioidUsuarioOld = persistentAsignacionDePermisos.getUsuarioidUsuario();
            Usuario usuarioidUsuarioNew = asignacionDePermisos.getUsuarioidUsuario();
            if (permisosidPermisosNew != null) {
                permisosidPermisosNew = em.getReference(permisosidPermisosNew.getClass(), permisosidPermisosNew.getIdPermisos());
                asignacionDePermisos.setPermisosidPermisos(permisosidPermisosNew);
            }
            if (usuarioidUsuarioNew != null) {
                usuarioidUsuarioNew = em.getReference(usuarioidUsuarioNew.getClass(), usuarioidUsuarioNew.getIdUsuario());
                asignacionDePermisos.setUsuarioidUsuario(usuarioidUsuarioNew);
            }
            asignacionDePermisos = em.merge(asignacionDePermisos);
            if (permisosidPermisosOld != null && !permisosidPermisosOld.equals(permisosidPermisosNew)) {
                permisosidPermisosOld.getAsignacionDePermisosCollection().remove(asignacionDePermisos);
                permisosidPermisosOld = em.merge(permisosidPermisosOld);
            }
            if (permisosidPermisosNew != null && !permisosidPermisosNew.equals(permisosidPermisosOld)) {
                permisosidPermisosNew.getAsignacionDePermisosCollection().add(asignacionDePermisos);
                permisosidPermisosNew = em.merge(permisosidPermisosNew);
            }
            if (usuarioidUsuarioOld != null && !usuarioidUsuarioOld.equals(usuarioidUsuarioNew)) {
                usuarioidUsuarioOld.getAsignacionDePermisosCollection().remove(asignacionDePermisos);
                usuarioidUsuarioOld = em.merge(usuarioidUsuarioOld);
            }
            if (usuarioidUsuarioNew != null && !usuarioidUsuarioNew.equals(usuarioidUsuarioOld)) {
                usuarioidUsuarioNew.getAsignacionDePermisosCollection().add(asignacionDePermisos);
                usuarioidUsuarioNew = em.merge(usuarioidUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignacionDePermisos.getIdAsignacionDePermisos();
                if (findAsignacionDePermisos(id) == null) {
                    throw new NonexistentEntityException("The asignacionDePermisos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionDePermisos asignacionDePermisos;
            try {
                asignacionDePermisos = em.getReference(AsignacionDePermisos.class, id);
                asignacionDePermisos.getIdAsignacionDePermisos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionDePermisos with id " + id + " no longer exists.", enfe);
            }
            Permisos permisosidPermisos = asignacionDePermisos.getPermisosidPermisos();
            if (permisosidPermisos != null) {
                permisosidPermisos.getAsignacionDePermisosCollection().remove(asignacionDePermisos);
                permisosidPermisos = em.merge(permisosidPermisos);
            }
            Usuario usuarioidUsuario = asignacionDePermisos.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getAsignacionDePermisosCollection().remove(asignacionDePermisos);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            em.remove(asignacionDePermisos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionDePermisos> findAsignacionDePermisosEntities() {
        return findAsignacionDePermisosEntities(true, -1, -1);
    }

    public List<AsignacionDePermisos> findAsignacionDePermisosEntities(int maxResults, int firstResult) {
        return findAsignacionDePermisosEntities(false, maxResults, firstResult);
    }

    private List<AsignacionDePermisos> findAsignacionDePermisosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsignacionDePermisos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionDePermisos findAsignacionDePermisos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionDePermisos.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionDePermisosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsignacionDePermisos> rt = cq.from(AsignacionDePermisos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
