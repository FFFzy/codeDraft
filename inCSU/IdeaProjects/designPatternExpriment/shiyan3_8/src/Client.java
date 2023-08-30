public class Client {
    public static void main(String args[]){
        EditText et = new EditText();
        Observer observer1,observer2,observer3;
        observer1 = new WordNumber("第一个文本信息统计区");
        observer2 = new Word("第二个文本信息统计区");
        observer3 = new OrderByNumber("第三个文本信息统计区");

        et.attach(observer1);
        et.attach(observer2);
        et.attach(observer3);

        et.notifyObserver();
    }
}
