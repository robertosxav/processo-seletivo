package br.com.robertoxavier.api.ports.minio;

import br.com.robertoxavier.service.Resource;
import br.com.robertoxavier.service.StorageService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.DeleteObject;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;


public class MinIOStorageService implements StorageService {
    private final String bucket;
    private final MinioClient minioClient;

    public MinIOStorageService(String bucket, MinioClient minioClient) {
        this.bucket = bucket;
        this.minioClient = minioClient;
    }

    @Override
    public void store(String id, Resource resource) {
        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(resource.content());
            final var info = PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(id)
                    .contentType(resource.contentType())
                    .stream(bais, bais.available(), -1)
                    .build();

            minioClient.putObject(info);

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Resource> get(String id) {
        GetObjectArgs objArgs = GetObjectArgs.builder()
                .bucket(bucket)
                .object(id)
                .build();

        try {
            var object = minioClient.getObject(objArgs);
            byte[] content = object.readAllBytes();

            String checksum = object.headers().get("ETag");

            String contentType = object.headers().get("Content-Type");

            String name = id;

            return Optional.of(Resource.with(content, checksum, contentType, name));

        } catch (ErrorResponseException | InsufficientDataException | InternalException
                 | InvalidKeyException | InvalidResponseException | IOException
                 | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new RuntimeException("Erro ao recuperar o objeto do MinIO: " + e.getMessage(), e);
        }
    }

    @Override
    public List<String> list(String prefix) {
        return List.of();
    }

    @Override
    public void deleteAll(List<String> ids) {
        final var idsArg = ids.stream().map(id -> new DeleteObject(id)).toList();
        RemoveObjectsArgs rmvArg= RemoveObjectsArgs.builder().bucket(bucket).objects(idsArg).build();
        minioClient.removeObjects(rmvArg);
    }

    @Override
    public String generateTemporaryLink(String id) {
        int expirationTimeSeconds= 300;
        final var args = GetPresignedObjectUrlArgs
                .builder()
                .expiry(expirationTimeSeconds)
                .method(Method.GET)
                .bucket(bucket)
                .object(id)

                .build();

        try{
            String url = minioClient.getPresignedObjectUrl(args);
            return url;
        }catch(ErrorResponseException | InsufficientDataException | InternalException
               | InvalidKeyException | InvalidResponseException | IOException
               | NoSuchAlgorithmException | ServerException | XmlParserException e){
            throw new RuntimeException("Erro ao gerar url temporaria: " + e.getMessage(), e);
        }
    }
}