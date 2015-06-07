package base;


import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zgdcool
 * @version 2015年1月14日 下午2:25:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/spring/applicationContext.xml")
public class BaseTest {
	protected Integer[] array = new Integer[10];
//	@Autowired
//	private ActivityInfoMapper mapper;
	
	@Test
	public void test(){
		System.out.println(array.length);
	}
	
	@Test
	public void test_addSelected() {
		System.out.println("junit ");
		fail("Not yet implemented");
//		System.out.println(dao);
//		MessageInfo messageInfo = CreateBeanFactory.createBean(MessageInfo.class);
//		messageInfo.setId(null);
//		
//		dao.insertSelective(messageInfo);
//		assertNotNull(messageInfo.getId());
	}
	
	@Test
	public void test_querySelectedPage() {
		System.out.println("junit ");
		fail("Not yet implemented");
	}
}
