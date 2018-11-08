package perezJuan_vivasLuis_Taller2;

import processing.core.PApplet;
import processing.core.PImage;

public class Comida {
	public int posX;
	public int posY;
	private int tipoComida, ran;
	private PApplet app;
	public boolean comido;
	private PImage tipoImagen;
	
	public Comida(PApplet app) {
		this.app=app;
		posX = (int) app.random(0, app.width);
		posY = (int) app.random(0, app.height);
		ran=(int) app.random(0,4);
		tipoImagen= app.loadImage(""+ran+".png");
		comido  = false;
	}
	
	public void pintar() {
		app.noStroke();
		app.fill(app.random(0, 255), app.random(0, 255), app.random(0, 255));
		app.ellipse(posX, posY, 20, 20);
		app.image(tipoImagen, posX, posY);
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

