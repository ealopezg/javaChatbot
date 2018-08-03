public class Message{
	private User sender;
	private String message;
	private String hour;
	private String keyboard

	public Message(String message,String sender,String hour){
		this.message=message;
		this.sender=sender;
		this.hour=hour;
	}

	private String getKeyboard(){
		if 
	}

	public boolean isCommand(){
		if(msg.charAt(0)=='!'){
			return true;
		}
		return false;
	}

	public String genString(){
		str = usuario.nombre + "" + mensaje;
		this.mensajes.add(str);
	}

	public String getSender(){
		return this.sender;
	}

}