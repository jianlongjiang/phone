var accessManager=null;
function access(){
	function accessInvoker(posCode,refId,type) {
		if (!refId) {
			alert("源数据不存在");
			return;
		}
	
		var img = new Image();	
		var clickCountUrl = QFC.config.path.clickCountUrl;
		var cppfSiteUrl = QFC.config.path.cppfSiteUrl;
		img.src= clickCountUrl + "/stat.php?current=" + cppfSiteUrl +"/recommend/"+posCode+"/"+refId+"|"+type+"/";
	};	
	
	function accessEntityInvoker(posCode,refId,type) {
		accessInvoker(posCode,refId,type);
	};	
	 
	this.accessCompanyInfoInvoker=function(posCode,refId){
		window.setTimeout(function(){try{		
		accessEntityInvoker(posCode,refId,5);//公司类型
		}catch(e){}}
		,0);
	}
	
	this.accessProductInfoInvoker=function(posCode,refId){
		window.setTimeout(function(){
			try{
			accessEntityInvoker(posCode,refId,1);//产品类型
			}catch(e){}
		},0);		
	}
	
	this.accessShopInvoker=function(posCode,refId){
		window.setTimeout(function(){
			try{
				accessEntityInvoker(posCode,refId,6);//推荐店铺类型
			}catch(e){}
			},0);
		}
	}

	if(!accessManager){
		accessManager = new access();
	}
 
