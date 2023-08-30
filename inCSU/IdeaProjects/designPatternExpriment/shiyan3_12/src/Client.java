public class Client {
    public static void main(String args[]){
        GameManager gameManager = GameManager.getInstance();
        gameManager.gameSetting();

        GameManager gameManager1 = GameManager.getInstance();
        if (gameManager == gameManager1){
            System.out.println("两个游戏管理器是相同实例.");
        }
        else {
            System.out.println("两个游戏管理器是不同实例.");
        }
    }
}
