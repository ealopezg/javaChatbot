import java.util.*;

public class Main{
	public static void main(String[] args) {
		String[] listaStringPeliculas = {
										"Avengers: Infinity war,ACCION,89,11:00;14:40;18:00;21:30!14:40;18:00;18:30;21:30!12:00;15:40;17:00;19:00!11:00;14:40;18:00;21:30!14:40;18:00;18:30;21:30!12:00;15:40;17:00;19:00!11:00;14:40;18:00;21:30",
										"Deadpool 2,ACCION,82,12:10;15:00;17:50;20:40!14:10;17:00;19:50;22:40!12:10;15:00;17:50;20:40!12:10;15:00;17:50;20:40!14:10;17:00;19:50;22:40!12:10;15:00;17:50;20:40!12:10;15:00;17:50;20:40",
										"Desobediencia,DRAMA,67,12:50;15:30;18:20;21:10!12:40;15:30;18:20;21:10!12:40;15:30;18:20;21:10!12:50;15:30;18:20;21:10!12:40;15:30;18:20;21:10!12:40;15:30;18:20;21:10!12:50;15:30;18:20;21:10",
										"Gnomos Al Ataque,INFANTIL,55,11:50;14:20!14:20;16:30!14:20;16:30!11:50;14:20!14:20;16:30!14:20;16:30!11:50;14:20",
										"Isla de Perros,ANIMACION,76,11:40;14:15;16:40!14:15;16:40;21:50!14:15;16:40;21:50!11:40;14:15;16:40!14:15;16:40;21:50!14:15;16:40;21:50!11:40;14:15;16:40",
										"La Isla De Los Pinguinos,DRAMA,78,14:00!14:00!14:00!14:00!14:00!14:00!14:00",
										"La Profecia,TERROR,76,15:00!21:00!15:00!15:00!21:00!15:00!15:00",
										"Los Extranos Caceria Nocturna,TERROR,56,12:30;14:50;17:10!12:30;14:50;17:10!12:30;14:50;17:10!12:30;14:50;17:10!12:30;14:50;17:10!12:30;14:50;17:10!12:30;14:50;17:10",
										"Rampage: Devastacion,ACCION,53,11:20;14:00!14:00;16:40!14:00;16:40!11:20;14:00!14:00;16:40!14:00;16:40!11:20;14:00",
										"Sexy Por Accidente,COMEDIA,45,11:50;14:20;16:50;19:30!14:20;16:50;19:30;22:10!14:20;16:50;19:30;22:10!11:50;14:20;16:50;19:30!14:20;16:50;19:30;22:10!14:20;16:50;19:30;22:10!11:50;14:20;16:50;19:30",
										"Sherlock Gnomes,INFANTIL,49,10:30;12:45!13:50;16:10!12:20;14:40!10:30;12:45!13:50;16:10!12:20;14:40!10:30;12:45",
										"Tully,DRAMA,73,13:10;15:50;18:30;21:00!13:10;15:50;18:30;21:00!13:10;15:50;18:30;21:00!13:10;15:50;18:30;21:00!13:10;15:50;18:30;21:00!13:10;15:50;18:30;21:00!13:10;15:50;18:30;21:00",
										"Yo Soy Simon,ROMANCE,81,17:20;19:10!12:50;19:10!12:50;19:10!17:20;19:10!12:50;19:10!12:50;19:10!17:20;19:10"
										};
		ArrayList<Pelicula> peliculas = new ArrayList();

		for(int i = 0; i < listaStringPeliculas.length ; i++){
			Pelicula peliculaNueva = new Pelicula(listaStringPeliculas[i]);
			peliculas.add(peliculaNueva);
		}

		

		User usuario = new User("Esteban");
		Scanner keyboard = new Scanner(System.in);
		Log log = new Log();
		int seed = 0;
		Chatbot chatbot;
		Message inMessage;
		Message outMessage;
	
		boolean flag = true;
		boolean started = false;
		while(flag){
			System.out.print(usuario.getNombre()+"> ");
			inMessage = new Message(keyboard.nextLine(),usuario.getNombre());
			log.writeToLog(inMessage);
			if(inMessage.isEndDialog()){
				flag=false;
			}
			if(inMessage.isBeginDialog() && started==false){
				seed = Integer.parseInt(inMessage.commandArgs()[0]);
				chatbot = new Chatbot(seed,peliculas);
				outMessage = chatbot.initialMessage();
				System.out.println(outMessage.getSender()+"> "+outMessage.getMessage());
				log.writeToLog(outMessage);
				started=true;
			}


		}
		System.out.print(log.toString());


	}







	
}