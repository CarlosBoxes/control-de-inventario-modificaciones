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
import EntidadesJPA.Clientes;
import EntidadesJPA.Bancos;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.DescripcionLiquidacion;
import java.util.ArrayList;
import java.util.Collection;
import EntidadesJPA.CuentaporCobrar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ChequesClientesJpaController implements Serializable {

    public ChequesClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ChequesClientes chequesClientes) {
        if (chequesClientes.getDescripcionLiquidacionCollection() == null) {
            chequesClientes.setDescripcionLiquidacionCollection(new ArrayList<DescripcionLiquidacion>());
        }
        if (chequesClientes.getCuentaporcobrarCollection() == null) {
            chequesClientes.setCuentaporcobrarCollection(new ArrayList<CuentaporCobrar>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clientesidCliente = chequesClientes.getClientesidCliente();
            if (clientesidCliente != null) {
                clientesidCliente = em.getReference(clientesidCliente.getClass(), clientesidCliente.getIdCliente());
                chequesClientes.setClientesidCliente(clientesidCliente);
            }
            Bancos bancosIdbancos = chequesClientes.getBancosIdbancos();
            if (bancosIdbancos != null) {
                bancosIdbancos = em.getReference(bancosIdbancos.getClass(), bancosIdbancos.getIdbancos());
                chequesClientes.setBancosIdbancos(bancosIdbancos);
            }
            Collection<DescripcionLiquidacion> attachedDescripcionLiquidacionCollection = new ArrayList<DescripcionLiquidacion>();
            for (DescripcionLiquidacion descripcionLiquidacionCollectionDescripcionLiquidacionToAttach : chequesClientes.getDescripcionLiquidacionCollection()) {
                descripcionLiquidacionCollectionDescripcionLiquidacionToAttach = em.getReference(descripcionLiquidacionCollectionDescripcionLiquidacionToAttach.getClass(), descripcionLiquidacionCollectionDescripcionLiquidacionToAttach.getIdDescripcionLiquidacion());
                attachedDescripcionLiquidacionCollection.add(descripcionLiquidacionCollectionDescripcionLiquidacionToAttach);
            }
            chequesClientes.setDescripcionLiquidacionCollection(attachedDescripcionLiquidacionCollection);
            Collection<CuentaporCobrar> attachedCuentaporcobrarCollection = new ArrayList<CuentaporCobrar>();
            for (CuentaporCobrar cuentaporcobrarCollectionCuentaporCobrarToAttach : chequesClientes.getCuentaporcobrarCollection()) {
                cuentaporcobrarCollectionCuentaporCobrarToAttach = em.getReference(cuentaporcobrarCollectionCuentaporCobrarToAttach.getClass(), cuentaporcobrarCollectionCuentaporCobrarToAttach.getIdcuenta());
                attachedCuentaporcobrarCollection.add(cuentaporcobrarCollectionCuentaporCobrarToAttach);
            }
            chequesClientes.setCuentaporcobrarCollection(attachedCuentaporcobrarCollection);
            em.persist(chequesClientes);
            if (clientesidCliente != null) {
                clientesidCliente.getChequesClientesCollection().add(chequesClientes);
                clientesidCliente = em.merge(clientesidCliente);
            }
            if (bancosIdbancos != null) {
                bancosIdbancos.getChequesClientesCollection().add(chequesClientes);
                bancosIdbancos = em.merge(bancosIdbancos);
            }
            for (DescripcionLiquidacion descripcionLiquidacionCollectionDescripcionLiquidacion : chequesClientes.getDescripcionLiquidacionCollection()) {
                ChequesClientes oldChequesClientesIdchequesClientesOfDescripcionLiquidacionCollectionDescripcionLiquidacion = descripcionLiquidacionCollectionDescripcionLiquidacion.getChequesClientesIdchequesClientes();
                descripcionLiquidacionCollectionDescripcionLiquidacion.setChequesClientesIdchequesClientes(chequesClientes);
                descripcionLiquidacionCollectionDescripcionLiquidacion = em.merge(descripcionLiquidacionCollectionDescripcionLiquidacion);
                if (oldChequesClientesIdchequesClientesOfDescripcionLiquidacionCollectionDescripcionLiquidacion != null) {
                    oldChequesClientesIdchequesClientesOfDescripcionLiquidacionCollectionDescripcionLiquidacion.getDescripcionLiquidacionCollection().remove(descripcionLiquidacionCollectionDescripcionLiquidacion);
                    oldChequesClientesIdchequesClientesOfDescripcionLiquidacionCollectionDescripcionLiquidacion = em.merge(oldChequesClientesIdchequesClientesOfDescripcionLiquidacionCollectionDescripcionLiquidacion);
                }
            }
            for (CuentaporCobrar cuentaporcobrarCollectionCuentaporCobrar : chequesClientes.getCuentaporcobrarCollection()) {
                ChequesClientes oldChequesClientesIdchequesClientesOfCuentaporcobrarCollectionCuentaporCobrar = cuentaporcobrarCollectionCuentaporCobrar.getChequesClientesIdchequesClientes();
                cuentaporcobrarCollectionCuentaporCobrar.setChequesClientesIdchequesClientes(chequesClientes);
                cuentaporcobrarCollectionCuentaporCobrar = em.merge(cuentaporcobrarCollectionCuentaporCobrar);
                if (oldChequesClientesIdchequesClientesOfCuentaporcobrarCollectionCuentaporCobrar != null) {
                    oldChequesClientesIdchequesClientesOfCuentaporcobrarCollectionCuentaporCobrar.getCuentaporcobrarCollection().remove(cuentaporcobrarCollectionCuentaporCobrar);
                    oldChequesClientesIdchequesClientesOfCuentaporcobrarCollectionCuentaporCobrar = em.merge(oldChequesClientesIdchequesClientesOfCuentaporcobrarCollectionCuentaporCobrar);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ChequesClientes chequesClientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ChequesClientes persistentChequesClientes = em.find(ChequesClientes.class, chequesClientes.getIdchequesClientes());
            Clientes clientesidClienteOld = persistentChequesClientes.getClientesidCliente();
            Clientes clientesidClienteNew = chequesClientes.getClientesidCliente();
            Bancos bancosIdbancosOld = persistentChequesClientes.getBancosIdbancos();
            Bancos bancosIdbancosNew = chequesClientes.getBancosIdbancos();
            Collection<DescripcionLiquidacion> descripcionLiquidacionCollectionOld = persistentChequesClientes.getDescripcionLiquidacionCollection();
            Collection<DescripcionLiquidacion> descripcionLiquidacionCollectionNew = chequesClientes.getDescripcionLiquidacionCollection();
            Collection<CuentaporCobrar> cuentaporcobrarCollectionOld = persistentChequesClientes.getCuentaporcobrarCollection();
            Collection<CuentaporCobrar> cuentaporcobrarCollectionNew = chequesClientes.getCuentaporcobrarCollection();
            if (clientesidClienteNew != null) {
                clientesidClienteNew = em.getReference(clientesidClienteNew.getClass(), clientesidClienteNew.getIdCliente());
                chequesClientes.setClientesidCliente(clientesidClienteNew);
            }
            if (bancosIdbancosNew != null) {
                bancosIdbancosNew = em.getReference(bancosIdbancosNew.getClass(), bancosIdbancosNew.getIdbancos());
                chequesClientes.setBancosIdbancos(bancosIdbancosNew);
            }
            Collection<DescripcionLiquidacion> attachedDescripcionLiquidacionCollectionNew = new ArrayList<DescripcionLiquidacion>();
            for (DescripcionLiquidacion descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach : descripcionLiquidacionCollectionNew) {
                descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach = em.getReference(descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach.getClass(), descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach.getIdDescripcionLiquidacion());
                attachedDescripcionLiquidacionCollectionNew.add(descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach);
            }
            descripcionLiquidacionCollectionNew = attachedDescripcionLiquidacionCollectionNew;
            chequesClientes.setDescripcionLiquidacionCollection(descripcionLiquidacionCollectionNew);
            Collection<CuentaporCobrar> attachedCuentaporcobrarCollectionNew = new ArrayList<CuentaporCobrar>();
            for (CuentaporCobrar cuentaporcobrarCollectionNewCuentaporCobrarToAttach : cuentaporcobrarCollectionNew) {
                cuentaporcobrarCollectionNewCuentaporCobrarToAttach = em.getReference(cuentaporcobrarCollectionNewCuentaporCobrarToAttach.getClass(), cuentaporcobrarCollectionNewCuentaporCobrarToAttach.getIdcuenta());
                attachedCuentaporcobrarCollectionNew.add(cuentaporcobrarCollectionNewCuentaporCobrarToAttach);
            }
            cuentaporcobrarCollectionNew = attachedCuentaporcobrarCollectionNew;
            chequesClientes.setCuentaporcobrarCollection(cuentaporcobrarCollectionNew);
            chequesClientes = em.merge(chequesClientes);
            if (clientesidClienteOld != null && !clientesidClienteOld.equals(clientesidClienteNew)) {
                clientesidClienteOld.getChequesClientesCollection().remove(chequesClientes);
                clientesidClienteOld = em.merge(clientesidClienteOld);
            }
            if (clientesidClienteNew != null && !clientesidClienteNew.equals(clientesidClienteOld)) {
                clientesidClienteNew.getChequesClientesCollection().add(chequesClientes);
                clientesidClienteNew = em.merge(clientesidClienteNew);
            }
            if (bancosIdbancosOld != null && !bancosIdbancosOld.equals(bancosIdbancosNew)) {
                bancosIdbancosOld.getChequesClientesCollection().remove(chequesClientes);
                bancosIdbancosOld = em.merge(bancosIdbancosOld);
            }
            if (bancosIdbancosNew != null && !bancosIdbancosNew.equals(bancosIdbancosOld)) {
                bancosIdbancosNew.getChequesClientesCollection().add(chequesClientes);
                bancosIdbancosNew = em.merge(bancosIdbancosNew);
            }
            for (DescripcionLiquidacion descripcionLiquidacionCollectionOldDescripcionLiquidacion : descripcionLiquidacionCollectionOld) {
                if (!descripcionLiquidacionCollectionNew.contains(descripcionLiquidacionCollectionOldDescripcionLiquidacion)) {
                    descripcionLiquidacionCollectionOldDescripcionLiquidacion.setChequesClientesIdchequesClientes(null);
                    descripcionLiquidacionCollectionOldDescripcionLiquidacion = em.merge(descripcionLiquidacionCollectionOldDescripcionLiquidacion);
                }
            }
            for (DescripcionLiquidacion descripcionLiquidacionCollectionNewDescripcionLiquidacion : descripcionLiquidacionCollectionNew) {
                if (!descripcionLiquidacionCollectionOld.contains(descripcionLiquidacionCollectionNewDescripcionLiquidacion)) {
                    ChequesClientes oldChequesClientesIdchequesClientesOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion = descripcionLiquidacionCollectionNewDescripcionLiquidacion.getChequesClientesIdchequesClientes();
                    descripcionLiquidacionCollectionNewDescripcionLiquidacion.setChequesClientesIdchequesClientes(chequesClientes);
                    descripcionLiquidacionCollectionNewDescripcionLiquidacion = em.merge(descripcionLiquidacionCollectionNewDescripcionLiquidacion);
                    if (oldChequesClientesIdchequesClientesOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion != null && !oldChequesClientesIdchequesClientesOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion.equals(chequesClientes)) {
                        oldChequesClientesIdchequesClientesOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion.getDescripcionLiquidacionCollection().remove(descripcionLiquidacionCollectionNewDescripcionLiquidacion);
                        oldChequesClientesIdchequesClientesOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion = em.merge(oldChequesClientesIdchequesClientesOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion);
                    }
                }
            }
            for (CuentaporCobrar cuentaporcobrarCollectionOldCuentaporCobrar : cuentaporcobrarCollectionOld) {
                if (!cuentaporcobrarCollectionNew.contains(cuentaporcobrarCollectionOldCuentaporCobrar)) {
                    cuentaporcobrarCollectionOldCuentaporCobrar.setChequesClientesIdchequesClientes(null);
                    cuentaporcobrarCollectionOldCuentaporCobrar = em.merge(cuentaporcobrarCollectionOldCuentaporCobrar);
                }
            }
            for (CuentaporCobrar cuentaporcobrarCollectionNewCuentaporCobrar : cuentaporcobrarCollectionNew) {
                if (!cuentaporcobrarCollectionOld.contains(cuentaporcobrarCollectionNewCuentaporCobrar)) {
                    ChequesClientes oldChequesClientesIdchequesClientesOfCuentaporcobrarCollectionNewCuentaporCobrar = cuentaporcobrarCollectionNewCuentaporCobrar.getChequesClientesIdchequesClientes();
                    cuentaporcobrarCollectionNewCuentaporCobrar.setChequesClientesIdchequesClientes(chequesClientes);
                    cuentaporcobrarCollectionNewCuentaporCobrar = em.merge(cuentaporcobrarCollectionNewCuentaporCobrar);
                    if (oldChequesClientesIdchequesClientesOfCuentaporcobrarCollectionNewCuentaporCobrar != null && !oldChequesClientesIdchequesClientesOfCuentaporcobrarCollectionNewCuentaporCobrar.equals(chequesClientes)) {
                        oldChequesClientesIdchequesClientesOfCuentaporcobrarCollectionNewCuentaporCobrar.getCuentaporcobrarCollection().remove(cuentaporcobrarCollectionNewCuentaporCobrar);
                        oldChequesClientesIdchequesClientesOfCuentaporcobrarCollectionNewCuentaporCobrar = em.merge(oldChequesClientesIdchequesClientesOfCuentaporcobrarCollectionNewCuentaporCobrar);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = chequesClientes.getIdchequesClientes();
                if (findChequesClientes(id) == null) {
                    throw new NonexistentEntityException("The chequesClientes with id " + id + " no longer exists.");
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
            ChequesClientes chequesClientes;
            try {
                chequesClientes = em.getReference(ChequesClientes.class, id);
                chequesClientes.getIdchequesClientes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chequesClientes with id " + id + " no longer exists.", enfe);
            }
            Clientes clientesidCliente = chequesClientes.getClientesidCliente();
            if (clientesidCliente != null) {
                clientesidCliente.getChequesClientesCollection().remove(chequesClientes);
                clientesidCliente = em.merge(clientesidCliente);
            }
            Bancos bancosIdbancos = chequesClientes.getBancosIdbancos();
            if (bancosIdbancos != null) {
                bancosIdbancos.getChequesClientesCollection().remove(chequesClientes);
                bancosIdbancos = em.merge(bancosIdbancos);
            }
            Collection<DescripcionLiquidacion> descripcionLiquidacionCollection = chequesClientes.getDescripcionLiquidacionCollection();
            for (DescripcionLiquidacion descripcionLiquidacionCollectionDescripcionLiquidacion : descripcionLiquidacionCollection) {
                descripcionLiquidacionCollectionDescripcionLiquidacion.setChequesClientesIdchequesClientes(null);
                descripcionLiquidacionCollectionDescripcionLiquidacion = em.merge(descripcionLiquidacionCollectionDescripcionLiquidacion);
            }
            Collection<CuentaporCobrar> cuentaporcobrarCollection = chequesClientes.getCuentaporcobrarCollection();
            for (CuentaporCobrar cuentaporcobrarCollectionCuentaporCobrar : cuentaporcobrarCollection) {
                cuentaporcobrarCollectionCuentaporCobrar.setChequesClientesIdchequesClientes(null);
                cuentaporcobrarCollectionCuentaporCobrar = em.merge(cuentaporcobrarCollectionCuentaporCobrar);
            }
            em.remove(chequesClientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ChequesClientes> findChequesClientesEntities() {
        return findChequesClientesEntities(true, -1, -1);
    }

    public List<ChequesClientes> findChequesClientesEntities(int maxResults, int firstResult) {
        return findChequesClientesEntities(false, maxResults, firstResult);
    }

    private List<ChequesClientes> findChequesClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ChequesClientes.class));
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

    public ChequesClientes findChequesClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ChequesClientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getChequesClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ChequesClientes> rt = cq.from(ChequesClientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
