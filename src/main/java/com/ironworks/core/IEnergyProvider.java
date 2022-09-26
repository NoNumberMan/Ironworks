package com.ironworks.core;

public interface IEnergyProvider {
	public boolean isEmpty();
	public int pullEnergy(final int maxAmount);
}
