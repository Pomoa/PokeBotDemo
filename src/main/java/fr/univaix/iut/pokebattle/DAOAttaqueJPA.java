package fr.univaix.iut.pokebattle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class DAOAttaqueJPA implements DAOAttaque {

        private EntityManager entityManager;

        public DAOAttaqueJPA(final EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        public final List<Attaque> findByType(final String type) {
            TypedQuery<Attaque> query =
            entityManager.createNamedQuery(Attaque.FIND_BY_TYPE, Attaque.class);

            query.setParameter("ftype", type);
            return query.getResultList();
        }

        @Override
        public final boolean delete(final Attaque obj) {
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
        public final List<Attaque> findAll() {
            TypedQuery<Attaque> query =
               entityManager.createNamedQuery(Attaque.FIND_ALL, Attaque.class);
            return query.getResultList();
        }

        @Override
        public final Attaque getById(final String id) {
            return entityManager.find(Attaque.class, id);
        }

        @Override
        public final Attaque insert(final Attaque obj) {
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            entityManager.persist(obj);
            tx.commit();
            return entityManager.find(Attaque.class, obj.getNomAttaque());
        }

        @Override
        public final boolean update(final Attaque obj) {
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