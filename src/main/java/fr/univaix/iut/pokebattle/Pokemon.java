package fr.univaix.iut.pokebattle;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
        @NamedQuery(name = Pokemon.FIND_ALL, query = "SELECT p FROM Pokemon p"),
        @NamedQuery(name = Pokemon.FIND_BY_TYPE, query = "SELECT p FROM Pokemon p WHERE p.Type1 = :ftype OR p.Type2 = :ftype"),
        @NamedQuery(name = Pokemon.FIND_BY_NOM, query = "SELECT p FROM Pokemon p WHERE p.Name = :fnom")
}) 

public class Pokemon {

    public static final String FIND_BY_TYPE = "findPokemonByType";
    public static final String FIND_ALL = "findAllPokemon";
    public static final String FIND_BY_NOM = "findPokemonByNom";

    @Id
    private String Name;

    @ManyToMany
    private Collection<Attaque> attaques;
    
    
    private String Type1;

    public String getType1() {
		return Type1;
	}

	public void setType1(String type1) {
		Type1 = type1;
	}

	public String getType2() {
		return Type2;
	}

	public void setType2(String type2) {
		Type2 = type2;
	}

	/* @Enumerated(EnumType.STRING) */
    private String Type2;

    private String Charac;
    private String Color;
    private String Owner;
    private String Evolution;
    private String Prevolution;
    private String Cry;

    private int Num;
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
    public boolean IsSauvage(Pokemon pokemon) {

        if (pokemon.getOwner() == null)
            return true;
        return false;
    }//IsSauvage


    // Getteurs & Setteurs


    public String getCry() {
        return Cry;
    }

    public Collection<Attaque> getAttaques() {
		return attaques;
	}

	public void setAttaques(Collection<Attaque> attaques) {
		this.attaques = attaques;
	}

	public void setCry(String cry) {
        this.Cry = cry;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String Owner) {
        this.Owner = Owner;
    }

    public int getPVMax() {
        return PVMax;
    }

    public void setPVMax(int pVMax) {
    	PVMax = pVMax;
    }

    public int getPVNow() {
        return PVNow;
    }

    public void setPVNow(int pVNow) {
    	PVNow = pVNow;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
    	Name = name;
    }

    public String getType() {
        return Type1;
    }

    public void setType(String type) {
        Type1 = type;
    }

    public String getCharac() {
        return Charac;
    }

    public void setCharac(String charac) {
    	Charac = charac;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
    	Color = color;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int xP) {
        XP = xP;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
    	Level = level;
    }

    public String getEvolution() {
        return Evolution;
    }

    public void setEvolution(String evolution) {
        this.Evolution = evolution;
    }

    public String getPrevolution() {
        return Prevolution;
    }

    public void setPrevolution(String prevolution) {
        this.Prevolution = prevolution;
    }

    public float getHeight() {
        return Height;
    }

    public void setHeight(float height) {
    	Height = height;
    }

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float weight) {
    	Weight = weight;
    }

    @Override
    public String toString() {
        return "Pokemon [Nom=" + Name + ", Type1=" + Type1 + ", Type2=" + Type2
                + ", Caractere=" + Charac + ", Couleur=" + Color
                + ", Eleveur=" + Owner + ", Evolution=" + Evolution
                + ", Prevolution=" + Prevolution + ", Cri=" + Cry + ", Num="
                + Num + ", Experience=" + XP + ", Niveau=" + Level
                + ", VieMax=" + PVMax + ", VieActuel=" + PVNow
                + ", Taille=" + Height + ", Poid="
                + Weight + "]";
    }



}
