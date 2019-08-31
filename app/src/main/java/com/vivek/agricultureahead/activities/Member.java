package com.vivek.agricultureahead.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.vivek.agricultureahead.R;
import com.vivek.agricultureahead.utilities.DataGetter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Member {

    static class FetchData extends AsyncTask<String, String, String> {

        ProgressDialog loading;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // loading = ProgressDialog.show(getApplicationContext(), "", "Hold on.....", true, false);
        }

        @Override
        protected String doInBackground(String... arg0) {
            HashMap<String, String> data = new HashMap<>();

            DataGetter dataGetter = new DataGetter();

            String result = dataGetter.sendPostRequest("https://agricultureahead.000webhostapp.com/getData.php", data);



            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("JSON", result);
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {


                    JSONObject json = jsonArray.getJSONObject(i);
                    Log.i("JSON OBJ", json.getString("strings"));


                    loading.dismiss();

                    Integer[] hindiText = {R.string.crop_production_card_title_hi, R.string.treatment_card_title_hi,
                            R.string.storage_card_title_hi, R.string.horticulture_card_title_hi, R.string.policy_card_title_hi};

                    Integer[] englishText = {R.string.crop_production_card_title_en, R.string.treatment_card_title_en,
                            R.string.storage_card_title_en, R.string.horticulture_card_title_en, R.string.policy_card_title_en};

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    public Integer[] hindiTexts(){
        Integer[] data={R.string.crop_production_card_title_hi,R.string.treatment_card_title_hi,
                R.string.storage_card_title_hi,R.string.horticulture_card_title_hi,R.string.policy_card_title_hi};
        return data;

    }
    public Integer[] englishTexts() {
        Integer[] data = {R.string.crop_production_card_title_en,R.string.treatment_card_title_en,
                R.string.storage_card_title_en,R.string.horticulture_card_title_en,R.string.policy_card_title_en};
        return data;
    }
    public Integer[] imageUrls() {
        Integer[] data = {R.raw.production, R.raw.treat, R.raw.shc2, R.drawable.horticulture_main, R.raw.govp};
        return data;
    }
}
