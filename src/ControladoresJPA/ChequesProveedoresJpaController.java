/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.Proveedores;
import EntidadesJPA.Bancos;
import EntidadesJPA.ChequesProveedores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ChequesProveedoresJpaController implements Serializable {

    public ChequesProveedoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ChequesProveedores chequesProveedores) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores proveedoresidProveedores = chequesProveedores.getProveedoresidProveedores();
            if (proveedoresidProveedores != null) {
                proveedoresidProveedores = em.getReference(proveedoresidProveedores.getClass(), proveedoresidProveedores.getIdProveedores());
                chequesProveedores.setProveedoresidProveedores(proveedoresidProveedores);
            }
            Bancos bancosIdbancos = chequesProveedores.getBancosIdbancos();
            if (bancosIdbancos != null) {
                bancosIdbancos = em.getReference(bancosIdbancos.getClass(), bancosIdbancos.getIdbancos());
                chequesProveedores.setBancosIdbancos(bancosIdbancos);
            }
            em.persist(chequesProveedores);
            if (proveedoresidProveedores != null) {
                proveedoresidProveedores.getChequesProveedoresCollection().add(chequesProveedores);
                proveedoresidProveedores = em.merge(proveedoresidProveedores);
            }
            if (bancosIdbancos != null) {
                bancosIdbancos.getChequesProveedoresCollection().add(chequesProveedores);
                bancosIdbancos = em.merge(bancosIdbancos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ChequesProveedores chequesProveedores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ChequesProveedores persistentChequesProveedores = em.find(ChequesProveedores.class, chequesProveedores.getIdchequesProveedores());
            Proveedores proveedoresidProveedoresOld = persistentChequesProveedores.getProveedoresidProveedores();
            Proveedores proveedoresidProveedoresNew = chequesProveedores.getProveedoresidProveedores();
            Bancos bancosIdbancosOld = persistentChequesProveedores.getBancosIdbancos();
            Bancos bancosIdbancosNew = chequesProveedores.getBancosIdbancos();
            if (proveedoresidProveedoresNew != null) {
                proveedoresidProveedoresNew = em.getReference(proveedoresidProveedoresNew.getClass(), proveedoresidProveedoresNew.getIdProveedores());
                chequesProveedores.setProveedoresidProveedores(proveedoresidProveedoresNew);
            }
            if (bancosIdbancosNew != null) {
                bancosIdbancosNew = em.getReference(bancosIdbancosNew.getClass(), bancosIdbancosNew.getIdbancos());
                chequesProveedores.setBancosIdbancos(bancosIdbancosNew);
            }
            chequesProveedores = em.merge(chequesProveedores);
            if (proveedoresidProveedoresOld != null && !proveedoresidProveedoresOld.equals(proveedoresidProveedoresNew)) {
                proveedoresidProveedoresOld.getChequesProveedoresCollection().remove(chequesProveedores);
                proveedoresidProveedoresOld = em.merge(proveedoresidProveedoresOld);
            }
            if (proveedoresidProveedoresNew != null && !proveedoresidProveedoresNew.equals(proveedoresidProveedoresOld)) {
                proveedoresidProveedoresNew.getChequesProveedoresCollection().add(chequesProveedores);
                proveedoresidProveedoresNew = em.merge(proveedoresidProveedoresNew);
            }
            if (bancosIdbancosOld != null && !bancosIdbancosOld.equals(bancosIdbancosNew)) {
                bancosIdbancosOld.getChequesProveedoresCollection().remove(chequesProveedores);
                bancosIdbancosOld = em.merge(bancosIdbancosOld);
            }
            if (bancosIdbancosNew != null && !bancosIdbancosNew.equals(bancosIdbancosOld)) {
                bancosIdbancosNew.getChequesProveedoresCollection().add(chequesProveedores);
                bancosIdbancosNew = em.merge(bancosIdbancosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = chequesProveedores.getIdchequesProveedores();
                if (findChequesProveedores(id) == null) {
                    throw new NonexistentEntityException("The chequesProveedores with id " + id + " no longer exists.");
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
            ChequesProveedores chequesProveedores;
            try {
                chequesProveedores = em.getReference(ChequesProveedores.class, id);
                chequesProveedores.getIdchequesProveedores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chequesProveedores with id " + id + " no longer exists.", enfe);
            }
            Proveedores proveedoresidProveedores = chequesProveedores.getProveedoresidProveedores();
            if (proveedoresidProveedores != null) {
                proveedoresidProveedores.getChequesProveedoresCollection().remove(chequesProveedores);
                proveedoresidProveedores = em.merge(proveedoresidProveedores);
            }
            Bancos bancosIdbancos = chequesProveedores.getBancosIdbancos();
            if (bancosIdbancos != null) {
                bancosIdbancos.getChequesProveedoresCollection().remove(chequesProveedores);
                bancosIdbancos = em.merge(bancosIdbancos);
            }
            em.remove(chequesProveedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ChequesProveedores> findChequesProveedoresEntities() {
        return findChequesProveedoresEntities(true, -1, -1);
    }

    public List<ChequesProveedores> findChequesProveedoresEntities(int maxResults, int firstResult) {
        return findChequesProveedoresEntities(false, maxResults, firstResult);
    }

    private List<ChequesProveedores> findChequesProveedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ChequesProveedores.class));
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

    public ChequesProveedores findChequesProveedores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ChequesProveedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getChequesProveedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ChequesProveedores> rt = cq.from(ChequesProveedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
