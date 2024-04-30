public class StatChange {
    private int time;
    private int[] stats;
    public StatChange(int t,int[] s) {
        time=t;
        stats=s;
    }
    public void tickDown() {
        time--;
    }

    public int[] getStats() {
        return stats;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
