package base;


import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.phone.cn.bean.SearchBean;
import com.phone.cn.entity.BaseEntity;
import com.phone.cn.mapper.BaseMapper;

/**
 * @author zgdcool
 * @version 2015年1月14日 下午2:25:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/spring/applicationContext.xml")
public abstract class BaseDaoTest<T extends BaseEntity<Integer>, TT  extends SearchBean> {
	protected Integer[] array = new Integer[10];
//	@Autowired
//	private ActivityInfoMapper mapper;
	
	protected abstract BaseMapper<T, Integer> getDao() ;
	
	@Test
	public void test(){
		System.out.println(array.length);
	}
	
//	@Test
//	public void test_addSelected() {
//		T bean = (T) CreateBeanFactory
//				.createBean(bean.getClass());
//		getDao().insertSelective(bean);
//
////		bean = new T();
//	}
	
	@Test
	public void test_querySelectedPage() {
		System.out.println("junit ");
		fail("Not yet implemented");
	}
}
