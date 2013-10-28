package com.example.tiptopformation2;

import java.util.HashMap;

import Core.Jeu;
import Core.QuizzModel;
import Core.THEMES;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SelectionnerLevel extends Activity implements OnClickListener {

	//Couleur de grisement
	private static final String colorGriserBouton="#FF7777";
	private static final String colorAfficherBouton="#83B913";
	
	private THEMES theme;//thème choisi
	private int levelUserTheme;//level du joueur sur ce thème
	private int levelChoisiParUtilisateur=0;
	private QuizzModel quizz;
	
	//Les variables de vues
	private Button facile;
	private Button moyen;
	private Button difficile;
	private Button jouer;
	private static final String phrase ="Sélectionner le niveau pour le thème ";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectionner_level);

		
		quizz = Jeu.getInstance().getQuizz();
		theme = quizz.getTheme();
		levelUserTheme = Jeu.getInstance().getUser().getLevelByTheme(theme);
		
		String themeTitre = theme.toString();
		TextView titre = (TextView) findViewById(R.id.textView1);
		titre.setText(phrase+themeTitre);
		
		
		/*
		 * On initialise nos boutons de vue et on grise les 
		 * boutons qui sont interdits grâce à la fonction
		 *  ::griserLevelsInterdit(levelUser)
		 */
		
		facile = (Button) findViewById(R.id.facile);
		moyen = (Button) findViewById(R.id.moyen);
		difficile = (Button) findViewById(R.id.difficile);
		jouer = (Button) findViewById(R.id.jouer);
		griserLevelsInterdit();
		
		//Les listeners
		facile.setOnClickListener(this);
		moyen.setOnClickListener(this);
		difficile.setOnClickListener(this);
		jouer.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		
		
		if(v == facile) {
			levelChoisiParUtilisateur=1;
			
		}
		if(v == moyen) {
			if (levelUserTheme>=2)//on grise le bouton moyen
				levelChoisiParUtilisateur=2;
			else 
				levelChoisiParUtilisateur=-1;
			
		}
		if(v == difficile) {
			if (levelUserTheme>=3)//on grise le bouton difficile
				levelChoisiParUtilisateur=3;
			else 
				levelChoisiParUtilisateur=-1;
		}
		
		
		/*
		 * Si la personne n'a pas le niveau neccessaire on affiche un petit message
		 * Si le personne a le niveau requis on fait apparaitre un bouton "JOUER"
		 */
		if (levelChoisiParUtilisateur == -1){
			Toast.makeText(this, "Vous n'avez pas encore le niveau neccessaire" , Toast.LENGTH_LONG).show();
			jouer.setVisibility(View.GONE);
		}
		
		if (levelChoisiParUtilisateur != -1 && levelChoisiParUtilisateur != 0)
			jouer.setVisibility(View.VISIBLE);
		
		
		
		//Dès qu'on clique sur "Jouer"
		if (v == jouer){
			quizz.setLevelChoisi(levelChoisiParUtilisateur);
			Log.w("SelectionnerLevel", "on créer le quizz !");
			quizz.creerLeQuizz();
			
			Intent intent = new Intent(SelectionnerLevel.this, Quizz.class);
			startActivity(intent);
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selectionner_level, menu);
		return true;
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

	

}
