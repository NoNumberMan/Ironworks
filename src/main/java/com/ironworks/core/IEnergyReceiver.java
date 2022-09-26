package com.ironworks.core;

public interface IEnergyReceiver {
	public int getCapacity();
	public int getCurrentCharge();
	public boolean isFull();
	public int pushEnergy(final int energy);
}
