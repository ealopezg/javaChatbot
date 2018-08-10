import java.util.*;
/**
 * Calificacion del chatbot y del usuario.
 * Utiliza la interfaz comparable para poder
 * ordenarlos por fecha.
 */
public class Rate implements Comparable<Rate>{
	/**
	 * Entero de 0 a 5 con la nota del chatbot
	 */
	private int notaChatbot;

	/**
	 * Entero de 0 a 5 con la nota del usuario
	 */
	private int notaUsuario;

	/**
	 * Fecha de cuando fue evaluado
	 */
	private Date fecha;

	/**
	 * Constructor del objeto
	 * @param  nC nota del chatbot
	 * @param  nU nota del usuario
	 * @return    objeto rate
	 */
	public Rate(int nC,int nU){
		this.notaChatbot=nC;
		this.notaUsuario=nU;
		this.fecha = new Date();
	}

	/**
	 * Constructor del objeto con fecha
	 * Utilizado en loadLog
	 * @param  nC   nota del chatbot
	 * @param  nU   nota del usuario
	 * @param  date fecha
	 * @return      objeto rate
	 */
	public Rate(int nC,int nU,Date date){
		this.notaChatbot=nC;
		this.notaUsuario=nU;
		this.fecha = date;
	}

	/**
	 * Devuelve la nota del usuario
	 * @return Entero con la nota del usuario
	 */
	public int getNotaUsuario(){
		return this.notaUsuario;
	}

	/**
	 * Devuelve la nota del chatbot
	 * @return Entero con la nota del chatboy
	 */
	public int getNotaChatbot(){
		return this.notaChatbot;
	}

	/**
	 * Devuelve la fecha de cuando fue
	 * creado
	 * @return Fecha de creacion
	 */
	public Date getDate(){
		return this.fecha;
	}
	
	@Override
	/**
	 * Implementacion de la interfaz Comparable
	 * @param  otroRate otro objeto rate a comparar
	 * @return          entero
	 */
	public int compareTo(Rate otroRate){
		return this.fecha.compareTo(otroRate.getDate());
	}



}