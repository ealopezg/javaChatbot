import java.util.*;
import java.time.*;
/**
 * Clase Chatbot, genera las respuestas para
 * los mensajes del usuario
 */
public class Chatbot{
	/**
	 * La personalidad del chatbot, si es 0 es 
	 * informal y si es 1 es formal.
	 */
	private int personalidad;

	/**
	 * La semilla pseudoaleatoria que utiliza
	 * el chatbot para seleccionar las respuesta
	 * para el mensaje del usuario.
	 */
	private int seed;

	/**
	 * Es la cartelera del cine, un arreglo de 
	 * peliculas.
	 */
	private ArrayList<Pelicula> cartelera;

	/**
	 * Es un arreglo con las posibles respuestas
	 * del chatbot
	 */
	private String[][][] respuestas;

	/**
	 * Objeto Random, generador de numeros
	 * pseudoaleatorios
	 */
	private Random random;
	




	Chatbot(){}

	/**
	 * Devuelve un chatbot con sus posibles
	 * respuestas y la cartelera, segun la
	 * semilla que se ingrese, será la 
	 * personalidad del chatbot
	 * @param  seed      entero para generar numeros pseudoaleatorios
	 * @param  peliculas Arreglo de peliculas
	 * @return           Objeto chatbot
	 */
	public Chatbot(int seed,ArrayList<Pelicula> peliculas){
		this.random = new Random(seed);
		this.personalidad = this.random.nextInt(2);
		this.seed = seed;
		this.respuestas=generarListaRespuestas();
		this.cartelera=peliculas;
	}

	/**
	 * Devuelve un chatbot con sus posibles
	 * respuestas y la cartelera, con 
	 * personalidad formal y seed 0.
	 * @param  peliculas Arreglo de peliculas
	 * @return           Objeto chatbot
	 */
	public Chatbot(ArrayList<Pelicula> peliculas){
		this.random = new Random(0);
		this.personalidad = 0;
		this.seed = 0;
		this.respuestas=generarListaRespuestas();
		this.cartelera=peliculas;
	}






	/**
	 * Funcion que genera una respuesta al 
	 * mensaje del usuario, recibe el mensaje
	 * del usuario y el ultimo mensaje enviado
	 * por el chatbot, esto para poder identificar
	 * el camino y nivel de la conversacion.
	 * Si es necesario, reemplaza elementos por @
	 * en el mensaje de salida (por ejemplo, cuando
	 * el chatbot muestra el nombre de la pelicula y
	 * su puntaje)
	 * @param  inMessage       mensaje de entrada
	 * @param  lastCbotMessage ultimo mensaje del chatbot
	 * @param  strs            lista de strings a 
	 *                         utilizar para reemplazar @
	 * @return                 Mensaje a enviar
	 */
	public Message generateMessage(Message inMessage,String lastCbotMessage,String[] strs){
		int[] cNU=inMessage.determinarCaminoNivel();
		int[] cNC=determinarCaminoNivel(lastCbotMessage);
		String message="";

		if((cNU[0]!=-1) && (cNU[1]!=-1) && (cNC[0]!=-1) && (cNC[1]!=-1)){
			if(cNC[0]==cNU[0] && cNC[1]==cNU[1]-1){
				message=choose(this.respuestas[cNU[0]][cNU[1]]);
			}
		else if(cNC[0]==0){
				message=choose(this.respuestas[cNU[0]][cNU[1]]);
			}
		}
		else{
			message = "[!] No entendí lo que me quieres decir!!!!,asegurate de responder lo que te pido";
			return new Message(message,"CHATBOT");
		}
		message = replaceBrackets(message,strs);
		return new Message(message,"CHATBOT");
	}
	
	/**
	 * Funcion que genera una respuesta al 
	 * mensaje del usuario, recibe el mensaje
	 * del usuario y el ultimo mensaje enviado
	 * por el chatbot, esto para poder identificar
	 * el camino y nivel de la conversacion.
	 * Si es necesario, reemplaza elementos por @
	 * en el mensaje de salida (por ejemplo, cuando
	 * el chatbot muestra el nombre de la pelicula y
	 * su puntaje). Utilizado para el loadLog
	 * @param  inMessage       mensaje de entrada
	 * @param  lastCbotMessage ultimo mensaje del chatbot
	 * @param  strs            lista de strings a 
	 *                         utilizar para reemplazar @
	 * @param  date            Fecha
	 * @return                 Mensaje a enviar
	 */
	public Message generateMessage(Message inMessage,String lastCbotMessage,String[] strs,Date date){
		int[] cNU=inMessage.determinarCaminoNivel();
		int[] cNC=determinarCaminoNivel(lastCbotMessage);
		String message="";

		if((cNU[0]!=-1) && (cNU[1]!=-1) && (cNC[0]!=-1) && (cNC[1]!=-1)){
			if(cNC[0]==cNU[0] && cNC[1]==cNU[1]-1){
				message=choose(this.respuestas[cNU[0]][cNU[1]]);
			}
		else if(cNC[0]==0){
				message=choose(this.respuestas[cNU[0]][cNU[1]]);
			}
		}
		else{
			message = "[!] No entendí lo que me quieres decir!!!!,asegurate de responder lo que te pido";
			new Message(message,"CHATBOT",date);
		}
		message = replaceBrackets(message,strs);
		return new Message(message,"CHATBOT",date);
	}

	/**
	 * Devuelve una lista de strings, estos son
	 * ocupados por el chatbot para escoger las
	 * respuestas.
	 * @return String[][][]
	 */
	private String[][][] generarListaRespuestas(){
		String[][][][] dialogos = {
			{{{"Soy el Chatbot formal...Hola, en que lo puedo ayudar?", "Soy el Chatbot formal...Buenos dias, en que lo puedo ayudar?", "Soy el Chatbot formal...Hola, que agradable dia, en que lo puedo ayudar?"}, {"Soy el Chatbot formal...Buenas tardes, Que necesita?", "Soy el Chatbot formal...Buenas tardes, Que puedo hacer por usted?"}, {"Soy el Chatbot formal...Buenas noches, Que necesita?", "Soy el Chatbot formal...Que linda que esta la luna.... En que lo puedo ayudar?"}}, {{"Digame algun genero que le guste", "Que genero le gusta?"}, {"Le recomiendo la pelicula: @ , tiene una puntuacion de @ ,Hasta Luego !!!", "Tengo esta pelicula para usted: @ , tiene una puntuacion de @ ,Hasta Luego !!!"}}, {{"Para que dia?", "Que dia?"}, {"Que pelicula desea ver?", "Que pelicula quiere ver?"}, {"Para el dia @, tenemos los siguientes horarios @,Hasta luego !!!", "El dia @ tenemos los siguientes horarios @,Hasta luego !!!"}}, {{"Para que pelicula?", "Que pelicula desea?"}, {"Que dia tiene pensado ir a verla?", "Que dia?"}, {"Bueno, para el dia @ tenemos los siguientes horarios @, Que horario escoge?", "Bueno, para el dia @ le tengo los siguientes horarios @, Que horario escoge?"}, {"Ok, su entrada esta reservada para el dia @ a las @, Hasta Luego !!!", "Ok, su entrada esta reservada para el dia @ a las @, Adios...que tenga un lindo dia !!"}}},
			{{{"Soy el Chatbot informal...Hola, dime que quieres", "Soy el Chatbot informal...Buenos dias,buenas tardes, que quieres", "Soy el Chatbot informal...Hola, en que te puedo ayudar?"}, {"Soy el Chatbot informal...Buenas tardes, que quieres?", "Soy el Chatbot informal...Buenas tardes, que $&$ queri ?"}, {"Soy el Chatbot informal...Esta brigida la luna... Que necesitai?", "Soy el Chatbot informal...Wenah noshe , que queri?"}}, {{"Digame algun genero que te guste", "Que genero te gusta?"}, {"Te recomiendo la pelicula: @ , tiene una puntuacion de @,Me viro... !!!", "Tengo esta pelicula para vo' : @ , tiene una puntuacion de @,Bye !!!"}}, {{"Pa que dia?", "Que dia queri?"}, {"Que pelicula queri ?", "Que pelicula queri ver?"}, {"Pal @, tenemos los siguientes horarios @,Hasta la vista... !!!", "El @ te tengo los siguientes horarios @,Chao pescao !!!"}}, {{"que pelicula queri", "Que pelicula deseas baby?"}, {"Que dia tienes pensado ir a verla?", "Que dia?"}, {"el dia @ estan los siguientes horarios @,cual quieres", "Bueno, para el dia @ le tengo los siguientes horarios @,Que horario escoges?"}, {"Wena, tu entrada esta reservada para el dia @ a las @,Chao pescao !!!", "Listo compa , tu entrada esta reservada para el dia @ a las @,Hasta la vista !!!"}}}
			};
		return dialogos[this.personalidad];
	}

	/**
	 * Reemplaza los @ de un string de entrada
	 * por los elementos de una lista de strings
	 * @param  entrada String a reemplazar
	 * @param  lista   Lista de strings
	 * @return         Strings modificado
	 */
	private String replaceBrackets(String entrada,String[] lista){
		if(lista.length == 0){
			return entrada;
		}
		String[] entradaSplit = entrada.split("@");
		String salida = "";
		for(int i = 0;i<entradaSplit.length;i++){
			if(i<lista.length){
				salida = salida + entradaSplit[i]+lista[i];
			}
			else{
				salida=salida+entradaSplit[i];
			}
		}
		return salida;
	}
	
	/**
	 * Devuelve el mensaje inicial, utilizado
	 * despues de que el usuario ingresa 
	 * !beginDialog
	 * @return Mensaje
	 */
	public Message initialMessage(){
		int hr =LocalDateTime.now().getHour();
		String str="";
		if (5<hr && hr<=11){
			str = choose(this.respuestas[0][0]);
		}
		if (11<hr && hr<=20){
			str = choose(this.respuestas[0][1]);
		}
		if ((20<hr && hr<=23) || (0<=hr && hr<=5)){
			str = choose(this.respuestas[0][2]);
		}
		Message outMessage = new Message(str,"CHATBOT");
		return outMessage;

	}


	/**
	 * Devuelve el mensaje inicial, utilizado
	 * despues de que el usuario ingresa 
	 * !beginDialog, utilizado para el loadLog
	 * @param  date Fecha
	 * @return      Mensaje
	 */
	public Message initialMessage(Date date){
		int hr =LocalDateTime.now().getHour();
		String str="";
		if (5<hr && hr<=11){
			str = choose(this.respuestas[0][0]);
		}
		if (11<hr && hr<=20){
			str = choose(this.respuestas[0][1]);
		}
		if ((20<hr && hr<=23) || (0<=hr && hr<=5)){
			str = choose(this.respuestas[0][2]);
		}

		Message outMessage = new Message(str,"CHATBOT",date);
		return outMessage;

	}

	/**
	 * Devuelve el mensaje final, utilizado
	 * cuando el usuario ingresa !endDialog
	 * @return Mensaje
	 */
	public Message finalMessage(){
		Message outMessage = new Message("Adios","CHATBOT");
		return outMessage;
	}

	/**
	 * Devuelve el mensaje final, utilizado
	 * cuando el usuario ingresa !endDialog,
	 * se utiliza en el loadLog
	 * @param  date Fecha
	 * @return      Mensaje
	 */
	public Message finalMessage(Date date){
		Message outMessage = new Message("Adios","CHATBOT",date);
		return outMessage;
	}

	/**
	 * Elige al "azar" un elemento de
	 * una lista de strings
	 * @param  lista lista de strings
	 * @return       string
	 */
	private String choose(String[] lista){
		int largo = lista.length;
		int i =this.random.nextInt(largo);
		return lista[i];
	}

	/**
	 * Devuelve una lista de strings con el
	 * nombre de la pelicula y su puntaje.
	 * Se obtiene buscando la mejor pelicula
	 * del genero dado.
	 * @param  genero String con el genero
	 * @return        lista , el primer 
	 *                elemento es el nombre y
	 *                el segundo es el puntaje
	 */
	public String[] recomendarPelicula(String genero){
		ArrayList<Pelicula> aux = new ArrayList();
		for(int i=0;i<this.cartelera.size();i++){
			if(this.cartelera.get(i).getGender().equals(genero)){
				aux.add(this.cartelera.get(i));
			}

		}
		Pelicula mejorPelicula = Collections.max(aux);
		return new String[]{mejorPelicula.getName(),mejorPelicula.getScore()};
	}


	/**
	 * Devuelve el camino y el nivel de un 
	 * mensaje del chatbot.
	 * @param  message String con el mensaje
	 * @return         lista [camino,nivel]
	 */
	private int[] determinarCaminoNivel(String message){
		if(!message.contains("[]")){
			for(int i =0;i<this.respuestas.length;i++){
				for(int j=0;j<this.respuestas[i].length;j++){
					for(int k=0;k<this.respuestas[i][j].length;k++){
						if(message.equals(this.respuestas[i][j][k])){
							return new int[]{i,j};
						}
					}
				}
			}
		}
		else{
			for(int i =0;i<this.respuestas.length;i++){
				for(int j=0;j<this.respuestas[i].length;j++){
					for(int k=0;k<this.respuestas[i][j].length;k++){
						String[] respuestaSplit = this.respuestas[i][j][k].split(" ");
						boolean correcto = true;
						for (int l=0;l<respuestaSplit.length;l++){
							if (!respuestaSplit[l].equals("@")){
								if(!message.contains(respuestaSplit[l])){
								correcto = false;
									}
							}
						}
						if(correcto){
							return new int[]{i,j};
							}
						}
					}
				}
			}
			return new int[]{-1,-1};
		}

	/**
	 * Busca una pelicula de la cartelera,
	 * tiene que ser el nombre exacto.
	 * @param  nombrePelicula nombre de la pelicula
	 * @return                Objeto pelicula.
	 */
	private Pelicula buscarPelicula(String nombrePelicula){
		Pelicula pelicula = new Pelicula();
		for(int i=0;i<this.cartelera.size();i++){
			if(nombrePelicula.equals(this.cartelera.get(i).getName())){
				pelicula = this.cartelera.get(i);
			}
		}
		return pelicula;
	}

	/**
	 * Busca los horarios de una pelicula en
	 * la cartelera de un dia en especifico.
	 * La salida es una lista de strings HH:MM
	 * @param  peliculaStr nombre de la pelicula
	 * @param  dia         dia de la semana en mayusculas
	 * @return             Lista con los horarios del dia
	 */
	public String[] devolverHorarios(String peliculaStr,String dia){
		Pelicula pelicula = buscarPelicula(peliculaStr);
		String strHorarios = "";
		List<String> listaHorarios =pelicula.getHorario(dia);//
		for(int i = 0;i<listaHorarios.size();i++){
			strHorarios = strHorarios + "  " + listaHorarios.get(i);
		}
		return new String[]{dia,strHorarios};
	}
		


	


}