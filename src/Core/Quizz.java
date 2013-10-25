package Core;

import java.util.ArrayList;
import java.util.Collection;
import Core.Intents;
import android.content.Intent;
/*
 * Ce qu'il faut modifier quand on rajoute un exo, thème ...
 * 
 */
public class Quizz {
	
	//CONSTANTE
	private static final int nombreDeQuestionParQuizz = 10;
	
	//Les variables d'intent
	final int INTENT_NB_QUEST_PAR_QUIZZ = nombreDeQuestionParQuizz;
	
	
	//variables de la classe
	private THEMES theme;
	private int level;
	private ArrayList<QuestionReponse> tableauDeToutesLesQuestions;
	
	private int numeroQuestionCourante;
	
	public Quizz(THEMES theme, int level) {
		super();
		this.theme = theme;
		this.level = level;
		tableauDeToutesLesQuestions = new ArrayList<QuestionReponse>();
		//Intent intent = getIntent();
		//Log.w("[Quizz]", "Dans la classe !!! et le level choisi est : "+intent.getIntExtra(INTENT_LEVE, levelUserTheme));
		
		if (theme == THEMES.MENAGE){
			//on peut ajouter les exos qu'on veut pour le thème : Ménage
			int nombreExercicePourCeTheme = 3; 
			int nombreExoDuMemeType = nombreDeQuestionParQuizz / nombreExercicePourCeTheme;
			ajouterQuestionDansQuizz(EXERCICES.SYNONYME, nombreExoDuMemeType);
			ajouterQuestionDansQuizz(EXERCICES.DANGEROUS, nombreExoDuMemeType);
			
			//si ce n'est pas un multiple de 3 : on doit ajouter une question de plus à un thème
			
			if ((nombreDeQuestionParQuizz % nombreExercicePourCeTheme) == 1)
				ajouterQuestionDansQuizz(EXERCICES.TRICHOIX, nombreExoDuMemeType+1);
			else if ((nombreDeQuestionParQuizz % nombreExercicePourCeTheme) == 2)
				ajouterQuestionDansQuizz(EXERCICES.TRICHOIX, nombreExoDuMemeType+2);//on ajoute 2 questions de plus pour un exo
		}
		/*
		 * A Rajouter pour l'ajout d'un thème
		 */
		
		
	}
	
	private void ajouterQuestionDansQuizz (EXERCICES typeExo, int nombreDeQuestionAAjouter){
		if (typeExo == EXERCICES.SYNONYME){
			//on créer n instance de la classe Synonyme
			for (int i=0;i<nombreDeQuestionAAjouter;i++){
				tableauDeToutesLesQuestions.add(new SynonymeJeu());
			}
		}
		else if (typeExo == EXERCICES.DANGEROUS){
			//on créer n instance de la classe Dangerous
			for (int i=0;i<nombreDeQuestionAAjouter;i++){
				tableauDeToutesLesQuestions.add(new DangerousJeu());
			}
		}
		else if (typeExo == EXERCICES.TRICHOIX){
			//on créer n instance de la classe Trichoix
			for (int i=0;i<nombreDeQuestionAAjouter;i++){
				tableauDeToutesLesQuestions.add(new TriChoixJeu());
			}
		}
	}
	
	private void melangerQuestionsDuQuizz(){
		
		
	}
	
	
	
}
