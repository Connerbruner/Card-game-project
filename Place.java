public class Place extends Card {
    Effect effect;
    public Place(String p, boolean r,Effect e) {
        super(p, r,4);

    }

    public Effect getEffect() {
        return effect;
    }
}
