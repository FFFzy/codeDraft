public class text3 {
    public static void main(String[] args){
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8","9", "10", "Jack", "Queen", "King"};

        int i = (int)(Math.random()*52);
        String suit = suits[i/13];
        String rank = ranks[i%13];

        System.out.println("Card number " + (i+1) + " : "+ rank + " of " + suit);
    }
}
