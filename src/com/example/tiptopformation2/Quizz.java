package com.example.tiptopformation2;

import Core.EXERCICES;
import Core.Jeu;
import Core.QuizzModel;
import Exercices.QuestionReponse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Quizz extends Activity implements OnClickListener {

	private QuizzModel quizz;
	private Jeu jeu;
	
	//variables de vue
	Button recommencer;
	Button home;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizz); //pour le retour en arrière
		Log.w("Quizz (activité)", "onCreate - avant lancerLeQuizz()");
		jeu = Jeu.getInstance();
		quizz = Jeu.getInstance().getQuizz();
		
		lancerLeQuizz();
		Log.w("Quizz (activité)", "onCreate - après lancerLeQuizz()");
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
		Log.w("Quizz (activité)", "lancerLeQuizz() - Etape 0");
		quizz.incrementeNumeroQuestionCourante();
		int numeroDeLaQuestionCourante=quizz.getNumeroQuestionCourante();
		
		/*
		 * Quand le quizz est fini on affiche la page de résultat
		 */
		Log.w("Quizz (activité)", "lancerLeQuizz() ; numeroDeLaQuestionCourante = "+numeroDeLaQuestionCourante);
		Log.w("Quizz (activité)", "lancerLeQuizz() ; quizz.getNbquestionparquizz() = "+ quizz.getNbquestionparquizz() );
		
		
		
		if ( numeroDeLaQuestionCourante == quizz.getNbquestionparquizz() ){
			setContentView(R.layout.quizz_resultat);
			recommencer = (Button) findViewById(R.id.recommencer);
			recommencer.setOnClickListener(this);
			home = (Button) findViewById(R.id.revenirHome);
			home.setOnClickListener(this);
		}

		else {
			for (QuestionReponse question : quizz.getTableauDeToutesLesQuestions() ){
				
				if ( (question.getNumeroDeLaQuestion() == numeroDeLaQuestionCourante )   ){
					
					if ( question.getTypeExo() == EXERCICES.TEST){
						Intent intent = new Intent(Quizz.this, CultureGeneraleJeu.class);
						startActivity(intent);
					}
					
					else if ( question.getTypeExo() == EXERCICES.MULTICHOIX ){
						Intent intent = new Intent(Quizz.this, MultiChoixJeu.class);
						startActivity(intent);
					}
					
					else if ( question.getTypeExo() == EXERCICES.SUPERCHOIX ){
						Intent intent = new Intent(Quizz.this, SuperChoixJeu.class);
						startActivity(intent);
					}
					
					
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
