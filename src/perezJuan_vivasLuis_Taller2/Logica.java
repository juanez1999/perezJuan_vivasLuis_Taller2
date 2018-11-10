package perezJuan_vivasLuis_Taller2;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/* Clase logica que extiende de Thread para utilizar los metodos que llevan Thread, y convertir la clase en un Hilo propio, en el cual
se ejecuta la aplicacion en su mayoria*/
public class Logica extends Thread {
//Variables utilizadas para la clase, desde PImages hasta los ArrayList de la clase Comida y Tom
	private PApplet app;
	private PImage fondo;
	private PImage ganaste;
	private PImage inicio;
	private PImage instrucciones;
	private PImage perdiste;
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
	private int recX, recY, recCrecerx, recCrecery;
	public boolean recpintar;
	private int pantalla;
	
// Constructor donde incializo las variable que necesitamos para los metodos que utilizamos en la clase
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
		t = 0;
		tiempo = 0;
		vivo = true;
		posX = (int) app.random(0, app.width);
		posY = (int) app.random(0, app.height);
		time = app.millis() + 60000;
		recpintar = false;
		recX = 0;
		recY = 0;
		recCrecerx = 5;
		recCrecery = 5;
		pantalla = 0;
		ganaste = app.loadImage("ganaste.png");
		inicio = app.loadImage("inicio.png");
		instrucciones = app.loadImage("instrucciones.png");
		perdiste = app.loadImage("perdiste.png");
		
//Inicializo el hilo de la clase 
		start();


	}
//Metodo pintar donde se ve el switch que representa lo que se pinta en cada pantalla
	public void pintar() {
		switch (pantalla) {
		case 0:
			app.image(inicio, 0, 0);
			break;
		case 1:
			app.image(instrucciones, 0, 0);
			break;
		case 2:
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
			break;
		case 3:
			app.imageMode(app.CORNER);
			app.image(perdiste, 0, 0);
			break;
		case 4:
			app.imageMode(app.CORNER);
			app.image(ganaste, 0, 0);
			break;
		}

	}

	public void crearToms() {
		contCreate++;
		if (contCreate > 180) {
			// System.out.println("se creo");
			Tom t = new Tom(this, app, jerry);
			t.start();
			toms.add(t);

			contCreate = 0;
		}
	}
//Cuando tom se come la comida este metodo se escarga de eliminarla
	public void eliminarComidaTom(Comida c) {
		comidas.remove(c);
	}
//Metodo encargado de crear la comida cada 3 segundos
	public void crearComida() {
		contComida++;
		if (contComida > 135) {
			Comida c = new Comida(app);
			comidas.add(c);
			contComida = 0;
		}
	}

	public Jerry getJerry() {
		return jerry;
	}
	
//Metodo encargado de hacer todo el Hilo, todo lo que necesita ejecutarse, es decir los metodos definidos aca en la clase
	@Override
	public void run() {
		while (vivo) {
			crearToms();
			crearComida();
			comido();
			for (int i = 0; i < toms.size(); i++) {
				Tom t = toms.get(i);

				if (jerry.getArco() > t.getArco() && t.getArco() > 0) {

					quitarVidaTom();
				}
				if(jerry.getArco() == 0) {
					pantalla = 3;
				}
			}
			try {
				sleep(16);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
//Metodo encargado de validar cuando Jerry es mayor nivel que tom y puede quitarle vida, al llegar a 0 la vida de tom este es eliminado
	public void quitarVidaTom() {
		for (int i = 0; i < toms.size(); i++) {
			Tom t = toms.get(i);

			PVector copi = t.getPos().copy();
			if (PApplet.dist(jerry.pos.x, jerry.pos.y, copi.x, copi.y) < 50 && jerry.getArco() > 0) {
				float c = t.getArco();
				c -= 36;
				t.setArco(c);
				PVector p = t.vel.copy();
				p.mult(-50);
				t.pos.add(p);
				System.out.println("si quita vida jerry");

				if (c <= 0) {
					t.interrupt();
					toms.remove(t);
					break;
				}
				return;
			}
		}
	}

 /* Metodo encargado de validar cuando se come un alimento y encargado de validar dependiendo de que alimento se comio
  *  * hacer la modificacion que hace el alimento ya sea al usuario Jerry o a Tom*/
	 
	public void comido() {
		for (int i = 0; i < comidas.size(); i++) {
			Comida c = comidas.get(i);

			if (PApplet.dist(jerry.pos.x, jerry.pos.y, c.getPosX(), c.getPosY()) < 50) {
				if (c.getRan() == 0) {
					// codigo de lo que hace
					if (jerry.getArco() < 360) {
						jerry.setArco(jerry.getArco() + 36);
					}
					System.out.println("c lo comio");
				} else {
					c.setComido(false);
				}

				if (c.getRan() == 1) {
					for (int j = 0; j < toms.size(); j++) {
						Tom t = toms.get(j);
						t.setMaxVel(1);
					}
				}

				if (c.getRan() == 2) {
					jerry.setMaxVel(10);
				}

				if (c.getRan() == 3) {
					if (jerry.getArco() > 0) {
//					jerry.setArco(jerry.getArco()/2);
						for (int j = 0; j < toms.size(); j++) {
							Tom t = toms.get(j);
							t.setArco(t.getArco() / 2);
						}
					}
				}

				if (c.getRan() == 4) {
					t = app.millis();
					recpintar = true;
				}
				c.setComido(true);
				comidas.remove(c);
			}
		}
	}
//Metodo quien se encarga de pintar el rectangulo que dificulta la vision del usuario durante 3 segundos
	public void pintarRect() {
		if (t - app.millis() < -3000) {
			recpintar = false;
			t = 0;
		}
		if (recpintar == true) {
			app.fill(0, 0, 0);
			app.rectMode(app.CENTER);
			app.rect(app.width / 2, app.height / 2, recX, recY);
			recX += recCrecerx;
			recY += recCrecery;
			app.rectMode(app.CORNER);
			if (recCrecerx + recX >= app.width || recCrecerx + recX <= -app.width) {
				recCrecerx *= -1;
			}
			if (recCrecery + recY >= app.height || recCrecery + recY <= -app.height) {
				recCrecery *= -1;
			}
		}
	}
//Metodo encargado de pintar el tiempo maximo de juego que va decreciendo con el paso de los segundos
	public void time() {
		int seg = (time - app.millis()) / 1000;
		int min = seg / 60;
		seg -= min * 60;
		app.fill(0);
		app.textSize(50);
		app.text(min + ":" + seg, app.width / 2 + 460, 100);
		if(seg == 0) {
			pantalla = 4;
		}
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
//Metodo encargado de cambiar la variable pantalla al dar click para pasar entre las imagenes de pantallas de juego
	public void click() {
		switch (pantalla) {
		case 0:
			if (app.mousePressed) {
				pantalla = 1;
			}
			break;

		case 1:
			if (app.mousePressed) {
				pantalla = 2;
			}

		}
	}

}