/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antropometria.dao;

import com.antropometria.dao.exceptions.NonexistentEntityException;
import com.antropometria.dao.exceptions.RollbackFailureException;
import com.antropometria.models.Avaliacao;
import com.antropometria.models.Paciente;
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
public class PacienteJpaController implements Serializable {

    @Resource
    private UserTransaction utx = null;

    @PersistenceUnit(name = "Antropometria")
    private EntityManagerFactory emf = null;

    public PacienteJpaController() {
    }

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Antropometria");
        }
        return emf.createEntityManager();
    }

    public void create(Paciente paciente) throws RollbackFailureException, Exception {
        if (paciente.getAvaliacoes() == null) {
            paciente.setAvaliacoes(new ArrayList<Avaliacao>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Avaliacao> attachedAvaliacoes = new ArrayList<Avaliacao>();
            for (Avaliacao avaliacoesAvaliacaoToAttach : paciente.getAvaliacoes()) {
                avaliacoesAvaliacaoToAttach = em.getReference(avaliacoesAvaliacaoToAttach.getClass(), avaliacoesAvaliacaoToAttach.getId());
                attachedAvaliacoes.add(avaliacoesAvaliacaoToAttach);
            }
            paciente.setAvaliacoes(attachedAvaliacoes);
            em.persist(paciente);
            for (Avaliacao avaliacoesAvaliacao : paciente.getAvaliacoes()) {
                Paciente oldPacienteOfAvaliacoesAvaliacao = avaliacoesAvaliacao.getPaciente();
                avaliacoesAvaliacao.setPaciente(paciente);
                avaliacoesAvaliacao = em.merge(avaliacoesAvaliacao);
                if (oldPacienteOfAvaliacoesAvaliacao != null) {
                    oldPacienteOfAvaliacoesAvaliacao.getAvaliacoes().remove(avaliacoesAvaliacao);
                    oldPacienteOfAvaliacoesAvaliacao = em.merge(oldPacienteOfAvaliacoesAvaliacao);
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

    public void edit(Paciente paciente) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getId());
            List<Avaliacao> avaliacoesOld = persistentPaciente.getAvaliacoes();
            paciente.setAvaliacoes(avaliacoesOld);
            paciente = em.merge(paciente);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = paciente.getId();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
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
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            List<Avaliacao> avaliacoes = paciente.getAvaliacoes();
            for (Avaliacao avaliacoesAvaliacao : avaliacoes) {
                avaliacoesAvaliacao.setPaciente(null);
                avaliacoesAvaliacao = em.merge(avaliacoesAvaliacao);
            }
            em.remove(paciente);
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

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
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

    public Paciente findPaciente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
