import javax.sound.midi.*;

public class Program {

    public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
        int tonality = 48;
        PSO1 pso = new PSO1(tonality);
        int[] a = pso.PSO();

        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        MidiChannel[] channel1 = synth.getChannels();
        MidiChannel[] channel2 = synth.getChannels();
        MidiChannel[] channel3 = synth.getChannels();
        for (int i = 0; i < a.length; i++) {
            playChord(tonality, a[i]);
        }
    }

    public static void playChord(int tonality, int note) throws MidiUnavailableException, InterruptedException {
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        MidiChannel[] channel1 = synth.getChannels();
        MidiChannel[] channel2 = synth.getChannels();
        MidiChannel[] channel3 = synth.getChannels();
        switch ((note - tonality) % 7) {
            case 2: {
                channel1[2].noteOn(note, 60);
                channel2[2].noteOn(note + 2, 60);
                channel3[2].noteOn(note + 3, 60);
                Thread.sleep(500);
                channel1[2].noteOff(note);
                channel2[2].noteOff(note + 2);
                channel3[2].noteOff(note + 3);
                break;
            }
            case 4: {
                channel1[2].noteOn(note, 60);
                channel2[2].noteOn(note + 1, 60);
                channel3[2].noteOn(note + 3, 60);
                Thread.sleep(500);
                channel1[2].noteOff(note);
                channel2[2].noteOff(note + 1);
                channel3[2].noteOff(note + 3);
                break;
            }
            default: {
                channel1[2].noteOn(note, 60);
                channel2[2].noteOn(note + 2, 60);
                channel3[2].noteOn(note + 4, 60);
                Thread.sleep(500);
                channel1[2].noteOff(note);
                channel2[2].noteOff(note + 2);
                channel3[2].noteOff(note + 4);
            }
        }
    }
}
