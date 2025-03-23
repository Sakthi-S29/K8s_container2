## **README for Container 2 Repository**

# **Container 2 - Kubernetes Assignment**

This repository contains the code and configuration for **Container 2**, which is part of the Kubernetes Assignment for **CSCI 5409 - Advanced Topics in Cloud Computing**. Container 2 is a microservice that calculates the total of a product from a file stored in the persistent volume.

---

### **Table of Contents**
1. [What is Container 2?](#what-is-container-2)
2. [How Does It Work?](#how-does-it-work)
3. [Technologies Used](#technologies-used)
4. [Setup and Deployment](#setup-and-deployment)
5. [API Endpoints](#api-endpoints)
6. [Directory Structure](#directory-structure)
7. [CI/CD Pipeline](#cicd-pipeline)
8. [Important Notes](#important-notes)
9. [Contributing](#contributing)
10. [License](#license)

---

### **What is Container 2?**
Container 2 is a **Spring Boot microservice** that:
1. Reads a file from the **persistent volume**.
2. Calculates the total of a product and returns the result to **Container 1**.

---

### **How Does It Work?**
1. **File Reading:**
   - Container 2 receives a file name and product name from **Container 1**.
2. **Product Calculation:**
   - Container 2 reads the file from the persistent volume.
   - It calculates the total of the product and returns the result to **Container 1**.

---

### **Technologies Used**
- **Programming Language:** Java (Spring Boot)
- **Containerization:** Docker
- **Orchestration:** Kubernetes (GKE)
- **CI/CD:** Google Cloud Build
- **Persistent Storage:** PersistentVolume (PV) and PersistentVolumeClaim (PVC)
- **API Testing:** Postman/cURL

---

### **Setup and Deployment**

#### **1. Prerequisites**
Before you begin, ensure you have the following:
- A **Google Cloud Platform (GCP)** account.
- **Google Cloud SDK** (`gcloud`) installed.
- **Terraform** installed (for creating the GKE cluster).
- **Docker** installed.

#### **2. Clone the Repository**
```bash
git clone https://github.com/<your-username>/container2.git
cd container2
```

#### **3. Build the Application**
Build the Spring Boot application using Maven:
```bash
mvn clean package
```

#### **4. Build and Push Docker Image to Google Artifact Registry**
Build the Docker image and push it to the **Google Artifact Registry**:
```bash
docker build -t us-central1-docker.pkg.dev/<project-id>/k8s-container2/container2:latest .
docker push us-central1-docker.pkg.dev/<project-id>/k8s-container2/container2:latest
```

#### **5. Deploy to GKE**
Apply the Kubernetes manifests to deploy the application:
```bash
kubectl apply -f pv.yaml
kubectl apply -f pvc.yaml
kubectl apply -f deployment2.yaml
kubectl apply -f service2.yaml
```

---

### **API Endpoints**

#### **1. Calculate Product Total**
- **Endpoint:** `POST /calculate`
- **Request:**
  ```json
  {
    "file": "file.dat",
    "product": "wheat"
  }
  ```
- **Response:**
  ```json
  {
    "file": "file.dat",
    "sum": 30
  }
  ```

---

### **Directory Structure**
```
container2/
├── src/                     # Source code for the Spring Boot application
├── target/                  # Compiled JAR file
├── Dockerfile               # Dockerfile for building the container image
├── pv.yaml                  # PersistentVolume manifest
├── pvc.yaml                 # PersistentVolumeClaim manifest
├── deployment2.yaml         # Kubernetes Deployment manifest
├── service2.yaml            # Kubernetes Service manifest
├── README.md                # This file
└── ...                      # Other configuration files
```

---

### **CI/CD Pipeline**
The CI/CD pipeline is built using **Google Cloud Build**. It performs the following steps:
1. Builds the JAR file using Maven.
2. Builds and pushes the Docker image to the **Google Artifact Registry**.
3. Deploys the application to GKE.

---

### **Important Notes**
- **Container 2 depends on Container 1:** Container 2 is called by **Container 1** to calculate the total of a product. Both services must be deployed and running for the system to work.
- **Persistent Volume:** The persistent volume is mounted at `/SakthiSharan_PV_dir` in the container.
- **Service IP Address:** After deploying the service, get the external IP using:
  ```bash
  kubectl get services
  ```
  Use this IP to access the API endpoints.
- **Image Pull Policy:** The deployment file is configured to pull the image from the **Google Artifact Registry**. Ensure the image is pushed to the registry before deploying.

---

### **Key Points for Both Repositories**
- **Independent Repositories:** The two repositories (`container1` and `container2`) are independent and must be deployed separately.
- **Dependency:** Both services must be running for the system to function.
- **Persistent Volume:** The persistent volume is shared between the two containers and mounted at `/SakthiSharan_PV_dir`.
- **Service IP Addresses:** Use `kubectl get services` to get the external IPs for accessing the APIs.
- **Google Artifact Registry:** The deployment files are configured to pull images from the **Google Artifact Registry**. Ensure the images are pushed to the registry before deploying.
