package perezJuan_vivasLuis_Taller2;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Tom extends Personaje {
	
	private PImage tom;

	
	public Tom(Logica log, PApplet app) {
		super(log, app);
		tom = app.loadImage("tom.png");
		pos = new PVector(app.random(1200),app.random(600));
		vel = new PVector(0,0);
		acel= new PVector(0,0);
		maxVel = 2;
		maxFue= 0.5f;
		vivo = true;
	}

	@Override
	public void pintar() {
		app.imageMode(app.CENTER);
		app.image(tom, pos.x, pos.y);
	}
	public void actua() {
		vel.add(acel);
		vel.limit(maxVel);
		pos.add(vel);
		acel.mult(0);
	}
	
	public void perseguir(PVector objetivo) {
		PVector encontrado = PVector.sub(objetivo,pos);
		encontrado.normalize();
		encontrado.mult(maxVel);
		PVector dirigir = PVector.sub(encontrado,vel);
		dirigir.limit(maxFue);
		acel.add(dirigir);
	}


	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		while (vivo) {
			try {
				actua();
				perseguir(log.getJerry().getPos());
			sleep(16);
			} catch (Exception e) {
			
			}
		}
	}

	@Override
	public void matar() {
		// TODO Auto-generated method stub
		
	}

}
