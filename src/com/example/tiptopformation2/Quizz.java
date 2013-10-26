package com.example.tiptopformation2;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
import Core.*;
import Exercices.*;

public class Quizz extends Activity implements Intents {

	
		//variables de la classe
		private static final int nbQuestionParQuizz = 10;
		private THEMES theme;
		private int levelUserTheme;
		private ArrayList<QuestionReponse> tableauDeToutesLesQuestions;
		private int numeroQuestionCourante;
		private int nbPointGagne;
		private Jeu jeu;
		
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizz);
		
		tableauDeToutesLesQuestions = new ArrayList<QuestionReponse>();
		
		jeu = Jeu.getInstance();
		theme = jeu.getThemeChoisi();
		levelUserTheme = jeu.getUser().getLevelByTheme(theme);
		
		//Intent intent = new Intent(Quizz.this, Testctivite.class);
		//startActivity(intent);
		
		if (theme == THEMES.MENAGE){
			/*
			 * Pour le thème ménage, il y a les exos :
			 * - Synonyme
			 * - SuperChoix
			 * - MultiChoix 
			 */
			int nombreExercicePourCeTheme = 3; 
			int repetitionD1MemeExo = nbQuestionParQuizz / nombreExercicePourCeTheme;
			ajouterQuestionDansQuizz(EXERCICES.SYNONYME, repetitionD1MemeExo);
			ajouterQuestionDansQuizz(EXERCICES.SUPERCHOIX, repetitionD1MemeExo);
			
			//si ce n'est pas un multiple de 3 : on doit ajouter une question de plus à un exo
			if ((nbQuestionParQuizz % nombreExercicePourCeTheme) == 1)
				ajouterQuestionDansQuizz(EXERCICES.MULTICHOIX, repetitionD1MemeExo+1);//on ajoute 1 question de plus pour un exo
			else if ((nbQuestionParQuizz % nombreExercicePourCeTheme) == 2)
				ajouterQuestionDansQuizz(EXERCICES.MULTICHOIX, repetitionD1MemeExo+2);//on ajoute 2 questions de plus pour un exo
		}
		//A Rajouter ici pour l'ajout d'un thème ...
		
		
		//retourHome();
	}

	
	
	private void ajouterQuestionDansQuizz (EXERCICES typeExo, int nombreDeQuestionAAjouter){
		
		if (typeExo == EXERCICES.SYNONYME){
			for (int i=0;i<nombreDeQuestionAAjouter;i++)
				tableauDeToutesLesQuestions.add( new Synonyme() );
		}
		
		else if (typeExo == EXERCICES.SUPERCHOIX){
			for (int i=0;i<nombreDeQuestionAAjouter;i++)
				tableauDeToutesLesQuestions.add( new SuperChoix() );
		}
		
		else if (typeExo == EXERCICES.TRICHOIX){
			for (int i=0;i<nombreDeQuestionAAjouter;i++)
				tableauDeToutesLesQuestions.add( new TriChoix() );
		}
		
		else if (typeExo == EXERCICES.MULTICHOIX){
			for (int i=0;i<nombreDeQuestionAAjouter;i++)
				tableauDeToutesLesQuestions.add( new MultiChoix() );
		}
		
	}
	
	private void melangerQuestionsDuQuizz(){
		
		
	}
	
	private void creerLeQuizz (){
		
	}
	
	
	private void retourHome (){
		//Dès que le quizz est fini, on retourne au choix des thèmes => HOME
		Intent intent = new Intent(Quizz.this, Home.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quizz, menu);
		return true;
	}



}
