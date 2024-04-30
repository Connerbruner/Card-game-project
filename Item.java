public class Item extends Card {
    private int power;
    private Effect effect;
    private boolean isEvent;
    public Item(String n,String p, boolean r,boolean event,Effect e) {
        super(n,p, r,2);
        isEvent=event;
        effect=e;

    }

    public Effect getEffect() {
        return effect;
    }

    public int getPower() {
        return power;
    }
}
