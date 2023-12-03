// AddCarForm.java
package VIEW;

import DAO.CarDAO;
import DTO.CarDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class AddCarForm extends JFrame {
    private CarsView parentView;

    private JTextField brandField;
    private JTextField modelField;
    private JTextField versionField;
    private JTextField car_conditionField;

    public AddCarForm(CarsView parentView) {
        super("Add Car");

        this.parentView = parentView;

        setLayout(new GridLayout(5, 2));

        JLabel brandLabel = new JLabel("Brand:");
        brandField = new JTextField();

        JLabel modelLabel = new JLabel("Model:");
        modelField = new JTextField();

        JLabel versionLabel = new JLabel("Version:");
        versionField = new JTextField();

        JLabel conditionLabel = new JLabel("Condition:");
        car_conditionField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    addCar();
                } else {
                    JOptionPane.showMessageDialog(AddCarForm.this, "All fields must be filled.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
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
        add(car_conditionField);
        add(addButton);
        add(backButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(parentView);
        setVisible(true);
    }

    private boolean validateFields() {
        return !brandField.getText().isEmpty() &&
               !modelField.getText().isEmpty() &&
               !versionField.getText().isEmpty() &&
               !car_conditionField.getText().isEmpty();
    }

    private void addCar() {
        CarDTO newCar = new CarDTO();
        newCar.setId(UUID.randomUUID().toString());
        newCar.setBrand(brandField.getText());
        newCar.setModel(modelField.getText());
        newCar.setVersion(versionField.getText());
        newCar.setCarCondition(car_conditionField.getText());

        CarDAO carDAO = new CarDAO();
        carDAO.registerCar(newCar);

        parentView.updateTableAfterAddition(newCar);

        JOptionPane.showMessageDialog(this, "Car added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        closeForm();
    }

    private void closeForm() {
        setVisible(false);
        dispose();
    }
}
