
### Build container application
docker build -t my-library-app:v1 .

## how to run application all together
docker-compose up -d

### how to run images separately
cd docker-compose
### run postgress db here in separate container
docker-compose up

### run image app
docker run -p:8080:8080 --rm my-library-app:v1