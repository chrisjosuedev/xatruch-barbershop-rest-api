# Xatruch Barbershop RestAPI

<p style="justify-content: center">
   <img src="https://img.shields.io/badge/versión-v1.0-blue.svg" alt="version">
   <img src="https://img.shields.io/badge/status-completed-green" alt="status">
</p>

✨ Rest API developed as a solution for managing barber services.

## ⚡️ Description
Xatruch Barbershop RestAPI is a comprehensive solution for managing barber services seamlessly. With authentication functionalities, user-friendly endpoints for reviews, and an administrative interface for scheduling services and handling bookings, email sending and uploading pictures, this API streamlines the operation of a modern barbershop.

## 👨‍💻 Documentation OpenAPI
- [Review Collections & Documentation](https://documenter.getpostman.com/view/21748987/2s9Ykn92Fr)

## ✍🏻 Getting Started
Before you get started, make sure you have the following:
1. Java Development Kit (JDK): Install JDK 8 or above on your system.
2. Maven: Ensure you have Maven installed to manage dependencies and build the project.
3. Database: Configure a MySQL Database.

## 🚀 Setup
> Project Configuration.
1. Clone this project: `git clone https://github.com/chrisjosuedev/xatruch-barbershop-rest-api.git`
2. Go to the project folder: `cd xatruch-barbershop-rest-api`

> Database & Env Variables in your system:
1. Configure your MySQL database with your `DB_CONNECTION`, `DB_USER`, `DB_PASSWORD` values in application-dev.yml file.
2. Configure Env Variable: `CLIENT_SERVER` with your Client Server (if you want to develop it)
3. Change `EMAIL` and `EMAIL_PASSWORD` with an Email Account to enable sending email. (Outlook by Default as `host` in application.yml)
4. Set up a Cloudinary Account to upload profile pictures, and change `CLOUD_NAME`, `API_SECRET`, `API_KEY` with your data. If you dont want to use Cloudinary, go to `UploadImageServiceImpl` service class and change `@Qualifier("cloudinaryStorage")` to  `@Qualifier("localStorage")`, then create `uploads/images` folder in root project. This will enable saving profile pictures locally. You must create the directory first, otherwise it will throw an exception.

## 🛠 Run
Once the dependencies are installed, you can run via IDE or Maven.

> With Maven, Run:
1. Build the project using Maven `mvn clean install -DskipTests`
2. Compile and package project `mvn package -DskipTests`
3. Run application `java -jar target/xatruch-barbershop-api-0.0.1-SNAPSHOT.jar`

Test Endpoints via Postman (or your preferred API tester) on port 9090: `http://localhost:9090/api/...`
   > Please check the documentation (Postman) above to see the available endpoints and change `Dev Env`

## 🦀 Technologies
![SpringBoot badge](https://img.shields.io/badge/springboot-java-brightgreen)
![Java badge](https://img.shields.io/badge/java-21-red)
![MySQL badge](https://img.shields.io/badge/mysql-db-blue)

## 🧾 License

The MIT License (MIT)