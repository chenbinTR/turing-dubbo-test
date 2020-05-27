package com.turing.ledi.dubbo;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by cb on 2017-03-09.
 */
public class Utils {
    /**
     *
     * @param amp
     *  待写入文件的map
     * @param separators
     *  map写入文件后key和value的分隔符
     * @param filePath
     *  文件路径
     */
    public static void writeMapToFile(Map<Object, Object> amp, String filePath, String separators){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 对字符串进行无效信息清洗
     *
     * @param str
     * @return
     */
    public static String clenaString(String str) {
        str = str.replace(" ", "").replace("\t", "").replace("\r", "").replace("\n", "").replace("　", "").trim();
        return str;
    }

    /**
     * 获取指定路径下的所有文件/文件夹名字
     *
     * @param path
     * @return
     */
    public static List<String> getPathFileName(String path) {
        File file = new File(path);
        List<String> list = new ArrayList<String>();
        if (!file.exists()) {
            return list;
        }

        File f[] = file.listFiles();
        for (int i = 0; i < f.length; i++) {
            File fs = f[i];
            if (fs.isDirectory()) {
            } else {
                list.add(fs.getAbsolutePath());
            }
        }
        return list;
    }

    /**
     * http post
     *
     * @param param
     * @param url
     * @return
     */
    public static String httpPost(String param, String url) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(50000);
			conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(param);

            out.flush();
            out.close();
            //
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "Utf-8"));
            String line = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * get请求
     * @return
     */
    public static String doGet(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());

                return strResult;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * httpget
     *
     * @param url
     * @return
     */
    public static String httpGet(String url) {
        try {
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl
                    .openConnection();

            connection.setRequestProperty("Content-type", "application/json; charset=utf-8");
            connection.addRequestProperty("Content-type", "application/json; charset=utf-8");
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(5000);
            connection.connect();

            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            return sb.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }

    }

    /**
     * 写文件，指定文件路径
     *
     * @param path
     * @param content
     */
    public static void writeToTxt(String path, String content) {
        File file = new File(path);
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file, true);
            writer = new BufferedWriter(fw);
            writer.write(content);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读文件(按行读取，保存到list中，读取文件全部内容)
     *
     * @param filePath 文件路径
     */
    public static List<String> readFileToList(String filePath) {
        List<String> list = new ArrayList<String>();
        File file = new File(filePath);
        BufferedReader reader = null;
        if (file.exists()) {
            try {
                reader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file), "UTF-8"));
                String temp = null;
                while (null != (temp = reader.readLine())) {
                    list.add(temp.trim());
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return list;
    }

    /**
     * 读文件(按行读取，保存到String中，读取文件全部内容)
     *
     * @param filePath 文件路径
     */
    public static String readFileToString(String filePath) {
        StringBuffer sb = new StringBuffer();
        File file = new File(filePath);
        BufferedReader reader = null;
        if (file.exists()) {
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String temp = null;
                while (null != (temp = reader.readLine())) {
                    sb.append(temp.trim());
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * @param urlPath     下载路径
     * @param downloadDir 下载存放目录
     * @return 返回下载文件
     */
    public static void downloadFile(String urlPath, String downloadDir) throws Exception {
        File file = null;
//        try {
        // 统一资源
        URL url = new URL(urlPath);
        // 连接类的父类，抽象类
        URLConnection urlConnection = url.openConnection();
        // http的连接类
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
        // 设定请求的方法，默认是GET
        httpURLConnection.setRequestMethod("POST");
        // 设置字符编码
        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
        httpURLConnection.connect();

        // 文件大小
        int fileLength = httpURLConnection.getContentLength();

        // 文件名
        String filePathUrl = httpURLConnection.getURL().getFile();
//            String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);
        String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf("/") + 1);
        URLConnection con = url.openConnection();

        BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());

        String path = downloadDir + File.separatorChar + fileFullName;
        file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        OutputStream out = new FileOutputStream(file);
        int size = 0;
        int len = 0;
        byte[] buf = new byte[1024];
        while ((size = bin.read(buf)) != -1) {
            len += size;
            out.write(buf, 0, size);
            // 打印下载百分比
            // System.out.println("下载了-------> " + len * 100 / fileLength +
            // "%\n");
        }
        bin.close();
        out.close();
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            return file;
//        }
    }

    /**
     * 读取文件夹中所有的文件，保存文件名到list
     *
     * @param path    文件路径
     * @param suffix  后缀名, 为空则表示所有文件
     * @param isdepth 是否遍历子目录
     * @return list
     */
    public static List<String> getListFiles(String path, String suffix, boolean isdepth) {
        List<String> lstFileNames = new ArrayList<String>();
        File file = new File(path);
        return listFile(lstFileNames, file, suffix, isdepth);
    }

    private static List<String> listFile(List<String> lstFileNames, File f, String suffix, boolean isdepth) {
        // 若是目录, 采用递归的方法遍历子目录
        if (f.isDirectory()) {
            File[] t = f.listFiles();

            for (int i = 0; i < t.length; i++) {
                if (isdepth || t[i].isFile()) {
                    listFile(lstFileNames, t[i], suffix, isdepth);
                }
            }
        } else {
            String filePath = f.getAbsolutePath();
            if (!suffix.equals("")) {
                int begIndex = filePath.lastIndexOf("."); // 最后一个.(即后缀名前面的.)的索引
                String tempsuffix = "";

                if (begIndex != -1) {
                    tempsuffix = filePath.substring(begIndex + 1, filePath.length());
                    if (tempsuffix.equals(suffix)) {
                        lstFileNames.add(filePath);
                    }
                }
            } else {
                lstFileNames.add(filePath);
            }
        }
        return lstFileNames;
    }
}
