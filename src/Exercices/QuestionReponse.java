package Exercices;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.tiptopformation2.R;

import Core.EXERCICES;
import Core.Jeu;
import Core.QuizzModel;
import Core.THEMES;

public abstract class QuestionReponse {
	
	/*
	 * Tous ce qui est commun à tous les exos :
	 * - le thème du quizz, le type de l'exo, le level
	 * - numéro de la question, l'id de la question
	 */
	protected Jeu jeu;
	protected QuizzModel quizz;
	protected THEMES themeDuQuizz;
	protected EXERCICES typeExo;
	protected int level;
	
	protected String id;
	protected int numeroDeLaQuestion;
	protected String question;
	protected String phraseCorrection;
	
	
	
	

	

	public QuizzModel getQuizz() {
		return quizz;
	}

	public QuestionReponse() {
		super();
		jeu = Jeu.getInstance();
		quizz = jeu.getQuizz();
		level = quizz.getLevelChoisi();
		themeDuQuizz = quizz.getTheme();
	}
	
	public void setNumeroDeLaQuestion(int numeroDeLaQuestion) {
		this.numeroDeLaQuestion = numeroDeLaQuestion;
	}

	public String getQuestion() {
		return question;
	}

	public String getPhraseCorrection() {
		return phraseCorrection;
	}

	public int getLevel() {
		return level;
	}

	public int getNumeroDeLaQuestion() {
		return numeroDeLaQuestion;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public String getId() {
		return id;
	}

	public QuestionReponse getInstanceDeLaQuestion() {
		return this;
	}

	public THEMES getThemeDuQuizz() {
		return themeDuQuizz;
	}

	public EXERCICES getTypeExo() {
		return typeExo;
	}
	
	public String  lireFichierCSV (){
		Log.w("QuestionReponse", "lireFichierCSV 1");
		
		try {
			Context con = jeu.getContexte();
			Resources res = con.getResources();
	        InputStream in_s = null;
	        Log.w("QuestionReponse", "lireFichierCSV 2");
	        if (themeDuQuizz == THEMES.MENAGE)
	        {
	        	if (typeExo == EXERCICES.MULTICHOIX){
		        	if (level == 1)
		        		in_s = res.openRawResource(R.raw.multichoix_menage_lv1);
		        	if (level == 2)
		        		in_s = res.openRawResource(R.raw.multichoix_menage_lv2);
		        	if (level == 3)
		        		in_s = res.openRawResource(R.raw.multichoix_menage_lv3);
		        	
	        	}
	        	
	        	if (typeExo == EXERCICES.SUPERCHOIX){
		        	if (level == 1)
		        		in_s = res.openRawResource(R.raw.superchoix_menage_lv1);
		        	if (level == 2)
		        		in_s = res.openRawResource(R.raw.superchoix_menage_lv2);
		        	if (level == 3)
		        		in_s = res.openRawResource(R.raw.superchoix_menage_lv3);
		        	Log.w("QuestionReponse", "lireFichierCSV 3");
	        	}
	        	
	        	if (typeExo == EXERCICES.SYNONYME){
		        	if (level == 1)
		        		in_s = res.openRawResource(R.raw.synonyme_menage_lv1);
		        	if (level == 2)
		        		in_s = res.openRawResource(R.raw.synonyme_menage_lv2);
		        	if (level == 3)
		        		in_s = res.openRawResource(R.raw.synonyme_menage_lv3);
		        	
	        	}
	        	
	        }
	       
	        else if (themeDuQuizz == THEMES.FRANCAIS)
	        {
	        	
	        	if (typeExo == EXERCICES.MULTICHOIX){
		        	if (level == 1)
		        		in_s = res.openRawResource(R.raw.multichoix_francais_lv1);
		        	if (level == 2)
		        		in_s = res.openRawResource(R.raw.multichoix_francais_lv2);
		        	if (level == 3)
		        		in_s = res.openRawResource(R.raw.multichoix_francais_lv3);
		        	
	        	}
	        	
	        	if (typeExo == EXERCICES.SUPERCHOIX){
		        	if (level == 1)
		        		in_s = res.openRawResource(R.raw.superchoix_francais_lv1);
		        	if (level == 2)
		        		in_s = res.openRawResource(R.raw.superchoix_francais_lv2);
		        	if (level == 3)
		        		in_s = res.openRawResource(R.raw.superchoix_francais_lv3);
		        	Log.w("QuestionReponse", "lireFichierCSV 3");
	        	}
	        	
	        	if (typeExo == EXERCICES.SYNONYME){
		        	if (level == 1)
		        		in_s = res.openRawResource(R.raw.synonyme_francais_lv1);
		        	if (level == 2)
		        		in_s = res.openRawResource(R.raw.synonyme_francais_lv2);
		        	if (level == 3)
		        		in_s = res.openRawResource(R.raw.synonyme_francais_lv3);
		        	
	        	}
	        	
	        
	        	
	        }
	        
	        else if (themeDuQuizz == THEMES.MATHS)
	        {
	        	
	        	if (typeExo == EXERCICES.MULTICHOIX){
		        	if (level == 1)
		        		in_s = res.openRawResource(R.raw.multichoix_maths_lv1);
		        	if (level == 2)
		        		in_s = res.openRawResource(R.raw.multichoix_maths_lv2);
		        	if (level == 3)
		        		in_s = res.openRawResource(R.raw.multichoix_maths_lv3);
		        	
	        	}
	        	
	        	if (typeExo == EXERCICES.SUPERCHOIX){
		        	if (level == 1)
		        		in_s = res.openRawResource(R.raw.superchoix_maths_lv1);
		        	if (level == 2)
		        		in_s = res.openRawResource(R.raw.superchoix_maths_lv2);
		        	if (level == 3)
		        		in_s = res.openRawResource(R.raw.superchoix_maths_lv3);
		        	Log.w("QuestionReponse", "lireFichierCSV 3");
	        	}
	        	
	        	if (typeExo == EXERCICES.SYNONYME){
		        	if (level == 1)
		        		in_s = res.openRawResource(R.raw.synonyme_maths_lv1);
		        	if (level == 2)
		        		in_s = res.openRawResource(R.raw.synonyme_maths_lv2);
		        	if (level == 3)
		        		in_s = res.openRawResource(R.raw.synonyme_maths_lv3);
		        	
	        	}
	        	
	        
	        	
	        }
	       
	        byte[] b = new byte[in_s.available()];
	        
	        in_s.read(b);
	        String texte = new String(b);
	        Log.w("QuestionReponse", "le fichier lu : "+ texte);
	        return texte;
	        
	    } catch (Exception e) {
	    	 e.printStackTrace();
	    	 Log.w("test", "Erreur dans la lecture du fichier");
	    }
		
		return null;
		
		
	}
	
	public String[][] extraireTousElementsTableau(){
		String texte = lireFichierCSV ();
		Log.w("QuestionReponse", "extraireTousElementsTableau 1");
		List<String> listeLignes = new ArrayList<String>(); 
		//on sépare le fichier en ligne
		StringTokenizer lignes = new StringTokenizer(texte, "\n");
		
		while (lignes.hasMoreTokens()) {
			listeLignes.add(lignes.nextToken());
		}
		Log.w("QuestionReponse", "extraireTousElementsTableau 2");
		int nombreLignes = listeLignes.size();
		String [][] tableau = new String [nombreLignes][10];
		Log.w("QuestionReponse", "extraireTousElementsTableau 3");
		int numeroLigne = 0;
		for (String ligne : listeLignes) {
			StringTokenizer colonnes = new StringTokenizer(ligne, ";");
			//on parcourt les colonnes
			int i = 0;
			while (colonnes.hasMoreTokens()) {
				String terme = colonnes.nextToken();
				tableau[numeroLigne][i] = terme;
				i++;
			}
			numeroLigne++;
		}
		Log.w("QuestionReponse", "extraireTousElementsTableau 4");
		//retourne donc un tableau[ligne][colonnes] qui contient tout notre fichier
		return tableau;
	}
	
	
}
