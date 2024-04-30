public class Item extends Card {
    private int power;
    private Effect effect;
    private boolean isEvent;
    public Item(String n,String p, boolean r,boolean event,Effect e) {
        super(n,p, r,4);
        isEvent=event;
        effect=e;

    }
    public Item(String n,String p, boolean r,Effect e) {
        super(n,p, r,3);
        isEvent=false;
        effect=e;

    }

    public void runEffect() {
        effect.EffectVoid();
    }

    public int getPower() {
        return power;
    }
}
