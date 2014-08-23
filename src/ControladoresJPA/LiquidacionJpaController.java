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
import EntidadesJPA.Pedido;
import EntidadesJPA.DescripcionLiquidacion;
import EntidadesJPA.Liquidacion;
import java.util.ArrayList;
import java.util.Collection;
import EntidadesJPA.ProductoaCambiar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class LiquidacionJpaController implements Serializable {

    public LiquidacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Liquidacion liquidacion) {
        if (liquidacion.getDescripcionLiquidacionCollection() == null) {
            liquidacion.setDescripcionLiquidacionCollection(new ArrayList<DescripcionLiquidacion>());
        }
        if (liquidacion.getProductoacambiarCollection() == null) {
            liquidacion.setProductoacambiarCollection(new ArrayList<ProductoaCambiar>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedidoIdpedido = liquidacion.getPedidoIdpedido();
            if (pedidoIdpedido != null) {
                pedidoIdpedido = em.getReference(pedidoIdpedido.getClass(), pedidoIdpedido.getIdpedido());
                liquidacion.setPedidoIdpedido(pedidoIdpedido);
            }
            Collection<DescripcionLiquidacion> attachedDescripcionLiquidacionCollection = new ArrayList<DescripcionLiquidacion>();
            for (DescripcionLiquidacion descripcionLiquidacionCollectionDescripcionLiquidacionToAttach : liquidacion.getDescripcionLiquidacionCollection()) {
                descripcionLiquidacionCollectionDescripcionLiquidacionToAttach = em.getReference(descripcionLiquidacionCollectionDescripcionLiquidacionToAttach.getClass(), descripcionLiquidacionCollectionDescripcionLiquidacionToAttach.getIdDescripcionLiquidacion());
                attachedDescripcionLiquidacionCollection.add(descripcionLiquidacionCollectionDescripcionLiquidacionToAttach);
            }
            liquidacion.setDescripcionLiquidacionCollection(attachedDescripcionLiquidacionCollection);
            Collection<ProductoaCambiar> attachedProductoacambiarCollection = new ArrayList<ProductoaCambiar>();
            for (ProductoaCambiar productoacambiarCollectionProductoaCambiarToAttach : liquidacion.getProductoacambiarCollection()) {
                productoacambiarCollectionProductoaCambiarToAttach = em.getReference(productoacambiarCollectionProductoaCambiarToAttach.getClass(), productoacambiarCollectionProductoaCambiarToAttach.getIdproductoacambiar());
                attachedProductoacambiarCollection.add(productoacambiarCollectionProductoaCambiarToAttach);
            }
            liquidacion.setProductoacambiarCollection(attachedProductoacambiarCollection);
            em.persist(liquidacion);
            if (pedidoIdpedido != null) {
                pedidoIdpedido.getLiquidacionCollection().add(liquidacion);
                pedidoIdpedido = em.merge(pedidoIdpedido);
            }
            for (DescripcionLiquidacion descripcionLiquidacionCollectionDescripcionLiquidacion : liquidacion.getDescripcionLiquidacionCollection()) {
                Liquidacion oldLiquidacionIdliquidacionOfDescripcionLiquidacionCollectionDescripcionLiquidacion = descripcionLiquidacionCollectionDescripcionLiquidacion.getLiquidacionIdliquidacion();
                descripcionLiquidacionCollectionDescripcionLiquidacion.setLiquidacionIdliquidacion(liquidacion);
                descripcionLiquidacionCollectionDescripcionLiquidacion = em.merge(descripcionLiquidacionCollectionDescripcionLiquidacion);
                if (oldLiquidacionIdliquidacionOfDescripcionLiquidacionCollectionDescripcionLiquidacion != null) {
                    oldLiquidacionIdliquidacionOfDescripcionLiquidacionCollectionDescripcionLiquidacion.getDescripcionLiquidacionCollection().remove(descripcionLiquidacionCollectionDescripcionLiquidacion);
                    oldLiquidacionIdliquidacionOfDescripcionLiquidacionCollectionDescripcionLiquidacion = em.merge(oldLiquidacionIdliquidacionOfDescripcionLiquidacionCollectionDescripcionLiquidacion);
                }
            }
            for (ProductoaCambiar productoacambiarCollectionProductoaCambiar : liquidacion.getProductoacambiarCollection()) {
                Liquidacion oldLiquidacionIdliquidacionOfProductoacambiarCollectionProductoaCambiar = productoacambiarCollectionProductoaCambiar.getLiquidacionIdliquidacion();
                productoacambiarCollectionProductoaCambiar.setLiquidacionIdliquidacion(liquidacion);
                productoacambiarCollectionProductoaCambiar = em.merge(productoacambiarCollectionProductoaCambiar);
                if (oldLiquidacionIdliquidacionOfProductoacambiarCollectionProductoaCambiar != null) {
                    oldLiquidacionIdliquidacionOfProductoacambiarCollectionProductoaCambiar.getProductoacambiarCollection().remove(productoacambiarCollectionProductoaCambiar);
                    oldLiquidacionIdliquidacionOfProductoacambiarCollectionProductoaCambiar = em.merge(oldLiquidacionIdliquidacionOfProductoacambiarCollectionProductoaCambiar);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Liquidacion liquidacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Liquidacion persistentLiquidacion = em.find(Liquidacion.class, liquidacion.getIdliquidacion());
            Pedido pedidoIdpedidoOld = persistentLiquidacion.getPedidoIdpedido();
            Pedido pedidoIdpedidoNew = liquidacion.getPedidoIdpedido();
            Collection<DescripcionLiquidacion> descripcionLiquidacionCollectionOld = persistentLiquidacion.getDescripcionLiquidacionCollection();
            Collection<DescripcionLiquidacion> descripcionLiquidacionCollectionNew = liquidacion.getDescripcionLiquidacionCollection();
            Collection<ProductoaCambiar> productoacambiarCollectionOld = persistentLiquidacion.getProductoacambiarCollection();
            Collection<ProductoaCambiar> productoacambiarCollectionNew = liquidacion.getProductoacambiarCollection();
            List<String> illegalOrphanMessages = null;
            for (DescripcionLiquidacion descripcionLiquidacionCollectionOldDescripcionLiquidacion : descripcionLiquidacionCollectionOld) {
                if (!descripcionLiquidacionCollectionNew.contains(descripcionLiquidacionCollectionOldDescripcionLiquidacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DescripcionLiquidacion " + descripcionLiquidacionCollectionOldDescripcionLiquidacion + " since its liquidacionIdliquidacion field is not nullable.");
                }
            }
            for (ProductoaCambiar productoacambiarCollectionOldProductoaCambiar : productoacambiarCollectionOld) {
                if (!productoacambiarCollectionNew.contains(productoacambiarCollectionOldProductoaCambiar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductoaCambiar " + productoacambiarCollectionOldProductoaCambiar + " since its liquidacionIdliquidacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pedidoIdpedidoNew != null) {
                pedidoIdpedidoNew = em.getReference(pedidoIdpedidoNew.getClass(), pedidoIdpedidoNew.getIdpedido());
                liquidacion.setPedidoIdpedido(pedidoIdpedidoNew);
            }
            Collection<DescripcionLiquidacion> attachedDescripcionLiquidacionCollectionNew = new ArrayList<DescripcionLiquidacion>();
            for (DescripcionLiquidacion descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach : descripcionLiquidacionCollectionNew) {
                descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach = em.getReference(descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach.getClass(), descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach.getIdDescripcionLiquidacion());
                attachedDescripcionLiquidacionCollectionNew.add(descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach);
            }
            descripcionLiquidacionCollectionNew = attachedDescripcionLiquidacionCollectionNew;
            liquidacion.setDescripcionLiquidacionCollection(descripcionLiquidacionCollectionNew);
            Collection<ProductoaCambiar> attachedProductoacambiarCollectionNew = new ArrayList<ProductoaCambiar>();
            for (ProductoaCambiar productoacambiarCollectionNewProductoaCambiarToAttach : productoacambiarCollectionNew) {
                productoacambiarCollectionNewProductoaCambiarToAttach = em.getReference(productoacambiarCollectionNewProductoaCambiarToAttach.getClass(), productoacambiarCollectionNewProductoaCambiarToAttach.getIdproductoacambiar());
                attachedProductoacambiarCollectionNew.add(productoacambiarCollectionNewProductoaCambiarToAttach);
            }
            productoacambiarCollectionNew = attachedProductoacambiarCollectionNew;
            liquidacion.setProductoacambiarCollection(productoacambiarCollectionNew);
            liquidacion = em.merge(liquidacion);
            if (pedidoIdpedidoOld != null && !pedidoIdpedidoOld.equals(pedidoIdpedidoNew)) {
                pedidoIdpedidoOld.getLiquidacionCollection().remove(liquidacion);
                pedidoIdpedidoOld = em.merge(pedidoIdpedidoOld);
            }
            if (pedidoIdpedidoNew != null && !pedidoIdpedidoNew.equals(pedidoIdpedidoOld)) {
                pedidoIdpedidoNew.getLiquidacionCollection().add(liquidacion);
                pedidoIdpedidoNew = em.merge(pedidoIdpedidoNew);
            }
            for (DescripcionLiquidacion descripcionLiquidacionCollectionNewDescripcionLiquidacion : descripcionLiquidacionCollectionNew) {
                if (!descripcionLiquidacionCollectionOld.contains(descripcionLiquidacionCollectionNewDescripcionLiquidacion)) {
                    Liquidacion oldLiquidacionIdliquidacionOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion = descripcionLiquidacionCollectionNewDescripcionLiquidacion.getLiquidacionIdliquidacion();
                    descripcionLiquidacionCollectionNewDescripcionLiquidacion.setLiquidacionIdliquidacion(liquidacion);
                    descripcionLiquidacionCollectionNewDescripcionLiquidacion = em.merge(descripcionLiquidacionCollectionNewDescripcionLiquidacion);
                    if (oldLiquidacionIdliquidacionOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion != null && !oldLiquidacionIdliquidacionOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion.equals(liquidacion)) {
                        oldLiquidacionIdliquidacionOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion.getDescripcionLiquidacionCollection().remove(descripcionLiquidacionCollectionNewDescripcionLiquidacion);
                        oldLiquidacionIdliquidacionOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion = em.merge(oldLiquidacionIdliquidacionOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion);
                    }
                }
            }
            for (ProductoaCambiar productoacambiarCollectionNewProductoaCambiar : productoacambiarCollectionNew) {
                if (!productoacambiarCollectionOld.contains(productoacambiarCollectionNewProductoaCambiar)) {
                    Liquidacion oldLiquidacionIdliquidacionOfProductoacambiarCollectionNewProductoaCambiar = productoacambiarCollectionNewProductoaCambiar.getLiquidacionIdliquidacion();
                    productoacambiarCollectionNewProductoaCambiar.setLiquidacionIdliquidacion(liquidacion);
                    productoacambiarCollectionNewProductoaCambiar = em.merge(productoacambiarCollectionNewProductoaCambiar);
                    if (oldLiquidacionIdliquidacionOfProductoacambiarCollectionNewProductoaCambiar != null && !oldLiquidacionIdliquidacionOfProductoacambiarCollectionNewProductoaCambiar.equals(liquidacion)) {
                        oldLiquidacionIdliquidacionOfProductoacambiarCollectionNewProductoaCambiar.getProductoacambiarCollection().remove(productoacambiarCollectionNewProductoaCambiar);
                        oldLiquidacionIdliquidacionOfProductoacambiarCollectionNewProductoaCambiar = em.merge(oldLiquidacionIdliquidacionOfProductoacambiarCollectionNewProductoaCambiar);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = liquidacion.getIdliquidacion();
                if (findLiquidacion(id) == null) {
                    throw new NonexistentEntityException("The liquidacion with id " + id + " no longer exists.");
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
            Liquidacion liquidacion;
            try {
                liquidacion = em.getReference(Liquidacion.class, id);
                liquidacion.getIdliquidacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The liquidacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DescripcionLiquidacion> descripcionLiquidacionCollectionOrphanCheck = liquidacion.getDescripcionLiquidacionCollection();
            for (DescripcionLiquidacion descripcionLiquidacionCollectionOrphanCheckDescripcionLiquidacion : descripcionLiquidacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Liquidacion (" + liquidacion + ") cannot be destroyed since the DescripcionLiquidacion " + descripcionLiquidacionCollectionOrphanCheckDescripcionLiquidacion + " in its descripcionLiquidacionCollection field has a non-nullable liquidacionIdliquidacion field.");
            }
            Collection<ProductoaCambiar> productoacambiarCollectionOrphanCheck = liquidacion.getProductoacambiarCollection();
            for (ProductoaCambiar productoacambiarCollectionOrphanCheckProductoaCambiar : productoacambiarCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Liquidacion (" + liquidacion + ") cannot be destroyed since the ProductoaCambiar " + productoacambiarCollectionOrphanCheckProductoaCambiar + " in its productoacambiarCollection field has a non-nullable liquidacionIdliquidacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pedido pedidoIdpedido = liquidacion.getPedidoIdpedido();
            if (pedidoIdpedido != null) {
                pedidoIdpedido.getLiquidacionCollection().remove(liquidacion);
                pedidoIdpedido = em.merge(pedidoIdpedido);
            }
            em.remove(liquidacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Liquidacion> findLiquidacionEntities() {
        return findLiquidacionEntities(true, -1, -1);
    }

    public List<Liquidacion> findLiquidacionEntities(int maxResults, int firstResult) {
        return findLiquidacionEntities(false, maxResults, firstResult);
    }

    private List<Liquidacion> findLiquidacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Liquidacion.class));
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

    public Liquidacion findLiquidacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Liquidacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getLiquidacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Liquidacion> rt = cq.from(Liquidacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
