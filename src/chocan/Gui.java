package chocan;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
public class Gui extends JFrame{

	private static String ENTER = "Enter";
	static JButton enterButton;
	public static JTextArea output;
	public static JTextField input;
	static JFrame frame;
	static JPanel panel;
	public static String testString = "test";
	static Terminal term = new Terminal();
	static JLabel nullLabel = null;
	private static JLabel labelG = new JLabel();


	public static void createFrame()
	{
		labelG.setText("abc");
		ButtonListener buttonListener = new ButtonListener();

		//Frame
		frame = new JFrame("ChocAn System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Text area
		//JLabel testLabel = new JLabel("Test");
		JButton buttonTest = new JButton("Test");
		panel = makeInputPanel(buttonTest);

		initFrame();
		frame.pack();
		frame.setLocationByPlatform(true);
		// Center of screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(true);
		input.requestFocus();
		frame.setSize(500, 500);
	}

	public static void main(String[] args) {
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		createFrame();
	}



	public static void OpenMTerminal () {
		JButton button1 = new JButton(new AbstractAction("Generate Summary Report") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String fileName = JOptionPane.showInputDialog("Enter file name: ");
					fileName.replaceAll("\\s+", "");
					GenerateReport.printSummaryReport(fileName);

				}
				catch(Exception ex){}
			}
		});


		JButton button2 = new JButton(new AbstractAction("Generate Individual Member Report") {
			int number;
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String memNum = JOptionPane.showInputDialog("Enter member number: ");
					GenerateReport.generateIndividualMember(Integer.parseInt(memNum));
				}
				catch(Exception ex){}
			}
		});

		JButton button3 = new JButton(new AbstractAction("Generate Individual Provider Report") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String proNum = JOptionPane.showInputDialog("Enter provider number: ");
					GenerateReport.generateIndividualProvider(Integer.parseInt(proNum));
				}
				catch(Exception ex) {}
			}
		});

		JButton btnGenAllProReports = new JButton(new AbstractAction("Generate All Provider Reports") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					GenerateReport.generateProvider();
				}
				catch(Exception ex) {}
			}
		});

		JButton btnGenAllMemReports = new JButton(new AbstractAction("Generate All Member Reports") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					GenerateReport.generateMember();
				}
				catch(Exception ex) {}
			}
		});

		JButton button4 = new JButton(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {initFrame();}
				catch(Exception ex) {}
			}
		});

		button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		button4.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGenAllProReports.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGenAllMemReports.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
		newPanel.add(button1);
		newPanel.add(button2);
		newPanel.add(button3);
		newPanel.add(btnGenAllMemReports);
		newPanel.add(btnGenAllProReports);
		newPanel.add(button4);

		setFrame(newPanel);
	}

	public static void OpenPTerminal () throws FileNotFoundException, IOException {
		ButtonListener buttonListener = new ButtonListener();

		JButton button1 = new JButton(new AbstractAction("Validate Member") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					labelG.setText("Enter member number : ");
					setFrame(makeValidatePanel(validateButton));
				}
				catch(Exception ex){}
			}
		});

		JButton button2 = new JButton(new AbstractAction("Bill ChocAn") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					labelG.setText("Enter member number : ");
					setFrame(makeValidatePanel(validateButton));
					//if()
					setFrame(makeBillChocAnInputPanel(submitBillButton));
				}
				catch(Exception ex){}
			}
		});

		JButton button3 = new JButton(new AbstractAction("Request Provider Directory") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {RequestProviderDirectory.requestDirectory();}
				catch(Exception ex) {}
			}
		});

		JButton button4 = new JButton(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {initFrame();}
				catch(Exception ex) {}
			}
		});

		button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		button4.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
		newPanel.add(button1);
		newPanel.add(button2);
		newPanel.add(button3);
		newPanel.add(button4);

		setFrame(newPanel);
	}

	public static void OpenOTerminal () throws FileNotFoundException, IOException {
		JButton button1 = new JButton(new AbstractAction("Manage Member") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					OpenManageMember();
				}
				catch(Exception ex){}
			}
		});


		JButton button2 = new JButton(new AbstractAction("Manage Provider") {
			int number;
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					OpenManageProvider();
				}
				catch(Exception ex){}
			}
		});

		JButton button3 = new JButton(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {initFrame();}
				catch(Exception ex) {}
			}
		});

		button1.setAlignmentX(Component.CENTER_ALIGNMENT);		
		button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		button3.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
		newPanel.add(button1);
		newPanel.add(button2);
		newPanel.add(button3);

		setFrame(newPanel);
	}

	public static void OpenManageMember () throws FileNotFoundException, IOException {
		JButton button1 = new JButton(new AbstractAction("Add Member") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					setFrame(makeAddMemberInputPanel(saveAddMemberButton));
				}
				catch(Exception ex){}
			}
		});


		JButton button2 = new JButton(new AbstractAction("Update Member") {
			int number;
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					setFrame(makeUpdateMemberInputPanel(updateMemberButton));
				}
				catch(Exception ex){}
			}
		});

		JButton button3 = new JButton(new AbstractAction("Delete Member") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					setFrame(makeDeleteMemberInputPanel(deleteMemberButton));
				}
				catch(Exception ex) {}
			}
		});
		
		JButton btnExit = new JButton(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {OpenOTerminal();}
				catch(Exception ex) {}
			}
		});

		button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
		newPanel.add(button1);
		newPanel.add(button2);
		newPanel.add(button3);
		newPanel.add(btnExit);

		setFrame(newPanel);
	}

	public static void OpenManageProvider () throws FileNotFoundException, IOException {
		JButton button1 = new JButton(new AbstractAction("Add Provider") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					setFrame(makeAddProviderInputPanel(saveAddProviderButton));
				}
				catch(Exception ex){}
			}
		});


		JButton button2 = new JButton(new AbstractAction("Update Provider") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					setFrame(makeUpdateMemberInputPanel(updateMemberButton));
				}
				catch(Exception ex){}
			}
		});

		JButton button3 = new JButton(new AbstractAction("Delete Provider") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setFrame(makeDeleteProviderInputPanel(deleteProviderButton));
				}
				catch(Exception ex) {}
			}
		});
		
		JButton btnExit = new JButton(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {initFrame();}
				catch(Exception ex) {}
			}
		});

		button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
		newPanel.add(button1);
		newPanel.add(button2);
		newPanel.add(button3);
		newPanel.add(btnExit);

		setFrame(newPanel);
	}

	public static class ButtonListener implements ActionListener
	{
		public void actionPerformed(final ActionEvent ev)
		{
			if (!input.getText().trim().equals(""))
			{
				String cmd = ev.getActionCommand();
				if (ENTER.equals(cmd))
				{
//                  output.append(input.getText());
//                  if (input.getText().trim().equals(testString))
//                	  output.append(" = " + testString);
//                  else
//                	  output.append(" != " + testString);
//                  output.append("\n");


				}

			}
//          input.setText("");
//          input.requestFocus();
		}
	}

	public static void setFrame(JPanel panelTemp)
	{
		JPanel contentPane = (JPanel) frame.getContentPane();

		contentPane.removeAll();
		contentPane.add(BorderLayout.NORTH, panelTemp);
		contentPane.revalidate();
		contentPane.repaint();
	}

	public static void initFrame()
	{
		JPanel contentPane = (JPanel) frame.getContentPane();

		//Terminal Buttons
		JButton manTerminal = new JButton(new AbstractAction("Manager Terminal") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{OpenMTerminal();}
				catch(Exception ex){}
			}
		});

		JButton provTerminal = new JButton(new AbstractAction("Provider Terminal") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {OpenPTerminal();}
				catch(Exception ex) {}
			}
		});
		JButton opTerminal = new JButton(new AbstractAction("Operator Terminal") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {OpenOTerminal();}
				catch(Exception ex) {}
			}
		});

		manTerminal.setAlignmentX(Component.CENTER_ALIGNMENT);		
		Dimension size = manTerminal.getPreferredSize();
		size.setSize(100, 100); 		
		manTerminal.setPreferredSize(size);
		
		provTerminal.setAlignmentX(Component.CENTER_ALIGNMENT);
		opTerminal.setAlignmentX(Component.CENTER_ALIGNMENT);

		contentPane.removeAll();		

		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
		newPanel.add(manTerminal);
		newPanel.add(provTerminal);
		newPanel.add(opTerminal);
				
		setFrame(newPanel);;
	}

	static JButton validateButton = new JButton(new AbstractAction("Validate") {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				MemberAccounts ma = ValidateMember.validateMember(Integer.parseInt(input.getText()));
				if(ma.equals(null))
					JOptionPane.showMessageDialog(null,"Invalid number.");
				else if(!ma.getStatus())
					JOptionPane.showMessageDialog(null,"Suspended.");
				else if(ma.getStatus())
					JOptionPane.showMessageDialog(null,"Validated.");
			}
			catch(Exception ex){}
		}
	});

	static JButton submitBillButton = new JButton(new AbstractAction("Submit") {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				JPanel contentPane = panel;
				Component[] components = contentPane.getComponents();

				ArrayList<JTextField> list = new ArrayList<JTextField>();


				for (Component component : components) {
					if (component.getClass().equals(JTextField.class)) {
						list.add((JTextField)component);
					}
				}


				BillChocAn.billChocan(list.get(0).getText(), Integer.parseInt(list.get(1).getText()),Integer.parseInt(list.get(2).getText()), Integer.parseInt(list.get(3).getText()));
			}
			catch(Exception ex){}
		}
	});

	static JButton saveAddMemberButton = new JButton(new AbstractAction("Save") {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				JPanel contentPane = panel;
				Component[] components = contentPane.getComponents();

				ArrayList<JTextField> list = new ArrayList<JTextField>();


				for (Component component : components) {
					if (component.getClass().equals(JTextField.class)) {
						list.add((JTextField)component);
					}
				}

				 boolean flag = ManageAccounts.addMember(list.get(0).getText(),Integer.parseInt(list.get(1).getText()),list.get(2).getText(),list.get(3).getText(),list.get(4).getText(),Integer.parseInt(list.get(5).getText()));
				if(flag == true){
					JOptionPane.showMessageDialog(null, "Member saved successfully.");
				}
				else{
					JOptionPane.showMessageDialog(null, "Error. The member was not added.");
				}
			}
			catch(Exception ex){}
		}
	});

	static JButton saveAddProviderButton = new JButton(new AbstractAction("Save") {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				JPanel contentPane = panel;
				Component[] components = contentPane.getComponents();

				ArrayList<JTextField> list = new ArrayList<JTextField>();


				for (Component component : components) {
					if (component.getClass().equals(JTextField.class)) {
						list.add((JTextField)component);
					}
				}

				ManageAccounts.addProvider(list.get(0).getText(),Integer.parseInt(list.get(1).getText()),list.get(2).getText(),list.get(3).getText(),list.get(4).getText(),Integer.parseInt(list.get(5).getText()));

			}
			catch(Exception ex){}
		}
	});

	static JButton updateMemberButton = new JButton(new AbstractAction("Save") {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				JPanel contentPane = panel;
				Component[] components = contentPane.getComponents();

				ArrayList<JTextField> list = new ArrayList<JTextField>();


				for (Component component : components) {
					if (component.getClass().equals(JTextField.class)) {
						list.add((JTextField)component);
					}
				}

				ManageAccounts.updateMember(list.get(0).getText(),list.get(1).getText(),list.get(2).getText(),list.get(3).getText(),Integer.parseInt(list.get(4).getText()));

			}
			catch(Exception ex){}
		}
	});

	static JButton updateProviderButton = new JButton(new AbstractAction("Save") {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				JPanel contentPane = panel;
				Component[] components = contentPane.getComponents();

				ArrayList<JTextField> list = new ArrayList<JTextField>();


				for (Component component : components) {
					if (component.getClass().equals(JTextField.class)) {
						list.add((JTextField)component);
					}
				}

				ManageAccounts.updateProvider(list.get(0).getText(),list.get(1).getText(),list.get(2).getText(),list.get(3).getText(),Integer.parseInt(list.get(4).getText()));

			}
			catch(Exception ex){}
		}
	});

	static JButton deleteMemberButton = new JButton(new AbstractAction("Delete") {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				JPanel contentPane = panel;
				Component[] components = contentPane.getComponents();

				ArrayList<JTextField> list = new ArrayList<JTextField>();


				for (Component component : components) {
					if (component.getClass().equals(JTextField.class)) {
						list.add((JTextField)component);
					}
				}

				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this member?","Attention",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					// Delete code here
					ManageAccounts.deleteMember(Integer.parseInt(list.get(0).getText()));
					JOptionPane.showMessageDialog(null, "The member was deleted");
				}
			}
			catch(Exception ex){}
		}
	});

	static JButton deleteProviderButton = new JButton(new AbstractAction("Delete") {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				JPanel contentPane = panel;
				Component[] components = contentPane.getComponents();

				ArrayList<JTextField> list = new ArrayList<JTextField>();


				for (Component component : components) {
					if (component.getClass().equals(JTextField.class)) {
						list.add((JTextField)component);
					}
				}

				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this povider?","Attention",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					// Delete code here
					ManageAccounts.deleteProvider(Integer.parseInt(list.get(0).getText()));
					JOptionPane.showMessageDialog(null, "The provider was deleted");
				}
			}
			catch(Exception ex){}
		}
	});

	static JButton backToPTTerminalButton = new JButton(new AbstractAction("Back") {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				OpenPTerminal();
			}
			catch(Exception ex){}
		}
	});

	public static JPanel makeInputPanel(JButton button) {

		JPanel panel = new JPanel();
//	  	output = new JTextArea(15, 50);
//	    output.setWrapStyleWord(true);
//	    output.setEditable(false);
//	    JScrollPane scroller = new JScrollPane(output);
//	    scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//	    scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


		ButtonListener buttonListener = new ButtonListener();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(true);

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		input = new JTextField(20);
		enterButton = new JButton("Enter");
		enterButton.setActionCommand(ENTER);
		enterButton.addActionListener(buttonListener);

		input.setActionCommand(ENTER);
		input.addActionListener(buttonListener);

		//panel.add(scroller);
		//if(labelG != null)
		//	inputpanel.add(labelG);
		//inputpanel.add(input);
		//inputpanel.add(enterButton);
		//inputpanel.add(button);
		panel.add(inputpanel);

		return panel;
	}

	public static JPanel makeValidatePanel(JButton button) {

		JPanel panel = new JPanel();


		ButtonListener buttonListener = new ButtonListener();

		JButton backButton = backToPTTerminalButton;


		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(true);

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		input = new JTextField(20);
		enterButton = new JButton("Enter");
		enterButton.setActionCommand(ENTER);
		enterButton.addActionListener(buttonListener);

		inputpanel.add(backButton);

		input.setActionCommand(ENTER);
		input.addActionListener(buttonListener);

		//panel.add(scroller);
		if(labelG != null)
			inputpanel.add(labelG);
		inputpanel.add(input);
		//inputpanel.add(enterButton);
		inputpanel.add(button);
		panel.add(inputpanel);

		return panel;
	}


	public static JPanel makeBillChocAnInputPanel(JButton button) {

		JPanel BillChocAnpanel = new JPanel();

		ButtonListener buttonListener = new ButtonListener();

		JButton backButton = backToPTTerminalButton;

		BillChocAnpanel.setLayout(new BoxLayout(BillChocAnpanel, BoxLayout.Y_AXIS));
		BillChocAnpanel.setOpaque(true);

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		JLabel lblDateOfService = new JLabel("Enter date of service (MM-DD-YYYY): ");
		lblDateOfService.setAlignmentY(LEFT_ALIGNMENT);
		JTextField txtDateOfService = new JTextField(20);
		txtDateOfService.setAlignmentX(Component.CENTER_ALIGNMENT);

		inputpanel.add(backButton);

		JLabel lblProviderNumber = new JLabel("Enter provider number: ");
		JTextField txtProviderNumber = new JTextField(20);
		txtProviderNumber.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblServiceCode = new JLabel("Enter the six-digit service code: ");
		JTextField txtServiceCode = new JTextField(20);
		txtServiceCode.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblMemberNumber = new JLabel("Enter the member number: ");
		JTextField txtMemberNumber = new JTextField(20);
		txtMemberNumber.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand(ENTER);
		submitButton.addActionListener(buttonListener);
		submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		submitButton = button;


		input.setActionCommand(ENTER);
		input.addActionListener(buttonListener);

		inputpanel.add(lblDateOfService);
		inputpanel.add(txtDateOfService);
		inputpanel.add(lblProviderNumber);
		inputpanel.add(txtProviderNumber);
		inputpanel.add(lblServiceCode);
		inputpanel.add(txtServiceCode);
		inputpanel.add(lblMemberNumber);
		inputpanel.add(txtMemberNumber);

		inputpanel.add(submitButton);
		inputpanel.setLayout(new BoxLayout(inputpanel, BoxLayout.Y_AXIS));
		BillChocAnpanel.add(inputpanel);
		panel = inputpanel;

		return BillChocAnpanel;
	}

	public static JPanel makeAddMemberInputPanel(JButton button) {

		JPanel addMemberPanel = new JPanel();

		ButtonListener buttonListener = new ButtonListener();

		JButton backButton = backToPTTerminalButton;

		addMemberPanel.setLayout(new BoxLayout(addMemberPanel, BoxLayout.Y_AXIS));
		addMemberPanel.setOpaque(true);

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		JLabel lblMemberName = new JLabel("Enter the member's name: ");
		lblMemberName.setAlignmentY(LEFT_ALIGNMENT);
		JTextField txtMemberName = new JTextField(20);
		txtMemberName.setAlignmentX(Component.CENTER_ALIGNMENT);

		inputpanel.add(backButton);

		JLabel lblMemberNumber = new JLabel("Enter the member number: ");
		JTextField txtMemberNumber = new JTextField(20);
		txtMemberNumber.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblMemberAddress = new JLabel("Enter the member's address: ");
		JTextField txtMemberAddress = new JTextField(20);
		txtMemberAddress.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblMemberCity = new JLabel("Enter the member's city: ");
		JTextField txtMemberCity = new JTextField(20);
		txtMemberCity.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblMemberState = new JLabel("Enter the member's state: ");
		JTextField txtMemberState = new JTextField(20);
		txtMemberState.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblMemberZipCode = new JLabel("Enter the member's zip code: ");
		JTextField txtMemberZipCode = new JTextField(20);
		txtMemberZipCode.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand(ENTER);
		submitButton.addActionListener(buttonListener);
		submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		submitButton = button;


		input.setActionCommand(ENTER);
		input.addActionListener(buttonListener);

		inputpanel.add(lblMemberName);
		inputpanel.add(txtMemberName);
		inputpanel.add(lblMemberNumber);
		inputpanel.add(txtMemberNumber);
		inputpanel.add(lblMemberAddress);
		inputpanel.add(txtMemberAddress);
		inputpanel.add(lblMemberCity);
		inputpanel.add(txtMemberCity);
		inputpanel.add(lblMemberState);
		inputpanel.add(txtMemberState);
		inputpanel.add(lblMemberZipCode);
		inputpanel.add(txtMemberZipCode);

		inputpanel.add(submitButton);
		inputpanel.setLayout(new BoxLayout(inputpanel, BoxLayout.Y_AXIS));
		addMemberPanel.add(inputpanel);
		panel = inputpanel;

		return addMemberPanel;
	}

	public static JPanel makeAddProviderInputPanel(JButton button) {

		JPanel addProviderPanel = new JPanel();

		ButtonListener buttonListener = new ButtonListener();

		JButton backButton = backToPTTerminalButton;

		addProviderPanel.setLayout(new BoxLayout(addProviderPanel, BoxLayout.Y_AXIS));
		addProviderPanel.setOpaque(true);

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		JLabel lblProviderName = new JLabel("Enter the Provider's name: ");
		lblProviderName.setAlignmentY(LEFT_ALIGNMENT);
		JTextField txtProviderName = new JTextField(20);
		txtProviderName.setAlignmentX(Component.CENTER_ALIGNMENT);

		inputpanel.add(backButton);

		JLabel lblProviderNumber = new JLabel("Enter the Provider number: ");
		JTextField txtProviderNumber = new JTextField(20);
		txtProviderNumber.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblProviderAddress = new JLabel("Enter the Provider's address: ");
		JTextField txtProviderAddress = new JTextField(20);
		txtProviderAddress.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblProviderCity = new JLabel("Enter the Provider's city: ");
		JTextField txtProviderCity = new JTextField(20);
		txtProviderCity.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblProviderState = new JLabel("Enter the Provider's state: ");
		JTextField txtProviderState = new JTextField(20);
		txtProviderState.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblProviderZipCode = new JLabel("Enter the Provider's zip code: ");
		JTextField txtProviderZipCode = new JTextField(20);
		txtProviderZipCode.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand(ENTER);
		submitButton.addActionListener(buttonListener);
		submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		submitButton = button;


		input.setActionCommand(ENTER);
		input.addActionListener(buttonListener);

		inputpanel.add(lblProviderName);
		inputpanel.add(txtProviderName);
		inputpanel.add(lblProviderNumber);
		inputpanel.add(txtProviderNumber);
		inputpanel.add(lblProviderAddress);
		inputpanel.add(txtProviderAddress);
		inputpanel.add(lblProviderCity);
		inputpanel.add(txtProviderCity);
		inputpanel.add(lblProviderState);
		inputpanel.add(txtProviderState);
		inputpanel.add(lblProviderZipCode);
		inputpanel.add(txtProviderZipCode);

		inputpanel.add(submitButton);
		inputpanel.setLayout(new BoxLayout(inputpanel, BoxLayout.Y_AXIS));
		addProviderPanel.add(inputpanel);
		panel = inputpanel;

		return addProviderPanel;
	}

	public static JPanel makeUpdateMemberInputPanel(JButton button) throws FileNotFoundException, IOException {

		JPanel updateMemberPanel = new JPanel();
		ArrayList<MemberAccounts> members = Terminal.ReadMember();		
		 
		ArrayList<String> names = new ArrayList<String>();
		for(MemberAccounts m : members){
			names.add(m.getName());
		}
		
		JComboBox cbMembers = new JComboBox (names.toArray());
		
		
		ButtonListener buttonListener = new ButtonListener();
		

		JButton backButton = backToPTTerminalButton;

		updateMemberPanel.setLayout(new BoxLayout(updateMemberPanel, BoxLayout.Y_AXIS));
		updateMemberPanel.setOpaque(true);

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		JLabel lblMemberName = new JLabel("Enter the member's name: ");
		lblMemberName.setAlignmentY(LEFT_ALIGNMENT);
		JTextField txtMemberName = new JTextField(20);
		txtMemberName.setAlignmentX(Component.CENTER_ALIGNMENT);

		inputpanel.add(backButton);

		JLabel lblMemberAddress = new JLabel("Enter the member's address: ");
		JTextField txtMemberAddress = new JTextField(20);
		txtMemberAddress.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblMemberCity = new JLabel("Enter the member's city: ");
		JTextField txtMemberCity = new JTextField(20);
		txtMemberCity.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblMemberState = new JLabel("Enter the member's state: ");
		JTextField txtMemberState = new JTextField(20);
		txtMemberState.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblMemberZipCode = new JLabel("Enter the member's zip code: ");
		JTextField txtMemberZipCode = new JTextField(20);
		txtMemberZipCode.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand(ENTER);
		submitButton.addActionListener(buttonListener);
		submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		submitButton = button;

		ItemListener itemListener = new ItemListener(){
			 public void itemStateChanged(ItemEvent itemEvent) {
				 String name = cbMembers.getSelectedItem().toString();
				 
				 for (MemberAccounts m : members) {
				        if (m.getName() == name) {
				        	txtMemberName.setText(m.getName());
				        	txtMemberAddress.setText(m.getAddress());
				        	txtMemberCity.setText(m.getCity());
				        	txtMemberState.setText(m.getState());
				        	txtMemberZipCode.setText(String.valueOf(m.getZipCode()));				          				          
				        }
				      } 
			       
			      }
		};
		
		cbMembers.addItemListener(itemListener);
		
		for (MemberAccounts m : members) {
	        if (m.getName() == names.get(0)) {
	        	txtMemberName.setText(m.getName());
	        	txtMemberAddress.setText(m.getAddress());
	        	txtMemberCity.setText(m.getCity());
	        	txtMemberState.setText(m.getState());
	        	txtMemberZipCode.setText(String.valueOf(m.getZipCode()));				          				          
	        }
	      } 
       
      
		

		input.setActionCommand(ENTER);
		input.addActionListener(buttonListener);

		inputpanel.add(cbMembers);
		inputpanel.add(lblMemberName);
		inputpanel.add(txtMemberName);
		inputpanel.add(lblMemberAddress);
		inputpanel.add(txtMemberAddress);
		inputpanel.add(lblMemberCity);
		inputpanel.add(txtMemberCity);
		inputpanel.add(lblMemberState);
		inputpanel.add(txtMemberState);
		inputpanel.add(lblMemberZipCode);
		inputpanel.add(txtMemberZipCode);

		inputpanel.add(submitButton);
		inputpanel.setLayout(new BoxLayout(inputpanel, BoxLayout.Y_AXIS));
		updateMemberPanel.add(inputpanel);
		panel = inputpanel;

		return updateMemberPanel;
	}
	
	
	public static JPanel makeUpdateProviderInputPanel(JButton button) {

		JPanel addProviderPanel = new JPanel();

		ButtonListener buttonListener = new ButtonListener();

		JButton backButton = backToPTTerminalButton;

		addProviderPanel.setLayout(new BoxLayout(addProviderPanel, BoxLayout.Y_AXIS));
		addProviderPanel.setOpaque(true);

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		JLabel lblProviderName = new JLabel("Enter the Provider's name: ");
		lblProviderName.setAlignmentY(LEFT_ALIGNMENT);
		JTextField txtProviderName = new JTextField(20);
		txtProviderName.setAlignmentX(Component.CENTER_ALIGNMENT);

		inputpanel.add(backButton);

		JLabel lblProviderAddress = new JLabel("Enter the Provider's address: ");
		JTextField txtProviderAddress = new JTextField(20);
		txtProviderAddress.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblProviderCity = new JLabel("Enter the Provider's city: ");
		JTextField txtProviderCity = new JTextField(20);
		txtProviderCity.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblProviderState = new JLabel("Enter the Provider's state: ");
		JTextField txtProviderState = new JTextField(20);
		txtProviderState.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblProviderZipCode = new JLabel("Enter the Provider's zip code: ");
		JTextField txtProviderZipCode = new JTextField(20);
		txtProviderZipCode.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand(ENTER);
		submitButton.addActionListener(buttonListener);
		submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		submitButton = button;


		input.setActionCommand(ENTER);
		input.addActionListener(buttonListener);

		inputpanel.add(lblProviderName);
		inputpanel.add(txtProviderName);
		inputpanel.add(lblProviderAddress);
		inputpanel.add(txtProviderAddress);
		inputpanel.add(lblProviderCity);
		inputpanel.add(txtProviderCity);
		inputpanel.add(lblProviderState);
		inputpanel.add(txtProviderState);
		inputpanel.add(lblProviderZipCode);
		inputpanel.add(txtProviderZipCode);

		inputpanel.add(submitButton);
		inputpanel.setLayout(new BoxLayout(inputpanel, BoxLayout.Y_AXIS));
		addProviderPanel.add(inputpanel);
		panel = inputpanel;

		return addProviderPanel;
	}
	public static JPanel makeDeleteMemberInputPanel(JButton button) {

		JPanel deleteMemberPanel = new JPanel();

		ButtonListener buttonListener = new ButtonListener();

		JButton backButton = backToPTTerminalButton;

		deleteMemberPanel.setLayout(new BoxLayout(deleteMemberPanel, BoxLayout.Y_AXIS));
		deleteMemberPanel.setOpaque(true);

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		JLabel lblMemberName = new JLabel("Enter the member's number: ");
		lblMemberName.setAlignmentY(LEFT_ALIGNMENT);
		JTextField txtMemberName = new JTextField(20);
		txtMemberName.setAlignmentX(Component.CENTER_ALIGNMENT);

		inputpanel.add(backButton);

		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand(ENTER);
		submitButton.addActionListener(buttonListener);
		submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		submitButton = button;


		input.setActionCommand(ENTER);
		input.addActionListener(buttonListener);

		inputpanel.add(lblMemberName);
		inputpanel.add(txtMemberName);


		inputpanel.add(submitButton);
		inputpanel.setLayout(new BoxLayout(inputpanel, BoxLayout.Y_AXIS));
		deleteMemberPanel.add(inputpanel);
		panel = inputpanel;

		return deleteMemberPanel;
	}


	public static JPanel makeDeleteProviderInputPanel(JButton button) {

		JPanel deleteProviderPanel = new JPanel();

		ButtonListener buttonListener = new ButtonListener();

		JButton backButton = backToPTTerminalButton;

		deleteProviderPanel.setLayout(new BoxLayout(deleteProviderPanel, BoxLayout.Y_AXIS));
		deleteProviderPanel.setOpaque(true);

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		JLabel lblProviderNumber = new JLabel("Enter the provider's number: ");
		lblProviderNumber.setAlignmentY(LEFT_ALIGNMENT);
		JTextField txtProviderNumber = new JTextField(20);
		txtProviderNumber.setAlignmentX(Component.CENTER_ALIGNMENT);

		inputpanel.add(backButton);

		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand(ENTER);
		submitButton.addActionListener(buttonListener);
		submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		submitButton = button;


		input.setActionCommand(ENTER);
		input.addActionListener(buttonListener);

		inputpanel.add(lblProviderNumber);
		inputpanel.add(txtProviderNumber);


		inputpanel.add(submitButton);
		inputpanel.setLayout(new BoxLayout(inputpanel, BoxLayout.Y_AXIS));
		deleteProviderPanel.add(inputpanel);
		panel = inputpanel;

		return deleteProviderPanel;
	}

}
