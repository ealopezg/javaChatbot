import java.util.*;
import java.time.*;

public class Chatbot{
	//Atr
	private int personalidad;
	private int seed;
	private ArrayList<Pelicula> cartelera;
	private String[][][] respuestas;
	private Random random;
	

	Chatbot(){}

	public Chatbot(int seed,ArrayList<Pelicula> peliculas){
		this.random = new Random(seed);
		this.personalidad = this.random.nextInt(2);
		this.seed = seed;
		this.respuestas=generarListaRespuestas();
		this.cartelera=peliculas;
	}

	public Chatbot(ArrayList<Pelicula> peliculas){
		this.random = new Random(0);
		this.personalidad = 0;
		this.seed = 0;
		this.respuestas=generarListaRespuestas();
		this.cartelera=peliculas;
	}


	private String[][][] generarListaRespuestas(){
		String[][][][] dialogos = {
			{{{"Soy el Chatbot formal...Hola, en que lo puedo ayudar?", "Soy el Chatbot formal...Buenos dias, en que lo puedo ayudar?", "Soy el Chatbot formal...Hola, que agradable dia, en que lo puedo ayudar?"}, {"Soy el Chatbot formal...Buenas tardes, Que necesita?", "Soy el Chatbot formal...Buenas tardes, Que puedo hacer por usted?"}, {"Soy el Chatbot formal...Buenas noches, Que necesita?", "Soy el Chatbot formal...Que linda que esta la luna.... En que lo puedo ayudar?"}}, {{"Digame algun genero que le guste", "Que genero le gusta?"}, {"Le recomiendo la pelicula: @ , tiene una puntuacion de @ ,Hasta Luego !!!", "Tengo esta pelicula para usted: @ , tiene una puntuacion de @ ,Hasta Luego !!!"}}, {{"Para que dia?", "Que dia?"}, {"Que pelicula desea ver?", "Que pelicula quiere ver?"}, {"Para el dia @, tenemos los siguientes horarios @,Hasta luego !!!", "El dia @ tenemos los siguientes horarios @,Hasta luego !!!"}}, {{"Para que pelicula?", "Que pelicula desea?"}, {"Que dia tiene pensado ir a verla?", "Que dia?"}, {"Bueno, para el dia @ tenemos los siguientes horarios @, Que horario escoge?", "Bueno, para el dia @ le tengo los siguientes horarios @, Que horario escoge?"}, {"Ok, su entrada esta reservada para el dia @ a las @, Hasta Luego !!!", "Ok, su entrada esta reservada para el dia @ a las @, Adios...que tenga un lindo dia !!"}}},
			{{{"Soy el Chatbot informal...Hola, dime que quieres", "Soy el Chatbot informal...Buenos dias,buenas tardes, que quieres", "Soy el Chatbot informal...Hola, en que te puedo ayudar?"}, {"Soy el Chatbot informal...Buenas tardes, que quieres?", "Soy el Chatbot informal...Buenas tardes, que $&$ queri ?"}, {"Soy el Chatbot informal...Esta brigida la luna... Que necesitai?", "Soy el Chatbot informal...Wenah noshe , que queri?"}}, {{"Digame algun genero que te guste", "Que genero te gusta?"}, {"Te recomiendo la pelicula: @ , tiene una puntuacion de @,Me viro... !!!", "Tengo esta pelicula para vo' : @ , tiene una puntuacion de @,Bye !!!"}}, {{"Pa que dia?", "Que dia queri?"}, {"Que pelicula queri ?", "Que pelicula queri ver?"}, {"Pal @, tenemos los siguientes horarios @,Hasta la vista... !!!", "El @ te tengo los siguientes horarios @,Chao pescao !!!"}}, {{"que pelicula queri", "Que pelicula deseas baby?"}, {"Que dia tienes pensado ir a verla?", "Que dia?"}, {"el dia @ estan los siguientes horarios @,cual quieres", "Bueno, para el dia @ le tengo los siguientes horarios @,Que horario escoges?"}, {"Wena, tu entrada esta reservada para el dia @ a las @,Chao pescao !!!", "Listo compa , tu entrada esta reservada para el dia @ a las @,Hasta la vista !!!"}}}
			};
		return dialogos[this.personalidad];
	}


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

	public Message finalMessage(){
		Message outMessage = new Message("Adios","CHATBOT");
		return outMessage;
	}

	public Message finalMessage(Date date){
		Message outMessage = new Message("Adios","CHATBOT",date);
		return outMessage;
	}

	private String choose(String[] lista){
		int largo = lista.length;
		int i =this.random.nextInt(largo);
		return lista[i];
	}


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

	private Pelicula buscarPelicula(String nombrePelicula){
		Pelicula pelicula = new Pelicula();
		for(int i=0;i<this.cartelera.size();i++){
			if(nombrePelicula.equals(this.cartelera.get(i).getName())){
				pelicula = this.cartelera.get(i);
			}
		}
		return pelicula;
	}

	public String[] devolverHorarios(String peliculaStr,String dia){
		Pelicula pelicula = buscarPelicula(peliculaStr);
		String strHorarios = "";
		List<String> listaHorarios =pelicula.getHorario(dia);//
		for(int i = 0;i<listaHorarios.size();i++){
			strHorarios = strHorarios + "  " + listaHorarios.get(i);
		}
		return new String[]{dia,strHorarios};
	}
		

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

	


}