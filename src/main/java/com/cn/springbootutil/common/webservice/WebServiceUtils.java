package com.cn.springbootutil.common.webservice;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author: YZH
 * @create: 2020-03-11 14:09
 **/
public class WebServiceUtils {
    /**
     * 远程服务地址
     */
    /**
     * 服务器短信地址
     */
    private static final String ADDRESS = "http://192.168.180.4:8090/services/Sms";
    /**
     * 本地测试地址
     */
    // private static final String ADDRESS ="http://172.31.102.176:8085/demo/api?wsdl";

    /**
     * 测试地址
     */
    //private static  final String ADDRESS="http://121.201.75.147:10087/services/Sms";

    /**
     *        下发短信测试环境账号密码--无需vpn
     *        String soapXML = getXML("AjgDZw==","AlkDJF19UmtXMFAVUz9XYFw8B2I=");
     *        addresss =http://121.201.75.147:10087/services/Sms
     *        方法与文档一致
     */

    /**
     * 远程服务方法名--下发短信
     */
    private static final String INSERT_DOWN_SMS_METHOD="InsertDownSms";

    public static String encrypt(String txt, String key) {
        String encryptKey = "0f9cfb7a9acced8a4167ea8006ccd098";
        int ctr = 0;
        String tmp = "";
        int i;
        for (i = 0; i < txt.length(); i++) {
            ctr = (ctr == encryptKey.length()) ? 0 : ctr;
            tmp = tmp + encryptKey.charAt(ctr)
                    + (char) (txt.charAt(i) ^ encryptKey.charAt(ctr));
            ctr++;
        }
        return base64Encode(key(tmp, key));
    }

    /**
     * 解密算法
     */
    public static String decrypt(String cipherText, String key) {
        // base64解码
        try {
            cipherText = base64Decode(cipherText);
            cipherText = key(cipherText, key);
            String tmp = "";
            for (int i = 0; i < cipherText.length(); i++) {
                int c = cipherText.charAt(i) ^ cipherText.charAt(i + 1);
                String x = "" + (char) c;
                tmp += x;
                i++;
            }
            return tmp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String key(String txt, String encryptKey) {
        encryptKey = strmd5(encryptKey);
        int ctr = 0;
        String tmp = "";
        for (int i = 0; i < txt.length(); i++) {
            ctr = (ctr == encryptKey.length()) ? 0 : ctr;
            int c = txt.charAt(i) ^ encryptKey.charAt(ctr);
            String x = "" + (char) c;
            tmp = tmp + x;
            ctr++;
        }
        return tmp;
    }

    public static String base64Encode(String str) {
        return new sun.misc.BASE64Encoder().encode(str.getBytes());
    }

    public static String base64Decode(String str) {
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        if (str == null)
        { return null;}
        try {
            return new String(decoder.decodeBuffer(str));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final String strmd5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * obj-xml
     *
     * @param object
     * @return
     * @throws Exception
     */
    public static String objectToXml(Object object, Boolean needHead) throws Exception {
        Class<? extends Object> classType = object.getClass();
        //属性集合
        Field[] fields = classType.getDeclaredFields();
        String xml = "";
        for (Field field : fields) {
            String fieldName = field.getName();//属性名称
            String stringLetter = fieldName.substring(0, 1).toUpperCase();
            // 获得object对象相应的get方法
            String getName = "get" + stringLetter + fieldName.substring(1);
            // 获取相应的方法
            Method getMethod = classType.getMethod(getName, new Class[]{});
            // 调用源对象的get方法的值
            Object getValue = getMethod.invoke(object, new Object[]{});
            if (null == getValue) {
                getValue = "";
            }
            xml += "<" + fieldName + ">" + getValue + "</" + fieldName + ">";
        }
        if(needHead){
            xml = "<sendbody>" + xml + "</sendbody>";
        }
        return xml;
    }

    /**
     * 发送短信接口
     * @param mobile
     * @param code
     * @return
     */
    public Boolean sendMessage(String mobile, String code) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            send(mobile,code);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送失败了");
            //return false;
            //测试环境用
            return true;
        }
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("发送短信任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return true;

    }

    /**
     * 发送短信
     * @param mobile
     * @param code
     * @return
     * @throws Exception
     */
    public String send(String mobile, String code) throws Exception{
        //第一步：创建服务地址
        URL url = new URL(ADDRESS);
        System.out.println("请求的地址:" + ADDRESS);
        // URL url = new URL("http://121.201.75.147:10087/services/Sms");
        //第二步：打开一个通向服务地址的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("SOAPAction","");
        //第三步：设置参数
        //3.1发送方式设置：POST必须大写
        connection.setConnectTimeout(2000);
        connection.setRequestMethod("POST");
        //3.2设置数据格式：content-type
        connection.setRequestProperty("content-type", "text/xml;charset=utf-8");
        //3.3设置输入输出，因为默认新创建的connection没有读写权限，
        connection.setDoInput(true);
        connection.setDoOutput(true);
        StringBuilder sb = new StringBuilder();
        //第四步：组织SOAP数据，发送请求
        String username = encrypt("19", "chinagdn");
        String password=encrypt("Pzqmd@1533", "chinagdn");
        String soapXml = getxml(username,password,mobile,code);
        //将信息以流的方式发送出去
        //String soapxml = getTetsXml(username,password,"");
        System.out.println("发送saopXML文件:" + soapXml);
        OutputStream os = connection.getOutputStream();
        os.write(soapXml.getBytes());
        //第五步：接收服务端响应，打印
        int responseCode = connection.getResponseCode();
        InputStream errorStream = connection.getErrorStream();
        String responseMessage = connection.getResponseMessage();
        System.out.println("接收服务端响应代码:" + responseCode);
        //表示服务端响应成功
        if(200 == responseCode){
            //获取当前连接请求返回的数据流
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String temp = null;
            while(null != (temp = br.readLine())){
                sb.append(temp);
            }
            is.close();
            isr.close();
            br.close();
            System.out.println("接收服务端返回值: 发送电话：" + reStringToMap(sb.toString()).get("mobile")+"   msgId: " + reStringToMap(sb.toString()).get("msgId"));
        }
        try{

        }catch (Exception e){
            //保存发送短信的信息失败
            System.out.println("保存发送短信信息失败");
            e.printStackTrace();
        }
        os.close();
        System.out.println("接收服务端返回值去转译后的xml:" +reString2(sb.toString()));
        return sb.toString();
    }
    public static String getxml(String username, String password, String mobile, String code){
        String soapXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.chinagdn.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:InsertDownSms soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <username xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +username+"</username>\n" +
                "         <password xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +password+"</password>\n" +
                "         <batch xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\"></batch>\n" +
                "         <sendbody xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "\t\t<![CDATA[\n" +
                "\t\t<sendbody>\n" +
                "\t\t\t<message>\n" +
                "\t\t\t\t<orgaddr></orgaddr>\n" +
                "\t\t\t\t<mobile><![CDATA["+mobile+"]]]]><![CDATA[></mobile>\n" +
                "\t\t\t\t<content><![CDATA[本次修改密码的验证码为："+code+",请妥善保管好你的验证码]]]]><![CDATA[></content>\n" +
                "\t\t\t\t<sendtime></sendtime>\n" +
                "\t\t\t\t<needreport>1</needreport>\n" +
                "\t\t\t</message>\n" +
                "\t\t\t<publicContent></publicContent>\n" +
                "\t\t</sendbody>\n" +
                "\t\t]]>\n" +
                "         </sendbody>\n" +
                "      </web:InsertDownSms>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        return soapXml;
    }

    /**
     * 下发短信成功后获得返回值中的mobile和msgId
     * @param s
     * @return
     */
    public Map reStringToMap(String s){
        s = s.replace("&lt;", "<" + "");
        s = s.replace("&gt;", ">" + "");
        s=s.replace("&quot; ?","\"");
        String[] split = s.split("<msgid>");
        String result = split[1].split("</msgid>")[0];
        String[] phoneAndCode = result.split(",");
        Map map=new HashMap();
        map.put("mobile",phoneAndCode[0]);
        map.put("msgId",phoneAndCode[1]);
        return map;
    }

    /**
     * 去除转译字符
     * @param s
     * @return
     */
    public String reString2(String s){
        s = s.replace("&lt;", "<" + "");
        s = s.replace("&gt;", ">" + "");
        s=s.replace("&quot; ?","\"");

        return s;
    }

    /**
     * 测试下发短信状态saopXML，修改msgId即可
     * @param username
     * @param password
     * @param msgId 下发短信时返回的值
     * @return
     */
    public String testMsgId(String username, String password, String msgId){
        String s="<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.chinagdn.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:getSpecialDownSmsResult soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <username xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">"+username+"</username>\n" +
                "         <password xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">"+password+"</password>\n" +
                "         <batch xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\"></batch>\n" +
                "         <msgid xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">"+msgId+"</msgid>\n" +
                "      </web:getSpecialDownSmsResult>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        return s;
    }
}
