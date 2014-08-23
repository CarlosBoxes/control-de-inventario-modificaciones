/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.NonexistentEntityException;
import EntidadesJPA.DescripcionFactura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.Productos;
import EntidadesJPA.Facturas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class DescripcionFacturaJpaController implements Serializable {

    public DescripcionFacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DescripcionFactura descripcionFactura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productosidProductos = descripcionFactura.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos = em.getReference(productosidProductos.getClass(), productosidProductos.getIdProductos());
                descripcionFactura.setProductosidProductos(productosidProductos);
            }
            Facturas facturasidFacturas = descripcionFactura.getFacturasidFacturas();
            if (facturasidFacturas != null) {
                facturasidFacturas = em.getReference(facturasidFacturas.getClass(), facturasidFacturas.getIdFacturas());
                descripcionFactura.setFacturasidFacturas(facturasidFacturas);
            }
            em.persist(descripcionFactura);
            if (productosidProductos != null) {
                productosidProductos.getDescripcionFacturaCollection().add(descripcionFactura);
                productosidProductos = em.merge(productosidProductos);
            }
            if (facturasidFacturas != null) {
                facturasidFacturas.getDescripcionFacturaCollection().add(descripcionFactura);
                facturasidFacturas = em.merge(facturasidFacturas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DescripcionFactura descripcionFactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DescripcionFactura persistentDescripcionFactura = em.find(DescripcionFactura.class, descripcionFactura.getIddescripcionFactura());
            Productos productosidProductosOld = persistentDescripcionFactura.getProductosidProductos();
            Productos productosidProductosNew = descripcionFactura.getProductosidProductos();
            Facturas facturasidFacturasOld = persistentDescripcionFactura.getFacturasidFacturas();
            Facturas facturasidFacturasNew = descripcionFactura.getFacturasidFacturas();
            if (productosidProductosNew != null) {
                productosidProductosNew = em.getReference(productosidProductosNew.getClass(), productosidProductosNew.getIdProductos());
                descripcionFactura.setProductosidProductos(productosidProductosNew);
            }
            if (facturasidFacturasNew != null) {
                facturasidFacturasNew = em.getReference(facturasidFacturasNew.getClass(), facturasidFacturasNew.getIdFacturas());
                descripcionFactura.setFacturasidFacturas(facturasidFacturasNew);
            }
            descripcionFactura = em.merge(descripcionFactura);
            if (productosidProductosOld != null && !productosidProductosOld.equals(productosidProductosNew)) {
                productosidProductosOld.getDescripcionFacturaCollection().remove(descripcionFactura);
                productosidProductosOld = em.merge(productosidProductosOld);
            }
            if (productosidProductosNew != null && !productosidProductosNew.equals(productosidProductosOld)) {
                productosidProductosNew.getDescripcionFacturaCollection().add(descripcionFactura);
                productosidProductosNew = em.merge(productosidProductosNew);
            }
            if (facturasidFacturasOld != null && !facturasidFacturasOld.equals(facturasidFacturasNew)) {
                facturasidFacturasOld.getDescripcionFacturaCollection().remove(descripcionFactura);
                facturasidFacturasOld = em.merge(facturasidFacturasOld);
            }
            if (facturasidFacturasNew != null && !facturasidFacturasNew.equals(facturasidFacturasOld)) {
                facturasidFacturasNew.getDescripcionFacturaCollection().add(descripcionFactura);
                facturasidFacturasNew = em.merge(facturasidFacturasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = descripcionFactura.getIddescripcionFactura();
                if (findDescripcionFactura(id) == null) {
                    throw new NonexistentEntityException("The descripcionFactura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DescripcionFactura descripcionFactura;
            try {
                descripcionFactura = em.getReference(DescripcionFactura.class, id);
                descripcionFactura.getIddescripcionFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The descripcionFactura with id " + id + " no longer exists.", enfe);
            }
            Productos productosidProductos = descripcionFactura.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos.getDescripcionFacturaCollection().remove(descripcionFactura);
                productosidProductos = em.merge(productosidProductos);
            }
            Facturas facturasidFacturas = descripcionFactura.getFacturasidFacturas();
            if (facturasidFacturas != null) {
                facturasidFacturas.getDescripcionFacturaCollection().remove(descripcionFactura);
                facturasidFacturas = em.merge(facturasidFacturas);
            }
            em.remove(descripcionFactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DescripcionFactura> findDescripcionFacturaEntities() {
        return findDescripcionFacturaEntities(true, -1, -1);
    }

    public List<DescripcionFactura> findDescripcionFacturaEntities(int maxResults, int firstResult) {
        return findDescripcionFacturaEntities(false, maxResults, firstResult);
    }

    private List<DescripcionFactura> findDescripcionFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DescripcionFactura.class));
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

    public DescripcionFactura findDescripcionFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DescripcionFactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getDescripcionFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DescripcionFactura> rt = cq.from(DescripcionFactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
