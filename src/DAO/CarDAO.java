package DAO;

import DTO.CarDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CarDAO {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<CarDTO> list = new ArrayList<>();

    public void registerCar(CarDTO carDto) {
        String SQLCommand = "INSERT INTO cars (id, brand, model, version, car_condition) VALUES (?, ?, ?, ?, ?)";

        conn = new ConnectionDAO().connectBD();

        try {
            pstm = conn.prepareStatement(SQLCommand);
            pstm.setString(1, carDto.getId());
            pstm.setString(2, carDto.getBrand());
            pstm.setString(3, carDto.getModel());
            pstm.setString(4, carDto.getVersion());
            pstm.setString(5, carDto.getCarCondition());

            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CarDAO Register" + erro);
        }
    }

    public ArrayList<CarDTO> listCars() {
        String sql = "SELECT * FROM cars";

        conn = new ConnectionDAO().connectBD();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                CarDTO carDto = new CarDTO();
                carDto.setId(rs.getString("id"));
                carDto.setBrand(rs.getString("brand"));
                carDto.setModel(rs.getString("model"));
                carDto.setVersion(rs.getString("version"));
                carDto.setCarCondition(rs.getString("car_condition"));

                list.add(carDto);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "CarDAO List" + error);
        }

        return list;
    }

    public void deleteCar(String carId) {
        String SQLCommand = "DELETE FROM cars WHERE id = ?";
        conn = new ConnectionDAO().connectBD();

        try {
            pstm = conn.prepareStatement(SQLCommand);
            pstm.setString(1, carId);
            pstm.executeUpdate();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CarDAO Delete" + erro);
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCar(CarDTO carDto) {
        String SQLCommand = "UPDATE cars SET brand=?, model=?, version=?, car_condition=? WHERE id=?";

        conn = new ConnectionDAO().connectBD();

        try {
            pstm = conn.prepareStatement(SQLCommand);
            pstm.setString(1, carDto.getBrand());
            pstm.setString(2, carDto.getModel());
            pstm.setString(3, carDto.getVersion());
            pstm.setString(4, carDto.getCarCondition());
            pstm.setString(5, carDto.getId());

            pstm.executeUpdate();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CarDAO Update" + erro);
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public CarDTO getCarById(String carId) {
        String SQLCommand = "SELECT * FROM cars WHERE id = ?";
        conn = new ConnectionDAO().connectBD();
        CarDTO car = null;

        try {
            pstm = conn.prepareStatement(SQLCommand);
            pstm.setString(1, carId);

            rs = pstm.executeQuery();

            if (rs.next()) {
                car = new CarDTO();
                car.setId(rs.getString("id"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setVersion(rs.getString("version"));
                car.setCarCondition(rs.getString("car_condition"));
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CarDAO" + erro);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return car;
    }
}
