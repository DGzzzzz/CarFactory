package com.example.dao;

import java.util.List;

import com.example.dao.ICarroDAO;
import com.example.dao.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.example.domain.Carro;

public class CarroDAO implements ICarroDAO {
    private Session session;

    public void save(Carro carro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(carro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void update(Carro carro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(carro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Carro carro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(carro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Carro findById(int id) {
        Transaction transaction = null;
        Carro carro = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            carro = session.get(Carro.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return carro;
    }

    public List<Carro> findAll() {
        Transaction transaction = null;
        List<Carro> carros = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            carros = session.createQuery("FROM Carro").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return carros;
    }
}
