import java.util.*;

/**
 * Clase pelicula, implementa la interfaz comparable
 * para poder elegir la mejor entre dos peliculas
 * segun la puntuacion
 */
public class Pelicula implements Comparable<Pelicula>{
	/**
	 * String con el nombre de la pelicula
	 */
	private String name;

	/**
	 * String con el genero de la pelicula
	 */
	private String gender;

	/**
	 * Entero con el puntaje de la pelicula
	 */
	private int score;
	/**
	 * Lista de listas con los horarios de cada
	 * dia de la pelicula.
	 */
	private List<List<String>> horarios;


	/**
	 * Contructor pelicula, devuelve una pelicula
	 * a partir de un string formateado de la siguiente
	 * forma:
	 * nombrePelicula,GENERO,SCORE,HORARIO
	 * donde HORARIO es de la forma:
	 * LUNES!MARTES!MIERCOLES!JUEVES!VIERNES!SABADO!DOMINGO
	 *
	 * Cada dia corresponde a un string con los horarios del
	 * dia, separados por ';'
	 * 
	 * @param  lineaPelicula String formateado
	 * @return               Pelicula
	 * */
	public Pelicula(){}
	public Pelicula(String lineaPelicula){
		String[] splitPelicula = lineaPelicula.split(",");
		this.name=splitPelicula[0];
		this.gender=splitPelicula[1];
		this.score=Integer.parseInt(splitPelicula[2]);
		this.horarios=readStringHorario(splitPelicula[3]);
	}

	/**
	 * Funcion que lee el string formateado de cada pelicula y
	 * devuelve una lista de lista con los horarios de cada
	 * dia.
	 * @param  linea String formateado
	 * @return       Lista de lista de strings
	 */
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
	
	/**
	 * Devuelve el genero de la pelicula
	 * @return Genero
	 */
	public String getGender(){
		return this.gender;
	}
	/**
	 * Devuelve el horario de la pelicula
	 * segun el dia en Numeros(0,6)
	 * @param  dia [description]
	 * @return     [description]
	 */
	public List<String> getHorario(int dia){
		return this.horarios.get(dia);
	}
	/**
	 * Devuelve el horario de la pelicula
	 * segun el dia en String en mayusculas
	 * @param 	dia Dia en String
	 * @return 		Lista de strings
	 */
	public List<String> getHorario(String dia){
		int n = diaToInt(dia);
		return this.horarios.get(n);

	}

	
	/**
	 * Devuelve el nombre de la pelicula
	 * @return Nombre de la pelicula
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Devuelve la puntuacion de la pelicula en
	 * forma de string
	 * @return String con el puntaje
	 */
	public String getScore(){
		String scoreString = Integer.toString(this.score);
		return scoreString.substring(0,1)+"."+scoreString.substring(1);
	}

	/**
	 * Devuelve un string de algun dia de la
	 * semana, recibiendo como entrada algun
	 * numero del 0 al 6, el 0 corresponde al
	 * LUNES y el 6 corresponde al DOMINGO
	 * @param  n numero del 0 al 6
	 * @return   dia de la semana
	 */
	private String diaToString(int n){
		String[] diasSemana={"LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO","DOMINGO"};
		return diasSemana[n];
	}

	/**
	 * A partir de un String con el dia de la
	 * semana en mayúsculas, devuelve un numero
	 * del 0 al 6. El 0 corresponde al
	 * LUNES y el 6 corresponde al DOMINGO.
	 * @param  dia String con algun dia de la semana
	 * @return     entero del 0 al 6
	 */
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


	@Override
	/**
	 * Override para utilizar la comparacion para 
	 * poder escoger una pelicula en base del puntaje
	 * @param  other Otro objeto pelicula
	 * @return       entero
	 */
	public int compareTo(Pelicula other){
		return Integer.compare(this.score, other.score);
	}

}