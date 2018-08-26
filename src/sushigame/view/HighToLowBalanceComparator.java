package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class HighToLowBalanceComparator implements Comparator<Chef> {

	private String orderType;
	public HighToLowBalanceComparator(String orderType) {
		this.orderType = orderType;
	}
	
	@Override
	public int compare(Chef a, Chef b) {
		// We do b - a because we want largest to smallest
		switch (orderType) {
		case "balance":
			return (int) (Math.round(b.getBalance()*100.0) - 
					Math.round(a.getBalance()*100));
		case "spoiled":
			// we want smallest to largest for spoiled food amount
			return (int) (Math.round(a.getFoodSpoiledAmount()*100.0) - 
					Math.round(b.getFoodSpoiledAmount()*100));
		case "consumed":
			return (int) (Math.round(b.getFoodConsumedAmount()*100.0) - 
					Math.round(a.getFoodConsumedAmount()*100));
		default:
			return (int) (Math.round(b.getBalance()*100.0) - 
					Math.round(a.getBalance()*100));
		}
		
	}			
}
