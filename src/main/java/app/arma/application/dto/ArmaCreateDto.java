package app.arma.application.dto;

public class ArmaCreateDto {
	private long id;
	private String serie;
	private String brand;
	private String model;
        private boolean locked = false;
	private long administradoId;
	
	public ArmaCreateDto() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
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

        public long getAdministradoId() {
            return administradoId;
        }

        public void setAdministradoId(long administradoId) {
            this.administradoId = administradoId;
        }
}
