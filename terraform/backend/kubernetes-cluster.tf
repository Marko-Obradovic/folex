resource "azurerm_kubernetes_cluster" "cluster" {
  name                = "folex-cluster"
  location            = data.azurerm_resource_group.tf_resource_group.location
  resource_group_name = data.azurerm_resource_group.tf_resource_group.name
  dns_prefix          = "folex-cluster"

  default_node_pool {
    name       = "default"
    node_count = 2
    vm_size    = "Standard_D2_v2"
  }

  identity {
    type = "SystemAssigned"
  }

}

resource "azurerm_role_assignment" "aks_acr_pull" {
  scope                = azurerm_container_registry.acr.id
  role_definition_name = "AcrPull"
  principal_id         = azurerm_kubernetes_cluster.cluster.kubelet_identity[0].object_id
  skip_service_principal_aad_check = true
}
