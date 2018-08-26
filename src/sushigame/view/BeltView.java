package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import comp401.sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver, ActionListener{

	private Belt belt;
//	private JLabel[] belt_labels;
	private PlateViewWidget[] belt_panels;

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(7,5));
		belt_panels = new PlateViewWidget[belt.getSize()];
		for (int i = 0; i < belt.getSize(); i++) {
			PlateViewWidget plate_panel = new PlateViewWidget(belt,i);
			plate_panel.setOpaque(true);
			belt_panels[i] = plate_panel;
		}
		int j = 0;
		int last_line = 12;
		for (int i = 0; i < 35; i++) {
			if ((i>4 && i<9)||(i>16 && i<20)||(i>24 && i<29)) {
				if (i==7) {
					JButton intro = new JButton("Intro");
					intro.addActionListener(this);
					intro.setActionCommand("intro");
					JPanel intro_panel = new JPanel();
					intro_panel.add(intro, BorderLayout.CENTER);
					intro_panel.setOpaque(true);
					add(intro_panel);
				} else {
					add(new JLabel());
				}
			} else if ((i==20)||(i==10)||(i==15)) {
				ImageIcon icon = createImageIcon("arrow.png","label image"); 		
				JLabel img = new JLabel(icon);
				img.setOpaque(true);
				add(img);
			} else if (i==30) {
				ImageIcon chef = createImageIcon("chef.png","label image"); 
				JLabel end = new JLabel(chef);
				end.setOpaque(true);
				add(end);
			} else if (i>30){
				add(belt_panels[i-last_line]);
				last_line +=2;				
			} else {
				add(belt_panels[j]);
				j++;
			}
		}
		refresh();
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
	
	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			PlateViewWidget plate_panel = belt_panels[i];	
			// just set the plate to this PlateViewWideget
			if (p == null) {
				plate_panel.setBackground(Color.GRAY);
//				plate_panel.setPlate(p);
				plate_panel.setPlate(null);
				plate_panel.getPlabel().setBackground(Color.GRAY);
				plate_panel.setPlateInfo("No Plate here!");
				plate_panel.setBorder(BorderFactory.createRaisedBevelBorder());
			} else {				
				plate_panel.setBorder(BorderFactory.createRaisedBevelBorder());	
				plate_panel.setOpaque(true);
				
				String sushiType = "<html><h3>Sushi Type: " + p.getContents().getName() + ".</h3><br>";
				String chefName = "<html<h3><i>" + p.getChef().getName() + " </i>made this sushi for you!</h3><br>";
				String plateAge = "<html><h3>This plate's age is: " + belt.getAgeOfPlateAtPosition(i) + ".</h3>";
				String ingredients = "<html><h3>Your sushi includes: ";
				if (p.getContents().getName().contains("Roll")) {					
					for (int j = 0; j < p.getContents().getIngredients().length; j++) {
						ingredients += p.getContents().getIngredients()[j].getName() + ", ";
					}					
				}
				if (p.getContents().getName().contains("Roll")) {
					plate_panel.setPlateInfo(sushiType + ingredients + chefName + plateAge); 
				} else {
					plate_panel.setPlateInfo(sushiType + chefName + plateAge); 
				}
				plate_panel.setPlate(p);
				switch (p.getColor()) {
				case RED:
					plate_panel.getPlabel().setBackground(new Color(231, 76, 60)); break;
				case GREEN:
					plate_panel.getPlabel().setBackground(new Color(3, 201, 169)); break;
				case BLUE:
					plate_panel.getPlabel().setBackground(new Color(65, 131, 215)); break;
				case GOLD:
					plate_panel.getPlabel().setBackground(new Color(244, 208, 63)); break;
				}
				
			}
		}
	}

	// specifically for intro button function
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String intro1 = "<html><h1>Introduction</h1><br>";
		String intro2 = "<html><h2>Hi, there! Welcom to Zhepu's sushi game. Here are some tips for your journey.</h2><br>";
		String intro3 = "<html><h3>1. You can click \"clear\" to clear your choices</h3><br>";
		String intro4 = "<html><h3>2. To get the information of plate, just click the plate! Then you can get the popup message!</h3>";
		if (arg0.getActionCommand().equals("intro")) {
			JOptionPane.showMessageDialog(null, intro1+intro2+intro3+intro4);
		}
	}

}
