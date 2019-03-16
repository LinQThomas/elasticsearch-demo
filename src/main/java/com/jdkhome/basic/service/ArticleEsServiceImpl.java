package com.jdkhome.basic.service;

import com.jdkhome.basic.dao.ArticleEsRepository;
import com.jdkhome.basic.pojo.ArticleDTO;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: w
 * CreatedTime: 2018/9/7 下午2:36
 * Description:
 */
@Service
public class ArticleEsServiceImpl implements ArticleEsService {


    @Autowired
    private ArticleEsRepository articleEsRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    private BoolQueryBuilder getBuilder(String summary) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (!StringUtils.isEmpty(summary)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("summary.pinyin", summary));
        }

        return boolQueryBuilder;
    }

    @Override
    public List<ArticleDTO> getAllArticle(String summary) {

        BoolQueryBuilder builder = this.getBuilder(summary);

        Iterable<ArticleDTO> search = articleEsRepository.search(builder);

        List<ArticleDTO> list = new ArrayList<>();
        search.forEach(articleDTO -> list.add(articleDTO));
        return list;
    }

    @Override
    public void addArticle(String summary, String content) {

        elasticsearchTemplate.putMapping(ArticleDTO.class);

        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setSummary(summary);
        articleDTO.setContent(content);

        articleEsRepository.save(articleDTO);

    }

    @Override
    public void deleteArticle() {

        articleEsRepository.deleteAll();
    }
}
