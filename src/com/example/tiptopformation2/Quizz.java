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

public class Quizz extends Activity {

	private Jeu jeu;
	private QuizzModel quizz;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizz);

		jeu = Jeu.getInstance();
		quizz = jeu.getQuizz();
		quizz.setTheme(jeu.getThemeChoisi());
		quizz.setLevelUserTheme(jeu.getLevelChoisi());
		
		quizz.creerLeQuizz();
		lancerLeQuizz();
	}

	

	

	private void retourHome() {
		// Dès que le quizz est fini, on retourne au choix des thèmes => HOME
		Intent intent = new Intent(Quizz.this, Home.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quizz, menu);
		return true;
	}

	private void lancerLeQuizz() {
		/*
		 * une boucle for qui parcourt la liste de question pour chaque
		 * itération faire un if (typeExercice = "Synonyme") lancer activité
		 * Synonyme et modifier la méthode getQuestionInstance
		 */
		
		for (QuestionReponse question : quizz.getTableauDeToutesLesQuestions() ){
			if ( (question.getNumeroDeLaQuestion() == quizz.getNumeroQuestionCourante() )  && (question.getTypeExo() == EXERCICES.TEST) ){
				Intent intent = new Intent(Quizz.this, CultureGeneraleJeu.class);
				startActivity(intent);
			}
		}
	}

	

}
