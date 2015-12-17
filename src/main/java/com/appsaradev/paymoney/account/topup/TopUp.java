package com.appsaradev.paymoney.account.topup;

public class TopUp {

	private MandatCash mandatCash;

	public MandatCash getMandatCash() {
		return mandatCash;
	}

	public ScratchCard getScratchCard() {
		return scratchCard;
	}

	public void setMandatCash(MandatCash mandatCash) {
		this.mandatCash = mandatCash;
	}

	public void setScratchCard(ScratchCard scratchCard) {
		this.scratchCard = scratchCard;
	}

	private ScratchCard scratchCard;
}
