/**
 * Class that generate notes with PSO algorithm
 */

import java.util.Arrays;

public class PSO {
    /**
     * @value lowerBound - the hardcoded lower point in our interval for generating chords
     * @value upperBound - the hardcoded upper point in our interval for generating chords
     * @value lowerMelody - the hardcoded lower point in our interval for generating melody
     * @value upperMelody - the hardcoded upper point in our interval for generation melody
     * @value reference[] - the array with possible first notes of chords. Depends of tonality
     * @value tonality - the tonality of our song
     * @value c1 - the constant for PSO
     * @value c2 - the constant for PSO
     * @value m - the impulse for PSO
     */
    int lowerBound = 48;
    int upperBound = 72;
    int lowerMelody = 73;
    int upperMelody = 96;
    int[] reference = new int[15];
    int tonality;
    double c1 = 1.2;
    double c2 = 1.2;
    double m = 0.8;

    /**
     * Constructor for creating object of PSO class
     * Defines reference notes depends on tonality
     *
     * @param tonality - the tonality of the song
     */
    public PSO(int tonality) {
        this.tonality = tonality;
        reference = setReference(tonality);
    }

    /**
     * This function generates melody notes for chords notes
     *
     * @param notes - generated noted of chords
     * @return generated notes of melody
     */
    public int[] PSO2(int[] notes) {
        int[] melody = new int[notes.length / 3 * 2];
        int j = 0;
        for (int i = 0; i < notes.length; i += 3) {
            if (i == 0) {
                melody[j] = PSO(melodyReference(notes[i], notes[i + 1], notes[i + 2], 0));
                j++;
            } else {
                melody[j] = PSO(melodyReference(notes[i], notes[i + 1], notes[i + 2], melody[j - 1]));
                j++;
            }
            melody[j] = PSO(melodyReference(notes[i], notes[i + 1], notes[i + 2], melody[j - 1]));
            j++;
        }
        return melody;
    }

    /**
     * Defines array with possible notes of the melody for the chord
     *
     * @param note1    - the first note of a chord
     * @param note2    - the second note of a chord
     * @param note3    - the third note of a chord
     * @param prevNote - the previous note of the melody
     * @return sorted array with possible notes of melody for the chord
     */
    private int[] melodyReference(int note1, int note2, int note3, int prevNote) {
        int[] a = new int[15];
        int j = 0;
        if (prevNote != 0) {
            for (int i = lowerMelody; i <= upperMelody; i++) {
                if ((i - note1) % 12 == 0 && Math.abs(i - prevNote) <= 12 && i != prevNote) {
                    a[j] = i;
                    j++;
                }
            }
            for (int i = lowerMelody; i <= upperMelody; i++) {
                if ((i - note2) % 12 == 0 && Math.abs(i - prevNote) <= 12 && i != prevNote) {
                    a[j] = i;
                    j++;
                }
            }
            for (int i = lowerMelody; i <= upperMelody; i++) {
                if ((i - note3) % 12 == 0 && Math.abs(i - prevNote) <= 12 && i != prevNote) {
                    a[j] = i;
                    j++;
                }
            }
        } else {
            for (int i = lowerMelody; i <= upperMelody; i++) {
                if ((i - note1) % 12 == 0) {
                    a[j] = i;
                    j++;
                }
            }
            for (int i = lowerMelody; i <= upperMelody; i++) {
                if ((i - note2) % 12 == 0) {
                    a[j] = i;
                    j++;
                }
            }
            for (int i = lowerMelody; i <= upperMelody; i++) {
                if ((i - note3) % 12 == 0) {
                    a[j] = i;
                    j++;
                }
            }
        }
        int[] b = new int[j];
        for (int i = 0; i < j; i++) {
            b[i] = a[i];
        }
        Arrays.sort(b);
        return b;
    }

    /**
     * Public function to run PSO algorithm
     *
     * @return array of the first notes of the sequence of chords
     */
    public int[] PSO() {
        int a[] = new int[16];
        for (int i = 0; i < 16; i++) {
            if (i == 0) {
                a[i] = PSO(reference);
            } else {
                a[i] = PSO(resetReference(a, i - 1));
            }
        }
        return a;
    }

    /**
     * Generates gamma depends on major tonality
     *
     * @param tonality - integer tonality in midi values
     * @return generated gamma in the array
     */
    private int[] setReference(int tonality) {
        int[] a = new int[15];
        int current = tonality;
        int j = tonality;
        int i = 0;
        while (j <= upperBound) {
            a[i] = current;
            if (((i + 1) % 7 == 0 && (i + 1) != 0) || ((i + 1) % 7 == 3)) {
                current++;
                j++;
            } else {
                current += 2;
                j += 2;
            }
            i++;
        }
        return a;
    }

    /**
     * The function redefine the array with possible notes.
     * New notes can't make intervals exceeds 12 and there are no the same notes in the one bar
     *
     * @param notes - notes of chords that had been already generated
     * @param k     - the number of the last generated note
     * @return array of new possible notes depends on generated notes
     */
    private int[] resetReference(int[] notes, int k) {
        int[] a = new int[24];
        int j = 0;
        for (int i = 0; i < reference.length; i++) {
            if (Math.abs(reference[i] - notes[k]) <= 12 && reference[i] != notes[k] && notNeighbour(notes, k, reference[i])) {
                a[j] = reference[i];
                j++;
            }
        }

        int b[] = new int[j];
        for (
                int i = 0;
                i < j; i++)

        {
            b[i] = a[i];
        }
        return b;
    }

    /**
     * The function defines if this note already exists in the bar
     *
     * @param notes         - the array of generated notes of chords
     * @param k             - the number of the last generated note
     * @param referenceNote - the array with possible notes to be chosen for generating chord
     * @return false if this note already exists in this bar, true otherwise
     */
    private boolean notNeighbour(int[] notes, int k, int referenceNote) {
        if (k - 1 >= 0 && referenceNote == notes[k - 1]) {
            return false;
        }
        if (k - 2 >= 0 && referenceNote == notes[k - 2]) {
            return false;
        }
        return true;
    }

    /**
     * The PSO algorithm for generating notes
     *
     * @param reference - the array with possible notes that can be started note of a chord
     * @return the next generated note
     */
    private int PSO(int[] reference) {
        Particles[] p = new Particles[1000];
        GlobalBest globalBest = new GlobalBest(reference);
        for (int j = 0; j < p.length; j++) {
            p[j] = new Particles(c1, c2, m, (double) reference[0], (double) reference[reference.length - 1]);
        }
        int best = globalBest.countGlobal(p, reference);

        for (int j = 0; j < p.length; j++) {
            p[j].setMyBest(p[j].updateMyBest(reference));
            p[j].setMyBestPosition(p[j].getPosition());
        }

        int k = 0;
        do {
            for (int i = 0; i < p.length; i++) {
                p[i].setVelocity(globalBest.countGlobal(p, reference));
                p[i].setPosition();
                if (p[i].getMyBest() > p[i].updateMyBest(reference)) {
                    p[i].setMyBest(p[i].updateMyBest(reference));
                    p[i].setMyBestPosition(p[i].getPosition());
                }
            }
            k++;
        } while (k < 100);
        return globalBest.getPosition();
    }
}
