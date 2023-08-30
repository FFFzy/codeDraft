import java.util.ArrayList;

public class Thermosensor {
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer){
        System.out.println(observer.getName()+"开始监控温度传感器.");
        observers.add(observer);
    }

    public void detach(Observer observer){
        System.out.println("");
        observers.remove(observer);
    }

    public void notifyObserver(){
        System.out.println("机房达到指定温度，温度传感器自动传递信号.");
        for(Object obj:observers){
            ((Observer)obj).update();
        }
    }
}
