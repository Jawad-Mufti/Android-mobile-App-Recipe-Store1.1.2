package se.chalmers.cse.dit341.group00;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import se.chalmers.cse.wm1819.dit341template.R;

public class DeleteRecipeActivity extends AppCompatActivity {

    private EditText content;
    private TextView recipeView2;
    private EditText editTitle;
    private EditText editDescription;
    private EditText editImage;
    private Button editRecipe;
    private EditText editId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_recipe);
        // Intent intent = getIntent();
        editId = (EditText) findViewById(R.id.editTextId);
        recipeView2 = (TextView) findViewById(R.id.RecipeView2);
      //  editTitle = (EditText) findViewById(R.id.editText);
       // editDescription = (EditText) findViewById(R.id.editText2);
       // editImage = (EditText) findViewById(R.id.editText3);
         editRecipe = (Button) findViewById(R.id.editButton2);EditText content;
        content = (EditText) findViewById(R.id.editTextId2);



        editRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editRecipeMethod();

            }
        });
    }



    public void editRecipeMethod() {

        EditText editId = findViewById(R.id.editTextId2);
       // EditText editTitle = findViewById(R.id.editText);
        EditText editDescription = findViewById(R.id.editTextId2);
      //  EditText editImage = findViewById(R.id.editText3);
        String content = editId.getText().toString();
String titleString = editTitle.getText().toString();
        String descriptionString = editDescription.getText().toString();
        String imageString = editImage.getText().toString();
        String crappyPrefix = "null";
      //  HttpStack httpStack;
//        if(result.startsWith(crappyPrefix)){
//            result = result.substring(crappyPrefix.length(), result.length());
//        }
//        JSONObject jo = new JSONObject(result);

        if(imageString.matches("")|| titleString.matches("") || descriptionString.matches("") ){ // handle empty inputs
            Context context = getApplicationContext();
            CharSequence text = "you can't enter an empty field";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;

        }


          //  httpStack = new HttpClientStack(AndroidHttpClient.newInstance("Android"));
        //}
        String  URL = getString(R.string.server_url) + "/admin/recipes/" + content ;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_id:",this.editId);
//            jsonObject.put("annee", );
//            jsonObject.put("cru", "http://www.power-cellar.eu:5000/api/v1.0/crus/4");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest deleteRequest = new JsonObjectRequest(Request.Method.DELETE, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("onResponse", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                // Basic Authentication
                String credentials = "gustave@example.com:1234";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                String json;
                if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
                    try {
                        json = new String(volleyError.networkResponse.data,
                                HttpHeaderParser.parseCharset(volleyError.networkResponse.headers));
                    } catch (UnsupportedEncodingException e) {
                        return new VolleyError(e.getMessage());
                    }
                    return new VolleyError(json);
                }
                return volleyError;
            }
        };
        requestQueue.add(deleteRequest);
    }
}
//    public void editRecipeMethod() {
//        try {
//
//            //final EditText editId = findViewById(R.id.editTextId);
//            String content2 = editId.getText().toString();
//
//            // final EditText editTitle = findViewById(R.id.editTextTitle);
//            //final EditText editDescription = findViewById(R.id.editTextDescription);
//            // final EditText editImage = findViewById(R.id.editTextImage);
//
//            String editTitle1 = editTitle.getText().toString();
//            String editDescription1 = editDescription.getText().toString();
//            String editImage1 = editImage.getText().toString();
//
//
//            if (editImage1.matches("") || editTitle1.matches("") || editDescription1.matches("")) { // handle empty inputs
//                Context context = getApplicationContext();
//                CharSequence text = "you can't enter an empty field";
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
//                // return;
//
//            }
//
//            JSONObject jasonBody = new JSONObject();
//
//            jasonBody.put("title", editTitle1);
//            jasonBody.put("description", editDescription1);
//            jasonBody.put("image", editImage1);
//
//
//            String url2 = getString(R.string.server_url) + "/admin/recipes/" + content2;
//
//
//            StringRequest putRequest = new StringRequest(Request.Method.PUT, url2, jasonBody,  Response.Listener<JSONObject>(){
//
//                @Override
//                public void onResponse(String response) {
//                    // response
//                    Toast.makeText(getApplicationContext(), "response:  " + response.toString(), Toast.LENGTH_SHORT).show();
//
//                }
//
//                },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    // error
//                    onBackPressed();
//
//                }
//            });
//
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    final Map<String, String> params = new HashMap<>();
//                   params.put("Authorization", "Basic " + "c2FnYXJAa2FydHBheS5jb206cnMwM2UxQUp5RnQzNkQ5NDBxbjNmUDgzNVE3STAyNzI=");//put your token here
//
//                    return params;
//
//
//                }
//
//
////
//
//            };
//            RequestQueue queue = Volley.newRequestQueue(this);
//
//            queue.add(putRequest);
//
//
//        } catch (JSONException e){
//            e.printStackTrace();
//        }
//
//
//
//
//    }



    //////

//}