package archivio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import dto.PrenotazioneDTO;
import dto.UserDTO;
import dto.VisitaDTO;
import utility.Credenziali;

public interface Archivio {
	public boolean checkPrimoAvvio ();
	public int getTipoUtente (String username);
	//Precondizione: CallerType = CostantiStruttura.CONFIGURATORE
	public boolean tryPubblicaPiano();
	/*
	 * Precondizione: CallerType = CostantiStruttura.CONFIGURATORE
	 * Postcondizione: prodotte visite del mese i+1
	 */
	public boolean pubblicaPiano();
	//Precondizione: CallerType = CostantiStruttura.CONFIGURATORE
	public boolean tryChiudiRaccoltaDisponibilita ();
	public boolean chiudiRaccoltaDisponibilita();
	//Precondizione: CallerType = CostantiStruttura.CONFIGURATORE
	public boolean tryApriRaccoltaDisponibilita();
	public boolean apriRaccoltaDisponibilita();
	public boolean checkIfCanLinkVolontario(String volontario, String tipoVisita);
	
	//Precondizione: callerType = CostantiStruttura.CONFIGURATORE
	public boolean associaVolontarioEsistenteATipoVisitaEsistente(String volontario, String tipoVisita);
	public boolean getPossibileDareDisponibilita();
	//Precondizione: callerTyper = CostantiStruttura.VOLONTARIO
    public boolean inserisciDisponibilita(String data, String username);
	//Precondizione: callerType = CostantiStruttura.VOLONTARIO
    public Map<String, List<String>> getDatePerDisponibilita(String username);
	public Set<String> getElencoTipiVisite ();
 	public List<String> getElencoTipiVisiteVolontario (String username);
	//Precondizione: callerTyper = CostantiStruttura.CONFIGURATORE
	public boolean tryImpostaCredenzialiNuovoVolontario (String username, String password, Set<String> tipi_visiteVal, boolean tipiVisitaNecessario);
	
	/*
	 * Precondizione: callerType = CostantiStruttura.CONFIGURATORE
	 * Postcondizione: new Volontario in Archivio
	 */
	public boolean impostaCredenzialiNuovoVolontario (String username, String password, JSONArray tipi_visite, boolean tipiVisitaNecessari);
	//Precondizione: callerType = CostantiStruttura.CONFIGURATORE
	public Map<String, List<String>> getElencoTipiVisiteLuogo ();
	public List<String> getElencoLuoghiVisitabili ();
	/*
	 * Precondizione: 0 < tipo_user < 4
	 */
	public Set<UserDTO> getListaUser(int tipo_user);
	//Precondizione: max > 0
	public boolean impostaMaxPrenotazione(int max);
	//Precondizione: callerType = CostantiStruttura.CONFIGURATORE
	public boolean impostaCredenzialiNuovoConfiguratore(String username, String password);
	public boolean isPrimaConfigurazione();
	public void impostaAmbitoTerritoriale(String nomeAmbito);
	public boolean checkIfUserExists (String username);
	public boolean impostaCredenzialiNuovoFruitore(String username, String password);
	public boolean checkIfVisitTypeExists (String tipo);
	//Precondizione: callerTyper = CostantiStruttura.CONFIGURATORE
	public boolean tryAggiungiVisite (String luogo, String tipoVisita, String titolo, String descrizione, String puntoIncontro, 
			String dataInizio, String dataFine, ArrayList<Integer> giorniPrenotabiliVal, String oraInizio,
			int durataVisita, boolean daAcquistare, int minFruitore, int maxFruitore, ArrayList<String> volontariVal);
	public boolean checkIfPlaceExists (String luogo);
	//Precondizione: callerTyper = CostantiStruttura.CONFIGURATORE
	public boolean aggiungiLuogo (String tag, String nome, String descrizione, String collocazione, Set<String> tipiVisitaVal);
	public void setPrimaConfigurazione();
	public boolean modificaCredenziali (String username, Credenziali c);
	public boolean primoAccessoEseguito (String user);
	
	/*
	 * Precondizione: callerType = CostantiStruttura.VOLONARIO
	 * Postcondizione: remove Prenotazione
	 */
	public boolean rimuoviPrenotazione(String username, String codicePrenotazione);
	//Precondizione: callerType = CostantiStruttura.VOLONTARIO
	public List<VisitaDTO> visiteConfermateVolontario (String username);
	//Precondizione: callerType = CostantiStruttura.FRUITORE
	public List<PrenotazioneDTO> getElencoPrenotazioniFruitore (String username);
	public List<VisitaDTO> getElencoVisiteProposteConfermateCancellateFruitore();
	//Precondizione: callerType = CostantiStruttura.FRUITORE
	public List<VisitaDTO> getElencoVisiteProposteConfermateCancellatePrenotateDalFruitore (String username);
	public List<VisitaDTO> getElencoVisiteProposteCompleteConfermateCancellateEffettuate ();
	public List<VisitaDTO> getElencoVisiteProposteConfermateCancellateFruitoreGiornoDato (String date);
	
	/*
	 * Precondizione: callerType = CostantiStruttura.FRUITORE
	 * Postcondizione: new Prenotazione
	 */
	public String inserisciPrenotazione (String username, PrenotazioneDTO prenotazione);
	public boolean indicaDatePrecluse (String date);
	//Precondizione: callerTyper = CostantiStruttura.CONFIGURATORE
	public void setPossibilitaDareDisponibilitaVolontari(boolean b);
	public boolean isReleaseOrLaterDay();
	//Precondizione: callerTyper = CostantiStruttura.CONFIGURATORE
	public boolean isPrimaPubblicazione ();
	public boolean checkCredenzialiCorrette (Credenziali c);
	public boolean checkPrimoAccesso (String username);
	/*
	 * Precondizione: tipoVisita exists && associazioni esistenti
	 * Postcondizione: remove tipoVisita && remove associazioni
	 */
	public boolean rimuoviTipo (String tipo);
	/*
	 * Precondizione: luogo in Archivio && associazioni esistenti
	 * Postcondizione: remove Luogo && remove associazioniLuogo
	 */
	public boolean rimuoviLuogo (String luogo);
	
	/*
	 * Precondizione: callerType = CostantiStruttura.VOLONTARIO && tipiVisitaAssociati esistenti
	 * Postcondizione: remove Volontario && remove associazioni
	 */
	public boolean rimuoviVolontario (String volontario);
	//Precondizione: isPrimoAvvio
	public Credenziali getCredenzialiConfIniziale();
	public void setPrimoAvvio ();
	public boolean canAddOrRemove();
	public void removeVisiteEffettuateCancellate (); //controllo del DBMS?
	public void setVisiteCancellateConfermate (); //controllo del DBMS?
}
