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
import com.antropometria.models.PregasCutaneas;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author anderson
 */
@Named
@RequestScoped
public class PregasCutaneasJpaController implements Serializable {

    @Resource
    private UserTransaction utx = null;
    @RequestScoped
    private EntityManagerFactory emf = null;

    public PregasCutaneasJpaController() {
    }

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Antropometria");
        }
        return emf.createEntityManager();
    }

    public void create(PregasCutaneas pregasCutaneas) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Avaliacao avaliacao = pregasCutaneas.getAvaliacao();
            if (avaliacao != null) {
                avaliacao = em.getReference(avaliacao.getClass(), avaliacao.getId());
                pregasCutaneas.setAvaliacao(avaliacao);
            }
            em.persist(pregasCutaneas);
            if (avaliacao != null) {
                PregasCutaneas oldPregasOfAvaliacao = avaliacao.getPregas();
                if (oldPregasOfAvaliacao != null) {
                    oldPregasOfAvaliacao.setAvaliacao(null);
                    oldPregasOfAvaliacao = em.merge(oldPregasOfAvaliacao);
                }
                avaliacao.setPregas(pregasCutaneas);
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

    public void edit(PregasCutaneas pregasCutaneas) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PregasCutaneas persistentPregasCutaneas = em.find(PregasCutaneas.class, pregasCutaneas.getId());
            Avaliacao avaliacaoOld = persistentPregasCutaneas.getAvaliacao();
            Avaliacao avaliacaoNew = pregasCutaneas.getAvaliacao();
            if (avaliacaoNew != null) {
                avaliacaoNew = em.getReference(avaliacaoNew.getClass(), avaliacaoNew.getId());
                pregasCutaneas.setAvaliacao(avaliacaoNew);
            }
            pregasCutaneas = em.merge(pregasCutaneas);
            if (avaliacaoOld != null && !avaliacaoOld.equals(avaliacaoNew)) {
                avaliacaoOld.setPregas(null);
                avaliacaoOld = em.merge(avaliacaoOld);
            }
            if (avaliacaoNew != null && !avaliacaoNew.equals(avaliacaoOld)) {
                PregasCutaneas oldPregasOfAvaliacao = avaliacaoNew.getPregas();
                if (oldPregasOfAvaliacao != null) {
                    oldPregasOfAvaliacao.setAvaliacao(null);
                    oldPregasOfAvaliacao = em.merge(oldPregasOfAvaliacao);
                }
                avaliacaoNew.setPregas(pregasCutaneas);
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
                Long id = pregasCutaneas.getId();
                if (findPregasCutaneas(id) == null) {
                    throw new NonexistentEntityException("The pregasCutaneas with id " + id + " no longer exists.");
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
            PregasCutaneas pregasCutaneas;
            try {
                pregasCutaneas = em.getReference(PregasCutaneas.class, id);
                pregasCutaneas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pregasCutaneas with id " + id + " no longer exists.", enfe);
            }
            Avaliacao avaliacao = pregasCutaneas.getAvaliacao();
            if (avaliacao != null) {
                avaliacao.setPregas(null);
                avaliacao = em.merge(avaliacao);
            }
            em.remove(pregasCutaneas);
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

    public List<PregasCutaneas> findPregasCutaneasEntities() {
        return findPregasCutaneasEntities(true, -1, -1);
    }

    public List<PregasCutaneas> findPregasCutaneasEntities(int maxResults, int firstResult) {
        return findPregasCutaneasEntities(false, maxResults, firstResult);
    }

    private List<PregasCutaneas> findPregasCutaneasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PregasCutaneas.class));
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

    public PregasCutaneas findPregasCutaneas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PregasCutaneas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPregasCutaneasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PregasCutaneas> rt = cq.from(PregasCutaneas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
