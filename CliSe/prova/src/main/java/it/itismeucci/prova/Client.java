
package it.itismeucci.prova;

    import java.io.*;
    import java.net.*;
    import java.util.*;

public class Client {

//indirizzo server locale
String nomeServer ="localhost";

//porta per servizio data e ora
int portaServer = 6789;

Socket miosocket;

//buffer per'input da tastiera
BufferedReader tastiera;

//stringa inserita da utente
String stringaUtente;

//stringa ricevuta dal server
String stringaRicevutadalserver;
        
//stream di output
DataOutputStream outVersoServer;

//stream di input
BufferedReader inDalServer;

    public Socket connetti(){
        System.out.println("2 client partito in esecuzione ...");
        try {
            //per l'input da tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            
            //creo un socket
            miosocket = new Socket(nomeServer,portaServer);
            
            //mio socket = new Socket(InetAdress.getLocalHost(), 6789);
            //associo due oggetti al socket per effettuare la scrittura e la lettura
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader (miosocket.getInputStream()));
            
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
    return miosocket;
    }
    
    public void comunica(){
        try {   //leggi una riga
            
            System.out.println("4 ... inserisci la stringa da trasmettere al server:"+'\n');
            stringaUtente = tastiera.readLine();
            
            //la spedisco al server
            System.out.println("5 ... invio la stringa al server e attendo ...");
            outVersoServer.writeBytes( stringaUtente+'\n');
            //leggo la risposta del server
            stringaRicevutadalserver = inDalServer.readLine();
            System.out.println("8 ... risposta dal server"+'\n'+stringaRicevutadalserver);
            //chiudo la connessione
            System.out.println("9 CLIENT: termina elaborazione e chiude connessione ");
            miosocket.close();
            
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione col server!");
            System.exit(1);
        }
    }

    
    
    
    
}
