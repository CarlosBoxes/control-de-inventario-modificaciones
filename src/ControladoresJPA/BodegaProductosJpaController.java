/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.IllegalOrphanException;
import ControladoresJPA.exceptions.NonexistentEntityException;
import EntidadesJPA.BodegaProductos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.InventarioProducto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class BodegaProductosJpaController implements Serializable {

    public BodegaProductosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BodegaProductos bodegaProductos) {
        if (bodegaProductos.getInventarioProductoCollection() == null) {
            bodegaProductos.setInventarioProductoCollection(new ArrayList<InventarioProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<InventarioProducto> attachedInventarioProductoCollection = new ArrayList<InventarioProducto>();
            for (InventarioProducto inventarioProductoCollectionInventarioProductoToAttach : bodegaProductos.getInventarioProductoCollection()) {
                inventarioProductoCollectionInventarioProductoToAttach = em.getReference(inventarioProductoCollectionInventarioProductoToAttach.getClass(), inventarioProductoCollectionInventarioProductoToAttach.getIdInventarioProducto());
                attachedInventarioProductoCollection.add(inventarioProductoCollectionInventarioProductoToAttach);
            }
            bodegaProductos.setInventarioProductoCollection(attachedInventarioProductoCollection);
            em.persist(bodegaProductos);
            for (InventarioProducto inventarioProductoCollectionInventarioProducto : bodegaProductos.getInventarioProductoCollection()) {
                BodegaProductos oldBodegaProductosidbodegaOfInventarioProductoCollectionInventarioProducto = inventarioProductoCollectionInventarioProducto.getBodegaProductosidbodega();
                inventarioProductoCollectionInventarioProducto.setBodegaProductosidbodega(bodegaProductos);
                inventarioProductoCollectionInventarioProducto = em.merge(inventarioProductoCollectionInventarioProducto);
                if (oldBodegaProductosidbodegaOfInventarioProductoCollectionInventarioProducto != null) {
                    oldBodegaProductosidbodegaOfInventarioProductoCollectionInventarioProducto.getInventarioProductoCollection().remove(inventarioProductoCollectionInventarioProducto);
                    oldBodegaProductosidbodegaOfInventarioProductoCollectionInventarioProducto = em.merge(oldBodegaProductosidbodegaOfInventarioProductoCollectionInventarioProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BodegaProductos bodegaProductos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BodegaProductos persistentBodegaProductos = em.find(BodegaProductos.class, bodegaProductos.getIdbodega());
            Collection<InventarioProducto> inventarioProductoCollectionOld = persistentBodegaProductos.getInventarioProductoCollection();
            Collection<InventarioProducto> inventarioProductoCollectionNew = bodegaProductos.getInventarioProductoCollection();
            List<String> illegalOrphanMessages = null;
            for (InventarioProducto inventarioProductoCollectionOldInventarioProducto : inventarioProductoCollectionOld) {
                if (!inventarioProductoCollectionNew.contains(inventarioProductoCollectionOldInventarioProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InventarioProducto " + inventarioProductoCollectionOldInventarioProducto + " since its bodegaProductosidbodega field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<InventarioProducto> attachedInventarioProductoCollectionNew = new ArrayList<InventarioProducto>();
            for (InventarioProducto inventarioProductoCollectionNewInventarioProductoToAttach : inventarioProductoCollectionNew) {
                inventarioProductoCollectionNewInventarioProductoToAttach = em.getReference(inventarioProductoCollectionNewInventarioProductoToAttach.getClass(), inventarioProductoCollectionNewInventarioProductoToAttach.getIdInventarioProducto());
                attachedInventarioProductoCollectionNew.add(inventarioProductoCollectionNewInventarioProductoToAttach);
            }
            inventarioProductoCollectionNew = attachedInventarioProductoCollectionNew;
            bodegaProductos.setInventarioProductoCollection(inventarioProductoCollectionNew);
            bodegaProductos = em.merge(bodegaProductos);
            for (InventarioProducto inventarioProductoCollectionNewInventarioProducto : inventarioProductoCollectionNew) {
                if (!inventarioProductoCollectionOld.contains(inventarioProductoCollectionNewInventarioProducto)) {
                    BodegaProductos oldBodegaProductosidbodegaOfInventarioProductoCollectionNewInventarioProducto = inventarioProductoCollectionNewInventarioProducto.getBodegaProductosidbodega();
                    inventarioProductoCollectionNewInventarioProducto.setBodegaProductosidbodega(bodegaProductos);
                    inventarioProductoCollectionNewInventarioProducto = em.merge(inventarioProductoCollectionNewInventarioProducto);
                    if (oldBodegaProductosidbodegaOfInventarioProductoCollectionNewInventarioProducto != null && !oldBodegaProductosidbodegaOfInventarioProductoCollectionNewInventarioProducto.equals(bodegaProductos)) {
                        oldBodegaProductosidbodegaOfInventarioProductoCollectionNewInventarioProducto.getInventarioProductoCollection().remove(inventarioProductoCollectionNewInventarioProducto);
                        oldBodegaProductosidbodegaOfInventarioProductoCollectionNewInventarioProducto = em.merge(oldBodegaProductosidbodegaOfInventarioProductoCollectionNewInventarioProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bodegaProductos.getIdbodega();
                if (findBodegaProductos(id) == null) {
                    throw new NonexistentEntityException("The bodegaProductos with id " + id + " no longer exists.");
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
            BodegaProductos bodegaProductos;
            try {
                bodegaProductos = em.getReference(BodegaProductos.class, id);
                bodegaProductos.getIdbodega();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bodegaProductos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<InventarioProducto> inventarioProductoCollectionOrphanCheck = bodegaProductos.getInventarioProductoCollection();
            for (InventarioProducto inventarioProductoCollectionOrphanCheckInventarioProducto : inventarioProductoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BodegaProductos (" + bodegaProductos + ") cannot be destroyed since the InventarioProducto " + inventarioProductoCollectionOrphanCheckInventarioProducto + " in its inventarioProductoCollection field has a non-nullable bodegaProductosidbodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(bodegaProductos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BodegaProductos> findBodegaProductosEntities() {
        return findBodegaProductosEntities(true, -1, -1);
    }

    public List<BodegaProductos> findBodegaProductosEntities(int maxResults, int firstResult) {
        return findBodegaProductosEntities(false, maxResults, firstResult);
    }

    private List<BodegaProductos> findBodegaProductosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BodegaProductos.class));
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

    public BodegaProductos findBodegaProductos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BodegaProductos.class, id);
        } finally {
            em.close();
        }
    }

    public int getBodegaProductosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BodegaProductos> rt = cq.from(BodegaProductos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
