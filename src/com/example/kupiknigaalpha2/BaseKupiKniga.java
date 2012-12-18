package com.example.kupiknigaalpha2;


import android.content.Context;
import android.content.Intent;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class BaseKupiKniga extends SherlockFragmentActivity{
	
	
	 
	  @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getSupportMenuInflater().inflate(R.menu.activity_main, menu);
	        return true;
	    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.menu_logging:
			startActivity(new Intent(BaseKupiKniga.this, LoginActivity.class));
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	  
	  

}
