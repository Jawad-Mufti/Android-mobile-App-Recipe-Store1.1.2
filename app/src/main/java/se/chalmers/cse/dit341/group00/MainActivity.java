package se.chalmers.cse.dit341.group00;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import se.chalmers.cse.wm1819.dit341template.R;
import se.chalmers.cse.dit341.group00.model.recipe;




public class MainActivity  extends AppCompatActivity {


    //Field for parameter name
    public static final String HTTP_PARAM = "httpResponse";
    ArrayList<recipe> recipeList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // recovering the instance state
        setContentView(R.layout.activity_main);
    }
    public void onClickRecipe(View view)
    {
        Intent intent = new Intent(this, MainRecipeActivity.class);
        startActivity(intent);
    }

    public void onClickGetRecipes(View view) {
        {
            Intent intent = new Intent(this, MainRecipeActivity.class);
            startActivity(intent);
        }
    }
    public void onClickNewActivity3 (View view) {
        //TextView mCamelView = findViewById(R.id.camelTextView);

        //Starts a new activity, providing the text from my HTTP text field as an input
        Intent intent = new Intent(this, PostRecipeActivity.class);
        //intent.putExtra(HTTP_PARAM, mCamelView.getText().toString());
        startActivity(intent);
    }
//    public void onClickDeleteRecipes(View view){
//
//
//
//
//}

//    public void onClickGetRecipeById (View view) {
//        //TextView mCamelView = findViewById(R.id.camelTextView);
//
//        //Starts a new activity, providing the text from my HTTP text field as an input
//        Intent intent = new Intent(this, MainFindRecipe.class);
//        //intent.putExtra(HTTP_PARAM, mCamelView.getText().toString());
//        startActivity(intent);
//    }

//
//    public String getNameById(String RecipeId) {
//        for (int i = 0; i < recipeList.size(); i++) {
//            if (recipeList.get(i)._id.equals(RecipeId)) {
//                return  recipeList.get(i).title;
//            }
//        }
//        return "Not Found";
//    }
    public void onClickGetRecipeByID(View view) {
        {
            Intent intent = new Intent(this, MainFindRecipe.class);
            startActivity(intent);
        }
    }
    public void onClickNewDeleteActivity(View view) {
        {
            Intent intent = new Intent(this, DeleteRecipeActivity.class);
            startActivity(intent);
        }
    }
    //   se.chalmers.cse.dit341.group00.model.recipe.delete();


}



