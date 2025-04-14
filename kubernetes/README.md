# Folex Kubernetes Cluster ⌚
Kubernetes configuration files for deploying the services behind Folex.

## Getting Started ⛏️
In order for the Kubernetes deployment to work, you must have the following configured:
- A Kubernetes cluster in Azure Kubernetes Service (AKS).
- The relevant repositories in the Azure Container Registry (ACR).

## Configuration 🔨
For each of the services, a kubernetes secret must be set up with the correct titles and variables:

- **Order Service**:
    - The secret must be titled "sc-orderservicesc-secret".
    - The secret must contain the variables:
        >- "SPRING_DATASOURCE_URL"
        >- "SPRING_DATASOURCE_USERNAME"
        >- "SPRING_DATASOURCE_PASSWORD"

- **Workflow Service**:
    - The secret must be titled "workflow-secrets".
    - The secret must contain the variables:
        >- "SPRING_DATASOURCE_URL"
        >- "SPRING_DATASOURCE_USERNAME"
        >- "SPRING_DATASOURCE_PASSWORD"
        >- "CAMUNDA_BPM_ADMIN_USER_ID"
        >- "CAMUNDA_BPM_ADMIN_USER_PASSWORD"
        >- "SPRING_RABBITMQ_HOST"
        >- "SPRING_RABBITMQ_USERNAME"
        >- "SPRING_RABBITMQ_PASSWORD"
        >- "FOLEX_API_ORDER"
        >- "FOLEX_API_FACTORY"

- **Wedding Watches**:
    - The secret must be titles "wedding-watches-secret".
    - The secret must contain the variables:
        >- "QUEUE_URL"
        >- "QUEUE_USERNAME"
        >- "QUEUE_PASSWORD"
        >- "ORIGIN"

    *"ORIGIN" must be configured with the external IP of the wedding watches service.*

- **Rabbit**:
    - The secret must be titled "rabbit-secrets".
    - The secret must contain the variables:
        >- "RABBITMQ_DEFAULT_USER"
        >- "RABBITMQ_DEFAULT_PASS"


## Deployment 🚀
To deploy the Folex services on Kubernetes, in the kubernetes directory run the following command in your terminal:
```
bash deploy1.sh
```
Once the external IP of the wedding-watches service has been added to the wedding watches secrets, in the kubernetes directory run the following command in your terminal:
```
kubectl apply -f wedding-watches-deployment.yml
```