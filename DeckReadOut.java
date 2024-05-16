public class DeckReadOut {
    public static void main(String[] args) {
        int chachterCount=0;
        int eventCount=0;
        int itemCount=0;
        int plasmaCount=0;
        int laserCount=0;
        int nameCount=0;

        for (int i = 0; i < Deck.BASE_DECK.size(); i++) {
            if(Deck.BASE_DECK.get(i).getType()==2) {
                chachterCount++;
            } else if(Deck.BASE_DECK.get(i).getType()==3) {
                itemCount++;
            } else if(Deck.BASE_DECK.get(i).getType()==4) {
                eventCount++;
            }
            if(Deck.BASE_DECK.get(i).getName()!=null) {
            if(Deck.BASE_DECK.get(i).getName().contains("Plasma") ) {
                plasmaCount++;
            } else  if(Deck.BASE_DECK.get(i).getName().contains("Laser")) {
                laserCount++;
            }
            nameCount++;

        }
        }
        System.out.println("chachterCount: "+chachterCount);
        System.out.println("eventCount: "+eventCount);
        System.out.println("itemCount: "+itemCount);
        System.out.println("plasmaCount: "+plasmaCount);
        System.out.println("LaserCount: "+laserCount);
    }

}
