public abstract class DataProcessing {
    public void readData(){
        System.out.println("读取数据.");
    }

    public void formatChange(){
        System.out.println("转换数据格式.");
    }

    public abstract void callAlgorithm();

    public void display(){
        System.out.println("显示数据分类结果.");
    }

    public void processing(){
        readData();
        formatChange();
        callAlgorithm();
        display();
    }
}
