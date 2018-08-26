package sushigame.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import comp401.sushi.AvocadoPortion;
import comp401.sushi.CrabPortion;
import comp401.sushi.EelPortion;
import comp401.sushi.IngredientPortion;
import comp401.sushi.Nigiri;
import comp401.sushi.RicePortion;
import comp401.sushi.Roll;
import comp401.sushi.SalmonPortion;
import comp401.sushi.Sashimi;
import comp401.sushi.SeaweedPortion;
import comp401.sushi.ShrimpPortion;
import comp401.sushi.Sushi;
import comp401.sushi.TunaPortion;

public class PlayerChefView extends JPanel implements ActionListener{

	private List<ChefViewListener> listeners;
	private Sushi player_sushi;
	private JComboBox sashimi_choices;
	private JComboBox nigiri_choices;
	private JLabel[] roll_choices;
	private JComboBox[] roll_amounts;
	private JComboBox position_choices;
	private JComboBox color_choices;
	private JComboBox gold_prices;
	private JLabel gold_label;
	private int belt_size;
	private IngredientPortion[] roll_portion;
	
	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new BorderLayout());
		JLabel title = new JLabel("<html><h1 style='font-family: \"Arial Black\", Gadget, sans-serif; "
				+ "font-size: 30px; color: rgb(255, 179, 153)'>Be a Sushi Chef!</h1>",SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.BOTTOM);
		add(title, BorderLayout.NORTH);
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(2,1));		
		
		JPanel upper_panel = new JPanel();
		upper_panel.setLayout(new GridLayout(4,1));
		
		// color_panel
		JPanel color_panel = new JPanel();
		JLabel color_label = new JLabel("<html><h2 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>Choose the color of the plate: </h2>");
		color_panel.add(color_label);
		String[] colors = {"NUll", "red: $1", "green: 2$", "blue: $4", "gold: greater than $5"};
		color_choices = new JComboBox<String>(colors);
		color_choices.addActionListener(this);
		color_panel.add(color_choices);
		String[] gold_price = {"5", "6", "7", "8", "9", "10"};
		gold_label = new JLabel("<html><h2 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>Choose the price of gold plate ($): </h2>");
		gold_label.setVisible(false);
		gold_prices = new JComboBox<String>(gold_price);
		gold_prices.addActionListener(this);
		gold_prices.setVisible(false);		
		color_panel.add(gold_label);
		color_panel.add(gold_prices);		
		upper_panel.add(color_panel);
		
		// position_panel
		JPanel position_panel = new JPanel();
		JLabel position_text = new JLabel("<html><h2 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>Choose the position you want to place: </h2>");
		position_panel.add(position_text);
		
		String[] positions = new String[20];		
		for (int i = 0; i < 20; i++) {
			positions[i] = ""+i;
		}
		position_choices = new JComboBox<String>(positions);	
		position_choices.addActionListener(this);
		position_panel.add(position_choices);
		upper_panel.add(position_panel);
		
		
		// sashimi_panel
		JPanel sashimi_panel = new JPanel();
		String[] sashimi_nigiri_type = {"NULL", "TUNA", "SALMON", "EEL", "CRAB", "SHRIMP"};
		JLabel sashimi_choice = new JLabel("<html><h2 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>Choose the Sashimi type: </h2>");
		sashimi_panel.add(sashimi_choice);
		sashimi_choices = new JComboBox<String>(sashimi_nigiri_type);
		sashimi_choices.addActionListener(this);
		sashimi_choices.setActionCommand("sashimi");
		sashimi_panel.add(sashimi_choices);
		upper_panel.add(sashimi_panel);
		
		// nigiri_panel
		JPanel nigiri_panel = new JPanel();
		JLabel nigiri_choice = new JLabel("<html><h2 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>Choose the Nigiri type: </h2>");
		nigiri_panel.add(nigiri_choice, BorderLayout.NORTH);
		nigiri_choices = new JComboBox<String>(sashimi_nigiri_type);
		nigiri_choices.addActionListener(this);
		nigiri_choices.setActionCommand("nigiri");
		nigiri_panel.add(nigiri_choices);
		upper_panel.add(nigiri_panel);
		
		content.add(upper_panel);
		JPanel lower_panel = new JPanel();
		lower_panel.setLayout(new BorderLayout());
		
		
		JPanel roll_panel = new JPanel();
		roll_panel.setLayout(new BorderLayout());
		JLabel roll_label = new JLabel("<html><h2 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>Customize your own Roll: </h2>", SwingConstants.CENTER);
		roll_panel.add(roll_label, BorderLayout.NORTH);
		JPanel roll_subpanel = new JPanel();
		roll_subpanel.setLayout(new GridLayout(4,4));
		String[] roll_ingredients = {"avocado", "crab", "eel", "rice", "salmon", "seaweed", "shrimp", "tuna"};
		String[] roll_ingredient_amount = new String[16];
		for (int i = 0; i < 16; i++) {
			roll_ingredient_amount[i] = ""+(((double)i)/10);
		}
		roll_choices = new JLabel[8];
		roll_amounts = new JComboBox[8];
		for (int i = 0; i < 8; i++) {
			roll_choices[i] = new JLabel();
			roll_choices[i].setText("<html><h3 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>" + roll_ingredients[i] + "</h3>");			
			roll_amounts[i] = new JComboBox<String>(roll_ingredient_amount);
			roll_amounts[i].addActionListener(this);
			roll_amounts[i].setActionCommand("roll");
			roll_subpanel.add(roll_amounts[i]);
			roll_subpanel.add(roll_choices[i]);
		}
		roll_panel.add(roll_subpanel, BorderLayout.CENTER);
		lower_panel.add(roll_panel, BorderLayout.CENTER);
		
		// place button panel
		JPanel btn_panel = new JPanel();		
		JButton clear_btn = new JButton();
		clear_btn.setActionCommand("clear");
		clear_btn.addActionListener(this);
		clear_btn.setText("Clear");
		btn_panel.add(clear_btn);
		JButton create_btn = new JButton();
		create_btn.setActionCommand("create");
		create_btn.setText("Go Place it!");
		create_btn.addActionListener(this);
		btn_panel.add(create_btn);
		lower_panel.add(btn_panel, BorderLayout.SOUTH);
		
		content.add(lower_panel);
		add(content);
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		// position choose
		int position;
		position = Integer.parseInt(position_choices.getSelectedItem().toString());
		
		// gold plate choose, gold price shows
		if (color_choices.getSelectedItem().toString().equals("gold: greater than $5")) {
			gold_prices.setVisible(true);			
			gold_label.setVisible(true);
		} else {
			gold_prices.setVisible(false);
			gold_label.setVisible(false);
		}
		
		
		// sashimi, nigiri, and roll choose and switch
		boolean roll_selected = false;
		for (int i = 0; i < 8; i++) {
			if (!roll_amounts[i].getSelectedItem().toString().equals("0.0")) {
				roll_selected = true;
			}
		}
		switch(e.getActionCommand()) {
		case "sashimi":
			if (sashimi_choices.getSelectedItem().toString() != "NULL") {
				nigiri_choices.setEnabled(false);
				for (int i = 0; i < 8; i++) {
					roll_amounts[i].setEnabled(false);
				}
			} else {
				sashimi_choices.setEnabled(true);
				nigiri_choices.setEnabled(true);
				for (int i = 0; i < 8; i++) {
					roll_amounts[i].setEnabled(true);
				}
			}
			break;
		case "nigiri":
			if (nigiri_choices.getSelectedItem().toString() != "NULL") {
				sashimi_choices.setEnabled(false);
				for (int i = 0; i < 8; i++) {
					roll_amounts[i].setEnabled(false);
				}
			} else {
				sashimi_choices.setEnabled(true);
				nigiri_choices.setEnabled(true);
				for (int i = 0; i < 8; i++) {
					roll_amounts[i].setEnabled(true);
				}
			}
			break;
		case "roll":
			if (roll_selected == true) {
				nigiri_choices.setEnabled(false);
				sashimi_choices.setEnabled(false);
			} else {
				nigiri_choices.setEnabled(true);
				sashimi_choices.setEnabled(true);
			}
			break;
		}
		
		// roll portion created 
		int ingredient_count = 0;
		for (int i = 0; i < 8; i++) {
			if (!roll_amounts[i].getSelectedItem().toString().equals("0.0")) {
				ingredient_count++;
			}
		}
		roll_portion = new IngredientPortion[ingredient_count];
		int j = 0;
		for (int i = 0; i < 8; i++) {
			if (!roll_amounts[i].getSelectedItem().toString().equals("0.0")) {
				switch (roll_choices[i].getText()) {
				case "<html><h3 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>avocado</h3>":
					roll_portion[j] = new AvocadoPortion(
							Double.parseDouble(roll_amounts[i].getSelectedItem().toString()));
					j++;
					break;
				case "<html><h3 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>crab</h3>":
					roll_portion[j] = new CrabPortion(
							Double.parseDouble(roll_amounts[i].getSelectedItem().toString()));
					j++;
					break;
				case "<html><h3 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>eel</h3>":
					roll_portion[j] = new EelPortion(
							Double.parseDouble(roll_amounts[i].getSelectedItem().toString()));
					j++;
					break;
				case "<html><h3 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>rice</h3>":
					roll_portion[j] = new RicePortion(
							Double.parseDouble(roll_amounts[i].getSelectedItem().toString()));
					j++;
					break;
				case "<html><h3 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>salmon</h3>":
					roll_portion[j] = new SalmonPortion(
							Double.parseDouble(roll_amounts[i].getSelectedItem().toString()));
					j++;
					break;
				case "<html><h3 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>seaweed</h3>":
					roll_portion[j] = new SeaweedPortion(
							Double.parseDouble(roll_amounts[i].getSelectedItem().toString()));
					j++;
					break;
				case "<html><h3 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>shrimp</h3>":
					roll_portion[j] = new ShrimpPortion(
							Double.parseDouble(roll_amounts[i].getSelectedItem().toString()));
					j++;
					break;
				case "<html><h3 style='font-family:\"Comic Sans MS\", cursive, sans-serif'>tuna</h3>":
					roll_portion[j] = new TunaPortion(
							Double.parseDouble(roll_amounts[i].getSelectedItem().toString()));
					j++;
					break;
				}
			}

		}
		player_sushi = new Roll("Player Roll", roll_portion);
		
		// sashimi
		switch (sashimi_choices.getSelectedItem().toString()){
		case "CRAB":
			player_sushi = new Sashimi(Sashimi.SashimiType.CRAB);
			break;
		case "TUNA":
			player_sushi = new Sashimi(Sashimi.SashimiType.TUNA);
			break;
		case "SALMON":
			player_sushi = new Sashimi(Sashimi.SashimiType.SALMON);
			break;
		case "EEL":
			player_sushi = new Sashimi(Sashimi.SashimiType.EEL);
			break;
		case "SHRIMP":
			player_sushi = new Sashimi(Sashimi.SashimiType.SHRIMP);
			break;
		}
		
		// nigiri
		switch (nigiri_choices.getSelectedItem().toString()){
		case "CRAB":
			player_sushi = new Nigiri(Nigiri.NigiriType.CRAB);
			break;
		case "TUNA":
			player_sushi = new Nigiri(Nigiri.NigiriType.TUNA);
			break;
		case "SALMON":
			player_sushi = new Nigiri(Nigiri.NigiriType.SALMON);
			break;
		case "EEL":
			player_sushi = new Nigiri(Nigiri.NigiriType.EEL);
			break;
		case "SHRIMP":
			player_sushi = new Nigiri(Nigiri.NigiriType.SHRIMP);
			
			break;
		}

		// clear the choices
		if (e.getActionCommand().equals("clear")) {
			color_choices.setSelectedIndex(0);
			sashimi_choices.setSelectedIndex(0);
			nigiri_choices.setSelectedIndex(0);
			position_choices.setSelectedIndex(0);
			for (int i = 0; i < 8; i++) {
				roll_amounts[i].setSelectedIndex(0);
			}
		}
		
		// create plate on the belt
		if (e.getActionCommand().equals("create")) {

			boolean isNull = false;
			if (player_sushi.getIngredients().length == 0) isNull = true;
			switch (color_choices.getSelectedItem().toString()) {
			case "blue: $4":
				if (!isNull) {
					makeBluePlateRequest(player_sushi,position);
				} else {
					JOptionPane.showMessageDialog(null, "Oops! You created an empty sushi!");
				}	
				color_choices.setSelectedIndex(0);
				sashimi_choices.setSelectedIndex(0);
				nigiri_choices.setSelectedIndex(0);
				position_choices.setSelectedIndex(0);
				break;
			case "red: $1":
				if (!isNull) {
					makeRedPlateRequest(player_sushi,position);
				} else {
					JOptionPane.showMessageDialog(null, "Oops! You created an empty sushi!");
				}					
				color_choices.setSelectedIndex(0);
				sashimi_choices.setSelectedIndex(0);
				nigiri_choices.setSelectedIndex(0);
				position_choices.setSelectedIndex(0);
				break;
			case "green: 2$":
				if (!isNull) {
					makeGreenPlateRequest(player_sushi,position);
				} else {
					JOptionPane.showMessageDialog(null, "Oops! You created an empty sushi!");
				}	
				color_choices.setSelectedIndex(0);
				sashimi_choices.setSelectedIndex(0);
				nigiri_choices.setSelectedIndex(0);
				position_choices.setSelectedIndex(0);
				break;
			case "gold: greater than $5":	
				double gold_price = Double.parseDouble(gold_prices.getSelectedItem().toString());
				if (!isNull) {
					makeGoldPlateRequest(player_sushi, position, gold_price);
				} else {
					JOptionPane.showMessageDialog(null, "Oops! You created an empty sushi!");
				}					
				gold_prices.setVisible(false);
				gold_label.setVisible(false);
				color_choices.setSelectedIndex(0);
				sashimi_choices.setSelectedIndex(0);
				nigiri_choices.setSelectedIndex(0);
				position_choices.setSelectedIndex(0);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Sorry, you should select a plate");
			}
			for (int i = 0; i < 8; i++) {
				roll_amounts[i].setSelectedIndex(0);
			}
		}
	}

}
