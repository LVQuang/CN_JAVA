package dev.lvpq.dao;

import dev.lvpq.domain.Manufacturer;
import dev.lvpq.repository.Repository;
import dev.lvpq.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ManufacturerDAO implements Repository<Manufacturer, Long> {
    @Override
    public Long add(Manufacturer item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            Long id = (Long) session.save(item);

            session.getTransaction().commit();
            return id;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Manufacturer> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            List<Manufacturer> manufacturers = session
                    .createQuery("FROM Manufacturer", Manufacturer.class).list();

            session.getTransaction().commit();
            return manufacturers;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Manufacturer get(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            Manufacturer manufacturer = session.find(Manufacturer.class, id);

            session.getTransaction().commit();
            return manufacturer;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Manufacturer manufacturer) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.update(manufacturer);

            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(Manufacturer item) {
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

            Manufacturer manufacturer = session.find(Manufacturer.class, id);

            session.delete(manufacturer);

            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean allHaveMoreEmployees(Long number) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacturer> cr1 = cb.createQuery(Manufacturer.class);
            Root<Manufacturer> root1 = cr1.from(Manufacturer.class);
            cr1.select(root1).where(cb.gt(root1.get("employeeNumber"), number));
            Query<Manufacturer> query1 = session.createQuery(cr1);
            List<Manufacturer> manufacturerList = query1.getResultList();
            CriteriaQuery<Long> cr2 = cb.createQuery(Long.class);
            Root<Manufacturer> root2 = cr2.from(Manufacturer.class);
            cr2.select(cb.count(root2.get("id")));
            Query<Long> query2 = session.createQuery(cr2);
            Long manufactureNumber = query2.getSingleResult();
            session.getTransaction().commit();
            return manufacturerList.size() == manufactureNumber;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public Integer getTotalEmployees() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {

            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Integer> cr = cb.createQuery(Integer.class);
            Root<Manufacturer> root = cr.from(Manufacturer.class);
            cr.select(cb.sum(root.get("employeeNumber")));
            Query<Integer> query = session.createQuery(cr);
            Integer employeeNumber = query.getSingleResult();

            session.getTransaction().commit();
            return employeeNumber;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public List<Manufacturer> findManufacturesByLocation(String location) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacturer> cr = cb.createQuery(Manufacturer.class);
            Root<Manufacturer> root = cr.from(Manufacturer.class);
            cr.select(root).where(cb.equal(root.get("location"), location));
            Query<Manufacturer> query = session.createQuery(cr);
            List<Manufacturer> manufacturerList = query.getResultList();

            session.getTransaction().commit();
            return manufacturerList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
