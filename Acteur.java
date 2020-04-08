package JeuDeLaVie;

public abstract class Acteur {
	private int x;
	private int y;
	private int v;
	private int dx;
	private int dy;
	private String id = "";
	private int pv;
	protected Environnement env;
	private static int compteur = 1;
	private boolean male;
	
	public Acteur(int x, int y, int v, Environnement e, int pv, boolean b) {
		this.x = x;
		this.y = y;
		if(this.x < 0 || this.x > e.getWidth()||this.y < 0 || this.y > e.getHeight())
			throw new Error("Les coordonnées données sont hors des limites!");
		this.v = v;
		this.env = e;
		this.pv = pv;
		this.id += compteur;
		int random = (int)Math.random()*3 - 1;
		this.dx = random;
		random = (int)Math.random()*3 - 1;
		this.dy = random;
		this.male = b;
		compteur ++;
	}
	
	public Acteur(int v, Environnement e, int pv, boolean b) {
		this.v = v;
		this.env = e;
		this.pv = pv;
		this.id += compteur;
		compteur ++;
		int random = (int)(Math.random()*this.env.getWidth());
		this.x= random;
		random = (int)(Math.random()*this.env.getHeight());
		this.y = random;
		random = (int)Math.random()*3 - 1;
		this.dx = random;
		random = (int)Math.random()*3 - 1;
		this.dy = random;
		
		this.male = b;
	
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getId() {
		return id;
	}

	public void decrementerPv(int p) {
		this.pv -= p;
	}
	
	public boolean estVivant() {
		return this.pv>0;
	}
	
	public void meurt() {
		this.pv = 0;
	}
	
	public void seDeplace() {
		double chanDir = Math.random();
		if(chanDir < 0.2) {
			this.changerDir();
		}
		this.x += this.dx*this.v;
		this.y += this.dy*this.v;
		if(!env.dansTerrain(x, y)) {
			this.x -= this.dx*this.v*2;
			this.y -= this.dy*this.v*2;
		}
	}
	
	public void changerDir() {
		int random = (int)Math.random()*3 - 1;
		this.dx = random;
		random = (int)Math.random()*3 - 1;
		this.dy = random;
	}
	
	public abstract void agit();
	
	public String toString() {
		return this.id + " a " + this.pv + " pv ";
	}
	
	public boolean estMale() {
		return this.male;
	}
}

