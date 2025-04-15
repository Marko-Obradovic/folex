terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = ">= 4.22.0"
    }
  }
  backend "azurerm" {
  }
}

provider "azurerm" {
  features {}
  subscription_id = var.subscription_id
}

data "azurerm_resource_group" "db_resource_group" {
  name     = var.resource_group
}


resource "azurerm_postgresql_flexible_server" "postgres_server" {
  name                = "folex-postgres-server-${terraform.workspace}"
  resource_group_name = data.azurerm_resource_group.db_resource_group.name
  location            = data.azurerm_resource_group.db_resource_group.location
  version                       = "13"
  public_network_access_enabled = true
  administrator_login           = var.database_username
  administrator_password        = var.database_password
  zone                          = "2"

  storage_mb   = 32768
  storage_tier = "P4"

  sku_name = "B_Standard_B1ms"
}

resource "azurerm_postgresql_flexible_server_database" "order_db" {
  name                = "order-db-${terraform.workspace}"
  server_id           = azurerm_postgresql_flexible_server.postgres_server.id
  collation           = "en_US.utf8"
  charset             = "UTF8"

  lifecycle {
    prevent_destroy = true
  }
}

resource "azurerm_postgresql_flexible_server_database" "camunda_db" {
  name                = "camunda-db-${terraform.workspace}"
  server_id           = azurerm_postgresql_flexible_server.postgres_server.id
  collation           = "en_US.utf8"
  charset             = "UTF8"

  lifecycle {
    prevent_destroy = true
  }
}

resource "azurerm_postgresql_flexible_server_firewall_rule" "folex_postgres_firewall_rule" {
  name                = "AllowAll"
  server_id        = azurerm_postgresql_flexible_server.postgres_server.id
  start_ip_address    = "0.0.0.0"
  end_ip_address      = "255.255.255.255"
}

resource "azurerm_user_assigned_identity" "postgres_identity" {
  name                = "folex-postgres-identity-${terraform.workspace}"
  resource_group_name = data.azurerm_resource_group.db_resource_group.name
  location            = data.azurerm_resource_group.db_resource_group.location
}

resource "azurerm_role_assignment" "postgres_db_access" {
  principal_id   = azurerm_user_assigned_identity.postgres_identity.principal_id
  role_definition_name = "Contributor"
  scope           = azurerm_postgresql_flexible_server.postgres_server.id
}

resource "azurerm_api_management" "folex_api_management" {
  name                = "folex-apim"
  location            = data.azurerm_resource_group.db_resource_group.location
  resource_group_name = var.resource_group
  publisher_name      = "Folex"
  publisher_email     = var.admin_email

  sku_name = "Developer_1"
}