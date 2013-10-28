package Exercices;

import Core.EXERCICES;
import Core.Jeu;
import Core.THEMES;
import android.content.res.Resources.Theme;

public abstract class QuestionReponse {
	
	

	protected Theme themeDuQuizz;//null
	protected EXERCICES typeExo;//test


	protected int level;//0
	protected int numeroDeLaQuestion;//0
	protected Jeu jeu;//reference
	protected int id;///1
	
	
	/*
	 * Pour savoir si un type d'exo a des doublons
	 */
	protected static int nbQuestionDejaAjouter=0;
	protected static int[] tabQuestionHistorique = new int[5];//tableau des id qui ont déjà été ajoutés
	
	public QuestionReponse() {
		super();
		jeu = Jeu.getInstance();
	}
	
	public void setNumeroDeLaQuestion(int numeroDeLaQuestion) {
		this.numeroDeLaQuestion = numeroDeLaQuestion;
	}

	protected static void setTabQuestionHistorique(int[] tabQuestionHistorique) {
		QuestionReponse.tabQuestionHistorique = tabQuestionHistorique;
	}

	public int getLevel() {
		return level;
	}

	public int getNumeroDeLaQuestion() {
		return numeroDeLaQuestion;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public int getId() {
		return id;
	}

	public QuestionReponse getInstanceDeLaQuestion() {
		return this;
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
		String chaine[]={"01","Ma question est ","réponse 1", "réponse 2","réponse 3"};
		return chaine;
	}
	
	/*
	 * Fonction commune à la plupart des classes filles
	 * Elle permet d'extraire les éléments d'un tableau d'une position x jusqu'à la fin du tableau
	 */
	protected String[] extraireTousElementsTableau(String[] tableau, int x){
		String tab[] = null;
		int counterTab = 0;
		boolean encoreElement = true;
		int i=x;
		while (encoreElement){
			if (tableau[i]!=null){
				tab[counterTab] = tableau[i];
			}
			else {
				encoreElement = false;
			}
		}
		return tab;
	}
	
	public EXERCICES getTypeExo() {
		return typeExo;
	}
	
	@Override
	public String toString() {
		return "QuestionReponse [themeDuQuizz=" + themeDuQuizz + ", typeExo="
				+ typeExo + ", level=" + level + ", numeroDeLaQuestion="
				+ numeroDeLaQuestion + ", jeu=" + jeu + ", id=" + id + "]";
	}
}
