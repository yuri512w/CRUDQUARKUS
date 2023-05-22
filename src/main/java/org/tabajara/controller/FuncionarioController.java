package org.tabajara.controller;


import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.tabajara.entity.*;




@ApplicationScoped
public class FuncionarioController {

    @Inject
    private EntityManager entityManager;

    public FuncionarioController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Funcionario> listarFuncionarios() {
        return entityManager.createQuery("SELECT f FROM Funcionario f", Funcionario.class)
                .getResultList();
    }

    public Funcionario obterFuncionarioPorId(long id) {
        return entityManager.find(Funcionario.class, id);
    }

    @Transactional
    public void criarFuncionario(Funcionario funcionario) {
        entityManager.persist(funcionario);
    }

    @Transactional
    public Funcionario atualizarFuncionario(Funcionario funcionario) {
        return entityManager.merge(funcionario);
    }

    @Transactional
    public boolean excluirFuncionario(long id) {
        Funcionario funcionario = entityManager.find(Funcionario.class, id);
        if (funcionario != null) {
            entityManager.remove(funcionario);
            return true;
        }
        return false;
    }
}
