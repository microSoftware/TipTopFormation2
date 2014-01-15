package com.example.tiptopformation2;

import Core.Jeu;
import Core.THEMES;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

public class Home extends Activity implements OnClickListener{

	//les boutons
	private Button B_menage;
	private Button B_maths;
	private Button B_francais;
	private Button B_cultureGenerale;
	private Button boutonCommencer;
	private ImageView aPropos;
	private ImageView barreDeNiveau;
	
	
	
	//Variable Core
	private Jeu jeu;
	private static boolean splashScreenTerminee = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		
		
		if ( ! splashScreenTerminee)
			afficherSplashScreen();
		
		if (splashScreenTerminee){
			
			
			jeu = Jeu.getInstance();
			jeu.initialiserContexteAndroid(this.getApplicationContext());
			jeu.initialiserLeQuizz();
			
			if (jeu.getUser().isPremierDemarrage()){
				setContentView(R.layout.premier_demarrage);
				boutonCommencer = (Button) findViewById(R.id.bouton_commencer);
				boutonCommencer.setOnClickListener(this);
			}
			else {
				
			
			setContentView(R.layout.activity_home);
			Log.w("Home", "onCreate - avant initialiserQuizz()");
			
			
			
			
			Log.w("Home", "onCreate - après initialiserQuizz() REF TACHE= "+this.getTaskId()+" ET "+this.hashCode());
			//
			//Listener sur les boutons 
			//
			
			Log.w("d",getApplicationContext().toString());
			Log.w("d",getBaseContext().toString());
			
			aPropos = (ImageView) findViewById(R.id.imageView1);
			aPropos.setOnClickListener(this);
			
			
			
			barreDeNiveau = (ImageView) findViewById(R.id.imageView2);
			barreDeNiveau.setOnClickListener(this);
			
			B_menage = (Button) findViewById(R.id.button1);
			B_menage.setOnClickListener(this);
			
			B_maths = (Button) findViewById(R.id.button2);
			B_maths.setOnClickListener(this);
			
			B_francais = (Button) findViewById(R.id.button3);
			B_francais.setOnClickListener(this);
			
			B_cultureGenerale = (Button) findViewById(R.id.button4);
			B_cultureGenerale.setOnClickListener(this);
			
			
			ProgressBar progress=(ProgressBar) findViewById(R.id.progressbar);
			progress.setProgress(jeu.getUser().scoreToral());
			
			Log.w("","---------------------------------------------------");
			Log.w("","---------------------------------------------------");
			Log.w("","---------------    on CREATE()   ------------------");
			Log.w("","---------------   user = "+Integer.toString( jeu.getUser().scoreToral() ) +"   ------------------");
			Log.w("","---------------------------------------------------");
			Log.w("","---------------------------------------------------");
			
			Jeu.getInstance().getQuizz().viderIdHistorique();
			}
		}
		
	}

	private void afficherAPropos (){
		
		LayoutInflater factory = LayoutInflater.from(Home.this);

		View aProposView = factory.inflate(R.layout.a_propos_dialog, null);

		AlertDialog.Builder adb = new AlertDialog.Builder(this);

		
		
		adb.setView(aProposView);

		adb.setTitle("A propos...");

		adb.setPositiveButton("Retour", null);

		//adb.show();
		
		
		AlertDialog alert = adb.create();
		alert.show();
		
		//setContentView(R.layout.activity_home);
		onCreate(null);
	}
	
	
	private void afficherSplashScreen() {
		 final int _splashTime = 2000; 

		    Thread splashTread;
		    setContentView(R.layout.splash);

		     

		      /** Thread pour l'affichage du SplashScreen */
		      splashTread = new Thread() 
		      {
		         @Override
		         public void run() 
		         {
		            try 
		            {
		                 synchronized(this)
		                 {
		                    wait(_splashTime);
		                 }
		             } catch(InterruptedException e) {} 
		             finally 
		             {
		               splashScreenTerminee = true;
		               startActivity(new Intent(Home.this, Home.class));
		             }
		          }
		       };

		       
		       		splashTread.start();
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if (v==boutonCommencer){
			jeu.getQuizz().setTheme(THEMES.CULTURE_GENERALE);
			jeu.getQuizz().setLevelChoisi(1);
			jeu.getQuizz().creerLeQuizz();
			
			Intent intent = new Intent(Home.this, Quizz.class);
			startActivity(intent);
		}
		
		if (v == aPropos)
			afficherAPropos();
		
		if (v == barreDeNiveau)
			Toast.makeText(Home.this, "Il faut travailler dur pour que cette barre soit pleine", Toast.LENGTH_SHORT).show();
			
		if(v == B_menage) {
			jeu.getQuizz().setTheme(THEMES.MENAGE);
			choisirNiveau ();
		}
		
		else if(v == B_maths) {
			jeu.getQuizz().setTheme(THEMES.MATHS);
			choisirNiveau ();
		}
		
		else if(v == B_francais) {
			jeu.getQuizz().setTheme(THEMES.FRANCAIS);
			choisirNiveau ();
		}
		
		else if(v == B_cultureGenerale) {
			jeu.getQuizz().setTheme(THEMES.CULTURE_GENERALE);
			choisirNiveau ();
		}
		
		
	}
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_propos:
				afficherAPropos();
				return true;
			case R.id.menu_didacticiel:
				afficherDidacticiel();
				return true;
			default:
				//return super.onOptionsItemSelected(item);
				return true;
		}
	}

	private void afficherDidacticiel() {
		Toast.makeText(Home.this, "Pas encore disponible", Toast.LENGTH_LONG).show();
		LayoutInflater factory = LayoutInflater.from(Home.this);

		View aProposView = factory.inflate(R.layout.didacticiel, null);

		AlertDialog.Builder adb = new AlertDialog.Builder(this);

		adb.setView(aProposView);

		adb.setTitle("Didacticiel");

		adb.setPositiveButton("Retour", null);
		
		setContentView(R.layout.didacticiel);
		
		VideoView video_player_view = (VideoView) findViewById(R.id.videoView1);
		MediaController media_Controller = new MediaController(this);
		DisplayMetrics  dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        video_player_view.setMinimumWidth(width);
        video_player_view.setMinimumHeight(height);
        video_player_view.setMediaController(media_Controller);
        Uri chemin = Uri.parse("/sdcard/a.avi");
        video_player_view.setVideoURI(chemin);
        
        video_player_view.start();



	   

	
		
		
		AlertDialog alert = adb.create();
		alert.show();
		
		//setContentView(R.layout.activity_home);
		onCreate(null);
		
	}

	/*
	 * On choisi son niveau
	 * 
	 */
	public void choisirNiveau (){
			//on change d'activité => SelectionnerLevel
			Intent intent = new Intent(Home.this, SelectionnerLevel.class);
			startActivity(intent);
			overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
			finish();
	}

	
	

}
