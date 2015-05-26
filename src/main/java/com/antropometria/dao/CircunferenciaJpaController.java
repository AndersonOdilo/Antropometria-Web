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
import com.antropometria.models.Circunferencia;
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
public class CircunferenciaJpaController implements Serializable {

    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit
    private EntityManagerFactory emf = null;

    public CircunferenciaJpaController() {
    }

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Antropometria");
        }
        return emf.createEntityManager();
    }

    public void create(Circunferencia circunferencia) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Avaliacao avaliacao = circunferencia.getAvaliacao();
            if (avaliacao != null) {
                avaliacao = em.getReference(avaliacao.getClass(), avaliacao.getId());
                circunferencia.setAvaliacao(avaliacao);
            }
            em.persist(circunferencia);
            if (avaliacao != null) {
                Circunferencia oldCircunferenciaOfAvaliacao = avaliacao.getCircunferencia();
                if (oldCircunferenciaOfAvaliacao != null) {
                    oldCircunferenciaOfAvaliacao.setAvaliacao(null);
                    oldCircunferenciaOfAvaliacao = em.merge(oldCircunferenciaOfAvaliacao);
                }
                avaliacao.setCircunferencia(circunferencia);
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

    public void edit(Circunferencia circunferencia) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Circunferencia persistentCircunferencia = em.find(Circunferencia.class, circunferencia.getId());
            Avaliacao avaliacaoOld = persistentCircunferencia.getAvaliacao();
            Avaliacao avaliacaoNew = circunferencia.getAvaliacao();
            if (avaliacaoNew != null) {
                avaliacaoNew = em.getReference(avaliacaoNew.getClass(), avaliacaoNew.getId());
                circunferencia.setAvaliacao(avaliacaoNew);
            }
            circunferencia = em.merge(circunferencia);
            if (avaliacaoOld != null && !avaliacaoOld.equals(avaliacaoNew)) {
                avaliacaoOld.setCircunferencia(null);
                avaliacaoOld = em.merge(avaliacaoOld);
            }
            if (avaliacaoNew != null && !avaliacaoNew.equals(avaliacaoOld)) {
                Circunferencia oldCircunferenciaOfAvaliacao = avaliacaoNew.getCircunferencia();
                if (oldCircunferenciaOfAvaliacao != null) {
                    oldCircunferenciaOfAvaliacao.setAvaliacao(null);
                    oldCircunferenciaOfAvaliacao = em.merge(oldCircunferenciaOfAvaliacao);
                }
                avaliacaoNew.setCircunferencia(circunferencia);
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
                Long id = circunferencia.getId();
                if (findCircunferencia(id) == null) {
                    throw new NonexistentEntityException("The circunferencia with id " + id + " no longer exists.");
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
            Circunferencia circunferencia;
            try {
                circunferencia = em.getReference(Circunferencia.class, id);
                circunferencia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The circunferencia with id " + id + " no longer exists.", enfe);
            }
            Avaliacao avaliacao = circunferencia.getAvaliacao();
            if (avaliacao != null) {
                avaliacao.setCircunferencia(null);
                avaliacao = em.merge(avaliacao);
            }
            em.remove(circunferencia);
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

    public List<Circunferencia> findCircunferenciaEntities() {
        return findCircunferenciaEntities(true, -1, -1);
    }

    public List<Circunferencia> findCircunferenciaEntities(int maxResults, int firstResult) {
        return findCircunferenciaEntities(false, maxResults, firstResult);
    }

    private List<Circunferencia> findCircunferenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Circunferencia.class));
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

    public Circunferencia findCircunferencia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Circunferencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getCircunferenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Circunferencia> rt = cq.from(Circunferencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
