package repository;

import entity.Document;
import querries.DocumentQuerries;

import javax.persistence.*;
import java.util.List;

public class DocumentRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("city_hall");

    public void insertNewDocument(Document document) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(document);
        em.getTransaction().commit();
        em.close();
    }

    public void updateDocument(Document document){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(document);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteDocument(Document document){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(document) ? document : em.merge(document));
        em.getTransaction().commit();
        em.close();
    }

    public List<Document> findAllDocuments(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Document> query=em.createQuery(DocumentQuerries.FIND_ALL_DOCUMENTS,Document.class);
        List<Document> documents=query.getResultList();
        em.close();
        return documents;
    }

    public Document findById(String id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query=em.createQuery(DocumentQuerries.FIND_DOCUMENT_BY_ID);
        query.setParameter("document_id",id);
        if(query.getResultList().size()==0){
            return null;
        }
        Document document=(Document) query.getResultList().get(0);
        em.close();
        return document;
    }

    public Document findByName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query=em.createQuery(DocumentQuerries.FIND_DOCUMENT_BY_NAME);
        query.setParameter("document_tip",name);
        if(query.getResultList().size()==0){
            return null;
        }
        Document document=(Document) query.getResultList().get(0);
        em.close();
        return document;
    }

}
