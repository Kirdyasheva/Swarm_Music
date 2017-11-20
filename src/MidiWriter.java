/**
 * This class is used to write our sequence of notes to midi file
 */

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import java.io.File;
import java.io.IOException;

class MidiWriter {

    int[] chords;
    int[] melody;

    /**
     * Constructor for saving generated notes to local arrays
     *
     * @param chords - the array with notes of chords
     * @param melody - the array with notes of melody
     */

    public MidiWriter(int[] chords, int[] melody) {
        this.chords = chords;
        this.melody = melody;
    }

    /**
     * Converts string to midi file
     *
     * @param tempo       - hardcoded BMP of music
     * @param musicString - string built by generateMusicString()
     */

    private void createMidiFile(int tempo, String musicString) {
        String midiFileNameEnd = "song.mid";
        Pattern pattern = new Pattern(musicString).setVoice(0).setInstrument("Piano").setTempo(tempo);
        try {
            MidiFileManager.savePatternToMidi(pattern, new File(midiFileNameEnd));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Public function for generating midi file
     */

    public void generateMusicString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            if (i % 2 == 0) {
                int m = melody[i];
                int c = chords[i / 2];
                sb.append(c).append("qa45+").append(m).append("ia55 ");
            } else {
                sb.append(melody[i]).append("ia55 ");
            }
        }
        createMidiFile(120, sb.toString());
    }
}