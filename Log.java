import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.Math;

public class Log{
	private ArrayList<Message> mensajes;
	private ArrayList<Rate> listaRate;

	public Log(){
		this.mensajes = new ArrayList();
		this.listaRate = new ArrayList(); 
	}

	public int size(){
		return mensajes.size();
	}

	

	public void writeToLog(Message mensaje){
		this.mensajes.add(mensaje);
	}

	public void ordenarLog(){
		Collections.sort(this.mensajes);
		Collections.sort(this.listaRate);
	}

	public void chatbotPerformance(){
		String salida="";
		if(listaRate.size()==0){
			salida= "No hay evaluaciones disponibles\n";
		}
		else{
			ArrayList<Integer> listaChatbot = new ArrayList();
			ArrayList<Integer> listaUsuario = new ArrayList();
			for(int i=0;i<listaRate.size();i++){
				if(listaRate.get(i).getNotaChatbot()!=0){
					listaChatbot.add(listaRate.get(i).getNotaChatbot());
				}
				if(listaRate.get(i).getNotaUsuario()!=0){
					listaUsuario.add(listaRate.get(i).getNotaUsuario());
				}
			}
			double promedioChatbot = promedio(listaChatbot);
			double promedioUsuario = promedio(listaUsuario);
			double desviacionChatbot=desviacionEstandar(listaChatbot);
			double desviacionUsuario=desviacionEstandar(listaUsuario);
			salida = 	"[!]  CHATBOT PERFORMANCE\n"+
						"Cantidad de Evaluaciones: "+
						"	Chatbot: "+Integer.toString(listaChatbot.size())+"\n"+
						"	Usuario: "+Double.toString(listaUsuario.size())+"\n"+
						"Promedios:\n"+
						"	Chatbot: "+Double.toString(promedioChatbot)+"\n"+
						"	Usuario: "+Double.toString(promedioUsuario)+"\n"+
						"Desviacion Estandar\n"+
						"	Chatbot: "+Double.toString(desviacionChatbot)+"\n"+
						"	Usuario: "+Double.toString(desviacionUsuario)+"\n";
			}
		System.out.print(salida);
	}


	private double desviacionEstandar(ArrayList<Integer> listaNumeros){
		double promedio = promedio(listaNumeros);
		double sumatoria =0;
		for(int i=0;i<listaNumeros.size();i++){
			sumatoria = sumatoria + Math.pow((listaNumeros.get(i)-promedio),2);
		}
		if(listaNumeros.size()==0){
			return 0;
		}
		return Math.sqrt(sumatoria/listaNumeros.size());
	}


	private double promedio(ArrayList<Integer> listaNumeros){
		double suma = 0;
		for(int i=0;i<listaNumeros.size();i++){
			suma=suma+listaNumeros.get(i);
		}
		if(listaNumeros.size()==0){
			return 0;
		}
		return suma/listaNumeros.size();
	}

	public void writeRate(Rate calificacion){
		this.listaRate.add(calificacion);
	}	

	public ArrayList<Message> loadLog(String fileName){
		ArrayList<Message> mensajes=new ArrayList();

        String line = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
        Message message;

        try {

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	String[] lineaSplit = line.split(">");
            	String fechaString = lineaSplit[0];
            	String sender = lineaSplit[1];
            	String messageString = lineaSplit[2];
            	Date date = dateFormat.parse(fechaString);
            	message = new Message(messageString,sender,date);
                mensajes.add(message);

            }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "No se ha encontrado el archivo '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error leyendo el archivo '" 
                + fileName + "'");                  
        }
        catch(ParseException ex){
        	System.out.println("ERROR EN LAS FECHAS");
        }
        return mensajes;

		
	}



	public void saveLog() throws IOException{
		String strLog = this.toString();
		Date date = new Date();
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		String fileName = form.format(date)+".log";
	    FileWriter fileWriter = new FileWriter(fileName);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.print(strLog);
	    printWriter.close();


	}

	public void saveToJson() throws IOException{
		TimeZone tz = TimeZone.getTimeZone("UTC");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		df.setTimeZone(tz);
		String strLog="{\n\t\"log\": [\n";
		int j=0;
		for(int i=0;i<(this.mensajes.size()-1);i++){

			String fechaFormateada = df.format(this.mensajes.get(i).getDate());
			strLog=strLog +	"\t\t{\n"+
							"\t\t\t\"date\": \""+fechaFormateada+"\",\n"+
							"\t\t\t\"sender\": \""+this.mensajes.get(i).getSender()+"\",\n"+
							"\t\t\t\"message\": \""+this.mensajes.get(i).getMessage()+"\"\n"+
							"\t\t},\n";
			j=j+1;
		}

		String fechaFormateada = df.format(this.mensajes.get(j).getDate());
		strLog=strLog +	"\t\t{\n"+
						"\t\t\t\"date\": \""+fechaFormateada+"\",\n"+
						"\t\t\t\"sender\": \""+this.mensajes.get(j).getSender()+"\",\n"+
						"\t\t\t\"message\": \""+this.mensajes.get(j).getMessage()+"\"\n"+
						"\t\t}\n"+
						"\t]\n"+
						"}";
		Date date = new Date();
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		String fileName = form.format(date)+".json";
	    FileWriter fileWriter = new FileWriter(fileName);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.print(strLog);
	    printWriter.close();


	}

	public void saveToXML() throws IOException{
		TimeZone tz = TimeZone.getTimeZone("UTC");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		df.setTimeZone(tz);
		String strLog=	"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n\n\n"+
						"<Log>\n";
		for(int i=0;i<this.mensajes.size();i++){
			String fechaFormateada = df.format(this.mensajes.get(i).getDate());
			strLog=strLog +	"\t<Mensaje>\n"+
							"\t\t<Date>"+fechaFormateada+"</Date>\n"+
							"\t\t<Sender>"+this.mensajes.get(i).getSender()+"</Sender>\n"+
							"\t\t<Content>"+this.mensajes.get(i).getMessage()+"</Content>\n"+
							"\t</Mensaje>\n";
		}
		strLog =strLog + "</Log>";
		Date date = new Date();
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		String fileName = form.format(date)+".xml";
	    FileWriter fileWriter = new FileWriter(fileName);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.print(strLog);
	    printWriter.close();


	}



	public String toString(){
		String salida="";
		for(int i=0;i<mensajes.size();i++){
			salida = salida + mensajes.get(i).getDateStr() +">"+ mensajes.get(i).getSender() + ">" + mensajes.get(i).getMessage() + "\n";
		}
		return salida;
	}



	public Message getLastMessageFrom(String sender){
		for(int i = (this.mensajes.size()-1);i> -1;i--){
			if(this.mensajes.get(i).getSender().equals(sender) && !this.mensajes.get(i).getMessage().substring(0,3).equals("[!]")){
				return this.mensajes.get(i);
				}
		}
		return new Message("",sender);
	}


}