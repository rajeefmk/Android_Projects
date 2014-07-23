package us.jaaga.msugam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText mOtherPartyTin ,mInvoiceNo ,mTaxableVal ,mVATVal ,mOtherVal ,mCommCode ,mPlace;
	Spinner mSpinnerType, mSpinnerCategory;
	String fOtherPartyTin ,fInvoiceNo ,fTaxableVal ,fVATVal ,fOtherVal ,fCommCode ,fPlace;
	String Message ,Number, fsptype,fspcateg, finalSpinnerType, finalSpinnerCategory ;
	Button mButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Layout Element Inflators
        
        mOtherPartyTin = (EditText) findViewById(R.id.OtherPartyTin);
        mInvoiceNo = (EditText) findViewById(R.id.InvoiceNo);
        mTaxableVal = (EditText) findViewById(R.id.TaxableVal);
        mVATVal = (EditText) findViewById(R.id.VATVal);
        mOtherVal = (EditText) findViewById(R.id.OtherVal);
        mCommCode = (EditText) findViewById(R.id.CommCode);
        mPlace = (EditText) findViewById(R.id.Place);
        
      //Getting data from Spinner Type
        
           //Spinner layout being inflated
	       mSpinnerType = (Spinner) findViewById(R.id.SpinnerType);
	       
	       //Adapter which supplies the data to the spinner is defined here.
	       ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this, R.array.type_list, 
	    		   																android.R.layout.simple_spinner_item);
	       
	       adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	       
	       //OnItemSelectedListener is implemented and actions based on selection is made
	       mSpinnerType.setOnItemSelectedListener(new OnItemSelectedListener() {
	
				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int pos,
						long id) {
					
					fsptype = parent.getItemAtPosition(pos).toString();
					
					String marray[] = fsptype.split("-");
					
					finalSpinnerType = marray[0];
					
					
				}
		
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					
				}
		  });
	        
       //Getting selected item from Spinner Category
       
	       mSpinnerCategory = (Spinner) findViewById(R.id.SpinnerCategory);
	       
	       ArrayAdapter<CharSequence> adapterCategory= ArrayAdapter.createFromResource(this, R.array.category_list,
	    		   														android.R.layout.simple_spinner_item);
       
	       adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	       
	       mSpinnerCategory.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long arg3) {
				
				  fspcateg = parent.getSelectedItem().toString();
			       
			      String marray2[] = fspcateg.split("-");
			       
			      finalSpinnerCategory = marray2[0];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		   });
        
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
