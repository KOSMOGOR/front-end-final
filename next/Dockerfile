FROM node:20

ARG APP_DIR=app
RUN mkdir -p ${APP_DIR}
WORKDIR ${APP_DIR}
COPY . .

RUN npm install
RUN npm run build

ENTRYPOINT [ "npm", "run", "start" ]