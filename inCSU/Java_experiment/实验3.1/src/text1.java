public class text1 {
    public static void main(String[] args){
        Rectangle rectangle1 = new Rectangle(4,40);
        System.out.println("矩形1的宽为："+rectangle1.width+" 矩形1的高为："+rectangle1.height+
                " 矩形1的面积为："+rectangle1.getArea()+" 矩形1的周长为："+rectangle1.getPerimeter());

        Rectangle rectangle2 = new Rectangle(3.5,35.9);
        System.out.println("矩形2的宽为："+rectangle2.width+" 矩形2的高为："+rectangle2.height+
                " 矩形2的面积为："+rectangle2.getArea()+" 矩形2的周长为："+rectangle2.getPerimeter());
    }
}

class Rectangle{
    double width;
    double height;

    Rectangle(){
        width = 1;
        height = 1;
    }

    Rectangle(double newWidth,double newHeight){
        width = newWidth;
        height = newHeight;
    }

    double getArea(){
        return width*height;
    }

    double getPerimeter(){
        return 2*(width+height);
    }

    void setWidthHeight(double newWidth,double newHeight){
        width = newWidth;
        height = newHeight;
    }
}

