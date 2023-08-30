public class SecurityDoorAdapter implements Observer{
    private String name;
    private SecurityDoor securityDoor;

    public SecurityDoorAdapter(String name,SecurityDoor securityDoor){
        this.name = name;
        this.securityDoor = securityDoor;
    }

    public void update(){
        securityDoor.open();
    }

    @Override
    public String getName() {
        return name;
    }
}
