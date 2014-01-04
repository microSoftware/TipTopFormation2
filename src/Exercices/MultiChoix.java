package Exercices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Core.EXERCICES;
import android.util.Log;

public class MultiChoix extends QuestionReponse {
	
	private int NbBonnesReponses;
	private String[][] lesElements;

	/*
	 * Historique des ids pour ne pas avoir 2 fois la même question.
	 */
	private static List<String> tabQuestionHistorique;//liste des id que l'on a déjà
	
	public MultiChoix() {
		super();
		typeExo = EXERCICES.MULTICHOIX;
		if (MultiChoix.tabQuestionHistorique == null) 
			MultiChoix.tabQuestionHistorique = new ArrayList<String>();
		lesElements = new String[5][5];
		remplirLesVariables();
		Log.w("MultiChoix", "Constructeur");
		
		
	}
	
	
	public void remplirLesVariables () {
		 Log.w("SuperChoix", "remplirLesVariables()");
		
		 String[] tabData = recupererQuestion();
		 Log.w("", "Test de l'id      #"+tabData[0]+"#");
		 
		 id = tabData[0];
		 question= tabData[1];
		 phraseCorrection = tabData[2];
		 NbBonnesReponses = 1;//Integer.parseInt(tabData[3]);
		 
		 if (level == 1){
			 lesElements[0][0] = tabData[4];//la réponse texte 1
			 lesElements[0][1] = tabData[5];//la réponse image 1
			 
			 lesElements[1][0] = tabData[6];//la réponse texte 2
			 lesElements[1][1] = tabData[7];//la réponse image 2
		 }
		 else if (level == 2){
			 lesElements[0][0] = tabData[4];//la réponse texte 1
			 lesElements[1][0] = tabData[5];//la réponse image 1
			 lesElements[2][0] = tabData[6];//la réponse texte 3
		}
		else if (level == 3){
			 lesElements[0][0] = tabData[4];//la réponse texte 1
			 lesElements[1][0] = tabData[5];//la réponse image 1
			 lesElements[2][0] = tabData[6];//la réponse texte 3
			 lesElements[3][0] = tabData[7];//la réponse texte 3
		}
		 
	}
	
	private String[] recupererQuestion(){
		Log.w("a","---------------------------------------------");
		Log.w("a","---------------------------------------------");
		Log.w("a","---------------------------------------------");
		Log.w("a","--------------recupererQuestion-----------");
		Log.w("a","---------------------------------------------");
		Log.w("a","---------------------------------------------");
		Log.w("a","---------------------------------------------");
		Log.w("", "La liste des id historique : "+MultiChoix.tabQuestionHistorique);
		String[][] tabTexte = extraireTousElementsTableau();
	
		
		int nombreElementTableau = tabTexte.length;
		
		boolean ok = false;
		int nbEssai = 5; //eviter une boucle infini
		while (!ok && nbEssai >= 0){
			nbEssai--;
			int alea = (int) (Math.random()*nombreElementTableau);//0,1,2
			Log.w("", "alea = "+alea);
			String question[] = tabTexte[alea];
			Log.w("", "ques alea : "+ question[0]);
			String idQuestion = question[0];
			
			
			if (!isDejaCetteQuestion(idQuestion)){
				//on a pas encore cette question dans la liste
				//donc on ajoute l'id
				MultiChoix.tabQuestionHistorique.add(idQuestion);
				ok=true;//on sort de la boucle
				return question;
			}
		}
		
		//si on a les déjà tous fait 
		//on rajoute première question du fichier
		return tabTexte[0];
	}
		
	private boolean isDejaCetteQuestion(String id){
		
		for (String idDejaFait : MultiChoix.tabQuestionHistorique){
			
			if (id.equals(idDejaFait) ){
				//on a déjà cette question dans le quizz !
				Log.w("","on a déjà cette question !!!");
				return true;
			}
		}
		return false;
	}
	
	public int getNbBonnesReponses() {
		return NbBonnesReponses;
	}

	/*
	 *  - tab[0][0] = Element 0 : le texte ET tab[0][1] = Element 0 : l'image
	 *  - tab[1][0] = Element 1 : le texte ET tab[1][1] = Element 1 : l'image
	 *  ...
	 *  Cette méthode permet donc d'ajouter autant d'info que l'in veut par élément :
	 *  correction personnalisé, un deuxième images, une aide, une autre formulation du
	 *  texte...
	 */
	public String[][] getLesElements() {
		return lesElements;
	}
	
	
		
	
	
}
