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
import EntidadesJPA.Vendedores;
import EntidadesJPA.DescripcionPedido;
import java.util.ArrayList;
import java.util.Collection;
import EntidadesJPA.DescripcionClientes;
import EntidadesJPA.Facturas;
import EntidadesJPA.Liquidacion;
import EntidadesJPA.Pedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class PedidoJpaController implements Serializable {

    public PedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedido pedido) {
        if (pedido.getDescripcionPedidoCollection() == null) {
            pedido.setDescripcionPedidoCollection(new ArrayList<DescripcionPedido>());
        }
        if (pedido.getDescripcionClientesCollection() == null) {
            pedido.setDescripcionClientesCollection(new ArrayList<DescripcionClientes>());
        }
        if (pedido.getFacturasCollection() == null) {
            pedido.setFacturasCollection(new ArrayList<Facturas>());
        }
        if (pedido.getLiquidacionCollection() == null) {
            pedido.setLiquidacionCollection(new ArrayList<Liquidacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vendedores vendedoresIdvendedores = pedido.getVendedoresIdvendedores();
            if (vendedoresIdvendedores != null) {
                vendedoresIdvendedores = em.getReference(vendedoresIdvendedores.getClass(), vendedoresIdvendedores.getIdvendedores());
                pedido.setVendedoresIdvendedores(vendedoresIdvendedores);
            }
            Collection<DescripcionPedido> attachedDescripcionPedidoCollection = new ArrayList<DescripcionPedido>();
            for (DescripcionPedido descripcionPedidoCollectionDescripcionPedidoToAttach : pedido.getDescripcionPedidoCollection()) {
                descripcionPedidoCollectionDescripcionPedidoToAttach = em.getReference(descripcionPedidoCollectionDescripcionPedidoToAttach.getClass(), descripcionPedidoCollectionDescripcionPedidoToAttach.getIddescripcionPedido());
                attachedDescripcionPedidoCollection.add(descripcionPedidoCollectionDescripcionPedidoToAttach);
            }
            pedido.setDescripcionPedidoCollection(attachedDescripcionPedidoCollection);
            Collection<DescripcionClientes> attachedDescripcionClientesCollection = new ArrayList<DescripcionClientes>();
            for (DescripcionClientes descripcionClientesCollectionDescripcionClientesToAttach : pedido.getDescripcionClientesCollection()) {
                descripcionClientesCollectionDescripcionClientesToAttach = em.getReference(descripcionClientesCollectionDescripcionClientesToAttach.getClass(), descripcionClientesCollectionDescripcionClientesToAttach.getIddescripcionclientes());
                attachedDescripcionClientesCollection.add(descripcionClientesCollectionDescripcionClientesToAttach);
            }
            pedido.setDescripcionClientesCollection(attachedDescripcionClientesCollection);
            Collection<Facturas> attachedFacturasCollection = new ArrayList<Facturas>();
            for (Facturas facturasCollectionFacturasToAttach : pedido.getFacturasCollection()) {
                facturasCollectionFacturasToAttach = em.getReference(facturasCollectionFacturasToAttach.getClass(), facturasCollectionFacturasToAttach.getIdFacturas());
                attachedFacturasCollection.add(facturasCollectionFacturasToAttach);
            }
            pedido.setFacturasCollection(attachedFacturasCollection);
            Collection<Liquidacion> attachedLiquidacionCollection = new ArrayList<Liquidacion>();
            for (Liquidacion liquidacionCollectionLiquidacionToAttach : pedido.getLiquidacionCollection()) {
                liquidacionCollectionLiquidacionToAttach = em.getReference(liquidacionCollectionLiquidacionToAttach.getClass(), liquidacionCollectionLiquidacionToAttach.getIdliquidacion());
                attachedLiquidacionCollection.add(liquidacionCollectionLiquidacionToAttach);
            }
            pedido.setLiquidacionCollection(attachedLiquidacionCollection);
            em.persist(pedido);
            if (vendedoresIdvendedores != null) {
                vendedoresIdvendedores.getPedidoCollection().add(pedido);
                vendedoresIdvendedores = em.merge(vendedoresIdvendedores);
            }
            for (DescripcionPedido descripcionPedidoCollectionDescripcionPedido : pedido.getDescripcionPedidoCollection()) {
                Pedido oldPedidoIdpedidoOfDescripcionPedidoCollectionDescripcionPedido = descripcionPedidoCollectionDescripcionPedido.getPedidoIdpedido();
                descripcionPedidoCollectionDescripcionPedido.setPedidoIdpedido(pedido);
                descripcionPedidoCollectionDescripcionPedido = em.merge(descripcionPedidoCollectionDescripcionPedido);
                if (oldPedidoIdpedidoOfDescripcionPedidoCollectionDescripcionPedido != null) {
                    oldPedidoIdpedidoOfDescripcionPedidoCollectionDescripcionPedido.getDescripcionPedidoCollection().remove(descripcionPedidoCollectionDescripcionPedido);
                    oldPedidoIdpedidoOfDescripcionPedidoCollectionDescripcionPedido = em.merge(oldPedidoIdpedidoOfDescripcionPedidoCollectionDescripcionPedido);
                }
            }
            for (DescripcionClientes descripcionClientesCollectionDescripcionClientes : pedido.getDescripcionClientesCollection()) {
                Pedido oldPedidoIdpedidoOfDescripcionClientesCollectionDescripcionClientes = descripcionClientesCollectionDescripcionClientes.getPedidoIdpedido();
                descripcionClientesCollectionDescripcionClientes.setPedidoIdpedido(pedido);
                descripcionClientesCollectionDescripcionClientes = em.merge(descripcionClientesCollectionDescripcionClientes);
                if (oldPedidoIdpedidoOfDescripcionClientesCollectionDescripcionClientes != null) {
                    oldPedidoIdpedidoOfDescripcionClientesCollectionDescripcionClientes.getDescripcionClientesCollection().remove(descripcionClientesCollectionDescripcionClientes);
                    oldPedidoIdpedidoOfDescripcionClientesCollectionDescripcionClientes = em.merge(oldPedidoIdpedidoOfDescripcionClientesCollectionDescripcionClientes);
                }
            }
            for (Facturas facturasCollectionFacturas : pedido.getFacturasCollection()) {
                Pedido oldPedidoidPedidoOfFacturasCollectionFacturas = facturasCollectionFacturas.getPedidoidPedido();
                facturasCollectionFacturas.setPedidoidPedido(pedido);
                facturasCollectionFacturas = em.merge(facturasCollectionFacturas);
                if (oldPedidoidPedidoOfFacturasCollectionFacturas != null) {
                    oldPedidoidPedidoOfFacturasCollectionFacturas.getFacturasCollection().remove(facturasCollectionFacturas);
                    oldPedidoidPedidoOfFacturasCollectionFacturas = em.merge(oldPedidoidPedidoOfFacturasCollectionFacturas);
                }
            }
            for (Liquidacion liquidacionCollectionLiquidacion : pedido.getLiquidacionCollection()) {
                Pedido oldPedidoIdpedidoOfLiquidacionCollectionLiquidacion = liquidacionCollectionLiquidacion.getPedidoIdpedido();
                liquidacionCollectionLiquidacion.setPedidoIdpedido(pedido);
                liquidacionCollectionLiquidacion = em.merge(liquidacionCollectionLiquidacion);
                if (oldPedidoIdpedidoOfLiquidacionCollectionLiquidacion != null) {
                    oldPedidoIdpedidoOfLiquidacionCollectionLiquidacion.getLiquidacionCollection().remove(liquidacionCollectionLiquidacion);
                    oldPedidoIdpedidoOfLiquidacionCollectionLiquidacion = em.merge(oldPedidoIdpedidoOfLiquidacionCollectionLiquidacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getIdpedido());
            Vendedores vendedoresIdvendedoresOld = persistentPedido.getVendedoresIdvendedores();
            Vendedores vendedoresIdvendedoresNew = pedido.getVendedoresIdvendedores();
            Collection<DescripcionPedido> descripcionPedidoCollectionOld = persistentPedido.getDescripcionPedidoCollection();
            Collection<DescripcionPedido> descripcionPedidoCollectionNew = pedido.getDescripcionPedidoCollection();
            Collection<DescripcionClientes> descripcionClientesCollectionOld = persistentPedido.getDescripcionClientesCollection();
            Collection<DescripcionClientes> descripcionClientesCollectionNew = pedido.getDescripcionClientesCollection();
            Collection<Facturas> facturasCollectionOld = persistentPedido.getFacturasCollection();
            Collection<Facturas> facturasCollectionNew = pedido.getFacturasCollection();
            Collection<Liquidacion> liquidacionCollectionOld = persistentPedido.getLiquidacionCollection();
            Collection<Liquidacion> liquidacionCollectionNew = pedido.getLiquidacionCollection();
            List<String> illegalOrphanMessages = null;
            for (DescripcionPedido descripcionPedidoCollectionOldDescripcionPedido : descripcionPedidoCollectionOld) {
                if (!descripcionPedidoCollectionNew.contains(descripcionPedidoCollectionOldDescripcionPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DescripcionPedido " + descripcionPedidoCollectionOldDescripcionPedido + " since its pedidoIdpedido field is not nullable.");
                }
            }
            for (DescripcionClientes descripcionClientesCollectionOldDescripcionClientes : descripcionClientesCollectionOld) {
                if (!descripcionClientesCollectionNew.contains(descripcionClientesCollectionOldDescripcionClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DescripcionClientes " + descripcionClientesCollectionOldDescripcionClientes + " since its pedidoIdpedido field is not nullable.");
                }
            }
            for (Facturas facturasCollectionOldFacturas : facturasCollectionOld) {
                if (!facturasCollectionNew.contains(facturasCollectionOldFacturas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facturas " + facturasCollectionOldFacturas + " since its pedidoidPedido field is not nullable.");
                }
            }
            for (Liquidacion liquidacionCollectionOldLiquidacion : liquidacionCollectionOld) {
                if (!liquidacionCollectionNew.contains(liquidacionCollectionOldLiquidacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Liquidacion " + liquidacionCollectionOldLiquidacion + " since its pedidoIdpedido field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (vendedoresIdvendedoresNew != null) {
                vendedoresIdvendedoresNew = em.getReference(vendedoresIdvendedoresNew.getClass(), vendedoresIdvendedoresNew.getIdvendedores());
                pedido.setVendedoresIdvendedores(vendedoresIdvendedoresNew);
            }
            Collection<DescripcionPedido> attachedDescripcionPedidoCollectionNew = new ArrayList<DescripcionPedido>();
            for (DescripcionPedido descripcionPedidoCollectionNewDescripcionPedidoToAttach : descripcionPedidoCollectionNew) {
                descripcionPedidoCollectionNewDescripcionPedidoToAttach = em.getReference(descripcionPedidoCollectionNewDescripcionPedidoToAttach.getClass(), descripcionPedidoCollectionNewDescripcionPedidoToAttach.getIddescripcionPedido());
                attachedDescripcionPedidoCollectionNew.add(descripcionPedidoCollectionNewDescripcionPedidoToAttach);
            }
            descripcionPedidoCollectionNew = attachedDescripcionPedidoCollectionNew;
            pedido.setDescripcionPedidoCollection(descripcionPedidoCollectionNew);
            Collection<DescripcionClientes> attachedDescripcionClientesCollectionNew = new ArrayList<DescripcionClientes>();
            for (DescripcionClientes descripcionClientesCollectionNewDescripcionClientesToAttach : descripcionClientesCollectionNew) {
                descripcionClientesCollectionNewDescripcionClientesToAttach = em.getReference(descripcionClientesCollectionNewDescripcionClientesToAttach.getClass(), descripcionClientesCollectionNewDescripcionClientesToAttach.getIddescripcionclientes());
                attachedDescripcionClientesCollectionNew.add(descripcionClientesCollectionNewDescripcionClientesToAttach);
            }
            descripcionClientesCollectionNew = attachedDescripcionClientesCollectionNew;
            pedido.setDescripcionClientesCollection(descripcionClientesCollectionNew);
            Collection<Facturas> attachedFacturasCollectionNew = new ArrayList<Facturas>();
            for (Facturas facturasCollectionNewFacturasToAttach : facturasCollectionNew) {
                facturasCollectionNewFacturasToAttach = em.getReference(facturasCollectionNewFacturasToAttach.getClass(), facturasCollectionNewFacturasToAttach.getIdFacturas());
                attachedFacturasCollectionNew.add(facturasCollectionNewFacturasToAttach);
            }
            facturasCollectionNew = attachedFacturasCollectionNew;
            pedido.setFacturasCollection(facturasCollectionNew);
            Collection<Liquidacion> attachedLiquidacionCollectionNew = new ArrayList<Liquidacion>();
            for (Liquidacion liquidacionCollectionNewLiquidacionToAttach : liquidacionCollectionNew) {
                liquidacionCollectionNewLiquidacionToAttach = em.getReference(liquidacionCollectionNewLiquidacionToAttach.getClass(), liquidacionCollectionNewLiquidacionToAttach.getIdliquidacion());
                attachedLiquidacionCollectionNew.add(liquidacionCollectionNewLiquidacionToAttach);
            }
            liquidacionCollectionNew = attachedLiquidacionCollectionNew;
            pedido.setLiquidacionCollection(liquidacionCollectionNew);
            pedido = em.merge(pedido);
            if (vendedoresIdvendedoresOld != null && !vendedoresIdvendedoresOld.equals(vendedoresIdvendedoresNew)) {
                vendedoresIdvendedoresOld.getPedidoCollection().remove(pedido);
                vendedoresIdvendedoresOld = em.merge(vendedoresIdvendedoresOld);
            }
            if (vendedoresIdvendedoresNew != null && !vendedoresIdvendedoresNew.equals(vendedoresIdvendedoresOld)) {
                vendedoresIdvendedoresNew.getPedidoCollection().add(pedido);
                vendedoresIdvendedoresNew = em.merge(vendedoresIdvendedoresNew);
            }
            for (DescripcionPedido descripcionPedidoCollectionNewDescripcionPedido : descripcionPedidoCollectionNew) {
                if (!descripcionPedidoCollectionOld.contains(descripcionPedidoCollectionNewDescripcionPedido)) {
                    Pedido oldPedidoIdpedidoOfDescripcionPedidoCollectionNewDescripcionPedido = descripcionPedidoCollectionNewDescripcionPedido.getPedidoIdpedido();
                    descripcionPedidoCollectionNewDescripcionPedido.setPedidoIdpedido(pedido);
                    descripcionPedidoCollectionNewDescripcionPedido = em.merge(descripcionPedidoCollectionNewDescripcionPedido);
                    if (oldPedidoIdpedidoOfDescripcionPedidoCollectionNewDescripcionPedido != null && !oldPedidoIdpedidoOfDescripcionPedidoCollectionNewDescripcionPedido.equals(pedido)) {
                        oldPedidoIdpedidoOfDescripcionPedidoCollectionNewDescripcionPedido.getDescripcionPedidoCollection().remove(descripcionPedidoCollectionNewDescripcionPedido);
                        oldPedidoIdpedidoOfDescripcionPedidoCollectionNewDescripcionPedido = em.merge(oldPedidoIdpedidoOfDescripcionPedidoCollectionNewDescripcionPedido);
                    }
                }
            }
            for (DescripcionClientes descripcionClientesCollectionNewDescripcionClientes : descripcionClientesCollectionNew) {
                if (!descripcionClientesCollectionOld.contains(descripcionClientesCollectionNewDescripcionClientes)) {
                    Pedido oldPedidoIdpedidoOfDescripcionClientesCollectionNewDescripcionClientes = descripcionClientesCollectionNewDescripcionClientes.getPedidoIdpedido();
                    descripcionClientesCollectionNewDescripcionClientes.setPedidoIdpedido(pedido);
                    descripcionClientesCollectionNewDescripcionClientes = em.merge(descripcionClientesCollectionNewDescripcionClientes);
                    if (oldPedidoIdpedidoOfDescripcionClientesCollectionNewDescripcionClientes != null && !oldPedidoIdpedidoOfDescripcionClientesCollectionNewDescripcionClientes.equals(pedido)) {
                        oldPedidoIdpedidoOfDescripcionClientesCollectionNewDescripcionClientes.getDescripcionClientesCollection().remove(descripcionClientesCollectionNewDescripcionClientes);
                        oldPedidoIdpedidoOfDescripcionClientesCollectionNewDescripcionClientes = em.merge(oldPedidoIdpedidoOfDescripcionClientesCollectionNewDescripcionClientes);
                    }
                }
            }
            for (Facturas facturasCollectionNewFacturas : facturasCollectionNew) {
                if (!facturasCollectionOld.contains(facturasCollectionNewFacturas)) {
                    Pedido oldPedidoidPedidoOfFacturasCollectionNewFacturas = facturasCollectionNewFacturas.getPedidoidPedido();
                    facturasCollectionNewFacturas.setPedidoidPedido(pedido);
                    facturasCollectionNewFacturas = em.merge(facturasCollectionNewFacturas);
                    if (oldPedidoidPedidoOfFacturasCollectionNewFacturas != null && !oldPedidoidPedidoOfFacturasCollectionNewFacturas.equals(pedido)) {
                        oldPedidoidPedidoOfFacturasCollectionNewFacturas.getFacturasCollection().remove(facturasCollectionNewFacturas);
                        oldPedidoidPedidoOfFacturasCollectionNewFacturas = em.merge(oldPedidoidPedidoOfFacturasCollectionNewFacturas);
                    }
                }
            }
            for (Liquidacion liquidacionCollectionNewLiquidacion : liquidacionCollectionNew) {
                if (!liquidacionCollectionOld.contains(liquidacionCollectionNewLiquidacion)) {
                    Pedido oldPedidoIdpedidoOfLiquidacionCollectionNewLiquidacion = liquidacionCollectionNewLiquidacion.getPedidoIdpedido();
                    liquidacionCollectionNewLiquidacion.setPedidoIdpedido(pedido);
                    liquidacionCollectionNewLiquidacion = em.merge(liquidacionCollectionNewLiquidacion);
                    if (oldPedidoIdpedidoOfLiquidacionCollectionNewLiquidacion != null && !oldPedidoIdpedidoOfLiquidacionCollectionNewLiquidacion.equals(pedido)) {
                        oldPedidoIdpedidoOfLiquidacionCollectionNewLiquidacion.getLiquidacionCollection().remove(liquidacionCollectionNewLiquidacion);
                        oldPedidoIdpedidoOfLiquidacionCollectionNewLiquidacion = em.merge(oldPedidoIdpedidoOfLiquidacionCollectionNewLiquidacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedido.getIdpedido();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
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
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getIdpedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DescripcionPedido> descripcionPedidoCollectionOrphanCheck = pedido.getDescripcionPedidoCollection();
            for (DescripcionPedido descripcionPedidoCollectionOrphanCheckDescripcionPedido : descripcionPedidoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedido (" + pedido + ") cannot be destroyed since the DescripcionPedido " + descripcionPedidoCollectionOrphanCheckDescripcionPedido + " in its descripcionPedidoCollection field has a non-nullable pedidoIdpedido field.");
            }
            Collection<DescripcionClientes> descripcionClientesCollectionOrphanCheck = pedido.getDescripcionClientesCollection();
            for (DescripcionClientes descripcionClientesCollectionOrphanCheckDescripcionClientes : descripcionClientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedido (" + pedido + ") cannot be destroyed since the DescripcionClientes " + descripcionClientesCollectionOrphanCheckDescripcionClientes + " in its descripcionClientesCollection field has a non-nullable pedidoIdpedido field.");
            }
            Collection<Facturas> facturasCollectionOrphanCheck = pedido.getFacturasCollection();
            for (Facturas facturasCollectionOrphanCheckFacturas : facturasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedido (" + pedido + ") cannot be destroyed since the Facturas " + facturasCollectionOrphanCheckFacturas + " in its facturasCollection field has a non-nullable pedidoidPedido field.");
            }
            Collection<Liquidacion> liquidacionCollectionOrphanCheck = pedido.getLiquidacionCollection();
            for (Liquidacion liquidacionCollectionOrphanCheckLiquidacion : liquidacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedido (" + pedido + ") cannot be destroyed since the Liquidacion " + liquidacionCollectionOrphanCheckLiquidacion + " in its liquidacionCollection field has a non-nullable pedidoIdpedido field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Vendedores vendedoresIdvendedores = pedido.getVendedoresIdvendedores();
            if (vendedoresIdvendedores != null) {
                vendedoresIdvendedores.getPedidoCollection().remove(pedido);
                vendedoresIdvendedores = em.merge(vendedoresIdvendedores);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
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

    public Pedido findPedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
