import java.util.*;

public class Pelicula implements Comparable<Pelicula>{
	private String name;
	private String gender;
	private int score;
	private List<List<String>> horarios;



	public Pelicula(String lineaPelicula){
		String[] splitPelicula = lineaPelicula.split(",");
		this.name=splitPelicula[0];
		this.gender=splitPelicula[1];
		this.score=Integer.parseInt(splitPelicula[2]);
		this.horarios=readStringHorario(splitPelicula[3]);

	}

	private List<List<String>> readStringHorario(String linea){
		List<List<String>> horarios = new ArrayList();
		String[] listaDias = linea.split("!");
		horarios.add(Arrays.asList(listaDias[0].split(";")));

		return horarios;

	}
	

	public String toString(){
	
		return this.name + " (" + Integer.toString(this.score) + ")";
	}

	public String getGender(){
		return this.gender;
	}

	public float getScore(){
		return this.score;
	}

	private String diaToString(int n){
		String[] diasSemana={"LUNES","MARTES","MIERCOLES","JUEVES","SABADO","DOMINGO"};
		return diasSemana[n];
	}

	private int diaToInt(String dia){
		String[] diasSemana={"LUNES","MARTES","MIERCOLES","JUEVES","SABADO","DOMINGO"};
		int i=0;
		while(i<7){
			if(dia.equals(diasSemana[i])){
				break;
			}
		}
		return i;
	}

	public List<String> getHorario(String dia){
		int n = diaToInt(dia);
		return this.horarios.get(n);

	}

	public List<String> getHorario(int dia){
		return this.horarios.get(dia);
	}

	public String getName(){
		return this.name;
	}

	public int compareTo(Pelicula other){
		return Integer.compare(this.score, other.score);
	}





}