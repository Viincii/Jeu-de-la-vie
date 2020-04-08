package JeuDeLaVie;

public class Essai {
	public static void main(String[] args) {
		Environnement e = new Environnement(60, 60);
		e.init();

		int comp = 0;
		while(e.nbLoup()!= 0 && e.nbMouton() !=0) {
			e.unTour();
			System.out.println("Tour fini!");
			comp++;
		}
		System.out.println(comp);
	}
}
