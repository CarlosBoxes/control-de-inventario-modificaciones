/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.AsignacionDePermisos;
import EntidadesJPA.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getAsignacionDePermisosCollection() == null) {
            usuario.setAsignacionDePermisosCollection(new ArrayList<AsignacionDePermisos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<AsignacionDePermisos> attachedAsignacionDePermisosCollection = new ArrayList<AsignacionDePermisos>();
            for (AsignacionDePermisos asignacionDePermisosCollectionAsignacionDePermisosToAttach : usuario.getAsignacionDePermisosCollection()) {
                asignacionDePermisosCollectionAsignacionDePermisosToAttach = em.getReference(asignacionDePermisosCollectionAsignacionDePermisosToAttach.getClass(), asignacionDePermisosCollectionAsignacionDePermisosToAttach.getIdAsignacionDePermisos());
                attachedAsignacionDePermisosCollection.add(asignacionDePermisosCollectionAsignacionDePermisosToAttach);
            }
            usuario.setAsignacionDePermisosCollection(attachedAsignacionDePermisosCollection);
            em.persist(usuario);
            for (AsignacionDePermisos asignacionDePermisosCollectionAsignacionDePermisos : usuario.getAsignacionDePermisosCollection()) {
                Usuario oldUsuarioidUsuarioOfAsignacionDePermisosCollectionAsignacionDePermisos = asignacionDePermisosCollectionAsignacionDePermisos.getUsuarioidUsuario();
                asignacionDePermisosCollectionAsignacionDePermisos.setUsuarioidUsuario(usuario);
                asignacionDePermisosCollectionAsignacionDePermisos = em.merge(asignacionDePermisosCollectionAsignacionDePermisos);
                if (oldUsuarioidUsuarioOfAsignacionDePermisosCollectionAsignacionDePermisos != null) {
                    oldUsuarioidUsuarioOfAsignacionDePermisosCollectionAsignacionDePermisos.getAsignacionDePermisosCollection().remove(asignacionDePermisosCollectionAsignacionDePermisos);
                    oldUsuarioidUsuarioOfAsignacionDePermisosCollectionAsignacionDePermisos = em.merge(oldUsuarioidUsuarioOfAsignacionDePermisosCollectionAsignacionDePermisos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Collection<AsignacionDePermisos> asignacionDePermisosCollectionOld = persistentUsuario.getAsignacionDePermisosCollection();
            Collection<AsignacionDePermisos> asignacionDePermisosCollectionNew = usuario.getAsignacionDePermisosCollection();
            Collection<AsignacionDePermisos> attachedAsignacionDePermisosCollectionNew = new ArrayList<AsignacionDePermisos>();
            for (AsignacionDePermisos asignacionDePermisosCollectionNewAsignacionDePermisosToAttach : asignacionDePermisosCollectionNew) {
                asignacionDePermisosCollectionNewAsignacionDePermisosToAttach = em.getReference(asignacionDePermisosCollectionNewAsignacionDePermisosToAttach.getClass(), asignacionDePermisosCollectionNewAsignacionDePermisosToAttach.getIdAsignacionDePermisos());
                attachedAsignacionDePermisosCollectionNew.add(asignacionDePermisosCollectionNewAsignacionDePermisosToAttach);
            }
            asignacionDePermisosCollectionNew = attachedAsignacionDePermisosCollectionNew;
            usuario.setAsignacionDePermisosCollection(asignacionDePermisosCollectionNew);
            usuario = em.merge(usuario);
            for (AsignacionDePermisos asignacionDePermisosCollectionOldAsignacionDePermisos : asignacionDePermisosCollectionOld) {
                if (!asignacionDePermisosCollectionNew.contains(asignacionDePermisosCollectionOldAsignacionDePermisos)) {
                    asignacionDePermisosCollectionOldAsignacionDePermisos.setUsuarioidUsuario(null);
                    asignacionDePermisosCollectionOldAsignacionDePermisos = em.merge(asignacionDePermisosCollectionOldAsignacionDePermisos);
                }
            }
            for (AsignacionDePermisos asignacionDePermisosCollectionNewAsignacionDePermisos : asignacionDePermisosCollectionNew) {
                if (!asignacionDePermisosCollectionOld.contains(asignacionDePermisosCollectionNewAsignacionDePermisos)) {
                    Usuario oldUsuarioidUsuarioOfAsignacionDePermisosCollectionNewAsignacionDePermisos = asignacionDePermisosCollectionNewAsignacionDePermisos.getUsuarioidUsuario();
                    asignacionDePermisosCollectionNewAsignacionDePermisos.setUsuarioidUsuario(usuario);
                    asignacionDePermisosCollectionNewAsignacionDePermisos = em.merge(asignacionDePermisosCollectionNewAsignacionDePermisos);
                    if (oldUsuarioidUsuarioOfAsignacionDePermisosCollectionNewAsignacionDePermisos != null && !oldUsuarioidUsuarioOfAsignacionDePermisosCollectionNewAsignacionDePermisos.equals(usuario)) {
                        oldUsuarioidUsuarioOfAsignacionDePermisosCollectionNewAsignacionDePermisos.getAsignacionDePermisosCollection().remove(asignacionDePermisosCollectionNewAsignacionDePermisos);
                        oldUsuarioidUsuarioOfAsignacionDePermisosCollectionNewAsignacionDePermisos = em.merge(oldUsuarioidUsuarioOfAsignacionDePermisosCollectionNewAsignacionDePermisos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Collection<AsignacionDePermisos> asignacionDePermisosCollection = usuario.getAsignacionDePermisosCollection();
            for (AsignacionDePermisos asignacionDePermisosCollectionAsignacionDePermisos : asignacionDePermisosCollection) {
                asignacionDePermisosCollectionAsignacionDePermisos.setUsuarioidUsuario(null);
                asignacionDePermisosCollectionAsignacionDePermisos = em.merge(asignacionDePermisosCollectionAsignacionDePermisos);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
