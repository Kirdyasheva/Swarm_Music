import javax.sound.midi.*;

public class Program {
    public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
        int tonality = 48;
        PSO pso = new PSO(tonality);
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
        Thread.sleep(2000);
        int[] melody = pso.PSO2(chords);
        j = 0;
        MidiChannel[] channel4 = synth.getChannels();
        MidiChannel[] channel5 = synth.getChannels();
        for (int i = 0; i < chords.length - 4; i += 3) {
            channel1[2].noteOn(chords[i], 60);
            channel2[2].noteOn(chords[i + 1], 60);
            channel3[2].noteOn(chords[i + 2], 60);
            channel4[2].noteOn(melody[j], 60);
            Thread.sleep(250);
            channel4[2].noteOff(melody[j]);
            j++;
            channel5[2].noteOn(melody[j], 60);
            Thread.sleep(250);
            channel5[2].noteOff(melody[j], 60);
            channel1[2].noteOff(chords[i]);
            channel2[2].noteOff(chords[i + 1]);
            channel3[2].noteOff(chords[i + 2]);
            j++;
            System.out.print(i);
            System.out.println();
            System.out.println(j);
        }
        MidiWriter writer = new MidiWriter(chords, melody);
        writer.generateMusicString();
    }
}
