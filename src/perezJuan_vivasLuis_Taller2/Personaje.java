package perezJuan_vivasLuis_Taller2;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Personaje extends Thread {
	
	protected PVector pos;
	protected PApplet app;
	protected boolean vivo;
	protected Logica log;
	protected PVector vel, acel;
	protected float maxVel, maxFue, r;
	
	public Personaje(Logica log, PApplet app) {
		this.log=log;
		this.app=app;
	}
	
	public abstract void pintar();
	public abstract void mover();
	public abstract void run();
	public abstract void matar();
	
	
}
