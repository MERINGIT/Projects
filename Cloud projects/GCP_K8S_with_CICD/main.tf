# main.tf

provider "google" {
  credentials = file("kubernetes-project-416718-30528a2920b6.json")
  project     = "kubernetes-project-416718"
  region      = "us-central1"
}

resource "google_container_cluster" "my_cluster" {
  name     = "my-cluster1"
  project     = "kubernetes-project-416718"
  location = "us-central1-c"
  initial_node_count   = 1
 
 # Node pool configurations
  node_config {
    machine_type = "e2-micro"
    disk_size_gb = 10
    image_type   = "COS_CONTAINERD"
    disk_type    = "pd-standard"
  }


}


