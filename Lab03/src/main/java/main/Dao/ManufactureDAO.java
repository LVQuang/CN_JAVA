package main.Dao;

import jakarta.persistence.criteria.Root;
import main.Utils.HibernateUtils;
import main.Domain.Manufacture;
import main.Repository.Repository;

import org.hibernate.Session;
import org.hibernate.query.Query;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ManufactureDAO implements Repository<Manufacture, Long> {

    @Override
    public Long add(Manufacture item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // Begin a unit of work
            session.beginTransaction();
            Long id = (Long) session.save(item);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return id;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Manufacture> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            List<Manufacture> manufacturerList = session.createQuery("FROM Manufacture", Manufacture.class).list();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return manufacturerList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Manufacture get(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            Manufacture manufacturer = session.find(Manufacture.class, id);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return manufacturer;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Manufacture item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            session.update(item);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(Manufacture item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            session.delete(item);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            Manufacture manufacturer = session.find(Manufacture.class, id);
            session.delete(manufacturer);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean allHaveMoreEmployees(Long number) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacture> cr1 = cb.createQuery(Manufacture.class);
            Root<Manufacture> root1 = cr1.from(Manufacture.class);
            cr1.select(root1).where(cb.gt(root1.get("employeeNumber"), number));
            Query<Manufacture> query1 = session.createQuery(cr1);
            List<Manufacture> manufacturerList = query1.getResultList();
            CriteriaQuery<Long> cr2 = cb.createQuery(Long.class);
            Root<Manufacture> root2 = cr2.from(Manufacture.class);
            cr2.select(cb.count(root2.get("id")));
            Query<Long> query2 = session.createQuery(cr2);
            Long manufactureNumber = query2.getSingleResult();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return manufacturerList.size() == manufactureNumber;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public Integer getTotalEmployees() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Integer> cr = cb.createQuery(Integer.class);
            Root<Manufacture> root = cr.from(Manufacture.class);
            cr.select(cb.sum(root.get("employeeNumber")));
            Query<Integer> query = session.createQuery(cr);
            Integer employeeNumber = query.getSingleResult();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return employeeNumber;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public List<Manufacture> findManufacturesByLocation(String location) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacture> cr = cb.createQuery(Manufacture.class);
            Root<Manufacture> root = cr.from(Manufacture.class);
            cr.select(root).where(cb.equal(root.get("location"), location));
            Query<Manufacture> query = session.createQuery(cr);
            List<Manufacture> manufacturerList = query.getResultList();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return manufacturerList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
