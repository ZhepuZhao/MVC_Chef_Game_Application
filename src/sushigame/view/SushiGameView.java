package sushigame.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.SushiGameModel;

public class SushiGameView extends JPanel implements ActionListener, BeltObserver {

	private PlayerChefView player_chef_ui;
	private List<RotationRequestListener> rotation_request_listeners;
	private JLabel controller_messages;
	ScoreboardWidget scoreboard;
	
	public SushiGameView(SushiGameModel game_model) {
		setLayout(new GridLayout(1,3));
		
		scoreboard = new ScoreboardWidget(game_model);
		add(scoreboard);
				
		BeltView belt_view = new BeltView(game_model.getBelt());
		add(belt_view);
		
		player_chef_ui = new PlayerChefView(game_model.getBelt().getSize());
		add(player_chef_ui);
		
		JPanel bottom_panel = new JPanel();
		bottom_panel.setLayout(new BorderLayout());
		
		JButton rotate_button = new JButton("Rotate");
		rotate_button.setActionCommand("rotate");
		rotate_button.addActionListener(this);
		
		bottom_panel.add(rotate_button, BorderLayout.WEST);
		
		controller_messages = new JLabel("Controller messages.");		
		bottom_panel.add(controller_messages, BorderLayout.CENTER);
		
		scoreboard.add(bottom_panel, BorderLayout.SOUTH);
		
		rotation_request_listeners = new ArrayList<RotationRequestListener>();
		
		game_model.getBelt().registerBeltObserver(this);
	}
	
	public void registerPlayerChefListener(ChefViewListener cl) {
		player_chef_ui.registerChefListener(cl);
	}
	
	public void registerRotationRequestListener(RotationRequestListener rrl) {
		rotation_request_listeners.add(rrl);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("rotate")) {
			for (RotationRequestListener rrl : rotation_request_listeners) {
				rrl.handleRotationRequest();
			}
		}
	}
	
	public void setControllerMessage(String message) {
		controller_messages.setText(message);
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			controller_messages.setText("");
		}
	}
	
	public void refreshScoreboard() {
		scoreboard.refresh();
	}
}
