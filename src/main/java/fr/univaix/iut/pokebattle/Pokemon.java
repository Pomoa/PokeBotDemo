package fr.univaix.iut.pokebattle;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;




@Entity
@NamedQueries({
        @NamedQuery(name = Pokemon.FIND_ALL, query = "SELECT p FROM Pokemon p"),
        @NamedQuery(name = Pokemon.FIND_BY_NOM,
                    query = "SELECT p FROM Pokemon p WHERE p.name = :fnom"),
        @NamedQuery(name = Pokemon.FIND_BY_TYPE,
                    query = "SELECT p FROM Pokemon p WHERE p.type1 = :ftype")
        })

public class Pokemon {

    public static final String FIND_BY_TYPE = "findPokemonByType";
    public static final String FIND_ALL = "findAllPokemon";
    public static final String FIND_BY_NOM = "findPokemonByNom";

    @Id
    private String name;

    @ManyToMany
    private List<Attaque> attaques;

    @Enumerated(EnumType.STRING)
    private Type type1;


    public final Type getType1() {
        return type1;
    }

    public final void setType1(final Type types1) {
        this.type1 = types1;
    }



    private String Color;
    private String Owner;




    private int XP;
    private int Level;
    private int PVMax;
    private int PVNow;


    private float Height;
    private float Weight;

    public Pokemon() {
        // TODO Auto-generated constructor stub
    }

    // Fonction qui renvoie vrai si le pokemon n'a pas de maître.
    public final boolean IsSauvage(final Pokemon pokemon) {

        if (pokemon.getOwner() == null) {
            return true;
        }
        return false;
    } //IsSauvage


    // Getteurs & Setteurs


    public final List<Attaque> getAttaques() {
        return attaques;
    }

    public final boolean isAttackOf(final String attack) {
        for (int i = 0; i < attaques.size(); i++) {
            if (attaques.get(i).getNomAttaque().toLowerCase()
                    .equals(attack.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public final boolean isAttackOf(final Attaque attack) {
        for (int i = 0; i < attaques.size(); i++) {
            if (attaques.get(i).getNomAttaque()
                    .equals(attack.getNomAttaque())) {
                return true;
            }
        }
        return false;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String nom) {
        name = nom;
    }

    public final int getPVMax() {
        return PVMax;
    }

    public final void setPVMax(final int pVMax) {
        PVMax = pVMax;
    }

    public final int getPVNow() {
        return PVNow;
    }

    public final void setPVNow(final int pVNow) {
        PVNow = pVNow;
    }

    public final void setAttaques(final List<Attaque> attaques) {
        this.attaques = attaques;
    }


    public final String getOwner() {
        return Owner;
    }

    public final void setOwner(final String owner) {
        Owner = owner;
    }

    public final String getColor() {
        return Color;
    }

    public final void setColor(final String color) {
        Color = color;
    }

    public final int getXP() {
        return XP;
    }

    public final void setXP(final int xP) {
        XP = xP;
    }

    public final int getLevel() {
        return Level;
    }

    public final void setLevel(final int level) {
        Level = level;
    }


    public final float getHeight() {
        return Height;
    }

    public final void setHeight(final float height) {
        Height = height;
    }

    public final float getWeight() {
        return Weight;
    }

    public final void setWeight(final float weight) {
        Weight = weight;
    }

    @Override
    public final String toString() {
        return "Pokemon [Nom=" + name + ", Type1=" + type1 + ","
                + "Couleur=" + Color
                + ", Eleveur=" + Owner + ", Experience=" + XP
                + ", Niveau=" + Level + ", VieMax=" + PVMax + ","
                + "VieActuel=" + PVNow
                + ", Taille=" + Height + ", Poid="
                + Weight + "]";
    }
}
