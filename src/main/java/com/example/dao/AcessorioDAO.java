package com.example.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.domain.Acessorio;

public class AcessorioDAO implements IAcessorioDAO {
    @Override
    public void save(Acessorio acessorio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(acessorio);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Acessorio acessorio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(acessorio);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Acessorio acessorio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(acessorio);
        transaction.commit();
        session.close();
    }

    @Override
    public Acessorio findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Acessorio> cq = cb.createQuery(Acessorio.class);
        Root<Acessorio> root = cq.from(Acessorio.class);
        cq.select(root).where(cb.equal(root.get("id"), id));
        TypedQuery<Acessorio> query = session.createQuery(cq);
        Acessorio acessorio = null;
        try {
            acessorio = query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Nenhum resultado encontrado para a query");
        } finally {
            session.close();
        }
        return acessorio;   
    }

    @Override
    public List<Acessorio> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Acessorio> cq = cb.createQuery(Acessorio.class);
        Root<Acessorio> root = cq.from(Acessorio.class);
        cq.select(root);
        TypedQuery<Acessorio> query = session.createQuery(cq);
        List<Acessorio> acessorios = query.getResultList();
        session.close();
        return acessorios;
    }
}
