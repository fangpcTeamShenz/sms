package com.pj.app.utils;
import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;


public class DesUtils {
  
  private static String strDefaultKey = "smsota";

  private Cipher encryptCipher = null;


  private Cipher decryptCipher = null;


  public static String byteArr2HexStr(byte[] arrB) throws Exception {
    int iLen = arrB.length;
  
    StringBuffer sb = new StringBuffer(iLen * 2);
    for (int i = 0; i < iLen; i++) {
      int intTmp = arrB[i];
      
      while (intTmp < 0) {
        intTmp = intTmp + 256;
      }
  
      if (intTmp < 16) {
        sb.append("0");
      }
      sb.append(Integer.toString(intTmp, 16));
    }
   
    return sb.toString();
  }

  
  public static byte[] hexStr2ByteArr(String strIn) throws Exception {
    byte[] arrB = strIn.getBytes();
    int iLen = arrB.length;

   
    byte[] arrOut = new byte[iLen / 2];
    for (int i = 0; i < iLen; i = i + 2) {
      String strTmp = new String(arrB, i, 2);
      arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
    }
    return arrOut;
  }


  public DesUtils() throws Exception {
    this(strDefaultKey);
  }

 
  public DesUtils(String strKey) throws Exception {
//    Security.addProvider(new com.sun.crypto.provider.SunJCE());
    Key key = getKey(strKey.getBytes());

    encryptCipher = Cipher.getInstance("DES");
    encryptCipher.init(Cipher.ENCRYPT_MODE, key);

    decryptCipher = Cipher.getInstance("DES");
    decryptCipher.init(Cipher.DECRYPT_MODE, key);
  }

  
  public byte[] encrypt(byte[] arrB) throws Exception {
    return encryptCipher.doFinal(arrB);
  }

 
  public String encrypt(String strIn) throws Exception {
    return byteArr2HexStr(encrypt(strIn.getBytes()));
  }
  
 
  public byte[] decrypt(byte[] arrB) throws Exception {
    return decryptCipher.doFinal(arrB);
  }


  public String decrypt(String strIn) throws Exception {
    return new String(decrypt(hexStr2ByteArr(strIn)));
  }


  private Key getKey(byte[] arrBTmp) throws Exception {
    
    byte[] arrB = new byte[8];

     
    for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
      arrB[i] = arrBTmp[i];
    }


    Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

    return key;
  }
  
  protected String deEncrypt(String miwen) throws Exception{
	   	 DesUtils des = new DesUtils("smsota");
	        return des.decrypt(miwen) ;
	   }
  
  
  /**
corpID=jiuyifu_test&corpName=91pay&orderID=140710-20140710-145144-470
&cardInfo=ZGYwZTI5ZDJhMmI2ODNjNjAxNWRjMjk5ODg1YWQ3ZTIxODhjZmY0NDJmZWRhNWNiZTIzZmQxM2Y5MTFkOGFhYTAzNGE2MTM2YjI2YmM3Mjk=
&notifyURL=http://192.168.1.112:8080/ShuoRuanPay/backAction_back.action
&userFlag=13580362960&userName=Gilbert&userIP=192.168.1.112&signMD5=1a72e199ba568d02d1618c004f063e40&"remark=so good"
signMD5=LOWERCASE(MD5(corpID+orderID+cardInfo+userFlag+userName+userIP+MD5KEY))
cardInfo=BASE64(DES(���#���к�#���룬DESkey))
"50#09718090922371234#091486606360775678" *
    public static void main(String[] args) {
    try {
      String cash = "50";
     // System.out.println("���:"+cash);
      String mulu = "09718090922371234";
      //System.out.println("���к�:"+mulu);      
      String cardpsw = "091486606360775678";
      //System.out.println("����:"+cardpsw); 
      String corpID = "jiuyifu_test";
      String orderID = "140710-20140710-145144-470";
      String userFlag = "13580362960";
      String userName = "Gilbert";
      String userIP = "192.168.1.112";
      String md5key = "E4F6091A1B69331A";
      String test = cash+"#"+mulu+"#"+cardpsw;
      String deskey = "OTgyOTFkMWI0Yjhl";
      DesUtils des = new DesUtils(deskey);//�Զ�����Կ   
      String desCardInfo = des.encrypt(test);
      System.out.println("cardInfo=BASE64(DES(���#���к�#���룬DESkey))");
      System.out.println("cardInfo=BASE64(DES("+test+","+deskey+"))");
      System.out.println("DES("+test+","+deskey+") --> "+desCardInfo);
      String cardInfo = Base64Util.encode(desCardInfo.getBytes());
      System.out.println("cardInfo --> " + cardInfo+"\n");
      
      String prepareMd5 = corpID+""+orderID+""+cardInfo+""+userFlag+""+userName+userIP+md5key;
      System.out.println("signMD5=LOWERCASE(MD5(corpID+orderID+cardInfo+userFlag+userName+userIP+MD5KEY))");
      System.out.println("signMD5=LOWERCASE(MD5("+prepareMd5+"))");
      System.out.println("signMD5 --> " + StringUtil.md5String(prepareMd5));
   //   System.out.println("���ܺ���ַ�" + des.decrypt(des.encrypt(test)));
      
/*	  String decode = "45B2453D5F6A3917C05A3096D35225DC365612BCA24A64746A968773B828E91AB300E6D2BC16D9C3CA9E330472298F01749F47249F169CAE99605AA7F4341A57BAB19C21A617C954129775FE7CA9D6B2AF0E06BBCB3F8AB2F2FCEDBC5AA076E07A7BB6F65B97D55D081D63E266A055D646DCA990889C09FDFA72E7EAA4FCE102";
	  String prikey = new StringUtil().MD5UtilStr("50817").toLowerCase();
	  DesUtils des = new DesUtils(prikey);//�Զ�����Կ   
	  String reStr = des.decrypt(decode);
	  System.out.println(reStr);
      String decode = "D9AB0109D5ADDD829349A04C9CD0F04E1A06295DF57263BB9F65F83A27136BD20D559C76AB5362846EA0AF5F80533B2F82A2530D2F93595B56C3C1133AC1B11CDD4320DEA9EBB81A9C067146B5220216E8E6F3A27C91290A193F5B9FA5CA44F1707FDD4A76C5B72F3ACCB93C99A8928D800E7EA49ABA5F66A79A960797DB927F";
	  String prikey = new StringUtil().MD5UtilStr("50827").toLowerCase();
	  DesUtils des = new DesUtils(prikey);//�Զ�����Կ   
	  String reStr = des.decrypt(decode);
	  System.out.println(reStr);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }     
  */ 
 
}
