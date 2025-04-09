resource "azurerm_container_registry" "acr" {
  name                = var.container_registry
  resource_group_name = data.azurerm_resource_group.tf_resource_group.name
  location            = data.azurerm_resource_group.tf_resource_group.location
  sku                 = "Standard"
  admin_enabled       = false
}
