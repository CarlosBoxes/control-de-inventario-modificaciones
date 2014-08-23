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
import EntidadesJPA.TipoVendedores;
import EntidadesJPA.Pedido;
import java.util.ArrayList;
import java.util.Collection;
import EntidadesJPA.ListaClientes;
import EntidadesJPA.ProductosDefectuoso;
import EntidadesJPA.Facturas;
import EntidadesJPA.Vendedores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class VendedoresJpaController implements Serializable {

    public VendedoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vendedores vendedores) {
        if (vendedores.getPedidoCollection() == null) {
            vendedores.setPedidoCollection(new ArrayList<Pedido>());
        }
        if (vendedores.getListaclientesCollection() == null) {
            vendedores.setListaclientesCollection(new ArrayList<ListaClientes>());
        }
        if (vendedores.getProductosDefectuosoCollection() == null) {
            vendedores.setProductosDefectuosoCollection(new ArrayList<ProductosDefectuoso>());
        }
        if (vendedores.getFacturasCollection() == null) {
            vendedores.setFacturasCollection(new ArrayList<Facturas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoVendedores tipoVendedoresidTipoVendedores = vendedores.getTipoVendedoresidTipoVendedores();
            if (tipoVendedoresidTipoVendedores != null) {
                tipoVendedoresidTipoVendedores = em.getReference(tipoVendedoresidTipoVendedores.getClass(), tipoVendedoresidTipoVendedores.getIdTipoVendedores());
                vendedores.setTipoVendedoresidTipoVendedores(tipoVendedoresidTipoVendedores);
            }
            Collection<Pedido> attachedPedidoCollection = new ArrayList<Pedido>();
            for (Pedido pedidoCollectionPedidoToAttach : vendedores.getPedidoCollection()) {
                pedidoCollectionPedidoToAttach = em.getReference(pedidoCollectionPedidoToAttach.getClass(), pedidoCollectionPedidoToAttach.getIdpedido());
                attachedPedidoCollection.add(pedidoCollectionPedidoToAttach);
            }
            vendedores.setPedidoCollection(attachedPedidoCollection);
            Collection<ListaClientes> attachedListaclientesCollection = new ArrayList<ListaClientes>();
            for (ListaClientes listaclientesCollectionListaClientesToAttach : vendedores.getListaclientesCollection()) {
                listaclientesCollectionListaClientesToAttach = em.getReference(listaclientesCollectionListaClientesToAttach.getClass(), listaclientesCollectionListaClientesToAttach.getIdlistaclientes());
                attachedListaclientesCollection.add(listaclientesCollectionListaClientesToAttach);
            }
            vendedores.setListaclientesCollection(attachedListaclientesCollection);
            Collection<ProductosDefectuoso> attachedProductosDefectuosoCollection = new ArrayList<ProductosDefectuoso>();
            for (ProductosDefectuoso productosDefectuosoCollectionProductosDefectuosoToAttach : vendedores.getProductosDefectuosoCollection()) {
                productosDefectuosoCollectionProductosDefectuosoToAttach = em.getReference(productosDefectuosoCollectionProductosDefectuosoToAttach.getClass(), productosDefectuosoCollectionProductosDefectuosoToAttach.getIdProductoDefectuoso());
                attachedProductosDefectuosoCollection.add(productosDefectuosoCollectionProductosDefectuosoToAttach);
            }
            vendedores.setProductosDefectuosoCollection(attachedProductosDefectuosoCollection);
            Collection<Facturas> attachedFacturasCollection = new ArrayList<Facturas>();
            for (Facturas facturasCollectionFacturasToAttach : vendedores.getFacturasCollection()) {
                facturasCollectionFacturasToAttach = em.getReference(facturasCollectionFacturasToAttach.getClass(), facturasCollectionFacturasToAttach.getIdFacturas());
                attachedFacturasCollection.add(facturasCollectionFacturasToAttach);
            }
            vendedores.setFacturasCollection(attachedFacturasCollection);
            em.persist(vendedores);
            if (tipoVendedoresidTipoVendedores != null) {
                tipoVendedoresidTipoVendedores.getVendedoresCollection().add(vendedores);
                tipoVendedoresidTipoVendedores = em.merge(tipoVendedoresidTipoVendedores);
            }
            for (Pedido pedidoCollectionPedido : vendedores.getPedidoCollection()) {
                Vendedores oldVendedoresIdvendedoresOfPedidoCollectionPedido = pedidoCollectionPedido.getVendedoresIdvendedores();
                pedidoCollectionPedido.setVendedoresIdvendedores(vendedores);
                pedidoCollectionPedido = em.merge(pedidoCollectionPedido);
                if (oldVendedoresIdvendedoresOfPedidoCollectionPedido != null) {
                    oldVendedoresIdvendedoresOfPedidoCollectionPedido.getPedidoCollection().remove(pedidoCollectionPedido);
                    oldVendedoresIdvendedoresOfPedidoCollectionPedido = em.merge(oldVendedoresIdvendedoresOfPedidoCollectionPedido);
                }
            }
            for (ListaClientes listaclientesCollectionListaClientes : vendedores.getListaclientesCollection()) {
                Vendedores oldVendedoresIdvendedoresOfListaclientesCollectionListaClientes = listaclientesCollectionListaClientes.getVendedoresIdvendedores();
                listaclientesCollectionListaClientes.setVendedoresIdvendedores(vendedores);
                listaclientesCollectionListaClientes = em.merge(listaclientesCollectionListaClientes);
                if (oldVendedoresIdvendedoresOfListaclientesCollectionListaClientes != null) {
                    oldVendedoresIdvendedoresOfListaclientesCollectionListaClientes.getListaclientesCollection().remove(listaclientesCollectionListaClientes);
                    oldVendedoresIdvendedoresOfListaclientesCollectionListaClientes = em.merge(oldVendedoresIdvendedoresOfListaclientesCollectionListaClientes);
                }
            }
            for (ProductosDefectuoso productosDefectuosoCollectionProductosDefectuoso : vendedores.getProductosDefectuosoCollection()) {
                Vendedores oldVendedoresIdvendedoresOfProductosDefectuosoCollectionProductosDefectuoso = productosDefectuosoCollectionProductosDefectuoso.getVendedoresIdvendedores();
                productosDefectuosoCollectionProductosDefectuoso.setVendedoresIdvendedores(vendedores);
                productosDefectuosoCollectionProductosDefectuoso = em.merge(productosDefectuosoCollectionProductosDefectuoso);
                if (oldVendedoresIdvendedoresOfProductosDefectuosoCollectionProductosDefectuoso != null) {
                    oldVendedoresIdvendedoresOfProductosDefectuosoCollectionProductosDefectuoso.getProductosDefectuosoCollection().remove(productosDefectuosoCollectionProductosDefectuoso);
                    oldVendedoresIdvendedoresOfProductosDefectuosoCollectionProductosDefectuoso = em.merge(oldVendedoresIdvendedoresOfProductosDefectuosoCollectionProductosDefectuoso);
                }
            }
            for (Facturas facturasCollectionFacturas : vendedores.getFacturasCollection()) {
                Vendedores oldVendedoridVendedorOfFacturasCollectionFacturas = facturasCollectionFacturas.getVendedoridVendedor();
                facturasCollectionFacturas.setVendedoridVendedor(vendedores);
                facturasCollectionFacturas = em.merge(facturasCollectionFacturas);
                if (oldVendedoridVendedorOfFacturasCollectionFacturas != null) {
                    oldVendedoridVendedorOfFacturasCollectionFacturas.getFacturasCollection().remove(facturasCollectionFacturas);
                    oldVendedoridVendedorOfFacturasCollectionFacturas = em.merge(oldVendedoridVendedorOfFacturasCollectionFacturas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vendedores vendedores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vendedores persistentVendedores = em.find(Vendedores.class, vendedores.getIdvendedores());
            TipoVendedores tipoVendedoresidTipoVendedoresOld = persistentVendedores.getTipoVendedoresidTipoVendedores();
            TipoVendedores tipoVendedoresidTipoVendedoresNew = vendedores.getTipoVendedoresidTipoVendedores();
            Collection<Pedido> pedidoCollectionOld = persistentVendedores.getPedidoCollection();
            Collection<Pedido> pedidoCollectionNew = vendedores.getPedidoCollection();
            Collection<ListaClientes> listaclientesCollectionOld = persistentVendedores.getListaclientesCollection();
            Collection<ListaClientes> listaclientesCollectionNew = vendedores.getListaclientesCollection();
            Collection<ProductosDefectuoso> productosDefectuosoCollectionOld = persistentVendedores.getProductosDefectuosoCollection();
            Collection<ProductosDefectuoso> productosDefectuosoCollectionNew = vendedores.getProductosDefectuosoCollection();
            Collection<Facturas> facturasCollectionOld = persistentVendedores.getFacturasCollection();
            Collection<Facturas> facturasCollectionNew = vendedores.getFacturasCollection();
            List<String> illegalOrphanMessages = null;
            for (Pedido pedidoCollectionOldPedido : pedidoCollectionOld) {
                if (!pedidoCollectionNew.contains(pedidoCollectionOldPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedido " + pedidoCollectionOldPedido + " since its vendedoresIdvendedores field is not nullable.");
                }
            }
            for (ListaClientes listaclientesCollectionOldListaClientes : listaclientesCollectionOld) {
                if (!listaclientesCollectionNew.contains(listaclientesCollectionOldListaClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ListaClientes " + listaclientesCollectionOldListaClientes + " since its vendedoresIdvendedores field is not nullable.");
                }
            }
            for (ProductosDefectuoso productosDefectuosoCollectionOldProductosDefectuoso : productosDefectuosoCollectionOld) {
                if (!productosDefectuosoCollectionNew.contains(productosDefectuosoCollectionOldProductosDefectuoso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductosDefectuoso " + productosDefectuosoCollectionOldProductosDefectuoso + " since its vendedoresIdvendedores field is not nullable.");
                }
            }
            for (Facturas facturasCollectionOldFacturas : facturasCollectionOld) {
                if (!facturasCollectionNew.contains(facturasCollectionOldFacturas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facturas " + facturasCollectionOldFacturas + " since its vendedoridVendedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tipoVendedoresidTipoVendedoresNew != null) {
                tipoVendedoresidTipoVendedoresNew = em.getReference(tipoVendedoresidTipoVendedoresNew.getClass(), tipoVendedoresidTipoVendedoresNew.getIdTipoVendedores());
                vendedores.setTipoVendedoresidTipoVendedores(tipoVendedoresidTipoVendedoresNew);
            }
            Collection<Pedido> attachedPedidoCollectionNew = new ArrayList<Pedido>();
            for (Pedido pedidoCollectionNewPedidoToAttach : pedidoCollectionNew) {
                pedidoCollectionNewPedidoToAttach = em.getReference(pedidoCollectionNewPedidoToAttach.getClass(), pedidoCollectionNewPedidoToAttach.getIdpedido());
                attachedPedidoCollectionNew.add(pedidoCollectionNewPedidoToAttach);
            }
            pedidoCollectionNew = attachedPedidoCollectionNew;
            vendedores.setPedidoCollection(pedidoCollectionNew);
            Collection<ListaClientes> attachedListaclientesCollectionNew = new ArrayList<ListaClientes>();
            for (ListaClientes listaclientesCollectionNewListaClientesToAttach : listaclientesCollectionNew) {
                listaclientesCollectionNewListaClientesToAttach = em.getReference(listaclientesCollectionNewListaClientesToAttach.getClass(), listaclientesCollectionNewListaClientesToAttach.getIdlistaclientes());
                attachedListaclientesCollectionNew.add(listaclientesCollectionNewListaClientesToAttach);
            }
            listaclientesCollectionNew = attachedListaclientesCollectionNew;
            vendedores.setListaclientesCollection(listaclientesCollectionNew);
            Collection<ProductosDefectuoso> attachedProductosDefectuosoCollectionNew = new ArrayList<ProductosDefectuoso>();
            for (ProductosDefectuoso productosDefectuosoCollectionNewProductosDefectuosoToAttach : productosDefectuosoCollectionNew) {
                productosDefectuosoCollectionNewProductosDefectuosoToAttach = em.getReference(productosDefectuosoCollectionNewProductosDefectuosoToAttach.getClass(), productosDefectuosoCollectionNewProductosDefectuosoToAttach.getIdProductoDefectuoso());
                attachedProductosDefectuosoCollectionNew.add(productosDefectuosoCollectionNewProductosDefectuosoToAttach);
            }
            productosDefectuosoCollectionNew = attachedProductosDefectuosoCollectionNew;
            vendedores.setProductosDefectuosoCollection(productosDefectuosoCollectionNew);
            Collection<Facturas> attachedFacturasCollectionNew = new ArrayList<Facturas>();
            for (Facturas facturasCollectionNewFacturasToAttach : facturasCollectionNew) {
                facturasCollectionNewFacturasToAttach = em.getReference(facturasCollectionNewFacturasToAttach.getClass(), facturasCollectionNewFacturasToAttach.getIdFacturas());
                attachedFacturasCollectionNew.add(facturasCollectionNewFacturasToAttach);
            }
            facturasCollectionNew = attachedFacturasCollectionNew;
            vendedores.setFacturasCollection(facturasCollectionNew);
            vendedores = em.merge(vendedores);
            if (tipoVendedoresidTipoVendedoresOld != null && !tipoVendedoresidTipoVendedoresOld.equals(tipoVendedoresidTipoVendedoresNew)) {
                tipoVendedoresidTipoVendedoresOld.getVendedoresCollection().remove(vendedores);
                tipoVendedoresidTipoVendedoresOld = em.merge(tipoVendedoresidTipoVendedoresOld);
            }
            if (tipoVendedoresidTipoVendedoresNew != null && !tipoVendedoresidTipoVendedoresNew.equals(tipoVendedoresidTipoVendedoresOld)) {
                tipoVendedoresidTipoVendedoresNew.getVendedoresCollection().add(vendedores);
                tipoVendedoresidTipoVendedoresNew = em.merge(tipoVendedoresidTipoVendedoresNew);
            }
            for (Pedido pedidoCollectionNewPedido : pedidoCollectionNew) {
                if (!pedidoCollectionOld.contains(pedidoCollectionNewPedido)) {
                    Vendedores oldVendedoresIdvendedoresOfPedidoCollectionNewPedido = pedidoCollectionNewPedido.getVendedoresIdvendedores();
                    pedidoCollectionNewPedido.setVendedoresIdvendedores(vendedores);
                    pedidoCollectionNewPedido = em.merge(pedidoCollectionNewPedido);
                    if (oldVendedoresIdvendedoresOfPedidoCollectionNewPedido != null && !oldVendedoresIdvendedoresOfPedidoCollectionNewPedido.equals(vendedores)) {
                        oldVendedoresIdvendedoresOfPedidoCollectionNewPedido.getPedidoCollection().remove(pedidoCollectionNewPedido);
                        oldVendedoresIdvendedoresOfPedidoCollectionNewPedido = em.merge(oldVendedoresIdvendedoresOfPedidoCollectionNewPedido);
                    }
                }
            }
            for (ListaClientes listaclientesCollectionNewListaClientes : listaclientesCollectionNew) {
                if (!listaclientesCollectionOld.contains(listaclientesCollectionNewListaClientes)) {
                    Vendedores oldVendedoresIdvendedoresOfListaclientesCollectionNewListaClientes = listaclientesCollectionNewListaClientes.getVendedoresIdvendedores();
                    listaclientesCollectionNewListaClientes.setVendedoresIdvendedores(vendedores);
                    listaclientesCollectionNewListaClientes = em.merge(listaclientesCollectionNewListaClientes);
                    if (oldVendedoresIdvendedoresOfListaclientesCollectionNewListaClientes != null && !oldVendedoresIdvendedoresOfListaclientesCollectionNewListaClientes.equals(vendedores)) {
                        oldVendedoresIdvendedoresOfListaclientesCollectionNewListaClientes.getListaclientesCollection().remove(listaclientesCollectionNewListaClientes);
                        oldVendedoresIdvendedoresOfListaclientesCollectionNewListaClientes = em.merge(oldVendedoresIdvendedoresOfListaclientesCollectionNewListaClientes);
                    }
                }
            }
            for (ProductosDefectuoso productosDefectuosoCollectionNewProductosDefectuoso : productosDefectuosoCollectionNew) {
                if (!productosDefectuosoCollectionOld.contains(productosDefectuosoCollectionNewProductosDefectuoso)) {
                    Vendedores oldVendedoresIdvendedoresOfProductosDefectuosoCollectionNewProductosDefectuoso = productosDefectuosoCollectionNewProductosDefectuoso.getVendedoresIdvendedores();
                    productosDefectuosoCollectionNewProductosDefectuoso.setVendedoresIdvendedores(vendedores);
                    productosDefectuosoCollectionNewProductosDefectuoso = em.merge(productosDefectuosoCollectionNewProductosDefectuoso);
                    if (oldVendedoresIdvendedoresOfProductosDefectuosoCollectionNewProductosDefectuoso != null && !oldVendedoresIdvendedoresOfProductosDefectuosoCollectionNewProductosDefectuoso.equals(vendedores)) {
                        oldVendedoresIdvendedoresOfProductosDefectuosoCollectionNewProductosDefectuoso.getProductosDefectuosoCollection().remove(productosDefectuosoCollectionNewProductosDefectuoso);
                        oldVendedoresIdvendedoresOfProductosDefectuosoCollectionNewProductosDefectuoso = em.merge(oldVendedoresIdvendedoresOfProductosDefectuosoCollectionNewProductosDefectuoso);
                    }
                }
            }
            for (Facturas facturasCollectionNewFacturas : facturasCollectionNew) {
                if (!facturasCollectionOld.contains(facturasCollectionNewFacturas)) {
                    Vendedores oldVendedoridVendedorOfFacturasCollectionNewFacturas = facturasCollectionNewFacturas.getVendedoridVendedor();
                    facturasCollectionNewFacturas.setVendedoridVendedor(vendedores);
                    facturasCollectionNewFacturas = em.merge(facturasCollectionNewFacturas);
                    if (oldVendedoridVendedorOfFacturasCollectionNewFacturas != null && !oldVendedoridVendedorOfFacturasCollectionNewFacturas.equals(vendedores)) {
                        oldVendedoridVendedorOfFacturasCollectionNewFacturas.getFacturasCollection().remove(facturasCollectionNewFacturas);
                        oldVendedoridVendedorOfFacturasCollectionNewFacturas = em.merge(oldVendedoridVendedorOfFacturasCollectionNewFacturas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vendedores.getIdvendedores();
                if (findVendedores(id) == null) {
                    throw new NonexistentEntityException("The vendedores with id " + id + " no longer exists.");
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
            Vendedores vendedores;
            try {
                vendedores = em.getReference(Vendedores.class, id);
                vendedores.getIdvendedores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vendedores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Pedido> pedidoCollectionOrphanCheck = vendedores.getPedidoCollection();
            for (Pedido pedidoCollectionOrphanCheckPedido : pedidoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vendedores (" + vendedores + ") cannot be destroyed since the Pedido " + pedidoCollectionOrphanCheckPedido + " in its pedidoCollection field has a non-nullable vendedoresIdvendedores field.");
            }
            Collection<ListaClientes> listaclientesCollectionOrphanCheck = vendedores.getListaclientesCollection();
            for (ListaClientes listaclientesCollectionOrphanCheckListaClientes : listaclientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vendedores (" + vendedores + ") cannot be destroyed since the ListaClientes " + listaclientesCollectionOrphanCheckListaClientes + " in its listaclientesCollection field has a non-nullable vendedoresIdvendedores field.");
            }
            Collection<ProductosDefectuoso> productosDefectuosoCollectionOrphanCheck = vendedores.getProductosDefectuosoCollection();
            for (ProductosDefectuoso productosDefectuosoCollectionOrphanCheckProductosDefectuoso : productosDefectuosoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vendedores (" + vendedores + ") cannot be destroyed since the ProductosDefectuoso " + productosDefectuosoCollectionOrphanCheckProductosDefectuoso + " in its productosDefectuosoCollection field has a non-nullable vendedoresIdvendedores field.");
            }
            Collection<Facturas> facturasCollectionOrphanCheck = vendedores.getFacturasCollection();
            for (Facturas facturasCollectionOrphanCheckFacturas : facturasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vendedores (" + vendedores + ") cannot be destroyed since the Facturas " + facturasCollectionOrphanCheckFacturas + " in its facturasCollection field has a non-nullable vendedoridVendedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoVendedores tipoVendedoresidTipoVendedores = vendedores.getTipoVendedoresidTipoVendedores();
            if (tipoVendedoresidTipoVendedores != null) {
                tipoVendedoresidTipoVendedores.getVendedoresCollection().remove(vendedores);
                tipoVendedoresidTipoVendedores = em.merge(tipoVendedoresidTipoVendedores);
            }
            em.remove(vendedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vendedores> findVendedoresEntities() {
        return findVendedoresEntities(true, -1, -1);
    }

    public List<Vendedores> findVendedoresEntities(int maxResults, int firstResult) {
        return findVendedoresEntities(false, maxResults, firstResult);
    }

    private List<Vendedores> findVendedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vendedores.class));
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

    public Vendedores findVendedores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vendedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vendedores> rt = cq.from(Vendedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
