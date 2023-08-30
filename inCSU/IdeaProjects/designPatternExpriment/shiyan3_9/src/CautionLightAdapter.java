public class CautionLightAdapter implements Observer{
    private String name;
    private CautionLight cautionLight;

    public CautionLightAdapter(String name,CautionLight cautionLight){
        this.name = name;
        this.cautionLight = cautionLight;
    }

    public void update(){
        cautionLight.flicker();
    }

    @Override
    public String getName() {
        return name;
    }
}
