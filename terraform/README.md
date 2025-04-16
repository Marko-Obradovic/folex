# Folex Terraform files ⌚
Terraform files for deploying the postgres server and databases for Folex.


## Configuring and deploying the backend 🔨
The first step is to set-up a remote backend for Terraform, as well as other initial resources that won't be frequently changed.
This is to allow terraform to detect the state of resources across different systems and within azure pipelines as well.
To this end we:
1) create an Azure Storage account and container, where the tfstate files are stored.
2) create a container registry in which we'll have repositories for storing the various docker images necessary for our services to run.
3) create a devops identity for use in creating the service connection for our azure pipelines to run correctly
4) create the kubernetes cluster in which the kubernetes services will be created/updated

First, go into the terraform/backend and create a terraform.tfvars file with the following:

```
subscription_id: your azure subscription id
resource_group: the name of your existing azure resource group
storage_account: a suitable name for the storage account
storage_container: a suitable name for the storage container
container_registry: a suitable name for the container registry
object_ids: list of microsoft entra object IDs
devops_pat: a generated pat from azure devops
project_name: the name of your project on azure devops
org_name: the organisation name on azure devops
```

After this, run the following, checking the output to terminal for anything unexpected:

```
terraform init
terraform plan
terraform apply
```
This should, once complete, set up everything you need for managed terraform as well as key initial resources.


## Configuring and deploying the postgres server and databases🔨

After setting up the backend, go into the terraform/main folder, and create a .env file with the following:

```
ARM_SUBSCRIPTION_ID: azure subscription id
ARM_STORAGE_ACCOUNT: the storage account name created in the backend step
ARM_CONTAINER_NAME: the storage container created in the backend step
ARM_RESOURCE_GROUP: your existing resource group
ARM_STATE_KEY=terraform.tfstate
```

Once this is done, run:

```
bash init_backend.sh (name for development workspace e.g., dev)
```

This initalises terraform using the above environment variables, allowing terraform to connect to our previously created remote backend
and creating the dev workspace (if you don't already have it) and switching to it.
Through the use of workspaces the remote backend will save separate tfstate files for each workspace, and via the use of ${terraform.workspace}
we can have entirely separate resources and environments per workspace. This allows us to handle dev and prod resources separately.

If you wish to create/update production resources, you simply run:

```
bash init_backend.sh (name for production workspace e.g., prod)
```

instead.

Afterwards, you run:

```
terraform plan
terraform apply
```

to create/update the resources for that particular environment.

Of course, you're free to use as many workspaces/environments as needed for your use case, such as multiple test environments.

To switch workspaces, you can run:

```
terraform workspace select (name of workspace)
```

though it must be noted that 1) the environment must already have been created, and 2) the backend must have been initialised at least once with the bash script.

Alternatively,

```
bash init_backend.sh (workspace name)
```

will (re)-initialise the backend and create/switch to any given workspace and can be used instead.
