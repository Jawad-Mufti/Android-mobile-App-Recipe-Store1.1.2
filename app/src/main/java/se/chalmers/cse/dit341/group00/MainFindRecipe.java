package se.chalmers.cse.dit341.group00;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import se.chalmers.cse.dit341.group00.model.recipe;
import se.chalmers.cse.wm1819.dit341template.R;

import static java.lang.System.err;


public class MainFindRecipe extends AppCompatActivity {
    public final String STATE_LEVEL = "IK";

    public static final String HTTP_PARAM = "httpResponse";

    // Camel camel =new Camel("","","");

    EditText editId;
  //  private  static  MyVolleyRequest instance;
    Button search;
    // TextView recipeView2 ;
    ArrayList<recipe> recipeList = new ArrayList<>();
    String inputId = null;
    TextView recipeView2;
    EditText editTitle;
    EditText editDescription;
    EditText editImage;
    Button editRecipe;
   // private IVolley iVolley;

//    public TextView getRecipeView2() {
//        return recipeView2;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_find_recipe);
       // Intent intent = getIntent();
        editId = (EditText) findViewById(R.id.editTextId);
        search = (Button) findViewById(R.id.button3);
        recipeView2 = (TextView) findViewById(R.id.RecipeView2);
        editTitle = (EditText) findViewById(R.id.editTextTitle);
        editDescription = (EditText) findViewById(R.id.editTextDescription);
        editImage = (EditText) findViewById(R.id.editTextImage);
       // editRecipe = (Button) findViewById(R.id.editBtn);

       // editRecipe.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View view) {
             //   editRecipeMethod();

        //    }
     //   });
    }




//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View v){
//
//               onClickGetRecipesByID();
//                                  }});
//    }


    public void onClickGetRecipesByID(View view) {

        final EditText editId = findViewById(R.id.editTextId);
        final TextView recipeView2 = findViewById(R.id.RecipeView2);
        final TextView recipeView3 = findViewById(R.id.recipeView3);
        final TextView recipeView4 = findViewById(R.id.recipeView4);


        String content = editId.getText().toString();

        //Get the text view in which we will show the result.

        String url = getString(R.string.server_url) + "/admin/recipes/" + content + "/comments/";
        //This uses Volley (Threading and a request queue is automatically handled in the background)
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // JSONArray jsArray = new JSONArray(recipeList);


                        //GSON allows to parse a JSON string/JSONObject directly into a user-defined class

                        String dataArray = null;
                        String description = null;
                        String image = null;


                        try {
                            //JsonParser parser = new JsonParser();
                            dataArray = response.getString("title");
                            description = response.getString("description");
                            image = response.getString("image");


                        } catch (JSONException e) {
                            Log.e(this.getClass().toString(), e.getMessage());
                        }

                        StringBuilder recipeString = new StringBuilder();
                        recipeString.append("This is the recipe that matches the id you entered \n");
                        //  recipeString.append("this is the title"+recipeString.getClass());

                        //  recipe[] recipes = gson.fromJson(dataArray, recipe[].class);
                        // recipe recipe = gson.fromJson(dataArray, recipe.class);
//                        Gson gson.fromJson(recipe.class) ;
//                        String json = gson.toJson(recipe.class);

                        recipeView2.setText(dataArray);
                        recipeView3.setText(description);
                        recipeView4.setText(image);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        recipeView2.setText("Error! " + error.toString());
                    }
                });

        //The request queue makes sure that HTTP requests are processed in the right order.
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {


        outState.putString(STATE_LEVEL, recipeView2.getText().toString());

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        recipeView2.setText(savedInstanceState.getString(STATE_LEVEL));
    }


//        btngoi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               // getRecipeById2(editId);
//                editId   = (EditText)findViewById(R.id.editTextId);
//                inputId=  editId.getText().toString();
//                //recipeView2 = (TextView)findViewById(R.id.t);
//               // recipeView2.setText("Welcome "+ recipeView2.getText().toString()+"!");
//            }
//        });
//


//        public String getRecipeById2(EditText RecipeId) {
//            for (int i = 0; i < recipeList.size(); i++) {
//                if (recipeList.g2et(i)._id.equals(RecipeId)) {
//                    return  recipeList.get(i)._id;
//                }
//            }
//            return "Not Found";
//        }
//    public void onClickGetRecipeByID (View view) {
//        //Get the text view in which we will show the result.
//        //  final TextView bookView = findViewById(R.id.recipeTextView);
//
//        String url = getString(R.string.server_url) + "/admin/recipes";
//        //This uses Volley (Threading and a request queue is automatically handled in the background)
//        RequestQueue queue = Volley.newRequestQueue(this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        //GSON allows to parse a JSON string/JSONObject directly into a user-defined class
//                        Gson gson = new Gson();
//
//                        String dataArray = null;
//                        editId   = (EditText)findViewById(R.id.editTextId);
//                        while (inputId!=null){
//                        inputId=  editId.getText().toString();}
//
//                        try {
//                            dataArray = response.getString("data");
//                        } catch (JSONException e) {
//                            Log.e(this.getClass().toString(), e.getMessage());
//                        }
//
//                        StringBuilder recipeString = new StringBuilder();
//                        recipeString.append("This is the list of my Recipes: \n");
//
//                       // recipe[] recipes = gson.fromJson(dataArray, recipe[].class);
//
//                        for (int i =0; i<recipeList.size();i++) {
//                            if (recipeList.get(i)._id.equals(inputId) ){
//                                recipeString.append("description: " + recipeList.get(i).description +"\n" + " image: "
//                                        + recipeList.get(i).image+"\n"+" title : "+ recipeList.get(i).title + "\n");
//                            }
//                        }
//
//
//
//
//
//                        recipeView2.setText(recipeString.toString());
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        recipeView2.setText("Error! " + error.toString());
//                    }
//                });
//
//        //The request queue makes sure that HTTP requests are processed in the right order.
//        queue.add(jsonObjectRequest);
//    }

//
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
//            } ){
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
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//    }


}

//
//}
////////////////////////////////////////////////////////////////////////////////////////////////
//
//        private void EditRecipe() {
//            try {
//              //   EditText editId = findViewById(R.id.editTextId);
//            String content2 = editId.getText().toString();
//
//            // final EditText editTitle = findViewById(R.id.editTextTitle);
//            //final EditText editDescription = findViewById(R.id.editTextDescription);
//            // final EditText editImage = findViewById(R.id.editTextImage);
//
//            String editTitle1 = editTitle.getText().toString();
//            String editDescription1 = editDescription.getText().toString();
//            String editImage1 = editImage.getText().toString();
////
//
//                if (editImage1.matches("") || editTitle1.matches("") || editDescription1.matches("")) { // handle empty inputs
//                    Context context = getApplicationContext();
//                    CharSequence text = "you can't enter an empty field";
//                    int duration = Toast.LENGTH_SHORT;
//
//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.show();
//                    return;
//
//                }
//
//
//                JSONObject jasonBody = new JSONObject();
//
//            jasonBody.put("title", editTitle1);
//            jasonBody.put("description", editDescription1);
//            jasonBody.put("image", editImage1);
//
//                String url = getString(R.string.server_url) + "/admin/recipes"+content2;
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jasonBody, new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //Toast.makeText(PostRecipeActivity.this,error+"",Toast.LENGTH_SHORT).show();
//                        onBackPressed();
//
//                    }
//                }) {
//
//                    public Map<String, String> getHeaders() throws AuthFailureError {
//                        final Map<String, String> params = new HashMap<>();
//                        params.put("Authorization", "Basic " + "c2FnYXJAa2FydHBheS5jb206cnMwM2UxQUp5RnQzNkQ5NDBxbjNmUDgzNVE3STAyNzI=");//put your token here
//
//                        return params;
//
//
//                    }
//
//                };
//
//
//                RequestQueue queue = Volley.newRequestQueue(this);
//                queue.add(jsonObjectRequest);
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//
//
//











