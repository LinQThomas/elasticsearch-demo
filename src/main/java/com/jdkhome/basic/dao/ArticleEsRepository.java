package com.jdkhome.basic.dao;

import com.jdkhome.basic.pojo.ArticleDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created with IntelliJ IDEA.
 * User: w
 * CreatedTime: 2018/9/6 下午6:17
 * Description:
 */
public interface ArticleEsRepository extends ElasticsearchRepository<ArticleDTO,String> {



}
