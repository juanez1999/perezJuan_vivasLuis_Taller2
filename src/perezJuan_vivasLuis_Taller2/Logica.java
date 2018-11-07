package perezJuan_vivasLuis_Taller2;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica extends Thread {
	private PApplet app;
	private PImage fondo;
	private Jerry jerry;
	private ArrayList<Tom> toms;
	private ArrayList<Comida> comidas;
//	private Tom tom;
	private boolean vivo;
	private int contadorAgregar;
	private int m;
	private int tiempo;
	private int contCreate;
	private int contComida;
	public int posX;
	public int posY;
	

	public Logica(PApplet app) {
		this.app = app;

		contCreate = 0;
		
		contComida = 0;

		fondo = app.loadImage("fondo.jpg");
		jerry = new Jerry(this, app);
		toms = new ArrayList<>();
		comidas = new ArrayList<Comida>();
//		tom= new Tom(this, app);
		jerry.start();
//		tom.start();
		contadorAgregar = 0;
		m = app.millis();
//		crearToms();
		tiempo=0;
		vivo = true;
		posX = (int) app.random(0, app.width);
		posY = (int) app.random(0, app.height);
		start();
	}

	public void pintar() {
		app.image(fondo, app.width / 2, app.height / 2);
		app.noFill();
		app.stroke(255, 255, 0);
		app.strokeWeight(4);
		app.ellipse(app.mouseX, app.mouseY, 15, 15);
		app.noStroke();
		jerry.pintar();
		for (int i = 0; i < toms.size(); i++) {
			toms.get(i).pintar();
		}
		
		tiempo++;
		app.textSize(50);
		app.fill(255);
		app.text(tiempo, 1000, 200);
		
		for (int i = 0; i < comidas.size(); i++) {
			comidas.get(i).pintar();
		}

	}

	public void crearToms() {
		contCreate++;
		if (contCreate > 180) {
			System.out.println("se creo");
			Tom t = new Tom(this, app);
			t.start();
			toms.add(t);

			contCreate = 0;
		}
	}
	
	public void eliminarComidaTom(Comida c) {
		comidas.remove(c);
	}
	
	public void crearComida() {
		contComida++;
		if(contComida > 135) {
			Comida c = new Comida(app);

			comidas.add(c);
			
			contComida = 0;
		}
	}

	public Jerry getJerry() {
		return jerry;
	}

	@Override
	public void run() {
		while (vivo) {
			crearToms();
			crearComida();
			comido();
			
			try {
				sleep(16);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public void comido() {
		for (int i = 0; i < comidas.size(); i++) {
			Comida c = comidas.get(i);
		
		if(PApplet.dist(jerry.pos.x, jerry.pos.y, c.getPosX(), c.getPosY()) < 50) {
			c.setComido(true);
			comidas.remove(c);
			if(jerry.getArco() < 360) {
				jerry.setArco(jerry.getArco()+36);
				jerry.contadorVida += 1;
			}
			System.out.println("c lo comio");
		} else {
			c.setComido(false);
		}
	}
	}
	
	
	
	
	
	public ArrayList<Comida> getComidas() {
		return comidas;
	}

	public void setComidas(ArrayList<Comida> comidas) {
		this.comidas = comidas;
	}
	
	

}
