package perezJuan_vivasLuis_Taller2;

import processing.core.PApplet;
import processing.core.PVector;

//Clase padre encargada de dar los metodos que necesitará cada clase hija
public abstract class Personaje extends Thread {
	
	protected PVector pos;
	protected PApplet app;
	protected boolean vivo;
	protected Logica log;
	protected PVector vel, acel;
	protected float maxVel, maxFue, r;
	protected int vida;

	
	public Personaje(Logica log, PApplet app) {
		this.log=log;
		this.app=app;
	}
	
	public abstract void pintar();
	public abstract void run();
	
	
}
