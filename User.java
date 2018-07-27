import java.util.*;

public class User{
	private String nombre;
	private String genero;
	private String pelicula;
	private String dia;
	private Hora hora;

	public User(String name){
		this.nombre = name;
	}

	public boolean hasGenero(){
		return this.genero!=null;
	}

	public boolean hasPelicula(){
		return this.pelicula!=null;
	}

	public boolean hasDia(){
		return this.dia!=null;
	}

	public boolean hasHora(){
		return this.hora!=null;
	}

	public void addGenero(String gen){
		this.genero = gen;
	}


	public void addPelicula(String pelicula){
		this.pelicula = pel;
	}

	public void addDia(String dia){
		this.dia = dia;
	} 

	public void addHora(H hora){
		this
	}



}