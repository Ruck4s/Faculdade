// EditCarForm.java
package VIEW;

import DAO.CarDAO;
import DTO.CarDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCarForm extends JFrame {
    private CarsView parentView;
    private CarDTO currentCar;

    private JTextField brandField;
    private JTextField modelField;
    private JTextField versionField;
    private JTextField conditionField;

    public EditCarForm(CarDTO currentCar, CarsView parentView) {
        super("Edit Car");

        this.parentView = parentView;
        this.currentCar = currentCar;

        setLayout(new GridLayout(5, 2));

        JLabel brandLabel = new JLabel("Brand:");
        brandField = new JTextField(currentCar.getBrand());

        JLabel modelLabel = new JLabel("Model:");
        modelField = new JTextField(currentCar.getModel());

        JLabel versionLabel = new JLabel("Version:");
        versionField = new JTextField(currentCar.getVersion());

        JLabel conditionLabel = new JLabel("Condition:");
        conditionField = new JTextField(currentCar.getCarCondition());

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCar();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeForm();
            }
        });

        add(brandLabel);
        add(brandField);
        add(modelLabel);
        add(modelField);
        add(versionLabel);
        add(versionField);
        add(conditionLabel);
        add(conditionField);
        add(updateButton);
        add(backButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(parentView);
        setVisible(true);
    }

    private void updateCar() {
        currentCar.setBrand(brandField.getText());
        currentCar.setModel(modelField.getText());
        currentCar.setVersion(versionField.getText());
        currentCar.setCarCondition(conditionField.getText());

        CarDAO carDAO = new CarDAO();
        carDAO.updateCar(currentCar);

        parentView.updateTableAfterEdit(currentCar);

        JOptionPane.showMessageDialog(this, "Car updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        closeForm();
    }

    private void closeForm() {
        setVisible(false);
        dispose();
    }
}
