package com.phone.cn.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.phone.cn.bean.ResultBean;
import com.phone.cn.utils.JsonMapper;


/**
 * baseAction 
 * @author admin
 *
 * @param <T>
 * @param <Integer>
 */
public  abstract class  BaseController {
//	BaseService<T, Integer> baseService;
	
	protected static String SUCCESS = "成功";
	protected static String FAIL = "失败";
	
	@Autowired  
	protected  HttpServletRequest request; 
	
	
	
	protected Map<String,Object> suc(String... msg){
		ResultBean resultBean =null;
		if(msg == null || msg.length == 0){
			resultBean = ResultBean.suc(null);
		}else {
			resultBean = ResultBean.suc(msg[0]);
		}
		return JsonMapper.beanToMap(resultBean);
	}
	
	
	protected Map<String,Object> fail(String... msg){
		ResultBean resultBean =null;
		if(msg == null || msg.length == 0){
			resultBean = ResultBean.fail(null);
		}else {
			resultBean = ResultBean.fail(msg[0]);
		}
		
		return JsonMapper.beanToMap(resultBean);
	}
	
	protected  void _setLastAccessUrl(String backUrl ){
		
		String lastAccessUrl = request.getRequestURL() + "?" + request.getQueryString();
		lastAccessUrl = backUrl == null? lastAccessUrl:backUrl;
		request.getSession().setAttribute("lastAccessUrl", lastAccessUrl);
	}
	
	
	protected  String _getLastAccessUrl( ){
		return (String) request.getSession().getAttribute("lastAccessUrl");
	}
	
	
	protected  String _removeLastAccessUrl( ){
		String lastAccessUrl = (String) request.getSession().getAttribute("lastAccessUrl");
		 request.getSession().removeAttribute("lastAccessUrl");
		 return lastAccessUrl;
	}
	
	
	
	protected  boolean  _needRedirect(){
		return _getLastAccessUrl()!= null;
	}
	
	
	protected  String  goRedirect(){
		return "redirect:"+_getLastAccessUrl();
	}
	
	
	
	/**
	 * 
	 * <p style=\"text-align: center; margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\"></span></p><p style=\"margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\">&nbsp;&nbsp;&nbsp;&nbsp;周董婚了，伦敦人民表示非常欢迎，纷纷在网上要求与周董&ldquo;偶遇&rdquo;，周董在伦敦的求婚地自然成了大家的关注点。今天小编就跟大家来八一八咱们周董都带周夫人去了哪些地方,嘻嘻。</span></p><p style=\"text-align: right; margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\"><img title=\"1418189892775020537.png\" alt=\"114265479.png\" src=\"/resources/admin/ueditor/jsp/upload/image/20141210/1418189892775020537.png\"/></span></p><p style=\"margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\">伦敦眼</span></p><p style=\"margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\">伦敦眼（英文名：The&nbsp;London&nbsp;Eye），全称英国航空伦敦眼（The&nbsp;British&nbsp;Airways&nbsp;London&nbsp;Eye）又称千禧之轮，坐落在伦敦泰晤士河畔，是伦敦的地标之一，也是伦敦最吸引游人的观光点之一。伦敦眼于1999年年底开幕，总高度135米（443英尺）。伦敦眼共有32个乘坐舱，因舱内外用钢化玻璃打造，所以设有空调系统。每个乘坐舱可载客约16名，回转速度约为每秒0.26米，即一圈需时30分钟。</span></p><p style=\"text-align: center; margin-top: 0px; margin-bottom: 0px;\"><img width=\"752\" height=\"455\" title=\"1418189835699077289.jpg\" style=\"width: 580px; height: 351px;\" alt=\"yephouse london eye.jpg\" src=\"/resources/admin/ueditor/jsp/upload/image/20141210/1418189835699077289.jpg\"/><span style=\"font-family:;\">&nbsp;</span></p><p style=\"margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\">英国Harrods百货</span></p><p style=\"margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\">（哈罗斯）百货公司位于Knightsbridge，在1849年开业时它只是一家卖杂货的商店，现在则是伦敦最有名的百货公司。Harrods的奢华和考究在于，里面所有的货品都精选自全球各地最佳的产品。货品种类繁多，式样齐全，以高价品著称。曾听人说，哪怕是提着墨绿底金色字样的Harrods袋子在街上走，背脊似乎挺得比平时都直些。这话听着虚荣了些，但明显可见Harrods在人们心目中的地位。</span></p><p style=\"text-align: center; margin-top: 0px; margin-bottom: 0px;\"><img title=\"1418189844205035727.jpg\" alt=\"yephouse harrods.jpg\" src=\"/resources/admin/ueditor/jsp/upload/image/20141210/1418189844205035727.jpg\"/><span style=\"font-family:;\">&nbsp;</span></p><p style=\"margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\">伦敦邦德街Bondstreet</span></p><p style=\"margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\">伦敦邦德街上都是最昂贵、最独特的奢侈品牌。那里是伦敦最高档的时尚购物区之一。邦德街[Bond&nbsp;Street]与牛津街垂直，一直通向皮卡迪利大街。这块地区被称作梅费尔[Mayfair]，自18世纪初期威斯敏斯特公爵格罗夫纳[Grosvenor]氏统治此地以来就一直是财富和权利的象征。这里遍布着伦敦最高档的流行女装店、时尚精品店、珠宝店、古董店和艺术画廊。在邦德街上能买到许多世界顶尖名牌，从阿玛尼[Armani]到范思哲[Versace]，从玛百莉[Mulberry]的手袋到丘奇[Church&rsquo;s]的的英国皮鞋，都可以在这里找到</span></p><p style=\"text-align: center; margin-top: 0px; margin-bottom: 0px;\"><img title=\"1418189855673072573.jpg\" alt=\"bondstreet yephouse.jpg\" src=\"/resources/admin/ueditor/jsp/upload/image/20141210/1418189855673072573.jpg\"/><span style=\"font-family:;\">&nbsp;</span></p><p style=\"margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\">中国城</span></p><p style=\"margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\">&ldquo;中国城&rdquo;地处伦敦的索霍地区，伦敦最繁华的牛津街和摄政街在此交错，英国人把这块方圆不足一平方公里的弹丸之地称为&ldquo;中国城&rdquo;，又曰&ldquo;唐人街&rdquo;。&ldquo;中国城&rdquo;既无高层建筑，也没有豪华的大公司，几乎所有建筑都比较矮，&nbsp;在&ldquo;中国城&rdquo;除少数是外国人经营的商店和酒吧间外，几乎是华人的天下。&nbsp;漫步&ldquo;中国城&rdquo;，一间间用汉字书写的商号目不暇接。&ldquo;中国城&rdquo;的餐馆林立，粗略计算至少不下100余家。&ldquo;中国城&rdquo;的华人，大部分来自香港、东南亚，也有少数是从上海、北京、江苏等地去的。每逢新春佳节，&ldquo;中国城&rdquo;张灯结彩，男女老幼穿上新&nbsp;装，互相道贺&ldquo;恭喜发财&rdquo;，男女青年舞龙耍狮，表演中国杂技，一片欢腾景象，&nbsp;吸引不少当地居民和外来观光者。</span></p><p style=\"text-align: center; margin-top: 0px; margin-bottom: 0px;\"><img title=\"1418189862232089049.jpg\" alt=\"yephouse China town.jpg\" src=\"/resources/admin/ueditor/jsp/upload/image/20141210/1418189862232089049.jpg\"/><span style=\"font-family:;\">&nbsp;</span></p><p style=\"margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\">海德公园</span></p><p style=\"margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\">海德公园（HYDE&nbsp;PARK）是伦敦最知名的公园。&nbsp;海德公园是英国最大的皇家公园。位于伦敦市中心的威斯敏斯特教堂&nbsp;(Westminster&nbsp;Abbey，即西敏寺)地区，占地360多英亩，原属威斯敏斯特教堂产业。十八世纪前这里是英王的狩鹿场。16世纪，英王亨利八世将之用作王室的公园。查理一世执政期间，海德公园曾向公众开放。1851年，维多利亚女王首次在这里举办伦敦国际博览会。1944年，美国总统罗斯福（Roosevelt）和英国首相丘吉尔（Churchill）曾在这里签订了海德公园协议（Hyde&nbsp;Park&nbsp;Agreement），这项美英之间关于二战期间核武器研发合作的协议。现在也是人们举行各种政治集会和其他群众活动的场所，有著名的&ldquo;演讲者之角&rdquo;(Speakers&rsquo;&nbsp;Corner)。</span></p><p style=\"text-align: center; margin-top: 0px; margin-bottom: 0px;\"><img width=\"800\" height=\"536\" title=\"1418189872747057175.jpg\" style=\"width: 573px; height: 398px;\" alt=\"yephouse hyde park.jpg\" src=\"/resources/admin/ueditor/jsp/upload/image/20141210/1418189872747057175.jpg\"/><span style=\"font-family:;\">&nbsp;</span></p><p style=\"margin-top: 0px; margin-bottom: 0px;\"><span style=\"font-family:;\">更多欧洲资讯，请关注www.yephouse.com</span></p><p></p>
	 * @param str
	 * @return
	 */
//	protected String doEditor(String str) {
//		if (StringUtils.isNotEmpty(str)) {
//			return StringEscapeUtils.unescapeHtml3(str).replace("\n", "")
//					.replace("\"", "\\" + "\"").replace("\t", "").replace("\r", "");
//		}
//		return str;
//	}
	
//	public void pre(Model model, T t){};
}
