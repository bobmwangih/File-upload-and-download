package com.bob.service;

import org.springframework.web.multipart.MultipartFile;

import com.bob.entity.Attachment;

public interface AttachmentService {

	Attachment saveAttachent(MultipartFile file) throws Exception;

	Attachment getAttachment(String fileId) throws Exception;

}
