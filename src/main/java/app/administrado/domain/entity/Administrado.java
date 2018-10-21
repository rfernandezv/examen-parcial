package app.administrado.domain.entity;

import java.util.Set;

import app.recibo.domain.entity.Recibo;
import app.arma.domain.entity.Arma;
import java.util.HashSet;

public class Administrado {
    private long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private Boolean isActive;
    private Set<Recibo> recibos;
    private Set<Arma> armas;
    
    public Administrado() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        return String.format("%s, %s", this.lastName, this.firstName);
    }
    
    public String getIdentityDocument() {
		return identityDocument;
	}

	public void setIdentityDocument(String identityDocument) {
		this.identityDocument = identityDocument;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

        public Set<Recibo> getRecibos() {
            if(recibos == null){
                recibos = new HashSet<>();
            }
            return recibos;
        }

        public void setRecibos(Set<Recibo> recibos) {
            this.recibos = recibos;
        }

        public Set<Arma> getArmas() {
            if(armas == null){
                armas = new HashSet<>();
            }
            return armas;
        }

        public void setArmas(Set<Arma> armas) {
            this.armas = armas;
        }
}
