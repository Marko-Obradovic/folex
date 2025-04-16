FROM node:20-alpine

RUN mkdir -p /app
WORKDIR /app

COPY *.config.js ./
COPY *.config.ts ./
COPY *.json ./
COPY src ./src
COPY static ./static
COPY .npmrc /root/.npmrc
COPY folexca.pem /folexca.pem

RUN npm install
RUN npm run build
RUN npm ci --omit dev --force

CMD ["node", "build"]
