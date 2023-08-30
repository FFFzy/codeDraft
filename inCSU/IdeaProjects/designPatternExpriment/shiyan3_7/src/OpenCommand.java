public class OpenCommand implements Command{
    BoardScreen boardScreen;

    OpenCommand(){
        boardScreen = new BoardScreen();
    }

    @Override
    public void execute() {
        boardScreen.open();
    }
}
