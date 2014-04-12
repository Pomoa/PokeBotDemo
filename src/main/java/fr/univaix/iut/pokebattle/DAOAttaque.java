/**
 *Package qui contient toute la BD du Pokemon ainsi que sa création.
 *
 * @since 1.0
 * @see java.awt
 */

package fr.univaix.iut.pokebattle;

import java.util.List;

public interface DAOAttaque extends DAO<Attaque, String> {
    List<Attaque> findByType(String type);
    Attaque getById(String nom);
}