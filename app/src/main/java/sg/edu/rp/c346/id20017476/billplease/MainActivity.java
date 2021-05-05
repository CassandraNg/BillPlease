package sg.edu.rp.c346.id20017476.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView amt;
    EditText amtInput;
    TextView numPax;
    EditText numPaxInput;
    ToggleButton SVS;
    ToggleButton GST;
    TextView discount;
    EditText discountInput;
    RadioGroup rgPayment;
    Button split;
    Button reset;
    TextView totalBill;
    TextView eachBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt = findViewById(R.id.amountTextView);
        amtInput = findViewById(R.id.amountInput);
        numPax = findViewById(R.id.numOfPaxTextView);
        numPaxInput = findViewById(R.id.numOfPaxInput);
        SVS = findViewById(R.id.toggleButtonSvs);
        GST = findViewById(R.id.toggleButtonGst);
        discount = findViewById(R.id.discountTextView);
        discountInput = findViewById(R.id.discountInput);
        rgPayment = findViewById(R.id.radioGroupPayment);
        split = findViewById(R.id.buttonSplit);
        reset = findViewById(R.id.buttonReset);
        totalBill = findViewById(R.id.totalOutput);
        eachBill = findViewById(R.id.splitOutput);


        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pax = Integer.parseInt(numPaxInput.getText().toString());
                double totalAmt = Double.parseDouble(amtInput.getText().toString());

                if(SVS.isChecked()){
                    totalAmt = totalAmt *1.10;
                }
                else if (GST.isChecked()){
                    totalAmt = totalAmt *1.07;
                }
                else if(SVS.isChecked() && GST.isChecked()){
                    totalAmt = totalAmt *1.17;
                }
                else{
                    totalAmt = totalAmt;
                }

                Double discountAmt = Double.parseDouble(discountInput.getText().toString());
                if(discountAmt != 0) {
                    totalAmt = (1 - (discountAmt / 100)) * totalAmt;
                }
                totalBill.setText("Total Bill: $"+ String.format("%.2f",totalAmt));
                String eachBillAmt = "Each Pays: $"+ String.format("%.2f",totalAmt/pax);

                if(rgPayment.getCheckedRadioButtonId() == R.id.radioButtonCash){
                    eachBillAmt = eachBillAmt + " in cash";
                }
                else{
                    eachBillAmt = eachBillAmt +" via PayNow to 99830239";
                }
                eachBill.setText(eachBillAmt);


                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        amtInput.setText("");
                        numPaxInput.setText("");
                        SVS.setChecked(false);
                        GST.setChecked(false);
                        discountInput.setText("");
                        totalBill.setText("");
                        eachBill.setText("");
                    }

                });

            }
        });

    }
}