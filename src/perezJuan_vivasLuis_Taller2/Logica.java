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
	
	public Logica(PApplet app) {
		this.app=app;
		fondo = app.loadImage("fondo.jpg");
		jerry = new Jerry(this, app);
		toms = new ArrayList<>();
//		tom= new Tom(this, app);
		jerry.start();
//		tom.start();
		crearMundo();
		contadorAgregar = 0;
	}
	
	public void pintar() {
		app.image(fondo,app.width/2,app.height/2);
		app.noFill();
		app.stroke(255,255,0);
		app.strokeWeight(4);
		app.ellipse(app.mouseX, app.mouseY, 15, 15);
		jerry.pintar();
//		tom.pintar();
		for (int i = 0; i < toms.size(); i++) {
			toms.get(i).pintar();
		}
		contadorAgregar++;
		System.out.println(contadorAgregar);
		
	}
	
	public void crearMundo() {	
		if(contadorAgregar % 200 == 0) {
			System.out.println("si entro");
		for (int i = 0; i < toms.size(); i++) {
			Tom t = new Tom(this, app);
			t.start();
			toms.add(t);	
			
		}
		contadorAgregar = 0 ;
		}
	}
	
	
	public Jerry getJerry() {
		return jerry;
	}
	

	@Override
	public void run() {
		while (vivo) {
			try {
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	
	
}
