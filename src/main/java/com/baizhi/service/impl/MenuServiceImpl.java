package com.baizhi.service.impl;

import com.baizhi.dao.MenuDao;
import com.baizhi.entity.Menu;
import com.baizhi.respository.EmpRepository;
import com.baizhi.service.MenuService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private EmpRepository empRepository;
    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public void save(Menu menu) {
        menu.setCreateDate(new Date());
        menu.setId(UUID.randomUUID().toString());
        menuDao.save(menu);
        empRepository.save(menu);
    }

    @Override
    public void update(Menu menu) {
        menuDao.update(menu);
        empRepository.save(menu);
    }

    @Override
    public Menu findOne(String id) {
        return menuDao.findOne(id);
    }

    @Override
    public void delete(String id) {
        menuDao.delete(id);
        empRepository.deleteById(id);
    }

    @Override
    public List<Menu> querySearch(String message) {
        //2.?????????????????????
        HighlightBuilder.Field field = new HighlightBuilder.Field("*");
        field.preTags("<span style='color:red'>");  //??????
        field.postTags("</span>");  //??????
        field.requireFieldMatch(false); //??????????????????

        //????????????????????????
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("menus")
                .withTypes("menu")
                .withQuery(QueryBuilders.queryStringQuery(message).field("name").field("digest"))  //bool??????
                .withHighlightFields(field)  //1.????????????
                .build();
        //????????????
        AggregatedPage<Menu> menus = elasticsearchTemplate.queryForPage(searchQuery, Menu.class, new SearchResultMapper() {

            @Override  //???????????????     searchResponse:????????????????????????????????????
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                //???????????????????????????
                SearchHit[] hits = searchResponse.getHits().getHits();

                ArrayList<Menu> menuList = new ArrayList<>();

                //??????????????????
                for (SearchHit hit : hits) {

                    //???????????????????????????  {birthday=1621493552076, name=?????????, sign=???????????????, id=4, age=12}
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    //System.out.println("???????????????????????????: "+sourceAsMap);


                    String id = sourceAsMap.get("id").toString();
                    String name = sourceAsMap.get("name").toString();
                    String photo = sourceAsMap.get("photo").toString();
                    String createPerson = sourceAsMap.get("createPerson").toString();
                    String digest = sourceAsMap.get("digest").toString();
                    String step = sourceAsMap.get("step").toString();

                    //????????????String????????????long??????
                    long date = Long.parseLong(sourceAsMap.get("createDate").toString());
                    //????????????long????????????Date??????
                    Date createDate = new Date(date);

                    //?????????????????????????????????
                    Menu menu = new Menu(id,name,photo,createDate,createPerson,digest,step);

                    /** ?????????????????????
                     {
                     sign=[sign],
                     fragments[[???????????????<span style='color:red'>??????</span>]],
                     name=[name],
                     fragments[[???<span style='color:red'>??????</span>]]}
                     }
                     * */
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    //System.out.println("?????????????????????: "+highlightFields);

                    //???????????????name
                    if(highlightFields.containsKey("name")){
                        String names = highlightFields.get("name").fragments()[0].toString();
                        menu.setName(names); //????????????name????????????
                    }
                    //???????????????sign
                    if(highlightFields.containsKey("digest")){
                        String digests = highlightFields.get("digest").fragments()[0].toString();
                        menu.setDigest(digests);//????????????signs????????????
                    }

                    //?????????????????????
                    menuList.add(menu);
                }

                //?????? ??????
                return new AggregatedPageImpl<T>((List<T>) menuList);
            }
        });

        //?????????????????????????????????
        List<Menu> menuList = menus.getContent();

        return menuList;
    }

    @Override
    public Map<String, Object> deleteAll() {
        HashMap<String, Object> map = new HashMap<>();
        empRepository.deleteAll();
        map.put("status",true);
        return map;
    }

    @Override
    public Map<String, Object> createIndex() {
        HashMap<String, Object> map = new HashMap<>();
        List<Menu> all = menuDao.findAll();
        empRepository.saveAll(all);
        map.put("status",true);
        return map;
    }

   /* @Override
    public List<Menu> queryAll() {
        ArrayList<Menu> menuList = new ArrayList<>();
        Iterable<Menu> all = empRepository.findAll();
        for (Menu menu : all) {
            menuList.add(menu);
        }
        return menuList;
    }*/

    @Override
    public Menu getDetail(String id) {
        return empRepository.findById(id).get();
    }

    @Override
    public Integer getCount() {
        return (int)empRepository.count();
    }

    @Override
    public List<Menu> query(Integer currentPage,Integer size) {
        ArrayList<Menu> list = new ArrayList<>();
        Page<Menu> all = empRepository.findAll(PageRequest.of(currentPage, size));
        for (Menu menu : all) {
            list.add(menu);
        }
        return list;
    }
}
