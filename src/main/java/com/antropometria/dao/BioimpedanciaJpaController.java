/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antropometria.dao;

import com.antropometria.dao.exceptions.NonexistentEntityException;
import com.antropometria.dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.antropometria.models.Avaliacao;
import com.antropometria.models.Bioimpedancia;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

/**
 *
 * @author anderson
 */
@Named
@RequestScoped
public class BioimpedanciaJpaController implements Serializable {

    @Resource
    private UserTransaction utx = null;
    
    @PersistenceUnit(name = "Antropometria")
    private EntityManagerFactory emf = null;

    public BioimpedanciaJpaController() {
    }
    
    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Antropometria");
        }
        return emf.createEntityManager();
    }

    public void create(Bioimpedancia bioimpedancia) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Avaliacao avaliacao = bioimpedancia.getAvaliacao();
            if (avaliacao != null) {
                avaliacao = em.getReference(avaliacao.getClass(), avaliacao.getId());
                bioimpedancia.setAvaliacao(avaliacao);
            }
            em.persist(bioimpedancia);
            if (avaliacao != null) {
                Bioimpedancia oldBioimpedanciaOfAvaliacao = avaliacao.getBioimpedancia();
                if (oldBioimpedanciaOfAvaliacao != null) {
                    oldBioimpedanciaOfAvaliacao.setAvaliacao(null);
                    oldBioimpedanciaOfAvaliacao = em.merge(oldBioimpedanciaOfAvaliacao);
                }
                avaliacao.setBioimpedancia(bioimpedancia);
                avaliacao = em.merge(avaliacao);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bioimpedancia bioimpedancia) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Bioimpedancia persistentBioimpedancia = em.find(Bioimpedancia.class, bioimpedancia.getId());
            Avaliacao avaliacaoOld = persistentBioimpedancia.getAvaliacao();
            Avaliacao avaliacaoNew = bioimpedancia.getAvaliacao();
            if (avaliacaoNew != null) {
                avaliacaoNew = em.getReference(avaliacaoNew.getClass(), avaliacaoNew.getId());
                bioimpedancia.setAvaliacao(avaliacaoNew);
            }
            bioimpedancia = em.merge(bioimpedancia);
            if (avaliacaoOld != null && !avaliacaoOld.equals(avaliacaoNew)) {
                avaliacaoOld.setBioimpedancia(null);
                avaliacaoOld = em.merge(avaliacaoOld);
            }
            if (avaliacaoNew != null && !avaliacaoNew.equals(avaliacaoOld)) {
                Bioimpedancia oldBioimpedanciaOfAvaliacao = avaliacaoNew.getBioimpedancia();
                if (oldBioimpedanciaOfAvaliacao != null) {
                    oldBioimpedanciaOfAvaliacao.setAvaliacao(null);
                    oldBioimpedanciaOfAvaliacao = em.merge(oldBioimpedanciaOfAvaliacao);
                }
                avaliacaoNew.setBioimpedancia(bioimpedancia);
                avaliacaoNew = em.merge(avaliacaoNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = bioimpedancia.getId();
                if (findBioimpedancia(id) == null) {
                    throw new NonexistentEntityException("The bioimpedancia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Bioimpedancia bioimpedancia;
            try {
                bioimpedancia = em.getReference(Bioimpedancia.class, id);
                bioimpedancia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bioimpedancia with id " + id + " no longer exists.", enfe);
            }
            Avaliacao avaliacao = bioimpedancia.getAvaliacao();
            if (avaliacao != null) {
                avaliacao.setBioimpedancia(null);
                avaliacao = em.merge(avaliacao);
            }
            em.remove(bioimpedancia);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bioimpedancia> findBioimpedanciaEntities() {
        return findBioimpedanciaEntities(true, -1, -1);
    }

    public List<Bioimpedancia> findBioimpedanciaEntities(int maxResults, int firstResult) {
        return findBioimpedanciaEntities(false, maxResults, firstResult);
    }

    private List<Bioimpedancia> findBioimpedanciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bioimpedancia.class));
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

    public Bioimpedancia findBioimpedancia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bioimpedancia.class, id);
        } finally {
            em.close();
        }
    }

    public int getBioimpedanciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bioimpedancia> rt = cq.from(Bioimpedancia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
