package wechat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class WxServlet extends HttpServlet {
    private final String token = "hnust";

    public WxServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("开始签名校验");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        ArrayList<String> array = new ArrayList();
        array.add(signature);
        array.add(timestamp);
        array.add(nonce);
        String sortString = sort("hnust", timestamp, nonce);
        String mytoken = SHA1(sortString);
        if (mytoken != null && mytoken != "" && mytoken.equals(signature)) {
            System.out.println("校验签名通过");
            response.getWriter().println(echostr);
        } else {
            System.out.println("校验签名失败");
        }

    }

    public static String sort(String token, String timestamp, String nonce) {
        String[] strArray = new String[]{token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sbuilder = new StringBuilder();
        String[] var8 = strArray;
        int var7 = strArray.length;

        for(int var6 = 0; var6 < var7; ++var6) {
            String str = var8[var6];
            sbuilder.append(str);
        }

        return sbuilder.toString();
    }

    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuffer hexString = new StringBuffer();

            for(int i = 0; i < messageDigest.length; ++i) {
                String shaHex = Integer.toHexString(messageDigest[i] & 255);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }

                hexString.append(shaHex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException var6) {
            var6.printStackTrace();
            return "";
        }
    }

    public static Map parseXml(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        Iterator var8 = elementList.iterator();

        while(var8.hasNext()) {
            Element e = (Element)var8.next();
            map.put(e.getName(), e.getText());
        }

        inputStream.close();
        inputStream = null;
        return map;
    }

    private static String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        boolean var2 = true;

        int i;
        while((i = is.read()) != -1) {
            baos.write(i);
        }

        return baos.toString();
    }

    public static void main(String[] args) {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("请求进入");
        String responseMessage = "";

        try {
            Map<String, String> map = MessageHandlerUtil.parseXml(request);
            System.out.println("开始构造消息");
            responseMessage = MessageHandlerUtil.buildResponseMessage(map);
            System.out.println(responseMessage);
            if (responseMessage.equals("")) {
                responseMessage = "未正确响应";
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            System.out.println("发生异常" + var5.getMessage());
        }

        response.getWriter().println(responseMessage);
    }
}
