# Fetching the lastest node image on apline linux
FROM node:19.1.0

WORKDIR /app
COPY package.json package.json
COPY package-lock.json package-lock.json
RUN npm install --force
COPY . .
CMD ["npm", "start"]