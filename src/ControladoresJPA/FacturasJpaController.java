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
import EntidadesJPA.Vendedores;
import EntidadesJPA.Pedido;
import EntidadesJPA.DescripcionFactura;
import EntidadesJPA.Facturas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class FacturasJpaController implements Serializable {

    public FacturasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facturas facturas) {
        if (facturas.getDescripcionFacturaCollection() == null) {
            facturas.setDescripcionFacturaCollection(new ArrayList<DescripcionFactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clientesidCliente = facturas.getClientesidCliente();
            if (clientesidCliente != null) {
                clientesidCliente = em.getReference(clientesidCliente.getClass(), clientesidCliente.getIdCliente());
                facturas.setClientesidCliente(clientesidCliente);
            }
            Vendedores vendedoridVendedor = facturas.getVendedoridVendedor();
            if (vendedoridVendedor != null) {
                vendedoridVendedor = em.getReference(vendedoridVendedor.getClass(), vendedoridVendedor.getIdvendedores());
                facturas.setVendedoridVendedor(vendedoridVendedor);
            }
            Pedido pedidoidPedido = facturas.getPedidoidPedido();
            if (pedidoidPedido != null) {
                pedidoidPedido = em.getReference(pedidoidPedido.getClass(), pedidoidPedido.getIdpedido());
                facturas.setPedidoidPedido(pedidoidPedido);
            }
            Collection<DescripcionFactura> attachedDescripcionFacturaCollection = new ArrayList<DescripcionFactura>();
            for (DescripcionFactura descripcionFacturaCollectionDescripcionFacturaToAttach : facturas.getDescripcionFacturaCollection()) {
                descripcionFacturaCollectionDescripcionFacturaToAttach = em.getReference(descripcionFacturaCollectionDescripcionFacturaToAttach.getClass(), descripcionFacturaCollectionDescripcionFacturaToAttach.getIddescripcionFactura());
                attachedDescripcionFacturaCollection.add(descripcionFacturaCollectionDescripcionFacturaToAttach);
            }
            facturas.setDescripcionFacturaCollection(attachedDescripcionFacturaCollection);
            em.persist(facturas);
            if (clientesidCliente != null) {
                clientesidCliente.getFacturasCollection().add(facturas);
                clientesidCliente = em.merge(clientesidCliente);
            }
            if (vendedoridVendedor != null) {
                vendedoridVendedor.getFacturasCollection().add(facturas);
                vendedoridVendedor = em.merge(vendedoridVendedor);
            }
            if (pedidoidPedido != null) {
                pedidoidPedido.getFacturasCollection().add(facturas);
                pedidoidPedido = em.merge(pedidoidPedido);
            }
            for (DescripcionFactura descripcionFacturaCollectionDescripcionFactura : facturas.getDescripcionFacturaCollection()) {
                Facturas oldFacturasidFacturasOfDescripcionFacturaCollectionDescripcionFactura = descripcionFacturaCollectionDescripcionFactura.getFacturasidFacturas();
                descripcionFacturaCollectionDescripcionFactura.setFacturasidFacturas(facturas);
                descripcionFacturaCollectionDescripcionFactura = em.merge(descripcionFacturaCollectionDescripcionFactura);
                if (oldFacturasidFacturasOfDescripcionFacturaCollectionDescripcionFactura != null) {
                    oldFacturasidFacturasOfDescripcionFacturaCollectionDescripcionFactura.getDescripcionFacturaCollection().remove(descripcionFacturaCollectionDescripcionFactura);
                    oldFacturasidFacturasOfDescripcionFacturaCollectionDescripcionFactura = em.merge(oldFacturasidFacturasOfDescripcionFacturaCollectionDescripcionFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facturas facturas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturas persistentFacturas = em.find(Facturas.class, facturas.getIdFacturas());
            Clientes clientesidClienteOld = persistentFacturas.getClientesidCliente();
            Clientes clientesidClienteNew = facturas.getClientesidCliente();
            Vendedores vendedoridVendedorOld = persistentFacturas.getVendedoridVendedor();
            Vendedores vendedoridVendedorNew = facturas.getVendedoridVendedor();
            Pedido pedidoidPedidoOld = persistentFacturas.getPedidoidPedido();
            Pedido pedidoidPedidoNew = facturas.getPedidoidPedido();
            Collection<DescripcionFactura> descripcionFacturaCollectionOld = persistentFacturas.getDescripcionFacturaCollection();
            Collection<DescripcionFactura> descripcionFacturaCollectionNew = facturas.getDescripcionFacturaCollection();
            List<String> illegalOrphanMessages = null;
            for (DescripcionFactura descripcionFacturaCollectionOldDescripcionFactura : descripcionFacturaCollectionOld) {
                if (!descripcionFacturaCollectionNew.contains(descripcionFacturaCollectionOldDescripcionFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DescripcionFactura " + descripcionFacturaCollectionOldDescripcionFactura + " since its facturasidFacturas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clientesidClienteNew != null) {
                clientesidClienteNew = em.getReference(clientesidClienteNew.getClass(), clientesidClienteNew.getIdCliente());
                facturas.setClientesidCliente(clientesidClienteNew);
            }
            if (vendedoridVendedorNew != null) {
                vendedoridVendedorNew = em.getReference(vendedoridVendedorNew.getClass(), vendedoridVendedorNew.getIdvendedores());
                facturas.setVendedoridVendedor(vendedoridVendedorNew);
            }
            if (pedidoidPedidoNew != null) {
                pedidoidPedidoNew = em.getReference(pedidoidPedidoNew.getClass(), pedidoidPedidoNew.getIdpedido());
                facturas.setPedidoidPedido(pedidoidPedidoNew);
            }
            Collection<DescripcionFactura> attachedDescripcionFacturaCollectionNew = new ArrayList<DescripcionFactura>();
            for (DescripcionFactura descripcionFacturaCollectionNewDescripcionFacturaToAttach : descripcionFacturaCollectionNew) {
                descripcionFacturaCollectionNewDescripcionFacturaToAttach = em.getReference(descripcionFacturaCollectionNewDescripcionFacturaToAttach.getClass(), descripcionFacturaCollectionNewDescripcionFacturaToAttach.getIddescripcionFactura());
                attachedDescripcionFacturaCollectionNew.add(descripcionFacturaCollectionNewDescripcionFacturaToAttach);
            }
            descripcionFacturaCollectionNew = attachedDescripcionFacturaCollectionNew;
            facturas.setDescripcionFacturaCollection(descripcionFacturaCollectionNew);
            facturas = em.merge(facturas);
            if (clientesidClienteOld != null && !clientesidClienteOld.equals(clientesidClienteNew)) {
                clientesidClienteOld.getFacturasCollection().remove(facturas);
                clientesidClienteOld = em.merge(clientesidClienteOld);
            }
            if (clientesidClienteNew != null && !clientesidClienteNew.equals(clientesidClienteOld)) {
                clientesidClienteNew.getFacturasCollection().add(facturas);
                clientesidClienteNew = em.merge(clientesidClienteNew);
            }
            if (vendedoridVendedorOld != null && !vendedoridVendedorOld.equals(vendedoridVendedorNew)) {
                vendedoridVendedorOld.getFacturasCollection().remove(facturas);
                vendedoridVendedorOld = em.merge(vendedoridVendedorOld);
            }
            if (vendedoridVendedorNew != null && !vendedoridVendedorNew.equals(vendedoridVendedorOld)) {
                vendedoridVendedorNew.getFacturasCollection().add(facturas);
                vendedoridVendedorNew = em.merge(vendedoridVendedorNew);
            }
            if (pedidoidPedidoOld != null && !pedidoidPedidoOld.equals(pedidoidPedidoNew)) {
                pedidoidPedidoOld.getFacturasCollection().remove(facturas);
                pedidoidPedidoOld = em.merge(pedidoidPedidoOld);
            }
            if (pedidoidPedidoNew != null && !pedidoidPedidoNew.equals(pedidoidPedidoOld)) {
                pedidoidPedidoNew.getFacturasCollection().add(facturas);
                pedidoidPedidoNew = em.merge(pedidoidPedidoNew);
            }
            for (DescripcionFactura descripcionFacturaCollectionNewDescripcionFactura : descripcionFacturaCollectionNew) {
                if (!descripcionFacturaCollectionOld.contains(descripcionFacturaCollectionNewDescripcionFactura)) {
                    Facturas oldFacturasidFacturasOfDescripcionFacturaCollectionNewDescripcionFactura = descripcionFacturaCollectionNewDescripcionFactura.getFacturasidFacturas();
                    descripcionFacturaCollectionNewDescripcionFactura.setFacturasidFacturas(facturas);
                    descripcionFacturaCollectionNewDescripcionFactura = em.merge(descripcionFacturaCollectionNewDescripcionFactura);
                    if (oldFacturasidFacturasOfDescripcionFacturaCollectionNewDescripcionFactura != null && !oldFacturasidFacturasOfDescripcionFacturaCollectionNewDescripcionFactura.equals(facturas)) {
                        oldFacturasidFacturasOfDescripcionFacturaCollectionNewDescripcionFactura.getDescripcionFacturaCollection().remove(descripcionFacturaCollectionNewDescripcionFactura);
                        oldFacturasidFacturasOfDescripcionFacturaCollectionNewDescripcionFactura = em.merge(oldFacturasidFacturasOfDescripcionFacturaCollectionNewDescripcionFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturas.getIdFacturas();
                if (findFacturas(id) == null) {
                    throw new NonexistentEntityException("The facturas with id " + id + " no longer exists.");
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
            Facturas facturas;
            try {
                facturas = em.getReference(Facturas.class, id);
                facturas.getIdFacturas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DescripcionFactura> descripcionFacturaCollectionOrphanCheck = facturas.getDescripcionFacturaCollection();
            for (DescripcionFactura descripcionFacturaCollectionOrphanCheckDescripcionFactura : descripcionFacturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Facturas (" + facturas + ") cannot be destroyed since the DescripcionFactura " + descripcionFacturaCollectionOrphanCheckDescripcionFactura + " in its descripcionFacturaCollection field has a non-nullable facturasidFacturas field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Clientes clientesidCliente = facturas.getClientesidCliente();
            if (clientesidCliente != null) {
                clientesidCliente.getFacturasCollection().remove(facturas);
                clientesidCliente = em.merge(clientesidCliente);
            }
            Vendedores vendedoridVendedor = facturas.getVendedoridVendedor();
            if (vendedoridVendedor != null) {
                vendedoridVendedor.getFacturasCollection().remove(facturas);
                vendedoridVendedor = em.merge(vendedoridVendedor);
            }
            Pedido pedidoidPedido = facturas.getPedidoidPedido();
            if (pedidoidPedido != null) {
                pedidoidPedido.getFacturasCollection().remove(facturas);
                pedidoidPedido = em.merge(pedidoidPedido);
            }
            em.remove(facturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facturas> findFacturasEntities() {
        return findFacturasEntities(true, -1, -1);
    }

    public List<Facturas> findFacturasEntities(int maxResults, int firstResult) {
        return findFacturasEntities(false, maxResults, firstResult);
    }

    private List<Facturas> findFacturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facturas.class));
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

    public Facturas findFacturas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facturas.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facturas> rt = cq.from(Facturas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
