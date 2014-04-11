package fr.univaix.iut.pokebattle;

import java.util.List;

public interface DAOAttaque extends DAO<Attaque, String> {
    List<Attaque> findByType(String type);
    Attaque getById(String nom);
}