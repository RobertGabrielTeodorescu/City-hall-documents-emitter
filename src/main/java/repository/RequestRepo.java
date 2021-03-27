package repository;

import entity.Address;
import entity.Document;
import entity.Request;
import querries.RequestQuerries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("city_hall");

    public void insertNewRequest(Request request) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(request);
        em.getTransaction().commit();
        em.close();
    }

    public void updateRequest(Request request){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(request);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteRequest(Request request){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(request) ? request : em.merge(request));
        em.getTransaction().commit();
        em.close();
    }

    public Request findById(String id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Request request=em.find(Request.class,id);
        em.getTransaction().commit();
        em.close();
        return request;
    }

    public List<Request> findRequestsByAddressDocumentYear(Address address, Document document, LocalDateTime localDateTime){
        List<Request> requests=new ArrayList<>();
        List<Request> allRequests=findAllRequests();
        for(Request request:allRequests){
            if(request.getAddress().getId().equals(address.getId())&&request.getDocument().getId().equals(document.getId())&&request.getRequestTime().getYear()==localDateTime.getYear()){
                requests.add(request);
            }
        }
        return requests;
    }

    public List<Request> findAllRequests(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Request> query=em.createQuery(RequestQuerries.FIND_ALL_REQUESTS,Request.class);
        List<Request> requests=query.getResultList();
        em.close();
        return requests;
    }

}
