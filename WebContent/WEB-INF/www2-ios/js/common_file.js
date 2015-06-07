//window.onerror = function(msg, url, line) {
//	var idx = url.lastIndexOf("/");
//	if (idx > -1) {
//		url = url.substring(idx + 1);
//	}
//	alert("ERROR in " + url + " (line #" + line + "): " + msg);
//	return false;
//};

function downloadFun(imgs) {
	downloader.init({
		folder : appName,
		unzip : true
	});
	//	var  arrays = [];
	//	for(var index in imgs){
	//		var  img = imgs = {};
	//	}

	downloader
			.getMultipleFiles([
					{
						url : "http://yinglian.hzlianhai.com/resources/admin/ueditor/jsp/upload/image/20150508/1431016026002005756.jpg"
					},
					{
						url : "http://yinglian.hzlianhai.com/resources/admin/ueditor/jsp/upload/image/20150508/1431016264801095535.jpg"
					},
					{
						url : "http://yinglian.hzlianhai.com/resources/admin/ueditor/jsp/upload/image/20150508/1431017654083016547.png"
					} ]);

}

function webImg(img) {
	//	return  BASE_IMAGE+img;
	if (isBlack(fileSystem)) {
		return BASE_IMAGE + img;
	} else {
		localFile(img);
		return img;
	}
}

// ======================================
// 图片上传  
// ======================================
//  var  sucfun = (function(imgId){
//                             return  function(result) {
//                             var imageArrays = result.response.split(",");
//                             $("#"+imgId).attr("data-img",imageArrays[0]);
//  //                          console.log("========== 接收图片闭包"+ imgId +"=== 图片名称:"+imageArrays[0]);
//                         }
//  })(imgId) ;
// myupFile(results[i], sucfun); 
// myupFile("data:image/jpeg;base64," + imageData, suc)

function myupFile(imageURI, sucFun) {
	// 拍照成功后，需要上传文件
	var fileName = imageURI.substr(imageURI.lastIndexOf('/') + 1);
	var options = new FileUploadOptions();

	options.fileKey = "fieldName";// 图片域名！！！
	if (fileName.indexOf('?') == -1) {
		options.fileName = fileName;
	} else {
		options.fileName = fileName.substr(0, fileName.indexOf('?'));
	}

	options.mimeType = "image/jpeg";
	// options.mimeType = "multipart/form-data";
	options.chunkedMode = false;

	var params = {};
	// params.sid = user.sid;
	// params.fileType = "customer";
	options.params = params;

	var uri = encodeURI(BASE_URL + "/phoneGap");
	var ft = new FileTransfer();
	ft.upload(imageURI, uri, sucFun, onFileUploadFail, options);
}
function onFileUploadFail(error) {
	console.log("图片上传 失败   ===" + error);
}

/**********下载相片***********/
function downloadPic(sourceUrl, targetUrl) {
	var fileTransfer = new FileTransfer();
	var uri = encodeURI(sourceUrl);

	(function(targetUrl){
		return 
	})(targetUrl);
	fileTransfer.onprogress = function(progressEvent) {
		
		console.log("xxxxxx   ===>+ "+progressEvent+ "----"+progressEvent.loaded / progressEvent.total);
	    if (progressEvent.lengthComputable) {
	//      loadingStatus.setPercentage(progressEvent.loaded / progressEvent.total);
	//      alert("图片下载完成 ");
	    		console.log("下载成功")
	    } else {
	    	console.log("下载失败")
//	    	alert("图片下载失败 ");
	 //     loadingStatus.increment();
	    }
	};


	fileTransfer.download(uri, targetUrl, function(fileEntry) {
		//            var smallImage = document.getElementById(parseImageName(fileEntry.fullPath));
		//            smallImage.style.display = 'block';
		//            smallImage.src = _fileSystem.root.nativeURL+fileEntry.fullPath;   
		//            //alert(_fileSystem.root.nativeURL);       
		var imgs = document
				.getElementsByClassName(parseImageName(fileEntry.fullPath));
		for ( var index in imgs) {
			imgs[index].src = _fileSystem.root.nativeURL + fileEntry.fullPath;
		}
		 $("img[title='"+parseImageName(fileEntry.fullPath)+"']").attr("src",_fileSystem.root.nativeURL + fileEntry.fullPath);
	}, function(error) {
		console.log("下载网络图片出现错误");
	});
}

/**
 *	文件操作 初始化
 */
var fileSystem;
var imageWaitArrays = [];
function fileInit() {
	try {
		if (isBlack(LocalFileSystem))
			return;
		window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, function(fs) {
			_fileSystem = fileSystem = fs;
			fileSystem.root.getDirectory(appName, {
				create : true
			}, function(fileEntry) {
			}, fileError);
			
			fileSystem.root.getDirectory(appName + "/download", {
				create : true
			}, function(fileEntry) {
			}, fileError);

			//  本地js 初始化好了, 加载图片信息()
			for ( var index in imageWaitArrays) {
				localFile(imageWaitArrays[index]);
			}
		}, fileError);
	} catch (e) {
	}
}

function parseImageName(str) {
	var index = str.lastIndexOf("/");
	return str.substring(index + 1);
}

/**
 *
 * @param url
 */
function localFileByUrl(url) {
	//TODO 待完善
	var name = url.substr(url.lastIndexOf('/') + 1);

	/** native js 文件js 还未初始化    **/
	if (!fileSystem) {
		imageWaitArrays.push(name);
		return;
	}
	var _localFile = appName + "/download/" + name;
	var _url = BASE_IMAGE + name;
	//查找文件  
	//  错误方法
	var errfun = (function(_localFile, _url) {
		return function(err) {
			//否则就到网络下载图片!  
			fileSystem.root.getFile(_localFile, {
				create : true
			}, function(fileEntry) {
				var targetURL = fileEntry.toURL();
				downloadPic(_url, targetURL);
			}, function() {
				// alert('下载图片出错');
				console.log("下载图片出错");
			});
		}
	})(_localFile, _url);

	fileSystem.root.getFile(_localFile, null, function(fileEntry) {
		//文件存在就直接显示  
		//            alert("//文件存在就直接显示  --  common_file.js");

		//            var smallImage = document.getElementById(parseImageName(fileEntry.fullPath));
		//            smallImage.style.display = 'block';
		//            smallImage.src = _fileSystem.root.nativeURL+fileEntry.fullPath;
		var imgs = document
				.getElementsByClassName(parseImageName(fileEntry.fullPath));
		for ( var index in imgs) {
			imgs[index].src = _fileSystem.root.nativeURL + fileEntry.fullPath;
			;
		}
	}, errfun);
}

// ======================================
// 图片本地缓存  name      注意在 img  + class ="name"
// ======================================
//localFile("150504000624pcgames0930ophead30.bmp");
function localFile(nameUrl) {

	//return ;
	/** native js 文件js 还未初始化    **/
	if (!fileSystem) {
		imageWaitArrays.push(nameUrl);
		return;
	}
	
	var _localFile ;
	var _url  ;
	var name = "";
	if(nameUrl.indexOf("/") > -1){
		name = parseImageName(nameUrl);
		_localFile = appName + "/download/" + name;
		 _url = nameUrl;
	}else { 
		name = nameUrl;
		_localFile = appName + "/download/" + name;
		 _url = BASE_IMAGE + name;
	}
	
//	var _localFile = appName + "/download/" + name;
//	var _url = BASE_IMAGE + name;
	//查找文件  
	//  错误方法
	var errfun = (function(_localFile, _url) {
		return function(err) {
			//否则就到网络下载图片!  
			fileSystem.root.getFile(_localFile, {
				create : true
			}, function(fileEntry) {
				var targetURL = fileEntry.toURL();
				downloadPic(_url, targetURL);
			}, function() {
				// alert('下载图片出错');
				console.log("下载图片出错");
			});
		}
	})(_localFile, _url);

	fileSystem.root.getFile(_localFile, null, function(fileEntry) {
		//文件存在就直接显示  
		//            alert("//文件存在就直接显示  --  common_file.js");
		//            var smallImage = document.getElementById(parseImageName(fileEntry.fullPath));
		//            smallImage.style.display = 'block';
		//            smallImage.src = _fileSystem.root.nativeURL+fileEntry.fullPath;
		var imgs = document
				.getElementsByClassName(parseImageName(fileEntry.fullPath));
		for ( var index in imgs) {
			imgs[index].src = _fileSystem.root.nativeURL + fileEntry.fullPath;
		}
		
		 $("img[title='"+parseImageName(fileEntry.fullPath)+"']").attr("src",_fileSystem.root.nativeURL + fileEntry.fullPath);
//		for ( var index in editorImgs) {
//			imgs[index].src = _fileSystem.root.nativeURL + fileEntry.fullPath;
//		}
		
		
	}, errfun);
}

/**
 * 清除图片
 */
function clearLocalImgs() {

	//	fileSystem.root.getFile("test.txt", {create:true}, function(f) {
	//        f.remove(function() {
	//            logit("File removed<p/>");
	//        });
	//    }, onError);

	fileSystem.root.getDirectory(appName + "/download", {
		create : true
	}, function(fileEntry) {
		fileEntry.removeRecursively(function() {
			console.log("success!");
			fileSystem.root.getDirectory(appName + "/download", {
				create : true
			}, function(fileEntry) {
			}, fileError);
		}, function() {
			console.log("fail!");
		})
	}, fileError);

	

}

function fileError(err) {
	var msg = 'An error occured: ';
	console.log("加载文件系统出现错误");
	switch (err.code) {
	case FileError.NOT_FOUND_ERR:
		msg += 'File or directory not found';
		break;
	case FileError.NOT_READABLE_ERR:
		msg += 'File or directory not readable';
		break;
	case FileError.PATH_EXISTS_ERR:
		msg += 'File or directory already exists';
		break;
	case FileError.TYPE_MISMATCH_ERR:
		msg += 'Invalid filetype';
		break;
	default:
		msg += 'Unknown Error';
		break;
	}
	;
	console.log(msg + err);
	console.log("创建目录失败");
}