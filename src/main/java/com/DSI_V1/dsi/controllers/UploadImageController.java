package com.DSI_V1.dsi.controllers;

import com.DSI_V1.dsi.helpers.ExtractUserIDFromToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("api/v1/image")
public class UploadImageController {

    private int user_idString;
    @Autowired
    private ExtractUserIDFromToken extractUserIDFromToken;
    private final String uploadDir = System.getProperty("user.dir") + "/resources/images/"+user_idString+ "/";
    private final String GetDir = System.getProperty("user.dir")+ "/resources/images/";


    // API lưu ảnh
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            user_idString = extractUserIDFromToken.getUserIDFromToken(request);

            if (file.isEmpty()) {
                return new ResponseEntity<>("File không hợp lệ!", HttpStatus.BAD_REQUEST);
            }


            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path path = Paths.get(GetDir +user_idString + "/" + fileName);

            if (Files.exists(path)) {
                return new ResponseEntity<>("File đã tồn tại!", HttpStatus.CONFLICT); // 409 Conflict
            }

            Files.createDirectories(Paths.get(GetDir + user_idString));

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return new ResponseEntity<>(fileName, HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>("Lỗi khi lưu file: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // API lấy ảnh
    @GetMapping("/download/{id}/{fileName:.+}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String id, @PathVariable String fileName) {
        try {
            // Đường dẫn file ảnh
            Path filePath = Paths.get(GetDir + '/' + id + "/" + fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                // Thiết lập header cho ảnh
                HttpHeaders headers = new HttpHeaders();
                String extension = getFileExtension(fileName);
                MediaType mediaType = getMediaType(extension);
                headers.setContentType(mediaType);
                headers.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS).cachePublic()); // Caching cho ảnh


                return ResponseEntity.ok()
                        .headers(headers)
                        .body(resource);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    private String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf('.');
        if (lastIndexOf == -1) {
            return ""; // Không có phần mở rộng, trả về chuỗi trống
        }
        return fileName.substring(lastIndexOf + 1).toLowerCase();
    }

    // Hàm để xác định MediaType (Content-Type) dựa trên phần mở rộng file
    private MediaType getMediaType(String extension) {
        switch (extension) {
            case "jpg":
            case "jpeg":
                return MediaType.IMAGE_JPEG; // Định dạng JPEG
            case "png":
                return MediaType.IMAGE_PNG; // Định dạng PNG
            case "webp":
                return MediaType.valueOf("image/webp"); // Định dạng WebP
            default:
                return MediaType.IMAGE_JPEG; // Mặc định là JPEG nếu không xác định được
        }
    }
}
