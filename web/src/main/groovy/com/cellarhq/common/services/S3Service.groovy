package com.cellarhq.common.services

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.internal.Mimetypes
import com.amazonaws.services.s3.model.AccessControlList
import com.amazonaws.services.s3.model.GroupGrantee
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.Permission
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.PutObjectResult
import com.cellarhq.common.CellarHQConfig
import com.google.inject.Inject
import groovy.transform.CompileStatic

@CompileStatic
class S3Service {

    static final String BUCKET_PRODUCTION  = 'storage-www.cellarhq.com'
    static final String BUCKET_DEVELOPMENT = 'storage-local.cellarhq.com'

    private final AmazonS3Client client
    private final String bucketName

    @Inject
    S3Service(AWSCredentials credentials, CellarHQConfig cellarHQConfig) {
        client = new AmazonS3Client(credentials)

        // IMPORTANT: Changing this will require changing how getObjectUrl works. We probably shouldn't change it.
        client.region = Region.getRegion(Regions.US_EAST_1)

        this.bucketName = cellarHQConfig.s3StorageBucket
    }

    PutObjectResult upload(String key, InputStream is, ObjectMetadata metadata) {
        if (!metadata.contentType) {
            metadata.contentType = Mimetypes.instance.getMimetype(key)
        }

        PutObjectRequest request = new PutObjectRequest(bucketName, key, is, metadata)

        // The canned public-read ACL doesn't work for images; it only allows downloading, so we have to use our own
        request.accessControlList = new AccessControlList().with { AccessControlList self ->
            grantPermission(GroupGrantee.AuthenticatedUsers, Permission.FullControl)
            grantPermission(GroupGrantee.AllUsers, Permission.ReadAcp)
            return self
        }

        return client.putObject(request)
    }

    String getObjectUrl(String key) {
        return "http://${bucketName}.s3-website-us-east-1.amazonaws.com/${key}"
    }
}
