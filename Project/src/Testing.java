import javax.sound.midi.MidiUnavailableException;

public class Testing {
        public static void main(String[] args) throws MidiUnavailableException {
            Test t = new Test("D");
            t.playNote(t.getNote());
        }
}
