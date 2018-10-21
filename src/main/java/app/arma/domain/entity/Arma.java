package app.arma.domain.entity;

import app.administrado.domain.entity.Administrado;

public class Arma {
	private long id;
	private String serie;
	private String brand;
	private String model;
        private boolean isLocked;
	private Administrado administrado;
	
	public Arma() {
        }

        public void lock() {
            if (!this.isLocked) {
                this.isLocked = true;
            }
        }

        public void unLock() {
            if (this.isLocked) {
                this.isLocked = false;
            }
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public boolean getIsLocked() {
            return isLocked;
        }

        public void setIsLocked(boolean isLocked) {
            this.isLocked = isLocked;
        }

        public String getSerie() {
            return serie;
        }

        public void setSerie(String serie) {
            this.serie = serie;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Administrado getAdministrado() {
            return administrado;
        }

        public void setAdministrado(Administrado administrado) {
            this.administrado = administrado;
        }
}
