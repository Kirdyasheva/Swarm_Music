public class PSO1 {
    int lowerBound = 48;
    int upperBound = 72;
    int lowerMelody = 73;
    int upperMelody = 96;
    int[] reference = new int[15];
    int tonality;
    double c1 = 0.9;
    double c2 = 1.2;
    double m = 0.015;

    public PSO1(int tonality) {
        this.tonality = tonality;
        reference = setReference(tonality);
    }

  /*  public int[] PSO2(int[] notes) {
        int[] melody = new int[notes.length * 2];
        int j = 0;
        for (int i = 0; i < melody.length; i++) {
            if (i == 0) {
                melody[i] = PSO(setMelodyReference(notes[j], 0));
            } else {
                melody[i] = PSO(setMelodyReference(notes[j], melody[i - 1]));
            }
            melody[i] = PSO(setMelodyReference(notes[j], melody[i - 1]));
            j++;
        }
        return melody;
    }*/

  /*  private int[] setMelodyReference(int chord, int prevNote) {
        int reference[] = new int[32];
        int j = 0;
        int
        do {

        } while ()
    }*/

   /* private int[] melodyReference(int chord) {
        int[] a = new int[15];
        int current = chord;
        int[] j = {chord}
        int i = 0;
        while (current <= upperMelody) {
            a[i] = current + 12;
            current += 12;
            i++;
        }
        int[] b = new int[i];

        return a;
    }*/


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

    private int[] resetReference(int[] notes, int k) { //reset array with possible values of chords depends on the neighbour note
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

    private boolean notNeighbour(int[] notes, int k, int referenceNote) {
        if (k - 1 >= 0 && referenceNote == notes[k - 1]) {
            return false;
        }
        if (k - 2 >= 0 && referenceNote == notes[k - 2]) {
            return false;
        }
        return true;
    }

    private int PSO(int[] reference) {
        Particles[] p = new Particles[100];
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
        } while (k < 10);
        return globalBest.getPosition();
    }

   /* private double calculateFitness(Particles p, int[] reference) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < reference.length; i++) {
            if (Math.abs(p.getPosition() - reference[i]) < min) {
                min = Math.abs(p.getPosition() - reference[i]);
            }
        }
        return min;
    }*/
}
