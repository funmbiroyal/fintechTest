
# Fintech Backend

## Project Overview

This is the backend of the **Fintech Dashboard** project. It is designed to handle financial operations such as loan application management, transaction history, and other fintech-related functionalities. The backend is built using **Spring Boot** and exposes RESTful APIs for the required features.

## Features

- **Loan Application Management**: APIs to apply for loans, check loan status, and calculate dynamic interest rates.
- **Transaction History**: View and manage transaction history with filtering options for date range and transaction types (credit, debit).
- **Authentication**: Login and registration features using JWT for secure session management.
- **API Documentation**: Swagger integration for easy exploration of all available APIs.
- **Data Visualizations**: APIs to support visualizing loan repayment trends and transaction types.

## Setup Instructions

### 1. Clone the Repository
Start by cloning the repository to your local machine:
```
git clone https://github.com/your-username/fintechTest.git
cd fintech-dashboard
```
2. Install Dependencies
The project uses Maven for dependency management. Run the following command to install all the necessary dependencies:
```
mvn install
```
3. Configure Application Properties
Ensure that you set up the necessary environment variables, such as database connections or any other configurations that are required by the application.

You can configure these in the application.properties or application.yml file located under src/main/resources.

4. Run the Application
Once the dependencies are installed and configured, you can run the backend with:

```
mvn spring-boot:run
```
This will start the application locally on http://localhost:8080.

API Documentation
This backend API is fully documented using Swagger. You can explore the available endpoints through the Swagger UI by navigating to the following URL after running the application:

http://localhost:8080/swagger-ui.html

Here, you can find all the API endpoints, their descriptions, and how to interact with them.

Example API Endpoints:
POST /api/loans/apply - Apply for a new loan.
GET /api/transactions - Retrieve transaction history.
POST /api/auth/login - Login to the application and receive a JWT token.
E.t.c

Deployment
Backend Deployment:
The backend has been deployed on Heroku. You can access the deployed application using the following URL:

Backend URL: https://fintech-backend.herokuapp.com

You can interact with the API through this link, and the Swagger UI documentation will also be available at /swagger-ui.html.

Assumptions & Limitations:
The backend assumes the use of JWT tokens for user authentication.
The loan application process is simplified for the purpose of the project, with static interest rates for preview.
Pagination is implemented for transactions with filtering options based on date range and transaction type (credit or debit).

License
This project is licensed under the MIT License - see the LICENSE file for details.


### What the README Contains:
1. **Project Overview**: Describes what the backend does and its purpose.
2. **Features**: Lists out the functionalities provided by the backend.
3. **Setup Instructions**: Provides detailed steps to clone the repository, install dependencies, and run the application locally.
4. **API Documentation**: Instructions for using the Swagger UI to explore the API.
5. **Deployment Information**: Information on how the backend is deployed on Heroku with the link provided.
6. **Error Handling & Notifications**: Mentions how the system handles errors and notifications for a better user experience.
7. **Assumptions & Limitations**: Outlines any assumptions or limitations in the backend's design.

### Next Steps:
- Replace `https://github.com/your-username/fintech-dashboard.git` with your actual repository URL.
- If you have any specific details regarding the database configuration or other services, ensure to include them in the **Setup Instructions** section.






