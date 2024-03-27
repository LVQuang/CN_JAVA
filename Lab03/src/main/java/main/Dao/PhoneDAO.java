package main.Dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import main.Domain.Phone;
import main.Repository.Repository;
import main.Utils.HibernateUtils;


import java.util.List;

public class PhoneDAO implements Repository<Phone, Long> {

    @Override
    public Long add(Phone item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // Begin a unit of work
            session.beginTransaction();
            Long phoneID = (Long) session.save(item);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return phoneID;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Phone> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            List<Phone> phoneList = session.createQuery("FROM Phone", Phone.class).list();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return phoneList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Phone get(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            Phone phone = session.find(Phone.class, id);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return phone;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Phone item) {
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
    public boolean remove(Phone item) {
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
            Phone phone = session.find(Phone.class, id);
            session.delete(phone);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }


    public List<Phone> getHighestPricePhones() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Double> cr1 = cb.createQuery(Double.class);
            Root<Phone> root1 = cr1.from(Phone.class);
            cr1.select(cb.max(root1.get("Price")));
            Query<Double> query1 = session.createQuery(cr1);
            Double max = query1.getSingleResult();
            System.out.println("Max price is "  + max);
            CriteriaQuery<Phone> cr2 = cb.createQuery(Phone.class);
            Root<Phone> root2 = cr2.from(Phone.class);
            cr2.select(root2).where(cb.equal(root2.get("price"), max));
            Query<Phone> query2 = session.createQuery(cr2);
            List<Phone> phoneList = query2.getResultList();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return phoneList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Phone> sortByCountry() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
            Root<Phone> root = cr.from(Phone.class);
            cr.orderBy(
                    cb.asc(root.get("country")),
                    cb.desc(root.get("price")));
            Query<Phone> query = session.createQuery(cr);
            List<Phone> phoneList = query.getResultList();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return phoneList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Phone> getPhonesHigherThanPrice(Double price) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
            Root<Phone> root = cr.from(Phone.class);
            cr.select(root).where(cb.gt(root.get("price"), price));
            Query<Phone> query = session.createQuery(cr);
            List<Phone> phoneList = query.getResultList();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return phoneList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Phone> getPhonesWithColorAndPrice(String color, Double price) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
            Root<Phone> root = cr.from(Phone.class);
            Predicate[] predicates = new Predicate[2];
            predicates[0] = cb.equal(root.get("color"), color);
            predicates[1] = cb.gt(root.get("price"), price);
            cr.select(root).where(predicates);
            Query<Phone> query = session.createQuery(cr);
            List<Phone> phoneList = query.getResultList();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return phoneList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
