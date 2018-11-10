package perezJuan_vivasLuis_Taller2;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

// Clase hija Tom quien extiende de la clase padre Personaje
public class Tom extends Personaje {
/* Variables que utilizamos para toda la clase en general y su funcionamiento, heredadas de la clase padre y otras creadas
	especialmente para la clase */
	
	private PImage tom;
	private int random;
	private PVector posObj;
	private Jerry jerry;
	private ArrayList<Comida> comidas;
	private float arco;
	private int contadorVida;
	private boolean convida;
	private int m;
	
	public Tom(Logica log, PApplet app, Jerry jerry) {
	/* Inicializamos las variables, recibimos por parametros La logica para validar lo que tenemos en ella, y el Jerry, personaje del usuario
	para poder hacer los metodos de validacion con el Jerry */
		super(log, app);
		tom = app.loadImage("tom.png");
		this.jerry=jerry;
		vel = new PVector(0,0);
		acel= new PVector(0,0);
		maxVel = 3;
		maxFue= 0.5f;
		arco = 36;
		contadorVida = 0;
		convida=true;
		vivo = true;
		random = (int) app.random(3);
		generarToms();
		comidas = log.getComidas();
		 m=0;
	}
//Metodo encargado de pintar la imagen de Tom y el arco que representa la vida
	@Override
	public void pintar() {
		app.imageMode(app.CENTER);
		app.image(tom, pos.x, pos.y);
		app.arc(pos.x, pos.y, 100, 100, app.radians(0), app.radians(arco));
	}
	
/*Metodo encargado de pintar las posiciones de Tom, para que se pinten desde diferentes lados, teniendo en cuenta la cantidad
	que arroje el random*/
	
	public void generarToms() {
		if(random == 1) {
			pos = new PVector(1300 ,app.height/2+50);
		} else if(random == 2) {
			pos = new PVector(app.width/2 , 700);
		} else {
			pos = new PVector(-50 ,app.height/2);
		}
	}
/*Metodo encargado de hacer las operaciones con los vectores que se utilizan en cada Tom, estas operaciones abarcan agregar
	y limitar la velocidad a la cual se pueden mover, y su aceleracion*/
	public void actua() {
		vel.add(acel);
		vel.limit(maxVel);
		pos.add(vel);
		acel.mult(0);
	}
/* Metodo encargado de hacer la validacion para perseguir al usuario, el objetivo que recibe por parametro es la posicion del 
 * usuario, cuando encuentra la posicion del usuario, se le agregar la maxima velocidad contra la cual puede ir Tom hacia el usuario	
 */
	public void perseguir(PVector objetivo) {
		PVector encontrado = PVector.sub(objetivo,pos);
		encontrado.normalize();
		encontrado.mult(maxVel);
		PVector dirigir = PVector.sub(encontrado,vel);
		dirigir.limit(maxFue);
		acel.add(dirigir);
	}
/* Metodo encargador de perseguir la comida, cuando valida la comida que esta mas cercana a el toma ese nuevo vector dirigido hacia ella
 * para moverse hacia la comida*/	
 
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

 /* Metodo encargado de validar cuando se come un alimento y encargado de validar dependiendo de que alimento se comio
  * hacer la modificacion que hace el alimento ya sea al usuario Jerry o a si mismo*/
 
	public void comido() {
		for (int i = 0; i < comidas.size(); i++) {
			Comida c = comidas.get(i);
		
		if(PApplet.dist(pos.x, pos.y, c.getPosX(), c.getPosY()) < 50) {
			c.setComido(true);
			log.eliminarComidaTom(c);
			if(c.getRan() == 0) {
				//codigo de lo que hace
				if(arco < 360) {
					arco+=36;
					//jerry.setArco(jerry.getArco()+36);
				}
				System.out.println("c lo comio");
			}  else {
				c.setComido(false);
			}
			
			if (c.getRan() == 1) {
//				for (int j = 0; j < toms.size(); j++) {
//					Tom t= toms.get(j);
					jerry.setMaxVel(1);
				}
			
			if(c.getRan() == 2) {
				maxVel=7;
			}
			

			if(c.getRan() == 3) {	
				if( arco > 0) {
						jerry.setArco(jerry.getArco()/2);
					}
				}
			
			if(c.getRan() == 4) {
				log.t=app.millis();
				log.recpintar=true;
			}
		
			
			
	}
	}
	}
/*Metodo para quitar vida contra Jerry siempre y cuando Tom tenga mas vida que el, cuando le quite una vida el vector
 * se multiplica para que se aleje y no le siga quitando vida */	
	public void quitarVida() {
		PVector copi = jerry.getPos().copy();
		if(PApplet.dist(pos.x, pos.y, copi.x, copi.y) < 50 && jerry.getArco()>0) {
			float c= jerry.getArco();
			c-=36;
			jerry.setArco(c);
			PVector p = vel.copy();
			p.mult(-50);
			pos.add(p);
			System.out.println("si quita vida");
			return;
		}
	}
// Metodo encargado	de saber en que momento la vida se puede restar//
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
				tiempoLento();
				comido();
			sleep(16);
			} catch (Exception e) {
			
			}
		}
	}
//Metodo encargado de cambiar la maxima velocidad cuando se come el alimento	
	public void setMaxVel(float v) {
		maxVel=v;
		m=app.millis();
	}
//Metodo encargado de limitar el tiempo que se esta rapido	
	public void tiempoRapido() {
		if(m-app.millis()<-3000) {
			maxVel=4;
			m=0;
		}
	}
// Metodo encargado de limitar el tiempo que se esta lento	
	public void tiempoLento() {	
		if(m-app.millis()<-2000) {
			maxVel=3;
			m=0;
//			System.out.println("si entro");
		}
	}
	
	
	public float getArco() {
		return arco;
	}

	public void setArco(float arco) {
		this.arco = arco;
	}
	
	public PVector getPos() {
		return pos;
	}


	

}
