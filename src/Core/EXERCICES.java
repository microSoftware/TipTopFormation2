package Core;

public enum EXERCICES {

	SYNONYME, DANGEROUS, TRICHOIX;
	
	public String toString() {
		switch(this) {
		case SYNONYME: return "Synonyme";
		case DANGEROUS: return "Dangerous";
		case TRICHOIX: return "TriChoix";
		default: return "inconnu";
		}
	}
	
}
