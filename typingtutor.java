package gametypint;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class typingtutor extends JFrame implements ActionListener {

	private JLabel timing;
	private JLabel scoring;
	private JLabel wording;
	private JTextField textword;
	private JButton starting;
	private JButton stoping;

	private boolean running;
	private Timer timer = null;
	private int score = 0;
	private int timeremaining = 0;
	private String[] word = null;

	public typingtutor(String[] args) {
		this.word = args;
		GridLayout layout = new GridLayout(3, 2);

		super.setLayout(layout);
		Font font = new Font("sogeo script", 1, 150);

		timing = new JLabel(" TIMER");
		timing.setFont(font);
		super.add(timing);
		scoring = new JLabel(" SCORE");
		scoring.setFont(font);
		super.add(scoring);
		wording = new JLabel("");
		wording.setFont(font);
		super.add(wording);
		textword = new JTextField("text");
		textword.setFont(font);
		super.add(textword);
		starting = new JButton(" START");
		starting.setFont(font);
		starting.addActionListener(this);
		starting.setForeground(Color.blue);
		starting.setBackground(Color.lightGray);
		super.add(starting);
		stoping = new JButton(" STOP");
		stoping.setFont(font);
		super.add(stoping);
		stoping.addActionListener(this);
stoping.setForeground(Color.GREEN);
stoping.setBackground(Color.MAGENTA);
		super.setExtendedState(MAXIMIZED_BOTH);
		super.setTitle("typingtutor");
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setVisible(true);
		setupthegame();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == starting) {
			handlestart();

		} else if (e.getSource() == stoping) {
			handlestop();
		} else if (e.getSource() == timer) {
			handletimer();
		}
	}

	public void handlestart() {
		// JOptionPane.showMessageDialog(this, " startwas clicked");
		if (running == true) {
			timer.stop();
			running = false;
			starting.setText("resume");
			stoping.setEnabled(false);
			textword.setEnabled(false);

		} else {
			timer.start();
			running = true;
			starting.setText("pause");
			stoping.setEnabled(true);
			textword.setEnabled(true);

		}

	}

	private void handletimer() {
		timeremaining--;
		String actual, expected;
		actual = textword.getText();
		expected = wording.getText();
		if (expected.length() > 0 && actual.equals(expected)) {
			score++;
		}

		scoring.setText("scoring:" + score);
		if (timeremaining == -1) {
			handlestop();
		} else {
			timing.setText("timing: " + timeremaining);

			int ridx = (int) (Math.random() * word.length);
			wording.setText(word[ridx]);
			textword.setText("");
		}
	}

	public void handlestop() {
		timer.stop();
		int choice = JOptionPane.showConfirmDialog(this, "scoring" + score + ".continue?");
		if (choice == JOptionPane.YES_OPTION) {
			setupthegame();
		} else if (choice == JOptionPane.NO_OPTION) {
			super.dispose();

		} else {
			if (timeremaining == -1) {
				setupthegame();

			} else {
				timer.start();
			}
		}

	}

	private void setupthegame() {
		score = 0;
		timeremaining = 20;
		timer = new Timer(2000, this);
		running = false;
		starting.setText("start");
		timing.setText("timer:" + timeremaining);
		scoring.setText("scoring" + score);
		wording.setText("");
		stoping.setEnabled(false);
		textword.setEnabled(false);
		textword.setText("");

	}
}
