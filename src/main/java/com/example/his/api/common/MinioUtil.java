package com.example.his.api.common;

import com.example.his.api.exception.HisException;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:
 * Package: com.example.his.api.common
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/7/5 23:14
 * @Version 1.0
 */
@Component
@Slf4j
public class MinioUtil {
    /**
     * minio 访问url地址
     */
    @Value("${minio.endpoint}")
    private String endpoint;

    /**
     * 账号
     */
    @Value("${minio.access-key}")
    private String accessKey;

    /**
     * 密码
     */
    @Value("${minio.secret-key}")
    private String secretKey;

    /**
     * 桶名，用于存储文件
     */
    @Value("${minio.bucket}")
    private String buckey;

    /**
     * minio客户端连接对象
     */
    private MinioClient client;

    /**
     * SpringBoot项目启动后自动执行该方法
     */
    @PostConstruct
    public void init() {
        this.client = new MinioClient.Builder()
                                    .endpoint(endpoint) // url 访问路径
                                    .credentials(accessKey, secretKey) // 账号密码
                                    .build();
    }

    /**
     * 上传文件
     * @param imagePath 放置的图片的路径
     * @param imageFile 图片文件
     */
    public void uploadImage(String imagePath, MultipartFile imageFile) {
        try {
            // 在 Minio 中保存图片（文件不能超过5M）
            this.client.putObject(
                    PutObjectArgs.builder()
                            .bucket(buckey) // 桶名
                            .object(imagePath) // 放置的图片的路径
                            .stream(
                                    imageFile.getInputStream(),
                                    -1, // 不知道文件大小时设置为 -1
                                    5 * 1024 * 1024 // 最大的文件大小为 5MB
                            )
                            .build()
            );
        } catch (Exception e) {
            log.error("保存文件失败", e);
            throw new HisException("保存文件失败");
        }
    }
}
