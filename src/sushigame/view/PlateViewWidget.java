package sushigame.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import comp401.sushi.Plate;
import sushigame.model.Belt;

public class PlateViewWidget extends JPanel implements MouseListener{

	private Belt belt;
	private int position;
	private String plateInfo;
	private JLabel plabel;
	private Plate plate;

	public PlateViewWidget(Belt belt, int position) {
		// TODO Auto-generated constructor stub
		this.belt = belt;
		this.position = position;
		this.setOpaque(true);
		this.plate = belt.getPlateAtPosition(position);
		ImageIcon icon = createImageIcon("plate5.png","label image"); 		
		plabel = new JLabel(icon);
		plabel.setOpaque(true);
		plabel.addMouseListener(this);
		
		add(plabel);
	}
	
	public Plate getPlate() {
		return plate;
	}

	public void setPlate(Plate plate) {
		this.plate = plate;
	}

	public JLabel getPlabel() {
		return plabel;
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
	
	public String getPlateInfo() {
		return plateInfo;
	}
	
	public void setPlateInfo(String plateInfo) {
		this.plateInfo = plateInfo;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, this.getPlateInfo());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
