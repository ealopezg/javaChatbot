import java.util.*;
import java.text.SimpleDateFormat;

public class Message implements Comparable<Message>{
	private String sender;
	private String message;
	private Date date;
	private String command;

	public Message(String message,String sender){
		this.message=message;
		this.sender=sender;
		this.date=new Date();
	}

	public Message(String message,String sender,Date fecha){
		this.message=message;
		this.sender=sender;
		this.date=fecha;

	}

	public boolean isCommand(){
		if(this.message.length()>0){
			if(this.message.charAt(0)=='!'){
				return true;
			}
			return false;
		}
		return false;
	}

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

	public boolean isBeginDialog(){
		String[] messageSplit = this.message.split(" ");
		return (messageSplit[0].equals("!beginDialog") && ((messageSplit.length==1) || ((messageSplit.length==2) && messageSplit[1].matches("-?\\d+"))));
	}

	public boolean isEndDialog(){
		return this.message.equals("!endDialog");
	}

	public boolean isSaveLog(){
		return this.message.equals("!saveLog");
	}

	public boolean isChatbotPerformance(){
		return this.message.equals("!chatbotPerformance");
	}

	public boolean isSaveToJson(){
		return this.message.equals("!saveToJson");
	}
	public boolean isSaveToXml(){
		return this.message.equals("!saveToXml");
	}

	public boolean isHelp(){
		return this.message.equals("!help");
	}



	public boolean isLoadLog(){
		String[] messageSplit = this.message.split(" ");
		return (messageSplit[0].equals("!loadLog") && (messageSplit.length==2));
	}

	public boolean isRate(){
		String[] messageSplit = this.message.split(" ");
		return (messageSplit[0].equals("!rate") && (messageSplit.length==3));
	}

	public boolean isExit(){
		return this.message.equals("!exit");
	}

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

	public String dijoPelicula(){
		String[] listaPeliculas={"Avengers: Infinity war","Deadpool 2","Desobediencia","Gnomos Al Ataque","Isla de Perros","La Isla De Los Pinguinos","La Profecia","Los Extranos Caceria Nocturna","Rampage: Devastacion","Sexy Por Accidente","Sherlock Gnomes","Tully","Yo Soy Simon"};
		for(int j = 0;j<listaPeliculas.length;j++){
			if(this.message.toUpperCase().contains(listaPeliculas[j].toUpperCase())){
				return listaPeliculas[j];
			}
		}
		return "";
	}

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

	public String toString(){
		return this.getDateStr()+">"+this.getSender()+">"+this.getMessage();
	}

	public String getSender(){
		return this.sender;
	}

	public Date getDate(){
		return this.date;
	}

	public String getDateStr(){
		SimpleDateFormat form = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
		return form.format(this.date);

	}

	public String getMessage(){
		return this.message;
	}

	public int compareTo(Message otroMensaje){
		return this.date.compareTo(otroMensaje.getDate());
	}


}