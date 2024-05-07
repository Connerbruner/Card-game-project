public class Event extends Card{
    EventVoid effect;
    public Event(String n, String p, EventVoid e) {
        super(n, p, 4);
        effect = e;
    }
    public void trigger() {
        effect.run();
    }
    public void setEffect(EventVoid e) {
        effect=e;
    }
}
interface EventVoid {
    void run();
}
