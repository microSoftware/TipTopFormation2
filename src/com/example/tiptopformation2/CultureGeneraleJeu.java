package com.example.tiptopformation2;

import Core.Jeu;
import Exercices.CultureGenerale;
import Exercices.QuestionReponse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CultureGeneraleJeu extends Activity implements OnClickListener {

	private QuestionReponse  instanceDeLaQuestion;;
	private Button valider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_culture_generale_jeu);
		
		Jeu jeu = Jeu.getInstance();
		instanceDeLaQuestion = jeu.getQuizz().getQuestionInstance();
		Log.w("CCulture>GeneraleJeu", "instanceDeLaQuestion = "+instanceDeLaQuestion);
		int numeroDeLaQuestion = instanceDeLaQuestion.getNumeroDeLaQuestion();
		TextView num = (TextView) findViewById(R.id.textView3);
		String numeroDeLaQuestionString = String.valueOf(numeroDeLaQuestion);
		num.setText(numeroDeLaQuestionString);
		
		valider = (Button) findViewById(R.id.valider);
		valider.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.culture_generale_jeu, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if (v == valider){
			Intent intent = new Intent(CultureGeneraleJeu.this, Quizz.class);
			startActivity(intent);
		}
		
	}

}
