package Exercices;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.example.tiptopformation2.R;

import Core.EXERCICES;
import Core.THEMES;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

public class SuperChoix extends QuestionReponse {

	private String[]lesElements;
	protected String image;
	/*
	 * Historique des ids pour ne pas avoir 2 fois la même question.
	 */
	private static List<String> tabQuestionHistorique;//liste des id que l'on a déjà
	
	
	
	public SuperChoix() {
		super();
		Log.w("SuperChoix", "Debut Constructeur");
		typeExo = EXERCICES.SUPERCHOIX;
		if (SuperChoix.tabQuestionHistorique == null) 
			SuperChoix.tabQuestionHistorique = new ArrayList<String>();
		lesElements = new String[5];
		remplirLesVariables();
		Log.w("SuperChoix", "FIN Constructeur");
	}
	
	public void remplirLesVariables (){
		 Log.w("SuperChoix", "remplirLesVariables()");
		
		 String[] tabData = recupererQuestion();
		 Log.w("", "Test de l'id      #"+tabData[0]+"#");
		 id = tabData[0];
		
		 question= tabData[2];
		 image = tabData[1];
		 lesElements[0] = tabData[3];
		 lesElements[1] = tabData[4];
		 if (level == 2)
			 lesElements[2] = tabData[5];
		
		 phraseCorrection = "La correction de la question";
	}
	
	
	private String[] recupererQuestion(){
		Log.w("a","---------------------------------------------");
		Log.w("a","---------------------------------------------");
		Log.w("a","---------------------------------------------");
		Log.w("a","--------------recupererQuestion-----------");
		Log.w("a","---------------------------------------------");
		Log.w("a","---------------------------------------------");
		Log.w("a","---------------------------------------------");
		Log.w("", "La liste des id historique : "+SuperChoix.tabQuestionHistorique);
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
				SuperChoix.tabQuestionHistorique.add(idQuestion);
				ok=true;//on sort de la boucle
				return question;
			}
		}
		
		//si on a les déjà tous fait 
		//on rajoute première question du fichier
		return tabTexte[0];
	}
	
	/*
	private String[] recupererQuestion1(){
		Log.w("a","---------------------------------------------");
		Log.w("a","---------------------------------------------");
		Log.w("a","---------------------------------------------");
		Log.w("a","--------------recupererQuestion1-----------");
		Log.w("a","---------------------------------------------");
		Log.w("a","---------------------------------------------");
		Log.w("a","---------------------------------------------");
		String[][] tabTexte = new String [5][];
		String[] tabData1 = {"04","menage_trestoxique","Glisser le mot correspondant au pictogramme1","Très toxique","Irritant","Polluant"};
		String[] tabData2 = {"05","menage_inflammable","Glisser le mot correspondant au pictogramme","Inflammable","Explosif","Comburant"};
		String[] tabData3 = {"06","menage_irritant","Glisser le mot correspondant au pictogramme","Irritant","Nocif","Toxique"};
		String[] tabData4 = {"07","menage_trestoxique","Glisser le mot correspondant au pictogramme2","Très toxique","Irritant","Polluant"};
		String[] tabData5 = {"08","menage_trestoxique","Glisser le mot correspondant au pictogramme3","Très toxique","Irritant","Polluant"};
		tabTexte[0] = tabData1;
		tabTexte[1] = tabData2;
		tabTexte[2] = tabData3;
		tabTexte[3] = tabData4;
		tabTexte[4] = tabData5;
		int nombreElementTableau = tabTexte.length -1;
		
		boolean ok = false;
		while (!ok){
			int alea = (int) (Math.random()*nombreElementTableau);
			String question[] = tabTexte[alea];
			int idQuestion = Integer.valueOf(question[0]);
			int tailleListe = tabQuestionHistorique.size();
			if (!isDejaCetteQuestion(idQuestion)){
				//on a pas encore cette question dans la liste
				//donc on l'ajoute
				ok=true;//on sort de la boucle
				tabQuestionHistorique.add(idQuestion);
				return question;
			}
			
		}
		
		return null;
	}
	 */
	
	
	private boolean isDejaCetteQuestion(String id){
		
		for (String idDejaFait : SuperChoix.tabQuestionHistorique){
			
			if (id.equals(idDejaFait) ){
				//on a déjà cette question dans le quizz !
				Log.w("","on a déjà cette question !!!");
				return true;
			}
		}
		return false;
	}
	
	public String[] getLesElements() {
		return lesElements;
	}

	public String getImage() {
		return image;
	}

	

}
