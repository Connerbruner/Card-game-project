public class Event extends Card {
    private Effect effect;
    public Event(String p, boolean r, Effect e) {
        super(p, r,3);
        effect=e;

    }

    public Effect getEffect() {
        return effect;
    }
}
