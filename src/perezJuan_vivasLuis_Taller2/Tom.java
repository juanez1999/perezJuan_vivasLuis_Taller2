package perezJuan_vivasLuis_Taller2;


import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Tom extends Personaje {
	
	private PImage tom;
	private int random;
	private PVector posObj;
	private Jerry jerry;
	private ArrayList<Comida> comidas;
	private float arco;
	private int contadorVida;
	private boolean convida;
	
	public Tom(Logica log, PApplet app, Jerry jerry) {
		super(log, app);
		tom = app.loadImage("tom.png");
		this.jerry=jerry;
		vel = new PVector(0,0);
		acel= new PVector(0,0);
		maxVel = 4;
		maxFue= 0.5f;
		arco = 0;
		contadorVida = 0;
		convida=true;
		vivo = true;
		random = (int) app.random(3);
		if(random == 1) {
			pos = new PVector(1300 ,app.height/2+50);
		} else if(random == 2) {
			pos = new PVector(app.width/2 , 700);
		} else {
			pos = new PVector(-50 ,app.height/2);
		}
		comidas = log.getComidas();
	}

	@Override
	public void pintar() {
		app.imageMode(app.CENTER);
		app.image(tom, pos.x, pos.y);
		app.arc(pos.x, pos.y, 100, 100, app.radians(0), app.radians(arco));
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
	
	public void perseguirComida() {
		Comida c = null;
		if(comidas.size() > 0) {
			c = comidas.get(0);
		}
		for(int i = 0; i < comidas.size(); i++) {
			Comida comida = comidas.get(i);
			if(app.dist(c.posX, c.posY, pos.x, pos.y) > app.dist(comida.posX, comida.posY, pos.x, pos.y)) {
				c = comida;
				}
			}
		if(c != null) {
			PVector obj = new PVector(c.posX, c.posY);
			perseguir(obj);
		}
	}


	public void comido() {
		for (int i = 0; i < comidas.size(); i++) {
			Comida c = comidas.get(i);
		
		if(PApplet.dist(pos.x, pos.y, c.getPosX(), c.getPosY()) < 50) {
			c.setComido(true);
			log.eliminarComidaTom(c);
			if(arco < 360) {
				arco+=36;
			}
			//System.out.println("c lo comio tom");
		} else {
			c.setComido(false);
		}
	}
	}
	
	public void quitarVida() {
		PVector copi = jerry.getPos().copy();
		if(PApplet.dist(pos.x, pos.y, copi.x, copi.y) < 50 && jerry.getArco()>0) {
			float c= jerry.getArco();
			c-=36;
			jerry.setArco(c);
			PVector p = vel.copy();
			p.mult(-50);
			pos.add(p);
////			pos.x-=10;
//			vel.normalize();
//			//acel.mult(-10);
//			vel.mult(-500);
//			//contVida();
			System.out.println("si quita vida");
			return;
		}
	}
	
	public void contVida() {
		int m= app.millis();
		while(m-app.millis()<1000) {
			convida=false;
		}
		convida=true;
	}

	@Override
	public void run() {
		while (vivo) {
			try {
				actua();
				if(log.getJerry().getArco() < arco) {
					perseguir(log.getJerry().getPos());
					quitarVida();
				} else {
					perseguirComida();
				}
				comido();
			sleep(16);
			} catch (Exception e) {
			
			}
		}
	}
	
	
	@Override
	public void matar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}
	
	

}
