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
import com.antropometria.models.Osseo;
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
public class OsseoJpaController implements Serializable {

    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(name = "Antropometria")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Antropometria");
        }
        return emf.createEntityManager();
    }

    public void create(Osseo osseo) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Avaliacao avaliacao = osseo.getAvaliacao();
            if (avaliacao != null) {
                avaliacao = em.getReference(avaliacao.getClass(), avaliacao.getId());
                osseo.setAvaliacao(avaliacao);
            }
            em.persist(osseo);
            if (avaliacao != null) {
                Osseo oldOsseoOfAvaliacao = avaliacao.getOsseo();
                if (oldOsseoOfAvaliacao != null) {
                    oldOsseoOfAvaliacao.setAvaliacao(null);
                    oldOsseoOfAvaliacao = em.merge(oldOsseoOfAvaliacao);
                }
                avaliacao.setOsseo(osseo);
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

    public void edit(Osseo osseo) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Osseo persistentOsseo = em.find(Osseo.class, osseo.getId());
            Avaliacao avaliacaoOld = persistentOsseo.getAvaliacao();
            Avaliacao avaliacaoNew = osseo.getAvaliacao();
            if (avaliacaoNew != null) {
                avaliacaoNew = em.getReference(avaliacaoNew.getClass(), avaliacaoNew.getId());
                osseo.setAvaliacao(avaliacaoNew);
            }
            osseo = em.merge(osseo);
            if (avaliacaoOld != null && !avaliacaoOld.equals(avaliacaoNew)) {
                avaliacaoOld.setOsseo(null);
                avaliacaoOld = em.merge(avaliacaoOld);
            }
            if (avaliacaoNew != null && !avaliacaoNew.equals(avaliacaoOld)) {
                Osseo oldOsseoOfAvaliacao = avaliacaoNew.getOsseo();
                if (oldOsseoOfAvaliacao != null) {
                    oldOsseoOfAvaliacao.setAvaliacao(null);
                    oldOsseoOfAvaliacao = em.merge(oldOsseoOfAvaliacao);
                }
                avaliacaoNew.setOsseo(osseo);
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
                Long id = osseo.getId();
                if (findOsseo(id) == null) {
                    throw new NonexistentEntityException("The osseo with id " + id + " no longer exists.");
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
            Osseo osseo;
            try {
                osseo = em.getReference(Osseo.class, id);
                osseo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The osseo with id " + id + " no longer exists.", enfe);
            }
            Avaliacao avaliacao = osseo.getAvaliacao();
            if (avaliacao != null) {
                avaliacao.setOsseo(null);
                avaliacao = em.merge(avaliacao);
            }
            em.remove(osseo);
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

    public List<Osseo> findOsseoEntities() {
        return findOsseoEntities(true, -1, -1);
    }

    public List<Osseo> findOsseoEntities(int maxResults, int firstResult) {
        return findOsseoEntities(false, maxResults, firstResult);
    }

    private List<Osseo> findOsseoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Osseo.class));
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

    public Osseo findOsseo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Osseo.class, id);
        } finally {
            em.close();
        }
    }

    public int getOsseoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Osseo> rt = cq.from(Osseo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
