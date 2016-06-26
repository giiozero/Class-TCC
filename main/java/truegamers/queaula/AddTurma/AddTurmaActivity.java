package truegamers.queaula.AddTurma;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import truegamers.queaula.BancoController;
import truegamers.queaula.R;

public class AddTurmaActivity extends Activity {
    private Button Btngetdata, BtnAddData, BtnPular;
    private static EditText  TxtTurma=null;
    private JSONArray turma;
    private JSONObject itensDoArray;
    private ArrayList<String> lista, Data, Aula, Sala, Prof, Sigla, Materia;
    //JSON Node Names
    private static String TAG_TURMA;
    //URL to get JSON Array
    private static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addturma);
        Btngetdata = (Button)findViewById(R.id.getdata);
        BtnAddData = (Button) findViewById(R.id.adddata);
        BtnPular = (Button) findViewById(R.id.pular);
        BtnPular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), truegamers.queaula.MainActivity.class); //Inicio
                startActivity(i);
            }
        });
        BtnAddData.setVisibility(View.GONE);
        BtnAddData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BancoController crud = new BancoController(getBaseContext());
               for (int i=0; Aula.size() > i;i++ ) {

                     String resultado =   crud.insertAulas(TAG_TURMA,
                                Aula.get(i),
                                Data.get(i),
                                Materia.get(i),
                                Sigla.get(i),
                                Prof.get(i),
                                Sala.get(i));

                    Log.d("AddTurma", TAG_TURMA+" - "+ Aula.get(i) + " - "+Data.get(i));
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                }
            }
        });
        Btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONParse().execute();
            }
        });
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Variáveis
            TxtTurma = (EditText)findViewById(R.id.TxtTurma);
            TAG_TURMA = TxtTurma.getText().toString();
            url = "http://queaula.sytes.net:8080/json/json.php?turma="+ TAG_TURMA;
            pDialog = new ProgressDialog(AddTurmaActivity.this);
            pDialog.setMessage("Obtendo Dados ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            Log.d("URL=", url);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
                // Getting JSON Array
                 turma = json.getJSONArray(TAG_TURMA);

                //Criar um array de Strings, que será utilizado em seu ListActivity
                lista = new ArrayList<>();
                Data= new ArrayList<>(); Aula= new ArrayList<>(); Sala= new ArrayList<>(); Prof= new ArrayList<>(); Sigla= new ArrayList<>(); Materia= new ArrayList<>();
                for (int i=0; turma.length() > i;i++ ){
                    itensDoArray = turma.getJSONObject(i);
                    if (itensDoArray.get("materia").toString().equals("af")) {
                        lista.add("TURMA NÃO ENCONTRADA");
                        BtnAddData.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Turma não encontrada, tente novamente.", Toast.LENGTH_SHORT).show();
                    } else {
                        lista.add(i, ("Data: " + String.valueOf(itensDoArray.get("data")) +
                                " - Aula " + itensDoArray.get("aula") +
                                " - Sala " + itensDoArray.get("sala") +
                                " - Prof: " + itensDoArray.get("prof") +
                                " \n Sigla: "+itensDoArray.get("sigla") +
                                " - Materia: "+itensDoArray.get("materia")));
                        Log.d(null, String.valueOf(itensDoArray.get("data")));
                        Data.add(i, String.valueOf(itensDoArray.get("data")));
                        Aula.add(i, String.valueOf(itensDoArray.get("aula")));
                        Sala.add(i, String.valueOf(itensDoArray.get("sala")));
                        Prof.add(i, String.valueOf(itensDoArray.get("prof")));
                        Sigla.add(i, String.valueOf( itensDoArray.get("sigla")));
                        Materia.add(i, String.valueOf(itensDoArray.get("materia")));

                        //Hide Teclado
                        InputMethodManager imm = (InputMethodManager) getSystemService(
                                INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        BtnAddData.setVisibility(View.VISIBLE);
                    }
                }
                ListView listView = (ListView) findViewById(R.id.JsonResult);
                ArrayAdapter<String> zombiesAdapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1, lista);
                //Set JSON Data in ListView
                listView.setAdapter(zombiesAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
}