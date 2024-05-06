public class StatChange {
    private int time;
    private int[] stats;
    public StatChange(int[] s,int t) {
        time=t;
        stats=s;
    }
    public void tickDown() {
        time--;
    }

    public int[] getStats() {
        return stats;
    }

    public boolean hasRunout() {
        return time<1;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
