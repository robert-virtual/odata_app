package com.example.odata_app.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.http.HttpServletRequest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public  class EntityManagerFilter  implements ContainerRequestFilter, ContainerResponseFilter {
    public static final  String EM_REQUEST_ATTRIBUTE = EntityManagerFilter.class.getName()+"_ENTITY_MANAGER";
    private final EntityManagerFactory emf;
    public  EntityManagerFilter(EntityManagerFactory emf){
       this.emf = emf;
    }
    @Context
    private HttpServletRequest httpRequest;
    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        EntityManager em = this.emf.createEntityManager();
        httpRequest.setAttribute(EM_REQUEST_ATTRIBUTE,em);
        if (!"GET".equalsIgnoreCase(ctx.getMethod())) em.getTransaction().begin();
    }

    @Override
    public void filter(
            ContainerRequestContext requestContext,
            ContainerResponseContext responseContext
    ) throws IOException {
        EntityManager em = this.emf.createEntityManager();
        if (!"GET".equalsIgnoreCase(requestContext.getMethod())) {
            EntityTransaction t = em.getTransaction();
            if (t.isActive() && !t.getRollbackOnly()) t.commit();
        }
        em.close();

    }
}
