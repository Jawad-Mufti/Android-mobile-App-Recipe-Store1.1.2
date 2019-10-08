package se.chalmers.cse.dit341.group00;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import se.chalmers.cse.wm1819.dit341template.R;
import se.chalmers.cse.dit341.group00.model.recipe;


public class MainRecipeActivity extends AppCompatActivity {
    TextView recipeView ;
   // ArrayList<recipe> recipeList = new ArrayList<>();
    public final String STATE_LEVEL="IK";


    //Field for parameter name
    public static final String HTTP_PARAM = "httpResponse";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // recovering the instance state


        setContentView(R.layout.activity_main_recipe);
        recipeView = findViewById(R.id.recipeTextView);
        Intent intent = getIntent();

    }








    public void onClickGetRecipes (View view) {
        //Get the text view in which we will show the result.
      //  final TextView bookView = findViewById(R.id.recipeTextView);

        String url = getString(R.string.server_url_recipes);

        //This uses Volley (Threading and a request queue is automatically handled in the background)
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //GSON allows to parse a JSON string/JSONObject directly into a user-defined class
                        Gson gson = new Gson();

                        String dataArray = null;

                        try {
                            dataArray = response.getString("data");
                        } catch (JSONException e) {
                            Log.e(this.getClass().toString(), e.getMessage());
                        }

                        StringBuilder recipeString = new StringBuilder();
                        recipeString.append("This is the list of my Recipes: \n \n \n");

                        recipe[] recipes = gson.fromJson(dataArray, recipe[].class);

                        for (recipe current : recipes) {
                            recipeString.append("description: " + current.description +"\n" + " image: "
                                    + current.image+"\n"+" title : "+ current.title + "\n"+"\n");
                        }

                        recipeView.setText(recipeString.toString());
                    ;}
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        recipeView.setText("Error! " + error.toString());
                    }
                });

        //The request queue makes sure that HTTP requests are processed in the right order.
        queue.add(jsonObjectRequest);
    }





    @Override
    public void onSaveInstanceState(Bundle outState) {


        outState.putString(STATE_LEVEL,recipeView.getText().toString());

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        recipeView.setText(savedInstanceState.getString(STATE_LEVEL));
    }


//
//
//    public void onClickGetRecipeByID (View view) {
//        //Get the text view in which we will show the result.
//        //  final TextView bookView = findViewById(R.id.recipeTextView);
//
//        String url = getString(R.string.server_url_recipes);
//
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
//                        recipe[] recipes = gson.fromJson(dataArray, recipe[].class);
//
//                        for (recipe current : recipes) {
//                            recipeString.append("description: " + current.description +"\n" + " image: "
//                                    + current.image+"\n"+" title : "+ current.title + "\n");
//                        }
//
//                        recipeView.setText(recipeString.toString());
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        recipeView.setText("Error! " + error.toString());
//                    }
//                });
//
//        //The request queue makes sure that HTTP requests are processed in the right order.
//        queue.add(jsonObjectRequest);
//    }



//    public String getRecipeById2(String RecipeId) {
//        for (int i = 0; i < recipeList.size(); i++) {
//            if (recipeList.get(i)._id.equals(RecipeId)) {
//                return  recipeList.get(i).title;
//            }
//        }
//        return "Not Found";
//    }


}



// make the same as get request but with no for loop
// change the url and put the id in the url as the edit text that the user inputs