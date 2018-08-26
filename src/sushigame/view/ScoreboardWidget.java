package sushigame.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display;
	private JButton balanceOrder;
	private JButton spoiledFoodAmountOrder;
	private JButton consumedFoodAmountOrder;
	private String orderType;
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		orderType = "";
		
		this.setLayout(new BorderLayout());
		JLabel title = new JLabel("<html><h1 style='font-family: \"Arial Black\", Gadget, sans-serif; "
				+ "font-size: 30px; color: rgb(111,155,51)'>Sushi Kingdom</h1>",SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.BOTTOM);
		add(title, BorderLayout.NORTH);
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(2,1));
		
		// the 1st row of content: the buttons and score board
		JPanel upperPartOfContent = new JPanel();
		upperPartOfContent.setLayout(new BorderLayout());

		JPanel centerPart = new JPanel();
		centerPart.setLayout(new GridLayout(1,3)); // use Jscroll pane
		balanceOrder = new JButton("Balance");
		balanceOrder.setActionCommand("balance");
		balanceOrder.addActionListener(this);
		centerPart.add(balanceOrder);
		
		spoiledFoodAmountOrder = new JButton("Spoiled Amount");
		spoiledFoodAmountOrder.setActionCommand("spoiled");
		spoiledFoodAmountOrder.addActionListener(this);
		centerPart.add(spoiledFoodAmountOrder);
		
		consumedFoodAmountOrder = new JButton("Consumed Amount");
		consumedFoodAmountOrder.setActionCommand("consumed");
		consumedFoodAmountOrder.addActionListener(this);
		centerPart.add(consumedFoodAmountOrder);
		upperPartOfContent.add(centerPart, BorderLayout.PAGE_END);
		
		// right part of the first row of content
		display = new JLabel("", SwingConstants.CENTER);
		upperPartOfContent.add(display, BorderLayout.PAGE_START);
		
		content.add(upperPartOfContent);
		
		// the lower part of the content which is an image		
		JPanel lowerPartOfContent = new JPanel();
		ImageIcon icon = createImageIcon("sushi_backup.jpg","label image"); 		
		JLabel img = new JLabel();
		img.setOpaque(true);
		img.setIcon(icon);
		img.setVerticalAlignment(SwingConstants.CENTER);
		lowerPartOfContent.add(img, BorderLayout.CENTER);
		
		content.add(lowerPartOfContent);
		
		add(content, BorderLayout.CENTER);
		
	}

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	private String makeScoreboardHTML(String orderType) {
		String sb_html = "<html><head>\n" + 
				"  <meta charset=\"utf-8\">\n" + 
				"<style>\n" + 
				"table {\n" + 
				"    font-family: arial, sans-serif;\n" + 
				"    border-collapse: collapse;\n" + 
				"    width: 100%;\n" + 
				"}\n" + 
				"\n" + 
				"td, th {\n" + 
				"    border: 1px solid gray;\n" + 
				"    text-align: left;\n" + 
				"    padding: 8px;\n" + 
				"}\n" + 
				"\n" + 
				"tr:nth-child(even) {\n" + 
				"    background-color: gray;\n" + 
				"}\n" + 
				"</style>" +
				"</head>";
		sb_html += "<body><h1 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>Scores Here</h1>"
				+ "<table>\n" + 
				"  <tr>\n" + 
				"    <th>Chef Name</th>\n" + 
				"    <th>Amount ($ or Oz)</th>\n" + 
				"  </tr>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowBalanceComparator(orderType));
		
		switch (orderType) {
		case "balance":
			for (Chef c : chefs) {
				sb_html += "<tr><td>" + c.getName() + "</td>" + " <td>($ " + Math.round(c.getBalance()*100.0)/100.0 + ") </td></tr>";
			}
			break;
		case "spoiled":
			for (Chef c : chefs) {
				sb_html += "<tr><td>" + c.getName() + "</td>" + "  <td>(Ounces " + Math.round(c.getFoodSpoiledAmount()*100.0)/100.0 + ") </td></tr>";				
			}
			break;
		case "consumed":
			for (Chef c : chefs) {
				sb_html += "<tr><td>" + c.getName() + "</td>" + "  <td>(Ounces " + Math.round(c.getFoodConsumedAmount()*100.0)/100.0 + ") </td></tr>";				
			}
			break;
		default:
			for (Chef c : chefs) {
				sb_html += "<tr><td>" + c.getName() + "</td>" + " <td>($ " + Math.round(c.getBalance()*100.0)/100.0 + ") </td></tr>";
			}
			break;
		}
		return sb_html;
	}

	public void refresh() {		
		display.setText(makeScoreboardHTML(orderType));		
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getActionCommand()) {
		case "balance":
			orderType = "balance";
			display.setText(makeScoreboardHTML("balance"));
			break;
		case "spoiled":
			orderType = "spoiled";
			display.setText(makeScoreboardHTML("spoiled"));
			break;
		case "consumed":
			orderType = "consumed";
			display.setText(makeScoreboardHTML("consumed"));
			break;
		}
	}

}
