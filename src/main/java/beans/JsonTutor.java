package beans;

import java.util.ArrayList;

public class JsonTutor {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private ArrayList<Tutor> data;
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public ArrayList<Tutor> getData() {
		return data;
	}
	public void setData(ArrayList<Tutor> data) {
		this.data = data;
	}
	
	

}
