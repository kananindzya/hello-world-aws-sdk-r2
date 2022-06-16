package com.example.aws.api.r2;

import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class R2ServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(R2ServiceImpl.class);

    @Autowired
    private AmazonS3 r2Client;

    public void createBucket(String bucketName) {
        try {
            r2Client.createBucket(bucketName);
        } catch (Throwable t) {
            log.error("Error during creating bucket", t);
            throw new IllegalStateException(t);
        }
    }

    public boolean isExistObject(String bucketName, String key) {
        try {
            boolean isExistBucket = r2Client.doesBucketExist(bucketName);
            if (!isExistBucket) {
                throw new IllegalStateException("Bucket does not exist");
            }
            return r2Client.doesObjectExist(bucketName, key);
        } catch (Throwable t) {
            log.error("Got error during checking on existing", t);
            return false;
        }
    }

    public void uploadFileWithStringBody(String bucketName, String key, String content) {
        try {
            r2Client.putObject(bucketName, key, content);
        } catch (Throwable t) {
            log.error("Got error during puting object", t);
            throw new IllegalStateException(t);
        }
    }

}
