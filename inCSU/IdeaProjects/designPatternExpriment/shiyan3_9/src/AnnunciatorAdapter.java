public class AnnunciatorAdapter implements Observer{
    private String name;
    private Annunciator annunciator;

    public AnnunciatorAdapter(String name,Annunciator annunciator){
        this.name = name;
        this.annunciator = annunciator;
    }

    public void update(){
        annunciator.alarm();
    }

    @Override
    public String getName() {
        return name;
    }
}
