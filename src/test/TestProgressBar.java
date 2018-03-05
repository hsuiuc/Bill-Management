package test;

import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestProgressBar {
    public static void main(String[] args) {
        GUIUtil.useLNF();

        JPanel panel = new JPanel();

        //new circle progress bar
        CircleProgressBar circleProgressBar = new CircleProgressBar();
        circleProgressBar.setBackgroundColor(ColorUtil.blueColor);
        circleProgressBar.setProgress(0);

        //new button
        JButton button = new JButton("click");

        //add components to panel
        panel.setLayout(new BorderLayout());
        panel.add(circleProgressBar, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);

        //show panel
        GUIUtil.showPanel(panel);

        //add event listener to button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        for (int i = 0; i < 100; i++) {
                            circleProgressBar.setProgress(i + 1);
                            circleProgressBar.setForegroundColor(ColorUtil.getByPercentage(i));

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }
                }.execute();
            }
        });
    }
}
