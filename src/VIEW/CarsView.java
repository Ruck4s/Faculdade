package VIEW;

import DAO.CarDAO;
import DTO.CarDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarsView extends JFrame {
    private CarDAO carDAO = new CarDAO();

    private JTable table;
    private DefaultTableModel model;
    private JButton deleteButton;
    private JButton editButton;
    private JButton backButton;
    private JButton addButton;

    public CarsView() {
        super("Registered Cars");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] columnNames = {"ID", "Marca", "Modelo", "Versão", "Condição"};
        model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        deleteButton = new JButton("Deletar Carro");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String carId = (String) model.getValueAt(selectedRow, 0);

                    int option = JOptionPane.showConfirmDialog(
                            CarsView.this,
                            "Tem certeza que quer deletar esse carro?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        deleteSelectedCar(carId);
                    }
                } else {
                    JOptionPane.showMessageDialog(CarsView.this, "Selecione um carro para deletar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        editButton = new JButton("Editar Carro");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String carId = (String) model.getValueAt(selectedRow, 0);

                    // Get current car information
                    CarDTO currentCar = carDAO.getCarById(carId);

                    // Display an edit form
                    showEditCarForm(currentCar);
                } else {
                    JOptionPane.showMessageDialog(CarsView.this, "Selecione um carro para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        addButton = new JButton("Adicionar Carro");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddCarForm();
            }
        });

        backButton = new JButton("Voltar");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OptionsScreen optionsScreen = new OptionsScreen();
                optionsScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                optionsScreen.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        populateTable();
    }

    public void addRow(String[] rowData) {
        model.addRow(rowData);
    }

    private void populateTable() {
        ArrayList<CarDTO> carList = carDAO.listCars();

        for (CarDTO car : carList) {
            Object[] row = { car.getId(), car.getBrand(), car.getModel(), car.getVersion(), car.getCarCondition() };
            model.addRow(row);
        }   
    }
    

     private void deleteSelectedCar(String carId) {
        carDAO.deleteCar(carId);

        model.setRowCount(0);
    }


    private void showEditCarForm(CarDTO currentCar) {
        EditCarForm editCarForm = new EditCarForm(currentCar, this);
        editCarForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editCarForm.setVisible(true);
    }

    private void showAddCarForm() {
        AddCarForm addCarForm = new AddCarForm(this);
        addCarForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addCarForm.setVisible(true);
    }

    public void updateTableAfterAddition(CarDTO newCar) {
        addRow(new String[]{newCar.getId(), newCar.getBrand(), newCar.getModel(), newCar.getVersion(), newCar.getCarCondition()});
    }

    public void updateTableAfterEdit(CarDTO updatedCar) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(updatedCar.getId())) {
                model.setValueAt(updatedCar.getBrand(), i, 1);
                model.setValueAt(updatedCar.getModel(), i, 2);
                model.setValueAt(updatedCar.getVersion(), i, 3);
                model.setValueAt(updatedCar.getCarCondition(), i, 4);
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarsView::new);
    }
}
