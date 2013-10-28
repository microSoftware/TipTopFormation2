package com.example.tiptopformation2;


import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SuperChoixJeuME1 extends Activity implements OnTouchListener{

	Button depot;

	ClipData data;
	Button correct;
	Button incorrect;

	Button valider;

	TextView reponse;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_super_choix_me1);

		depot = (Button) findViewById(R.id.zoneDepot);
		correct = (Button) findViewById(R.id.correct);
		incorrect = (Button) findViewById(R.id.incorrect);
		valider = (Button) findViewById(R.id.valider);

		correct.setOnTouchListener(this);
		incorrect.setOnTouchListener(this);

		//depot.setEnabled(false);

		depot.setOnDragListener(new OnDragListener() {

			public boolean onDrag(View v, DragEvent event) {

				if (event.getAction() == DragEvent.ACTION_DROP){

					// Bouton qui a subi le drag and drop
					Button drager = (Button) event.getLocalState();
					depot.setText(drager.getText());
					depot.setBackgroundColor(Color.GRAY);
					drager.setVisibility(-1);

					if (depot.getText().equals(correct.getText())){
						incorrect.setVisibility(1);
					}
					
					else { // on a déposé incorrect
						correct.setVisibility(1);
					}
				}
				return true;
			}});



		valider.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				setContentView(R.layout.resultat_super_choix_jeu);
				reponse = (TextView) findViewById(R.id.reponse);

				if (depot.getText().equals(incorrect.getText())){
					reponse.setText("Mauvaise réponse ! ");
					reponse.setTextColor(Color.RED);
				}
			}
		});

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		data = ClipData.newPlainText("", "");

		DragShadowBuilder sb = new View.DragShadowBuilder(v);

		v.startDrag(data, sb, v, 0);

		return true;
	}

}

