package com.turing.ledi.dubbo;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ChenOT
 * @date 2020-05-25
 * @see
 * @since
 */
public class TestEs {
    @Test
    public void testInsert() {
        String indexName = "nlp_book_search";
        String typeName = "doc";
        Map<String, Object> data = new HashMap<>();
        data.put("name", "123");
        insertEs(indexName, typeName, data);
    }

    /**
     * 通配符查询
     */
    @Test
    public void searcWildcardQuery() {
        SearchResponse searchResponse = getClient().prepareSearch("nlp_book_search")
                .setTypes("doc")
                .setQuery(QueryBuilders.wildcardQuery("name", "123"))
                .get();
        SearchHits hits = searchResponse.getHits();
        // 获取命中次数，查询结果有多少对象
        System.out.println("查询结果有：" + hits.getTotalHits() + "条");
        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()) {
            SearchHit next = iterator.next();
            System.out.println(next.getSourceAsString());
        }
    }

    private TransportClient getClient() {
        TransportClient client = null;
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "market-media-management").build();
        try {
            client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("39.107.91.143"), 9600));
        } catch (Exception e) {
            System.out.println("clusterName or ip or port is Error!");
            e.printStackTrace();
        }
        return client;
    }

    private int insertEs(String indexName, String typeName, Map<String, Object> source) {
        TransportClient client = getClient();
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        bulkRequest.add(client.prepareIndex(indexName, typeName).setSource(source));
        BulkResponse response = bulkRequest.get();
        if (response.hasFailures()) {
            System.out.println("数据插入失败" + source);
            return 0;
        }
        return 1;
    }


}
