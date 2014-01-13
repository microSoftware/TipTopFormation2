package com.example.tiptopformation2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.tiptopformation2.R.id;

import Core.Jeu;
import Core.QuizzModel;
import Exercices.MultiChoix;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MultiChoixJeu extends Activity  {

		/*
		 * Note sur les levels
		 * Level 1 : 2 �lements + on affiche le nombre de bonnes r�ponses
		 * Level 2 : 4 �l�ments + on affiche le nombre de bonnes r�ponses
		 * Level 3 : 4 �l�ments + on affiche PAS le nombre de bonnes r�ponses
		 */
		
		
		//Donn�es du Mod�le 
		private MultiChoix  instanceDeLaQuestion;
		private int niveau;
		private int NbBonnesReponses; 
		int numeroQuestion;
		int nombreDeQuestion;
		private String question;
		private String[][] lesElements; 
		
		
		// Proposition1 & 2 sont les deux bonnes r�ponses.
		// Dans le cas o� il n'y en a qu'une, seul proposition1 est une bonne r�ponse
		private String proposition1, proposition2, proposition3, proposition4;
		private String image1,image2,image3,image4;
		private String correction = "Il fallait s�lectionner la bonne r�ponse !";

		// Variables correspondant aux �l�ments de l'XML
		private TextView questionPosee, nombreBonneReponse, correctionEcrite, numeroQuestionVue;
		private CheckBox reponse1, reponse2, reponse3, reponse4;
		private ImageView img1, img2, img3, img4;
		private Button submit;		
		
		// Cette liste permettra de distribuer al�atoirement les variables.
		List<String[]> list = new ArrayList<String[]>();
		
		private boolean boutonSuivantAfficher=false;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_multi_choix_jeu);
			initialiserModel();
			initialiserVariables();
			gererComposants();
			melanger();
			
			
			submit.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					boolean isRight;

					// Une fois qu'il a cliqu� sur le bouton OK, on v�rifie s'il
					// n'a pas coch� une mauvaise r�ponse ou qu'il n'a pas oubli� de bonnes r�ponses
					isRight = verifierResultats();

					// En fonction de son r�sultat, on affiche une correction correspondante
					
					
						if (!boutonSuivantAfficher){
							if (!isRight) 
								Toast.makeText(MultiChoixJeu.this, "Mauvaise r�ponse !\n"+correction, Toast.LENGTH_SHORT).show();
							else {
								instanceDeLaQuestion.getQuizz().gagnerPoint();
								Toast.makeText(MultiChoixJeu.this, "Bonne r�ponse !\n"+correction, Toast.LENGTH_SHORT).show();
							}
							
							submit.setText("Suivant");
							boutonSuivantAfficher = true;
						}
						else {
							Intent intent = new Intent(MultiChoixJeu.this, Quizz.class);
							startActivity(intent);
							finish();
						}
						
						
					

					reponse1.setEnabled(false);
					reponse2.setEnabled(false);
					reponse3.setEnabled(false);
					reponse4.setEnabled(false);
				}
			});
			
		}

		private void initialiserModel() {
			// TODO Auto-generated method stub
			/*
			 * On r�cup�re l'instance de notre question (le mod�le de celle-ci)
			 * On r�cup�re donc toutes les infos de la question :
			 * - la question
			 * - les �l�ments (bonnes r�ponses et mauvaises)
			 * - le num�ro de la question
			 * - le nombre de question
			 * - la correction
			 */
			instanceDeLaQuestion = (MultiChoix) Jeu.getInstance().getQuizz().getQuestionInstance();
			niveau = Jeu.getInstance().getQuizz().getLevelChoisi();
			NbBonnesReponses = instanceDeLaQuestion.getNbBonnesReponses();
			numeroQuestion = instanceDeLaQuestion.getNumeroDeLaQuestion() + 1;
			nombreDeQuestion = QuizzModel.getNbquestionparquizz();
			question = instanceDeLaQuestion.getQuestion();
			correction = instanceDeLaQuestion.getPhraseCorrection();
			lesElements = new String[5][2];
			lesElements = instanceDeLaQuestion.getLesElements();
			
			
			
			
			//on remplace les �l�ments par les vrais du model
			
			
			 if (niveau == 1){
				proposition1 = lesElements[0][0];//la r�ponse texte
				image1 =  lesElements[0][1];//l'image de la r�ponse
				proposition2 = lesElements[1][0];
				image2 =  lesElements[1][1];
			 }
			 else if (niveau == 2){//3 r�ponses possible
				 proposition1 = lesElements[0][0];
				 proposition2 = lesElements[1][0];
				 proposition3 = lesElements[2][0];
			}
			else if (niveau == 3){//4 r�ponses possible
				 proposition1 = lesElements[0][0];
				 proposition2 = lesElements[1][0];
				 proposition3 = lesElements[2][0];
				 proposition4 = lesElements[3][0];
			}
			
			
			
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.home, menu);
			return true;
		}

		
		
		public void initialiserVariables(){
			questionPosee = (TextView) findViewById(id.Question);
			nombreBonneReponse = (TextView) findViewById(id.NombreBonneReponse);
			reponse1 = (CheckBox) findViewById(id.checkBox1);
			reponse2 = (CheckBox) findViewById(id.checkBox2);
			reponse3 = (CheckBox) findViewById(id.checkBox3);
			reponse4 = (CheckBox) findViewById(id.checkBox4);
			img1 = (ImageView) findViewById(id.imageView1);
			img2 = (ImageView) findViewById(id.ImageView01);
			img3 = (ImageView) findViewById(id.ImageView02);
			img4 = (ImageView) findViewById(id.ImageView03);
			correctionEcrite = (TextView) findViewById(id.correction);
			submit = (Button) findViewById(id.Valider);
			numeroQuestionVue = (TextView) findViewById(id.numeroQuestion);
		
			
		}

		public void gererComposants(){
			// Il ne faut pas afficher la correction
			correctionEcrite.setText("");

			// Ajout des propositions dans la liste, globales quel que soit le niveau
			list.add(lesElements[0]);
			list.add(lesElements[1]);
			nombreBonneReponse.setVisibility(-1);
			img4.setVisibility(-1);
			img3.setVisibility(-1);
			img2.setVisibility(-1);
			img1.setVisibility(-1);
			if (niveau == 1){
				// on 2 checkbox, 2 images
				reponse3.setVisibility(-1);
				reponse4.setVisibility(-1);
			}
			else if (niveau == 2){
				// on 3 checkbox, PAS D'IMAGE
				reponse4.setVisibility(-1);
				list.add(lesElements[2]);
			}
			else if  (niveau == 3) {//4 choix, PAS D'IMAGE
				list.add(lesElements[2]);
                list.add(lesElements[3]);
			}

			questionPosee.setText(question);
			numeroQuestionVue.setText(numeroQuestion+"/"+nombreDeQuestion);
		}

		public void melanger(){
			
			// M�lange via la m�thode shuffle
			Collections.shuffle(list);
			if (niveau == 1){
				// Attribution des valeurs
				reponse1.setText(list.get(0)[0]);
				reponse2.setText(list.get(1)[0]);
				//img1.setImageResource(getResources().getIdentifier(list.get(0)[1], "drawable", getPackageName()));
				//img2.setImageResource(getResources().getIdentifier(list.get(1)[1], "drawable", getPackageName()));
			}
			else if (niveau == 2){
				reponse1.setText(list.get(0)[0]);
				reponse2.setText(list.get(1)[0]);
				reponse3.setText(list.get(2)[0]);
			}
			else if (niveau == 3){
				reponse1.setText(list.get(0)[0]);
				reponse2.setText(list.get(1)[0]);
				reponse3.setText(list.get(2)[0]);
				reponse4.setText(list.get(3)[0]);
			}
		}

		public boolean verifierResultats(){
			if (NbBonnesReponses == 1){
				if(getNbChecked() != 1)
					return false;
			}
			
			else if (NbBonnesReponses == 2){
				if(getNbChecked() != 2)
					return false;
			}
			
			else if (NbBonnesReponses == 3){
				if(getNbChecked() != 3)
					return false;
			}
		
		
			
			if ( reponse1.isChecked() && !isBon(reponse1.getText().toString()) )
				return false;
			if ( reponse2.isChecked() && !isBon(reponse2.getText().toString()) )
				return false;
			
			if (niveau >= 2){
				if ( !(reponse3.isChecked() && isBon(reponse3.getText().toString())) )
					return false;
			}
			if (niveau == 3){
				if ( !(reponse4.isChecked() && isBon(reponse4.getText().toString())) )
					return false;
			}
				
			// Si aucune erreur n'est trouv�, on renvoit vrai.
			return true;
		}
		
		private boolean isBon (String proposition){
			for (int i=0;i<NbBonnesReponses;i++){
				if (proposition.equals(lesElements[i][0]))
					return true;
			}
			return false;
		}
		// fonction qui retourne le nombre de checkbox coch�
		public int getNbChecked(){
			int value = 0;
			
			if (reponse1.isChecked())
				value++;
			if (reponse2.isChecked())
				value++;
			if (reponse3.isChecked())
				value++;
			if (reponse4.isChecked())
				value++;
			return value;
		}
		
		public void onBackPressed(){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setTitle("Retour � l'accueil");
			builder.setMessage("Vous vous arr�ter votre partie");

			builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// Do nothing but close the dialog
					QuizzModel quizz = Jeu.getInstance().getQuizz();
					quizz.viderIdHistorique();
					quizz = null;
					quizz = new QuizzModel();
					dialog.dismiss();
					Intent intent = new Intent(MultiChoixJeu.this, Home.class);
					startActivity(intent);
				}

			});

			builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					//donc on cache la boite de dialogue
					dialog.dismiss();
				}
			});

			AlertDialog alert = builder.create();
			alert.show();
		}

}
