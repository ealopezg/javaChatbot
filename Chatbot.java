import java.util.*;

public class Chatbot{
	private int personalidad;
	private int seed;
	private Pelicula[] cartelera;
	private ArrayList<String> respuestas;
	private Random random;
	

	public Chatbot(int personalidad,int seed,ArrayList respuestas){
		this.personalidad = personalidad;
		this.seed = seed;
		this.random = new Random(seed);
		





	}


	public Message generateMessage(Message inMessage){
		Message out = new Message()
	}

}