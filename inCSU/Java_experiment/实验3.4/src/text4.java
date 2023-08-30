import java.util.Scanner;

public class text4 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入二维数组的行数和列数：");
        int x = input.nextInt();
        int y = input.nextInt();
        double[][] a = new double[x][y];
        System.out.println("输入数组：");
        for (int i=0;i<x;i++){
            for(int j=0; j<y;j++){
                a[i][j]=input.nextDouble();
            }
        }
        Location location = locateLargetst(a);
        System.out.println("最大元素及其下标是："+location.maxValue+" 在（"+location.row+","+location.column+")");
    }

    public static Location locateLargetst(double[][] a){
        Location location = new Location();
        location.column = 0;
        location.row = 0;
        location.maxValue = a[0][0];
        for (int i=0;i<a.length;i++){
            for (int j = 0;j<a[i].length;j++){
                if (a[i][j]>location.maxValue){
                    location.maxValue = a[i][j];
                    location.row = i;
                    location.column = j;
                }
            }
        }
        return location;
    }
}

class Location{
    public double maxValue;
    public int row,column;
}
