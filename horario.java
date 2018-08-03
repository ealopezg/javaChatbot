import java.util.*;

public class Horario{
	//Atributos
	private ArrayList<String> lunes;
	private ArrayList<String> martes;
	private ArrayList<String> miercoles;
	private ArrayList<String> jueves;
	private ArrayList<String> viernes;
	private ArrayList<String> sabado;
	private ArrayList<String> domingo;

	//Constructor
	public Horario(int seed,String movie,ArrayList<String> disponibles){
		this.lunes = new ArrayList();
		this.martes = new ArrayList();
		this.miercoles = new ArrayList();
		this.jueves = new ArrayList();
		this.viernes = new ArrayList();
		this.sabado = new ArrayList();
		this.domingo = new ArrayList();

		for(int i=0;i<5;i++){

		}
	}




	private genHorario(int seed){


	}

	private generateDay(int seed){
		
	}

	public exists(String horario,String day){
		ArrayList listaDia = getHorarios(day);
		return listaDia.contains(horario);
	}

	public getHorarios(String day){
		if(day=="LUNES"){
			return this.lunes;
		}
		if(day=="MARTES"){
			return this.martes;
		}

		if(day=="MIERCOLES"){
			return this.miercoles;
		}

		if(day=="JUEVES"){
			return this.jueves;
		}

		if(day=="VIERNES"){
			return this.viernes;
		}

		if(day=="SABADO"){
			return this.sabado;
		}

		if(day=="DOMINGO"){
			return this.domingo;
		}
	}


}