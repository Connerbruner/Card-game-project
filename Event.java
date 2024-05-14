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
    public Event(Event e) {
        super(e.getName(), e.getPath(), 4);
        effect = e.effect;
    }
}
interface EventVoid {
    void run();
}
