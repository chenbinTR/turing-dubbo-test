package com.turing.ledi.dubbo;

import com.alibaba.fastjson.JSONObject;
import com.turing.protocal.chatV2.*;
import com.turing.service.chat.ChatService;
import com.turing.utils.TuringNlpUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cb
 * @date 2018-07-19 15:16
 */
public class ChatTest {
    private static ClassPathXmlApplicationContext context;
    private static ChatService service;
    static{
        context = new ClassPathXmlApplicationContext(
                "classpath*:dubbo-application.xml");
        context.start();
        service = context.getBean("chatService", ChatService.class);
    }
    public static void main(String[] args) throws InterruptedException {
        List<String> qs = Utils.readFileToList("Q:\\logs\\1.txt");
        int count = 0;
        for(String q:qs){
            System.out.println(count++);
            try{
                String question = q.split("\t")[0];
                ChatResult chatResult = reqChat(question);
                Utils.writeToTxt("Q:\\logs\\prod-1.txt", q+"\t"+chatResult.getAnswer()+"\t"+chatResult.getAppId()+"\t"+chatResult.getParseType());
            }catch (Exception e){
                e.printStackTrace();
//                Utils.writeToTxt("Q:\\logs\\2.txt", q);
            }
//            System.out.println(q);
//            System.out.println(chatResult.getAnswer());
//            System.out.println(chatResult.getAppId()+"\t"+chatResult.getParseType());
//            System.out.println(chatResult.getAnswerEmotionId());
//            System.out.println(JSONObject.toJSONString(chatResult));
        }
//        for(int i=0; i<10; i++){
//            ChatResult chatResult = reqChat("你还知道");
//            System.out.println(chatResult.getAnswer());
//            System.out.println(chatResult.getAppId()+"\t"+chatResult.getParseType());
//            System.out.println(JSONObject.toJSONString(chatResult));
//        }
//        reqNewChat("你好");
    }
    private static ChatResult reqChat(String cmd){
        ChatRequest chatRequest = new ChatRequest();
        CmdInfo cmdInfo = new CmdInfo();
        cmdInfo.setQuestion(cmd);
        cmdInfo.setSourceTerms(TuringNlpUtils.getTerms(cmd));
        chatRequest.setCmdInfo(cmdInfo);

        chatRequest.setRequestId("1");

        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("tuling123@uzoo.cn");
        userInfo.setUserId(RandomUtils.nextInt(1,10000)+"");
        userInfo.setChannelType(0);
        chatRequest.setUserInfo(userInfo);

        RobotSetting robotSetting = new RobotSetting();
        Map<Integer, String> robotInfoConfigMap = new HashMap();
        // 开启机器人姓名功能
        robotInfoConfigMap.put(10001,"1");
        // 性别
        robotInfoConfigMap.put(10003, "1");
        // 星座
        robotInfoConfigMap.put(10009, "1");
        // 年龄
        robotInfoConfigMap.put(10005, "1");
        // 生日
        robotInfoConfigMap.put(10015, "1");
        // 母亲
        robotInfoConfigMap.put(10018, "1");
        robotSetting.setRobotInfoConifg(robotInfoConfigMap);
        chatRequest.setRobotSetting(robotSetting);

        ChatContextItem chatContextItem1 = new ChatContextItem();
        chatContextItem1.setLastQ("a");

        List<ChatContextItem> chatContextItemList = new ArrayList<>();
        chatContextItemList.add(chatContextItem1);
        chatRequest.setContextItemList(chatContextItemList);
        ChatResult chatResult = null;
        try{
            chatResult = service.requestChat(chatRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
        return chatResult;
    }

    private static NewChatResult reqNewChat(String cmd){
        NewChatRequest chatRequest = new NewChatRequest();
        chatRequest.setRequestId("123456");

        Info info = new Info();
        info.setContent(cmd);
        chatRequest.setQuestion(info);

        chatRequest.setSourceTerms(TuringNlpUtils.getTerms(cmd));

        chatRequest.setCity("北京");

        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("tuling123@uzoo.cn");
        userInfo.setUserId(RandomUtils.nextInt(1,10000)+"");
        userInfo.setChannelType(0);
        chatRequest.setUserInfo(userInfo);

        RobotSetting robotSetting = new RobotSetting();
        Map<Integer, String> robotInfoConfigMap = new HashMap();
        // 开启机器人姓名功能
        robotInfoConfigMap.put(10001,"1");
        // 性别
        robotInfoConfigMap.put(10003, "1");
        // 星座
        robotInfoConfigMap.put(10009, "1");
        // 年龄
        robotInfoConfigMap.put(10005, "1");
        // 生日
        robotInfoConfigMap.put(10015, "1");
        // 母亲
        robotInfoConfigMap.put(10018, "1");
        robotSetting.setRobotInfoConifg(robotInfoConfigMap);
        chatRequest.setRobotSetting(robotSetting);

        NewChatContext chatContextItem1 = new NewChatContext();
//        chatContextItem1.set

        List<NewChatContext> contextItemList = new ArrayList<>();
        contextItemList.add(chatContextItem1);

        chatRequest.setContextItemList(contextItemList);
        NewChatResult chatResult = null;
        try{
            chatResult = service.requestNewChat(chatRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
        return chatResult;
    }
}
