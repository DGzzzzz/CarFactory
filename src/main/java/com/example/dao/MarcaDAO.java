package com.example.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.domain.Marca;

public class MarcaDAO implements IMarcaDAO {

    @Override
    public void save(Marca marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(marca);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Marca marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(marca);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Marca marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(marca);
        transaction.commit();
        session.close();
    }

    @Override
    public Marca findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Marca> cq = cb.createQuery(Marca.class);
        Root<Marca> root = cq.from(Marca.class);
        cq.select(root).where(cb.equal(root.get("id"), id));
        TypedQuery<Marca> query = session.createQuery(cq);
        Marca marca = null;
        try{
            marca = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Nenhum resultado encontrado para a query");
        } finally {
            session.close();
        }
        return marca;
    }
    

    @Override
    public List<Marca> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Marca> cq = cb.createQuery(Marca.class);
        Root<Marca> root = cq.from(Marca.class);
        cq.select(root);
        TypedQuery<Marca> query = session.createQuery(cq);
        List<Marca> marcas = query.getResultList();
        session.close();
        return marcas;
    }
}