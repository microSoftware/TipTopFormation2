package Exercices;

import android.content.res.Resources.Theme;

public abstract class QuestionReponse {
	
	private Theme themeDuQuizz;
	private int level;
	protected static int[] tabQuestionHistorique = new int[5];//tableau des id qui ont déjà été ajoutés
	
	protected static int nbQuestionDejaAjouter=0;
	
	protected static void setTabQuestionHistorique(int[] tabQuestionHistorique) {
		QuestionReponse.tabQuestionHistorique = tabQuestionHistorique;
	}

	protected static void setNbQuestionDejaAjouter(int nbQuestionDejaAjouter) {
		QuestionReponse.nbQuestionDejaAjouter = nbQuestionDejaAjouter;
	}

	protected Theme getThemeDuQuizz() {
		return themeDuQuizz;
	}

	protected static int[] getTabQuestionHistorique() {
		return tabQuestionHistorique;
	}

	protected static int getNbQuestionDejaAjouter() {
		return nbQuestionDejaAjouter;
	}

	protected static String[]  lireCSV (int[] idANePasPrendre){
		/*
		 int nbLigne = nbLigneFichier(urlFichier);
		String[][] monTableau = new String[nbLigne][nbColonneMax];
 
		try {
 
			FileInputStream fichier = new FileInputStream(urlFichier);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(
					fichier));
			LineNumberReader l = new LineNumberReader(buffer);
			String str;
			String ligne;
			while ((ligne = l.readLine()) != null) {
				// on parcourt toutes les lignes
 
				// ligne = notre ligne
 
				StringTokenizer colonnes = new StringTokenizer(ligne, ";");
				// nos colonnne
				int i = 0;
				while (colonnes.hasMoreTokens()) {
 
					monTableau[l.getLineNumber() - 1][i] = colonnes.nextToken();
					i++;
				}
 
			}
 
		} catch (IOException e) {
			System.out.println("Erreur dans la lecture du fichier");
			e.printStackTrace();
		}
		//retourne donc un tableau[ligne][colonne] qui contient tout notre fichier
		return monTableau;
		 */
		
		//NE PAS OUBLIER D'AJOUTER l'id DE LA QUESTION DANS "tabQuestionHistorique" et d'INCREMENTER "nbQuestionDejaAjouter"
		return null;
	}
	
	
}
