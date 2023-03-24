
import javax.sound.midi.MidiUnavailableException;
import java.util.Random;

import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

public class Test {
    private RealtimePlayer player;
    private String noteLetter;
    private Note note;
    private Random random = new Random();

    public Test (String n) {
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
}

