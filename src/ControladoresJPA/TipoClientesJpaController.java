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
import EntidadesJPA.Clientes;
import java.util.ArrayList;
import java.util.Collection;
import EntidadesJPA.ProductosClientes;
import EntidadesJPA.TipoClientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class TipoClientesJpaController implements Serializable {

    public TipoClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoClientes tipoClientes) {
        if (tipoClientes.getClientesCollection() == null) {
            tipoClientes.setClientesCollection(new ArrayList<Clientes>());
        }
        if (tipoClientes.getProductosclientesCollection() == null) {
            tipoClientes.setProductosclientesCollection(new ArrayList<ProductosClientes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Clientes> attachedClientesCollection = new ArrayList<Clientes>();
            for (Clientes clientesCollectionClientesToAttach : tipoClientes.getClientesCollection()) {
                clientesCollectionClientesToAttach = em.getReference(clientesCollectionClientesToAttach.getClass(), clientesCollectionClientesToAttach.getIdCliente());
                attachedClientesCollection.add(clientesCollectionClientesToAttach);
            }
            tipoClientes.setClientesCollection(attachedClientesCollection);
            Collection<ProductosClientes> attachedProductosclientesCollection = new ArrayList<ProductosClientes>();
            for (ProductosClientes productosclientesCollectionProductosClientesToAttach : tipoClientes.getProductosclientesCollection()) {
                productosclientesCollectionProductosClientesToAttach = em.getReference(productosclientesCollectionProductosClientesToAttach.getClass(), productosclientesCollectionProductosClientesToAttach.getIdproductosespecialclientes());
                attachedProductosclientesCollection.add(productosclientesCollectionProductosClientesToAttach);
            }
            tipoClientes.setProductosclientesCollection(attachedProductosclientesCollection);
            em.persist(tipoClientes);
            for (Clientes clientesCollectionClientes : tipoClientes.getClientesCollection()) {
                TipoClientes oldTipoClientesidTipoClientesOfClientesCollectionClientes = clientesCollectionClientes.getTipoClientesidTipoClientes();
                clientesCollectionClientes.setTipoClientesidTipoClientes(tipoClientes);
                clientesCollectionClientes = em.merge(clientesCollectionClientes);
                if (oldTipoClientesidTipoClientesOfClientesCollectionClientes != null) {
                    oldTipoClientesidTipoClientesOfClientesCollectionClientes.getClientesCollection().remove(clientesCollectionClientes);
                    oldTipoClientesidTipoClientesOfClientesCollectionClientes = em.merge(oldTipoClientesidTipoClientesOfClientesCollectionClientes);
                }
            }
            for (ProductosClientes productosclientesCollectionProductosClientes : tipoClientes.getProductosclientesCollection()) {
                TipoClientes oldTipoclientesidTipoClientesOfProductosclientesCollectionProductosClientes = productosclientesCollectionProductosClientes.getTipoclientesidTipoClientes();
                productosclientesCollectionProductosClientes.setTipoclientesidTipoClientes(tipoClientes);
                productosclientesCollectionProductosClientes = em.merge(productosclientesCollectionProductosClientes);
                if (oldTipoclientesidTipoClientesOfProductosclientesCollectionProductosClientes != null) {
                    oldTipoclientesidTipoClientesOfProductosclientesCollectionProductosClientes.getProductosclientesCollection().remove(productosclientesCollectionProductosClientes);
                    oldTipoclientesidTipoClientesOfProductosclientesCollectionProductosClientes = em.merge(oldTipoclientesidTipoClientesOfProductosclientesCollectionProductosClientes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoClientes tipoClientes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoClientes persistentTipoClientes = em.find(TipoClientes.class, tipoClientes.getIdTipoClientes());
            Collection<Clientes> clientesCollectionOld = persistentTipoClientes.getClientesCollection();
            Collection<Clientes> clientesCollectionNew = tipoClientes.getClientesCollection();
            Collection<ProductosClientes> productosclientesCollectionOld = persistentTipoClientes.getProductosclientesCollection();
            Collection<ProductosClientes> productosclientesCollectionNew = tipoClientes.getProductosclientesCollection();
            List<String> illegalOrphanMessages = null;
            for (Clientes clientesCollectionOldClientes : clientesCollectionOld) {
                if (!clientesCollectionNew.contains(clientesCollectionOldClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Clientes " + clientesCollectionOldClientes + " since its tipoClientesidTipoClientes field is not nullable.");
                }
            }
            for (ProductosClientes productosclientesCollectionOldProductosClientes : productosclientesCollectionOld) {
                if (!productosclientesCollectionNew.contains(productosclientesCollectionOldProductosClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductosClientes " + productosclientesCollectionOldProductosClientes + " since its tipoclientesidTipoClientes field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Clientes> attachedClientesCollectionNew = new ArrayList<Clientes>();
            for (Clientes clientesCollectionNewClientesToAttach : clientesCollectionNew) {
                clientesCollectionNewClientesToAttach = em.getReference(clientesCollectionNewClientesToAttach.getClass(), clientesCollectionNewClientesToAttach.getIdCliente());
                attachedClientesCollectionNew.add(clientesCollectionNewClientesToAttach);
            }
            clientesCollectionNew = attachedClientesCollectionNew;
            tipoClientes.setClientesCollection(clientesCollectionNew);
            Collection<ProductosClientes> attachedProductosclientesCollectionNew = new ArrayList<ProductosClientes>();
            for (ProductosClientes productosclientesCollectionNewProductosClientesToAttach : productosclientesCollectionNew) {
                productosclientesCollectionNewProductosClientesToAttach = em.getReference(productosclientesCollectionNewProductosClientesToAttach.getClass(), productosclientesCollectionNewProductosClientesToAttach.getIdproductosespecialclientes());
                attachedProductosclientesCollectionNew.add(productosclientesCollectionNewProductosClientesToAttach);
            }
            productosclientesCollectionNew = attachedProductosclientesCollectionNew;
            tipoClientes.setProductosclientesCollection(productosclientesCollectionNew);
            tipoClientes = em.merge(tipoClientes);
            for (Clientes clientesCollectionNewClientes : clientesCollectionNew) {
                if (!clientesCollectionOld.contains(clientesCollectionNewClientes)) {
                    TipoClientes oldTipoClientesidTipoClientesOfClientesCollectionNewClientes = clientesCollectionNewClientes.getTipoClientesidTipoClientes();
                    clientesCollectionNewClientes.setTipoClientesidTipoClientes(tipoClientes);
                    clientesCollectionNewClientes = em.merge(clientesCollectionNewClientes);
                    if (oldTipoClientesidTipoClientesOfClientesCollectionNewClientes != null && !oldTipoClientesidTipoClientesOfClientesCollectionNewClientes.equals(tipoClientes)) {
                        oldTipoClientesidTipoClientesOfClientesCollectionNewClientes.getClientesCollection().remove(clientesCollectionNewClientes);
                        oldTipoClientesidTipoClientesOfClientesCollectionNewClientes = em.merge(oldTipoClientesidTipoClientesOfClientesCollectionNewClientes);
                    }
                }
            }
            for (ProductosClientes productosclientesCollectionNewProductosClientes : productosclientesCollectionNew) {
                if (!productosclientesCollectionOld.contains(productosclientesCollectionNewProductosClientes)) {
                    TipoClientes oldTipoclientesidTipoClientesOfProductosclientesCollectionNewProductosClientes = productosclientesCollectionNewProductosClientes.getTipoclientesidTipoClientes();
                    productosclientesCollectionNewProductosClientes.setTipoclientesidTipoClientes(tipoClientes);
                    productosclientesCollectionNewProductosClientes = em.merge(productosclientesCollectionNewProductosClientes);
                    if (oldTipoclientesidTipoClientesOfProductosclientesCollectionNewProductosClientes != null && !oldTipoclientesidTipoClientesOfProductosclientesCollectionNewProductosClientes.equals(tipoClientes)) {
                        oldTipoclientesidTipoClientesOfProductosclientesCollectionNewProductosClientes.getProductosclientesCollection().remove(productosclientesCollectionNewProductosClientes);
                        oldTipoclientesidTipoClientesOfProductosclientesCollectionNewProductosClientes = em.merge(oldTipoclientesidTipoClientesOfProductosclientesCollectionNewProductosClientes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoClientes.getIdTipoClientes();
                if (findTipoClientes(id) == null) {
                    throw new NonexistentEntityException("The tipoClientes with id " + id + " no longer exists.");
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
            TipoClientes tipoClientes;
            try {
                tipoClientes = em.getReference(TipoClientes.class, id);
                tipoClientes.getIdTipoClientes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoClientes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Clientes> clientesCollectionOrphanCheck = tipoClientes.getClientesCollection();
            for (Clientes clientesCollectionOrphanCheckClientes : clientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoClientes (" + tipoClientes + ") cannot be destroyed since the Clientes " + clientesCollectionOrphanCheckClientes + " in its clientesCollection field has a non-nullable tipoClientesidTipoClientes field.");
            }
            Collection<ProductosClientes> productosclientesCollectionOrphanCheck = tipoClientes.getProductosclientesCollection();
            for (ProductosClientes productosclientesCollectionOrphanCheckProductosClientes : productosclientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoClientes (" + tipoClientes + ") cannot be destroyed since the ProductosClientes " + productosclientesCollectionOrphanCheckProductosClientes + " in its productosclientesCollection field has a non-nullable tipoclientesidTipoClientes field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoClientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoClientes> findTipoClientesEntities() {
        return findTipoClientesEntities(true, -1, -1);
    }

    public List<TipoClientes> findTipoClientesEntities(int maxResults, int firstResult) {
        return findTipoClientesEntities(false, maxResults, firstResult);
    }

    private List<TipoClientes> findTipoClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoClientes.class));
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

    public TipoClientes findTipoClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoClientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoClientes> rt = cq.from(TipoClientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
