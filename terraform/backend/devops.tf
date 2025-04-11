# data "azuredevops_project" "project" {
#   name        = var.project_name
# }

resource "azurerm_user_assigned_identity" "devops_identity" {
  name                = "folex-identity-devops"
  resource_group_name = data.azurerm_resource_group.tf_resource_group.name
  location            = data.azurerm_resource_group.tf_resource_group.location
}

resource "azurerm_role_assignment" "devops_access" {
  principal_id         = azurerm_user_assigned_identity.devops_identity.principal_id
  role_definition_name = "Contributor"
  scope                = data.azurerm_resource_group.tf_resource_group.id
}
