package com.example.tiptopformation2;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import Core.*;
import Exercices.*;

public class Quizz extends Activity {

	private QuizzModel quizz;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizz);

		quizz = Jeu.getInstance().getQuizz();
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
		quizz.incrementeNumeroQuestionCourante();
		int numeroDeLaQuestionCourante=quizz.getNumeroQuestionCourante();
		/*
		 * Quand le quizz est fini on affiche la page de résultat
		 */
		if (numeroDeLaQuestionCourante == quizz.getNbquestionparquizz() ){
			setContentView(R.layout.quizz_resultat);
			Button recommencer = (Button) findViewById(R.id.button1);
			Button home = (Button) findViewById(R.id.button2);
		}
		else {
			for (QuestionReponse question : quizz.getTableauDeToutesLesQuestions() ){
				if ( (question.getNumeroDeLaQuestion() == numeroDeLaQuestionCourante )  && (question.getTypeExo() == EXERCICES.TEST) ){
					
					Log.w("tag", "----------------------------------------------");
					Log.w("tag", "Numéro de la question courante : "+numeroDeLaQuestionCourante);
					Log.w("tag", "Numéro de l'instance de la question "+question.getNumeroDeLaQuestion());
					Log.w("tag", "----------------------------------------------");
					
					Intent intent = new Intent(Quizz.this, CultureGeneraleJeu.class);
					startActivity(intent);
				}
			}
		}
		
	}

	

}
