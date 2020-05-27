//package com.turing.ledi.dubbo;
//
//import com.turing.similarity.comm.entity.RequestType;
//import com.turing.similarity.comm.entity.Source;
//import com.turing.similarity.dubbo.client.SimilarityClient;
//import org.apache.commons.lang3.tuple.Pair;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @author ChenOT
// * @date 2019-05-15
// * @see
// * @since
// */
//public class SimiTowTest {
//    private static Set<String> answers = new HashSet<>();
//
//    public static void main(String[] args) throws InterruptedException {
//        try {
//            Pair<Source, Double> pair = SimilarityClient.getSimilarity("slot_sick_name_Slot_position需要注意什么", "slot_sick_name_Slot_position需要注意什么", RequestType.CHAT);
//            System.out.println(pair.getValue().intValue());
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//
//    }
//}
