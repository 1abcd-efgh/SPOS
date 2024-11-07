import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JTextField; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MathApp {
    public static void main(String[] args){
        JFrame frame = new JFrame("Math operations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,150);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel resultLable = new JLabel("Result: ");
        JTextField num1Field = new JTextField(10);
        JTextField num2Field = new JTextField(10);
        JButton addButton = new JButton("Add");
        JButton subtractButton = new JButton("Substarct");
        JButton multiButton = new JButton("Multiplication");
        JButton divButton = new JButton("Division");

        panel.add(new JLabel("Number 1:"));
        panel.add(num1Field);
        panel.add(new JLabel("Number 2:"));
        panel.add(num2Field);
        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiButton);
        panel.add(divButton);
        panel.add(resultLable);

        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    double num1 = Double.parseDouble(num1Field.getText());
                    double num2 = Double.parseDouble(num2Field.getText());
                    double result = MathOperation.add(num1,num2);
                    resultLable.setText("Result:" + result);
                }catch(NumberFormatException ex){
                    resultLable.setText("Invalid Input.");
                }
            }
        });

        subtractButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    double num1 = Double.parseDouble(num1Field.getText());
                    double num2 = Double.parseDouble(num2Field.getText());
                    double result = MathOperation.subtract(num1,num2);
                    resultLable.setText("Result:" + result);
                }catch(NumberFormatException ex){
                    resultLable.setText("Invalid Input.");
                }
            }
        });
        multiButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    double num1 = Double.parseDouble(num1Field.getText());
                    double num2 = Double.parseDouble(num2Field.getText());
                    double result = MathOperation.multi(num1,num2);
                    resultLable.setText("Result:" + result);
                }catch(NumberFormatException ex){
                    resultLable.setText("Invalid Input.");
                }
            }
        }));
        divButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    double num1 = Double.parseDouble(num1Field.getText());
                    double num2 = Double.parseDouble(num2Field.getText());
                    double result = MathOperation.div(num1,num2);
                    resultLable.setText("Result:" + result);
                }catch(NumberFormatException ex){
                    resultLable.setText("Invalid Input.");
                }
            }
        }));
        frame.setVisible(true);
    }
}