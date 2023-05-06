package com.xylink.test.convert;

import java.io.*;

/**
 * @ClassName ImageHexConvertUtil
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/8/18 19:53
 */
public class ImageHexConvertUtil {

    public static void main(String[] args) throws Exception {
//        imageToHex();
        hexToImage();
    }

    public static void imageToHex(){
        try {
            StringBuffer sb = new StringBuffer();
            FileInputStream fis = new FileInputStream("C:\\Users\\Vicky\\Pictures\\computer_lqh.jpg");
            BufferedInputStream bis = new BufferedInputStream(fis);
            java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = fis.read(buff)) != -1) {
                bos.write(buff, 0, len);
            }
            // 得到图片的字节数组
            byte[] result = bos.toByteArray();
            System.out.println("++++" + byte2HexStr(result));
            // 字节数组转成十六进制
            String str = byte2HexStr(result);
            PrintWriter pw = new PrintWriter(
                    new FileWriter("C:\\Users\\Vicky\\Pictures\\lqh_hex.txt"));
            pw.println(str);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现字节数组向十六进制的转换方法一
     * @param b
     * @return
     */
    public static String byte2HexStr(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1){
                hs = hs + "0" + stmp;
            }
            else{
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }


    public static void hexToImage() throws Exception {
        InputStream is = new FileInputStream("C:\\Users\\Vicky\\Pictures\\lqh_hex.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String str = null;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            System.out.println(str);
            sb.append(str);
        }
        saveToImgFile(sb.toString().toUpperCase(), "C:\\Users\\Vicky\\Pictures\\hexToImage.jpg");
    }

    public static void saveToImgFile(String src, String output) {
        if (src == null || src.length() == 0) {
            return;
        }
        try {
            FileOutputStream out = new FileOutputStream(new File(output));
            byte[] bytes = src.getBytes();
            for (int i = 0; i < bytes.length; i += 2) {
                out.write(charToInt(bytes[i]) * 16 + charToInt(bytes[i + 1]));
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int charToInt(byte ch) {
        int val = 0;
        if (ch >= 0x30 && ch <= 0x39) {
            val = ch - 0x30;
        } else if (ch >= 0x41 && ch <= 0x46) {
            val = ch - 0x41 + 10;
        }
        return val;
    }

}
