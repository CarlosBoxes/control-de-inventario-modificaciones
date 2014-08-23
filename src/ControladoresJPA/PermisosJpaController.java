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
import EntidadesJPA.Permisos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class PermisosJpaController implements Serializable {

    public PermisosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Permisos permisos) {
        if (permisos.getAsignacionDePermisosCollection() == null) {
            permisos.setAsignacionDePermisosCollection(new ArrayList<AsignacionDePermisos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<AsignacionDePermisos> attachedAsignacionDePermisosCollection = new ArrayList<AsignacionDePermisos>();
            for (AsignacionDePermisos asignacionDePermisosCollectionAsignacionDePermisosToAttach : permisos.getAsignacionDePermisosCollection()) {
                asignacionDePermisosCollectionAsignacionDePermisosToAttach = em.getReference(asignacionDePermisosCollectionAsignacionDePermisosToAttach.getClass(), asignacionDePermisosCollectionAsignacionDePermisosToAttach.getIdAsignacionDePermisos());
                attachedAsignacionDePermisosCollection.add(asignacionDePermisosCollectionAsignacionDePermisosToAttach);
            }
            permisos.setAsignacionDePermisosCollection(attachedAsignacionDePermisosCollection);
            em.persist(permisos);
            for (AsignacionDePermisos asignacionDePermisosCollectionAsignacionDePermisos : permisos.getAsignacionDePermisosCollection()) {
                Permisos oldPermisosidPermisosOfAsignacionDePermisosCollectionAsignacionDePermisos = asignacionDePermisosCollectionAsignacionDePermisos.getPermisosidPermisos();
                asignacionDePermisosCollectionAsignacionDePermisos.setPermisosidPermisos(permisos);
                asignacionDePermisosCollectionAsignacionDePermisos = em.merge(asignacionDePermisosCollectionAsignacionDePermisos);
                if (oldPermisosidPermisosOfAsignacionDePermisosCollectionAsignacionDePermisos != null) {
                    oldPermisosidPermisosOfAsignacionDePermisosCollectionAsignacionDePermisos.getAsignacionDePermisosCollection().remove(asignacionDePermisosCollectionAsignacionDePermisos);
                    oldPermisosidPermisosOfAsignacionDePermisosCollectionAsignacionDePermisos = em.merge(oldPermisosidPermisosOfAsignacionDePermisosCollectionAsignacionDePermisos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Permisos permisos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Permisos persistentPermisos = em.find(Permisos.class, permisos.getIdPermisos());
            Collection<AsignacionDePermisos> asignacionDePermisosCollectionOld = persistentPermisos.getAsignacionDePermisosCollection();
            Collection<AsignacionDePermisos> asignacionDePermisosCollectionNew = permisos.getAsignacionDePermisosCollection();
            Collection<AsignacionDePermisos> attachedAsignacionDePermisosCollectionNew = new ArrayList<AsignacionDePermisos>();
            for (AsignacionDePermisos asignacionDePermisosCollectionNewAsignacionDePermisosToAttach : asignacionDePermisosCollectionNew) {
                asignacionDePermisosCollectionNewAsignacionDePermisosToAttach = em.getReference(asignacionDePermisosCollectionNewAsignacionDePermisosToAttach.getClass(), asignacionDePermisosCollectionNewAsignacionDePermisosToAttach.getIdAsignacionDePermisos());
                attachedAsignacionDePermisosCollectionNew.add(asignacionDePermisosCollectionNewAsignacionDePermisosToAttach);
            }
            asignacionDePermisosCollectionNew = attachedAsignacionDePermisosCollectionNew;
            permisos.setAsignacionDePermisosCollection(asignacionDePermisosCollectionNew);
            permisos = em.merge(permisos);
            for (AsignacionDePermisos asignacionDePermisosCollectionOldAsignacionDePermisos : asignacionDePermisosCollectionOld) {
                if (!asignacionDePermisosCollectionNew.contains(asignacionDePermisosCollectionOldAsignacionDePermisos)) {
                    asignacionDePermisosCollectionOldAsignacionDePermisos.setPermisosidPermisos(null);
                    asignacionDePermisosCollectionOldAsignacionDePermisos = em.merge(asignacionDePermisosCollectionOldAsignacionDePermisos);
                }
            }
            for (AsignacionDePermisos asignacionDePermisosCollectionNewAsignacionDePermisos : asignacionDePermisosCollectionNew) {
                if (!asignacionDePermisosCollectionOld.contains(asignacionDePermisosCollectionNewAsignacionDePermisos)) {
                    Permisos oldPermisosidPermisosOfAsignacionDePermisosCollectionNewAsignacionDePermisos = asignacionDePermisosCollectionNewAsignacionDePermisos.getPermisosidPermisos();
                    asignacionDePermisosCollectionNewAsignacionDePermisos.setPermisosidPermisos(permisos);
                    asignacionDePermisosCollectionNewAsignacionDePermisos = em.merge(asignacionDePermisosCollectionNewAsignacionDePermisos);
                    if (oldPermisosidPermisosOfAsignacionDePermisosCollectionNewAsignacionDePermisos != null && !oldPermisosidPermisosOfAsignacionDePermisosCollectionNewAsignacionDePermisos.equals(permisos)) {
                        oldPermisosidPermisosOfAsignacionDePermisosCollectionNewAsignacionDePermisos.getAsignacionDePermisosCollection().remove(asignacionDePermisosCollectionNewAsignacionDePermisos);
                        oldPermisosidPermisosOfAsignacionDePermisosCollectionNewAsignacionDePermisos = em.merge(oldPermisosidPermisosOfAsignacionDePermisosCollectionNewAsignacionDePermisos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = permisos.getIdPermisos();
                if (findPermisos(id) == null) {
                    throw new NonexistentEntityException("The permisos with id " + id + " no longer exists.");
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
            Permisos permisos;
            try {
                permisos = em.getReference(Permisos.class, id);
                permisos.getIdPermisos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permisos with id " + id + " no longer exists.", enfe);
            }
            Collection<AsignacionDePermisos> asignacionDePermisosCollection = permisos.getAsignacionDePermisosCollection();
            for (AsignacionDePermisos asignacionDePermisosCollectionAsignacionDePermisos : asignacionDePermisosCollection) {
                asignacionDePermisosCollectionAsignacionDePermisos.setPermisosidPermisos(null);
                asignacionDePermisosCollectionAsignacionDePermisos = em.merge(asignacionDePermisosCollectionAsignacionDePermisos);
            }
            em.remove(permisos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Permisos> findPermisosEntities() {
        return findPermisosEntities(true, -1, -1);
    }

    public List<Permisos> findPermisosEntities(int maxResults, int firstResult) {
        return findPermisosEntities(false, maxResults, firstResult);
    }

    private List<Permisos> findPermisosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Permisos.class));
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

    public Permisos findPermisos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Permisos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPermisosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Permisos> rt = cq.from(Permisos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
