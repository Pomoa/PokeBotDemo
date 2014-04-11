package fr.univaix.iut.pokebattle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class DAOPokemonJPA implements DAOPokemon {

    private EntityManager entityManager;

    public DAOPokemonJPA(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public final List<Pokemon> findByType(final Type type) {
        TypedQuery<Pokemon> query =
            entityManager.createNamedQuery(Pokemon.FIND_BY_TYPE, Pokemon.class);
        query.setParameter("ftype", type);
        return query.getResultList();
    }

    @Override
    public final boolean delete(final Pokemon obj) {
        try {
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            entityManager.remove(obj);
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public final List<Pokemon> findAll() {
        TypedQuery<Pokemon> query = entityManager.
                createNamedQuery(Pokemon.FIND_ALL, Pokemon.class);
        return query.getResultList();
    }

    @Override
    public final Pokemon getById(final String id) {
        return entityManager.find(Pokemon.class, id);
    }

    @Override
    public final Pokemon insert(final Pokemon obj) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(obj);
        tx.commit();
        return entityManager.find(Pokemon.class, obj.getName());
    }

    @Override
    public final boolean update(final Pokemon obj) {
        try {
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(obj);
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}