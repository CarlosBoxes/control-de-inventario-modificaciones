/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.IllegalOrphanException;
import ControladoresJPA.exceptions.NonexistentEntityException;
import EntidadesJPA.BodegaProduccion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.InventarioMateriaPrima;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class BodegaProduccionJpaController implements Serializable {

    public BodegaProduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BodegaProduccion bodegaProduccion) {
        if (bodegaProduccion.getInventarioMateriaPrimaCollection() == null) {
            bodegaProduccion.setInventarioMateriaPrimaCollection(new ArrayList<InventarioMateriaPrima>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<InventarioMateriaPrima> attachedInventarioMateriaPrimaCollection = new ArrayList<InventarioMateriaPrima>();
            for (InventarioMateriaPrima inventarioMateriaPrimaCollectionInventarioMateriaPrimaToAttach : bodegaProduccion.getInventarioMateriaPrimaCollection()) {
                inventarioMateriaPrimaCollectionInventarioMateriaPrimaToAttach = em.getReference(inventarioMateriaPrimaCollectionInventarioMateriaPrimaToAttach.getClass(), inventarioMateriaPrimaCollectionInventarioMateriaPrimaToAttach.getIdInventarioMateriaPrima());
                attachedInventarioMateriaPrimaCollection.add(inventarioMateriaPrimaCollectionInventarioMateriaPrimaToAttach);
            }
            bodegaProduccion.setInventarioMateriaPrimaCollection(attachedInventarioMateriaPrimaCollection);
            em.persist(bodegaProduccion);
            for (InventarioMateriaPrima inventarioMateriaPrimaCollectionInventarioMateriaPrima : bodegaProduccion.getInventarioMateriaPrimaCollection()) {
                BodegaProduccion oldBodegaProduccionidbodegaProduccionOfInventarioMateriaPrimaCollectionInventarioMateriaPrima = inventarioMateriaPrimaCollectionInventarioMateriaPrima.getBodegaProduccionidbodegaProduccion();
                inventarioMateriaPrimaCollectionInventarioMateriaPrima.setBodegaProduccionidbodegaProduccion(bodegaProduccion);
                inventarioMateriaPrimaCollectionInventarioMateriaPrima = em.merge(inventarioMateriaPrimaCollectionInventarioMateriaPrima);
                if (oldBodegaProduccionidbodegaProduccionOfInventarioMateriaPrimaCollectionInventarioMateriaPrima != null) {
                    oldBodegaProduccionidbodegaProduccionOfInventarioMateriaPrimaCollectionInventarioMateriaPrima.getInventarioMateriaPrimaCollection().remove(inventarioMateriaPrimaCollectionInventarioMateriaPrima);
                    oldBodegaProduccionidbodegaProduccionOfInventarioMateriaPrimaCollectionInventarioMateriaPrima = em.merge(oldBodegaProduccionidbodegaProduccionOfInventarioMateriaPrimaCollectionInventarioMateriaPrima);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BodegaProduccion bodegaProduccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BodegaProduccion persistentBodegaProduccion = em.find(BodegaProduccion.class, bodegaProduccion.getIdbodegaProduccion());
            Collection<InventarioMateriaPrima> inventarioMateriaPrimaCollectionOld = persistentBodegaProduccion.getInventarioMateriaPrimaCollection();
            Collection<InventarioMateriaPrima> inventarioMateriaPrimaCollectionNew = bodegaProduccion.getInventarioMateriaPrimaCollection();
            List<String> illegalOrphanMessages = null;
            for (InventarioMateriaPrima inventarioMateriaPrimaCollectionOldInventarioMateriaPrima : inventarioMateriaPrimaCollectionOld) {
                if (!inventarioMateriaPrimaCollectionNew.contains(inventarioMateriaPrimaCollectionOldInventarioMateriaPrima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InventarioMateriaPrima " + inventarioMateriaPrimaCollectionOldInventarioMateriaPrima + " since its bodegaProduccionidbodegaProduccion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<InventarioMateriaPrima> attachedInventarioMateriaPrimaCollectionNew = new ArrayList<InventarioMateriaPrima>();
            for (InventarioMateriaPrima inventarioMateriaPrimaCollectionNewInventarioMateriaPrimaToAttach : inventarioMateriaPrimaCollectionNew) {
                inventarioMateriaPrimaCollectionNewInventarioMateriaPrimaToAttach = em.getReference(inventarioMateriaPrimaCollectionNewInventarioMateriaPrimaToAttach.getClass(), inventarioMateriaPrimaCollectionNewInventarioMateriaPrimaToAttach.getIdInventarioMateriaPrima());
                attachedInventarioMateriaPrimaCollectionNew.add(inventarioMateriaPrimaCollectionNewInventarioMateriaPrimaToAttach);
            }
            inventarioMateriaPrimaCollectionNew = attachedInventarioMateriaPrimaCollectionNew;
            bodegaProduccion.setInventarioMateriaPrimaCollection(inventarioMateriaPrimaCollectionNew);
            bodegaProduccion = em.merge(bodegaProduccion);
            for (InventarioMateriaPrima inventarioMateriaPrimaCollectionNewInventarioMateriaPrima : inventarioMateriaPrimaCollectionNew) {
                if (!inventarioMateriaPrimaCollectionOld.contains(inventarioMateriaPrimaCollectionNewInventarioMateriaPrima)) {
                    BodegaProduccion oldBodegaProduccionidbodegaProduccionOfInventarioMateriaPrimaCollectionNewInventarioMateriaPrima = inventarioMateriaPrimaCollectionNewInventarioMateriaPrima.getBodegaProduccionidbodegaProduccion();
                    inventarioMateriaPrimaCollectionNewInventarioMateriaPrima.setBodegaProduccionidbodegaProduccion(bodegaProduccion);
                    inventarioMateriaPrimaCollectionNewInventarioMateriaPrima = em.merge(inventarioMateriaPrimaCollectionNewInventarioMateriaPrima);
                    if (oldBodegaProduccionidbodegaProduccionOfInventarioMateriaPrimaCollectionNewInventarioMateriaPrima != null && !oldBodegaProduccionidbodegaProduccionOfInventarioMateriaPrimaCollectionNewInventarioMateriaPrima.equals(bodegaProduccion)) {
                        oldBodegaProduccionidbodegaProduccionOfInventarioMateriaPrimaCollectionNewInventarioMateriaPrima.getInventarioMateriaPrimaCollection().remove(inventarioMateriaPrimaCollectionNewInventarioMateriaPrima);
                        oldBodegaProduccionidbodegaProduccionOfInventarioMateriaPrimaCollectionNewInventarioMateriaPrima = em.merge(oldBodegaProduccionidbodegaProduccionOfInventarioMateriaPrimaCollectionNewInventarioMateriaPrima);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bodegaProduccion.getIdbodegaProduccion();
                if (findBodegaProduccion(id) == null) {
                    throw new NonexistentEntityException("The bodegaProduccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BodegaProduccion bodegaProduccion;
            try {
                bodegaProduccion = em.getReference(BodegaProduccion.class, id);
                bodegaProduccion.getIdbodegaProduccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bodegaProduccion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<InventarioMateriaPrima> inventarioMateriaPrimaCollectionOrphanCheck = bodegaProduccion.getInventarioMateriaPrimaCollection();
            for (InventarioMateriaPrima inventarioMateriaPrimaCollectionOrphanCheckInventarioMateriaPrima : inventarioMateriaPrimaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BodegaProduccion (" + bodegaProduccion + ") cannot be destroyed since the InventarioMateriaPrima " + inventarioMateriaPrimaCollectionOrphanCheckInventarioMateriaPrima + " in its inventarioMateriaPrimaCollection field has a non-nullable bodegaProduccionidbodegaProduccion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(bodegaProduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BodegaProduccion> findBodegaProduccionEntities() {
        return findBodegaProduccionEntities(true, -1, -1);
    }

    public List<BodegaProduccion> findBodegaProduccionEntities(int maxResults, int firstResult) {
        return findBodegaProduccionEntities(false, maxResults, firstResult);
    }

    private List<BodegaProduccion> findBodegaProduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BodegaProduccion.class));
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

    public BodegaProduccion findBodegaProduccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BodegaProduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getBodegaProduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BodegaProduccion> rt = cq.from(BodegaProduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
