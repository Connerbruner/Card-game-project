public class Item extends Card {
    private int power;
    private Effect effect;
    public Item(String p, boolean r,int p1,Effect e) {
        super(p, r,2);
        power=p1;
        effect=e;

    }

    public Effect getEffect() {
        return effect;
    }

    public int getPower() {
        return power;
    }
}
