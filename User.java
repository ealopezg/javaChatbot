import java.util.*;
/**
 * Clase usuario
 */
public class User{
	/**
	 * Nombre del usuario,por defecto USUARIO
	 */
	private String nombre;
	/**
	 * Genero de la pelicula escogida por el usuario
	 */
	private String genero;
	/**
	 * Pelicula escogida por el usuario
	 */
	private String pelicula;
	/**
	 * Dia escogido por el usuario
	 */
	private String dia;

	/**
	 * Contructor
	 * @return Usuario Vacio
	 */
	public User(){	
		this.nombre="USUARIO";
	}


	/**
	 * Booleano, pregunta si tiene genero o no
	 * @return boolean
	 */
	public boolean hasGenero(){
		return this.genero!=null;
	}

	/**
	 * Booleano, pregunta si tiene pelicula o no
	 * @return boolean
	 */
	public boolean hasPelicula(){
		return this.pelicula!=null;
	}

	/**
	 * Booleano, pregunta si tiene dia o no
	 * @return boolean
	 */	public boolean hasDia(){
		return this.dia!=null;
	}

	/**
	 * Configura el genero escogido por el 
	 * usuario
	 * @param gen genero
	 */
	public void setGenero(String gen){
		this.genero = gen;
	}

	/**
	 * Modifica el valor de la pelicula
	 * @param pelicula String pelicula
	 */
	public void setPelicula(String pelicula){
		this.pelicula = pelicula;
	}

	/**
	 * Modifica el valor del dia escogido
	 * por el usuario
	 * @param dia String dia
	 */			
	public void setDia(String dia){
		this.dia = dia;
	} 

	/**
	 * Devuelve el nombre del usuario
	 * @return String con el nombre
	 */
	public String getNombre(){
		return this.nombre;
	}

	/**
	 * Devuelve el string con el dia
	 * @return String con el dia
	 */
	public String getDia(){
		return this.dia;
	}




}