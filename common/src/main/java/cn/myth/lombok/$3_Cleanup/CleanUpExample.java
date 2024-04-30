package cn.myth.lombok.$3_Cleanup;

import lombok.Cleanup;
import org.junit.Test;

import java.io.*;

public class CleanUpExample {

    @Test
    public void example1() throws IOException {
        String inputFile = "xxx";
        String outputFile = "yyy";

        @Cleanup InputStream in = new FileInputStream(inputFile);
        @Cleanup OutputStream out = new FileOutputStream(outputFile);

        byte[] buffer = new byte[1024];
        int length;

        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }

        // 不需要显示关闭输入输出流，它们将在方法执行完毕后被自动关闭
        // @Cleanup存在一个潜在问题——如果字节码中的try块中出现异常，且finally中（对应关闭方法）也出现异常，那么前边的异常会被后边的异常“吞掉”。
    }

    public void destroy() {
        //System.out.println("destroy");
        throw new RuntimeException("Destroy");
    }

    @Test
    public void example2() {
        @Cleanup("destroy") CleanUpExample cleanUpExample = new CleanUpExample();
        throw new RuntimeException("example2");
    }

}
