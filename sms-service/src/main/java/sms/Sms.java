package sms;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import util.MySSLProtocolSocketFactory;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@Slf4j
public class Sms {
    private static final String IP = "10.190.49.11";
    private static XmlMapper xmlMapper = new XmlMapper();


    /**
     *  向单个用户发送短信
     * @param phoneNum
     * @param msgContent
     * @return
     */
    public static boolean sendMsgSingle(String phoneNum, String msgContent) {
        List<SmsSearchBean> list = new ArrayList<>();
        SmsSearchBean smsBean = new SmsSearchBean();
        smsBean.setPhone(phoneNum);
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        DateFormat format = new SimpleDateFormat(dateFormat);
        Calendar instance = Calendar.getInstance();
        Date time_now = instance.getTime();
        String formated_time = format.format(time_now);
        smsBean.setContent(msgContent+"\n发送时间:" + formated_time);
        list.add(smsBean);
        String response = sendmsg(list);
        return resolveResponse(response);

    }

    /**
     * 短信群发功能
     * @param phoneNums
     * @param msgContent
     * @return
     */
    public static boolean sendMsgGroup(List<String> phoneNums, String msgContent) {
        List<SmsSearchBean> list = new ArrayList<SmsSearchBean>();
        for (String phoneNum : phoneNums) {
            SmsSearchBean smsBean = new SmsSearchBean();
            smsBean.setPhone(phoneNum);
            smsBean.setContent(msgContent);
            list.add(smsBean);
        }
        String response = sendmsg(list);

        return resolveResponse(response);
    }

    private static boolean resolveResponse(String response){
        try {
            String realRes = xmlMapper.readTree(response).at(JsonPointer.compile("/Body/InsertDownSmsResponse/InsertDownSmsReturn/")).textValue();
            int code = xmlMapper.readTree(realRes).at(JsonPointer.compile("/head/code")).intValue();
            String messageId = xmlMapper.readTree(realRes).at(JsonPointer.compile("/body/msgid")).textValue();
            log.info("短信发送结果，code：{}， msgid：{}", code, messageId);
            return 0 == code;
        } catch (Exception e) {
            log.error("解析返回值出错", e);
            return false;
        }
//<code>0</code>  code=0 表示发送成功， <msgid>18665998104,201905201532063000</msgid>  msgid中包含号码和时间戳
    }

    public static String sendmsg(List<SmsSearchBean> list) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            String ss = getSoapRequest(list);//获取XML内容
            HttpClient client = new HttpClient();
            Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
            Protocol.registerProtocol("https", myhttps);
            PostMethod post = new PostMethod("http://"+IP +"/services/Sms");
            post.setRequestEntity(new ByteArrayRequestEntity(ss.getBytes("utf-8")));
            post.setRequestHeader("SOAPAction", "InsertDownSms");
            post.getParams().setContentCharset("utf-8");
            client.executeMethod(post);
            map.put("ss", ss);
            String respStr = post.getResponseBodyAsString();
//		logWriter.error("respStr:------------"+respStr);
            return respStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }


    public static String getSoapRequest(List<SmsSearchBean> list) {
        String msgsend = "";
        for (int i = 0; i < list.size(); i++) {
            msgsend = msgsend
                    + "	<message>"
                    + "	<orgaddr></orgaddr>"
                    + "	<mobile>" + list.get(i).getPhone() + "</mobile>"
                    + "	<content>" + list.get(i).getContent() + "</content>"
                    + "	<sendtime></sendtime>"
                    + "	<needreport>1</needreport>"
                    + "	</message>";
        }
        String msg = "<![CDATA["
                + "<sendbody>"
                + msgsend
                + "	<publicContent></publicContent>"
                + "</sendbody>"
                + "]]>";
        String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.chinagdn.com\">"
                + "<soapenv:Header/>"
                + " <soapenv:Body>"
                + " <web:InsertDownSms soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                + "<username xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">" + encrypt("1604", "chinagdn") + "</username>"
                + "<password xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">" + encrypt("!q2w3e4R", "chinagdn") + "</password>"
                + "<batch xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\"></batch>"
                + "<sendbody xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">" + msg + "</sendbody>"
                + "</web:InsertDownSms>"
                + " </soapenv:Body>"
                + "</soapenv:Envelope>";
        return body;
    }


    /**
     * 加密算法
     */
    public static String encrypt(String txt, String key) {
        String encrypt_key = "0f9cfb7a9acced8a4167ea8006ccd098";
        int ctr = 0;
        String tmp = "";
        int i;
        for (i = 0; i < txt.length(); i++) {
            ctr = (ctr == encrypt_key.length()) ? 0 : ctr;
            tmp = tmp + encrypt_key.charAt(ctr)
                    + (char) (txt.charAt(i) ^ encrypt_key.charAt(ctr));
            ctr++;
        }
        return base64_encode(key(tmp, key));
    }

    /**
     * 解密算法
     */
    public String decrypt(String cipherText, String key) {
        // base64解码
        cipherText = base64_decode(cipherText);
        cipherText = key(cipherText, key);
        String tmp = "";
        for (int i = 0; i < cipherText.length(); i++) {
            int c = cipherText.charAt(i) ^ cipherText.charAt(i + 1);
            String x = "" + (char) c;

            tmp += x;
            i++;
        }
        return tmp;
    }

    public static String key(String txt, String encrypt_key) {
        encrypt_key = strMD5(encrypt_key);
        int ctr = 0;
        String tmp = "";
        for (int i = 0; i < txt.length(); i++) {
            ctr = (ctr == encrypt_key.length()) ? 0 : ctr;
            int c = txt.charAt(i) ^ encrypt_key.charAt(ctr);
            String x = "" + (char) c;
            tmp = tmp + x;
            ctr++;
        }
        return tmp;
    }

    public static String base64_encode(String str) {
        return new sun.misc.BASE64Encoder().encode(str.getBytes());
    }

    public String base64_decode(String str) {
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        if (str == null)
            return null;
        try {
            return new String(decoder.decodeBuffer(str));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final String strMD5(String s) {
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
     * map转xml（不支持嵌套）
     *
     * @param map
     * @return
     */
    public static String transXml(HashMap<String, String> map) {
        StringBuffer xml = new StringBuffer();
        Iterator<Entry<String, String>> iter = map.entrySet().iterator();
        xml.append("<xml>");
        while (iter.hasNext()) {
            Entry<String, String> entry = iter.next();
            xml.append("<" + entry.getKey() + ">");
            xml.append(entry.getValue());
            xml.append("</" + entry.getKey() + ">");
        }
        xml.append("</xml>");
        return xml.toString();
    }

    public static void main(String[] args){
        ArrayList<String> users = new ArrayList<>();
        String phone1 = "18374846533";
//        String phone3 = "17722650516";
//        String phone4 = "15989585398";
//        String phone5 = "18374846533";
        users.add(phone1);
//        users.add(phone3);
//        users.add(phone4);
//        users.add(phone5);
        String contents = "智慧福田预警监测平台测试短信.....";
//        int i = Sms.sendMsgSingle(phone1,contents);
        boolean b = Sms.sendMsgGroup(users, contents);
        if (b){
            System.out.println("发送完成");
        }

    }
}
