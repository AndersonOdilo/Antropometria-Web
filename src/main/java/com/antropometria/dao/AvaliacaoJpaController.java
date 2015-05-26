/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antropometria.dao;

import com.antropometria.dao.exceptions.NonexistentEntityException;
import com.antropometria.dao.exceptions.RollbackFailureException;
import com.antropometria.models.Avaliacao;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.antropometria.models.Paciente;
import com.antropometria.models.Professor;
import com.antropometria.models.Circunferencia;
import com.antropometria.models.Osseo;
import com.antropometria.models.PregasCutaneas;
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
public class AvaliacaoJpaController implements Serializable {

    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(name = "Antropometria")
    private EntityManagerFactory emf = null;

    public AvaliacaoJpaController() {
    }

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Antropometria");
        }
        return emf.createEntityManager();
    }

    public void create(Avaliacao avaliacao) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Paciente paciente = avaliacao.getPaciente();
            if (paciente != null) {
                paciente = em.getReference(paciente.getClass(), paciente.getId());
                avaliacao.setPaciente(paciente);
            }
            Professor professor = avaliacao.getProfessor();
            if (professor != null) {
                professor = em.getReference(professor.getClass(), professor.getId());
                avaliacao.setProfessor(professor);
            em.persist(avaliacao);
            }
            if (paciente != null) {
                paciente.getAvaliacoes().add(avaliacao);
                paciente = em.merge(paciente);
            }
            if (professor != null) {
                professor.getAvaliacoes().add(avaliacao);
                professor = em.merge(professor);
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

    public void edit(Avaliacao avaliacao) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Avaliacao persistentAvaliacao = em.find(Avaliacao.class, avaliacao.getId());
            Paciente pacienteOld = persistentAvaliacao.getPaciente();
            Paciente pacienteNew = avaliacao.getPaciente();
            Professor professorOld = persistentAvaliacao.getProfessor();
            Professor professorNew = avaliacao.getProfessor();
            Circunferencia circunferenciaOld = persistentAvaliacao.getCircunferencia();
            Circunferencia circunferenciaNew = avaliacao.getCircunferencia();
            Osseo osseoOld = persistentAvaliacao.getOsseo();
            Osseo osseoNew = avaliacao.getOsseo();
            PregasCutaneas pregasOld = persistentAvaliacao.getPregas();
            PregasCutaneas pregasNew = avaliacao.getPregas();
            Bioimpedancia bioimpedanciaOld = persistentAvaliacao.getBioimpedancia();
            Bioimpedancia bioimpedanciaNew = avaliacao.getBioimpedancia();
            if (pacienteNew != null) {
                pacienteNew = em.getReference(pacienteNew.getClass(), pacienteNew.getId());
                avaliacao.setPaciente(pacienteNew);
            }
            if (professorNew != null) {
                professorNew = em.getReference(professorNew.getClass(), professorNew.getId());
                avaliacao.setProfessor(professorNew);
            }
            if (circunferenciaNew != null) {
                circunferenciaNew = em.getReference(circunferenciaNew.getClass(), circunferenciaNew.getId());
                avaliacao.setCircunferencia(circunferenciaNew);
            }
            if (osseoNew != null) {
                osseoNew = em.getReference(osseoNew.getClass(), osseoNew.getId());
                avaliacao.setOsseo(osseoNew);
            }
            if (pregasNew != null) {
                pregasNew = em.getReference(pregasNew.getClass(), pregasNew.getId());
                avaliacao.setPregas(pregasNew);
            }
            if (bioimpedanciaNew != null) {
                bioimpedanciaNew = em.getReference(bioimpedanciaNew.getClass(), bioimpedanciaNew.getId());
                avaliacao.setBioimpedancia(bioimpedanciaNew);
            }
            avaliacao = em.merge(avaliacao);
            if (pacienteOld != null && !pacienteOld.equals(pacienteNew)) {
                pacienteOld.getAvaliacoes().remove(avaliacao);
                pacienteOld = em.merge(pacienteOld);
            }
            if (pacienteNew != null && !pacienteNew.equals(pacienteOld)) {
                pacienteNew.getAvaliacoes().add(avaliacao);
                pacienteNew = em.merge(pacienteNew);
            }
            if (professorOld != null && !professorOld.equals(professorNew)) {
                professorOld.getAvaliacoes().remove(avaliacao);
                professorOld = em.merge(professorOld);
            }
            if (professorNew != null && !professorNew.equals(professorOld)) {
                professorNew.getAvaliacoes().add(avaliacao);
                professorNew = em.merge(professorNew);
            }
            if (circunferenciaOld != null && !circunferenciaOld.equals(circunferenciaNew)) {
                circunferenciaOld.setAvaliacao(null);
                circunferenciaOld = em.merge(circunferenciaOld);
            }
            if (circunferenciaNew != null && !circunferenciaNew.equals(circunferenciaOld)) {
                Avaliacao oldAvaliacaoOfCircunferencia = circunferenciaNew.getAvaliacao();
                if (oldAvaliacaoOfCircunferencia != null) {
                    oldAvaliacaoOfCircunferencia.setCircunferencia(null);
                    oldAvaliacaoOfCircunferencia = em.merge(oldAvaliacaoOfCircunferencia);
                }
                circunferenciaNew.setAvaliacao(avaliacao);
                circunferenciaNew = em.merge(circunferenciaNew);
            }
            if (osseoOld != null && !osseoOld.equals(osseoNew)) {
                osseoOld.setAvaliacao(null);
                osseoOld = em.merge(osseoOld);
            }
            if (osseoNew != null && !osseoNew.equals(osseoOld)) {
                Avaliacao oldAvaliacaoOfOsseo = osseoNew.getAvaliacao();
                if (oldAvaliacaoOfOsseo != null) {
                    oldAvaliacaoOfOsseo.setOsseo(null);
                    oldAvaliacaoOfOsseo = em.merge(oldAvaliacaoOfOsseo);
                }
                osseoNew.setAvaliacao(avaliacao);
                osseoNew = em.merge(osseoNew);
            }
            if (pregasOld != null && !pregasOld.equals(pregasNew)) {
                pregasOld.setAvaliacao(null);
                pregasOld = em.merge(pregasOld);
            }
            if (pregasNew != null && !pregasNew.equals(pregasOld)) {
                Avaliacao oldAvaliacaoOfPregas = pregasNew.getAvaliacao();
                if (oldAvaliacaoOfPregas != null) {
                    oldAvaliacaoOfPregas.setPregas(null);
                    oldAvaliacaoOfPregas = em.merge(oldAvaliacaoOfPregas);
                }
                pregasNew.setAvaliacao(avaliacao);
                pregasNew = em.merge(pregasNew);
            }
            if (bioimpedanciaOld != null && !bioimpedanciaOld.equals(bioimpedanciaNew)) {
                bioimpedanciaOld.setAvaliacao(null);
                bioimpedanciaOld = em.merge(bioimpedanciaOld);
            }
            if (bioimpedanciaNew != null && !bioimpedanciaNew.equals(bioimpedanciaOld)) {
                Avaliacao oldAvaliacaoOfBioimpedancia = bioimpedanciaNew.getAvaliacao();
                if (oldAvaliacaoOfBioimpedancia != null) {
                    oldAvaliacaoOfBioimpedancia.setBioimpedancia(null);
                    oldAvaliacaoOfBioimpedancia = em.merge(oldAvaliacaoOfBioimpedancia);
                }
                bioimpedanciaNew.setAvaliacao(avaliacao);
                bioimpedanciaNew = em.merge(bioimpedanciaNew);
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
                Long id = avaliacao.getId();
                if (findAvaliacao(id) == null) {
                    throw new NonexistentEntityException("The avaliacao with id " + id + " no longer exists.");
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
            Avaliacao avaliacao;
            try {
                avaliacao = em.getReference(Avaliacao.class, id);
                avaliacao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The avaliacao with id " + id + " no longer exists.", enfe);
            }
            Paciente paciente = avaliacao.getPaciente();
            if (paciente != null) {
                paciente.getAvaliacoes().remove(avaliacao);
                paciente = em.merge(paciente);
            }
            Professor professor = avaliacao.getProfessor();
            if (professor != null) {
                professor.getAvaliacoes().remove(avaliacao);
                professor = em.merge(professor);
            }
            Circunferencia circunferencia = avaliacao.getCircunferencia();
            if (circunferencia != null) {
                circunferencia.setAvaliacao(null);
                circunferencia = em.merge(circunferencia);
            }
            Osseo osseo = avaliacao.getOsseo();
            if (osseo != null) {
                osseo.setAvaliacao(null);
                osseo = em.merge(osseo);
            }
            PregasCutaneas pregas = avaliacao.getPregas();
            if (pregas != null) {
                pregas.setAvaliacao(null);
                pregas = em.merge(pregas);
            }
            Bioimpedancia bioimpedancia = avaliacao.getBioimpedancia();
            if (bioimpedancia != null) {
                bioimpedancia.setAvaliacao(null);
                bioimpedancia = em.merge(bioimpedancia);
            }
            em.remove(avaliacao);
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

    public List<Avaliacao> findAvaliacaoEntities() {
        return findAvaliacaoEntities(true, -1, -1);
    }

    public List<Avaliacao> findAvaliacaoEntities(int maxResults, int firstResult) {
        return findAvaliacaoEntities(false, maxResults, firstResult);
    }

    private List<Avaliacao> findAvaliacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Avaliacao.class));
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

    public Avaliacao findAvaliacao(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Avaliacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getAvaliacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Avaliacao> rt = cq.from(Avaliacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
