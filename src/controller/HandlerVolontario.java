package controller;

import java.util.List;
import java.util.Map;

import dto.VisitaDTO;
import main.App;
import utility.MethodName;

public class HandlerVolontario extends ControllerUtente {
	//Precondizioni di tutti i metodi: param != null
	
	public HandlerVolontario(ControllerArchivio gdb, String username, App a) {
		this.gdb = gdb;
		this.username = username;
	}
	
	@MethodName("Visualizza i tipi di visita a cui sei collegato")
 	public void visualizzaTipiVisita(App a) {
		List<String> listaTipi = gdb.getElencoTipiVisiteVolontario(this);
		for (String tipo : listaTipi) {
			a.view(tipo);
		}
 	}
	
	private void visualListVisitDTO (List<VisitaDTO> visite, App a) {
		if (visite.size() != 0) {
			for (VisitaDTO v : visite) {
					a.view("-----------");
					a.view("Titolo: " +  v.getTitolo());
					a.view("Descrizione: " +  v.getDescrizione());
					a.view("Punto d'incontro: " +  v.getPuntoIncontro());
					a.view("Giorno: " +  v.getGiorno());
					a.view("Ora d'inizio: " +  v.getOraInizio());
					a.view("Da acquistare: " +  v.getDaAcquistare());
					a.view("Stato: " +  v.getStato());
					a.view("Tag: " +  v.getTag());
					String codiciPrenotazioni = "";
					for (int i = 0 ; i < v.getPrenotazioni().size() ; i++) {
						codiciPrenotazioni += "Codice: " + v.getPrenotazioni().get(i).getCodice() + ", n. iscritti:" + v.getPrenotazioni().get(i).getNum_da_prenotare() + "\n";
					}
					a.view(codiciPrenotazioni);
			}
		}
		else a.view("Nessuna visita confermata.");
	}
	
	@MethodName("Visualizza le visite confermate che gestirai")
	public void vediVisiteConfermate (App a) {
		List<VisitaDTO> visite = gdb.visiteConfermateVolontario(this);
		visualListVisitDTO(visite, a);
	}
	//Postcondizione: disponibilità in Archivio
	@MethodName("Comunica le tue prossime disponibilità")
 	public void comunicaDisponibilita(App a) {
 		if (gdb.getPossibilitaDareDisponibilita()) { //se posso dare disponibilità	
 			Map<String, List<String>> dateDisponibilita = gdb.getDatePerDisponibilita(this); //prendi disponibilità possibili
 			if (dateDisponibilita == null) { //TODO se null significa che il volontario dovrebbe essere eliminato
 				a.view("I tipi di visita a te associati non richiedono nuove disponibilità o c'è un problema con l'archivio, contatta un configuratore.");
 			}
 			else { //se ho disponibilità
 				for (String k : dateDisponibilita.keySet()) {
 					a.view(k.equals("Date precluse") ? k + ":" : "Giorni tipo " + k + ":"); //visualizzo le disponibilità
 					String days = "";
 					for (String i : dateDisponibilita.get(k)) {
 						days += i + " ";
 					}
 					a.view(days);
 				}
 				a.view("Indica le tue disponibilità.");
 				String data = "";
 				boolean b = true;
 				do { //le inserisco
 					data = a.richiediDataValida("data in cui dai disponibilità (dd-mm-yyyy)"); //inserisco data
 					b = gdb.inserisciDisponibilita(this, data, username); //controllo se inserita
 					if (b) { //se inserita chiedo se vuole continuare
 						a.view("La tua disponibilità è stata inserita.");
 						b = a.chiediSioNo("Vuoi aggiungere altre disponibilità?");
 					}
 					else { //se non inserita richiedo
 						a.view("La tua disponibilità non è stata inserita, assicurati che sia una data corretta.");
 						b = a.chiediSioNo("Vuoi continuare ad aggiungere?"); //do possibilità di chiudere loop
 					}
 				} while (b); //TODO se viene inserito un formato errato chiude
 				
 			}
 			
 		}
 		else a.view("Non puoi al momento comunicare le tue disponibilità.");
 	}
	
}
