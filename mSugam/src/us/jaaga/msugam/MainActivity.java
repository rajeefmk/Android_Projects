package us.jaaga.msugam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText mOtherPartyTin ,mInvoiceNo ,mTaxableVal ,mVATVal ,mOtherVal ,mCommCode ,mPlace;
	Spinner mSpinnerType, mSpinnerCategory;
	String fOtherPartyTin ,fInvoiceNo ,fTaxableVal ,fVATVal ,fOtherVal ,fCommCode ,fPlace;
	String fSpinnerType ,fSpinnerCategory ,Message ,Number,finalSpinnerType,finalSpinnerCategory;
	Button mButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Layout Element Inflators
        mSpinnerType = (Spinner) findViewById(R.id.SpinnerType);
        mSpinnerCategory = (Spinner) findViewById(R.id.SpinnerCategory);
        
        mOtherPartyTin = (EditText) findViewById(R.id.OtherPartyTin);
        mInvoiceNo = (EditText) findViewById(R.id.InvoiceNo);
        mTaxableVal = (EditText) findViewById(R.id.TaxableVal);
        mVATVal = (EditText) findViewById(R.id.VATVal);
        mOtherVal = (EditText) findViewById(R.id.OtherVal);
        mCommCode = (EditText) findViewById(R.id.CommCode);
        mPlace = (EditText) findViewById(R.id.Place);
        
      //Getting data from Spinners
        fSpinnerType = mSpinnerType.getSelectedItem().toString();
        
      // Converting data from Spinner Type
        switch(fSpinnerType){
        
        case "WS-Within State":
        	finalSpinnerType = "WS";
        	break;
        case "IS-Interstate State":
        	finalSpinnerType = "IS";
        	break;
        	
        case "EX-Export":
        	finalSpinnerType = "EX";
        	break;
        
        case "IM-Import":
        	finalSpinnerType = "IM";
        	break;
        
        }
        
        //Getting selected item from Spinner Category
        
        fSpinnerCategory = mSpinnerCategory.getSelectedItem().toString();
        
        if(fSpinnerCategory=="THP-To His Principal"){
        	
        	finalSpinnerCategory = "THP";
        	
        }
        /*
        switch(fSpinnerCategory){
        
        case "SAL-After Sale":
        	finalSpinnerCategory = "SAL";
        	break;
        	
        case "PUR-After Purchase":
        	finalSpinnerCategory = "PUR";
        	break;
        	
        case "THP-To His Principal":
        	finalSpinnerCategory = "THP";
        	break;
        	
        case "SGS-To Shop/Go down/Storage":
        	finalSpinnerCategory = "SGS";
        	break;
        	
        case "JRL-Job work/Return/Line Sales":
        	finalSpinnerCategory = "JRL";
        	break;
        	
        case "CSD-Cons. Sale(Despatch)":
        	finalSpinnerCategory = "CSD";
        	break;
        
        case "CSR-Cons. Sale(Receipt)":
        	finalSpinnerCategory = "CSR";
        	break;
        	
        case "STD-Stock Transfer(Despatch)":
        	finalSpinnerCategory = "STD";
        	break;
        
        case "STR-Stock Transfer(Receipt)":
        	finalSpinnerCategory = "STR";
        	break;
        
        case "OTH-Others":
        	finalSpinnerCategory = "OTH";
        	break;
        }
*/
        
        mButton = (Button) findViewById(R.id.submitButton);
        
        mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Getting data from Other fields
				fOtherPartyTin = mOtherPartyTin.getText().toString().trim();
		        fInvoiceNo = mInvoiceNo.getText().toString().trim();
		        fTaxableVal = mTaxableVal.getText().toString().trim();
		        fVATVal = mVATVal.getText().toString().trim();
		        fOtherVal = mOtherVal.getText().toString().trim();
		        fCommCode = mCommCode.getText().toString().trim();
		        fPlace = mPlace.getText().toString().trim();
		        
		        Message = "KSR " + finalSpinnerType + " " + finalSpinnerCategory + " " +
	        			fOtherPartyTin + " " + fInvoiceNo + " " + fTaxableVal + " " + 
	        			fVATVal + " " + fOtherVal + " " + fCommCode + " " + " " + fPlace;
	        
	        
		        Number = "09663845414"; 

				
				SmsManager sms = SmsManager.getDefault();
				
				sms.sendTextMessage(Number, null, Message, null, null);
				
				Toast.makeText(MainActivity.this, "Accepted: Sending Message...", Toast.LENGTH_LONG).show();
				
				
			}
		});
    }
    
    
    
    
    
    
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }
    
}
