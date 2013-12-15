package ch.uzh.ifi.seal.soprafs14.group_00_android;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import ch.uzh.ifi.seal.soprafs14.group_00_android.service.RestService;
import ch.uzh.ifi.seal.soprafs14.group_00_android.service.beans.JsonUriWrapper;
import ch.uzh.ifi.seal.soprafs14.group_00_android.service.beans.UserRequestBean;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onClickCreateUserBtn(View v) {
    	
    	UserRequestBean userRequestBean = new UserRequestBean();
    	userRequestBean.setName(((EditText)findViewById(R.id.name)).getText().toString());
    	userRequestBean.setUsername(((EditText)findViewById(R.id.username)).getText().toString());
    	
    	new CreateUserTask().execute(userRequestBean);
    }
    
    private class CreateUserTask extends AsyncTask<UserRequestBean, Void, JsonUriWrapper> {
        @Override
        protected JsonUriWrapper doInBackground(UserRequestBean... userRequestBeans) {
          try {
	          ResponseEntity<JsonUriWrapper>response = RestService.post("/user", userRequestBeans[0], JsonUriWrapper.class);
	          if(HttpStatus.CREATED.equals(response.getStatusCode())) {
	        	  return response.getBody();
	          }
          } catch(RestClientException rce) {
        	  Log.e("Main", rce.getMessage());
          }
          
          return null;
        }

        @Override
        protected void onPostExecute(JsonUriWrapper result) {
        	if(result != null) {
        		((TextView)findViewById(R.id.logBox)).setText(result.getUri());
        	} else {
        		((TextView)findViewById(R.id.logBox)).setText("ERROR");
        	}
        }
      }
}
