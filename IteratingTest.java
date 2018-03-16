import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratingTest{

  public static class Card{
  
    int test = 0;
  
    @Override
    public String toString(){
      return String.format("Card %d", test);
    } 
  
  }
  public static class Deck {
  
    private List<Card> cards = new ArrayList<Card>() {{
    
    add(new Card());
    add(new Card());
    add(new Card());
    add(new Card());
    add(new Card());
    add(new Card());
    
    
    
    }};
      public Iterator<Card> iterator(){
      return cards.iterator(); 
    }
    @Override 
    public String toString(){
      return cards.toString();
    }
  
  }

  public static void main(String[] args){
    Deck deck = new Deck();
    System.out.println(deck);
    Iterator<Card> iter = deck.iterator();
    Card card = iter.next();
    card.test = 4;
    System.out.println(deck);
    
  }
}