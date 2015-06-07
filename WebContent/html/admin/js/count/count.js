//count
var statCfg={"site":'cp'}; 
(function() {
    var qfc = document.createElement('script'); qfc.type = 'text/javascript'; qfc.async = true;
    qfc.src = QFC.config.path.clickCountUrl +'/js/count.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(qfc, s);
  })();

//google
var _gaq = _gaq || []; 
_gaq.push(['_setAccount', 'UA-327418-12']); 
_gaq.push(['_setDomainName', '.qfc.cn']); 
_gaq.push(['_addOrganic', 'soso', 'w']); 
_gaq.push(['_addOrganic', 'sogou', 'query']); 
_gaq.push(['_addOrganic', 'youdao', 'q']); 
_gaq.push(['_addOrganic', 'baidu', 'word']); 
_gaq.push(['_addOrganic', 'baidu', 'q1']); 
_gaq.push(['_addOrganic', '360', 'q']); 
_gaq.push(['_addOrganic', 'm.baidu', 'word']); 
_gaq.push(['_addOrganic', 'image.baidu', 'word']); 
_gaq.push(['_addOrganic', 'ucweb', 'keyword']); 
_gaq.push(['_addOrganic', 'ucweb', 'word']); 
_gaq.push(['_addOrganic', '114so', 'kw']); 
_gaq.push(['_trackPageview']); 
(function() { 
var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true; 
ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js'; 
var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s); 
})(); 

//baidu
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Ffe61abc0d14bd3b9dbfeceff198ed6dc' type='text/javascript'%3E%3C/script%3E"));

