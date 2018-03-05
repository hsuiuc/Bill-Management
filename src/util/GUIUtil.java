package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIUtil {
    private static String imageFolder = "/home/haosun/IdeaProjects/Bill-Management/img";

    /**
     * set image and text for a button
     * @param button the button
     * @param fileName image file name
     * @param tip button text
     */
    public static void setImageIcon(JButton button, String fileName, String tip) {
        ImageIcon imageIcon = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(61, 81));
        button.setToolTipText(tip);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setText(tip);
    }

    /**
     * set components to one color
     * @param color the color
     * @param components the components
     */
    public static void setColor(Color color, JComponent... components) {
        for (JComponent component : components) {
            component.setForeground(color);
        }
    }

    /**
     * use liquid look and feel
     */
    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * center and show a panel
     * @param panel the panel
     * @param stretchRate 0-1, 1 means cover the whole frame
     */
    public static void showPanel(JPanel panel, double stretchRate) {
        GUIUtil.useLNF();
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        CenterPanel centerPanel = new CenterPanel(stretchRate);
        frame.setContentPane(centerPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        centerPanel.show(panel);
    }

    public static void showPanel(JPanel panel) {
        showPanel(panel, 0.8);
    }

    /**
     * check if input text is empty
     * @param textField the text field to check
     * @param input input string
     * @return false for empty, true otherwise
     */
    public static boolean checkEmpty(JTextField textField, String input) {
        String text = textField.getText().trim();
        if (text.length() == 0) {
            JOptionPane.showMessageDialog(null, input + " input text can not be empty");
            textField.grabFocus();
            return false;
        }
        return true;
    }

    /**
     * check input is a num
     * @param textField the text field to check
     * @param input input string
     * @return false for empty, true otherwise
     */
    public static boolean checkNumber(JTextField textField, String input) {
        if (!checkEmpty(textField, input)) {
            return false;
        }

        String text = textField.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, input + " input should be a number, input again");
            textField.grabFocus();
            return false;
        }
    }

    /**
     * check input is 0
     * @param textField the text field to check
     * @param input input string
     * @return false for 0, true otherwise
     */
    public static boolean checkZero(JTextField textField, String input) {
        if (!checkNumber(textField, input)) {
            return false;
        }

        String text = textField.getText().trim();
        if (Integer.parseInt(text) == 0) {
            JOptionPane.showMessageDialog(null, input + " input should not be 0, input again");
            textField.grabFocus();
            return false;
        }
        return true;
    }

    public static int getInt(JTextField textField) {
        return Integer.parseInt(textField.getText());
    }
}
