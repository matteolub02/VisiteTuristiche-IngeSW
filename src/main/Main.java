package main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import archivio.ArchivioJSON;
import controller.ControllerArchivio;
import controller.Login;

/*
 * Progetto di ingegneria del software: Visite Turistiche
 * @author David Brembati (741041), Matteo Lublanis(736418), Singh Sahiljit (740552)
 */
public class Main {
	public static void main (String args[]) throws IllegalAccessException, InvocationTargetException, UnsupportedAudioFileException, IOException, LineUnavailableException {
    		ArchivioJSON d = new ArchivioJSON();
    		System.out.println("Applicazione avviata...");
    		ControllerArchivio gdb = new ControllerArchivio(d);
    		Login gl = new Login(gdb);
    		App app = new App(gl); 
    		app.start();
    }
}