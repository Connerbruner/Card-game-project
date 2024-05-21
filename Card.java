public class Card {
    private String path;
    private int type;
    private String name;

    public Card(String p) {
        name="";
        path=p;
        type=0;

    }
    public Card(String n,String p,int t) {
        path=p;
        type=t;
        name=n;

    }

    public void trigger() {

    }

    public int getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
