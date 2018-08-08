import java.util.*;
import java.text.SimpleDateFormat;

public class Message{
	private String sender;
	private String message;
	private Date date;
	private String command;

	public Message(String message,String sender){
		this.message=message;
		this.sender=sender;
		this.date=new Date();

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
		return (messageSplit[0].equals("!beginDialog") && (messageSplit.length==2) && messageSplit[1].matches("-?\\d+"));
	}

	public boolean isEndDialog(){
		return this.message.equals("!endDialog");
	}

	public boolean isSaveLog(){
		return this.message.equals("!saveLog");
	}

	public boolean isLoadLog(){
		String[] messageSplit = this.message.split(" ");
		return (messageSplit[0].equals("!loadLog") && (messageSplit.length==2));
	}

	public boolean isRate(){
		String[] messageSplit = this.message.split(" ");
		return (messageSplit[0].equals("!rate") && (messageSplit.length==3));
	}




	public String getSender(){
		return this.sender;
	}

	public Date getDate(){
		return this.date;
	}

	public String getDateStr(){
		SimpleDateFormat form = new SimpleDateFormat("[yyyy-mm-dd hh:mm:ss]");
		return form.format(this.date);

	}

	public String getMessage(){
		return this.message;
	}


}