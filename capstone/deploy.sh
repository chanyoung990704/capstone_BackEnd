#!/bin/bash

cd /home/ubuntu/deploy/

# 이미지 파일명과 컨테이너 이름 설정
IMAGE_TAR="urmovie_back.tar"
CONTAINER_NAME="urmovie_backend"

# .tar 파일로부터 Docker 이미지 로드
sudo docker load -i $IMAGE_TAR > /dev/null 2>&1

# 로드된 이미지의 ID 얻기
IMAGE_ID=$(sudo docker images -q | head -n 1)

# Docker 컨테이너 실행
sudo docker run -d -p 8080:8080 --name $CONTAINER_NAME $IMAGE_ID > /dev/null 2>&1
