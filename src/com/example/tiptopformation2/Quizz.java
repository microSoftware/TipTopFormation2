package com.example.tiptopformation2;

import Core.EXERCICES;
import Core.Jeu;
import Core.QuizzModel;
import Core.THEMES;
import Exercices.QuestionReponse;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Quizz extends Activity implements OnClickListener {

	private QuizzModel quizz;
	private Jeu jeu;
	
	//variables de vue
	Button recommencer;
	Button home;
	Button menage;
	Button francais;
	Button maths;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizz); //pour le retour en arrière
		overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
		
		
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

	

	private void lancerLeQuizz() {
		Log.w("Quizz (activité)", "lancerLeQuizz() - Etape 0");
		quizz.incrementeNumeroQuestionCourante();
		int numeroDeLaQuestionCourante=quizz.getNumeroQuestionCourante();
		
		/*
		 * Quand le quizz est fini on affiche la page de résultat
		 */
		Log.w("Quizz (activité)", "lancerLeQuizz() ; numeroDeLaQuestionCourante = "+numeroDeLaQuestionCourante);
		Log.w("Quizz (activité)", "lancerLeQuizz() ; quizz.getNbquestionparquizz() = "+ quizz.getNbquestionparquizz() );
		
		
		/*
		 * On a fini le quizz => on affiche la page de résultat
		 */
		if ( numeroDeLaQuestionCourante == quizz.getNbquestionparquizz() ){
			setContentView(R.layout.quizz_resultat);
			recommencer = (Button) findViewById(R.id.recommencer);
			recommencer.setOnClickListener(this);
			home = (Button) findViewById(R.id.revenirHome);
			home.setOnClickListener(this);
			
			TextView bravo = (TextView) findViewById(R.id.bravo);
			TextView felicitation = (TextView) findViewById(R.id.felicitation);
			String texteBravo = "Bravo, vous avez gagné "+
					jeu.getUser().nbPointsGagnes(quizz.getTheme())
					+" points";
			
			
		
			//Premier démarrage
			if (jeu.getUser().isPremierDemarrage()){
				int point = jeu.getUser().pointTotalGagnee();
				if (point > (jeu.getQuizz().getNbquestionparquizz() / 2)){
					texteBravo = "\n\nBravo, vous avez gagné "+point+ " point(s)";;
					bravo.setText(texteBravo);
					felicitation.setText("Vous passez directement au niveau 2");
				}
				else {
					texteBravo = "\n\nBravo, vous avez gagné "+point+ " point(s)";;
					bravo.setText(texteBravo);
					felicitation.setText("Vous commencez au niveau 1");
				}
				jeu.getUser().setPremierDemarrage(false);
			}
			else {//Pas le premier démarrage
				
				if (quizz.getTheme() == THEMES.CULTURE_GENERALE){
					texteBravo = "\n\nBravo, vous avez gagné "+jeu.getUser().pointTotalGagnee()+ " point(s)";;
					bravo.setText(texteBravo);
					felicitation.setText("Votre niveau en "+jeu.getUser().themeMoinsGagnerPoints().toString()+" est faible.\nNous "
							+ "vous encourageons à vous entrainer sur ce thème");
				}
				else {
					//nombre de point manquant pour passer au level suppérieur
					int nbPointManquantPourPasserNiveauSup = jeu.getUser().differencePointLevelSuivant(quizz.getTheme());
					
					String texteFelicitation = "";
					if (nbPointManquantPourPasserNiveauSup <= 0){
						int nouveauNiveau = jeu.getUser().getLevelByTheme(quizz.getTheme())+1;
						//L'utilisateur passe au level suppérieur
						 texteFelicitation = "Félicitation, vous venez de passer au niveau suppérieur !\n"
						 		+ "Vous êtes maintenant niveau "+ nouveauNiveau;
					}
					else {
						int niveau = jeu.getUser().getLevelByTheme(quizz.getTheme());
						 texteFelicitation = "Vous êtes niveau "+niveau+"\nIl vous manque encore "+nbPointManquantPourPasserNiveauSup+
								" points pour passer au niveau suppérieur" ;
					}
					
					bravo.setText(texteBravo);
					felicitation.setText(texteFelicitation);
					}
					
				
					
				}
			
			
			/*
			 * Si le thème n'est pas Culture générale (= tous les 
			 * thèmes à la fois). Alors on n'a pas besoin d'afficher
			 * les détails des points
			 */
			if (quizz.getTheme() != THEMES.CULTURE_GENERALE){
				cacherDetailsPoint();
			}
			else {
				menage = (Button) findViewById(R.id.entrainer_menage);
				francais = (Button) findViewById(R.id.entrainer_francais);
				maths = (Button) findViewById(R.id.entrainer_maths);
				
				menage.setOnClickListener(this);
				francais.setOnClickListener(this);
				maths.setOnClickListener(this);
			}
			
			
			//On sauvegarde les points
			jeu.getUser().sauvegarderPointPartie();
		}

		
		//On a pas encore fini le quizz
		else {
			for (QuestionReponse question : quizz.getTableauDeToutesLesQuestions() ){
				
				if ( (question.getNumeroDeLaQuestion() == numeroDeLaQuestionCourante )   ){
					
					
					
					if ( question.getTypeExo() == EXERCICES.MULTICHOIX ){
						Intent intent = new Intent(Quizz.this, MultiChoixJeu.class);
						startActivity(intent);
					}
					
					else if ( question.getTypeExo() == EXERCICES.SUPERCHOIX ){
						Intent intent = new Intent(Quizz.this, SuperChoixJeu.class);
						startActivity(intent);
					}
					else if ( question.getTypeExo() == EXERCICES.SYNONYME ){
						Intent intent = new Intent(Quizz.this, SynonymeJeu.class);
						startActivity(intent);
					}
					
					
					
					
				}
				
				
			}
		}
		
		
	}





	private void cacherDetailsPoint() {
		// TODO Auto-generated method stub
		menage = (Button) findViewById(R.id.entrainer_menage);
		francais = (Button) findViewById(R.id.entrainer_francais);
		maths = (Button) findViewById(R.id.entrainer_maths);
		
		TextView menageText = (TextView) findViewById(R.id.TextMenage);
		TextView francaisText = (TextView) findViewById(R.id.TextFrancais);
		TextView mathsText = (TextView) findViewById(R.id.TextMaths);
		
		//puis on cache tous ça !
		menage.setVisibility(View.INVISIBLE);
		francais.setVisibility(View.INVISIBLE);
		maths.setVisibility(View.INVISIBLE);
		menageText.setVisibility(View.INVISIBLE);
		francaisText.setVisibility(View.INVISIBLE);
		mathsText.setVisibility(View.INVISIBLE);
		
	}





	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		quizz.viderIdHistorique();
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
		else if(v == menage) {
			jeu.sentrainer(THEMES.MENAGE);
			Intent intent = new Intent(Quizz.this, SelectionnerLevel.class);
			startActivity(intent);
		}
		else if(v == francais) {
			jeu.sentrainer(THEMES.FRANCAIS);
			Intent intent = new Intent(Quizz.this, SelectionnerLevel.class);
			startActivity(intent);
		}
		else if(v == maths) {
			jeu.sentrainer(THEMES.MATHS);
			Intent intent = new Intent(Quizz.this, SelectionnerLevel.class);
			startActivity(intent);
		}
		
		
	}

	public void onBackPressed(){
		confirmationAccueil();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.quizz, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(Quizz.this, Home.class);
		switch (item.getItemId()) {
			case R.id.accueil:
				confirmationAccueil();
				return true;
				
			case R.id.recommencer:
				confirmationRecommencer();
				return true;
				
			default:
				//return super.onOptionsItemSelected(item);
				return true;
		}
	}
	
	private void confirmationRecommencer(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Recommencer");
		builder.setMessage("Voulez vous arrêter votre partie");

		builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// Do nothing but close the dialog
				Jeu.getInstance().recommencer();
				Intent intent = new Intent(Quizz.this, Quizz.class);
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
	
	private void confirmationAccueil(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Retour à l'accueil");
		builder.setMessage("Voulez vous arrêter votre partie");

		builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// Do nothing but close the dialog
				QuizzModel quizz = Jeu.getInstance().getQuizz();
				quizz.viderIdHistorique();
				quizz = null;
				quizz = new QuizzModel();
				dialog.dismiss();
				Intent intent = new Intent(Quizz.this, Home.class);
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
