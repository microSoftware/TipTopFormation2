package com.example.tiptopformation2;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
import Core.*;

public class Quizz extends Activity implements Intents {

	
		//variables de la classe
		private static final int nbQuestionParQuizz = 10;
		private String theme;
		private int levelUserTheme;
		private ArrayList<QuestionReponse> tableauDeToutesLesQuestions;
		private int numeroQuestionCourante;
		private int nbPointGagne;
		
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizz);
		
		tableauDeToutesLesQuestions = new ArrayList<QuestionReponse>();
		 Intent intent = getIntent();
		 if (intent != null) {
            theme =intent.getStringExtra(INTENT_THEME);
            Toast.makeText(this, "DANS QUIZZ"+ theme, Toast.LENGTH_LONG).show();
            levelUserTheme = intent.getIntExtra(INTENT_LEVEL_CHOISI, levelUserTheme);
          
         }
		
		
		if (theme == THEMES.MENAGE.toString()){
			//on peut ajouter les exos qu'on veut pour le thème : Ménage
			int nombreExercicePourCeTheme = 3; 
			int nombreExoDuMemeType = nbQuestionParQuizz / nombreExercicePourCeTheme;
			ajouterQuestionDansQuizz(EXERCICES.SYNONYME, nombreExoDuMemeType);
			ajouterQuestionDansQuizz(EXERCICES.DANGEROUS, nombreExoDuMemeType);
			
			//si ce n'est pas un multiple de 3 : on doit ajouter une question de plus à un thème
			
			if ((nbQuestionParQuizz % nombreExercicePourCeTheme) == 1)
				ajouterQuestionDansQuizz(EXERCICES.TRICHOIX, nombreExoDuMemeType+1);
			else if ((nbQuestionParQuizz % nombreExercicePourCeTheme) == 2)
				ajouterQuestionDansQuizz(EXERCICES.TRICHOIX, nombreExoDuMemeType+2);//on ajoute 2 questions de plus pour un exo
		}
		//A Rajouter ici pour l'ajout d'un thème ...
		
		
		retourHome();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quizz, menu);
		return true;
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
	
	private void creerLeQuizz (){
		
	}
	
	
	private void retourHome (){
		//Dès que le quizz est fini, on retourne au choix des thèmes => HOME
		Intent intent = new Intent(Quizz.this, Home.class);
		intent.putExtra(INTENT_POINTS_GAGNER, 8);
		intent.putExtra(INTENT_THEME, theme);
		intent.putExtra(INTENT_ORIGINE_QUIZZ_CLASS, true);
		startActivity(intent);
	}

}
