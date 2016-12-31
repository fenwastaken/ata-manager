package ata;

public class Line {

	String nom;
	String date;
	String type;
	String immat;
	String ata;
	String tache;
	boolean formation;
	boolean execution;
	boolean controles;
	boolean encadrement;
	boolean aprs;
	
	public Line(String nom, String date, String type, String immat, String ata,
			String tache, boolean formation, boolean execution,
			boolean controles, boolean encadrement, boolean aprs) {
		super();
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
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
	
	
	
}
