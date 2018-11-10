package perezJuan_vivasLuis_Taller2;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Jerry extends Personaje {

	private PImage jerry;
	private Comida c;
	private float arco;
	private ArrayList<Comida> comidas;
	private int s;

	public Jerry(Logica log, PApplet app) {
		super(log, app);
		jerry = app.loadImage("jerry.png");
		pos = new PVector(app.width / 2, app.height / 2);
		vel = new PVector(0, 0);
		acel = new PVector(0, 0);
		maxVel = 6;
		maxFue = 0.5f;
		vivo = true;
		vida = 2;
		arco = 36;
		comidas = log.getComidas();
		s = 0;

	}
	//Metodo encargado de pintar la imagen de Tom y el arco que representa la vida
	@Override
	public void pintar() {
		app.imageMode(app.CENTER);
		app.fill(0);
		app.image(jerry, pos.x, pos.y);
		app.stroke(255);
		app.noFill();
		app.stroke(0, 255, 0);
		app.arc(pos.x, pos.y, 100, 100, app.radians(0), app.radians(arco));
	}

/*Metodo encargado de hacer las operaciones con los vectores que se utiliza en Jerry, estas operaciones abarcan agregar
y limitar la velocidad a la cual se pueden mover, y su aceleracion*/
	public void actua() {
		vel.add(acel);
		vel.limit(maxVel);
		pos.add(vel);
		acel.mult(0);
	}
/* Metodo encargado de hacer la validacion para perseguir al mouse al puntero, el objetivo que recibe por parametro es la posicion del 
 * puntero del mouse, cuando encuentra la posicion del mouse, se le agregar la maxima velocidad contra la cual puede ir jerry hacia el
 *  mouse	
 */
	public void perseguir(PVector objetivo) {
		PVector encontrado = PVector.sub(objetivo, pos);
		encontrado.normalize();
		encontrado.mult(maxVel);
		PVector dirigir = PVector.sub(encontrado, vel);
		dirigir.limit(maxFue);
		acel.add(dirigir);
	}

//Metodo encargado de hacer todo el Hilo, todo lo que necesita ejecutarse, es decir los metodos definidos aca en la clase
	@Override
	public void run() {
		while (vivo) {
			try {
			/*For encargado de llevar el arco de la vida al maximo multiplo de 36 cercano para que al restarle la vida no se 
				represente de manera incorrecta*/
				for (int i = 0; i < 10; i++) {

					if (arco > 1 + (i * 36) && arco < 36 + (i * 36)) {
						arco = 36 + (i * 36);
					}
				}

				// System.out.println("si funciona");
				actua();
				PVector posM = new PVector(app.mouseX, app.mouseY);
				perseguir(posM);
				tiempoRapido();
				sleep(16);
			} catch (Exception e) {

			}
		}
	}
//Metodo encargado de limitar el tiempo que se esta rapido
	public void tiempoRapido() {
		if (s - app.millis() < -2000) {
			maxVel = 6;
			s = 0;
		}
	}

	public void setMaxVel(float vel) {
		maxVel = vel;
		s = app.millis();
	}

	

	public PVector getPos() {
		return pos;
	}

	public float getArco() {
		return arco;
	}

	public void setArco(float arco) {
		this.arco = arco;
	}

}
