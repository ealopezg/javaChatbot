import java.util.*;
import java.time.*;

public class Chatbot{
	private int personalidad;
	private int seed;
	private ArrayList<Pelicula> cartelera;
	private String[][][] respuestas;
	private Random random;
	

	public Chatbot(int seed,ArrayList<Pelicula> peliculas){
		this.random = new Random(seed);
		this.personalidad = devolverPersonalidad();
		this.seed = seed;
		this.respuestas=generarListaRespuestas();
		this.cartelera=peliculas;
	}

	private int devolverPersonalidad(){
		return this.random.nextInt(2);
	}

	private String[][][] generarListaRespuestas(){
		String[][][][] dialogos = {
			{{{"Hola, en que lo puedo ayudar?", "Buenos dias, en que lo puedo ayudar?", "Hola, que agradable dia, en que lo puedo ayudar?"}, {"Buenas tardes, Que necesita?", "Buenas tardes, Que puedo hacer por usted?"}, {"Buenas noches, Que necesita?", "Que linda que esta la luna.... En que lo puedo ayudar?"}}, {{"Digame algun genero que le guste", "Que genero le gusta?"}, {"Le recomiendo la pelicula: {} , tiene una puntuacion de {},Hasta Luego !!!", "Tengo esta pelicula para usted: {} , tiene una puntuacion de {},Hasta Luego !!!"}}, {{"Para que dia?", "Que dia?"}, {"Que pelicula desea ver?", "Que pelicula quiere ver?"}, {"Para el dia {}, tenemos los siguientes horarios {},Hasta luego !!!", "El dia {} tenemos los siguientes horarios {},Hasta luego !!!"}}, {{"Para que pelicula?", "Que pelicula desea?"}, {"Que dia tiene pensado ir a verla?", "Que dia?"}, {"Bueno, para el dia {} tenemos los siguientes horarios {}, Que horario escoge?", "Bueno, para el dia {} le tengo los siguientes horarios {}, Que horario escoge?"}, {"Ok, su entrada esta reservada para el dia {} a las {}, Hasta Luego !!!", "Ok, su entrada esta reservada para el dia {} a las {}, Adios...que tenga un lindo dia !!"}}},
			{{{"Hola, dime que quieres", "Buenos dias,buenas tardes, que quieres", "Hola, en que te puedo ayudar?"}, {"Buenas tardes, que quieres?", "Buenas tardes, que $&$ queri ?"}, {"Esta brigida la luna... Que necesitai?", "Buenas noches, En que te ayudo?"}}, {{"Digame algun genero que te guste", "Que genero te gusta?"}, {"Te recomiendo la pelicula: {} , tiene una puntuacion de {},Me viro... !!!", "Tengo esta pelicula para vo' : {} , tiene una puntuacion de {},Bye !!!"}}, {{"Pa que dia?", "Que dia queri?"}, {"Que pelicula queri ?", "Que pelicula queri ver?"}, {"Pal {}, tenemos los siguientes horarios {},Hasta la vista... !!!", "El {} te tengo los siguientes horarios {},Chao pescao !!!"}}, {{"que pelicula queri", "Que pelicula deseas baby?"}, {"Que dia tienes pensado ir a verla?", "Que dia?"}, {"el dia {} estan los siguientes horarios {},cual quieres", "Bueno, para el dia {} le tengo los siguientes horarios {},Que horario escoges?"}, {"Wena, tu entrada esta reservada para el dia {} a las {},Chao pescao !!!", "Listo compa , tu entrada esta reservada para el dia {} a las {},Hasta la vista !!!"}}}
			};
		return dialogos[this.personalidad];
	}

	public void mostrarPeliculas(){
		for(int i=0;i<this.cartelera.size();i++){
			System.out.println(this.cartelera.get(i).toString());
		}
	}

	private String replaceBrackets(String entrada,String[] lista){
		String[] entradaSplit = entrada.split("{}");
		String salida = "";
		for(int i = 0;i<(entradaSplit.length-1);i++){
			if(i<lista.length){
				salida = salida + entradaSplit[i]+lista[i];
			}
			else{
				salida=salida+entradaSplit[i];
			}
		}
		return salida;
	}
/*
	private String searchKeyword(String in){

	}
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

	private String choose(String[] lista){
		int largo = lista.length;
		int i =this.random.nextInt(largo);
		return lista[i];
	}


	private Pelicula recomendarPelicula(String genero){
		ArrayList<Pelicula> aux = new ArrayList();
		for(int i=0;i<this.cartelera.size();i++){
			if(this.cartelera.get(i).getGender().equals(genero)){
				aux.add(this.cartelera.get(i));
			}

		}
		return Collections.max(aux);
	}






	public Message generateMessage(Message inMessage){
		String[] inMessageSplit = inMessage.getMessage().split(" ");


	}


}