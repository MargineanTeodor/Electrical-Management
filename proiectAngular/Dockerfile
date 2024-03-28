FROM node:20.9.0 AS builder
WORKDIR /FE
COPY package*.json ./
RUN npm install -g npm@10.2.4
COPY . .
RUN npm run build
EXPOSE 4200
CMD ["npm","start","--","--host","0.0.0.0","--port","4200"]