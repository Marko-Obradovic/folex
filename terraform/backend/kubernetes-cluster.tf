resource "azurerm_kubernetes_cluster" "cluster" {
  name                = "folex-cluster"
  location            = data.azurerm_resource_group.tf_resource_group.location
  resource_group_name = data.azurerm_resource_group.tf_resource_group.name
  dns_prefix          = "folex-cluster"

  default_node_pool {
    name       = "default"
    node_count = 2
    vm_size    = "Standard_D2_v2"
    upgrade_settings {
    drain_timeout_in_minutes      = 0
    max_surge                     = "10%"
    node_soak_duration_in_minutes = 0
            }
  }

  azure_active_directory_role_based_access_control {
    admin_group_object_ids = var.object_ids
    azure_rbac_enabled = true
  }

  identity {
    type = "SystemAssigned"
  }

  oidc_issuer_enabled = true
}

# resource "azurerm_role_assignment" "node_rg_access_1" {
#   principal_id   = var.object_id_1
#   role_definition_name = "Contributor"
#   scope          = "/subscriptions/${var.subscription_id}/resourceGroups/${azurerm_kubernetes_cluster.cluster.node_resource_group}"
# }

# resource "azurerm_role_assignment" "node_rg_access_2" {
#   principal_id   = var.object_id_2
#   role_definition_name = "Contributor"
#   scope          = "/subscriptions/${var.subscription_id}/resourceGroups/${azurerm_kubernetes_cluster.cluster.node_resource_group}"
# }

# resource "azurerm_role_assignment" "node_rg_access_3" {
#   principal_id   = var.object_id_3
#   role_definition_name = "Contributor"
#   scope          = "/subscriptions/${var.subscription_id}/resourceGroups/${azurerm_kubernetes_cluster.cluster.node_resource_group}"
# }

# resource "azurerm_role_assignment" "node_rg_access_4" {
#   principal_id   = var.object_id_4
#   role_definition_name = "Contributor"
#   scope          = "/subscriptions/${var.subscription_id}/resourceGroups/${azurerm_kubernetes_cluster.cluster.node_resource_group}"
# }

resource "azurerm_role_assignment" "aks_acr_pull" {
  scope                = azurerm_container_registry.acr.id
  role_definition_name = "AcrPull"
  principal_id         = azurerm_kubernetes_cluster.cluster.kubelet_identity[0].object_id
  skip_service_principal_aad_check = true
}
