public class Hora{
	public int hor;
	public int min;

	public Hora(int h,int m){
		if (h>0 && h<23 && m>0 && m<59) {					
			this.hor=h;
			this.min=m;
		}
		else{
			this.hor=0;
			this.min=0;
		}

	}

	public String hToStr(){
		String str;
		String horStr;
		String minStr;
		if (this.hor<10) {
			horStr ="0"+integer.toStr(this.hor);
		}
		else{
			horStr = integer.toStr(this.hor);
		}

		if (this.min<10) {
			horStr ="0"+integer.toStr(this.min);
		}
		else{
			horStr = integer.toStr(this.min);
		}

		return horStr + ":" + minStr;
	}
}