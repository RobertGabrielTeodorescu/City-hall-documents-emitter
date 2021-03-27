package repository;

import entity.Address;
import querries.AddressQuerries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AddressRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("city_hall");

    public void insertNewAddress(Address address) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(address);
        em.getTransaction().commit();
        em.close();
    }

    public void updateAddress(Address address){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(address);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteAddress(Address address){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(address) ? address : em.merge(address));
        em.getTransaction().commit();
        em.close();
    }

    public Address findById(String id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query=em.createQuery(AddressQuerries.FIND_ADDRESS_BY_ID);
        query.setParameter("address_id",id);
        if(query.getResultList().size()==0){
            return null;
        }
        Address address=(Address) query.getResultList().get(0);
        em.close();
        return address;
    }

}
