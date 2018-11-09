package perezJuan_vivasLuis_Taller2;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Jerry extends Personaje {

	private PImage jerry;
	private Comida c;
	private float arco;
	private ArrayList<Comida> comidas;
	private int s;

	public Jerry(Logica log, PApplet app) {
		super(log, app);
		jerry = app.loadImage("jerry.png");
		pos = new PVector(app.width / 2, app.height / 2);
		vel = new PVector(0, 0);
		acel = new PVector(0, 0);
		maxVel = 6;
		maxFue = 0.5f;
		vivo = true;
		vida = 2;
		arco = 36;
		comidas = log.getComidas();
		s=0;

	}

	@Override
	public void pintar() {
		app.imageMode(app.CENTER);
		app.fill(0);
		app.image(jerry, pos.x, pos.y);
		app.stroke(255);
		app.noFill();
		app.stroke(0,255,0);
		app.arc(pos.x, pos.y, 100, 100, app.radians(0), app.radians(arco));
	}


	public void actua() {
		vel.add(acel);
		vel.limit(maxVel);
		pos.add(vel);
		acel.mult(0);
	}

	public void perseguir(PVector objetivo) {
		PVector encontrado = PVector.sub(objetivo, pos);
		encontrado.normalize();
		encontrado.mult(maxVel);
		PVector dirigir = PVector.sub(encontrado, vel);
		dirigir.limit(maxFue);
		acel.add(dirigir);
	}

	@Override
	public void run() {
		while (vivo) {
			try {
				// System.out.println("si funciona");
				actua();
				PVector posM = new PVector(app.mouseX, app.mouseY);
				perseguir(posM);
				tiempoRapido();
				sleep(16);
			} catch (Exception e) {

			}
		}
	}
	
	public void setMaxVel(float vel) {
		maxVel=vel;
		s=app.millis();
	}
	
	public void tiempoRapido() {
		if(s-app.millis()<-2000) {
			maxVel=6;
			s=0;
		}
	}


	@Override
	public void matar() {
		
	}
	
	public PVector getPos() {
		return pos;
	}

	public float getArco() {
		return arco;
	}

	public void setArco(float arco) {
		this.arco = arco;
	}
	
}
