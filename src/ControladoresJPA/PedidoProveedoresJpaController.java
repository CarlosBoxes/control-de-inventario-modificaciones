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
import EntidadesJPA.DescripcionPedidoProveedores;
import EntidadesJPA.PedidoProveedores;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class PedidoProveedoresJpaController implements Serializable {

    public PedidoProveedoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PedidoProveedores pedidoProveedores) {
        if (pedidoProveedores.getDescripcionPedidoProveedoresCollection() == null) {
            pedidoProveedores.setDescripcionPedidoProveedoresCollection(new ArrayList<DescripcionPedidoProveedores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores proveedoresidProveedores = pedidoProveedores.getProveedoresidProveedores();
            if (proveedoresidProveedores != null) {
                proveedoresidProveedores = em.getReference(proveedoresidProveedores.getClass(), proveedoresidProveedores.getIdProveedores());
                pedidoProveedores.setProveedoresidProveedores(proveedoresidProveedores);
            }
            Collection<DescripcionPedidoProveedores> attachedDescripcionPedidoProveedoresCollection = new ArrayList<DescripcionPedidoProveedores>();
            for (DescripcionPedidoProveedores descripcionPedidoProveedoresCollectionDescripcionPedidoProveedoresToAttach : pedidoProveedores.getDescripcionPedidoProveedoresCollection()) {
                descripcionPedidoProveedoresCollectionDescripcionPedidoProveedoresToAttach = em.getReference(descripcionPedidoProveedoresCollectionDescripcionPedidoProveedoresToAttach.getClass(), descripcionPedidoProveedoresCollectionDescripcionPedidoProveedoresToAttach.getIddescripcionP());
                attachedDescripcionPedidoProveedoresCollection.add(descripcionPedidoProveedoresCollectionDescripcionPedidoProveedoresToAttach);
            }
            pedidoProveedores.setDescripcionPedidoProveedoresCollection(attachedDescripcionPedidoProveedoresCollection);
            em.persist(pedidoProveedores);
            if (proveedoresidProveedores != null) {
                proveedoresidProveedores.getPedidoProveedoresCollection().add(pedidoProveedores);
                proveedoresidProveedores = em.merge(proveedoresidProveedores);
            }
            for (DescripcionPedidoProveedores descripcionPedidoProveedoresCollectionDescripcionPedidoProveedores : pedidoProveedores.getDescripcionPedidoProveedoresCollection()) {
                PedidoProveedores oldPedidoProveedoresIdpedidoProveedoresOfDescripcionPedidoProveedoresCollectionDescripcionPedidoProveedores = descripcionPedidoProveedoresCollectionDescripcionPedidoProveedores.getPedidoProveedoresIdpedidoProveedores();
                descripcionPedidoProveedoresCollectionDescripcionPedidoProveedores.setPedidoProveedoresIdpedidoProveedores(pedidoProveedores);
                descripcionPedidoProveedoresCollectionDescripcionPedidoProveedores = em.merge(descripcionPedidoProveedoresCollectionDescripcionPedidoProveedores);
                if (oldPedidoProveedoresIdpedidoProveedoresOfDescripcionPedidoProveedoresCollectionDescripcionPedidoProveedores != null) {
                    oldPedidoProveedoresIdpedidoProveedoresOfDescripcionPedidoProveedoresCollectionDescripcionPedidoProveedores.getDescripcionPedidoProveedoresCollection().remove(descripcionPedidoProveedoresCollectionDescripcionPedidoProveedores);
                    oldPedidoProveedoresIdpedidoProveedoresOfDescripcionPedidoProveedoresCollectionDescripcionPedidoProveedores = em.merge(oldPedidoProveedoresIdpedidoProveedoresOfDescripcionPedidoProveedoresCollectionDescripcionPedidoProveedores);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PedidoProveedores pedidoProveedores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedidoProveedores persistentPedidoProveedores = em.find(PedidoProveedores.class, pedidoProveedores.getIdpedidoProveedores());
            Proveedores proveedoresidProveedoresOld = persistentPedidoProveedores.getProveedoresidProveedores();
            Proveedores proveedoresidProveedoresNew = pedidoProveedores.getProveedoresidProveedores();
            Collection<DescripcionPedidoProveedores> descripcionPedidoProveedoresCollectionOld = persistentPedidoProveedores.getDescripcionPedidoProveedoresCollection();
            Collection<DescripcionPedidoProveedores> descripcionPedidoProveedoresCollectionNew = pedidoProveedores.getDescripcionPedidoProveedoresCollection();
            List<String> illegalOrphanMessages = null;
            for (DescripcionPedidoProveedores descripcionPedidoProveedoresCollectionOldDescripcionPedidoProveedores : descripcionPedidoProveedoresCollectionOld) {
                if (!descripcionPedidoProveedoresCollectionNew.contains(descripcionPedidoProveedoresCollectionOldDescripcionPedidoProveedores)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DescripcionPedidoProveedores " + descripcionPedidoProveedoresCollectionOldDescripcionPedidoProveedores + " since its pedidoProveedoresIdpedidoProveedores field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedoresidProveedoresNew != null) {
                proveedoresidProveedoresNew = em.getReference(proveedoresidProveedoresNew.getClass(), proveedoresidProveedoresNew.getIdProveedores());
                pedidoProveedores.setProveedoresidProveedores(proveedoresidProveedoresNew);
            }
            Collection<DescripcionPedidoProveedores> attachedDescripcionPedidoProveedoresCollectionNew = new ArrayList<DescripcionPedidoProveedores>();
            for (DescripcionPedidoProveedores descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedoresToAttach : descripcionPedidoProveedoresCollectionNew) {
                descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedoresToAttach = em.getReference(descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedoresToAttach.getClass(), descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedoresToAttach.getIddescripcionP());
                attachedDescripcionPedidoProveedoresCollectionNew.add(descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedoresToAttach);
            }
            descripcionPedidoProveedoresCollectionNew = attachedDescripcionPedidoProveedoresCollectionNew;
            pedidoProveedores.setDescripcionPedidoProveedoresCollection(descripcionPedidoProveedoresCollectionNew);
            pedidoProveedores = em.merge(pedidoProveedores);
            if (proveedoresidProveedoresOld != null && !proveedoresidProveedoresOld.equals(proveedoresidProveedoresNew)) {
                proveedoresidProveedoresOld.getPedidoProveedoresCollection().remove(pedidoProveedores);
                proveedoresidProveedoresOld = em.merge(proveedoresidProveedoresOld);
            }
            if (proveedoresidProveedoresNew != null && !proveedoresidProveedoresNew.equals(proveedoresidProveedoresOld)) {
                proveedoresidProveedoresNew.getPedidoProveedoresCollection().add(pedidoProveedores);
                proveedoresidProveedoresNew = em.merge(proveedoresidProveedoresNew);
            }
            for (DescripcionPedidoProveedores descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores : descripcionPedidoProveedoresCollectionNew) {
                if (!descripcionPedidoProveedoresCollectionOld.contains(descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores)) {
                    PedidoProveedores oldPedidoProveedoresIdpedidoProveedoresOfDescripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores = descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores.getPedidoProveedoresIdpedidoProveedores();
                    descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores.setPedidoProveedoresIdpedidoProveedores(pedidoProveedores);
                    descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores = em.merge(descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores);
                    if (oldPedidoProveedoresIdpedidoProveedoresOfDescripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores != null && !oldPedidoProveedoresIdpedidoProveedoresOfDescripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores.equals(pedidoProveedores)) {
                        oldPedidoProveedoresIdpedidoProveedoresOfDescripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores.getDescripcionPedidoProveedoresCollection().remove(descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores);
                        oldPedidoProveedoresIdpedidoProveedoresOfDescripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores = em.merge(oldPedidoProveedoresIdpedidoProveedoresOfDescripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedidoProveedores.getIdpedidoProveedores();
                if (findPedidoProveedores(id) == null) {
                    throw new NonexistentEntityException("The pedidoProveedores with id " + id + " no longer exists.");
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
            PedidoProveedores pedidoProveedores;
            try {
                pedidoProveedores = em.getReference(PedidoProveedores.class, id);
                pedidoProveedores.getIdpedidoProveedores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidoProveedores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DescripcionPedidoProveedores> descripcionPedidoProveedoresCollectionOrphanCheck = pedidoProveedores.getDescripcionPedidoProveedoresCollection();
            for (DescripcionPedidoProveedores descripcionPedidoProveedoresCollectionOrphanCheckDescripcionPedidoProveedores : descripcionPedidoProveedoresCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PedidoProveedores (" + pedidoProveedores + ") cannot be destroyed since the DescripcionPedidoProveedores " + descripcionPedidoProveedoresCollectionOrphanCheckDescripcionPedidoProveedores + " in its descripcionPedidoProveedoresCollection field has a non-nullable pedidoProveedoresIdpedidoProveedores field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedores proveedoresidProveedores = pedidoProveedores.getProveedoresidProveedores();
            if (proveedoresidProveedores != null) {
                proveedoresidProveedores.getPedidoProveedoresCollection().remove(pedidoProveedores);
                proveedoresidProveedores = em.merge(proveedoresidProveedores);
            }
            em.remove(pedidoProveedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PedidoProveedores> findPedidoProveedoresEntities() {
        return findPedidoProveedoresEntities(true, -1, -1);
    }

    public List<PedidoProveedores> findPedidoProveedoresEntities(int maxResults, int firstResult) {
        return findPedidoProveedoresEntities(false, maxResults, firstResult);
    }

    private List<PedidoProveedores> findPedidoProveedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PedidoProveedores.class));
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

    public PedidoProveedores findPedidoProveedores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PedidoProveedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoProveedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PedidoProveedores> rt = cq.from(PedidoProveedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
