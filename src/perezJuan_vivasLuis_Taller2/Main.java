package perezJuan_vivasLuis_Taller2;


import processing.core.PApplet;

public class Main extends PApplet {
	
	private Logica log;
	
	public static void main(String[] args) {
		PApplet.main("perezJuan_vivasLuis_Taller2.Main");
	}
	
	public void settings() {
		size(1200,676);
	}
	
	public void setup() {
		noCursor();
		log = new Logica (this);
	}
	
	public void draw() {
		background(0);
		log.pintar();
		
	}
	
	public void mousePressed() {
		log.click();
	}

}
