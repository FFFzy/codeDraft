public class EditCommand implements Command{
    BoardScreen boardScreen;

    EditCommand(){
        boardScreen = new BoardScreen();
    }

    @Override
    public void execute() {
        boardScreen.edit();
    }
}
