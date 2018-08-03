public class Pelicula{
	private String name;
	private String gender
	private float score;


	public Pelicula(String name,float scr){
		this.nombre=name;
		this.score=scr;
	}

	public String toString(){
		return this.nombre + '(' + this.scr.toString() + ')';
	}

	public String getGender(){
		return this.gender;
	}

	public float getScore(){
		return this.score;
	}

	public String getName(){
		return this.name;
	}


}