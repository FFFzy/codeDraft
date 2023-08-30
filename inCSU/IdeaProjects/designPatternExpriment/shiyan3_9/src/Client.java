public class Client {
    public static void main(String args[]){
        Thermosensor thermosensor = new Thermosensor();
        Observer observer1,observer2,observer3,observer4;

        observer1 = new AnnunciatorAdapter("报警器",new Annunciator());
        observer2 = new CautionLightAdapter("警示器",new CautionLight());
        observer3 = new InsulatedDoorAdapter("隔热门",new InsulatedDoor());
        observer4 = new SecurityDoorAdapter("安全逃生门",new SecurityDoor());

        thermosensor.attach(observer1);
        thermosensor.attach(observer2);
        thermosensor.attach(observer3);
        thermosensor.attach(observer4);

        thermosensor.notifyObserver();
    }
}
