package app.recibo.domain.entity;

import app.administrado.domain.entity.Administrado;
import app.common.domain.valueobject.MoneyAbstraction;
import app.common.domain.valueobject.MoneyNull;

public class ReciboNull extends ReciboAbstraction{
	
	public ReciboNull() {
            this.code = "0";
            this.process = "0";
            this.isLocked = false;
            this.balance = new MoneyNull();
        }        
}
