package util;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Base64;
public class codingutil {
    public static byte[] objtobytes(Object obj){
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj); //
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
//    public static byte[] decodeBase64(String input) throws Exception{
//        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
//        Method mainMethod= clazz.getMethod("decode", String.class);
//        mainMethod.setAccessible(true);
//        Object retObj=mainMethod.invoke(null, input);
//        return (byte[])retObj;
//    }
    public static Object stringtoObj(String str) throws Exception{
        byte[] bs = null;
        try {
//            bs = codingutil.decodeBase64(str);
            bs=Base64.getDecoder().decode(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object result = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bs);
            ObjectInputStream bitmap = new ObjectInputStream(bis);
            result = bitmap.readObject();  // error :local class incompatible: stream classdesc serialVersionUID = 177951238971833851
            bitmap.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
