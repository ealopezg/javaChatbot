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

	public Pelicula(){}

	private List<List<String>> readStringHorario(String linea){
		List<List<String>> horarios = new ArrayList();
		String[] listaDias = linea.split("!");

		horarios.add(Arrays.asList(listaDias[0].split(";")));
		horarios.add(Arrays.asList(listaDias[1].split(";")));
		horarios.add(Arrays.asList(listaDias[2].split(";")));
		horarios.add(Arrays.asList(listaDias[3].split(";")));
		horarios.add(Arrays.asList(listaDias[4].split(";")));
		horarios.add(Arrays.asList(listaDias[5].split(";")));
		horarios.add(Arrays.asList(listaDias[6].split(";")));
		return horarios;

	}
	

	public String toString(){
	
		return this.name + " (" + Integer.toString(this.score) + ")";
	}

	public String getGender(){
		return this.gender;
	}

	public String getScore(){
		String scoreString = Integer.toString(this.score);
		return scoreString.substring(0,1)+"."+scoreString.substring(1);
	}

	private String diaToString(int n){
		String[] diasSemana={"LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO","DOMINGO"};
		return diasSemana[n];
	}

	private int diaToInt(String dia){
		String[] diasSemana={"LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO","DOMINGO"};
		int i=0;
		while(i<7){
			if(dia.equals(diasSemana[i])){
				break;
			}
			i++;
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