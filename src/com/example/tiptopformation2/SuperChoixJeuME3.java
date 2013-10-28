package com.example.tiptopformation2;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SuperChoixJeuME3 extends Activity {
	
	List <String>motsAcceptes = new ArrayList<String>();
	
	EditText zoneTexte = null;
	
	Button valider = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_super_choix_me3);
		
		motsAcceptes.add("INFLAMMABLE");
		motsAcceptes.add("INFLAMABLE");
		motsAcceptes.add("INFLLAMABLE");
		motsAcceptes.add("IMFLAMMABLE");
		
		zoneTexte = (EditText) findViewById(R.id.zoneText);
		valider = (Button) findViewById(R.id.valider);
		
		valider.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String reponse = zoneTexte.getText().toString().toUpperCase();
				
				if (reponse.equals("")){
					Toast.makeText(SuperChoixJeuME3.this, "Vous n'avez pas rentrez votre réponse", Toast.LENGTH_LONG).show();
				}
				
				else {
					if (compare(reponse) == true){
						//on affiche la bonne réponse
						Toast.makeText(SuperChoixJeuME3.this, "Gagné !", Toast.LENGTH_LONG).show();
					}
					else {
						// on affiche la bonne réoponse
						Toast.makeText(SuperChoixJeuME3.this, "Perdu ! ", Toast.LENGTH_LONG).show();
					}
				}
				
			}
		});
		
	}
	
	public boolean compare(String motCompare){
		for (String motAccepte : motsAcceptes){
			if (motAccepte.toUpperCase().equals(motCompare)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
