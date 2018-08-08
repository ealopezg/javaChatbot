import java.util.*;

public class Log{
	private ArrayList<Message> mensajes;

	public Log(){
		this.mensajes = new ArrayList(); 
	}

	public Log(String nombreArchivo){
		
	}

	public void writeToLog(Message mensaje){
		this.mensajes.add(mensaje);
	}

	public String toString(){
		String salida="";
		for(int i=0;i<mensajes.size();i++){
			salida = salida + mensajes.get(i).getDateStr() +" "+ mensajes.get(i).getSender() + ": " + mensajes.get(i).getMessage() + "\n";
		}
		return salida;
	}

}