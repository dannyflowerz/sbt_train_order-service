# order-service

Order microservice after splitting the monolith

## GCP

### Upload jar file to bucket
```
gsutil cp target/order-service-0.0.1-SNAPSHOT.jar \
    gs://sbt-train-bucket/order-service.jar
```

### Create compute instance for service
```
gcloud compute instances create order-service-instance \
--image-family debian-9 \
--image-project debian-cloud \
--machine-type g1-small \
--scopes "userinfo-email,cloud-platform" \
--metadata-from-file startup-script=instance-startup.sh \
--metadata BUCKET=sbt-train-bucket \
--zone europe-west2-a \
--tags order-service
```

### Stop instance to avoid extra costs
```
gcloud compute instances stop order-service-instance
```
