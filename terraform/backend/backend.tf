terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = ">= 4.22.0"
    }
  }
}

provider "azurerm" {
  features {}
  subscription_id = var.subscription_id
}

data "azurerm_resource_group" "tf_resource_group" {
  name     = var.resource_group
}

resource "azurerm_storage_account" "tfstate" {
  name                     = var.storage_account
  resource_group_name      = data.azurerm_resource_group.tf_resource_group.name
  location                 = data.azurerm_resource_group.tf_resource_group.location
  account_tier             = "Standard"
  account_replication_type = "LRS"
}

resource "azurerm_storage_container" "tfstate" {
  name                  = var.storage_container
  storage_account_id  = azurerm_storage_account.tfstate.id
  container_access_type = "private"
}
