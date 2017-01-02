package ata;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Line {

	int id;
	
	String nom;
	Date date;
	String type;
	String immat;
	String ata;
	String tache;
	boolean formation;
	boolean execution;
	boolean controles;
	boolean encadrement;
	boolean aprs;
	
	boolean active;
	
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public Line(int id, String nom, Date date, String type, String immat, String ata,
			String tache, boolean formation, boolean execution,
			boolean controles, boolean encadrement, boolean aprs, boolean active) {
		super();
		this. id = id;
		this.nom = nom;
		this.date = date;
		this.type = type;
		this.immat = immat;
		this.ata = ata;
		this.tache = tache;
		this.formation = formation;
		this.execution = execution;
		this.controles = controles;
		this.encadrement = encadrement;
		this.aprs = aprs;
		this.active = active;
	}

	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImmat() {
		return immat;
	}

	public void setImmat(String immat) {
		this.immat = immat;
	}

	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}

	public String getTache() {
		return tache;
	}

	public void setTache(String tache) {
		this.tache = tache;
	}

	public boolean isFormation() {
		return formation;
	}

	public void setFormation(boolean formation) {
		this.formation = formation;
	}

	public boolean isExecution() {
		return execution;
	}

	public void setExecution(boolean execution) {
		this.execution = execution;
	}

	public boolean isControles() {
		return controles;
	}

	public void setControles(boolean controles) {
		this.controles = controles;
	}

	public boolean isEncadrement() {
		return encadrement;
	}

	public void setEncadrement(boolean encadrement) {
		this.encadrement = encadrement;
	}

	public boolean isAprs() {
		return aprs;
	}

	public void setAprs(boolean aprs) {
		this.aprs = aprs;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}



	public String toString(){
		String ret = df.format(this.getDate()) + "  |   "
				+ this.getImmat() + "  |   "
				+ this.getType() + "  |   "
				+ this.getAta() + "  |   "
				+ this.getTache();
		
		if(this.isControles()){
			ret += "  |   Contrôles";
		}
		
		if(this.isExecution()){
			ret += "  |   Execution";
		}
		
		if(this.isFormation()){
			ret += "  |   Formation";
		}
		
		if(this.isEncadrement()){
			ret += "  |   Encadrement";
		}
		
		if(this.isAprs()){
			ret += "  |   APRS";
		}
		
		ret += "  |   " + this.getNom();
		
		return ret;
		
	}
	
}
