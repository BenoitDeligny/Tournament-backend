# Tournament-backend

Launch this docker compose and try the API

```
version: '3.9'
services:
  mongodb:
    image: mongo:latest
    restart: always
    container_name: mongodb-container
    ports:
      - "27017:27017"
    volumes:
      - mongodb:/data/db
  backend:
    build:
      context: ./tournament-backend/
      dockerfile: Dockerfile
    restart: always
    container_name: tournament-backend
    ports:
      - "8080:8080"
    environment:
      PORT: 8080
      MONGODB_HOST: mongodb
      MONGODB_URI: mongodb://mongodb:27017
      DB_NAME: mydb
    depends_on:
      - mongodb

volumes:
  mongodb: {}
```
