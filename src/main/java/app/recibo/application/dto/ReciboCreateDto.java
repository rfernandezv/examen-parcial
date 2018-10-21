package app.recibo.application.dto;

import java.math.BigDecimal;

public class ReciboCreateDto {
	private long id;
	private String code;
        private String process;
	private BigDecimal balance;
	private String currency;
	private boolean locked = false;
	private long administradoId;
	
	public ReciboCreateDto() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }

        public String getProcess() {
            return process;
        }

        public void setProcess(String process) {
            this.process = process;
        }

        public long getAdministradoId() {
            return administradoId;
        }

        public void setAdministradoId(long administradoId) {
            this.administradoId = administradoId;
        }
}
