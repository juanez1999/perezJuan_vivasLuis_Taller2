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
	private boolean vivo;
	private int contadorAgregar;
	private int m;
	public int t;
	private int tiempo;
	private int contCreate;
	private int contComida;
	public int posX;
	public int posY;
	private int time;
	private int recX,recY,recCrecerx,recCrecery;
	public boolean recpintar;
	

	public Logica(PApplet app) {
		this.app = app;

		contCreate = 0;
		
		contComida = 0;

		fondo = app.loadImage("fondo.jpg");
		jerry = new Jerry(this, app);
		toms = new ArrayList<>();
		comidas = new ArrayList<Comida>();
		jerry.start();
		contadorAgregar = 0;
		m = app.millis();
		t=0;
		tiempo=0;
		vivo = true;
		posX = (int) app.random(0, app.width);
		posY = (int) app.random(0, app.height);
		start();
		time = app.millis()+60000; 
		recpintar=false;
		recX=0;
		recY=0;
		recCrecerx=5;
		recCrecery=5;
		
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
		
		time();
		
		for (int i = 0; i < comidas.size(); i++) {
			comidas.get(i).pintar();
		}
		pintarRect();
	
	}

	public void crearToms() {
		contCreate++;
		if (contCreate > 180) {
			//System.out.println("se creo");
			Tom t = new Tom(this, app, jerry);
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
			if(c.getRan() == 0) {
				//codigo de lo que hace
				if(jerry.getArco() < 360) {
					jerry.setArco(jerry.getArco()+36);
				}
				System.out.println("c lo comio");
			}  else {
				c.setComido(false);
			}
			
			if (c.getRan() == 1) {
				for (int j = 0; j < toms.size(); j++) {
					Tom t= toms.get(j);
					t.setMaxVel(1);
				}
			}
			
			if(c.getRan() == 2) {
				jerry.setMaxVel(10);
			}
			
			if(c.getRan() == 3) {	
				if(jerry.getArco() > 0) {
//					jerry.setArco(jerry.getArco()/2);
					for (int j = 0; j < toms.size(); j++) {
						Tom t= toms.get(j);
						t.setArco(t.getArco()/2);
					}
				}
			}
			
			if(c.getRan() == 4) {
				t=app.millis();
				recpintar=true;
			}
			c.setComido(true);
			comidas.remove(c);
	}
	}
	}
	
	public void pintarRect() {
		if(t-app.millis()<-4000) {
			recpintar=false;
			t=0;
		}
			if(recpintar == true) {
				app.fill(0,0,0);
				app.rectMode(app.CENTER);
				app.rect(app.width/2, app.height/2, recX, recY);
				recX+=recCrecerx;
				recY+=recCrecery;
				app.rectMode(app.CORNER);
				if(recCrecerx+recX>=app.width||recCrecerx+recX<=-app.width) {
					recCrecerx*=-1;
				}
				if(recCrecery+recY>=app.height||recCrecery+recY<=-app.height) {
					recCrecery*=-1;
				}
			}
	}
	

	public void time() {
		int seg= (time-app.millis())/1000;
		int min = seg/60;
		seg -= min*60;
		app.fill(0);
		app.textSize(50);
		app.text(min+":"+seg, app.width/2+460, 100);
	}
	
	
	
	public ArrayList<Comida> getComidas() {
		return comidas;
	}

	public void setComidas(ArrayList<Comida> comidas) {
		this.comidas = comidas;
	}

	public boolean isRecpintar() {
		return recpintar;
	}

	public void setRecpintar(boolean recpintar) {
		this.recpintar = recpintar;
	}
	
	
	

}
