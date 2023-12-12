# Specify the base image
FROM node:21

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
#COPY package*.json ./

#COPY .next ./
COPY package.json ./
COPY yarn.lock ./

# Install dependencies
# RUN yarn install --production
RUN yarn install

# Copy the rest of the code
COPY . .

RUN yarn build

EXPOSE 3000:3000
ENTRYPOINT [ "yarn", "start" ]