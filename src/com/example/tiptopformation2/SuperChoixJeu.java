package com.example.tiptopformation2;

import com.example.tiptopformation2.R.id;

import Core.Jeu;
import Core.QuizzModel;
import Exercices.MultiChoix;
import Exercices.SuperChoix;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SuperChoixJeu extends Activity implements OnTouchListener {

	/*
	 * Level 1 : 2 éléments
	 * Level 2 et 3 : 3 éléments
	 */
	
	//Données du Modèle 
	private SuperChoix  instanceDeLaQuestion;
	private int niveau;
	private int NbBonnesReponses; 
	int numeroQuestion;
	int nombreDeQuestion;
	private String question;
	private String[] lesElements; 
	private String correction;
	
	//variables de vue
	Button depot;

	ClipData data;
	Button correct;
	Button incorrect;
	Button incorrect2;

	Button valider;

	TextView reponse, numeroQuestionVue;
	TextView explication;
	ImageView image;
	
	
	private boolean boutonSuivantAfficher=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_super_choix_jeu);
		
		initialiserModel();
		
		rangerAleatoire();
		
		depot = (Button) findViewById(R.id.zoneDepot);


		valider = (Button) findViewById(R.id.valider);

		correct.setOnTouchListener(this);
		incorrect.setOnTouchListener(this);


		depot.setOnDragListener(new OnDragListener() {

			public boolean onDrag(View v, DragEvent event) {

				if (event.getAction() == DragEvent.ACTION_DROP){

					// Bouton qui a subi le drag and drop
					Button drager = (Button) event.getLocalState();
					depot.setText(drager.getText()); //on change le texte de la zone dépot par le text du bouton droper
					depot.setBackgroundColor(Color.CYAN);
					drager.setVisibility(View.INVISIBLE); // on cache le bouton droper

					if (depot.getText().equals(correct.getText())){ // on a déposer le bouton correct
						incorrect.setVisibility(View.VISIBLE);
						if (niveau == 2)
							incorrect2.setVisibility(View.VISIBLE);
					}

					else if (depot.getText().equals(incorrect.getText())){ // on a deposer le bouton incorrect1
						correct.setVisibility(View.VISIBLE);
						if (niveau == 2)
							incorrect2.setVisibility(View.VISIBLE);
					}

					else { //on a déposé incorrect 2
						incorrect.setVisibility(View.VISIBLE);
						correct.setVisibility(View.VISIBLE);
					}
				}
				return true;
			}});


		explication = (TextView) findViewById(R.id.explication);
		//explication.setText(""); on mettra ici l'explication lu dans le csv
		
		valider.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				reponse = (TextView) findViewById(R.id.reponse);
				boolean isRight = true;
				
				if (depot.getText().equals(correct.getText()) == false){
					
					reponse.setText("Mauvaise réponse ! \n"+correction);
					reponse.setTextColor(Color.RED);
					isRight = false;
				}
				
				reponse.setVisibility(View.VISIBLE);
				explication.setVisibility(View.VISIBLE);
				
				if (!boutonSuivantAfficher){
					valider.setText("Suivant");
					boutonSuivantAfficher = true;
				}
				else {
					Intent intent = new Intent(SuperChoixJeu.this, Quizz.class);
					startActivity(intent);
				}
			}
		});

	}

	public void rangerAleatoire(){
		int alea;

		image = (ImageView) findViewById(R.id.image);
		image.setImageResource(R.drawable.toxique);
		numeroQuestionVue = (TextView) findViewById(id.numeroQuestion);
		numeroQuestionVue.setText(numeroQuestion+"/"+nombreDeQuestion);
		
		if (niveau == 1){ // Si le niveau est 1 => 2 réponse possibes
			alea = (int)  (Math.random()*2);
			if (alea == 0){
				correct = (Button) findViewById(R.id.element1);
				incorrect = (Button) findViewById(R.id.element2);
			}
			else {
				correct = (Button) findViewById(R.id.element2);
				incorrect = (Button) findViewById(R.id.element1);
			}
		}
		else { // Le niveau est 2 => 3 réponses possibles
			alea  = (int) (Math.random()* 3);
			if (alea == 0){
				correct = (Button) findViewById(R.id.element1);
				incorrect = (Button) findViewById(R.id.element2);
				incorrect2 = (Button) findViewById(R.id.element3);
				incorrect2.setVisibility(View.VISIBLE);
			}
			else if (alea == 1){
				correct = (Button) findViewById(R.id.element2);
				incorrect = (Button) findViewById(R.id.element3);
				incorrect2 = (Button) findViewById(R.id.element1);
				incorrect.setVisibility(View.VISIBLE);
			}
			else {
				correct = (Button) findViewById(R.id.element3);
				incorrect = (Button) findViewById(R.id.element1);
				incorrect2 = (Button) findViewById(R.id.element2);
				correct.setVisibility(View.VISIBLE);
			}

			/*
			 * On change les texte du boutons en fonctino de ce que l'on 
			 * a lu dans le CSV
			 */
			incorrect2.setText(lesElements[2]);

			incorrect2.setOnTouchListener(this);
		}
		/*
		 * On change les texte des boutons en fonctino de ce que l'on 
		 * a lu dans le CSV
		 */
		correct.setText(lesElements[0]);
		incorrect.setText(lesElements[1]);
		
		correct.setOnTouchListener(this);
		incorrect.setOnTouchListener(this);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onTouch(View v, MotionEvent event) {
		data = ClipData.newPlainText("", "");

		DragShadowBuilder sb = new View.DragShadowBuilder(v);

		v.startDrag(data, sb, v, 0); // on evoie le bouton v dans le drop

		return true;
	}
	
	private void initialiserModel() {
		// TODO Auto-generated method stub
		/*
		 * On récupère l'instance de notre question (le modèle de celle-ci)
		 * On récupère donc toutes les infos de la question :
		 * - la question
		 * - les éléments (bonnes réponses et mauvaises)
		 * - le numéro de la question
		 * - le nombre de question
		 * - la correction
		 * - l'image
		 */
		instanceDeLaQuestion =  (SuperChoix) Jeu.getInstance().getQuizz().getQuestionInstance();
		niveau = Jeu.getInstance().getQuizz().getLevelChoisi();
		numeroQuestion = instanceDeLaQuestion.getNumeroDeLaQuestion();
		nombreDeQuestion = QuizzModel.getNbquestionparquizz();
		question = instanceDeLaQuestion.getQuestion();
		//lesElements = new String[5];
		lesElements = instanceDeLaQuestion.getLesElements();
		correction = instanceDeLaQuestion.getPhraseCorrection();

	}
	
	

}
