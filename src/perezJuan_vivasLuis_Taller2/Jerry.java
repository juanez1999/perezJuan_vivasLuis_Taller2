package perezJuan_vivasLuis_Taller2;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Jerry extends Personaje {

	private PImage jerry;
	private float arco;

	public Jerry(Logica log, PApplet app) {
		super(log, app);
		jerry = app.loadImage("jerry.png");
		pos = new PVector(app.width / 2, app.height / 2);
		vel = new PVector(0, 0);
		acel = new PVector(0, 0);
		maxVel = 6;
		maxFue = 0.5f;
		vivo = true;
//		vida = 5;
		vida = 2;
		arco = 0;
		contadorVida = 1;	
		arco+=36;
		arco+=36;
		arco+=36;

	}

	@Override
	public void pintar() {
		app.imageMode(app.CENTER);
		app.fill(0);
	
//		app.rect(pos.x-40, pos.y-65, vida+10, 20);
//		app.fill(0,255,0);
//		app.rect(pos.x-35, pos.y-60, vida, 10);
		app.image(jerry, pos.x, pos.y);
		app.stroke(255);
		app.fill(255);
		app.textSize(30);
		app.text(contadorVida, pos.x, pos.y);
		
		app.noFill();
		app.stroke(0,255,0);
//		app.ellipse(pos.x, pos.y, 100, 100);
		app.arc(pos.x, pos.y, 100, 100, app.radians(0), app.radians(arco));
		
	
		
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
