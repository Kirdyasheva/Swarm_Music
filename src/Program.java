import javax.sound.midi.*;

public class Program {
    public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
        int tonality = 48;
        PSO1 pso = new PSO1(tonality);
        int[] a = pso.PSO();
        int[] chords = new int[a.length * 3];

        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        MidiChannel[] channel1 = synth.getChannels();
        MidiChannel[] channel2 = synth.getChannels();
        MidiChannel[] channel3 = synth.getChannels();
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            switch ((a[i] - tonality) % 7) {
                case 2: {
                    chords[j] = a[i];
                    j++;
                    chords[j] = a[i] + 2;
                    j++;
                    chords[j] = a[i] + 3;
                    j++;
                    channel1[2].noteOn(a[i], 60);
                    channel2[2].noteOn(a[i] + 2, 60);
                    channel3[2].noteOn(a[i] + 3, 60);
                    Thread.sleep(500);
                    channel1[2].noteOff(a[i]);
                    channel2[2].noteOff(a[i] + 2);
                    channel3[2].noteOff(a[i] + 3);
                    break;
                }
                case 4: {
                    chords[j] = a[i];
                    j++;
                    chords[j] = a[i] + 1;
                    j++;
                    chords[j] = a[i] + 3;
                    j++;
                    channel1[2].noteOn(a[i], 60);
                    channel2[2].noteOn(a[i] + 1, 60);
                    channel3[2].noteOn(a[i] + 3, 60);
                    Thread.sleep(500);
                    channel1[2].noteOff(a[i]);
                    channel2[2].noteOff(a[i] + 1);
                    channel3[2].noteOff(a[i] + 3);
                    break;
                }
                default: {
                    chords[j] = a[i];
                    j++;
                    chords[j] = a[i] + 2;
                    j++;
                    chords[j] = a[i] + 4;
                    j++;
                    channel1[2].noteOn(a[i], 60);
                    channel2[2].noteOn(a[i] + 2, 60);
                    channel3[2].noteOn(a[i] + 4, 60);
                    Thread.sleep(500);
                    channel1[2].noteOff(a[i]);
                    channel2[2].noteOff(a[i] + 2);
                    channel3[2].noteOff(a[i] + 4);
                }
            }
        }
        int[] melody = pso.PSO2(chords);
    }
}
