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
	        // ��url��ָ��core���ƣ�taotao
	        String url = "http://192.168.197.60:8983/solr/userinfo";
	        HttpSolrServer httpSolrServer = new HttpSolrServer(url); //����solr��server
	        httpSolrServer.setParser(new XMLResponseParser()); // ������Ӧ������
	        httpSolrServer.setMaxRetries(1); // �������Դ������Ƽ�����Ϊ1
	        httpSolrServer.setConnectionTimeout(500); // �������ӵ��ʱ��
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
	    	SolrQuery solrQuery = new SolrQuery(); //������������
	        solrQuery.setQuery("userName:username2"); //�����ؼ���
	        // ���÷�ҳ start=0���Ǵ�0��ʼ����rows=5��ǰ����5����¼��
	        solrQuery.setStart(0);
	        solrQuery.setRows(1);

	        //�Ƿ���Ҫ����
	       // boolean isHighlighting = !StringUtils.equals("*", keywords) && StringUtils.isNotEmpty(keywords);

	        /*if (isHighlighting) {
	            // ���ø���
	            solrQuery.setHighlight(true); // �����������
	            solrQuery.addHighlightField("title");// �����ֶ�
	            solrQuery.setHighlightSimplePre("<em>");// ��ǣ������ؼ���ǰ׺
	            solrQuery.setHighlightSimplePost("</em>");// ��׺
	        }*/

	        // ִ�в�ѯ
	        QueryResponse queryResponse = this.httpSolrServer.query(solrQuery);
	        List<UserFS> users = queryResponse.getBeans(UserFS.class);
	        /*if (isHighlighting) {
	            // �������ı�������д�ص����ݶ�����
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
