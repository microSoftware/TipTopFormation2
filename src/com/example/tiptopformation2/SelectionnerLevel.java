package com.example.tiptopformation2;

import Core.Jeu;
import Core.QuizzModel;
import Core.THEMES;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SelectionnerLevel extends Activity implements OnClickListener {

	//Couleur de grisement
	private static final String colorGriserBouton="#FF5555";//rouge
	private static final String colorAfficherBouton="#F0F0F0";//blanc
	
	
	private int levelUserTheme;//level du joueur sur ce thème
	private int levelChoisiParUtilisateur=0;
	private QuizzModel quizz;
	
	//Les variables de vues
	private Button facile;
	private Button moyen;
	private Button difficile;
	private Button jouer;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectionner_level);
		overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
		
		quizz = Jeu.getInstance().getQuizz();
		
		THEMES theme = quizz.getTheme();
		
		levelUserTheme = Jeu.getInstance().getUser().getLevelByTheme(theme);
		
		
		
		/*
		 * On initialise nos boutons de vue et on grise les 
		 * boutons qui sont interdits grâce à la fonction
		 * griserLevelsInterdit(levelUser)
		 */
		
		facile = (Button) findViewById(R.id.facile);
		moyen = (Button) findViewById(R.id.moyen);
		difficile = (Button) findViewById(R.id.difficile);
		
		griserLevelsInterdit();
		
		//Les listeners
		facile.setOnClickListener(this);
		moyen.setOnClickListener(this);
		difficile.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		
		//On clique sur le bouton FACILE
		if(v == facile) {
			levelChoisiParUtilisateur=1;
			commencer();
		}
		
		//On clique sur le bouton MOYEN
		if(v == moyen) {
			if (levelUserTheme>=2){//on grise le bouton moyen
				levelChoisiParUtilisateur=2;
				commencer();
			}
			else 
				levelChoisiParUtilisateur=-1;
		}
		
		//On clique sur le bouton DIFFICILE
		if(v == difficile) {
			if (levelUserTheme>=3) {//on grise le bouton difficile
				levelChoisiParUtilisateur=3;
				commencer();
			}
			else 
				levelChoisiParUtilisateur=-1;
		}
		
		
		/*
		 * Si la personne n'a pas le niveau neccessaire on affiche un petit message
		 * Si le personne a le niveau requis => elle commence le quizz 
		 */
		if (levelChoisiParUtilisateur == -1){
			Toast.makeText(this, "Vous n'avez pas encore le niveau neccessaire" , Toast.LENGTH_LONG).show();
		}
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selectionner_level, menu);
		return true;
	}

	/*
	 * Si le level est correct on commence le quizz
	 */
	private void commencer(){
		quizz.setLevelChoisi(levelChoisiParUtilisateur);
		quizz.creerLeQuizz();
		
		Intent intent = new Intent(SelectionnerLevel.this, Quizz.class);
		startActivity(intent);
		finish();
	}
	
	private void griserLevelsInterdit (){
		
		facile.setBackgroundColor(Color.parseColor(colorAfficherBouton));
		moyen.setBackgroundColor(Color.parseColor(colorAfficherBouton));
		difficile.setBackgroundColor(Color.parseColor(colorAfficherBouton));
		if (levelUserTheme<3){//on grise le bouton difficile
			difficile.setBackgroundColor(Color.parseColor(colorGriserBouton));
		}
		if (levelUserTheme<2){//on grise le bouton moyen
			moyen.setBackgroundColor(Color.parseColor(colorGriserBouton));
		}
	}

	public void onBackPressed(){
		Intent intent = new Intent(SelectionnerLevel.this, Home.class);
		startActivity(intent);
		overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
	}

	

}
