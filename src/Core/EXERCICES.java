package Core;

public enum EXERCICES {

	SYNONYME, SUPERCHOIX, TRICHOIX, MULTICHOIX, TEST;
	
	public String toString() {
		switch(this) {
		case SYNONYME: return "Synonyme";
		case SUPERCHOIX: return "SuperChoix";
		case TRICHOIX: return "TriChoix";
		case MULTICHOIX: return "MultiChoix";
		case TEST: return "test";
		default: return "inconnu";
		}
	}
	
}
