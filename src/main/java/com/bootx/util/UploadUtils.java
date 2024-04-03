package com.bootx.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UploadUtils {

    private static final String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    private static final String accessKeyId = "LTAI4GCjrkxGi8rcyoiy6i8Y";
    private static final String accessKeySecret = "AEG4gBrjvNQvSJRSStrZfHfC4EJZOW";
    private static final String bucketName = "bootx-xiaochengxu";

    private static final String url="https://bootx-xiaochengxu.oss-cn-hangzhou.aliyuncs.com";

    public static void upload(File file, String path) {
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, file);
            ossClient.putObject(putObjectRequest);
            ossClient.shutdown();
        }finally {
            FileUtils.deleteQuietly(file);
        }
    }

    public static void upload(String url, String path) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = new URL(url).openStream();
        ossClient.putObject(bucketName, path, inputStream);
        ossClient.shutdown();
    }

    public static void upload(InputStream inputStream, String path) {
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, path, inputStream);
            ossClient.shutdown();
        }finally {
            try {
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String uploadNet(String url,String path) throws Exception {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            InputStream inputStream = new URL(url).openStream();
            ossClient.putObject(bucketName, path, inputStream);
        } catch (OSSException | ClientException oe) {
            oe.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return getUrl(path);
    }

    public static void upload1(File file, String path,String uuid,String bucketName) {
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, file).withProgressListener(new ProgressListener() {
                private long bytesWritten = 0;
                @Override
                public void progressChanged(ProgressEvent progressEvent) {
                    long bytes = progressEvent.getBytes();
                    if(progressEvent.getEventType()== ProgressEventType.REQUEST_BYTE_TRANSFER_EVENT){
                        this.bytesWritten += bytes;
                        int percent = (int)(this.bytesWritten * 10000.0 / file.length());
                        EhCacheUtils.setUploadRateCache(uuid,percent);
                    }
                }
            });
            ossClient.putObject(putObjectRequest);
            ossClient.shutdown();
        }finally {
            FileUtils.deleteQuietly(file);
        }
    }

    public static String getUrl(String path){
        if(StringUtils.startsWith(path,"/")){
            return url+path;
        }
        return url+"/"+path;
    }

    public static String getUrl1(String path) {
        if(StringUtils.startsWith(path,"/")){
            return url+path;
        }
        return url+"/"+path;
    }


    public static void exist(String resourceUrl){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            String replace = resourceUrl.replace(url+"/", "");
            boolean found = ossClient.doesObjectExist(bucketName, replace);
        } catch (OSSException | ClientException oe) {
            oe.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }


    public static void delete(String resourceUrl) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            String replace = resourceUrl.replace(url+"/", "");
            ossClient.deleteObject("bootx-xiaochengxu", replace);
        } catch (OSSException | ClientException oe) {
            oe.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public static List<OSSObjectSummary> list(){
        List<OSSObjectSummary> list = new ArrayList<>();
        int maxKeys = 200;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            String nextMarker = null;
            ObjectListing objectListing;
            do {
                objectListing = ossClient.listObjects(new ListObjectsRequest(bucketName).withMarker(nextMarker).withMaxKeys(maxKeys));
                List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
                list.addAll(sums);
                nextMarker = objectListing.getNextMarker();
            } while (objectListing.isTruncated());
        } catch (OSSException | ClientException oe) {
            oe.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return list;
    }


    public static void getInfo() throws Exception {
        String objectName = "2022/08/03/wx09eed9f5041cfd47/9ac1e62ead6940ea9651611f585494ca.png";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 获取指定versionId文件的部分元信息。
            GenericRequest getSimplifiedObjectMetaRequest = new GenericRequest(bucketName, objectName);
            SimplifiedObjectMeta objectMeta = ossClient.getSimplifiedObjectMeta(getSimplifiedObjectMetaRequest);
            System.out.println(objectMeta.getSize());
            System.out.println(objectMeta.getETag());
            System.out.println(objectMeta.getLastModified());

            // 获取指定versionId文件的全部元信息。
            GenericRequest getObjectMetadataRequest = new GenericRequest(bucketName, objectName);
            ObjectMetadata metadata = ossClient.getObjectMetadata(getObjectMetadataRequest);
            System.out.println(metadata.getContentType());
            System.out.println(metadata.getLastModified());
            System.out.println(metadata.getExpirationTime());
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
