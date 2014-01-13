package com.example.tiptopformation2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Core.Jeu;
import Core.QuizzModel;
import Exercices.Synonyme;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SynonymeJeu extends Activity implements OnTouchListener{

	Button valider;
	TextView texte;
	TextView numeroQuestion;
	TextView cor1;
	TextView cor2;
	TextView cor3;
	TextView cor4;

	Button prop1;
	Button prop2;
	Button prop3;
	Button prop4;
	Button prop5;
	Button prop6;

	Button rep1;
	Button rep2;
	Button rep3;
	Button rep4;

	private boolean isRight = false;
	

	ClipData data;
	List<Button> listeDesPropositions=new ArrayList<Button>();
	Dialog box;
	
	int nbDeBoutonMis = 0;
	
	//Donnée du modèle
	private String monTexte;
	private String tabElements[];
	private int nombreBonneReponse;
	private String titreCorrection;
	private Synonyme  instanceDeLaQuestion;
	int numeroQuestion1;
	int nombreDeQuestion;
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_synonyme);
		
		
		initialiserModel();
		initialiserElements();
		modifierTexteElements();//edit les boutons
		


		valider.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (nbDeBoutonMis != nombreBonneReponse)
					isRight = false;
				
				afficherCorrection();
			}});
		
		
		  

		rep1.setOnDragListener(new OnDragListener() {

			@Override
			public boolean onDrag(View v, DragEvent event) {
				if(event.getAction() == DragEvent.ACTION_DROP){
					Button leBouton = (Button) event.getLocalState();
					rep1.setText(leBouton.getText());
					LinearLayout ll = (LinearLayout) leBouton.getParent();
					ll.removeView(leBouton);
					nbDeBoutonMis++;
					if (! (leBouton.getText().equals(tabElements[0])) )
						isRight = false;
					else 
						isRight = true;
					
					
				}
				return true;
			}
		});

		rep2.setOnDragListener(new OnDragListener() {

			@Override
			public boolean onDrag(View v, DragEvent event) {
				if(event.getAction() == DragEvent.ACTION_DROP){
					Button leBouton = (Button) event.getLocalState();
					rep2.setText(leBouton.getText());
					LinearLayout ll = (LinearLayout) leBouton.getParent();
					ll.removeView(leBouton);
					nbDeBoutonMis++;
					
					if (! (leBouton.getText().equals(tabElements[1])) )
						isRight = false;
					else 
						isRight = true;
				}
				return true;
			}
		});

		rep3.setOnDragListener(new OnDragListener() {

			@Override
			public boolean onDrag(View v, DragEvent event) {
				if(event.getAction() == DragEvent.ACTION_DROP){
					Button leBouton = (Button) event.getLocalState();
					rep3.setText(leBouton.getText());
					LinearLayout ll = (LinearLayout) leBouton.getParent();
					ll.removeView(leBouton);
					nbDeBoutonMis++;
					
					if (! (leBouton.getText().equals(tabElements[2])) )
						isRight = false;
					else 
						isRight = true;
				}
				return true;
			}
		});

		rep4.setOnDragListener(new OnDragListener() {

			@Override
			public boolean onDrag(View v, DragEvent event) {
				if(event.getAction() == DragEvent.ACTION_DROP){
					Button leBouton = (Button) event.getLocalState();
					rep4.setText(leBouton.getText());
					LinearLayout ll = (LinearLayout) leBouton.getParent();
					ll.removeView(leBouton);
					nbDeBoutonMis++;
					
					if (! (leBouton.getText().equals(tabElements[3])) )
						isRight = false;
					else 
						isRight = true;
				}
				return true;
			}
		});
	}

	
	
	private void afficherCorrection() {
		setContentView(R.layout.activity_synonyme_correction);
		
		TextView title = (TextView) findViewById(R.id.textViewTexte);
		title.setText(titreCorrection);
		TextView phraseCorrection = (TextView) findViewById(R.id.sdfdsf); 
		
		if (isRight){
			phraseCorrection.setText("Bravo, vous avez bon");
		}
		else
			phraseCorrection.setText("Faux, vous ferez mieux la prochaine fois");
		
		Button c1 = (Button) findViewById(R.id.buttonRep1a);
		c1.setText(tabElements[0]);
		
		Button c2 = (Button) findViewById(R.id.buttonRep2a);
		c2.setText(tabElements[1]);
		
		Button c3 = (Button) findViewById(R.id.buttonRep3a);
		c3.setText(tabElements[2]);
		
		TextView t4 = (TextView) findViewById(R.id.textView4);
		Button c4 = (Button) findViewById(R.id.buttonRep4a);
		
		//si il n'y a que 3 bonnes réponses
		if (nombreBonneReponse == 3){
			t4.setVisibility(View.INVISIBLE);
			c4.setVisibility(View.INVISIBLE);
		}
		else {
			c4.setText(tabElements[4]);
		}
		
		Button suivant = (Button) findViewById(R.id.buttonSuivant);
		
		if (isRight)
			instanceDeLaQuestion.getQuizz().gagnerPoint();
		
		suivant.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(SynonymeJeu.this, Quizz.class);
				startActivity(intent);
				finish();
			}});
		
	}




	public boolean onTouch(View v, MotionEvent event) {
		data = ClipData.newPlainText ( "" , "" );
		DragShadowBuilder shadowBuilder = new View.DragShadowBuilder (v);
		v.startDrag(data, shadowBuilder,v, 0);

		return true;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.synonyme, menu);
		return true;
	}
	
	private void initialiserElements(){
		
		numeroQuestion = (TextView) findViewById(R.id.numeroQuestion);
		
		texte = (TextView) findViewById(R.id.textViewTexte);
		valider = (Button) findViewById(R.id.buttonValider);
		prop1 = (Button) findViewById(R.id.buttonProp1);
		listeDesPropositions.add(prop1);
		prop2 = (Button) findViewById(R.id.buttonProp2);
		listeDesPropositions.add(prop2);
		prop3 = (Button) findViewById(R.id.buttonProp3);
		listeDesPropositions.add(prop3);
		prop4 = (Button) findViewById(R.id.buttonProp4);
		listeDesPropositions.add(prop4);
		prop5 = (Button) findViewById(R.id.buttonProp5);
		listeDesPropositions.add(prop5);
		prop6 = (Button) findViewById(R.id.buttonProp6);
		listeDesPropositions.add(prop6);
		
		
		rep1 = (Button) findViewById(R.id.buttonRep1);
		rep2 = (Button) findViewById(R.id.buttonRep2);
		rep3 = (Button) findViewById(R.id.buttonRep3);
		rep4 = (Button) findViewById(R.id.buttonRep4);
		

		prop1.setOnTouchListener(this);
		prop2.setOnTouchListener(this);
		prop3.setOnTouchListener(this);
		prop4.setOnTouchListener(this);
		prop5.setOnTouchListener(this);
		prop6.setOnTouchListener(this);
		
	}

	private void initialiserModel() {
		instanceDeLaQuestion = (Synonyme) Jeu.getInstance().getQuizz().getQuestionInstance();
		this.titreCorrection = instanceDeLaQuestion.getPhraseCorrection();
		this.nombreBonneReponse = instanceDeLaQuestion.getNbBonnesReponses();
		this.monTexte = instanceDeLaQuestion.getQuestion();
		
		
		numeroQuestion1 = instanceDeLaQuestion.getNumeroDeLaQuestion() + 1;
		nombreDeQuestion = QuizzModel.getNbquestionparquizz();
		
		tabElements = instanceDeLaQuestion.getLesElements();
	}
	
	
	private static List genererSerieNombreAleatoire(int nombreElementAGenerer) {
		List<Integer> serie = new ArrayList<Integer>();

		// une serie avec les nombres dans l'odre
		for (int i = 0; i < nombreElementAGenerer; i++) {
			serie.add(i);
		}

		// on mélange la liste
		Collections.shuffle(serie);

		return serie;

	}
	
	private void modifierTexteElements() {
		numeroQuestion.setText(numeroQuestion1+"/"+nombreDeQuestion);
		texte.setText(monTexte);
	
		/*
		 * Les prépositions
		 */
		List<Integer> serie = genererSerieNombreAleatoire(tabElements.length);
		
		//on cache tous les éléments de la liste
				
		for (int i=0;i<tabElements.length;i++){
			int num = serie.get(i);//un nombre aléatoire
			listeDesPropositions.get(i).setText(tabElements[num]);
			//listeDesPropositions.get(i).setVisibility(View.VISIBLE);
		}
		
		for (Button b : listeDesPropositions){
			Log.w("", "Button = '"+b.getText()+"'");
			if (b.getText().equals("#") || b.getText().equals("") )
				b.setVisibility(View.INVISIBLE);
			else 
				b.setVisibility(View.VISIBLE);
		}
		
		
		
		/*
		 * Les cases 
		 */
		if (nombreBonneReponse == 3){
			rep4.setVisibility(View.INVISIBLE);
			TextView quatriemeValeur = (TextView)  findViewById(R.id.textView4);
			quatriemeValeur.setVisibility(View.INVISIBLE);
		}
		
		
	}
	
	public void onBackPressed(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Retour à l'accueil");
		builder.setMessage("Vous vous arrêter votre partie");

		builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// Do nothing but close the dialog
				QuizzModel quizz = Jeu.getInstance().getQuizz();
				quizz.viderIdHistorique();
				quizz = null;
				quizz = new QuizzModel();
				dialog.dismiss();
				Intent intent = new Intent(SynonymeJeu.this, Home.class);
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
