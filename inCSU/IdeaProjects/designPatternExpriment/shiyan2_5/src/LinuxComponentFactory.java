public class LinuxComponentFactory implements ComponentFactory{
    @Override
    public Button createButton() {
        return new LinuxButton();
    }

    @Override
    public Text createText() {
        return new LinuxText();
    }
}
