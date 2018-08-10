import java.util.*;
import java.io.*;

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


		

		User usuario = new User();
		Scanner keyboard = new Scanner(System.in);
		Log log = new Log();
		int seed = 0;
		Chatbot chatbot =new Chatbot();
		Message inMessage;
		Message outMessage;
	
		boolean flag = true;
		boolean started = false;
		boolean command = false;
		System.out.println("Este chatbot es de un Cine.\nte recomienda peliculas de la cartelera, \ny te muestra los horarios para una película en especifico\nPara iniciar el chat usa el comando '!beginDialog seed '\n");
		while(flag){
			System.out.print(usuario.getNombre()+"> ");
			inMessage = new Message(keyboard.nextLine(),usuario.getNombre());
			if(( inMessage.isEndDialog() || inMessage.isSaveLog() || inMessage.isRate()) && !started){
				System.out.println("[!] No puedes utilizar este comando si no has iniciado el chat con !beginDialog");
			}
			else if((inMessage.isBeginDialog() || inMessage.isLoadLog())&& started){
				System.out.println("[!] No puedes utilizar este comando si has iniciado el chat");
			}
			else{
				if( !inMessage.isLoadLog() || !inMessage.isExit() ){
					log.writeToLog(inMessage);
				}
				command = inMessage.isCommand();

				if(inMessage.isExit()){
					flag=false;
				}


				if(inMessage.isEndDialog()){
					started = false;
					outMessage = chatbot.finalMessage();
					System.out.println(outMessage.getSender()+"> "+outMessage.getMessage());
					log.writeToLog(outMessage);
				}

				if(inMessage.isSaveLog()){
					try {
						log.saveLog();
					}
					catch(IOException e) {
						  System.out.println("ERROR");
						}
				}

				if(inMessage.isLoadLog() && !started){
					String[] argumentos = inMessage.commandArgs();
					ArrayList<Message> listaMensajes = log.loadLog(argumentos[0]);
					Date cbotDate = new Date();

					if(listaMensajes.size()>0){
						String anuncio = "Archivo "+argumentos[0]+" cargado completamente";
						System.out.println(anuncio);
					}


					if(listaMensajes.size()>0 &&listaMensajes.get(1)!=null && listaMensajes.get(1).getSender().equals("CHATBOT")){
						cbotDate = listaMensajes.get(1).getDate();
					}
					

					for(int i=0;i<listaMensajes.size();i++){
						if(!listaMensajes.get(i).getSender().equals("CHATBOT")){
							cbotDate=listaMensajes.get(i).getDate();
							
							inMessage = listaMensajes.get(i);
							log.writeToLog(inMessage);
							command = inMessage.isCommand();


							if(inMessage.isEndDialog()){
								started = false;
								outMessage = chatbot.finalMessage(cbotDate);
								System.out.println(outMessage.getSender()+"> "+outMessage.getMessage());
								log.writeToLog(outMessage);
								usuario = new User();

							}


							if(started && !command){
								int[] cNU=inMessage.determinarCaminoNivel();
								String[] strs ={};
								if(!inMessage.dijoGenero().equals(" ") && cNU[0]==1 && cNU[1]==1 && !usuario.hasGenero()){
									String genero = inMessage.dijoGenero();
									strs = chatbot.recomendarPelicula(genero);
									usuario.setGenero(genero);
								}
								if(!inMessage.dijoDia().equals(" ") && cNU[0]==2 && cNU[1]==1 && !usuario.hasDia()){
									String dia = inMessage.dijoDia();
									usuario.setDia(dia);
								}
								if(!inMessage.dijoPelicula().equals(" ") && cNU[0]==2 && cNU[1]==2 && !usuario.hasPelicula()){
									String pelicula = inMessage.dijoPelicula();
									strs = chatbot.devolverHorarios(pelicula,usuario.getDia());
									usuario.setPelicula(pelicula);
								}

								outMessage = chatbot.generateMessage(inMessage,log.getLastMessageFrom("CHATBOT").getMessage(),strs,cbotDate);
								log.writeToLog(outMessage);


							}

							if(inMessage.isBeginDialog() && !started){
								seed = Integer.parseInt(inMessage.commandArgs()[0]);
								chatbot = new Chatbot(seed,peliculas);
								outMessage = chatbot.initialMessage(cbotDate);
								log.writeToLog(outMessage);
								started=true;
							}


							

						}	

					}


					
				}

				if(started && !command){
					int[] cNU=inMessage.determinarCaminoNivel();
					String[] strs ={};
					if(!inMessage.dijoGenero().equals(" ") && cNU[0]==1 && cNU[1]==1 && !usuario.hasGenero()){
						String genero = inMessage.dijoGenero();
						strs = chatbot.recomendarPelicula(genero);
						usuario.setGenero(genero);
					}
					if(!inMessage.dijoDia().equals(" ") && cNU[0]==2 && cNU[1]==1 && !usuario.hasDia()){
						String dia = inMessage.dijoDia();
						usuario.setDia(dia);
					}
					if(!inMessage.dijoPelicula().equals(" ") && cNU[0]==2 && cNU[1]==2 && !usuario.hasPelicula()){
						String pelicula = inMessage.dijoPelicula();
						strs = chatbot.devolverHorarios(pelicula,usuario.getDia());
						usuario.setPelicula(pelicula);
					}

					outMessage = chatbot.generateMessage(inMessage,log.getLastMessageFrom("CHATBOT").getMessage(),strs);
					System.out.println(outMessage.getSender()+"> "+outMessage.getMessage());
					log.writeToLog(outMessage);


				}

				if(inMessage.isBeginDialog() && !started){
					seed = Integer.parseInt(inMessage.commandArgs()[0]);
					chatbot = new Chatbot(seed,peliculas);
					outMessage = chatbot.initialMessage();
					System.out.println(outMessage.getSender()+"> "+outMessage.getMessage());
					log.writeToLog(outMessage);
					started=true;
				}
			}




		}
		System.out.print(log.toString());


	}










	
}