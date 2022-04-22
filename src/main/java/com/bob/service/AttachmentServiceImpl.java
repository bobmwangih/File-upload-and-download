package com.bob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bob.entity.Attachment;
import com.bob.repository.AttachmentRepository;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	AttachmentRepository attachmentRepository;

	@Override
	public Attachment saveAttachent(MultipartFile file) throws Exception {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(filename.contains("..")) {
				throw new Exception("File contains invalid path sequence "+ filename);
			}
			Attachment attachment = new Attachment(filename, file.getContentType(), file.getBytes());
			return attachmentRepository.save(attachment);
		} catch (Exception e) {
			throw new Exception("Could not save file: " + filename);
		}
		
	}

	@Override
	public Attachment getAttachment(String fileId) throws Exception {
		
		return attachmentRepository.findById(fileId).orElseThrow(()-> new Exception("File not found with id " + fileId ));
	}

}
