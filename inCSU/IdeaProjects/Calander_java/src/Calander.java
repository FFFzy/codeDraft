import java.util.Scanner;

public class Calander {	/**	 * @param args 	 */
public static void main(String[] args) {
    // TODO Auto-generated method stub
    int Year,W;    //W星期
    int Month, Day;
    Scanner scan = new Scanner(System.in);
    System.out.println("Please enter the year：");
    Year = scan.nextInt();
    System.out.println("please input the month：");
    Month = scan.nextInt();
		  /*if(Month<=0||Month>=13)
		   *{
		   	 System.out.println("please input the month again");
			 Month = scan.nextInt();
			 }*/
    System.out.println("please input the day：");
    Day = scan.nextInt();
			/*if(Day<=0||Day>=31)
				{
					System.out.println("please input the date again");
					Day = scan.nextInt();
				}	*/
    //System.out.println(Y);
    int temp = ((Year - 1) + (Year - 1) / 4 - (Year - 1) / 100 + (Year - 1)/400)%7;
//公元元年的第一天为周一。
    //(year-1)/4-(year-1)/100+(year-1)/400计算闰年的个数
    W = temp%7+1;     //W用于计算今年第一天的星期
    int count = W;
    int day = 0;
    for(int m=1;m<=Month-1;m++) //i for month;

    {
        if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
        {
            day=31;
        }
        else if(m==4||m==6||m==9||m==11)
        {
            day=30;
        }
        else if(m==2)
        {
            if((Year%4==0&&Year%100!=0)||(Year%100==0&&Year%400==0))
                day=29;
            else
                day=28;
        }
        count+=day;
    }//count 合计本年已结1束月份的总天数
    W=(count+Day-1)%7;
    System.out.print(Year+"年"+Month+"月"+Day+"日"+"是星期");
    switch(W)
    {
        case 1 :
            System.out.print("一");break;
        case 2 :
            System.out.print("二");break;
        case 3 :
            System.out.print("三");break;
        case 4 :
            System.out.print("四");break;
        case 5 :
            System.out.print("五");break;
        case 6 :
            System.out.print("六");break;
        case 0 :
            System.out.print("天");break;
    }
    System.out.print("。");
}
}
