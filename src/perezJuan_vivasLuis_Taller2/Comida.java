package perezJuan_vivasLuis_Taller2;

import processing.core.PApplet;
import processing.core.PImage;

public class Comida {
	private int posX;
	private int posY;
	private int tipoComida;
	private PApplet app;
	private boolean comido;
	private PImage tipoImagen;
	
	public Comida(PApplet app) {
		this.app=app;
	}
}
