package Exercices;

import Core.EXERCICES;
import Core.THEMES;

public class CultureGenerale extends QuestionReponse {
	
	private static final String phraseEntete="Remettez les éléments dans l'ordre";
	private String question;
	private String[] lesElements = new String[2];
	
	/*
	 * Historique des ids pour ne pas avoir 2 fois la même question.
	 */
	protected static int nbQuestionDejaAjouter=0;
	protected static int[] tabQuestionHistorique;
	
	public CultureGenerale() {
		super();
		typeExo = EXERCICES.TEST;
		tabQuestionHistorique = new int[getJeu().getQuizz().getNbquestionparquizz()];
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
