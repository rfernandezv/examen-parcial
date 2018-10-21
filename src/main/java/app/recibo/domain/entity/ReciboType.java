package app.recibo.domain.entity;

public enum ReciboType {
    NUEVO{
    public String toString() {
            return "NUEVO_REGISTRO_ARMA";
        }
    }, 
    RENOVACION{
    public String toString() {
            return "RENOVACION_REGISTRO_ARMA";
        }
    }, 
    CESE{
    public String toString() {
            return "CESE_REGISTRO_ARMA";
        }
    }
}
