import javax.sound.midi.*;

public class Program {
    public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
        long startTime = System.currentTimeMillis();

        /**
         * @value tonality - the chosen tonality of the song
         */
        final int tonality = 48;
        PSO pso = new PSO(tonality);
        /**
         * @value a - arrays with the first notes of the chords
         */
        int[] a = pso.PSO();
        /**
         * chords - the array of generated chords
         */
        int[] chords = createChords(a, tonality);

        Thread.sleep(2000);

        int[] melody = pso.PSO2(chords);

        playSong(chords, melody);

        /**
         * This code runs generating midi file with arrays of chords and melody
         */
        MidiWriter writer = new MidiWriter(chords, melody);
        writer.generateMusicString();
    }

    /**
     * The finction creates chords depends on the first notes of the chords and major tonality
     *
     * @param a        - the array contains the first note of the chords
     * @param tonality - the chosen tonality
     * @return the array of chords
     * @throws MidiUnavailableException
     * @throws InterruptedException
     */
    public static int[] createChords(int[] a, int tonality) throws MidiUnavailableException, InterruptedException {
        int chords[] = new int[48];
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
        return chords;
    }

    /**
     * The function plays the created song
     *
     * @param chords - the array of created chords
     * @param melody - the array of created notes of the melodu
     * @throws MidiUnavailableException
     * @throws InterruptedException
     */
    public static void playSong(int[] chords, int[] melody) throws MidiUnavailableException, InterruptedException {
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        MidiChannel[] channel1 = synth.getChannels();
        MidiChannel[] channel2 = synth.getChannels();
        MidiChannel[] channel3 = synth.getChannels();
        MidiChannel[] channel4 = synth.getChannels();
        MidiChannel[] channel5 = synth.getChannels();
        int j = 0;
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
        }
    }
}
