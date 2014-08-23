/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.IllegalOrphanException;
import ControladoresJPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.InventarioMateriaPrima;
import java.util.ArrayList;
import java.util.Collection;
import EntidadesJPA.DescripcionPedidoMateriaPrima;
import EntidadesJPA.MateriaPrima;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class MateriaPrimaJpaController implements Serializable {

    public MateriaPrimaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MateriaPrima materiaPrima) {
        if (materiaPrima.getInventarioMateriaPrimaCollection() == null) {
            materiaPrima.setInventarioMateriaPrimaCollection(new ArrayList<InventarioMateriaPrima>());
        }
        if (materiaPrima.getDescripcionPedidoMateriaPrimaCollection() == null) {
            materiaPrima.setDescripcionPedidoMateriaPrimaCollection(new ArrayList<DescripcionPedidoMateriaPrima>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<InventarioMateriaPrima> attachedInventarioMateriaPrimaCollection = new ArrayList<InventarioMateriaPrima>();
            for (InventarioMateriaPrima inventarioMateriaPrimaCollectionInventarioMateriaPrimaToAttach : materiaPrima.getInventarioMateriaPrimaCollection()) {
                inventarioMateriaPrimaCollectionInventarioMateriaPrimaToAttach = em.getReference(inventarioMateriaPrimaCollectionInventarioMateriaPrimaToAttach.getClass(), inventarioMateriaPrimaCollectionInventarioMateriaPrimaToAttach.getIdInventarioMateriaPrima());
                attachedInventarioMateriaPrimaCollection.add(inventarioMateriaPrimaCollectionInventarioMateriaPrimaToAttach);
            }
            materiaPrima.setInventarioMateriaPrimaCollection(attachedInventarioMateriaPrimaCollection);
            Collection<DescripcionPedidoMateriaPrima> attachedDescripcionPedidoMateriaPrimaCollection = new ArrayList<DescripcionPedidoMateriaPrima>();
            for (DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrimaToAttach : materiaPrima.getDescripcionPedidoMateriaPrimaCollection()) {
                descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrimaToAttach = em.getReference(descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrimaToAttach.getClass(), descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrimaToAttach.getIddescripcionMP());
                attachedDescripcionPedidoMateriaPrimaCollection.add(descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrimaToAttach);
            }
            materiaPrima.setDescripcionPedidoMateriaPrimaCollection(attachedDescripcionPedidoMateriaPrimaCollection);
            em.persist(materiaPrima);
            for (InventarioMateriaPrima inventarioMateriaPrimaCollectionInventarioMateriaPrima : materiaPrima.getInventarioMateriaPrimaCollection()) {
                MateriaPrima oldMateriaPrimaidmateriaPrimaOfInventarioMateriaPrimaCollectionInventarioMateriaPrima = inventarioMateriaPrimaCollectionInventarioMateriaPrima.getMateriaPrimaidmateriaPrima();
                inventarioMateriaPrimaCollectionInventarioMateriaPrima.setMateriaPrimaidmateriaPrima(materiaPrima);
                inventarioMateriaPrimaCollectionInventarioMateriaPrima = em.merge(inventarioMateriaPrimaCollectionInventarioMateriaPrima);
                if (oldMateriaPrimaidmateriaPrimaOfInventarioMateriaPrimaCollectionInventarioMateriaPrima != null) {
                    oldMateriaPrimaidmateriaPrimaOfInventarioMateriaPrimaCollectionInventarioMateriaPrima.getInventarioMateriaPrimaCollection().remove(inventarioMateriaPrimaCollectionInventarioMateriaPrima);
                    oldMateriaPrimaidmateriaPrimaOfInventarioMateriaPrimaCollectionInventarioMateriaPrima = em.merge(oldMateriaPrimaidmateriaPrimaOfInventarioMateriaPrimaCollectionInventarioMateriaPrima);
                }
            }
            for (DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima : materiaPrima.getDescripcionPedidoMateriaPrimaCollection()) {
                MateriaPrima oldMateriaPrimaidmateriaPrimaOfDescripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima = descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima.getMateriaPrimaidmateriaPrima();
                descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima.setMateriaPrimaidmateriaPrima(materiaPrima);
                descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima = em.merge(descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima);
                if (oldMateriaPrimaidmateriaPrimaOfDescripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima != null) {
                    oldMateriaPrimaidmateriaPrimaOfDescripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima.getDescripcionPedidoMateriaPrimaCollection().remove(descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima);
                    oldMateriaPrimaidmateriaPrimaOfDescripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima = em.merge(oldMateriaPrimaidmateriaPrimaOfDescripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MateriaPrima materiaPrima) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MateriaPrima persistentMateriaPrima = em.find(MateriaPrima.class, materiaPrima.getIdmateriaPrima());
            Collection<InventarioMateriaPrima> inventarioMateriaPrimaCollectionOld = persistentMateriaPrima.getInventarioMateriaPrimaCollection();
            Collection<InventarioMateriaPrima> inventarioMateriaPrimaCollectionNew = materiaPrima.getInventarioMateriaPrimaCollection();
            Collection<DescripcionPedidoMateriaPrima> descripcionPedidoMateriaPrimaCollectionOld = persistentMateriaPrima.getDescripcionPedidoMateriaPrimaCollection();
            Collection<DescripcionPedidoMateriaPrima> descripcionPedidoMateriaPrimaCollectionNew = materiaPrima.getDescripcionPedidoMateriaPrimaCollection();
            List<String> illegalOrphanMessages = null;
            for (InventarioMateriaPrima inventarioMateriaPrimaCollectionOldInventarioMateriaPrima : inventarioMateriaPrimaCollectionOld) {
                if (!inventarioMateriaPrimaCollectionNew.contains(inventarioMateriaPrimaCollectionOldInventarioMateriaPrima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InventarioMateriaPrima " + inventarioMateriaPrimaCollectionOldInventarioMateriaPrima + " since its materiaPrimaidmateriaPrima field is not nullable.");
                }
            }
            for (DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrimaCollectionOldDescripcionPedidoMateriaPrima : descripcionPedidoMateriaPrimaCollectionOld) {
                if (!descripcionPedidoMateriaPrimaCollectionNew.contains(descripcionPedidoMateriaPrimaCollectionOldDescripcionPedidoMateriaPrima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DescripcionPedidoMateriaPrima " + descripcionPedidoMateriaPrimaCollectionOldDescripcionPedidoMateriaPrima + " since its materiaPrimaidmateriaPrima field is not nullable.");
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
            materiaPrima.setInventarioMateriaPrimaCollection(inventarioMateriaPrimaCollectionNew);
            Collection<DescripcionPedidoMateriaPrima> attachedDescripcionPedidoMateriaPrimaCollectionNew = new ArrayList<DescripcionPedidoMateriaPrima>();
            for (DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrimaToAttach : descripcionPedidoMateriaPrimaCollectionNew) {
                descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrimaToAttach = em.getReference(descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrimaToAttach.getClass(), descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrimaToAttach.getIddescripcionMP());
                attachedDescripcionPedidoMateriaPrimaCollectionNew.add(descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrimaToAttach);
            }
            descripcionPedidoMateriaPrimaCollectionNew = attachedDescripcionPedidoMateriaPrimaCollectionNew;
            materiaPrima.setDescripcionPedidoMateriaPrimaCollection(descripcionPedidoMateriaPrimaCollectionNew);
            materiaPrima = em.merge(materiaPrima);
            for (InventarioMateriaPrima inventarioMateriaPrimaCollectionNewInventarioMateriaPrima : inventarioMateriaPrimaCollectionNew) {
                if (!inventarioMateriaPrimaCollectionOld.contains(inventarioMateriaPrimaCollectionNewInventarioMateriaPrima)) {
                    MateriaPrima oldMateriaPrimaidmateriaPrimaOfInventarioMateriaPrimaCollectionNewInventarioMateriaPrima = inventarioMateriaPrimaCollectionNewInventarioMateriaPrima.getMateriaPrimaidmateriaPrima();
                    inventarioMateriaPrimaCollectionNewInventarioMateriaPrima.setMateriaPrimaidmateriaPrima(materiaPrima);
                    inventarioMateriaPrimaCollectionNewInventarioMateriaPrima = em.merge(inventarioMateriaPrimaCollectionNewInventarioMateriaPrima);
                    if (oldMateriaPrimaidmateriaPrimaOfInventarioMateriaPrimaCollectionNewInventarioMateriaPrima != null && !oldMateriaPrimaidmateriaPrimaOfInventarioMateriaPrimaCollectionNewInventarioMateriaPrima.equals(materiaPrima)) {
                        oldMateriaPrimaidmateriaPrimaOfInventarioMateriaPrimaCollectionNewInventarioMateriaPrima.getInventarioMateriaPrimaCollection().remove(inventarioMateriaPrimaCollectionNewInventarioMateriaPrima);
                        oldMateriaPrimaidmateriaPrimaOfInventarioMateriaPrimaCollectionNewInventarioMateriaPrima = em.merge(oldMateriaPrimaidmateriaPrimaOfInventarioMateriaPrimaCollectionNewInventarioMateriaPrima);
                    }
                }
            }
            for (DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima : descripcionPedidoMateriaPrimaCollectionNew) {
                if (!descripcionPedidoMateriaPrimaCollectionOld.contains(descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima)) {
                    MateriaPrima oldMateriaPrimaidmateriaPrimaOfDescripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima = descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima.getMateriaPrimaidmateriaPrima();
                    descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima.setMateriaPrimaidmateriaPrima(materiaPrima);
                    descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima = em.merge(descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima);
                    if (oldMateriaPrimaidmateriaPrimaOfDescripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima != null && !oldMateriaPrimaidmateriaPrimaOfDescripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima.equals(materiaPrima)) {
                        oldMateriaPrimaidmateriaPrimaOfDescripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima.getDescripcionPedidoMateriaPrimaCollection().remove(descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima);
                        oldMateriaPrimaidmateriaPrimaOfDescripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima = em.merge(oldMateriaPrimaidmateriaPrimaOfDescripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materiaPrima.getIdmateriaPrima();
                if (findMateriaPrima(id) == null) {
                    throw new NonexistentEntityException("The materiaPrima with id " + id + " no longer exists.");
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
            MateriaPrima materiaPrima;
            try {
                materiaPrima = em.getReference(MateriaPrima.class, id);
                materiaPrima.getIdmateriaPrima();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiaPrima with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<InventarioMateriaPrima> inventarioMateriaPrimaCollectionOrphanCheck = materiaPrima.getInventarioMateriaPrimaCollection();
            for (InventarioMateriaPrima inventarioMateriaPrimaCollectionOrphanCheckInventarioMateriaPrima : inventarioMateriaPrimaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MateriaPrima (" + materiaPrima + ") cannot be destroyed since the InventarioMateriaPrima " + inventarioMateriaPrimaCollectionOrphanCheckInventarioMateriaPrima + " in its inventarioMateriaPrimaCollection field has a non-nullable materiaPrimaidmateriaPrima field.");
            }
            Collection<DescripcionPedidoMateriaPrima> descripcionPedidoMateriaPrimaCollectionOrphanCheck = materiaPrima.getDescripcionPedidoMateriaPrimaCollection();
            for (DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrimaCollectionOrphanCheckDescripcionPedidoMateriaPrima : descripcionPedidoMateriaPrimaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MateriaPrima (" + materiaPrima + ") cannot be destroyed since the DescripcionPedidoMateriaPrima " + descripcionPedidoMateriaPrimaCollectionOrphanCheckDescripcionPedidoMateriaPrima + " in its descripcionPedidoMateriaPrimaCollection field has a non-nullable materiaPrimaidmateriaPrima field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(materiaPrima);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MateriaPrima> findMateriaPrimaEntities() {
        return findMateriaPrimaEntities(true, -1, -1);
    }

    public List<MateriaPrima> findMateriaPrimaEntities(int maxResults, int firstResult) {
        return findMateriaPrimaEntities(false, maxResults, firstResult);
    }

    private List<MateriaPrima> findMateriaPrimaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MateriaPrima.class));
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

    public MateriaPrima findMateriaPrima(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MateriaPrima.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaPrimaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MateriaPrima> rt = cq.from(MateriaPrima.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
