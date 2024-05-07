public class Document extends Event{
    String path;
    String name;
    int value;
    EventVoid event  = () -> {
        GameBoard.sPrintln("Found "+name+". Worth: $"+value);
        GameBoard.addScore(value);
    };
    public Document(String n,String p,int v) {
        super(n, p,()->{});
        name=n;
        path=p;
        value=v;
        setEffect(event);
    }
    public Document(Document d) {
        super(d.name,d.path,()->{});
        name=d.name;
        path=d.path;
        value=d.value;
        setEffect(d.event);

    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
