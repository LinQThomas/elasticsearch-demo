## springboot2.1集成elasticsearch中文分词、拼音搜索

####启动服务
```
docker run -d \
--restart=always \
--name elasticsearch \
-p 9200:9200 \
-p 9300:9300 \
-e ES_JAVA_OPTS='-Xms128m -Xmx256m' \
-v /data/elasticsearch/data:/usr/share/elasticsearch/data \
-v /data/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
-e "discovery.type=single-node" \
--net glimfire-bridge --ip 10.0.0.92 \
--privileged=true \
docker.elastic.co/elasticsearch/elasticsearch:6.5.1
```

#### elasticsearch.yml
```
http.host: 0.0.0.0 
cluster.name: elasticsearch_production 
node.name: elasticsearch_001_data 
node.master: true 
node.data: true 
bootstrap.system_call_filter: false 
transport.host: 0.0.0.0 
discovery.zen.minimum_master_nodes: 1 
http.cors.enabled: true 
http.cors.allow-origin: "*"
```

#### 容器内安装插件
```
./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.5.1/elasticsearch-analysis-ik-6.5.1.zip
```
```
./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-pinyin/releases/download/v6.5.1/elasticsearch-analysis-pinyin-6.5.1.zip
```

#### 创建索引
```
curl -XPUT http://193.112.9.113:9200/index_article
```

#### 创建索引以及中文分词(ik_max_word or ik_smart)、拼音分析器
```
curl -XPUT "http://193.112.9.113:9200/index_article/" -H "Content-Type: application/json" -d'{
    "index": {
        "analysis": {
            "analyzer": {
                "ik_pinyin_analyzer": {
                    "type": "custom",
                    "tokenizer": "ik_smart",
                    "filter": ["my_pinyin", "word_delimiter"]
                }
            },
            "filter": {
                "my_pinyin": {
                    "type": "pinyin",
                    "first_letter": "prefix",
                    "padding_char": " "
                }
            }
        }
    }
}'
```
#### 创建映射关系
    需要注意的是article对应dto的type，summary对应dto的索引字段，即分词字段；如果一开始索引的映射关系配置错误，必须手动删除该索引，否则会报错误
    如 mapper [] of different type, current_type [text], merged_type [keyword]
##### 此时可以查映射关系,跟报错一致
```
curl -XGET "http://193.112.9.113:9200/index_article/_mapping/article?pretty"
```
##### 删除索引重新创建并指定正确的mapping即可
###### 需要注意的是，java api暂时没找到删除索引的方法，所以暂时只能调es的api删
```
curl -XDELETE http://193.112.9.113:9200/index_article
```
```
curl -XPUT "http://193.112.9.113:9200/index_article/article/_mapping" -H "Content-Type: application/json" -d'{
    "article": {
        "properties": {
            "summary": {
                "type": "keyword",
                "fields": {
                    "pinyin": {
                        "type": "text",
                        "store": "false",
                        "term_vector": "with_positions_offsets",
                        "analyzer": "ik_pinyin_analyzer",
                        "boost": 10
                    }
                }
            }
        }
    }
}'
```

#### pinyin、中文分词查询数据
```
curl -XPOST "http:///193.112.9.113:9200/index_commodity/_search?pretty" -H "Content-Type: application/json" -d'
{
  "query": {
    "match": {
      "name.pinyin": "gua"
    }
  },
  "highlight": {
    "fields": {
      "name.pinyin": {}
    }
  }
}'
```








