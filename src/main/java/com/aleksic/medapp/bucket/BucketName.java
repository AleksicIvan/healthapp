package com.aleksic.medapp.bucket;

public enum BucketName {
    HEALTHCHECK_BUCKET("medapp-healthcheck-12042020");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
