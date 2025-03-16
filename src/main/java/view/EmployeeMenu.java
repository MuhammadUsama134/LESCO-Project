package view;

import controller.BillingInfo;
import controller.Customer;
import controller.NADRADB;
import controller.TariffTaxInfo;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Reader;
import model.Writer;
import utility.Constants;
import utility.Help;

public class EmployeeMenu extends JFrame {

    private String userName;
    private String password;
    private ArrayList<Customer> custList;
    private ArrayList<BillingInfo> billList;
    private ArrayList<TariffTaxInfo> rates;
    private ArrayList<NADRADB> nadraInfo;

    public EmployeeMenu(String userName, String password) {
        this.userName = userName;
        this.password = password;
        custList = Reader.readCustomerData();
        billList = Reader.readBillingInfo();
        rates = Reader.readTariffInfo();
        nadraInfo = Reader.readNADRAInfo();
        init();
    }

    public void init() {
        setTitle("Employee Menu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(11, 1, 5, 5));
        addButton("Add Customer (New Meter)", e -> addCustomer());
        addButton("Add Bill", e -> addBillingInfo());
        addButton("View Bill", e -> viewBill());
        addButton("View Customer Info (Meter)", e -> viewCustomerInfo());
        addButton("View Tariff Info", e -> updateTariffInfo());
        addButton("View Nadra", e -> viewNadra());
        addButton("View Report of Paid and Unpaid Bills", e -> viewBillReports());
        addButton("View Report of CNIC Expiry", e -> viewCNICReports());
        addButton("Change Password", e -> changeEmployeePassword());
        addButton("Back", e -> backButton());
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void backButton() {
        dispose();
    }

    private void addButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        add(button);
    }

    private void addCustomer() {
        new AddCustomerInfo(custList, nadraInfo);
    }

    private void addBillingInfo() {
        new BillingInfoGUI(custList, billList, rates);
    }

    private void viewBill() {
        new ViewBillGUI(billList, custList, rates);
    }

    private void viewBillReports() {
        new ViewBillReportsGUI(billList);
    }

    private void viewCNICReports() {
        new ViewCNICReports(nadraInfo);
    }

    private void updateTariffInfo() {
        new UpdateTariffInfoGUI(rates);
    }

    private void changeEmployeePassword() {
        new ChangeEmpPass(userName, password);
    }

    private void viewCustomerInfo() {
        new ViewCutomerInfoGUI(nadraInfo, custList);
    }

    private void viewNadra() {
        new NADRAGUI(nadraInfo);
    }
}
