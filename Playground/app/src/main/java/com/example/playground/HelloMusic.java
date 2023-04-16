package com.example.playground;


import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

import java.util.Random;


public class HelloMusic {
    private RealtimePlayer player;
    private String noteLetter;
    private Note note;
    private Random random = new Random();

    public HelloMusic (String n) {
        assert n != null;
        noteLetter = n;
        note = new Note(noteLetter);
    }

    public String getLetter() {
        return noteLetter;
    }

    public Note getNote() {
        return note;
    }

    public void playNote(Note a) throws MidiUnavailableException {
        player = new RealtimePlayer();
        player.startNote(a);
    }
    public void changeInstrument() throws MidiUnavailableException{
        player = new RealtimePlayer();
        player.changeInstrument(random.nextInt(128));
    }

    public void stopNote(Note a) throws MidiUnavailableException {
        player = new RealtimePlayer();
        player.stopNote(a);
    }
    public Note makeQuarter(Note a) throws MidiUnavailableException {
       String letter = a.getOriginalString(); 
       letter += "q";
       note = new Note(letter);
       return note;
    }
    public Note makeWhole(Note a) throws MidiUnavailableException {
        String letter = a.getOriginalString(); 
        letter += "w";
        note = new Note(letter);
        return note;
    }
    public Note makeHalf(Note a) throws MidiUnavailableException {
        String letter = a.getOriginalString(); 
        letter += "h";
        note = new Note(letter);
        return note;
    }
    public Note makeEighth(Note a) throws MidiUnavailableException {
        String letter = a.getOriginalString(); 
        letter += "i";
        note = new Note(letter);
        return note;
    }
    //example use:
    //public static void main(String[] args) throws MidiUnavailableException {
        //HelloMusic myNote = new HelloMusic("G");
        //Player player = new Player();
        //player.play(myNote.makeQuarter(myNote.getNote()));
      //}
}