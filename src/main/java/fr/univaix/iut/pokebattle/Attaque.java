/**
 *Package qui contient toute la BD du Pokemon ainsi que sa création.
 *
 * @since 1.0
 * @see java.awt
 */

package fr.univaix.iut.pokebattle;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * @author capybaradesalpes
 * @Entity
 *
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Attaque.FIND_ALL, query = "SELECT a FROM Attaque a"),
        @NamedQuery(name = Attaque.FIND_BY_TYPE,
             query = "SELECT a FROM Attaque a WHERE a.typeAttaque = :ftype"),
        @NamedQuery(name = Attaque.FIND_BY_NOM,
             query = "SELECT a FROM Attaque a WHERE a.nomAttaque = :fnom")
})

public class Attaque {

    /**
     *@param String FIND_BY_TYPE
     *@return Toutes les attaques du même type
     *
     */

    public static final String FIND_BY_TYPE = "findAttaqueByType";

    /**
     *@param String FIND_ALL
     *@return Toutes les attaques
     */

    public static final String FIND_ALL = "findAllAttaque";

    /**
     *@param String FIND_BY_NOM
     *@return Un attaque
     *
     */

    public static final String FIND_BY_NOM = "findAttaqueByNom";

    /**
     *@param Id
     *@return Id (Nom de l'attaque)
     *
     */

    @Id
    private String nomAttaque;


    /**
     *@param typeAttaque
     *@return typeAttaque
     *@Enumerated(EnumType.STRING)
     *
     */
    private String typeAttaque;

    /**
     *@param pp
     *@return pp
     *
     */
    private int pp;

    /**
     *@param puissance
     *@return puissance
     *
     */
    private int puissance;

    /**
     *@param precision
     *@return precision
     *
     */
    private int precis;

    /**
     * Constructeur par défaut d'Attaque.
     *@param
     *@return
     *
     */

    public Attaque() {

    }

    /**
     *@param
     *@return nomAttaque
     *
     */
    public final String getNomAttaque() {
        return nomAttaque;
    }

    /**
     * @param nomattaque
     */
    public final void setNomAttaque(final String nomattaque) {
        this.nomAttaque = nomattaque;
    }

    /**
     * @return typeAttaque
     */
    public final String getTypeAttaque() {
            return typeAttaque;
    }

    /**
     * @param typeAttaque
     */
    public final void setTypeAttaque(final String typeattaque) {
        this.typeAttaque = typeattaque;
    }

    /**
     * @return pp
     */
    public final int getPp() {
        return pp;
    }

    /**
     * @param thepp
     */
    public final void setPp(final int thepp) {
        this.pp = thepp;
    }

    /**
     * @return puissance
     */
    public final int getPuissance() {
        return puissance;
    }

    /**
     * @param puissance
     */
    public final void setPuissance(final int puissance) {
        this.puissance = puissance;
    }

    /**
     * @return precis
     */
    public final int getPrecision() {
        return precis;
    }

    /**
     * @param precision
     */
    public final void setPrecision(final int precision) {
        precis = precision;
    }
    @Override
    public final String toString() {
        return "Attaque [NomAttaque=" + nomAttaque + ", TypeAttaque="
                + typeAttaque + ", Pp=" + pp + ", Puissance=" + puissance
                + ", Precision=" + precis + "]";
    }
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        if (nomAttaque == null) {
            result = prime * result;
        } else {
            result = prime * result + nomAttaque.hashCode();
        }
        result = prime * result + pp;
        result = prime * result + precis;
        result = prime * result + puissance;
        if (typeAttaque == null) {
            result = prime * result;
        } else {
            result = prime * result + typeAttaque.hashCode();
        }
        return result;
    }
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Attaque other = (Attaque) obj;
        if (nomAttaque == null) {
            if (other.nomAttaque != null) {
                return false;
            }
        } else if (!nomAttaque.equals(other.nomAttaque)) {
            return false;
        }
        if (pp != other.pp) {
            return false;
        }
        if (precis != other.precis) {
            return false;
        }
        if (puissance != other.puissance) {
            return false;
        }
        if (typeAttaque == null) {
            if (other.typeAttaque != null) {
                return false;
            }
        } else if (!typeAttaque.equals(other.typeAttaque)) {
            return false;
        }
        return true;
}


} // Attaque()
