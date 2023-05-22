package org.tabajara.entity;
import jakarta.enterprise.context.*;


import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Produces;



@ApplicationScoped
public class PersistenceUtil {

    @PersistenceContext(unitName = "cadastrodefuncionarios")
    private PersistenceUtil entityManager;

    @Produces
    public PersistenceUtil produceEntityManager() {
        return entityManager;
    }
}
