public class Event extends Card{
    EventVoid effect;
    public Event(String n, String p, boolean r, EventVoid e) {
        super(n, p, r, 4);
        effect = e;
    }
    public void runEffect() {
        effect.run();
    }
}
interface EventVoid {
    void run();
}
