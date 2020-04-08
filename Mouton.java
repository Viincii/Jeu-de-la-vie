package JeuDeLaVie;

import java.util.ArrayList;

public class Mouton extends Acteur {
	private static double pourcentageRepro = 10;
	private int tourNaissance;
	private static int age = 12;
	
	public Mouton(int x, int y, Environnement e, boolean b) {
		super(x, y, 10, e, age*360,b);
		this.tourNaissance = e.getNbTours();
	}
	public Mouton(Environnement e, boolean b) {
		super(10, e, age*360, b);
		this.tourNaissance = e.getNbTours();
	}
	
	public void setReproduction(double r) {
		pourcentageRepro = r;
	}
	
	public void agit() {
		if (this.env.getNbTours()%2 == this.tourNaissance%2) {
			this.seDeplace();
			double random = Math.random();
			if(random < pourcentageRepro/100) {
				boolean p =this.presencePartenaire();
				if(p) {
					double a = Math.random();
					if (a < 0.5)
						this.env.ajouter(new Mouton(this.env, true));
					else
						this.env.ajouter(new Mouton(this.env, true));
				}
			}
			this.decrementerPv(1);
		}
		else
			this.decrementerPv(1);
	}
	
	private boolean presencePartenaire() {
		ArrayList<Acteur> acteurs;
		acteurs = this.env.getActeurs();
		int i = 0;
		while ( i < acteurs.size()) {
			if(acteurs.get(i) instanceof Mouton)
				if(acteurs.get(i).estMale()!=this.estMale())
					if(acteurs.get(i).getX()>this.getX()-5 &&
						acteurs.get(i).getX()<this.getX()+5)
							if(acteurs.get(i).getY()>this.getY()-5 &&
								acteurs.get(i).getY()<this.getY()+5)
									return true;
			i++;
		}
		return false;
	}
	public String toString() {
		return "Le mouton né au tour " + this.tourNaissance+ " numéro " + super.toString();
	}
}
