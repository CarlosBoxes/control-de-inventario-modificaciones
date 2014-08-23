/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.NonexistentEntityException;
import ControladoresJPA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.Depositos;
import EntidadesJPA.Clientes;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.CuentaporCobrar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class CuentaporCobrarJpaController implements Serializable {

    public CuentaporCobrarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CuentaporCobrar cuentaporCobrar) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Depositos depositosidDepositos = cuentaporCobrar.getDepositosidDepositos();
            if (depositosidDepositos != null) {
                depositosidDepositos = em.getReference(depositosidDepositos.getClass(), depositosidDepositos.getIdDepositos());
                cuentaporCobrar.setDepositosidDepositos(depositosidDepositos);
            }
            Clientes clientesidCliente = cuentaporCobrar.getClientesidCliente();
            if (clientesidCliente != null) {
                clientesidCliente = em.getReference(clientesidCliente.getClass(), clientesidCliente.getIdCliente());
                cuentaporCobrar.setClientesidCliente(clientesidCliente);
            }
            ChequesClientes chequesClientesIdchequesClientes = cuentaporCobrar.getChequesClientesIdchequesClientes();
            if (chequesClientesIdchequesClientes != null) {
                chequesClientesIdchequesClientes = em.getReference(chequesClientesIdchequesClientes.getClass(), chequesClientesIdchequesClientes.getIdchequesClientes());
                cuentaporCobrar.setChequesClientesIdchequesClientes(chequesClientesIdchequesClientes);
            }
            em.persist(cuentaporCobrar);
            if (depositosidDepositos != null) {
                depositosidDepositos.getCuentaporcobrarCollection().add(cuentaporCobrar);
                depositosidDepositos = em.merge(depositosidDepositos);
            }
            if (clientesidCliente != null) {
                clientesidCliente.getCuentaporcobrarCollection().add(cuentaporCobrar);
                clientesidCliente = em.merge(clientesidCliente);
            }
            if (chequesClientesIdchequesClientes != null) {
                chequesClientesIdchequesClientes.getCuentaporcobrarCollection().add(cuentaporCobrar);
                chequesClientesIdchequesClientes = em.merge(chequesClientesIdchequesClientes);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCuentaporCobrar(cuentaporCobrar.getIdcuenta()) != null) {
                throw new PreexistingEntityException("CuentaporCobrar " + cuentaporCobrar + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CuentaporCobrar cuentaporCobrar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CuentaporCobrar persistentCuentaporCobrar = em.find(CuentaporCobrar.class, cuentaporCobrar.getIdcuenta());
            Depositos depositosidDepositosOld = persistentCuentaporCobrar.getDepositosidDepositos();
            Depositos depositosidDepositosNew = cuentaporCobrar.getDepositosidDepositos();
            Clientes clientesidClienteOld = persistentCuentaporCobrar.getClientesidCliente();
            Clientes clientesidClienteNew = cuentaporCobrar.getClientesidCliente();
            ChequesClientes chequesClientesIdchequesClientesOld = persistentCuentaporCobrar.getChequesClientesIdchequesClientes();
            ChequesClientes chequesClientesIdchequesClientesNew = cuentaporCobrar.getChequesClientesIdchequesClientes();
            if (depositosidDepositosNew != null) {
                depositosidDepositosNew = em.getReference(depositosidDepositosNew.getClass(), depositosidDepositosNew.getIdDepositos());
                cuentaporCobrar.setDepositosidDepositos(depositosidDepositosNew);
            }
            if (clientesidClienteNew != null) {
                clientesidClienteNew = em.getReference(clientesidClienteNew.getClass(), clientesidClienteNew.getIdCliente());
                cuentaporCobrar.setClientesidCliente(clientesidClienteNew);
            }
            if (chequesClientesIdchequesClientesNew != null) {
                chequesClientesIdchequesClientesNew = em.getReference(chequesClientesIdchequesClientesNew.getClass(), chequesClientesIdchequesClientesNew.getIdchequesClientes());
                cuentaporCobrar.setChequesClientesIdchequesClientes(chequesClientesIdchequesClientesNew);
            }
            cuentaporCobrar = em.merge(cuentaporCobrar);
            if (depositosidDepositosOld != null && !depositosidDepositosOld.equals(depositosidDepositosNew)) {
                depositosidDepositosOld.getCuentaporcobrarCollection().remove(cuentaporCobrar);
                depositosidDepositosOld = em.merge(depositosidDepositosOld);
            }
            if (depositosidDepositosNew != null && !depositosidDepositosNew.equals(depositosidDepositosOld)) {
                depositosidDepositosNew.getCuentaporcobrarCollection().add(cuentaporCobrar);
                depositosidDepositosNew = em.merge(depositosidDepositosNew);
            }
            if (clientesidClienteOld != null && !clientesidClienteOld.equals(clientesidClienteNew)) {
                clientesidClienteOld.getCuentaporcobrarCollection().remove(cuentaporCobrar);
                clientesidClienteOld = em.merge(clientesidClienteOld);
            }
            if (clientesidClienteNew != null && !clientesidClienteNew.equals(clientesidClienteOld)) {
                clientesidClienteNew.getCuentaporcobrarCollection().add(cuentaporCobrar);
                clientesidClienteNew = em.merge(clientesidClienteNew);
            }
            if (chequesClientesIdchequesClientesOld != null && !chequesClientesIdchequesClientesOld.equals(chequesClientesIdchequesClientesNew)) {
                chequesClientesIdchequesClientesOld.getCuentaporcobrarCollection().remove(cuentaporCobrar);
                chequesClientesIdchequesClientesOld = em.merge(chequesClientesIdchequesClientesOld);
            }
            if (chequesClientesIdchequesClientesNew != null && !chequesClientesIdchequesClientesNew.equals(chequesClientesIdchequesClientesOld)) {
                chequesClientesIdchequesClientesNew.getCuentaporcobrarCollection().add(cuentaporCobrar);
                chequesClientesIdchequesClientesNew = em.merge(chequesClientesIdchequesClientesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cuentaporCobrar.getIdcuenta();
                if (findCuentaporCobrar(id) == null) {
                    throw new NonexistentEntityException("The cuentaporCobrar with id " + id + " no longer exists.");
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
            CuentaporCobrar cuentaporCobrar;
            try {
                cuentaporCobrar = em.getReference(CuentaporCobrar.class, id);
                cuentaporCobrar.getIdcuenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuentaporCobrar with id " + id + " no longer exists.", enfe);
            }
            Depositos depositosidDepositos = cuentaporCobrar.getDepositosidDepositos();
            if (depositosidDepositos != null) {
                depositosidDepositos.getCuentaporcobrarCollection().remove(cuentaporCobrar);
                depositosidDepositos = em.merge(depositosidDepositos);
            }
            Clientes clientesidCliente = cuentaporCobrar.getClientesidCliente();
            if (clientesidCliente != null) {
                clientesidCliente.getCuentaporcobrarCollection().remove(cuentaporCobrar);
                clientesidCliente = em.merge(clientesidCliente);
            }
            ChequesClientes chequesClientesIdchequesClientes = cuentaporCobrar.getChequesClientesIdchequesClientes();
            if (chequesClientesIdchequesClientes != null) {
                chequesClientesIdchequesClientes.getCuentaporcobrarCollection().remove(cuentaporCobrar);
                chequesClientesIdchequesClientes = em.merge(chequesClientesIdchequesClientes);
            }
            em.remove(cuentaporCobrar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CuentaporCobrar> findCuentaporCobrarEntities() {
        return findCuentaporCobrarEntities(true, -1, -1);
    }

    public List<CuentaporCobrar> findCuentaporCobrarEntities(int maxResults, int firstResult) {
        return findCuentaporCobrarEntities(false, maxResults, firstResult);
    }

    private List<CuentaporCobrar> findCuentaporCobrarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CuentaporCobrar.class));
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

    public CuentaporCobrar findCuentaporCobrar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CuentaporCobrar.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuentaporCobrarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CuentaporCobrar> rt = cq.from(CuentaporCobrar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
