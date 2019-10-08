package se.chalmers.cse.dit341.group00;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import se.chalmers.cse.wm1819.dit341template.R;


public class PostRecipeActivity extends AppCompatActivity {

        public static final String HTTP_PARAM = "httpResponse";

        // Camel camel =new Camel("","","");

        EditText editDescription ,editImage,editTitle;
        Button btngoi;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_post_recipe);
            editDescription=(EditText) findViewById(R.id.editTextDescription);
            editImage=(EditText) findViewById(R.id.editTextImage);
            editTitle=(EditText) findViewById(R.id.editTextTitle);
            btngoi = (Button) findViewById(R.id.button3);


            btngoi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InserSv();

                }
            });

        }


        private void InserSv(){
            try{

                String des1 = editDescription.getText().toString();
                String img1 = editImage.getText().toString();
                String title1 = editTitle.getText().toString();

                if(img1.matches("")|| title1.matches("") || des1.matches("") ){ // handle empty inputs
                    Context context = getApplicationContext();
                    CharSequence text = "you can't enter an empty field";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;

                }


                JSONObject jsonBody = new JSONObject();


                jsonBody.put("description", des1);
                jsonBody.put("image",img1);
                jsonBody.put("title",title1);







                String url = getString(R.string.server_url) + "/admin/recipes";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,jsonBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(PostRecipeActivity.this,error+"",Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    }
                }) {

                    public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> params = new HashMap<>();
                        params.put("Authorization", "Basic " + "c2FnYXJAa2FydHBheS5jb206cnMwM2UxQUp5RnQzNkQ5NDBxbjNmUDgzNVE3STAyNzI=");//put your token here

                        return params;


                    }

                };



                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(jsonObjectRequest);




            } catch (JSONException e) {
                e.printStackTrace();
            }



        }








    }


