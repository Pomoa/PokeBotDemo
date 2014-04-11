package fr.univaix.iut.pokebattle;

import java.util.List;

public interface DAOPokemon extends DAO<Pokemon, String> {
    List<Pokemon> findByType(Type type1);
    Pokemon getById(String nom);
}
