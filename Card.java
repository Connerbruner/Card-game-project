public class Card {
    private String path;
    private boolean rare;
    private int type;

    public Card(String p,boolean r) {
        path=p;
        rare=r;
        type=0;

    }
    public Card(String p,boolean r,int t) {
        path=p;
        rare=r;
        type=t;

    }
    public void trigger() {}

    public int getType() {
        return type;
    }

    public boolean isRare() {
        return rare;
    }

    public String getPath() {
        return path;
    }
}
