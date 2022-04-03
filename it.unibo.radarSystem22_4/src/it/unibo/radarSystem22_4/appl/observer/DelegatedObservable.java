package it.unibo.radarSystem22_4.appl.observer;

import java.util.Observable;

public class DelegatedObservable extends Observable   {
	
	/**********************************************
	* void addObserver( Observer o ): registra l’ Observer nel suo elenco interno di oggetti da notificare.
	* 
    *protected void setChanged(): registra il cambiamento di stato
    *
	*boolean hasChanged(): restituisce true se l’oggetto ha cambiato stato (setChanged)
	*
	*void notifyObservers(): se l’oggetto è cambiato notifica tutti gli Observer poi chiama il metodo 
	*						 clearChanged per segnare che l’oggetto è nello stato “non cambiato”.
	*
	* void notifyObservers( Object arg ): oltre alla notifica invia ad ogniObserver un oggetto come
	* 									  secondo parametro del metodo update.
	* 
	* protected void clearChanged(): impone lo stato “non cambiato”
	* 
	* int countObservers(): il numero di Observer registrati. 
	* 
	* void deleteObservers(): cancella l’elenco degli Observer registrati.
	* 
	* void deleteObservers(Observer o): cancella un Observer o dall’elenco degli observer.
	 * 
	 **********************************************/
	
	
	public void clearChanged() { super.clearChanged(); }
	public void setChanged() { super.setChanged();}

	
/*	public void startProcess() {
		
		setChanged(); notifyObservers("Processo iniziato");
		//== do something
		setChanged(); notifyObservers("Processo concluso");
		}
*/
}