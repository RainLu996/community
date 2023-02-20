package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rainlu
 * @version 1.0.0
 * @Description Elasticsearch Service
 */
@Service
public class ElasticsearchService {

    //@Autowired
    //private DiscussPostRepository discussRepository;

    //@Autowired
    //private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    DiscussPostMapper discussPostMapper;

    /*public void saveDiscussPost(DiscussPost post) {
        discussRepository.save(post);
    }

    public void deleteDiscussPost(int id) {
        discussRepository.deleteById(id);
    }*/

    /** https://blog.csdn.net/qq_41767116/article/details/113734319
     * @Description: es 搜索功能
     * @param keyword 搜索的关键字
     * @param current 当前页，从0开始
     * @param limit 每页数据
     **/
    /*public List<DiscussPost> searchDiscussPost(String keyword, int current, int limit) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(keyword, "title", "content"))
                //.withSort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                //.withSort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                //.withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(current, limit))
                .withHighlightFields(
                        new HighlightBuilder.Field("title.key").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();

        // 执行查询
        SearchHits<DiscussPost> search = elasticsearchRestTemplate.search(searchQuery, DiscussPost.class);
        // 得到查询结果返回的内容
        List<SearchHit<DiscussPost>> searchHits = search.getSearchHits();
        // 设置一个需要返回的实体类集合
        List<DiscussPost> discussPosts = new ArrayList<>();
        // 遍历返回的内容进行处理
        for(SearchHit<DiscussPost> searchHit : searchHits){
            // 高亮的内容
            Map<String, List<String>> highLightFields = searchHit.getHighlightFields();
            // 将高亮的内容填充到content中（覆盖存放的数据内容）
            searchHit.getContent().setTitle(highLightFields.get("title") == null ? searchHit.getContent().getTitle() : highLightFields.get("title").get(0));
            searchHit.getContent().setTitle(highLightFields.get("content") == null ? searchHit.getContent().getContent() : highLightFields.get("content").get(0));
            // 放到实体类中
            discussPosts.add(searchHit.getContent());
        }

        return discussPosts;
    }*/

    public List<DiscussPost> searchDiscussPost(String keyword, int current, int limit) {
        return discussPostMapper.vagueSelectDiscussPosts(keyword, current, limit);
    }
}
