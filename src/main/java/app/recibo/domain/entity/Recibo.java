package app.recibo.domain.entity;

import app.administrado.domain.entity.Administrado;
import app.common.domain.valueobject.MoneyAbstraction;

public class Recibo {
	private long id;
	private String code;
        private String process;
	private MoneyAbstraction balance;
	private boolean isLocked;
	private Administrado administrado;
	
	public Recibo() {
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

        public boolean hasIdentity() {
            return this.id > 0 && !this.code.trim().equals("");
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public MoneyAbstraction getBalance() {
            return this.balance;
        }

        public void setBalance(MoneyAbstraction balance) {
            this.balance = balance;
        }

        public boolean getIsLocked() {
            return isLocked;
        }

        public void setIsLocked(boolean isLocked) {
            this.isLocked = isLocked;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
     
        public Administrado getAdministrado() {
            return administrado;
        }

        public void setAdministrado(Administrado administrado) {
            this.administrado = administrado;
        }

        public String getProcess() {
            return process;
        }

        public void setProcess(String process) {
            this.process = process;
        }
        
        
}
