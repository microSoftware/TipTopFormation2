package com.example.tiptopformation2;

import Core.EXERCICES;
import Core.Jeu;
import Core.QuizzModel;
import Core.THEMES;
import Exercices.QuestionReponse;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
			
			
			//on sauvegarde le score du joueur en sérialisant la classe User
			jeu.getUser().sauvegarder();
			
			//nombre de point manquant pour passer au level suppérieur
			int nbPointManquantPourPasserNiveauSup = jeu.getUser().differencePointLevelSuivant(quizz.getTheme());
			String texteFelicitation = "";
			if (nbPointManquantPourPasserNiveauSup == 0){
				//L'utilisateur passe au level suppérieur
				 texteFelicitation = "Félicitation, vous venez de passer au supérieur suppérieur !";
			}
			else {
				 texteFelicitation = "Il vous manque encore "+nbPointManquantPourPasserNiveauSup+
						" points pour passer au niveau suppérieur";
			}
			
			bravo.setText(texteBravo);
			felicitation.setText(texteFelicitation);
			
			//On sauvegarde les points
			//jeu.getUser().sauvegarderPointPartie();
			
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

	

}
