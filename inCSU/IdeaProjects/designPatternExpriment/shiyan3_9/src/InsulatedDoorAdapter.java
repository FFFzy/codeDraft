public class InsulatedDoorAdapter implements Observer{
    private String name;
    private InsulatedDoor insulatedDoor;

    public InsulatedDoorAdapter(String name,InsulatedDoor insulatedDoor){
        this.name = name;
        this.insulatedDoor = insulatedDoor;
    }

    public void update(){
        insulatedDoor.close();
    }

    @Override
    public String getName() {
        return name;
    }
}
