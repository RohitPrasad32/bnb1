//package com.airbnb.bnb1.config;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//
//
//    @Configuration
//     public class AWSS3Config {
//
//        @Value("${cloud.aws.credentials.access-key}")
//        private String accessKey;
//
//        @Value("${cloud.aws.credentials.secret-key}")
//        private String secretKey;
//
//        @Value("${cloud.aws.region.static}")
//        private String region;
//
//        public AWSCredentials credentials() {
//            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
//            return credentials;
//        }
//
//        @Bean
//        public AmazonS3 amazonS3() {
//            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                    .withCredentials(new AWSStaticCredentialsProvider(credentials())).build();
//            return s3Client;
//        }
//}

package com.airbnb.bnb1.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSS3Config {

    @Value("${accessKey}")
    private String accessKey;

    @Value("${secretKey}")
    private String secretKey;

    @Value("${region}")
    private String region;

    public AWSCredentials credentials() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
           return credentials;
    }

    @Bean
    public AmazonS3 amazonS3() {
         AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)  // Make sure region is set
                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .build();
        return s3Client;
    }
}
