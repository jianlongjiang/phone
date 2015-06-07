package base;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.phone.cn.utils.PasswordUtils;

/*******************************************************
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         	佛祖保佑       永无BUG  永不修改
         	佛祖镇楼                            BUG辟易  
                              佛曰:  
                                  写字楼里写字间，写字间里程序员；  
                                  程序人员写程序，又拿程序换酒钱。  
                                  酒醒只在网上坐，酒醉还来网下眠；  
                                  酒醉酒醒日复日，网上网下年复年。  
                                  但愿老死电脑间，不愿鞠躬老板前；  
                                  奔驰宝马贵者趣，公交自行程序员。  
                                  别人笑我忒疯癫，我笑自己命太贱；  
                                  不见满街漂亮妹，哪个归得程序员？  
 ************************************************************/

/**
 * @author long.jiang
 * @version  创建时间：2014-10-20 上午9:17:14   
 */
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration(locations = { "file:WebRoot/WEB-INF/spring*.xml" })
@ContextConfiguration("classpath*:/spring/applicationContext.xml")
public abstract class BaseTestAction{
	
	protected Logger				log	= LoggerFactory.getLogger(this.getClass());
	@Autowired
	public WebApplicationContext	wac;
	
	public MockMvc					mockMvc;

	@Before
	public abstract void beforeMethod();
	
	
	public void init( Object controller )  {
		try {
			if(controller != null)
				mockMvc = standaloneSetup(controller).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void test_test(){
		System.out.println("test------------test");
//		SessionCookieConfig
	}
	
	public void test_example() throws Exception{
		String societyUnionId= "1";
		String pageSize = "2";
		String pageNo = "1";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/societyUnion/listHostPageSocietyForUnion")
				.param("societyUnionId", societyUnionId).param("pageSize", pageSize)
				.param("pageNo", pageNo))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(PasswordUtils.encrypt("123456"));
	}
}
