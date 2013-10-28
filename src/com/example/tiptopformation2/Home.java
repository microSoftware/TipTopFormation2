package com.example.tiptopformation2;

import Core.Jeu;
import Core.THEMES;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Home extends Activity implements OnClickListener{

	//les boutons
	private Button B_menage;
	private Button B_maths;
	private Button B_francais;
	private Button B_cultureGenerale;
	
	//Variable Core
	private Jeu jeu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		jeu = Jeu.getInstance();
		/*On ne peux pas initialiser Quizz dans la classe Jeu 
		 * Je ne sais pas pourquoi, donc si qqu pouvait analyser
		 * ça...
		 */
		jeu.initialiserLeQuizz();
		
		//
		//Listener sur les boutons 
		//
		B_menage = (Button) findViewById(R.id.button1);
		B_menage.setOnClickListener(this);
		
		B_maths = (Button) findViewById(R.id.button2);
		B_maths.setOnClickListener(this);
		
		B_francais = (Button) findViewById(R.id.button3);
		B_francais.setOnClickListener(this);
		
		B_cultureGenerale = (Button) findViewById(R.id.button4);
		B_cultureGenerale.setOnClickListener(this);
		
		
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == B_menage) 
			jeu.getQuizz().setTheme(THEMES.MENAGE);
		
		else if(v == B_maths) 
			jeu.getQuizz().setTheme(THEMES.MATHS);
		
		else if(v == B_francais) 
			jeu.getQuizz().setTheme(THEMES.FRANCAIS);
		
		else if(v == B_cultureGenerale) 
			jeu.getQuizz().setTheme(THEMES.CULTURE_GENERALE);
		
		choisirNiveau ();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	

	/*
	 * Lance l'écran ou l'on choisi les niveaux 
	 * Paramètre : levelUserTheme qui est le niveaux du joueur 
	 * sur ce thème là (1,2 ou 3). Cela permet de griser les cases
	 * qu'elle n'est pas autorisé à cliquer
	 * 
	 * et le theme qui servira pour le quizz 
	 */
	public void choisirNiveau (){
		
			Intent intent = new Intent(Home.this, SelectionnerLevel.class);
			startActivity(intent);
	}

	
	

}
