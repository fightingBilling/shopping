package com.kong.shop.action.product;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.kong.shop.model.product.Brand;
import com.kong.shop.service.product.IBrandService;
import com.kong.shop.utils.SiteUrl;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class BrandManageAction extends ActionSupport {
	private static final long serialVersionUID = -6956692923392743452L;
	private IBrandService brandService;
	private Brand brand;
	private File logofile;
	private String extension;
	private String urladdress = SiteUrl.read("control.brand.list");
	
	public String getUrladdress() {
		return urladdress;
	}

	public void setUrladdress(String urladdress) {
		this.urladdress = urladdress;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public File getLogofile() {
		return logofile;
	}

	public void setLogofile(File logofile) {
		this.logofile = logofile;
	}

	public IBrandService getBrandService() {
		return brandService;
	}

	@Resource
	public void setBrandService(IBrandService brandService) {
		this.brandService = brandService;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String addUI() {
		return "addUI";
	}

	public String add() throws IOException {
		// If name not define, go to global message page
		if (brand.getName() == null || "".equals(brand.getName())) {
			addActionError("Please give one name for brand which you defined.");
			return "message";
		}

		// Handle file upload
		// 1. File available
		if (logofile != null && logofile.getTotalSpace() > 0) {
			// 2. Build file dir path
			String logodir = "/images/brand/"
					+ new SimpleDateFormat("yyyy/MM/dd/hh/").format(new Date());
			// 3. File real path in server
			String logorealdir = ServletActionContext.getServletContext()
					.getRealPath(logodir);
			System.out.println("logo file: " + logofile.getPath());
			// 4. Get extension for file
			String ext = extension.equals("gif") ? "." + extension : ".gif";
			System.out.println("logo file extension: " + ext);

			// 5. Create file dir if not exist
			File logodirFile = new File(logorealdir);
			if (!logodirFile.exists()) {
				logodirFile.mkdirs();
			}
			// 6. FileOutputStream to write file
			String logoname = UUID.randomUUID().toString();
			File logodestFile = new File(logodirFile, logoname + ext);
			String logopath = logodir + logoname + ext;
			System.out.println("web logopath: " + logopath);
			try {
				FileUtils.copyFile(logofile, logodestFile);
				// 7. set file path to brand object
				brand.setLogopath(logopath);
			} catch (IOException e) {
				e.printStackTrace();
				throw (e);
			}
		}

		brandService.save(brand);
		addActionMessage("Add brand Passed!");

		return "message";
	}

	public String editUI() {
		String code = brand.getCode();
		brand = brandService.find(Brand.class, code);
		return "editUI";
	}

	public String edit() {
		if(brand == null) {
			addActionError("No brand object found");
			return "message";
		}
		// If name not define, go to global message page
		if (brand.getName() == null || "".equals(brand.getName())) {
			addActionError("Please give one name for product type which you defined.");
			return "message";
		}
		
		brandService.update(brand);
		addActionMessage("Update brand Passed!");
		return "message";
	}

	public String queryUI() {
		return "queryUI";
	}
}
