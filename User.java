import java.util.*;

public class User{
	private String nombre;
	private String genero;
	private String pelicula;
	private String dia;

	public User(){
		this.nombre="USUARIO";
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


	public void setGenero(String gen){
		this.genero = gen;
	}

	public String getNombre(){
		return this.nombre;
	}

	public String getDia(){
		return this.dia;
	}


	public void setPelicula(String pelicula){
		this.pelicula = pelicula;
	}

	public void setDia(String dia){
		this.dia = dia;
	} 




}