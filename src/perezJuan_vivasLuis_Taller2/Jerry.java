package perezJuan_vivasLuis_Taller2;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Jerry extends Personaje{
	
	private PImage jerry;
	
	public Jerry(Logica log, PApplet app) {
		super(log, app);
		jerry = app.loadImage("jerry.png");
		pos = new PVector(app.width/2, app.height/2);
		vel = new PVector(0,0);
		acel= new PVector(0,0);
		maxVel = 6;
		maxFue= 0.5f;
		vivo = true;
	}

	@Override
	public void pintar() {
		app.imageMode(app.CENTER);
		app.image(jerry, pos.x, pos.y);
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
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
	public void run() {
		while (vivo) {
			try {
				//System.out.println("si funciona");
				actua();
				PVector posM = new PVector(app.mouseX, app.mouseY);
				perseguir(posM);
			sleep(16);
			} catch (Exception e) {
			
			}
		}
	}

	@Override
	public void matar() {
		// TODO Auto-generated method stub
		
	}
	
	public PVector getPos() {
		return pos;
	}

}
