kubectl apply -f order-deployment.yml
kubectl apply -f order-service.yml
kubectl apply -f order-hpa.yml
kubectl apply -f rabbitmq-deployment.yml
kubectl apply -f rabbitmq-service.yml
kubectl apply -f wedding-watches-service.yml
kubectl apply -f wedding-watches-hpa.yml
kubectl apply -f workflow-dep.yml
kubectl apply -f workflow-serv.yml
kubectl apply -f workflow-hpa.yml