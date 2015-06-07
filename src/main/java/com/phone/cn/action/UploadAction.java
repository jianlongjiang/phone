package com.phone.cn.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.phone.cn.conf.DataConfig;
import com.phone.cn.web.interceptor.AppUserLogin;

/**
 * @author zgdcool
 * @version 2014年10月11日 下午4:03:02
 * 
 *          _ooOoo_ o8888888o 88" . "88 (| -_- |) O\ = /O ____/`---'\____ .' \\|
 *          |// `. / \\||| : |||// \ / _||||| -:- |||||- \ | | \\\ - /// | | |
 *          \_| ''\---/'' | | \ .-\__ `-` ___/-. / ___`. .' /--.--\ `. . __ .""
 *          '< `.___\_<|>_/___.' >'"". | | : `- \`.;`\ _ /`;.`/ - ` : | | \ \
 *          `-. \_ __\ /__ _/ .-` / /
 *          ======`-.____`-.___\_____/___.-`____.-'====== `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 佛祖保佑 永无BUG 佛曰:
 *          写字楼里写字间，写字间里程序员； 程序人员写程序，又拿程序换酒钱。 酒醒只在网上坐，酒醉还来网下眠； 酒醉酒醒日复日，网上网下年复年。
 *          但愿老死电脑间，不愿鞠躬老板前； 奔驰宝马贵者趣，公交自行程序员。 别人笑我忒疯癫，我笑自己命太贱； 不见满街漂亮妹，哪个归得程序员？
 */
@Controller
public class UploadAction {
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyMMddHHmmss");
//	private  int yourMaxRequestSize  = 15;
	
	@RequestMapping(value="upload/images")
	public String imageExample(){
		return "admin/imageUpload";
	}

	
	@AppUserLogin
	@RequestMapping(value="uploadify/appUpload", method=RequestMethod.POST)
	@ResponseBody
	public  void  appImageUpload(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		imageUpload(request, response);
	}
	
	@RequestMapping(value="uploadify/upload", method=RequestMethod.POST)
	@ResponseBody
	public void imageUpload(HttpServletRequest request,HttpServletResponse response) throws IOException {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();   
        ServletContext servletContext = webApplicationContext.getServletContext(); 
 
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		File repository = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Set factory constraints
		// factory.setSizeThreshold(yourMaxMemorySize);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		@SuppressWarnings("static-access")
		boolean multipartContent = upload.isMultipartContent(request);

		// Set overall request size constraint
//		 upload.setSizeMax(yourMaxRequestSize);

		if (multipartContent) {
			// Parse the request
			String fileName = null;
			try {
				List<FileItem> items = upload.parseRequest(request);

				// Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();

					if (item.isFormField()) {
						processFormField(item);
					} else {
						fileName = processUploadedFile(item, servletContext.getRealPath("/html/admin/store"));
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.print(fileName);
		} else {
			response.getWriter().print("file upload format is wrong!");
		}
	}
	
	private String processUploadedFile(FileItem item, String storePath) throws IOException {
		// Process a file upload
		if (!item.isFormField()) {
//			String fieldName = item.getFieldName();
			String fileName = sf.format(new Date())+item.getName();
//			String contentType = item.getContentType();
//			boolean isInMemory = item.isInMemory();
//			long sizeInBytes = item.getSize();
			File uploadedFile = new File(storePath + "/" + fileName);
			if (!uploadedFile.exists()) {
				try {
					item.write(uploadedFile);
					Thumbnails.of(uploadedFile) 
					.scale(1)
//					.watermark(Positions.CENTER, ImageIO.read(new File(storePath + "/" + DataConfig.WATER_MARK)),0.5f)
					 .toFile(storePath + "/" + fileName);
					// 最小边 要> 100 才进行压缩
					BufferedImage bufferedImage = ImageIO.read(uploadedFile);
					int width = bufferedImage.getWidth();
					int height = bufferedImage.getHeight();
					int sizeLimit = 100;
					if(width > sizeLimit && height > sizeLimit){
						double scale = 1;
						if(width < height){
							scale = sizeLimit / width;
						}else {
							scale =  sizeLimit*1.0/height;
							Thumbnails.of(uploadedFile) 
							.scale(scale)
							 .toFile(storePath + "/100_100" + fileName);
						}
						
					}
					return fileName;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	@RequestMapping(value="uploadify/upload/build", method=RequestMethod.POST)
	@ResponseBody
	public void imageUploadBuild(HttpServletRequest request,HttpServletResponse response) throws IOException {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();   
        ServletContext servletContext = webApplicationContext.getServletContext(); 
 
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		File repository = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Set factory constraints
		// factory.setSizeThreshold(yourMaxMemorySize);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		@SuppressWarnings("static-access")
		boolean multipartContent = upload.isMultipartContent(request);

		// Set overall request size constraint
//		 upload.setSizeMax(yourMaxRequestSize);

		if (multipartContent) {
			// Parse the request
			String fileName = null;
			try {
				List<FileItem> items = upload.parseRequest(request);

				// Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();

					if (item.isFormField()) {
						processFormField(item);
					} else {
						fileName = processUploadedBuildFile(item, servletContext.getRealPath("/html/admin/store"));
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.print(fileName);
		} else {
			response.getWriter().print("file upload format is wrong!");
		}
	}
	
	
	@RequestMapping(value="uploadify/upload/pdf", method=RequestMethod.POST)
	@ResponseBody
	public void imageUploadPdf(HttpServletRequest request,HttpServletResponse response) throws IOException {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();   
        ServletContext servletContext = webApplicationContext.getServletContext(); 
 
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		File repository = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Set factory constraints
		// factory.setSizeThreshold(yourMaxMemorySize);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		@SuppressWarnings("static-access")
		boolean multipartContent = upload.isMultipartContent(request);

		// Set overall request size constraint
//		 upload.setSizeMax(yourMaxRequestSize);

		if (multipartContent) {
			// Parse the request
			String fileName = null;
			try {
				List<FileItem> items = upload.parseRequest(request);

				// Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();

					if (item.isFormField()) {
						processFormField(item);
					} else {
						fileName = processUploadedPdfFile(item, servletContext.getRealPath("/html/admin/store"));
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.print(fileName);
		} else {
			response.getWriter().print("file upload format is wrong!");
		}
	}
	
	private String processUploadedPdfFile(FileItem item, String storePath) throws IOException {
		// Process a file upload
		if (!item.isFormField()) {
//			String fieldName = item.getFieldName();
			String fileName = sf.format(new Date())+item.getName();
//			String contentType = item.getContentType();
//			boolean isInMemory = item.isInMemory();
//			long sizeInBytes = item.getSize();
			File uploadedFile = new File(storePath + "/" + fileName);
			if (!uploadedFile.exists()) {
				try {
					item.write(uploadedFile);
					return fileName;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	private String processUploadedBuildFile(FileItem item, String storePath) throws IOException {
		// Process a file upload
		if (!item.isFormField()) {
//			String fieldName = item.getFieldName();
			String fileName = sf.format(new Date())+item.getName();
//			String contentType = item.getContentType();
//			boolean isInMemory = item.isInMemory();
//			long sizeInBytes = item.getSize();
			File uploadedFile = new File(storePath + "/" + fileName);
			if (!uploadedFile.exists()) {
				try {
					item.write(uploadedFile);
					Thumbnails.of(uploadedFile) 
//			        .watermark(Positions.CENTER, ImageIO.read(new File(storePath+"/logo3.png")), 0.5f)
			        .scale(1.00f)
			        .outputQuality(0.8f) 
			        .toFile(storePath + "/" + fileName);
					
					Thumbnails.of(uploadedFile) 
					.size(311, 160)
//			        .watermark(Positions.CENTER, ImageIO.read(new File(storePath+"/logo3.png")), 0.5f) 
			        .outputQuality(0.8f) 
			        .toFile(storePath + "/311_160" + fileName);
					
					Thumbnails.of(uploadedFile) 
					.size(450, 230)
//			        .watermark(Positions.CENTER, ImageIO.read(new File(storePath+"/logo3.png")), 0.5f) 
			        .outputQuality(0.8f) 
			        .toFile(storePath + "/450_230" + fileName);
					return fileName;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private void processFormField(FileItem item) {
		// Process a regular form field
		if (item.isFormField()) {
//			String name = item.getFieldName();
//			String value = item.getString();
		}
	}
	
	
	
	@RequestMapping(value="uploadify/upload/waterMark", method=RequestMethod.POST)
	@ResponseBody
	public void imageUploadWaterMark(HttpServletRequest request,HttpServletResponse response) throws IOException {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();   
        ServletContext servletContext = webApplicationContext.getServletContext(); 
 
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		File repository = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Set factory constraints
		// factory.setSizeThreshold(yourMaxMemorySize);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		@SuppressWarnings("static-access")
		boolean multipartContent = upload.isMultipartContent(request);

		if (multipartContent) {
			// Parse the request
			String fileName = null;
			try {
				List<FileItem> items = upload.parseRequest(request);

				// Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();

					if (item.isFormField()) {
						processFormField(item);
					} else {
						fileName = processUploadedWaterMarkFile(item, servletContext.getRealPath("/html/admin/store"));
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.print(fileName);
		} else {
			response.getWriter().print("file upload format is wrong!");
		}
	}
	
	private String processUploadedWaterMarkFile(FileItem item, String storePath) throws IOException {
		// Process a file upload
		if (!item.isFormField()) {
//			String fieldName = item.getFieldName();
			String fileName = sf.format(new Date())+item.getName();
			File uploadedFile = new File(storePath + "/" + fileName);
			if (!uploadedFile.exists()) {
				try {
					item.write(uploadedFile);
					
					Thumbnails.of(uploadedFile) 
			     //   .scale(1.00f)  // 比例
			        .size(50, 50)  
			        .outputFormat("png")   
			        	.toFile(new File(storePath + "/" +DataConfig.WATER_MARK));   
  
					return DataConfig.WATER_MARK;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
