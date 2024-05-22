public class StatChange {
    private final int[] stats;
    private int time;

    public StatChange(int[] s, int t) {
        time = t;
        stats = s;
    }

    public void tickDown() {
        time--;
    }

    public int[] getStats() {
        return stats;
    }

    public boolean hasRunOut() {
        return time == 0;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
