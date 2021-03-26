import io.netty.util.NettyRuntime;
import org.junit.Test;

/**
 * @author: xh  2021-03-25 10:41
 */
public class NettyRuntimeTest {


    @Test
    public void test1(){
        System.out.println(NettyRuntime.availableProcessors());
    }
}
