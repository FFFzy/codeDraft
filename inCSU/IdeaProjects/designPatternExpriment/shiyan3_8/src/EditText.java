import java.util.ArrayList;

public class EditText {
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer){
        System.out.println(observer.getName()+"加入统计.");
        observers.add(observer);
    }

    public void detach(Observer observer){
        observers.remove(observer);
    }

    public void notifyObserver(){
        for (Object obj:observers){
            ((Observer)obj).update();
        }
    }
}
