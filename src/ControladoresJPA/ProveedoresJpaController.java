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
import EntidadesJPA.PedidoMateriaPrima;
import java.util.ArrayList;
import java.util.Collection;
import EntidadesJPA.ChequesProveedores;
import EntidadesJPA.PedidoProveedores;
import EntidadesJPA.Proveedores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ProveedoresJpaController implements Serializable {

    public ProveedoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedores proveedores) {
        if (proveedores.getPedidoMateriaPrimaCollection() == null) {
            proveedores.setPedidoMateriaPrimaCollection(new ArrayList<PedidoMateriaPrima>());
        }
        if (proveedores.getChequesProveedoresCollection() == null) {
            proveedores.setChequesProveedoresCollection(new ArrayList<ChequesProveedores>());
        }
        if (proveedores.getPedidoProveedoresCollection() == null) {
            proveedores.setPedidoProveedoresCollection(new ArrayList<PedidoProveedores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<PedidoMateriaPrima> attachedPedidoMateriaPrimaCollection = new ArrayList<PedidoMateriaPrima>();
            for (PedidoMateriaPrima pedidoMateriaPrimaCollectionPedidoMateriaPrimaToAttach : proveedores.getPedidoMateriaPrimaCollection()) {
                pedidoMateriaPrimaCollectionPedidoMateriaPrimaToAttach = em.getReference(pedidoMateriaPrimaCollectionPedidoMateriaPrimaToAttach.getClass(), pedidoMateriaPrimaCollectionPedidoMateriaPrimaToAttach.getIdpedidoMP());
                attachedPedidoMateriaPrimaCollection.add(pedidoMateriaPrimaCollectionPedidoMateriaPrimaToAttach);
            }
            proveedores.setPedidoMateriaPrimaCollection(attachedPedidoMateriaPrimaCollection);
            Collection<ChequesProveedores> attachedChequesProveedoresCollection = new ArrayList<ChequesProveedores>();
            for (ChequesProveedores chequesProveedoresCollectionChequesProveedoresToAttach : proveedores.getChequesProveedoresCollection()) {
                chequesProveedoresCollectionChequesProveedoresToAttach = em.getReference(chequesProveedoresCollectionChequesProveedoresToAttach.getClass(), chequesProveedoresCollectionChequesProveedoresToAttach.getIdchequesProveedores());
                attachedChequesProveedoresCollection.add(chequesProveedoresCollectionChequesProveedoresToAttach);
            }
            proveedores.setChequesProveedoresCollection(attachedChequesProveedoresCollection);
            Collection<PedidoProveedores> attachedPedidoProveedoresCollection = new ArrayList<PedidoProveedores>();
            for (PedidoProveedores pedidoProveedoresCollectionPedidoProveedoresToAttach : proveedores.getPedidoProveedoresCollection()) {
                pedidoProveedoresCollectionPedidoProveedoresToAttach = em.getReference(pedidoProveedoresCollectionPedidoProveedoresToAttach.getClass(), pedidoProveedoresCollectionPedidoProveedoresToAttach.getIdpedidoProveedores());
                attachedPedidoProveedoresCollection.add(pedidoProveedoresCollectionPedidoProveedoresToAttach);
            }
            proveedores.setPedidoProveedoresCollection(attachedPedidoProveedoresCollection);
            em.persist(proveedores);
            for (PedidoMateriaPrima pedidoMateriaPrimaCollectionPedidoMateriaPrima : proveedores.getPedidoMateriaPrimaCollection()) {
                Proveedores oldProveedoresidProveedoresOfPedidoMateriaPrimaCollectionPedidoMateriaPrima = pedidoMateriaPrimaCollectionPedidoMateriaPrima.getProveedoresidProveedores();
                pedidoMateriaPrimaCollectionPedidoMateriaPrima.setProveedoresidProveedores(proveedores);
                pedidoMateriaPrimaCollectionPedidoMateriaPrima = em.merge(pedidoMateriaPrimaCollectionPedidoMateriaPrima);
                if (oldProveedoresidProveedoresOfPedidoMateriaPrimaCollectionPedidoMateriaPrima != null) {
                    oldProveedoresidProveedoresOfPedidoMateriaPrimaCollectionPedidoMateriaPrima.getPedidoMateriaPrimaCollection().remove(pedidoMateriaPrimaCollectionPedidoMateriaPrima);
                    oldProveedoresidProveedoresOfPedidoMateriaPrimaCollectionPedidoMateriaPrima = em.merge(oldProveedoresidProveedoresOfPedidoMateriaPrimaCollectionPedidoMateriaPrima);
                }
            }
            for (ChequesProveedores chequesProveedoresCollectionChequesProveedores : proveedores.getChequesProveedoresCollection()) {
                Proveedores oldProveedoresidProveedoresOfChequesProveedoresCollectionChequesProveedores = chequesProveedoresCollectionChequesProveedores.getProveedoresidProveedores();
                chequesProveedoresCollectionChequesProveedores.setProveedoresidProveedores(proveedores);
                chequesProveedoresCollectionChequesProveedores = em.merge(chequesProveedoresCollectionChequesProveedores);
                if (oldProveedoresidProveedoresOfChequesProveedoresCollectionChequesProveedores != null) {
                    oldProveedoresidProveedoresOfChequesProveedoresCollectionChequesProveedores.getChequesProveedoresCollection().remove(chequesProveedoresCollectionChequesProveedores);
                    oldProveedoresidProveedoresOfChequesProveedoresCollectionChequesProveedores = em.merge(oldProveedoresidProveedoresOfChequesProveedoresCollectionChequesProveedores);
                }
            }
            for (PedidoProveedores pedidoProveedoresCollectionPedidoProveedores : proveedores.getPedidoProveedoresCollection()) {
                Proveedores oldProveedoresidProveedoresOfPedidoProveedoresCollectionPedidoProveedores = pedidoProveedoresCollectionPedidoProveedores.getProveedoresidProveedores();
                pedidoProveedoresCollectionPedidoProveedores.setProveedoresidProveedores(proveedores);
                pedidoProveedoresCollectionPedidoProveedores = em.merge(pedidoProveedoresCollectionPedidoProveedores);
                if (oldProveedoresidProveedoresOfPedidoProveedoresCollectionPedidoProveedores != null) {
                    oldProveedoresidProveedoresOfPedidoProveedoresCollectionPedidoProveedores.getPedidoProveedoresCollection().remove(pedidoProveedoresCollectionPedidoProveedores);
                    oldProveedoresidProveedoresOfPedidoProveedoresCollectionPedidoProveedores = em.merge(oldProveedoresidProveedoresOfPedidoProveedoresCollectionPedidoProveedores);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedores proveedores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores persistentProveedores = em.find(Proveedores.class, proveedores.getIdProveedores());
            Collection<PedidoMateriaPrima> pedidoMateriaPrimaCollectionOld = persistentProveedores.getPedidoMateriaPrimaCollection();
            Collection<PedidoMateriaPrima> pedidoMateriaPrimaCollectionNew = proveedores.getPedidoMateriaPrimaCollection();
            Collection<ChequesProveedores> chequesProveedoresCollectionOld = persistentProveedores.getChequesProveedoresCollection();
            Collection<ChequesProveedores> chequesProveedoresCollectionNew = proveedores.getChequesProveedoresCollection();
            Collection<PedidoProveedores> pedidoProveedoresCollectionOld = persistentProveedores.getPedidoProveedoresCollection();
            Collection<PedidoProveedores> pedidoProveedoresCollectionNew = proveedores.getPedidoProveedoresCollection();
            List<String> illegalOrphanMessages = null;
            for (PedidoMateriaPrima pedidoMateriaPrimaCollectionOldPedidoMateriaPrima : pedidoMateriaPrimaCollectionOld) {
                if (!pedidoMateriaPrimaCollectionNew.contains(pedidoMateriaPrimaCollectionOldPedidoMateriaPrima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PedidoMateriaPrima " + pedidoMateriaPrimaCollectionOldPedidoMateriaPrima + " since its proveedoresidProveedores field is not nullable.");
                }
            }
            for (ChequesProveedores chequesProveedoresCollectionOldChequesProveedores : chequesProveedoresCollectionOld) {
                if (!chequesProveedoresCollectionNew.contains(chequesProveedoresCollectionOldChequesProveedores)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ChequesProveedores " + chequesProveedoresCollectionOldChequesProveedores + " since its proveedoresidProveedores field is not nullable.");
                }
            }
            for (PedidoProveedores pedidoProveedoresCollectionOldPedidoProveedores : pedidoProveedoresCollectionOld) {
                if (!pedidoProveedoresCollectionNew.contains(pedidoProveedoresCollectionOldPedidoProveedores)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PedidoProveedores " + pedidoProveedoresCollectionOldPedidoProveedores + " since its proveedoresidProveedores field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<PedidoMateriaPrima> attachedPedidoMateriaPrimaCollectionNew = new ArrayList<PedidoMateriaPrima>();
            for (PedidoMateriaPrima pedidoMateriaPrimaCollectionNewPedidoMateriaPrimaToAttach : pedidoMateriaPrimaCollectionNew) {
                pedidoMateriaPrimaCollectionNewPedidoMateriaPrimaToAttach = em.getReference(pedidoMateriaPrimaCollectionNewPedidoMateriaPrimaToAttach.getClass(), pedidoMateriaPrimaCollectionNewPedidoMateriaPrimaToAttach.getIdpedidoMP());
                attachedPedidoMateriaPrimaCollectionNew.add(pedidoMateriaPrimaCollectionNewPedidoMateriaPrimaToAttach);
            }
            pedidoMateriaPrimaCollectionNew = attachedPedidoMateriaPrimaCollectionNew;
            proveedores.setPedidoMateriaPrimaCollection(pedidoMateriaPrimaCollectionNew);
            Collection<ChequesProveedores> attachedChequesProveedoresCollectionNew = new ArrayList<ChequesProveedores>();
            for (ChequesProveedores chequesProveedoresCollectionNewChequesProveedoresToAttach : chequesProveedoresCollectionNew) {
                chequesProveedoresCollectionNewChequesProveedoresToAttach = em.getReference(chequesProveedoresCollectionNewChequesProveedoresToAttach.getClass(), chequesProveedoresCollectionNewChequesProveedoresToAttach.getIdchequesProveedores());
                attachedChequesProveedoresCollectionNew.add(chequesProveedoresCollectionNewChequesProveedoresToAttach);
            }
            chequesProveedoresCollectionNew = attachedChequesProveedoresCollectionNew;
            proveedores.setChequesProveedoresCollection(chequesProveedoresCollectionNew);
            Collection<PedidoProveedores> attachedPedidoProveedoresCollectionNew = new ArrayList<PedidoProveedores>();
            for (PedidoProveedores pedidoProveedoresCollectionNewPedidoProveedoresToAttach : pedidoProveedoresCollectionNew) {
                pedidoProveedoresCollectionNewPedidoProveedoresToAttach = em.getReference(pedidoProveedoresCollectionNewPedidoProveedoresToAttach.getClass(), pedidoProveedoresCollectionNewPedidoProveedoresToAttach.getIdpedidoProveedores());
                attachedPedidoProveedoresCollectionNew.add(pedidoProveedoresCollectionNewPedidoProveedoresToAttach);
            }
            pedidoProveedoresCollectionNew = attachedPedidoProveedoresCollectionNew;
            proveedores.setPedidoProveedoresCollection(pedidoProveedoresCollectionNew);
            proveedores = em.merge(proveedores);
            for (PedidoMateriaPrima pedidoMateriaPrimaCollectionNewPedidoMateriaPrima : pedidoMateriaPrimaCollectionNew) {
                if (!pedidoMateriaPrimaCollectionOld.contains(pedidoMateriaPrimaCollectionNewPedidoMateriaPrima)) {
                    Proveedores oldProveedoresidProveedoresOfPedidoMateriaPrimaCollectionNewPedidoMateriaPrima = pedidoMateriaPrimaCollectionNewPedidoMateriaPrima.getProveedoresidProveedores();
                    pedidoMateriaPrimaCollectionNewPedidoMateriaPrima.setProveedoresidProveedores(proveedores);
                    pedidoMateriaPrimaCollectionNewPedidoMateriaPrima = em.merge(pedidoMateriaPrimaCollectionNewPedidoMateriaPrima);
                    if (oldProveedoresidProveedoresOfPedidoMateriaPrimaCollectionNewPedidoMateriaPrima != null && !oldProveedoresidProveedoresOfPedidoMateriaPrimaCollectionNewPedidoMateriaPrima.equals(proveedores)) {
                        oldProveedoresidProveedoresOfPedidoMateriaPrimaCollectionNewPedidoMateriaPrima.getPedidoMateriaPrimaCollection().remove(pedidoMateriaPrimaCollectionNewPedidoMateriaPrima);
                        oldProveedoresidProveedoresOfPedidoMateriaPrimaCollectionNewPedidoMateriaPrima = em.merge(oldProveedoresidProveedoresOfPedidoMateriaPrimaCollectionNewPedidoMateriaPrima);
                    }
                }
            }
            for (ChequesProveedores chequesProveedoresCollectionNewChequesProveedores : chequesProveedoresCollectionNew) {
                if (!chequesProveedoresCollectionOld.contains(chequesProveedoresCollectionNewChequesProveedores)) {
                    Proveedores oldProveedoresidProveedoresOfChequesProveedoresCollectionNewChequesProveedores = chequesProveedoresCollectionNewChequesProveedores.getProveedoresidProveedores();
                    chequesProveedoresCollectionNewChequesProveedores.setProveedoresidProveedores(proveedores);
                    chequesProveedoresCollectionNewChequesProveedores = em.merge(chequesProveedoresCollectionNewChequesProveedores);
                    if (oldProveedoresidProveedoresOfChequesProveedoresCollectionNewChequesProveedores != null && !oldProveedoresidProveedoresOfChequesProveedoresCollectionNewChequesProveedores.equals(proveedores)) {
                        oldProveedoresidProveedoresOfChequesProveedoresCollectionNewChequesProveedores.getChequesProveedoresCollection().remove(chequesProveedoresCollectionNewChequesProveedores);
                        oldProveedoresidProveedoresOfChequesProveedoresCollectionNewChequesProveedores = em.merge(oldProveedoresidProveedoresOfChequesProveedoresCollectionNewChequesProveedores);
                    }
                }
            }
            for (PedidoProveedores pedidoProveedoresCollectionNewPedidoProveedores : pedidoProveedoresCollectionNew) {
                if (!pedidoProveedoresCollectionOld.contains(pedidoProveedoresCollectionNewPedidoProveedores)) {
                    Proveedores oldProveedoresidProveedoresOfPedidoProveedoresCollectionNewPedidoProveedores = pedidoProveedoresCollectionNewPedidoProveedores.getProveedoresidProveedores();
                    pedidoProveedoresCollectionNewPedidoProveedores.setProveedoresidProveedores(proveedores);
                    pedidoProveedoresCollectionNewPedidoProveedores = em.merge(pedidoProveedoresCollectionNewPedidoProveedores);
                    if (oldProveedoresidProveedoresOfPedidoProveedoresCollectionNewPedidoProveedores != null && !oldProveedoresidProveedoresOfPedidoProveedoresCollectionNewPedidoProveedores.equals(proveedores)) {
                        oldProveedoresidProveedoresOfPedidoProveedoresCollectionNewPedidoProveedores.getPedidoProveedoresCollection().remove(pedidoProveedoresCollectionNewPedidoProveedores);
                        oldProveedoresidProveedoresOfPedidoProveedoresCollectionNewPedidoProveedores = em.merge(oldProveedoresidProveedoresOfPedidoProveedoresCollectionNewPedidoProveedores);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedores.getIdProveedores();
                if (findProveedores(id) == null) {
                    throw new NonexistentEntityException("The proveedores with id " + id + " no longer exists.");
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
            Proveedores proveedores;
            try {
                proveedores = em.getReference(Proveedores.class, id);
                proveedores.getIdProveedores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PedidoMateriaPrima> pedidoMateriaPrimaCollectionOrphanCheck = proveedores.getPedidoMateriaPrimaCollection();
            for (PedidoMateriaPrima pedidoMateriaPrimaCollectionOrphanCheckPedidoMateriaPrima : pedidoMateriaPrimaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedores (" + proveedores + ") cannot be destroyed since the PedidoMateriaPrima " + pedidoMateriaPrimaCollectionOrphanCheckPedidoMateriaPrima + " in its pedidoMateriaPrimaCollection field has a non-nullable proveedoresidProveedores field.");
            }
            Collection<ChequesProveedores> chequesProveedoresCollectionOrphanCheck = proveedores.getChequesProveedoresCollection();
            for (ChequesProveedores chequesProveedoresCollectionOrphanCheckChequesProveedores : chequesProveedoresCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedores (" + proveedores + ") cannot be destroyed since the ChequesProveedores " + chequesProveedoresCollectionOrphanCheckChequesProveedores + " in its chequesProveedoresCollection field has a non-nullable proveedoresidProveedores field.");
            }
            Collection<PedidoProveedores> pedidoProveedoresCollectionOrphanCheck = proveedores.getPedidoProveedoresCollection();
            for (PedidoProveedores pedidoProveedoresCollectionOrphanCheckPedidoProveedores : pedidoProveedoresCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedores (" + proveedores + ") cannot be destroyed since the PedidoProveedores " + pedidoProveedoresCollectionOrphanCheckPedidoProveedores + " in its pedidoProveedoresCollection field has a non-nullable proveedoresidProveedores field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(proveedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedores> findProveedoresEntities() {
        return findProveedoresEntities(true, -1, -1);
    }

    public List<Proveedores> findProveedoresEntities(int maxResults, int firstResult) {
        return findProveedoresEntities(false, maxResults, firstResult);
    }

    private List<Proveedores> findProveedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedores.class));
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

    public Proveedores findProveedores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedores> rt = cq.from(Proveedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
