/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ichag.simplejpa;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
 
public class StartMe {
 
    private EntityManagerFactory emFactoryObj;
    private String PERSISTENCE_UNIT_NAME = "OJVMUnit";  
    long t0,t1 ;
 
 
    // This Method Is Used To Retrieve The 'EntityManager' Object
    public EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }
 
    public static void main(String[] args) {
        System.out.println("loading persistence unit");
        StartMe start = new StartMe();
        start.emFactoryObj = Persistence.createEntityManagerFactory(start.PERSISTENCE_UNIT_NAME);

        EntityManager em = start.emFactoryObj.createEntityManager();
        //em.getTransaction().begin();
        start.t0=System.currentTimeMillis(); 
        Long result = null;
        
        try {
            
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Workers> rt = cq.from(Workers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            //System.out.println (((Long)q.getSingleResult()).toString());
            result = ((Long)q.getSingleResult());
        
        } finally {
            em.close();
        } 
        start.t1=System.currentTimeMillis(); 
        System.out.println ("====> SINGLE SELECT Duration: "+(int)(start.t1-start.t0)+ " Milliseconds");
        
        em = start.emFactoryObj.createEntityManager();
        //em.getTransaction().begin();
        start.t0=System.currentTimeMillis(); 
        
        try {
            for (int i=0;i<1000;i++) {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Workers> rt = cq.from(Workers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            //System.out.println (((Long)q.getSingleResult()).toString());
            result = ((Long)q.getSingleResult());
        }
        } finally {
            em.close();
        } 
        
        em = start.emFactoryObj.createEntityManager();
        start.t1=System.currentTimeMillis(); 
        System.out.println ("====> 1000 SELECT Duration: "+(int)(start.t1-start.t0)+ " Milliseconds");
        Workers worker = null;
        start.t0=System.currentTimeMillis(); 
        try {
            for (int i=0;i<1000;i++) {
              em.getTransaction().begin();
              worker = em.find(Workers.class, new java.math.BigDecimal(201));
              worker.setWname("John Doe"+i);
	      em.persist(worker);
	      em.getTransaction().commit();
            }
        } finally {
            em.close();
        } 
        start.t1=System.currentTimeMillis(); 
        System.out.println ("====> 1000 UPDATE+COMMIT Duration: "+(int)(start.t1-start.t0)+ " Milliseconds");

        //em.getTransaction().commit();
 
        //em.clear();
    }
}