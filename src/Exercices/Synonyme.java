package Exercices;

import java.util.ArrayList;
import java.util.List;

import Core.EXERCICES;
import android.util.Log;

public class Synonyme extends QuestionReponse{

	
	private int NbBonnesReponses;
	private String[] lesElements;

	/*
	 * Historique des ids pour ne pas avoir 2 fois la même question.
	 */
	private static List<String> tabQuestionHistorique;//liste des id que l'on a déjà
	
	public static void viderTabQuesHistorique (){
		if (tabQuestionHistorique != null)
			tabQuestionHistorique.clear();
	}
	
	public Synonyme() {
		super();
		
		typeExo = EXERCICES.SYNONYME;
		if (Synonyme.tabQuestionHistorique == null) 
			Synonyme.tabQuestionHistorique = new ArrayList<String>();
		lesElements = new String[5];
		remplirLesVariables();
		Log.w("Synonyme", "Constructeur");
		
	}
	
	public void remplirLesVariables () {
		 Log.w("Synonyme", "remplirLesVariables()");
		
		 String[] tabData = recupererQuestion();
		 
		 
		 id = tabData[0];
		 question= tabData[1];
		 phraseCorrection = tabData[2];
		 Log.w("", "NbBonnesReponses      #"+tabData[3]+"# AND class = "+tabData[3].getClass());
		 NbBonnesReponses = Integer.parseInt(tabData[3]);
		 
		
		 lesElements[0] = tabData[4];
		 lesElements[1] = tabData[5];
		 lesElements[2] = tabData[6];
			
		try {
		if (tabData[7]!=null)
			 lesElements[3] = tabData[7]; 
		
		if (tabData[8]!=null)
			 lesElements[4] = tabData[8]; 
		}
		catch (ArrayIndexOutOfBoundsException e){
			//c'est qu'il n'y a pas d'élément
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
		Log.w("", "La liste des id historique : "+Synonyme.tabQuestionHistorique);
		String[][] tabTexte = extraireTousElementsTableau();
	
		
		int nombreElementTableau = tabTexte.length;
		
		boolean ok = false;
		int nbEssai = 10; //eviter une boucle infini
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
				Synonyme.tabQuestionHistorique.add(idQuestion);
				ok=true;//on sort de la boucle
				return question;
			}
		}
		
		//si on a les déjà tous fait 
		//on rajoute première question du fichier
		return tabTexte[0];
	}
		
	private boolean isDejaCetteQuestion(String id){
		
		for (String idDejaFait : Synonyme.tabQuestionHistorique){
			
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

	public String[] getLesElements() {
		return lesElements;
	}
	
	
	
}
