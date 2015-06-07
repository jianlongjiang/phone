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
package testStream;

import java.util.List;

/**
 * @author long.jiang
 * @version  创建时间：2015-2-13 下午2:03:01   
 */
public class Person{
	 private int id;  
	    private int sex;  
	    private int age;  
	    private String name;  
	    private List<Address> addList;  
	  
	    public Person(int id, int sex, int age, String name, List<Address> addList) {  
	        this.id = id;  
	        this.sex = sex;  
	        this.age = age;  
	        this.name = name;  
	        this.addList = addList;  
	    }  
	  
	    public int getId() {  
	        return id;  
	    }  
	  
	    public void setId(int id) {  
	        this.id = id;  
	    }  
	  
	    public String getName() {  
	        return name;  
	    }  
	  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	  
	    public int getSex() {  
	        return sex;  
	    }  
	  
	    public void setSex(int sex) {  
	        this.sex = sex;  
	    }  
	  
	    public int getAge() {  
	        return age;  
	    }  
	  
	    public void setAge(int age) {  
	        this.age = age;  
	    }  
	  
	    public List<Address> getAddList() {  
	        return addList;  
	    }  
	  
	    public void setAddList(List<Address> addList) {  
	        this.addList = addList;  
	    }  
}
