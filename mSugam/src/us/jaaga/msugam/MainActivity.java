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
	String fSpinnerType ,fSpinnerCategory ,Message ,Number;
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
        fSpinnerCategory = mSpinnerCategory.getSelectedItem().toString();
        
        
        
        
        
        mButton = (Button) findViewById(R.id.submitButton);
        
        mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Getting data from Other fields
				fOtherPartyTin = mOtherPartyTin.getText().toString();
		        fInvoiceNo = mInvoiceNo.getText().toString();
		        fTaxableVal = mTaxableVal.getText().toString();
		        fVATVal = mVATVal.getText().toString();
		        fOtherVal = mOtherVal.getText().toString();
		        fCommCode = mCommCode.getText().toString();
		        fPlace = mPlace.getText().toString();
		        
		        Message = "KSR " + fSpinnerType + " " + fSpinnerCategory + " " +
	        			fOtherPartyTin + " " + fInvoiceNo + " " + fTaxableVal + " " + 
	        			fVATVal + " " + fOtherVal + " " + fCommCode + " " + " " + fPlace;
	        
	        
		        Number = "09212357123"; 

				
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
