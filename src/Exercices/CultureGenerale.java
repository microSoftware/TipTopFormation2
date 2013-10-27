package Exercices;

import Core.EXERCICES;
import Core.THEMES;

public class CultureGenerale extends QuestionReponse {
	
	private static final String phraseEntete="Remettez les éléments dans l'ordre";//a adapter pour les thèmes ...
	private String question;
	private String[] lesElements = new String[2];
	
	public CultureGenerale() {
		super();
		typeExo = EXERCICES.TEST;
		remplirLesVariables();
		
		
		
	}
	
	public void remplirLesVariables (){
		
		//String chaine[] = lireCSV(null);
		//id = Integer.parseInt(chaine[0]);
		//question= chaine[1];
		
		// lesElements = extraireTousElementsTableau(chaine, 2);
		
	
		 id = 1;
		 question= "Ceci est ma question";
		 lesElements[0] = "élement 1";
		 lesElements[1] = "élement 2";
	}
	
}
