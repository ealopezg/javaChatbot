import java.util.*;

public class User{
	private String nombre;
	private String genero;
	private String pelicula;
	private String dia;
	private String hora;

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

	public void setGenero(String gen){
		this.genero = gen;
	}


	public void setPelicula(String pelicula){
		this.pelicula = pel;
	}

	public void setDia(String dia){
		this.dia = dia;
	} 

	public void setHora(String hora){
		this.hora=hora;
	}



}