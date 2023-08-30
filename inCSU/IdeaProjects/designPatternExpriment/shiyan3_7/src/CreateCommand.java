public class CreateCommand implements Command{
    BoardScreen boardScreen;

    CreateCommand(){
        boardScreen = new BoardScreen();
    }

    @Override
    public void execute() {
        boardScreen.create();
    }
}
