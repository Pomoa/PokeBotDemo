package fr.univaix.iut.pokebattle;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
        @NamedQuery(name = Attaque.FIND_ALL, query = "SELECT a FROM Attaque a"),
        @NamedQuery(name = Attaque.FIND_BY_TYPE,
             query = "SELECT a FROM Attaque a WHERE a.TypeAttaque = :ftype"),
        @NamedQuery(name = Attaque.FIND_BY_NOM,
             query = "SELECT a FROM Attaque a WHERE a.nomAttaque = :fnom")
})

public class Attaque {

    public static final String FIND_BY_TYPE = "findAttaqueByType";
    public static final String FIND_ALL = "findAllAttaque";
    public static final String FIND_BY_NOM = "findAttaqueByNom";

    @Id
    private String nomAttaque;

    /* @Enumerated(EnumType.STRING) */
    private String TypeAttaque;

    private int Pp;
    private int Puissance;
    private int Precis;

    public Attaque() {

    }

    public final String getNomAttaque() {
        return nomAttaque;
    }
    public final void setNomAttaque(final String nom_Attaque) {
        nomAttaque = nom_Attaque;
    }
    public final String getTypeAttaque() {
            return TypeAttaque;
    }
    public final void setTypeAttaque(final String typeAttaque) {
        TypeAttaque = typeAttaque;
    }
    public final int getPp() {
        return Pp;
    }
    public final void setPp(final int pp) {
        Pp = pp;
    }
    public final int getPuissance() {
        return Puissance;
    }
    public final void setPuissance(final int puissance) {
        Puissance = puissance;
    }
    public final int getPrecision() {
        return Precis;
    }
    public final void setPrecision(final int precision) {
        Precis = precision;
    }
    @Override
    public final String toString() {
        return "Attaque [NomAttaque=" + nomAttaque + ", TypeAttaque="
                + TypeAttaque + ", Pp=" + Pp + ", Puissance=" + Puissance
                + ", Precision=" + Precis + "]";
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
        result = prime * result + Pp;
        result = prime * result + Precis;
        result = prime * result + Puissance;
        if (TypeAttaque == null) {
            result = prime * result;
        } else {
            result = prime * result + TypeAttaque.hashCode();
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
        if (Pp != other.Pp) {
            return false;
        }
        if (Precis != other.Precis) {
            return false;
        }
        if (Puissance != other.Puissance) {
            return false;
        }
        if (TypeAttaque == null) {
            if (other.TypeAttaque != null) {
                return false;
            }
        } else if (!TypeAttaque.equals(other.TypeAttaque)) {
            return false;
        }
        return true;
}


} // Attaque()
