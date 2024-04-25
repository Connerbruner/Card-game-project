public class Effect {
    private int type;
    private Void effectVoid;
    public Effect(int t,Void e) {
        type=t;
        effectVoid=e;
    }

    public int getType() {
        return type;
    }

    public void EffectVoid() {
        effectVoid.run();
    }
}
interface Void {
    void run();
}
