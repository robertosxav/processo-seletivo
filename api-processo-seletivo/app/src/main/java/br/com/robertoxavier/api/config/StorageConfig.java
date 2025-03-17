package br.com.robertoxavier.api.config;

import br.com.robertoxavier.api.ports.minio.MinIOStorageService;
import br.com.robertoxavier.service.StorageService;
//import br.com.robertoxavier.api.ports.minio.MinIOStorageService;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {


    @Value("${seletivo.s3.url}")
    private String s3url;

    @Value("${seletivo.s3.access-key}")
    private String s3AccessKey;

    @Value("${seletivo.s3.access-secret}")
    private String s3AccessSecret;

    @Value("${seletivo.s3.bucket-name}")
    private String bucketName;

    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(s3url)
                .credentials(s3AccessKey, s3AccessSecret)
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public StorageService gcStorageAPI(
            final MinioClient storage
    ) {
        return new MinIOStorageService(bucketName, storage);
    }
}
