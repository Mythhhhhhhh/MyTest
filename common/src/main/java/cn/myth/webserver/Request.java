package cn.myth.webserver;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

/**
 * 请求整个报文
 */
@Data
public class Request {

    private String requestContent;

    private String requestURI;

    private String method;

    private String protocol;

    /**
     * 将输入流解析成请求报文
     */
    private InputStream inputStream;

    public Request(InputStream inputStream) {

    }

    private void parse() {
        byte[] bytes = new byte[2048];
        int read = 0;
        try {
            read = inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < read; i++) {
            stringBuffer.append((char) bytes[i]);
        }
        this.requestContent = stringBuffer.toString();
    }

    public String getRequestURI() {
        //System.out.println(requestContent);
        String substring = null;
        int i = 0;
        if (requestContent != null) {
            int begin = requestContent.indexOf(' ');
            int end = requestContent.indexOf(' ', begin + 1);
            substring = requestContent.substring(begin + 2, end);
            //检查substring是否包含?
            i = substring.indexOf('?');
            if(i == -1){
                return substring;
            }
        }
        return substring.substring(0,i);
    }
}
