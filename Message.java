import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Clase mensaje,
 * implementa la interfaz Comparable
 */
public class Message implements Comparable<Message>{
	/** Nombre del remitente */
	private String sender;
	/** Contenido del mensaje */
	private String message;
	/* Fecha de envio */
	private Date date;


	/**
	 * Constructor, devuelve un objeto mensaje
	 * utilizando la fecha actual
	 * @param  message Mensaje
	 * @param  sender  Remitente
	 * @return         Objeto Mensaje
	 */
	public Message(String message,String sender){
		this.message=message;
		this.sender=sender;
		this.date=new Date();
	}

	/**
	 * Constructor, devuelve un objeto mensaje
	 * utilizando la fecha de entrada
	 * @param  message Mensaje
	 * @param  sender  Remitente
	 * @param  fecha   Fecha
	 * @return         Objeto Mensaje
	 */
	public Message(String message,String sender,Date fecha){
		this.message=message;
		this.sender=sender;
		this.date=fecha;
	}


	/**
	 * Devuelve el remitente del mensaje
	 * @return Remitente
	 */
	public String getSender(){
		return this.sender;
	}

	/**
	 * Devuelve la fecha de creacion del mensaje
	 * @return Fecha ( objeto Date )
	 */
	public Date getDate(){
		return this.date;
	}

	/**
	 * Devuelve la fecha de creacion en forma de
	 * string
	 * @return String con la fecha
	 */
	public String getDateStr(){
		SimpleDateFormat form = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
		return form.format(this.date);

	}

	/**
	 * Devuelve el contenido del mensaje
	 * @return String con el mensaje
	 */
	public String getMessage(){
		return this.message;
	}

	/** Comprobacion del tipo de comando */

	/**
	 * Comprueba si el mensaje es !beginDialog
	 * @return booleano
	 */
	public boolean isBeginDialog(){
		String[] messageSplit = this.message.split(" ");
		return (messageSplit[0].equals("!beginDialog") && ((messageSplit.length==1) || ((messageSplit.length==2) && messageSplit[1].matches("-?\\d+"))));
	}

	/**
	 * Comprueba si el mensaje es !endDialog
	 * @return booleano
	 */
	public boolean isEndDialog(){
		return this.message.equals("!endDialog");
	}

	/**
	 * Comprueba si el mensaje es !saveLog
	 * @return booleano
	 */
	public boolean isSaveLog(){
		return this.message.equals("!saveLog");
	}

	/**
	 * Comprueba si el mensaje es !chatbotPerformance
	 * @return booleano
	 */
	public boolean isChatbotPerformance(){
		return this.message.equals("!chatbotPerformance");
	}

	/**
	 * Comprueba si el mensaje es !saveToJson
	 * @return booleano
	 */
	public boolean isSaveToJson(){
		return this.message.equals("!saveToJson");
	}

	/**
	 * Comprueba si el mensaje es !saveToXml
	 * @return booleano
	 */
	public boolean isSaveToXml(){
		return this.message.equals("!saveToXml");
	}

	/**
	 * Comprueba si el mensaje es !help
	 * @return booleano
	 */
	public boolean isHelp(){
		return this.message.equals("!help");
	}

	/**
	 * Comprueba si el mensaje es !loadLog
	 * @return booleano
	 */
	public boolean isLoadLog(){
		String[] messageSplit = this.message.split(" ");
		return (messageSplit[0].equals("!loadLog") && (messageSplit.length==2));
	}

	/**
	 * Comprueba si el mensaje es !rate
	 * @return booleano
	 */
	public boolean isRate(){
		String[] messageSplit = this.message.split(" ");
		return (messageSplit[0].equals("!rate") && (messageSplit.length==3));
	}

	/**
	 * Comprueba si el mensaje es !exit
	 * @return booleano
	 */
	public boolean isExit(){
		return this.message.equals("!exit");
	}

	/**
	 * Comprueba si el mensaje es un commando
	 * @return booleano
	 */
	public boolean isCommand(){
		if(this.message.length()>0){
			if(this.message.charAt(0)=='!'){
				return true;
			}
			return false;
		}
		return false;
	}


	/**
	 * Devuelve una lista de strings con los
	 * argumentos que podria tener un comando
	 * @return booleano
	 */
	public String[] commandArgs(){
		if(this.isCommand()){
			if(this.isBeginDialog() || this.isLoadLog() || this.isRate()){
				List<String> lista = Arrays.asList(this.message.split(" "));
				return lista.subList(1,lista.size()).toArray(new String[lista.size()]);
			}
			else{
				String[] args = {};
				return args;
			}
		}
		else{
			String[] args = {};
			return args;
		}
	}

	/**
	 * Comprueba si el mensaje dice un dia de la semana
	 * @return Nombre del dia de la semana
	 */
	public String dijoDia(){
		String[] lista = this.message.split(" ");
		String[] diasSemana={"LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO","DOMINGO"};
		for(int i = 0;i<lista.length;i++){
			for(int j = 0;j<diasSemana.length;j++){
				if(diasSemana[j].equals(lista[i].toUpperCase())){
					return diasSemana[j];
				}
			}
		}
		return "";
	}

	/**
	 * Comprueba si el mensaje dice un genero
	 * @return Genero
	 */
	public String dijoGenero(){
		String[] lista = this.message.split(" ");
		String[] listaGeneros={"ACCION","INFANTIL","DRAMA","ANIMACION","TERROR","COMEDIA","ROMANCE"};
		for(int i = 0;i<lista.length;i++){
			for(int j = 0;j<listaGeneros.length;j++){
				if(listaGeneros[j].equals(lista[i].toUpperCase())){
					return listaGeneros[j];
				}
			}
		}
		return "";
	}

	/**
	 * Comprueba si el mensaje dice una pelicula
	 * @return Nombre de la pelicula
	 */
	public String dijoPelicula(){
		String[] listaPeliculas={"Avengers: Infinity war","Deadpool 2","Desobediencia","Gnomos Al Ataque","Isla de Perros","La Isla De Los Pinguinos","La Profecia","Los Extranos Caceria Nocturna","Rampage: Devastacion","Sexy Por Accidente","Sherlock Gnomes","Tully","Yo Soy Simon"};
		for(int j = 0;j<listaPeliculas.length;j++){
			if(this.message.toUpperCase().contains(listaPeliculas[j].toUpperCase())){
				return listaPeliculas[j];
			}
		}
		return "";
	}

	/**
	 * Revisa si el usuario dijo recomendar
	 * @return booleano
	 */
	private boolean dijoRecomendar(){
		String[] lista = this.message.split(" ");
		String[] listaKeyword = {"RECOMIENDAME","RECOMENDAR","SUGIERE","SUGIEREME"};
		for(int i = 0;i<lista.length;i++){
			for(int j = 0;j<listaKeyword.length;j++){
				if(listaKeyword[j].equals(lista[i].toUpperCase())){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Comprueba si el mensaje dijo revisar los
	 * horarios
	 * @return booleano
	 */
	private boolean dijoHorarios(){
		String[] lista = this.message.split(" ");
		String[] listaKeyword = {"HORARIOS"};
		for(int i = 0;i<lista.length;i++){
			for(int j = 0;j<listaKeyword.length;j++){
				if(listaKeyword[j].equals(lista[i].toUpperCase())){
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * Devuelve el camino y el nivel de un 
	 * mensaje del usuario
	 * @return         lista [camino,nivel]
	 */
	public int[] determinarCaminoNivel(){
		if(this.dijoRecomendar()){
			return new int[]{1,0};
		}
		else if(!this.dijoGenero().equals("")){
			return new int[]{1,1};
		}
		else if(this.dijoHorarios()){
			return new int[]{2,0};
		}
		else if(!this.dijoDia().equals("")){
			return new int[]{2,1};
		}
		else if(!this.dijoPelicula().equals("")){
			return new int[]{2,2};
		}
		return new int[]{-1,-1};
	}

	/**
	 * Transforma un mensaje a string,
	 * con el siguiente formato:
	 * [FECHA]>SENDER>MENSAJE
	 * @return String
	 */
	public String toString(){
		return this.getDateStr()+">"+this.getSender()+">"+this.getMessage();
	}

	/**
	 * Implementacion de la interfaz comparable
	 * @param  otroMensaje otro Mensaje
	 * @return             entero
	 */
	public int compareTo(Message otroMensaje){
		return this.date.compareTo(otroMensaje.getDate());
	}


}