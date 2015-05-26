/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antropometria.dao;

import com.antropometria.dao.exceptions.NonexistentEntityException;
import com.antropometria.dao.exceptions.RollbackFailureException;
import com.antropometria.models.Avaliacao;
import com.antropometria.models.Professor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author anderson
 */
@Named
@RequestScoped
public class ProfessorJpaController implements Serializable {

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

    public void create(Professor professor) throws RollbackFailureException, Exception {
        if (professor.getAvaliacoes() == null) {
            professor.setAvaliacoes(new ArrayList<Avaliacao>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Avaliacao> attachedAvaliacoes = new ArrayList<Avaliacao>();
            for (Avaliacao avaliacoesAvaliacaoToAttach : professor.getAvaliacoes()) {
                avaliacoesAvaliacaoToAttach = em.getReference(avaliacoesAvaliacaoToAttach.getClass(), avaliacoesAvaliacaoToAttach.getId());
                attachedAvaliacoes.add(avaliacoesAvaliacaoToAttach);
            }
            professor.setAvaliacoes(attachedAvaliacoes);
            em.persist(professor);
            for (Avaliacao avaliacoesAvaliacao : professor.getAvaliacoes()) {
                Professor oldProfessorOfAvaliacoesAvaliacao = avaliacoesAvaliacao.getProfessor();
                avaliacoesAvaliacao.setProfessor(professor);
                avaliacoesAvaliacao = em.merge(avaliacoesAvaliacao);
                if (oldProfessorOfAvaliacoesAvaliacao != null) {
                    oldProfessorOfAvaliacoesAvaliacao.getAvaliacoes().remove(avaliacoesAvaliacao);
                    oldProfessorOfAvaliacoesAvaliacao = em.merge(oldProfessorOfAvaliacoesAvaliacao);
                }
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

    public void edit(Professor professor) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Professor persistentProfessor = em.find(Professor.class, professor.getId());
            List<Avaliacao> avaliacoesOld = persistentProfessor.getAvaliacoes();
            professor.setAvaliacoes(avaliacoesOld);
            professor = em.merge(professor);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = professor.getId();
                if (findProfessor(id) == null) {
                    throw new NonexistentEntityException("The professor with id " + id + " no longer exists.");
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
            Professor professor;
            try {
                professor = em.getReference(Professor.class, id);
                professor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The professor with id " + id + " no longer exists.", enfe);
            }
            List<Avaliacao> avaliacoes = professor.getAvaliacoes();
            for (Avaliacao avaliacoesAvaliacao : avaliacoes) {
                avaliacoesAvaliacao.setProfessor(null);
                avaliacoesAvaliacao = em.merge(avaliacoesAvaliacao);
            }
            em.remove(professor);
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

    public List<Professor> findProfessorEntities() {
        return findProfessorEntities(true, -1, -1);
    }

    public List<Professor> findProfessorEntities(int maxResults, int firstResult) {
        return findProfessorEntities(false, maxResults, firstResult);
    }

    private List<Professor> findProfessorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Professor.class));
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

    public Professor findProfessor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Professor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfessorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Professor> rt = cq.from(Professor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
