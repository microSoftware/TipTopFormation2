package Core;

import java.util.ArrayList;
import java.util.Collections;

import Exercices.MultiChoix;
import Exercices.QuestionReponse;
import Exercices.SuperChoix;
import Exercices.Synonyme;
import android.util.Log;

public class QuizzModel {
	// variables de la classe
	private static  int nbQuestionParQuizz = 8; 
	private Jeu jeu;
	private THEMES theme;
	private int levelChoisi=0;
	private ArrayList<QuestionReponse> tableauDeToutesLesQuestions;
	private int numeroQuestionCourante;
	

	
	
	public QuizzModel() {
		super();
		jeu = Jeu.getInstance();
		numeroQuestionCourante=-1;
		tableauDeToutesLesQuestions = new ArrayList<QuestionReponse>(nbQuestionParQuizz);
		jeu.getUser().initialiserPointPartie();
	}
	
	//quand on recommence une partie
	public void recommencer (THEMES theme, int level){
		this.theme = theme;
		this.levelChoisi = level;
		creerLeQuizz();
		
	}
	
	public void viderIdHistorique() {
		SuperChoix.viderTabQuesHistorique();
		MultiChoix.viderTabQuesHistorique();
		Synonyme.viderTabQuesHistorique();
	}

	public void setTheme(THEMES theme) {
		this.theme = theme;
	}



	public void incrementeNumeroQuestionCourante() {
		if (numeroQuestionCourante <= nbQuestionParQuizz)
			this.numeroQuestionCourante++;
		}

	public static int getNbquestionparquizz() {
		return nbQuestionParQuizz;
	}

	public THEMES getTheme() {
		return theme;
	}

	public ArrayList<QuestionReponse> getTableauDeToutesLesQuestions() {
		return tableauDeToutesLesQuestions;
	}
	
	public void addTableauDeToutesLesQuestions(QuestionReponse exo){
		tableauDeToutesLesQuestions.add(exo);
	}

	public int getNumeroQuestionCourante() {
		return numeroQuestionCourante;
	}

	public int getNbPointGagne() {
		int nbPointGagne = jeu.getUser().nbPointsGagnes(this.theme);
		return nbPointGagne;
	}

	public Jeu getJeu() {
		return jeu;
	}

	
	
	public int getLevelChoisi() {
		return levelChoisi;
	}

	public void setLevelChoisi(int levelChoisi) {
		this.levelChoisi = levelChoisi;
	}

	private void melangerQuestionsDuQuizz() {
			
		Log.w("QuizzModel", "melangerQuestionsDuQuizz()");
			
		Collections.shuffle(tableauDeToutesLesQuestions);
			/*
			 * Une fois qu'on a mélangé toutes les questions, on peut donner
			 * un numéro (1/10)
			 */
			int i=0;
			for (QuestionReponse question : tableauDeToutesLesQuestions){
				question.setNumeroDeLaQuestion(i);
				i++;
			}
			afficherTableauQuestion ();//pour le déboggage
	}

	public void creerLeQuizz() {
		Log.w("QuizzModel", "creerLeQuizz()");	
		if (levelChoisi != 0){
			
			if (theme == THEMES.MENAGE)
				creerLeQuizz_MENAGE();
	
			else if (theme == THEMES.MATHS)
				creerLeQuizz_MATHS();
	
			else if (theme == THEMES.FRANCAIS)
				creerLeQuizz_FRANCAIS();
	
			else if (theme == THEMES.CULTURE_GENERALE)
				creerLeQuizz_CULTURE_GENERALE();
			
			melangerQuestionsDuQuizz();
		}

			
	}
		
	
	/*
	 * on récupère le thème de la question
	 * et on l'ajoute dans pointPartieEnCours de la
	 * classe User
	 */
	public void gagnerPoint(){
		THEMES theme = getQuestionInstance().getThemeDuQuizz();
		jeu.getUser().ajouterPoint(theme);
	}

	private void creerLeQuizz_MENAGE() {
		/*
		 * Pour le thème ménage, il y a les exos : - Synonyme - SuperChoix - MultiChoix
		 */
		int nombreExercicePourCeTheme = 3;
		int repetitionD1MemeExo = nbQuestionParQuizz / nombreExercicePourCeTheme;
		int reste = nbQuestionParQuizz % nombreExercicePourCeTheme;
		ajouterQuestionDansQuizz(EXERCICES.SYNONYME, nbQuestionParQuizz);
		ajouterQuestionDansQuizz(EXERCICES.MULTICHOIX, nbQuestionParQuizz  + reste);
		ajouterQuestionDansQuizz(EXERCICES.SUPERCHOIX, nbQuestionParQuizz);
	}

	private void creerLeQuizz_MATHS() {
		/*
		 * Pour le thème Maths, il y a les exos : -SuperChoix -  Synonyme - MultiChoix
		 */
		
		int nombreExercicePourCeTheme = 3;
		int repetitionD1MemeExo = nbQuestionParQuizz / nombreExercicePourCeTheme;
		int reste = nbQuestionParQuizz % nombreExercicePourCeTheme;
		ajouterQuestionDansQuizz(EXERCICES.MULTICHOIX, repetitionD1MemeExo );
		ajouterQuestionDansQuizz(EXERCICES.SUPERCHOIX, repetitionD1MemeExo + reste );
		ajouterQuestionDansQuizz(EXERCICES.SYNONYME, repetitionD1MemeExo);
	
	}

	private void creerLeQuizz_FRANCAIS() {
		/*
		 * Pour le thème Français, il y a les exos : - Synonyme - SuperChoix - multichoix
		 */
		
		int nombreExercicePourCeTheme = 3;
		int repetitionD1MemeExo = nbQuestionParQuizz / nombreExercicePourCeTheme;
		int reste = nbQuestionParQuizz % nombreExercicePourCeTheme;
		ajouterQuestionDansQuizz(EXERCICES.SYNONYME, nbQuestionParQuizz);
		ajouterQuestionDansQuizz(EXERCICES.MULTICHOIX, nbQuestionParQuizz  + reste);
		ajouterQuestionDansQuizz(EXERCICES.SUPERCHOIX, nbQuestionParQuizz);
	}

	private void creerLeQuizz_CULTURE_GENERALE() {
		
		//On double le nombre de question car il y a quand même 3
		//thèmes
		int nombreDeTheme = 3;
		int nbQuestionParQuizzDouble = nbQuestionParQuizz * 2;
		int nbQuestionParTheme = nbQuestionParQuizzDouble / nombreDeTheme;
		int resteQuestionTheme = nbQuestionParQuizzDouble % nombreDeTheme;
		
		/*----------- Theme Ménage -----------*/
		theme = THEMES.MENAGE;
		int nombreExercicePourCeTheme = 3;
		int nbQuestionParQuizz1 = nbQuestionParTheme;
		int repetitionD1MemeExo = nbQuestionParQuizz1 / nombreExercicePourCeTheme;
		int reste = nbQuestionParQuizz1 % nombreExercicePourCeTheme;
		ajouterQuestionDansQuizz(EXERCICES.SYNONYME, repetitionD1MemeExo);
		ajouterQuestionDansQuizz(EXERCICES.MULTICHOIX, repetitionD1MemeExo  + reste);
		ajouterQuestionDansQuizz(EXERCICES.SUPERCHOIX, repetitionD1MemeExo);
		/*----------- Theme Français -----------*/
		theme = THEMES.FRANCAIS;
		ajouterQuestionDansQuizz(EXERCICES.SYNONYME, repetitionD1MemeExo);
		ajouterQuestionDansQuizz(EXERCICES.MULTICHOIX, repetitionD1MemeExo  + reste);
		ajouterQuestionDansQuizz(EXERCICES.SUPERCHOIX, repetitionD1MemeExo);
		/*----------- Theme Maths -----------*/
		theme = THEMES.MATHS;
		int nbQuestionParQuizz2 = nbQuestionParTheme + resteQuestionTheme;
		int repetitionD1MemeExo2 = nbQuestionParQuizz2 / nombreExercicePourCeTheme;
		int reste2 = nbQuestionParQuizz2 % nombreExercicePourCeTheme;
		ajouterQuestionDansQuizz(EXERCICES.SYNONYME, repetitionD1MemeExo2);
		ajouterQuestionDansQuizz(EXERCICES.MULTICHOIX, repetitionD1MemeExo2  + reste2);
		ajouterQuestionDansQuizz(EXERCICES.SUPERCHOIX, repetitionD1MemeExo2);
		
		
		
		
		//On remplace le nombre de question par quizz par la nouvelle
		//valeur (le double)
		nbQuestionParQuizz = nbQuestionParQuizzDouble;
		theme = THEMES.CULTURE_GENERALE;
	}

	private void ajouterQuestionDansQuizz(EXERCICES typeExo, int nombreDeQuestionAAjouter) {
		Log.w("QuizzModel", "ajouterQuestionDansQuizz() - étape 0");
		
		if (typeExo == EXERCICES.SYNONYME) {
			for (int i = 0; i < nombreDeQuestionAAjouter; i++)
				tableauDeToutesLesQuestions.add( new Synonyme() );

		}

		else if (typeExo == EXERCICES.SUPERCHOIX) {
			for (int i = 0; i < nombreDeQuestionAAjouter; i++)
				tableauDeToutesLesQuestions.add(new SuperChoix());
		}


		else if (typeExo == EXERCICES.MULTICHOIX) {
			for (int i = 0; i < nombreDeQuestionAAjouter; i++)
				tableauDeToutesLesQuestions.add(new MultiChoix());
		}
		
		

	}

	public QuestionReponse getQuestionInstance() {
		
		for (QuestionReponse question : tableauDeToutesLesQuestions){
			if (question.getNumeroDeLaQuestion() == numeroQuestionCourante )
				return question;
			
		}
		return null;
	}

	/*
	 * Pour le débuggage
	 */
	private void afficherTableauQuestion (){
		
		Log.w("DEBUGGAGE", "============================");
		Log.w("DEBUGGAGE", "============================");
		Log.w("DEBUGGAGE", "FIN DE CREATION DU QUIZZ");
		Log.w("DEBUGGAGE", "============================");
		Log.w("DEBUGGAGE", "============================");
		for (QuestionReponse question : tableauDeToutesLesQuestions){
			Log.w("DEBUGGAGE", "-------------------------------");
			Log.w("DEBUGGAGE", "id = "+question.getId());
			Log.w("DEBUGGAGE", "numéro de la question = "+question.getNumeroDeLaQuestion());
			Log.w("DEBUGGAGE", "question = "+question.getQuestion());
			Log.w("DEBUGGAGE", "-------------------------------");
		}
		
	}
}
