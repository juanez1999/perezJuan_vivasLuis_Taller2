package perezJuan_vivasLuis_Taller2;


import processing.core.PApplet;

public class Main extends PApplet {
	
	private Logica log;
	
	public static void main(String[] args) {
		PApplet.main("perezJuan_vivasLuis_Taller2.Main");
	}
//Tamaño del juego en su totalidad
	public void settings() {
		size(1200,676);
	}

//Metodo que inicializa la logica y le quita el cursor a la aplicacion
	public void setup() {
		noCursor();
		log = new Logica (this);
	}
//Metodo encargado de ejecutar el pintar de la logica
	public void draw() {
		background(0);
		log.pintar();
		
	}
//Metodo quien se encarga de pasar las diferentes pantallas del juego	
	public void mousePressed() {
		log.click();
	}

}
