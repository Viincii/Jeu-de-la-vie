package JeuDeLaVie;

import java.util.ArrayList;

public class Loup extends Acteur{
	private static double pourcentageRepro = 3;
	private static int age = 6;
	private int sante;
	
	public Loup(int x, int y, Environnement e, boolean b) {
		super(x, y, 15, e, age*360, b);
		this.sante= 14;
	}
	public Loup(Environnement e, boolean b) {
		super(15, e, age*360, b);
		this.sante= 14;
	}
	
	public void setReproduction(double r) {
		pourcentageRepro = r;
	}
	
	public void agit() {
		this.seDeplace();
		if(this.essaieCaptureMouton()!=null) {
			Acteur m = this.essaieCaptureMouton();
			m.meurt();
			this.sante = 14;
		}
		else {
			this.sante --;
			if(sante <= 0)
				this.meurt();
		}
		double random = Math.random();
		if(random < pourcentageRepro/100) {
			boolean p =this.presencePartenaire();
			if(p) {
				double a = Math.random();
				if (a < 0.5)
					this.env.ajouter(new Loup(this.env, true));
				else
					this.env.ajouter(new Loup(this.env, true));
			}
		}	
		this.decrementerPv(1);
	}
	
	private boolean presencePartenaire() {
		ArrayList<Acteur> acteurs;
		acteurs = this.env.getActeurs();
		int i = 0;
		while ( i < acteurs.size()) {
			if(acteurs.get(i) instanceof Loup)
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
	private Acteur essaieCaptureMouton() {
		ArrayList<Acteur> acteurs;
		acteurs = this.env.getActeurs();
		int i = 0;
		while ( i < acteurs.size()) {
			if(acteurs.get(i) instanceof Mouton)
				if(acteurs.get(i).getX()>this.getX()-5 &&
					acteurs.get(i).getX()<this.getX()+5)
						if(acteurs.get(i).getY()>this.getY()-5 &&
							acteurs.get(i).getY()<this.getY()+5)
								return acteurs.get(i);
			i++;
		}
		return null;
	}
	
	public String toString() {
		return "Ce loup numéro "+ super.toString(); 
	}
}
