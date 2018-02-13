package test.liu.cn;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Before;
import org.junit.Test;

import liul.cn.pojo.UserFS;

public class SolrjServiceTest {

	    
	    private HttpSolrServer httpSolrServer;
	 @Before
	    public void setUp() throws Exception {
	        // 在url中指定core名称：taotao
	        String url = "http://192.168.197.60:8983/solr/userinfo";
	        HttpSolrServer httpSolrServer = new HttpSolrServer(url); //定义solr的server
	        httpSolrServer.setParser(new XMLResponseParser()); // 设置响应解析器
	        httpSolrServer.setMaxRetries(1); // 设置重试次数，推荐设置为1
	        httpSolrServer.setConnectionTimeout(500); // 建立连接的最长时间
	        this.httpSolrServer = httpSolrServer;

	    }
	
	    public void testAdd() throws Exception {
	       UserFS user=new UserFS();
	       user.setAge(12L);
	       user.setId(1L);
	       user.setPassword("pass2");
	       user.setSex(1L);
	       user.setUserIntroduce("intrduce2");
	       user.setUserName("username2");
	       user.setUserNickname("nickname");
	       httpSolrServer.addBean(user);
	       httpSolrServer.commit();
	    }

	    
	    public void testDelete() throws Exception {
	        this.httpSolrServer.deleteById("5fc41dc4-dfcf-4018-90c4-a756d70dc645");
	        this.httpSolrServer.commit();
	    }

	    @Test
	    public void testSearch() throws Exception {
	    	SolrQuery solrQuery = new SolrQuery(); //构造搜索条件
	        solrQuery.setQuery("userName:username2"); //搜索关键词
	        // 设置分页 start=0就是从0开始，，rows=5当前返回5条记录。
	        solrQuery.setStart(0);
	        solrQuery.setRows(1);

	        //是否需要高亮
	       // boolean isHighlighting = !StringUtils.equals("*", keywords) && StringUtils.isNotEmpty(keywords);

	        /*if (isHighlighting) {
	            // 设置高亮
	            solrQuery.setHighlight(true); // 开启高亮组件
	            solrQuery.addHighlightField("title");// 高亮字段
	            solrQuery.setHighlightSimplePre("<em>");// 标记，高亮关键字前缀
	            solrQuery.setHighlightSimplePost("</em>");// 后缀
	        }*/

	        // 执行查询
	        QueryResponse queryResponse = this.httpSolrServer.query(solrQuery);
	        List<UserFS> users = queryResponse.getBeans(UserFS.class);
	        /*if (isHighlighting) {
	            // 将高亮的标题数据写回到数据对象中
	            Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
	            for (Map.Entry<String, Map<String, List<String>>> highlighting : map.entrySet()) {
	                for (Foo foo : users) {
	                    if (!highlighting.getKey().equals(foo.getId().toString())) {
	                        continue;
	                    }
	                    foo.setTitle(StringUtils.join(highlighting.getValue().get("title"), ""));
	                    break;
	                }
	            }
	        }*/
	    
	        for (UserFS user : users) {
	            System.out.println(user);
	        }
	    }
	    
	   
	    public void testDeleteByQuery() throws Exception{
	        httpSolrServer.deleteByQuery("*:*");
	        httpSolrServer.commit();
	    }
	  
}
