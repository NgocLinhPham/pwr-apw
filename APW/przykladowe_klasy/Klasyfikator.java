import java.io.Serializable;
import java.util.List;


public interface Klasyfikator
{
	
	public void dodajDane(List<Obiekt> lista);
	// dodaje list� obiekt�w
	public void dodajDane(Obiekt obiekt);
	// dodaje jeden obiekt
	public Klasa zapytajO(Obiekt obiekt);
	// dokonuje klasyfikacji podanego obiektu
	public void zweryfikuj(boolean czyPoprawna);
	// (nie wiem, czy si� przyda) weryfikacja poprawno�ci poprzedniej klasyfikacji
	
	
	
}
