package perezJuan_vivasLuis_Taller2;

import processing.core.PApplet;
import processing.core.PImage;

// Clase comida, la cual es quien genera los elementos que pueden ser comidos por el usuario y por los enemigos
public class Comida {
//Variables que utilizamos para hacer el funcionamiento general de la clase
	public int posX;
	public int posY;
	private int tipoComida, ran;
	private float randon;
	private PApplet app;
	public boolean comido;
	private PImage tipoImagen;

	public Comida(PApplet app) {
// Inicializamos las variables, las posiciones donde se pintan las comidas son aleatorias
		this.app = app;
		posX = (int) app.random(100, app.width-200);
		posY = (int) app.random(300, app.height-200);
		randon = app.random(0, 1);
		System.out.println("entra al metodo");

// Metodo quien se encarga de generar los alimetnos con probabilidades
		generarAlimentos();
	}

	public void pintar() {
// pintamos aquí la imagen dependiendo del tipo de imagen que reciba con el metodo de generarAlimentos	
		app.noStroke();
		app.image(tipoImagen, posX, posY);
	}
	
	public void generarAlimentos() {
	/*Metodo encargado de elegir con la variable randon la probabilidad de que se pinte una comida, dependiento del randon,
	 se pinta la imagen que este en el if correspondiente y la variable ran toma un valor para validar cuando se la coman que tipo
	de imagen es*/
		
		if (randon <= 0.5 ) {
			tipoImagen = app.loadImage("" + 0 + ".png");
			ran = 0;
			System.out.println("hi");
		} else if (randon <= 0.6) {
			tipoImagen = app.loadImage("" + 1 + ".png");
			ran = 1;
		} else if (randon <= 0.7) {
			System.out.println("entra a la condicion");
			tipoImagen = app.loadImage("" + 2 + ".png");
			ran = 2;
		} else if (randon <= 0.8) {
			System.out.println("entra a la condicion 2");
			tipoImagen = app.loadImage("" + 3 + ".png");
			ran = 3;
		}  else if (randon <= 1) {
			tipoImagen = app.loadImage("" + 4 + ".png");
			ran = 4;
		}
		comido = false;
		System.out.println(randon);
	}


	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean isComido() {
		return comido;
	}

	public void setComido(boolean comido) {
		this.comido = comido;
	}

	public int getRan() {
		return ran;
	}

	public void setRan(int ran) {
		this.ran = ran;
	}

	public int getTipoComida() {
		return tipoComida;
	}

	public void setTipoComida(int tipoComida) {
		this.tipoComida = tipoComida;
	}
}
