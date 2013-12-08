package Exercices;

import Core.EXERCICES;
import android.util.Log;

public class SuperChoix extends QuestionReponse {

	private String[]lesElements;
	protected String image;
	/*
	 * Historique des ids pour ne pas avoir 2 fois la même question.
	 */
	protected static int nbQuestionDejaAjouter=0;
	protected static int[] tabQuestionHistorique;
	
	
	
	public SuperChoix() {
		super();
		typeExo = EXERCICES.SUPERCHOIX;
		tabQuestionHistorique = new int[getJeu().getQuizz().getNbquestionparquizz()];
		lesElements = new String[5];
		remplirLesVariables();
		Log.w("SuperChoix", "Constructeur");
	}
	
	public void remplirLesVariables (){
		Log.w("SuperChoix", "remplirLesVariables()");
		//String chaine[] = lireCSV(null);
		//id = Integer.parseInt(chaine[0]);
		//question= chaine[1];
		
		// lesElements = extraireTousElementsTableau(chaine, 2);
		
	
		 id = 1;
		 question= "Glisser la réponse dans la case";
		 image = "ic_launcher";
		 lesElements[0] = "Bonne réponse 1";
		 lesElements[1] = "Mauvaise réponse 1";
		 lesElements[2] = "Mauvaise réponse 2";
		 phraseCorrection = "La correction de la question";
	}

	public String[] getLesElements() {
		return lesElements;
	}

	public String getImage() {
		return image;
	}



}
