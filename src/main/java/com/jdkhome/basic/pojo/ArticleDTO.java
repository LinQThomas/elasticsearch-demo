package com.jdkhome.basic.pojo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: w
 * CreatedTime: 2018/9/6 下午6:16
 * Description: indexName:索引名  type:索引类型
 * article_search_mapping : article对应dto的type，summary对应dto的索引字段，即分词字段
 */
@Setting(settingPath = "article_search_setting.json")
@Mapping(mappingPath = "article_search_mapping.json")
@Document(indexName = "index_article",type = "article",shards = 1,replicas = 0)
@Data
public class ArticleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String summary;

    private String content;

    public ArticleDTO( String summary, String content) {
        this.summary = summary;
        this.content = content;
    }

    public ArticleDTO() {
    }
}
