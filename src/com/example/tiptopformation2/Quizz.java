package com.example.tiptopformation2;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import Core.*;
import Exercices.*;

public class Quizz extends Activity implements OnClickListener {

	private QuizzModel quizz;
	private Jeu jeu;
	
	//variables de vue
	Button recommencer;
	Button home;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizz);
		
		jeu = Jeu.getInstance();
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
		Log.w("Quizz", "lancerLeQuizz() ; numeroDeLaQuestionCourante = "+numeroDeLaQuestionCourante);
		Log.w("Quizz", "lancerLeQuizz() ; quizz.getNbquestionparquizz() = "+ quizz.getNbquestionparquizz() );
		
		/*
		 * Le problème c'est que le numéro de la question est incrémenté avant le if
		 * Sauf que si on le met après il y a une erreur. Trouve l'origine de ce erreur
		 * et le monde s'ouvrira à toi ^^
		 */
		
		if ( numeroDeLaQuestionCourante == quizz.getNbquestionparquizz()  ){
			setContentView(R.layout.quizz_resultat);
			recommencer = (Button) findViewById(R.id.recommencer);
			recommencer.setOnClickListener(this);
			home = (Button) findViewById(R.id.revenirHome);
			home.setOnClickListener(this);
		}

		else {
			for (QuestionReponse question : quizz.getTableauDeToutesLesQuestions() ){
				if ( (question.getNumeroDeLaQuestion() == numeroDeLaQuestionCourante )  && (question.getTypeExo() == EXERCICES.TEST) ){
					Intent intent = new Intent(Quizz.this, CultureGeneraleJeu.class);
					startActivity(intent);
				}
				else if ( (question.getNumeroDeLaQuestion() == numeroDeLaQuestionCourante )  && (question.getTypeExo() == EXERCICES.MULTICHOIX) ){
					Intent intent = new Intent(Quizz.this, MultiChoixJeu.class);
					startActivity(intent);
				}
				else if ( (question.getNumeroDeLaQuestion() == numeroDeLaQuestionCourante )  && (question.getTypeExo() == EXERCICES.SUPERCHOIX) ){
					Intent intent = new Intent(Quizz.this, SuperChoixJeu.class);
					startActivity(intent);
				}
				
			}
		}
		
		
	}





	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == recommencer) {
			jeu.recommencer();
			//et c'est partie !!!
			Intent intent = new Intent(Quizz.this, Quizz.class);
			startActivity(intent);
		}
		else if(v == home) {
			Intent intent = new Intent(Quizz.this, Home.class);
			startActivity(intent);
		}
		
		
	}

	

}
