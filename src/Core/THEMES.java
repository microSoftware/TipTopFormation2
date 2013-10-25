package Core;

public enum THEMES {
	
	MENAGE, MATHS, CULTURE_GENERALE, FRANCAIS;
	
	public String toString() {
		switch(this) {
		case MENAGE: return "menage";
		case MATHS: return "maths";
		case CULTURE_GENERALE: return "cultureGenerale";
		case FRANCAIS: return "francais";
		default: return "inconnu";
		}
	}
	
	
	
}
