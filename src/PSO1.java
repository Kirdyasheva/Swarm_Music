public class PSO1 {
    int lowerBound = 48;
    int upperBound = 72;
    int[] reference = new int[15];
    int tonality;
    double c1 = 1.1;
    double c2 = 1.2;
    double m = 1;

    public PSO1(int tonality) {
        this.tonality = tonality;
        reference = setReference(tonality);
    }

    public int PSO() {
        return PSO(reference);
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

    private int[] resetReference(int note) { //reset array with possible values of chords depends on the neighbour note
        int[] a = new int[24];
        int j = 0;
        for (int i = 0; i < reference.length; i++) {
            if (Math.abs(reference[i] - note) <= 12 && reference[i] != note) {
                a[j] = reference[i];
                j++;
            }
        }
        int b[] = new int[j - 1];
        for (int i = 0; i < j; i++) {
            b[i] = a[i];
        }
        return b;
    }

    private int PSO(int[] reference) {
        Particles[] p = new Particles[1000];
        GlobalBest globalBest = new GlobalBest(reference);
        for (int j = 0; j < 1000; j++) {
            p[j] = new Particles(c1, c2, m, (double) reference[0], (double) reference[reference.length - 1]);
        }
        int best = globalBest.countGlobal(p, reference);

        for (int j = 0; j < 1000; j++) {
            p[j].setMyBest(Math.abs(best - p[j].getPosition()));
        }

        int k = 0;
        do {
            for (int i = 0; i < p.length; i++) {
                p[i].setVelocity(globalBest.countGlobal(p, reference));
                p[i].setPosition();
                if (p[i].getMyBest() < p[i].updateMyBest(reference)) {
                    p[i].setMyBest(p[i].updateMyBest(reference));
                }
            }
            k++;
        } while (k < 100);
        return globalBest.getPosition();
    }

    private double calculateFitness(Particles p, int[] reference) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < reference.length; i++) {
            if (Math.abs(p.getPosition() - reference[i]) < min) {
                min = Math.abs(p.getPosition() - reference[i]);
            }
        }
        return min;
    }
}
