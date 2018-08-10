import java.util.*;

public class Rate implements Comparable<Rate>{
	private int notaChatbot;
	private int notaUsuario;
	private Date fecha;

	public Rate(int nC,int nU){
		this.notaChatbot=nC;
		this.notaUsuario=nU;
		this.fecha = new Date();
	}

	public Rate(int nC,int nU,Date date){
		this.notaChatbot=nC;
		this.notaUsuario=nU;
		this.fecha = date;
	}

	public int getNotaUsuario(){
		return this.notaUsuario;
	}

	public int getNotaChatbot(){
		return this.notaChatbot;
	}

	public Date getDate(){
		return this.fecha;
	}

	public int compareTo(Rate otroRate){
		return this.fecha.compareTo(otroRate.getDate());
	}



}