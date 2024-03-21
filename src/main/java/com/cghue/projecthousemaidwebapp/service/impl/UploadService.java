package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.FileInfo;
import com.cghue.projecthousemaidwebapp.repository.FileInfoRepository;
import com.cghue.projecthousemaidwebapp.utils.UploadUtils;
import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class UploadService {

    private final Cloudinary cloudinary;

    private final FileInfoRepository fileRepository;

    private final UploadUtils uploadUtils;

    public FileInfo saveAvatar(MultipartFile avatar) throws IOException {
        var file = new FileInfo();
        fileRepository.save(file);

        var uploadResult = cloudinary.uploader().upload(avatar.getBytes(), uploadUtils.buildImageUploadParams(file));

        String fileUrl = (String) uploadResult.get("secure_url");
        String fileFormat = (String) uploadResult.get("format");

        file.setFileName(file.getId() + "." + fileFormat);
        file.setFileUrl(fileUrl);
        file.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
        file.setCloudId(file.getFileFolder() + "/" + file.getId());
        String fileType = file.getFileName().substring(file.getFileName().lastIndexOf(".") + 1);
        file.setFileType(fileType);
        fileRepository.save(file);
        return file;
    }

}
