package com.barath.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
public class PDFController {
	
	private static final String SAMPLE_PDF="classpath:samplepdf.pdf";
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final ResourceLoader resourceLoader;
	
	
	public PDFController(ResourceLoader resourceLoader) {
		super();
		this.resourceLoader = resourceLoader;
	}


	@PostMapping(value="/retrive/pdf")
	public void retrivePDF(HttpServletResponse response) throws IOException {
		
		if(logger.isInfoEnabled()) { logger.info(" retrive pdf "); }
		Resource resource = this.resourceLoader.getResource(SAMPLE_PDF);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition", "attachment; filename=samplepdf");
        InputStream is= resource.getInputStream();
        byte[] data =FileCopyUtils.copyToByteArray(is);
        response.setContentLength(data.length);

        response.getOutputStream().write(data);
        response.getOutputStream().flush();
	}

}
