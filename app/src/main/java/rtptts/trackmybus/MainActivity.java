package rtptts.trackmybus;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public AutoCompleteTextView from_act, to_act,busno_act;
    public String[] addressList = {"baglur cross", "yelhanka", "BSF cross", "bellali cross", "dwarka nagar", "baba nagar"};
    public String [] busnoList ={"289","245","290"};
    public Button search,go;
    public TextInputLayout fromTil,toTil,busnoTil;

    public String from,to,busno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        from_act = (AutoCompleteTextView) findViewById(R.id.from_act);
        to_act = (AutoCompleteTextView) findViewById(R.id.to_act);
        busno_act=(AutoCompleteTextView)findViewById(R.id.busno_act);

        fromTil=(TextInputLayout)findViewById(R.id.from_til);
        toTil=(TextInputLayout)findViewById(R.id.to_til);
        busnoTil=(TextInputLayout)findViewById(R.id.busno_til);

        search = (Button) findViewById(R.id.search_button);
        go=(Button) findViewById(R.id.go_button);

        ArrayAdapter address = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, addressList);

        from_act.setAdapter(address);
        from_act.setThreshold(2);

        to_act.setAdapter(address);
        to_act.setThreshold(2);

        ArrayAdapter busnumber = new
                ArrayAdapter(this,android.R.layout.simple_list_item_1, busnoList);

        busno_act.setAdapter(busnumber);
        busno_act.setThreshold(1);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                from=from_act.getText().toString().trim();
                to=to_act.getText().toString().trim();
                validRout();
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                busno=busno_act.getText().toString().trim();
                validBus();
            }
        });
    }

    private boolean validRout() {
        if(from.isEmpty()){
          fromTil.setError("Invalid");
            requestFocus(from_act);
            return false;
        }else{
            fromTil.setErrorEnabled(false);
        }
        if (to.isEmpty()){
            toTil.setError("Invalid");
            requestFocus(to_act);
            return false;
        }else{
            toTil.setErrorEnabled(false);
        }
        return true;
    }
    private  boolean validBus(){
        if (busno.isEmpty()){
            busnoTil.setError("Invalid");
            requestFocus(busno_act);
            return false;
        }else{
            busnoTil.setErrorEnabled(false);
        }
        return true;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
