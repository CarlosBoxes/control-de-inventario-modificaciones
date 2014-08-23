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
import EntidadesJPA.Proveedores;
import EntidadesJPA.DescripcionPedidoMateriaPrima;
import EntidadesJPA.PedidoMateriaPrima;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class PedidoMateriaPrimaJpaController implements Serializable {

    public PedidoMateriaPrimaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PedidoMateriaPrima pedidoMateriaPrima) {
        if (pedidoMateriaPrima.getDescripcionPedidoMateriaPrimaCollection() == null) {
            pedidoMateriaPrima.setDescripcionPedidoMateriaPrimaCollection(new ArrayList<DescripcionPedidoMateriaPrima>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores proveedoresidProveedores = pedidoMateriaPrima.getProveedoresidProveedores();
            if (proveedoresidProveedores != null) {
                proveedoresidProveedores = em.getReference(proveedoresidProveedores.getClass(), proveedoresidProveedores.getIdProveedores());
                pedidoMateriaPrima.setProveedoresidProveedores(proveedoresidProveedores);
            }
            Collection<DescripcionPedidoMateriaPrima> attachedDescripcionPedidoMateriaPrimaCollection = new ArrayList<DescripcionPedidoMateriaPrima>();
            for (DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrimaToAttach : pedidoMateriaPrima.getDescripcionPedidoMateriaPrimaCollection()) {
                descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrimaToAttach = em.getReference(descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrimaToAttach.getClass(), descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrimaToAttach.getIddescripcionMP());
                attachedDescripcionPedidoMateriaPrimaCollection.add(descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrimaToAttach);
            }
            pedidoMateriaPrima.setDescripcionPedidoMateriaPrimaCollection(attachedDescripcionPedidoMateriaPrimaCollection);
            em.persist(pedidoMateriaPrima);
            if (proveedoresidProveedores != null) {
                proveedoresidProveedores.getPedidoMateriaPrimaCollection().add(pedidoMateriaPrima);
                proveedoresidProveedores = em.merge(proveedoresidProveedores);
            }
            for (DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima : pedidoMateriaPrima.getDescripcionPedidoMateriaPrimaCollection()) {
                PedidoMateriaPrima oldPedidomateriaprimaidpedidoMPOfDescripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima = descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima.getPedidomateriaprimaidpedidoMP();
                descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima.setPedidomateriaprimaidpedidoMP(pedidoMateriaPrima);
                descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima = em.merge(descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima);
                if (oldPedidomateriaprimaidpedidoMPOfDescripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima != null) {
                    oldPedidomateriaprimaidpedidoMPOfDescripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima.getDescripcionPedidoMateriaPrimaCollection().remove(descripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima);
                    oldPedidomateriaprimaidpedidoMPOfDescripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima = em.merge(oldPedidomateriaprimaidpedidoMPOfDescripcionPedidoMateriaPrimaCollectionDescripcionPedidoMateriaPrima);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PedidoMateriaPrima pedidoMateriaPrima) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedidoMateriaPrima persistentPedidoMateriaPrima = em.find(PedidoMateriaPrima.class, pedidoMateriaPrima.getIdpedidoMP());
            Proveedores proveedoresidProveedoresOld = persistentPedidoMateriaPrima.getProveedoresidProveedores();
            Proveedores proveedoresidProveedoresNew = pedidoMateriaPrima.getProveedoresidProveedores();
            Collection<DescripcionPedidoMateriaPrima> descripcionPedidoMateriaPrimaCollectionOld = persistentPedidoMateriaPrima.getDescripcionPedidoMateriaPrimaCollection();
            Collection<DescripcionPedidoMateriaPrima> descripcionPedidoMateriaPrimaCollectionNew = pedidoMateriaPrima.getDescripcionPedidoMateriaPrimaCollection();
            List<String> illegalOrphanMessages = null;
            for (DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrimaCollectionOldDescripcionPedidoMateriaPrima : descripcionPedidoMateriaPrimaCollectionOld) {
                if (!descripcionPedidoMateriaPrimaCollectionNew.contains(descripcionPedidoMateriaPrimaCollectionOldDescripcionPedidoMateriaPrima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DescripcionPedidoMateriaPrima " + descripcionPedidoMateriaPrimaCollectionOldDescripcionPedidoMateriaPrima + " since its pedidomateriaprimaidpedidoMP field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedoresidProveedoresNew != null) {
                proveedoresidProveedoresNew = em.getReference(proveedoresidProveedoresNew.getClass(), proveedoresidProveedoresNew.getIdProveedores());
                pedidoMateriaPrima.setProveedoresidProveedores(proveedoresidProveedoresNew);
            }
            Collection<DescripcionPedidoMateriaPrima> attachedDescripcionPedidoMateriaPrimaCollectionNew = new ArrayList<DescripcionPedidoMateriaPrima>();
            for (DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrimaToAttach : descripcionPedidoMateriaPrimaCollectionNew) {
                descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrimaToAttach = em.getReference(descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrimaToAttach.getClass(), descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrimaToAttach.getIddescripcionMP());
                attachedDescripcionPedidoMateriaPrimaCollectionNew.add(descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrimaToAttach);
            }
            descripcionPedidoMateriaPrimaCollectionNew = attachedDescripcionPedidoMateriaPrimaCollectionNew;
            pedidoMateriaPrima.setDescripcionPedidoMateriaPrimaCollection(descripcionPedidoMateriaPrimaCollectionNew);
            pedidoMateriaPrima = em.merge(pedidoMateriaPrima);
            if (proveedoresidProveedoresOld != null && !proveedoresidProveedoresOld.equals(proveedoresidProveedoresNew)) {
                proveedoresidProveedoresOld.getPedidoMateriaPrimaCollection().remove(pedidoMateriaPrima);
                proveedoresidProveedoresOld = em.merge(proveedoresidProveedoresOld);
            }
            if (proveedoresidProveedoresNew != null && !proveedoresidProveedoresNew.equals(proveedoresidProveedoresOld)) {
                proveedoresidProveedoresNew.getPedidoMateriaPrimaCollection().add(pedidoMateriaPrima);
                proveedoresidProveedoresNew = em.merge(proveedoresidProveedoresNew);
            }
            for (DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima : descripcionPedidoMateriaPrimaCollectionNew) {
                if (!descripcionPedidoMateriaPrimaCollectionOld.contains(descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima)) {
                    PedidoMateriaPrima oldPedidomateriaprimaidpedidoMPOfDescripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima = descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima.getPedidomateriaprimaidpedidoMP();
                    descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima.setPedidomateriaprimaidpedidoMP(pedidoMateriaPrima);
                    descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima = em.merge(descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima);
                    if (oldPedidomateriaprimaidpedidoMPOfDescripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima != null && !oldPedidomateriaprimaidpedidoMPOfDescripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima.equals(pedidoMateriaPrima)) {
                        oldPedidomateriaprimaidpedidoMPOfDescripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima.getDescripcionPedidoMateriaPrimaCollection().remove(descripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima);
                        oldPedidomateriaprimaidpedidoMPOfDescripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima = em.merge(oldPedidomateriaprimaidpedidoMPOfDescripcionPedidoMateriaPrimaCollectionNewDescripcionPedidoMateriaPrima);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedidoMateriaPrima.getIdpedidoMP();
                if (findPedidoMateriaPrima(id) == null) {
                    throw new NonexistentEntityException("The pedidoMateriaPrima with id " + id + " no longer exists.");
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
            PedidoMateriaPrima pedidoMateriaPrima;
            try {
                pedidoMateriaPrima = em.getReference(PedidoMateriaPrima.class, id);
                pedidoMateriaPrima.getIdpedidoMP();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidoMateriaPrima with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DescripcionPedidoMateriaPrima> descripcionPedidoMateriaPrimaCollectionOrphanCheck = pedidoMateriaPrima.getDescripcionPedidoMateriaPrimaCollection();
            for (DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrimaCollectionOrphanCheckDescripcionPedidoMateriaPrima : descripcionPedidoMateriaPrimaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PedidoMateriaPrima (" + pedidoMateriaPrima + ") cannot be destroyed since the DescripcionPedidoMateriaPrima " + descripcionPedidoMateriaPrimaCollectionOrphanCheckDescripcionPedidoMateriaPrima + " in its descripcionPedidoMateriaPrimaCollection field has a non-nullable pedidomateriaprimaidpedidoMP field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedores proveedoresidProveedores = pedidoMateriaPrima.getProveedoresidProveedores();
            if (proveedoresidProveedores != null) {
                proveedoresidProveedores.getPedidoMateriaPrimaCollection().remove(pedidoMateriaPrima);
                proveedoresidProveedores = em.merge(proveedoresidProveedores);
            }
            em.remove(pedidoMateriaPrima);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PedidoMateriaPrima> findPedidoMateriaPrimaEntities() {
        return findPedidoMateriaPrimaEntities(true, -1, -1);
    }

    public List<PedidoMateriaPrima> findPedidoMateriaPrimaEntities(int maxResults, int firstResult) {
        return findPedidoMateriaPrimaEntities(false, maxResults, firstResult);
    }

    private List<PedidoMateriaPrima> findPedidoMateriaPrimaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PedidoMateriaPrima.class));
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

    public PedidoMateriaPrima findPedidoMateriaPrima(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PedidoMateriaPrima.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoMateriaPrimaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PedidoMateriaPrima> rt = cq.from(PedidoMateriaPrima.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
