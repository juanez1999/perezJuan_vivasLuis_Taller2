package perezJuan_vivasLuis_Taller2;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica extends Thread {
	private PApplet app;
	private PImage fondo;
	private Jerry jerry;
	private ArrayList<Tom> toms;
//	private Tom tom;
	private boolean vivo;
	private int contadorAgregar;
	private int m;

	private int contCreate;

	public Logica(PApplet app) {
		this.app = app;

		contCreate = 0;

		fondo = app.loadImage("fondo.jpg");
		jerry = new Jerry(this, app);
		toms = new ArrayList<>();
//		tom= new Tom(this, app);
		jerry.start();
//		tom.start();
		contadorAgregar = 0;
		m = app.millis();
//		crearToms();

		vivo = true;
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
//		tom.pintar();

		for (int i = 0; i < toms.size(); i++) {
			toms.get(i).pintar();
		}

	}
//	public void crearToms() {	
//		
//		for (int i = 0; i < 15; i++) {
//			Tom t = new Tom(this, app);
//			t.start();
//			toms.add(t);	
//	}
//	}

	public void crearToms() {
		contCreate++;
		System.out.println(contCreate);
		if (contCreate > 180) {
			System.out.println("si entro");
			Tom t = new Tom(this, app);
			t.start();
			toms.add(t);

			contCreate = 0;
		}
	}

	public Jerry getJerry() {
		return jerry;
	}

	@Override
	public void run() {
		while (vivo) {
			crearToms();
			try {
				sleep(16);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}