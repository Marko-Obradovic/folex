set -e

ENV=$1
source .env

echo "Initializing Terraform backend for $ENV..."

terraform init \
  -backend-config="subscription_id=$ARM_SUBSCRIPTION_ID" \
  -backend-config="resource_group_name=$ARM_RESOURCE_GROUP" \
  -backend-config="storage_account_name=$ARM_STORAGE_ACCOUNT" \
  -backend-config="container_name=$ARM_CONTAINER_NAME" \
  -backend-config="key=$ARM_STATE_KEY"

if terraform workspace list | sed 's/^[* ]*//' | grep -Fxq "$ENV"; then
  echo "Selecting existing workspace: $ENV"
  terraform workspace select "$ENV"
else
  echo "Creating new workspace: $ENV"
  terraform workspace new "$ENV"
fi
