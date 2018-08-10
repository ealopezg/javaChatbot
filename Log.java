import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Log{
	private ArrayList<Message> mensajes;

	public Log(){
		this.mensajes = new ArrayList(); 
	}

	

	public void writeToLog(Message mensaje){
		this.mensajes.add(mensaje);
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

	public String toString(){
		String salida="";
		for(int i=0;i<mensajes.size();i++){
			salida = salida + mensajes.get(i).getDateStr() +">"+ mensajes.get(i).getSender() + ">" + mensajes.get(i).getMessage() + "\n";
		}
		return salida;
	}

	public Message getLastMessageFrom(String sender){
		for(int i = (this.mensajes.size()-1);i> -1;i--){
			if(this.mensajes.get(i).getSender().equals(sender)){
				return this.mensajes.get(i);
				}
		}
		return new Message("",sender);
	}

}