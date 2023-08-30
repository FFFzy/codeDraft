import java.text.SimpleDateFormat;
import java.util.Date;

public class ProxyBusiness implements Business{
    private RealBusiness realBusiness;

    public void before(){
        Date now = new Date();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        System.out.println("方法method()被调用，调用时间为"+time.format(now));
    }

    public void after(){
        System.out.println("方法method()调用成功");
    }

    @Override
    public void method() {
        realBusiness = new RealBusiness();
        before();
        try{
            realBusiness.method();
            after();
        } catch (NullPointerException e){
            System.out.println("方法method()调用失败");
        }
    }
}
