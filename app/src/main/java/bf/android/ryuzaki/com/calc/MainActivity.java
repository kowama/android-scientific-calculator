package bf.android.ryuzaki.com.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity{
    private TextView screenCal;
    private TextView screenRes;

    private String chaine;
    private double result, ans;
    private List<Double> operandes;
    private List<String> operators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenCal = (TextView) findViewById(R.id.screenCalc);
        screenRes = (TextView) findViewById(R.id.screenRes);


        chaine = new String();
        operandes = new ArrayList<Double>();
        operators = new ArrayList<String>();

    }

    public void onClickNumber(View v) {
        Button btn = (Button) v;
        chaine += btn.getText().toString();
        updateScreen();
    }
    public void onClickOperator(View v) {
        Button btn = (Button) v;
        chaine += btn.getText().toString();
        updateScreen();
        operators.add(btn.getText().toString());
    }
    public  void  onClickFunction(View v){
        Button btn = (Button) v;
        chaine += btn.getText().toString();
        updateScreen();
    }
    public  void  onClickControl(View v){
        Button btn = (Button) v;
        switch (btn.getId()) {
            case R.id.btnAns: {
                chaine += btn.getText().toString();
                operandes.add(ans);
                updateScreen();
                break;
            }
            case R.id.btnCLR: {
                clearAll();
                updateScreen();
                break;
            }
            default: {

            }

        }
    }
    public  void  onClickFormater(View v){
        Button btn = (Button) v;
        switch (btn.getId()) {
           case  R.id.btnErase: {
               chaine = chaine.substring(0, chaine.length() - 2);
               updateScreen();
               break;
           }
           default:{
               chaine += btn.getText().toString();
               updateScreen();
                break;
                }
            }
        }
    public void onClickOpEqual(View v) {
        makeComuputation();
    }

    /*



     */
    private  void makeComuputation(){
        String[] opds = chaine.split("\\D");
       if(opds.length >0){
           for (String opd : opds){
               operandes.add(Double.valueOf(opd));
           }
       }

        if (!operandes.isEmpty() && !operators.isEmpty()){
            result = operandes.get(0);
            for (int i=1 ; i < operandes.size(); i++){
                result =  simpleCalculation(result, operandes.get(i), operators.get(i-1) );
            }
            ans = result;
            updateScreen();
            clearAll();
        }else {
            if (operandes.size() == 1){
                result = operandes.get(0);
                screenRes.setText(String.valueOf(result));
                chaine = String.valueOf(result);
                clearAll();
            }else {
                clearAll();
                chaine = String.valueOf(result);
                screenRes.setText("Input is Empty");

            }
        }
    }
    private void clearAll(){
        operandes.clear();
        operators.clear();
        result = 0;
        chaine = "";
    }
    private  void  updateScreen(){
        screenCal.setText(chaine);
        screenRes.setText(String.valueOf(result));
    }
    private  double simpleCalculation(double op1, double op2, String operation){
        if (operation.equalsIgnoreCase(((Button)findViewById(R.id.btnAdd)).getText().toString())){
            return  op1 + op2;
        }else if (operation.equalsIgnoreCase( ((Button)findViewById(R.id.btnSoust)).getText().toString() )){
            return  op1 - op2;
        }else if (operation.equalsIgnoreCase(((Button)findViewById(R.id.btnMult)).getText().toString())){
            return  op1 * op2;
        }else if (operation.equalsIgnoreCase(((Button)findViewById(R.id.btnDiv)).getText().toString())){
            return  op1 / op2;
        }else {
            return Double.NaN;
        }
    }
}
