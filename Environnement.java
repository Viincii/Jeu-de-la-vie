package JeuDeLaVie;

import java.util.ArrayList;

public class Environnement {
	private int width;
	private int height;
	private int nbTours;
	private ArrayList<Acteur> acteurs;
	
	public Environnement(int w, int h) {
		this.width = w;
		this.height = h;
		this.nbTours = 0;
		acteurs = new ArrayList<Acteur>();
	}

	public int getNbTours() {
		return this.nbTours;
	}

	public void setNbTours(int nbTours) {
		this.nbTours = nbTours;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
	
	public ArrayList<Acteur> getActeurs(){
		return this.acteurs;
	}
	
	public Acteur getActeur(String i) {
		for(Acteur a: acteurs)
			if (a.getId() == i)
				return a;
		return null;
	}
	
	public void ajouter(Acteur e) {
		if(this.acteurs.size()<this.getHeight()*this.getWidth())
			this.acteurs.add(e);
	}
	
	public boolean dansTerrain(int x, int y) {
		return x>0 && x<this.width;
	}
	
	public void unTour() {
		for(int i = 0; i<this.acteurs.size(); i++) {
			if(this.acteurs.get(i).estVivant())
				this.acteurs.get(i).agit();
			else {
				this.acteurs.remove(i);
			}	
		}
		for (int i = 0; i< this.acteurs.size(); i++)
			if(!this.acteurs.get(i).estVivant())
				this.acteurs.remove(i);
		System.out.println("Nombre de mouton en vie: " + nbMouton());
		System.out.println("Nombre de loup en vie: " + nbLoup());
	}
	
	public void init() {
		for(int i = 0; i < 25; i++)
			this.ajouter(new Loup(this, true));
		for(int i = 0; i < 75; i++)
			this.ajouter(new Mouton(this, false));
		for(int i = 0; i < 25; i++)
			this.ajouter(new Loup(this, false));
		for(int i = 0; i < 75; i++)
			this.ajouter(new Mouton(this, true));
	}
	
	public int nbMouton() {
		int occ = 0;
		for(Acteur m : this.acteurs)
			if(m instanceof Mouton)
				occ++;
		return occ;
	}
	public int nbLoup() {
		int occ = 0;
		for(Acteur l : this.acteurs)
			if(l instanceof Loup)
				occ++;
		return occ;
	}
}
