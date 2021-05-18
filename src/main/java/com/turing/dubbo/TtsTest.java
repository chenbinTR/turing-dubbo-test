package com.turing.dubbo;

import com.alibaba.fastjson.JSON;
import com.turing.bmu.transcode.api.TranscodeService;
import com.turing.bmu.transcode.bean.TransFormat;
import com.turing.bmu.transcode.bean.TransRequest;
import com.turing.bmu.transcode.bean.TransResponse;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author ChenOT
 * @date 2019-09-18
 * @see
 * @since
 */
public class TtsTest {
    private static ClassPathXmlApplicationContext context;
    private static TranscodeService transcodeService;

    @Before
    public void initContext() {
        context = new ClassPathXmlApplicationContext(
                "classpath*:dubbo-application.xml");
        context.start();
        transcodeService = context.getBean("transcodeService", TranscodeService.class);
        System.out.println(transcodeService.hashCode());
        assert (null != transcodeService);
    }

    @Test
    public void testTranscode() {
        // the connection for speex between quality and frame
        // quality=8 frame=70
        // quality=5 frame=42
        String path = "E:\\";
        String fileName = "1";
        byte[] fileBytes = getFileBytes(path + fileName + ".amr");
//        TransRequest transRequest = new TransRequest();
//        transRequest.setDatas(fileBytes);
//        transRequest.setDataFormat(TransFormat.OPUS);
//        transRequest.setDestFormat(TransFormat.PCM_16K_16BIT);
//        transRequest.setChannel("commonToPcm");
//        transRequest.setGlobalId("1014659796625100");


        TransRequest request = new TransRequest();
        request.setDatas(fileBytes);
        request.setDataFormat(TransFormat.AMR_16K);
        request.setDestFormat(TransFormat.PCM_16K_16BIT);
        request.setChannel("commonToPcm");
        request.setGlobalId("123231232");
        TransResponse response = transcodeService.trans(request);

//        transRequest.setQuality(8);
//        transRequest.setFrame(70);
//        transRequest.setIndex(1);
        TransResponse transResponse = transcodeService.trans(request);
        System.err.println(JSON.toJSONString(transResponse));
        bytesToFile(path, transResponse.getDatas(), fileName + ".pcm");
        context.close();
    }

    @Test
    public void testTuring() {
        String TTS_URL = "http://tts.tuling123.com/tts/getText";
        String param = "{\"text_str\":\"我是张清二\", \"encode_fmt\":\"base64\", \"biz_code\":\"zw_16k_j3\", \"stream\":0}";
        String result = Utils.httpPost(param, TTS_URL);
        String[] results = result.split("}\\{");
        String pcmData = JSON.parseObject(results[0] + "}").getString("data");

        TransRequest transRequest = new TransRequest();
        transRequest.setDatas(Base64.decodeBase64(pcmData));
        transRequest.setDataFormat(TransFormat.PCM_16K_16BIT);
        transRequest.setDestFormat(TransFormat.WAV);
        transRequest.setChannel("TulingTtsEngine");
        transRequest.setGlobalId("123");

        TransResponse transResponse = transcodeService.trans(transRequest);
//        System.out.println(new String(transResponse.getDatas()));

        bytesToFile("Q:\\", transResponse.getDatas(), "jietong.wav");
        context.close();
    }

    private static byte[] getFileBytes(String file) {
        try {
            File f = new File(file);
            int length = (int) f.length();
            byte[] data = new byte[length];
            new FileInputStream(f).read(data);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * base64 写入文件
     *
     * @param destPath
     * @param fileName
     */
    private void bytesToFile(String destPath, byte[] bytes, String fileName) {
        File file;
        //创建文件目录
        String filePath = destPath;
        File dir = new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            file = new File(filePath + "/" + fileName);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * base64 写入文件
     *
     * @param destPath
     * @param base64
     * @param fileName
     */
    private void base64ToFile(String destPath, String base64, String fileName) {
        File file;
        //创建文件目录
        String filePath = destPath;
        File dir = new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.decodeBase64(base64);
            file = new File(filePath + "/" + fileName);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
