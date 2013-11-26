package com.example.tiptopformation2;


import com.example.tiptopformation2.R.id;

import Core.Jeu;
import Core.QuizzModel;
import Exercices.QuestionReponse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MultiChoixJeu extends Activity implements OnClickListener {

		/*
		 * Note sur le nombre d'élements par level
		 * Level 1 : 2 élements + on affiche le nombre de bonnes réponses
		 * Level 2 : 4 éléments + on affiche le nombre de bonnes réponses
		 * Level 3 : 4 éléments + on affiche PAS le nombre de bonnes réponses
		 */
		private QuestionReponse  instanceDeLaQuestion;
		
		//Donnée du Modèle 
		private int niveau;
		private int NbBonnesReponses;
		int numeroQuestion;
		int nombreDeQuestion;
		private String question;
		private String[][] lesElements = new String[4][2]; 
		
		// Variables de vue
		private TextView questionPosee, nombreBonneReponse, numeroQuestionVue;
		private CheckBox bonneReponse1, bonneReponse2;
		private CheckBox mauvaiseReponse1, mauvaiseReponse2;
		private ImageView imgBonne1, imgBonne2;
		private ImageView imgFausse1, imgFausse2;
		private Button valider;
		
		private boolean boutonSuivantAfficher=false;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_multi_choix_jeu);
			
			
			
			
			//Récupération des éléments
			questionPosee = (TextView) findViewById(id.Question);
			nombreBonneReponse = (TextView) findViewById(id.NombreBonneReponse);
			bonneReponse1 = (CheckBox) findViewById(id.BonneReponse1);
			bonneReponse2 = (CheckBox) findViewById(id.BonneReponse2);
			mauvaiseReponse1 = (CheckBox) findViewById(id.MauvaiseReponse1);
			mauvaiseReponse2 = (CheckBox) findViewById(id.MauvaiseReponse2);
			imgBonne1 = (ImageView) findViewById(id.BonneImage1);
			imgBonne2 = (ImageView) findViewById(id.BonneImage2);
			imgFausse1 = (ImageView) findViewById(id.MauvaiseImage1);
			imgFausse2 = (ImageView) findViewById(id.MauvaiseImage2);
			valider = (Button) findViewById(id.Valider);
			valider.setOnClickListener(this);
			numeroQuestionVue = (TextView) findViewById(id.numeroQuestion);
			
			
			
			
			
			
			/*
			 * On récupère l'instance de notre question (le modèle de celle-ci)
			 * On récupère donc toutes les infos de la question :
			 * - la question
			 * - les éléments (bonnes réponses et mauvaises)
			 * - le numéro de la question
			 * - le nombre de question
			 * - la correction
			 */
			instanceDeLaQuestion = Jeu.getInstance().getQuizz().getQuestionInstance();
			niveau = Jeu.getInstance().getQuizz().getLevelChoisi();
			NbBonnesReponses = instanceDeLaQuestion.getNbBonnesReponses();
			numeroQuestion = instanceDeLaQuestion.getNumeroDeLaQuestion();
			nombreDeQuestion = QuizzModel.getNbquestionparquizz();
			question = instanceDeLaQuestion.getQuestion();
			lesElements = instanceDeLaQuestion.getLesElements();

			
			
			
			/*
			 * On édite la vue (textes, images...)
			 */
			bonneReponse1.setText(lesElements[0][0]);
			bonneReponse2.setText(lesElements[1][0]);
			mauvaiseReponse1.setText(lesElements[2][0]);
			mauvaiseReponse2.setText(lesElements[3][0]);
			
			questionPosee.setText(question);
			numeroQuestionVue.setText(numeroQuestion+"/"+nombreDeQuestion);
			
			
			
			
		
			
			
			
			
			
			if (niveau == 1){
				bonneReponse2.setVisibility(-1);
				mauvaiseReponse2.setVisibility(-1);
				imgBonne2.setVisibility(-1);
				imgFausse2.setVisibility(-1);
				nombreBonneReponse.setVisibility(-1);
			}
			else if (niveau == 2){
				if (NbBonnesReponses > 1)
					nombreBonneReponse.setText("Bonnes réponses : "+NbBonnesReponses);
				else
					nombreBonneReponse.setText("Bonne réponse : "+NbBonnesReponses);
			}
			else if (niveau == 3){
				// on désactive le texte "nombre de bonne réponse"
				nombreBonneReponse.setVisibility(-1);
			}
			else {
				Toast.makeText(MultiChoixJeu.this, "Le niveau n'est pas encore implémenter !", Toast.LENGTH_SHORT).show();
			}
			
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.home, menu);
			return true;
		}

		@Override
		public void onClick(View v) {
			
			if (v == valider){
				
				boolean isRight = false;
				
				if (niveau == 1){
					if((bonneReponse1.isChecked()) 
							&& (! mauvaiseReponse1.isChecked()))
							isRight = true;
				}
				else {
					if((bonneReponse1.isChecked() 
							&& bonneReponse2.isChecked()) 
							&& (! mauvaiseReponse1.isChecked()) 
							&& (! mauvaiseReponse2.isChecked()))
							isRight = true;
				}
				
				
				
				if (!isRight) {
					Toast.makeText(MultiChoixJeu.this, "Mauvaise réponse !", Toast.LENGTH_SHORT).show();
				}
				else {
					if (!boutonSuivantAfficher){
					Toast.makeText(MultiChoixJeu.this, "Bonne réponse !", Toast.LENGTH_SHORT).show();
					valider.setText("Suivant");
					boutonSuivantAfficher = true;
					}
					else {
						Intent intent = new Intent(MultiChoixJeu.this, Quizz.class);
						startActivity(intent);
					}
					
					
				}
				
			
			}
			
			
		}

}
