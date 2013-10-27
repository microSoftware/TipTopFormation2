package Core;

import java.util.ArrayList;

import Exercices.CultureGenerale;
import Exercices.MultiChoix;
import Exercices.QuestionReponse;
import Exercices.SuperChoix;
import Exercices.Synonyme;
import Exercices.TriChoix;

public class QuizzModel {
	// variables de la classe
	private static final int nbQuestionParQuizz = 10;
	private THEMES theme;
	private ArrayList<QuestionReponse> tableauDeToutesLesQuestions = new ArrayList<QuestionReponse>();
	private int numeroQuestionCourante;
	private int nbPointGagne;
	private Jeu jeu;
	private int levelUserTheme;
	
	public QuizzModel() {
		super();
		
		jeu = Jeu.getInstance();
		
	}

	public void setTheme(THEMES theme) {
		this.theme = theme;
	}

	public void setLevelUserTheme(int levelUserTheme) {
		this.levelUserTheme = levelUserTheme;
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
		return nbPointGagne;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public int getLevelUserTheme() {
		return levelUserTheme;
	}
	
	private void melangerQuestionsDuQuizz() {

	}

	public void creerLeQuizz() {

		if (theme == THEMES.MENAGE)
			creerLeQuizz_MENAGE();

		else if (theme == THEMES.MATHS)
			creerLeQuizz_MATHS();

		else if (theme == THEMES.FRANCAIS)
			creerLeQuizz_FRANCAIS();

		else if (theme == THEMES.CULTURE_GENERALE)
			creerLeQuizz_CULTURE_GENERALE();

	}

	private void creerLeQuizz_MENAGE() {
		/*
		 * Pour le thème ménage, il y a les exos : - Synonyme - SuperChoix - MultiChoix
		 */
		int nombreExercicePourCeTheme = 3;
		int repetitionD1MemeExo = nbQuestionParQuizz
				/ nombreExercicePourCeTheme;
		ajouterQuestionDansQuizz(EXERCICES.SYNONYME, repetitionD1MemeExo);
		ajouterQuestionDansQuizz(EXERCICES.SUPERCHOIX, repetitionD1MemeExo);

		// si ce n'est pas un multiple de 3 : on doit ajouter une question de
		// plus à un exo
		if ((nbQuestionParQuizz % nombreExercicePourCeTheme) == 1)
			ajouterQuestionDansQuizz(EXERCICES.MULTICHOIX,
					repetitionD1MemeExo + 1);// on ajoute 1 question de plus
												// pour un exo
		else if ((nbQuestionParQuizz % nombreExercicePourCeTheme) == 2)
			ajouterQuestionDansQuizz(EXERCICES.MULTICHOIX,
					repetitionD1MemeExo + 2);// on ajoute 2 questions de plus
												// pour un exo
	}

	private void creerLeQuizz_MATHS() {
		/*
		 * Pour le thème Maths, il y a les exos : - TriChoix - SuperChoix - MultiChoix
		 */
		int nombreExercicePourCeTheme = 3;
		int repetitionD1MemeExo = nbQuestionParQuizz
				/ nombreExercicePourCeTheme;
		ajouterQuestionDansQuizz(EXERCICES.TRICHOIX, repetitionD1MemeExo);
		ajouterQuestionDansQuizz(EXERCICES.SUPERCHOIX, repetitionD1MemeExo);

		// si ce n'est pas un multiple de 3 : on doit ajouter une question de
		// plus à un exo
		if ((nbQuestionParQuizz % nombreExercicePourCeTheme) == 1)
			ajouterQuestionDansQuizz(EXERCICES.MULTICHOIX,
					repetitionD1MemeExo + 1);// on ajoute 1 question de plus
												// pour un exo
		else if ((nbQuestionParQuizz % nombreExercicePourCeTheme) == 2)
			ajouterQuestionDansQuizz(EXERCICES.MULTICHOIX,
					repetitionD1MemeExo + 2);// on ajoute 2 questions de plus
												// pour un exo
	}

	private void creerLeQuizz_FRANCAIS() {
		/*
		 * Pour le thème Français, il y a les exos : - Synonyme - SuperChoix - TriChoix
		 */
		int nombreExercicePourCeTheme = 3;
		int repetitionD1MemeExo = nbQuestionParQuizz
				/ nombreExercicePourCeTheme;
		ajouterQuestionDansQuizz(EXERCICES.SYNONYME, repetitionD1MemeExo);
		ajouterQuestionDansQuizz(EXERCICES.SUPERCHOIX, repetitionD1MemeExo);

		// si ce n'est pas un multiple de 3 : on doit ajouter une question de
		// plus à un exo
		if ((nbQuestionParQuizz % nombreExercicePourCeTheme) == 1)
			ajouterQuestionDansQuizz(EXERCICES.TRICHOIX,
					repetitionD1MemeExo + 1);// on ajoute 1 question de plus
												// pour un exo
		else if ((nbQuestionParQuizz % nombreExercicePourCeTheme) == 2)
			ajouterQuestionDansQuizz(EXERCICES.TRICHOIX,
					repetitionD1MemeExo + 2);
		// on ajoute 2 questions de plus
		// pour un exo
	}

	private void creerLeQuizz_CULTURE_GENERALE() {
		
		ajouterQuestionDansQuizz(EXERCICES.TEST, nbQuestionParQuizz);
		

	}

	private void ajouterQuestionDansQuizz(EXERCICES typeExo, int nombreDeQuestionAAjouter) {

		if (typeExo == EXERCICES.SYNONYME) {
			for (int i = 0; i < nombreDeQuestionAAjouter; i++)
				tableauDeToutesLesQuestions.add( new Synonyme() );

		}

		else if (typeExo == EXERCICES.SUPERCHOIX) {
			for (int i = 0; i < nombreDeQuestionAAjouter; i++)
				tableauDeToutesLesQuestions.add(new SuperChoix());
		}

		else if (typeExo == EXERCICES.TRICHOIX) {
			for (int i = 0; i < nombreDeQuestionAAjouter; i++)
				tableauDeToutesLesQuestions.add(new TriChoix());
		}

		else if (typeExo == EXERCICES.MULTICHOIX) {
			for (int i = 0; i < nombreDeQuestionAAjouter; i++)
				tableauDeToutesLesQuestions.add(new MultiChoix());
		}
		
		else if (typeExo == EXERCICES.TEST) {
			for (int i = 0; i < nombreDeQuestionAAjouter; i++)
				tableauDeToutesLesQuestions.add( new CultureGenerale() );
		}

	}

	public QuestionReponse getQuestionInstance() {
		
		for (QuestionReponse question : tableauDeToutesLesQuestions){
			if (question.getNumeroDeLaQuestion() == numeroQuestionCourante){
				question.setNumeroDeLaQuestion(numeroQuestionCourante);
				return question;
			}
		}
		return null;
	}
}
