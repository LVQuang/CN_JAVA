package dev.lvpq.dao;

import dev.lvpq.domain.Phone;
import dev.lvpq.repository.Repository;
import dev.lvpq.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class PhoneDAO implements Repository<Phone, Long> {
    @Override
    public Long add(Phone item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {

            session.beginTransaction();

            Long phoneID = (Long) session.save(item);

            session.getTransaction().commit();
            return phoneID;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Phone> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            List<Phone> phones = session.createQuery("FROM Phone", Phone.class).list();

            session.getTransaction().commit();

            return phones;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Phone get(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            Phone phone = session.find(Phone.class, id);

            session.getTransaction().commit();
            return phone;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Phone phone) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.update(phone);

            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(Phone item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.delete(item);

            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            Phone phone = session.find(Phone.class, id);
            session.delete(phone);

            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List<Phone> getHighestPricePhones() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Double> cr1 = cb.createQuery(Double.class);
            Root<Phone> root1 = cr1.from(Phone.class);
            cr1.select(cb.max(root1.get("price")));
            Query<Double> query1 = session.createQuery(cr1);
            Double max = query1.getSingleResult();
            System.out.println("Max price is "  + max);
            CriteriaQuery<Phone> cr2 = cb.createQuery(Phone.class);
            Root<Phone> root2 = cr2.from(Phone.class);
            cr2.select(root2).where(cb.equal(root2.get("price"), max));
            Query<Phone> query2 = session.createQuery(cr2);
            List<Phone> phoneList = query2.getResultList();

            session.getTransaction().commit();
            return phoneList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Phone> sortByCountry() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
            Root<Phone> root = cr.from(Phone.class);
            cr.orderBy(
                    cb.asc(root.get("country")),
                    cb.desc(root.get("price")));
            Query<Phone> query = session.createQuery(cr);
            List<Phone> phoneList = query.getResultList();

            session.getTransaction().commit();
            return phoneList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Phone> getPhonesHigherThanPrice(Double price) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
            Root<Phone> root = cr.from(Phone.class);
            cr.select(root).where(cb.gt(root.get("price"), price));
            Query<Phone> query = session.createQuery(cr);
            List<Phone> phoneList = query.getResultList();

            session.getTransaction().commit();
            return phoneList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Phone> getPhonesWithColorAndPrice(String color, Double price) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
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

            session.getTransaction().commit();
            return phoneList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
