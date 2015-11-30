package wortspiegeln;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Dies ist die Hauptmethode des Spiegelprojektes. Sie nimmt vom Nutzer ein Wort entgegen und gibt ihm das Spiegelwort aus.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class Spiegel {

	private String ursprungswort;
	private String spiegelwort = "";
	
	/**
	 * Diese Methode dient als Eingabe des Wortes. Sie wiederholt sich selbst, wenn der Nutzer das Feld leer gelassen hat.
	 */
	private void worteingabe() {
		JTextField textfeldname1 = new JTextField(new Feldbegrenzung(50), "", 0);
		
		Object[] namensfrage = {"Welches Wort möchtest Du spiegeln?", textfeldname1};
	    JOptionPane pane = new JOptionPane(namensfrage, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
	    pane.createDialog(null, "Wort spiegeln").setVisible(true);
	    
	    ursprungswort = textfeldname1.getText();
	    if(ursprungswort.equals("")) {
	    	JOptionPane.showMessageDialog(null, "Du hast kein Wort eingegeben.\nBitte hole dies nun nach.", "Kein Wort eingegeben", JOptionPane.WARNING_MESSAGE);
	    	worteingabe();
	    }
	}
	
	/**
	 * Diese Methode spiegelt das eingegebene Wort. Ausserdem achtet sie darauf, dass der erste Buchstabe gross und der letzte Buchstabe klein geschrieben wird.
	 */
	private void spiegeln() {
		for(int i=ursprungswort.length()-1;i>=0;i--) {
			if((i==ursprungswort.length()-1 || i==0) && ursprungswort.length()>1) {
				if(Character.isUpperCase(ursprungswort.charAt(i))==true) {
					String temp = ursprungswort.charAt(i)+"";
					String temp2 = temp.toLowerCase();
					spiegelwort += temp2;
				} else {
					String temp = ursprungswort.charAt(i)+"";
					String temp2 = temp.toUpperCase();
					spiegelwort += temp2;
				}
			} else {
				spiegelwort += ursprungswort.charAt(i);
			}
		}
	}
	
	/**
	 * Diese Methode gibt das Spiegelwort aus. Ausserdem weist sie den Benutzer darauf hin, wenn er ein Palindrom gefunden haben sollte.<br>
	 * Anschliessend fragt sie nach, ob ein neues Wort gespiegelt oder das Programm beendet werden soll.
	 */
	private void ausgabe() {
		if(ursprungswort.toLowerCase().equals(spiegelwort.toLowerCase())) {
			JOptionPane.showMessageDialog(null, "Herzlichen Glückwunsch!\nDu hast ein Palindrom gefunden!\nDas Spiegelwort von "+ursprungswort+" ist:\n"+spiegelwort, "Palindrom!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Das Spiegelwort von "+ursprungswort+" ist:\n"+spiegelwort, "Spiegelwort", JOptionPane.INFORMATION_MESSAGE);
		}
		int dialogneustart = JOptionPane.showConfirmDialog(null, "Möchtest Du ein weiteres Wort spiegeln?", "Neue Spiegelung?", JOptionPane.YES_NO_OPTION);
        if(dialogneustart == 0) {
     	   Spiegel spiegel = new Spiegel();
     	   spiegel.worteingabe();
     	   spiegel.spiegeln();
     	   spiegel.ausgabe();
        } else {
     	   System.exit(0);
        }
	}
	
	public static void main(String[] args) {
		Spiegel spiegel = new Spiegel();
		spiegel.worteingabe();
		spiegel.spiegeln();
		spiegel.ausgabe();
	}
}