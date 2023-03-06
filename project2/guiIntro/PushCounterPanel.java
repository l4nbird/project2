package guiIntro;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.Font;

public class PushCounterPanel extends JPanel{
	private int count;
	private Predictor predict;
	private JScrollPane scrollPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField removeField;
	private JTextField responseField;
	public PushCounterPanel() {
		//testing constructor and read file for predictor class
		predict = new Predictor("./guiIntro/weather.numeric.txt");
		//testing to string method for predictor
		//		System.out.println("Testing toString for predictor class: \n" + predict);

		//sets up background and layout
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(722, 436));

		//scroll pane for the main text area
		scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 183, 168, 243);
		add(scrollPane);

		//main text area that the file will output to
		JTextArea myData = new JTextArea();
		scrollPane.setViewportView(myData);
		myData.setText(predict.toString());

		//creates the label that contains the sun gif and adds the sun as an icon inside of it
		JLabel sunGif = new JLabel("");
		sunGif.setIcon(new ImageIcon(PushCounterPanel.class.getResource("/guiIntro/sun.gif")));
		sunGif.setBounds(441, 10, 256, 200);
		add(sunGif);

		//grabs an array of strings that represents the activities and make a dropdown menu out of them
		String[] acts = predict.getActivities();
		JComboBox actBox = new JComboBox();
		actBox.setModel(new DefaultComboBoxModel(acts));
		actBox.setBounds(207, 343, 201, 22);
		add(actBox);

		//a text field for output responses
		responseField = new JTextField();
		responseField.setText("Hello!");
		responseField.setBounds(472, 250, 192, 22);
		add(responseField);
		responseField.setColumns(10);
		
		//activity label for dropdown menu
		JLabel possibilitiesLabel = new JLabel("Possible Activities:");
		possibilitiesLabel.setBounds(250, 325, 153, 14);
		add(possibilitiesLabel);

		//radio button (of three) for the sunny outlook
		JRadioButton rdbtnSunny = new JRadioButton("sunny");
		buttonGroup.add(rdbtnSunny);
		rdbtnSunny.setSelected(true);
		rdbtnSunny.setBounds(225, 204, 179, 23);
		add(rdbtnSunny);

		//radio button (of three) for the overcast outlook
		JRadioButton rdbtnOvercast = new JRadioButton("overcast");
		buttonGroup.add(rdbtnOvercast);
		rdbtnOvercast.setBounds(224, 236, 180, 23);
		add(rdbtnOvercast);

		//radio button (of three) for the rainy outlook
		JRadioButton rdbtnRaining = new JRadioButton("raining");
		buttonGroup.add(rdbtnRaining);
		rdbtnRaining.setBounds(225, 267, 179, 23);
		add(rdbtnRaining);

		//label for the outlook radio buttons
		JLabel outlookLabel = new JLabel("Current Outlook:");
		outlookLabel.setBounds(259, 183, 123, 14);
		add(outlookLabel);
		
		//checkbox for whether it is windy or not
		JCheckBox windyCheckBox = new JCheckBox("Yes");
		windyCheckBox.setBounds(274, 389, 97, 23);
		add(windyCheckBox);
		
		//humidity slider using percent valeus
		JSlider humidSlider = new JSlider();
		humidSlider.setMinorTickSpacing(1);
		humidSlider.setFont(new Font("Tahoma", Font.PLAIN, 9));
		humidSlider.setPaintLabels(true);
		humidSlider.setPaintTicks(true);
		humidSlider.setMajorTickSpacing(5);
		humidSlider.setBounds(29, 29, 375, 44);
		add(humidSlider);
		
		//slider for temperature (up to 130 degrees since people shouldn't be doing things above 130 degrees||down to -10 degrees)
		JSlider tempSlider = new JSlider();
		tempSlider.setMinimum(-10);
		tempSlider.setMaximum(130);
		tempSlider.setFont(new Font("Tahoma", Font.PLAIN, 9));
		tempSlider.setMajorTickSpacing(10);
		tempSlider.setMinorTickSpacing(1);
		tempSlider.setPaintLabels(true);
		tempSlider.setPaintTicks(true);
		tempSlider.setBounds(29, 108, 375, 44);
		add(tempSlider);
		
		//main predictor button: takes the input in the panel (excluding the activity) and predicts what activity should be done and outputs it
		//to the page
		JButton predictorButton = new JButton("Predict");
		predictorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentActivity = (String)actBox.getSelectedItem();
				String currentOutlook = "sunny";
				if (rdbtnRaining.isSelected())
					currentOutlook = "rainy";
				else if (rdbtnOvercast.isSelected())
					currentOutlook = "overcast";
				String isWindy = "FALSE";
				if (windyCheckBox.isSelected()) {
					isWindy = "TRUE";
				}
				int humidity;
				humidity = humidSlider.getValue();
				int temperature;
				temperature = tempSlider.getValue();
				
				Instance updatingInstance = new Instance(currentOutlook, humidity, temperature, isWindy); //this instance will be added without an activity to test if we can update the 
				//instance to include an activity
				String prediction = predict.using(updatingInstance); //this outputs a prediction to the output text field
				//System.out.println(prediction);
				responseField.setText(prediction);

			}
		});
		predictorButton.setBounds(472, 389, 192, 23);
		add(predictorButton);
		
		//label for the wind button
		JLabel windButtonLabel = new JLabel("Windy?");
		windButtonLabel.setBounds(230, 393, 46, 14);
		add(windButtonLabel);

		//label for the humidity slider
		JLabel humidSliderLabel = new JLabel("Humidity");
		humidSliderLabel.setBounds(29, 10, 64, 14);
		add(humidSliderLabel);
		
		//separator for the info and the functions/image
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(420, 10, 15, 416);
		add(separator);
		
		
		
		//label for temperature slider
		JLabel tempSliderLabel = new JLabel("Temperature");
		tempSliderLabel.setBounds(29, 87, 89, 14);
		add(tempSliderLabel);
		
		//button that takes all of the selected info on the panel and adds it as an activity to the Predictor class
		//also updates the text in the text area and outputs a success message
		JButton addWAct = new JButton("Add Activity");
		addWAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentActivity = (String)actBox.getSelectedItem();
				String currentOutlook = "sunny";
				if (rdbtnRaining.isSelected())
					currentOutlook = "rainy";
				else if (rdbtnOvercast.isSelected())
					currentOutlook = "overcast";
				String isWindy = "FALSE";
				if (windyCheckBox.isSelected()) {
					isWindy = "TRUE";
				}
				int humidity;
				humidity = humidSlider.getValue();
				int temperature;
				temperature = tempSlider.getValue();
				String activity = String.valueOf(actBox.getSelectedItem());
				
				Instance updatingInstance = new Instance(currentOutlook, humidity, temperature, isWindy, activity); //this instance will be added without an activity to test if we can update the 
				//instance to include an activity
				predict.add(updatingInstance); //this should add the above instance to the dataset
				//System.out.println(updatingInstance.toString());
				responseField.setText("Activity added!");
				myData.setText(predict.toString());
			}
		});
		addWAct.setBounds(472, 363, 192, 23);
		add(addWAct);
		
		//entry field for index of instance to remove
		removeField = new JTextField();
		removeField.setBounds(472, 337, 89, 22);
		add(removeField);
		removeField.setColumns(10);
		
		//button that takes input from a remove text box and removes it from the predictor class
		//also includes type checking and bounds checking
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int x = Integer.parseInt(removeField.getText());
					if (x > predict.getSize() || x < 1) {
						System.out.println("Invalid input");
						responseField.setText("Error: Invalid Index");
					} else {
						predict.removeInst(x - 1);
						System.out.println("removed: Index " + x);
						myData.setText(predict.toString());
						responseField.setText("Instance Removed!");
					}
					
				} catch(Exception err) {
					System.out.println("Error encountered." + err);
					responseField.setText("Error: Invalid Input");
				}

			}
		});
		removeButton.setBounds(571, 337, 93, 22);
		add(removeButton);
		
		//label for remove input box
		JLabel removeLabel = new JLabel("Enter Index to Remove:");
		removeLabel.setBounds(472, 315, 192, 18);
		add(removeLabel);
		
		
		
		
	}
}
